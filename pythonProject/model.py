# -*- coding: utf-8 -*-
import pandas as pd
import numpy as np
from sklearn.model_selection import train_test_split
from sklearn.multioutput import MultiOutputClassifier
from xgboost import XGBClassifier, XGBRegressor
import joblib
from sklearn.preprocessing import LabelEncoder

# 1. 读取数据
data_path = r"dataset\input_file.csv"
df = pd.read_csv(data_path)

# 2. 定义各类列名

# 标识列（不作为特征）
id_col = '住院号'

# 多分类变量（后续将使用标签编码）
multiclass_cols = [
    '患者基本信息_入院主诊断名称',
    '心功能分级_NYHA分级',
    '患者身体状态_ASA分级',
    '患者身体状态_NRS2002(营养风险筛查评分)',
    '主要手术部位',
    '手术名称'
]

# 二分类变量（特征）
binary_cols = [
    '患者基本信息_性别',
    '饮酒史_饮酒',
    '吸烟史_吸烟',
    '高血压_高血压是否需要药物治疗',
    '胸部CT_肺部炎症',
    '心脏超声_左室舒张功能减低',
    '呼吸功能_严重COPD病史',
    '术前情况_手术前30天的充血性心力衰竭',
    '肾功能不全_是否透析',
    '肾功能不全_急性肾功能衰竭',
    '呼吸功能_呼吸困难',
    '术前情况_手术前48小时出现全身性败血症'
]

# 数值型变量（如果需要在特征预处理中单独使用，可在此定义）
numeric_cols = [
    '患者基本信息_年龄_岁',
    '患者基本信息_BMI_kg/cm2',
    '高压',
    '低压',
    '肝功能_白蛋白_g/L',
    '血凝常规_D-二聚体_ng/ml',
    '血凝常规_APTT_sec',
    '血凝常规_PT_sec',
    '肝功能_前白蛋白_mg/L',
    '血常规_血红蛋白_g/L',
    '肝功能_总胆红素_umol/L',
    '肝功能_直接胆红素_umol/L',
    '血常规_白细胞_10^9/L',
    '电解质_钾_mmol/L',
    '血糖_空腹血糖值_mmol/L',
    '血脂_甘油三酯_mmol/L',
    '肝功能_间接胆红素_umol/L',
    '血凝常规_抗凝血酶III_%',
    '肾功能_尿素氮_mmmol/L',
    '肾功能_Cr_umol/L',
    '肝功能_ALT_U/L',
    '肝功能_AST_U/L'
]

# 标签列：18个二分类标签和1个回归标签（住院天数）
class_labels = [
    '心肌梗死', '脑出血', '脑栓塞', '脑梗死', '呼吸衰竭',
    '肺炎', '呼吸机依赖', '肺栓塞', '下肢深静脉血栓',
    '浅表切口感染（切口感染_裂开）', '吻合口瘘', '体腔内器官周围感染',
    '腹部CT_腹腔出血', '腹部CT_腹腔积液', '非计划再手术',
    '出血（血管外科）', '肠粘连(胃肠外科)', '肠梗阻(胃肠外科)'
]
regression_label = '住院情况_住院天数'

# 3. 数据预处理
# 删除标识列
df = df.drop(columns=[id_col])

# 对特征中的二分类变量进行处理：如果数据为字符串“是”/“否”，映射为 1/0,“男”/“女”→“1/0”映射
for col in binary_cols:
    # 针对性别单独处理
    if col == '患者基本信息_性别':
        df[col] = df[col].map({'男': 1, '女': 0})
    else:
        df[col] = df[col].map({'是': 1, '否': 0})

# 对多分类变量使用标签编码
# 注意：这里将所有多分类变量转换为字符串再编码，避免因数据中存在nan等问题出错
le_dict = {}  # 用于存储每个变量对应的LabelEncoder（如后续需对新数据进行同样编码，可复用）
for col in multiclass_cols:
    le = LabelEncoder()
    df[col] = le.fit_transform(df[col].astype(str))
    le_dict[col] = le

# 分离特征和标签
# 特征：去除所有标签列（包括分类和回归标签）
X = df.drop(columns=class_labels + [regression_label])

# 标签（注意：这里先复制一份，避免 SettingWithCopyWarning）
y_class = df[class_labels].copy()
y_reg = df[regression_label].copy()

# 对二分类标签进行转换：将“是”/“否”映射为 1/0，并确保类型为整数
for col in class_labels:
    if y_class[col].dtype == 'object':
        y_class[col] = y_class[col].map({'是': 1, '否': 0})
    y_class[col] = y_class[col].astype(int)

# 确保回归标签为数值型
y_reg = pd.to_numeric(y_reg, errors='coerce')


# 4. 划分训练集和测试集（这里测试集占0.1%，设置随机种子保证复现）
X_train, X_test, y_class_train, y_class_test, y_reg_train, y_reg_test = train_test_split(
    X, y_class, y_reg, test_size=0.001, random_state=42
)

# 5. 使用 XGBoost 训练模型

# 5.1 训练分类模型：使用 MultiOutputClassifier 包装 XGBClassifier
xgb_clf = XGBClassifier(use_label_encoder=False, eval_metric='logloss', random_state=42)
clf = MultiOutputClassifier(xgb_clf)
clf.fit(X_train, y_class_train)

# 5.2 训练回归模型：用于预测住院天数
reg = XGBRegressor(objective='reg:squarederror', random_state=42)
reg.fit(X_train, y_reg_train)

# 6. 保存训练后的模型文件及标签编码器
joblib.dump(clf, 'model/xgb_classification_model.pkl')
joblib.dump(reg, 'model/xgb_regression_model.pkl')
joblib.dump(le_dict, 'model/label_encoders.pkl')  # 保存LabelEncoder字典
print("模型及标签编码器已保存！")

# 7. 加载模型并进行预测

# 加载保存的模型
clf_loaded = joblib.load('model/xgb_classification_model.pkl')
reg_loaded = joblib.load('model/xgb_regression_model.pkl')

# 7.1 分类预测：得到每个二分类标签为“是”（正类）的概率
# predict_proba 返回的列表中，每个元素为对应标签的预测概率数组，形状为 (n_samples, 2)
predicted_class_probs = clf_loaded.predict_proba(X_test)

# 整理各标签正类（索引 1）的概率为 DataFrame
class_prob_df = pd.DataFrame()
for idx, col in enumerate(class_labels):
    class_prob_df[col] = predicted_class_probs[idx][:, 1]

# 将分类概率转换为百分比格式（字符串形式，保留两位小数）
class_prob_df = class_prob_df.applymap(lambda x: f"{x * 100:.2f}%")

# 7.2 回归预测：预测住院天数，并取整为整数
predicted_days = reg_loaded.predict(X_test)
predicted_days = np.rint(predicted_days).astype(int)  # 四舍五入取整

# 7.3 合并输出预测结果：分类结果以百分比表示，住院天数为整数
result_df = class_prob_df.copy()
result_df[regression_label] = predicted_days

print("\n预测结果（部分展示）：")
print(result_df.head())
