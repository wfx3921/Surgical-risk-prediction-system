import pandas as pd
from sklearn.preprocessing import LabelEncoder, StandardScaler
from sklearn.linear_model import LogisticRegression
from sklearn.model_selection import train_test_split, GridSearchCV, StratifiedKFold
from sklearn.ensemble import RandomForestClassifier, GradientBoostingClassifier, VotingClassifier
import joblib

# 尝试使用不同的编码格式，直到找到正确的编码
encoding_list = ['utf-8', 'gb18030', 'gbk', 'latin1', 'iso-8859-1', 'cp1252']
for encoding in encoding_list:
    try:
        data = pd.read_csv('dataset/output_file2.csv', encoding=encoding)
        print(f"使用编码 {encoding} 读取成功。")
        print("CSV文件中的标签列字段名：")
        print(data.columns)
        break
    except UnicodeDecodeError:
        print(f"使用编码 {encoding} 读取失败，尝试下一个编码。")

# 数据预处理
# 将分类特征进行编码
label_encoders = {}
for column in data.columns[1:41]:
    if data[column].dtype == 'object':
        le = LabelEncoder()
        data[column] = le.fit_transform(data[column])
        label_encoders[column] = le

# 特征值和标签列
X = data.iloc[:, 1:41]
y = data.iloc[:, 41:59]

# 对特征进行标准化
scaler = StandardScaler()
X = scaler.fit_transform(X)

# 创建和训练逻辑回归模型，并使用网格搜索优化参数
param_grid_lr = {
    'C': [0.1, 1, 10, 100],
    'solver': ['newton-cg', 'lbfgs', 'liblinear', 'saga']
}
logistic_models = {}
single_class_labels = {}

for column in y.columns:
    y_col = y[column]
    if y_col.nunique() == 1:
        single_class_labels[column] = y_col.unique()[0]
    else:
        X_train, X_test, y_train, y_test = train_test_split(X, y_col, test_size=0.2, random_state=42)
        stratified_kf = StratifiedKFold(n_splits=2)
        grid_search = GridSearchCV(LogisticRegression(max_iter=10000), param_grid_lr, cv=stratified_kf, scoring='accuracy')
        grid_search.fit(X_train, y_train)
        best_model = grid_search.best_estimator_
        logistic_models[column] = best_model

# 创建和训练随机森林和梯度提升模型
rf_models = {}
gb_models = {}

for column in y.columns:
    y_col = y[column]
    if y_col.nunique() > 1:
        X_train, X_test, y_train, y_test = train_test_split(X, y_col, test_size=0.2, random_state=42)
        rf_model = RandomForestClassifier(n_estimators=100, random_state=42)
        gb_model = GradientBoostingClassifier(n_estimators=100, random_state=42)
        rf_model.fit(X_train, y_train)
        gb_model.fit(X_train, y_train)
        rf_models[column] = rf_model
        gb_models[column] = gb_model

# 创建投票分类器
voting_models = {}
for column in y.columns:
    y_col = y[column]
    if y_col.nunique() > 1:
        voting_clf = VotingClassifier(
            estimators=[('lr', logistic_models[column]), ('rf', rf_models[column]), ('gb', gb_models[column])],
            voting='soft'
        )
        X_train, X_test, y_train, y_test = train_test_split(X, y_col, test_size=0.2, random_state=42)
        voting_clf.fit(X_train, y_train)
        voting_models[column] = voting_clf

# 保存模型
joblib.dump({
    'logistic_models': logistic_models,
    'rf_models': rf_models,
    'gb_models': gb_models,
    'voting_models': voting_models,
    'label_encoders': label_encoders,
    'scaler': scaler,
    'single_class_labels': single_class_labels,
    'columns': data.columns[1:59]  # 保存列名
}, 'model/model2.pkl')

# 加载模型
model_data = joblib.load('model/model2.pkl')

logistic_models = model_data['logistic_models']
rf_models = model_data['rf_models']
gb_models = model_data['gb_models']
voting_models = model_data['voting_models']
label_encoders = model_data['label_encoders']
scaler = model_data['scaler']
single_class_labels = model_data['single_class_labels']
columns = model_data['columns']  # 加载列名

# 输入特征值进行预测
input_features = [64, '胃恶性肿瘤', 25.64, 'I', '第二级', 3, '胃', '开腹远端胃切除术', '男', '是', '是', 110, 80, '否', 27.4, 860, 25.6, 10.2, 229.7, 127, 4.6, 1.9, 5.64, 3.96, 5.39, 1.16, 2.7, 95.09, 4.4, 49.1, 13.5, 23.8, '否', '是', '否', '否', '否', '否', '否', '否']

# 对输入特征进行编码
encoded_input = []
for i, feature in enumerate(input_features):
    column_name = columns[i]
    if column_name in label_encoders:
        encoded_input.append(label_encoders[column_name].transform([feature])[0])
    else:
        encoded_input.append(feature)

# 对输入特征进行标准化
encoded_input = scaler.transform([encoded_input])

# 输出所有标签列的值出现的概率
predictions = {}
for column, model in voting_models.items():
    probabilities = model.predict_proba(encoded_input)[0]
    predictions[column] = probabilities[1]  # 输出1的概率

# 对于只有一个类别的标签列，直接输出类别对应的概率
for column, single_class in single_class_labels.items():
    predictions[column] = 1.0 if single_class == '是' else 0.0

# 输出结果
for label, probability in predictions.items():
    print(f"标签列 {label} 出现的概率: {probability * 100:.2f}%")
