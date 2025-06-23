import pandas as pd
import numpy as np

# 读取CSV文件
file_path = 'dataset/input_file.csv'
# 尝试使用不同的编码格式，直到找到正确的编码
encoding_list = ['utf-8', 'gb18030', 'gbk', 'latin1', 'iso-8859-1', 'cp1252']
for encoding in encoding_list:
    try:
        df = pd.read_csv('dataset/input_file.csv', encoding=encoding)
        # 如果读取成功，打印列名
        print(f"使用编码 {encoding} 读取成功。")
        print("CSV文件中的标签列字段名：")
        print(df.columns)
        break
    except UnicodeDecodeError:
        print(f"使用编码 {encoding} 读取失败，尝试下一个编码。")

# 选择需要的列
required_columns = [
    '住院号', '患者基本信息_年龄_岁', '患者基本信息_入院主诊断名称', '患者基本信息_BMI_kg/cm2',
    '心功能分级_NYHA分级', '患者身体状态_ASA分级', '患者身体状态_NRS2002(营养风险筛查评分)', '主要手术部位',
    '手术名称', '患者基本信息_性别', '饮酒史_饮酒', '吸烟史_吸烟', '高压', '低压',
    '高血压_高血压是否需要药物治疗', '肝功能_白蛋白_g/L', '血凝常规_D-二聚体_ng/ml', '血凝常规_APTT_sec',
    '血凝常规_PT_sec', '肝功能_前白蛋白_mg/L', '血常规_血红蛋白_g/L', '肝功能_总胆红素_umol/L',
    '肝功能_直接胆红素_umol/L', '血常规_白细胞_10^9/L', '电解质_钾_mmol/L', '血糖_空腹血糖值_mmol/L',
    '血脂_甘油三酯_mmol/L', '肝功能_间接胆红素_umol/L', '血凝常规_抗凝血酶III_%', '肾功能_尿素氮_mmmol/L',
    '肾功能_Cr_umol/L', '肝功能_ALT_U/L', '肝功能_AST_U/L', '胸部CT_肺部炎症', '心脏超声_左室舒张功能减低',
    '呼吸功能_严重COPD病史', '术前情况_手术前30天的充血性心力衰竭', '肾功能不全_是否透析', '肾功能不全_急性肾功能衰竭',
    '呼吸功能_呼吸困难', '术前情况_手术前48小时出现全身性败血症', '心肌梗死', '脑出血', '脑栓塞', '脑梗死', '呼吸衰竭',
    '肺炎', '呼吸机依赖', '肺栓塞', '下肢深静脉血栓', '浅表切口感染（切口感染_裂开）', '吻合口瘘',
    '体腔内器官周围感染', '腹部CT_腹腔出血', '腹部CT_腹腔积液', '非计划再手术',
    '出血（血管外科）', '肠粘连(胃肠外科)', '肠梗阻(胃肠外科)', '住院情况_住院天数'
]
# 选择并保存需要的列
df_filtered = df[required_columns]

# 确保选择的列在DataFrame中是唯一的
df_filtered = df[required_columns].copy()
df_filtered = df_filtered.loc[:, ~df_filtered.columns.duplicated()]

# 对数值型列进行均值填充（保留两位小数）
numerical_columns = df_filtered.select_dtypes(include=[np.number]).columns
for col in numerical_columns:
    df_filtered.loc[:, col] = df_filtered[col].fillna(round(df_filtered[col].mean(), 2))

# 对布尔型列进行处理
binary_columns = ['饮酒史_饮酒', '吸烟史_吸烟', '高血压_高血压是否需要药物治疗', '胸部CT_肺部炎症', '心脏超声_左室舒张功能减低',
                  '呼吸功能_严重COPD病史', '术前情况_手术前30天的充血性心力衰竭', '肾功能不全_是否透析', '肾功能不全_急性肾功能衰竭',
                  '呼吸功能_呼吸困难', '术前情况_手术前48小时出现全身性败血症', '心肌梗死', '脑出血', '脑栓塞', '脑梗死',
                  '呼吸衰竭', '肺炎', '呼吸机依赖', '肺栓塞', '下肢深静脉血栓', '浅表切口感染（切口感染_裂开）',
                  '吻合口瘘', '体腔内器官周围感染', '非计划再手术', '腹部CT_腹腔出血', '腹部CT_腹腔积液','出血（血管外科）']

for col in binary_columns:
    df_filtered[col] = df_filtered[col].astype('str').fillna(np.random.choice(['否', '是'], p=[0.85, 0.15]))

# 对'主要手术部位'字段填充为'胃'
df_filtered.loc[:, '主要手术部位'] = df_filtered['主要手术部位'].fillna('胃')

# 将指定字段移动到最后
columns_to_move = [
    '心肌梗死', '脑出血', '脑栓塞', '脑梗死', '呼吸衰竭', '肺炎', '呼吸机依赖', '肺栓塞', '下肢深静脉血栓',
    '浅表切口感染（切口感染_裂开）', '吻合口瘘', '体腔内器官周围感染', '腹部CT_腹腔出血', '腹部CT_腹腔积液',
    '非计划再手术', '出血（血管外科）', '肠粘连(胃肠外科)', '肠梗阻(胃肠外科)', '住院情况_住院天数'
]
df_filtered = df_filtered[[col for col in df_filtered.columns if col not in columns_to_move] + columns_to_move]



# 保存结果到新的CSV文件
output_file_path = 'dataset/output_file2.csv'
df_filtered.to_csv(output_file_path, index=False)


