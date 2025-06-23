import pandas as pd
from sklearn.model_selection import train_test_split, GridSearchCV
from sklearn.ensemble import RandomForestRegressor
from sklearn.preprocessing import LabelEncoder, StandardScaler, OneHotEncoder
from sklearn.compose import ColumnTransformer
from sklearn.pipeline import Pipeline
from sklearn.impute import SimpleImputer
import joblib

def train_and_save_model(input_file, model_path='model/model1.pkl'):
    # 尝试使用不同的编码格式，直到找到正确的编码
    encoding_list = ['utf-8', 'gb18030', 'gbk', 'latin1', 'iso-8859-1', 'cp1252']
    for encoding in encoding_list:
        try:
            data = pd.read_csv(input_file, encoding=encoding)
            # 如果读取成功，打印列名
            print(f"使用编码 {encoding} 读取成功。")
            print("CSV文件中的标签列字段名：")
            print(data.columns)
            break
        except UnicodeDecodeError:
            print(f"使用编码 {encoding} 读取失败，尝试下一个编码。")

    # 预处理数据：将中文字符数据转换为数值编码
    label_encoders = {}
    for column in data.columns:
        if data[column].dtype == object:
            le = LabelEncoder()
            data[column] = le.fit_transform(data[column])
            label_encoders[column] = le

    # 提取特征和标签
    features = data.iloc[:, 1:41]  # 所有列，除了最后一列
    labels = data.iloc[:, -1]  # 只使用最后一列作为标签

    # 检查并处理缺失值
    imputer = SimpleImputer(strategy='mean')
    features = pd.DataFrame(imputer.fit_transform(features), columns=features.columns)

    # 对特征进行标准化和One-Hot编码
    numeric_features = features.select_dtypes(include=['int64', 'float64']).columns
    categorical_features = features.select_dtypes(include=['object']).columns

    preprocessor = ColumnTransformer(
        transformers=[
            ('num', StandardScaler(), numeric_features),
            ('cat', OneHotEncoder(handle_unknown='ignore'), categorical_features)
        ])

    # 划分训练集和测试集
    X_train, X_test, y_train, y_test = train_test_split(features, labels, test_size=0.2, random_state=42)

    # 创建并训练模型
    pipeline = Pipeline(steps=[('preprocessor', preprocessor),
                               ('regressor', RandomForestRegressor(random_state=42))])
    param_grid = {
        'regressor__n_estimators': [100, 200],
        'regressor__max_features': ['sqrt', 'log2'],
        'regressor__max_depth': [4, 5, 6, 7, 8]
    }

    search = GridSearchCV(pipeline, param_grid, n_jobs=-1, cv=2)  # 将cv从3改为2
    search.fit(X_train, y_train)
    best_model = search.best_estimator_

    # 保存训练好的模型
    joblib.dump(best_model, model_path)
    print(f"模型已保存到 {model_path}")

    # 保存label_encoders以便预测时使用
    joblib.dump(label_encoders, 'model/label_encoders.pkl')
    print("label_encoders 已保存")

train_and_save_model('dataset/output_file1.csv')
