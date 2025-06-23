import pandas as pd
import numpy as np
import joblib


# 定义加载模型和编码器的函数
def load_model1_and_encoders(model_path='model/model1.pkl', label_encoders_path='model/label_encoders.pkl'):
    # 加载训练好的模型
    best_model = joblib.load(model_path)

    # 加载label_encoders
    label_encoders = joblib.load(label_encoders_path)

    return best_model, label_encoders


# 定义预测函数
def predict_with_loaded_model1(input_features, best_model, label_encoders,
                              training_data_path='dataset/output_file1.csv'):
    # 从训练数据中加载列名
    training_data = pd.read_csv(training_data_path, encoding='utf-8')
    feature_columns = training_data.columns[1:41]

    # 将输入特征值转为数据框
    input_df = pd.DataFrame([input_features], columns=feature_columns)

    # 对中文字符进行编码
    for column, le in label_encoders.items():
        if column in input_df.columns:
            input_df[column] = input_df[column].apply(lambda x: le.transform([x])[0] if x in le.classes_ else np.nan)

    # 将输入特征值转换为数值类型
    input_df = input_df.apply(pd.to_numeric)

    # 对输入特征值进行预测
    predictions = best_model.predict(input_df)

    # 向上取整
    ceiled_predictions = np.ceil(predictions).astype(int)

    return ceiled_predictions




# # 加载模型和编码器
# best_model, label_encoders = load_model1_and_encoders()
# # 输入特征值进行预测
# input_features = [
#     64, '胃恶性肿瘤', 25.64, 'I', '第二级', 3, '胃', '开腹远端胃切除术', '男', '是', '是', 110, 80, '否', 27.4, 860, 25.6, 10.2, 229.7, 127,
#     4.6, 1.9, 5.64, 3.96, 5.39, 1.16, 2.7, 95.09, 4.4, 49.1, 13.5, 23.8, '否', '是', '否', '否', '否', '否', '否', '否'
# ]
# # 调用预测功能
# predictions = predict_with_loaded_model1(input_features, best_model, label_encoders)
# # 输出结果
# print("预测结果：", predictions)
