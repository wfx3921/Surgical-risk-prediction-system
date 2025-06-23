import joblib
from collections import OrderedDict

# 定义加载模型和编码器的函数
def load_model2_and_encoders(model_path='model/model2.pkl'):
    model_data = joblib.load(model_path)

    logistic_models = model_data['logistic_models']
    rf_models = model_data['rf_models']
    gb_models = model_data['gb_models']
    voting_models = model_data['voting_models']
    label_encoders = model_data['label_encoders']
    scaler = model_data['scaler']
    single_class_labels = model_data['single_class_labels']
    columns = model_data['columns']

    return logistic_models, rf_models, gb_models, voting_models, label_encoders, scaler, single_class_labels, columns

# 定义预测函数
def predict_with_loaded_model2(input_features, voting_models, label_encoders, scaler, single_class_labels, columns):
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

    # 输出所有标签列的值为‘是’的概率
    predictions = OrderedDict()

    # 处理具有多个类别的标签列
    for column, model in voting_models.items():
        probabilities = model.predict_proba(encoded_input)[0]
        predictions[column] = probabilities[1]  # 输出1的概率

    # 处理只有一个类别的标签列
    for column, single_class in single_class_labels.items():
        predictions[column] = 1.0 if single_class == '是' else 0.0

    # 确保输出顺序与columns一致
    ordered_predictions = OrderedDict((col, predictions[col]) for col in columns if col in predictions)

    return ordered_predictions




# # 加载模型和编码器
# logistic_models, rf_models, gb_models, voting_models, label_encoders, scaler, single_class_labels, columns = load_model2_and_encoders()
# # 输入特征值进行预测
# input_features = [
#     62, '胃恶性肿瘤', 21.26, 'I', '第二级', 3, '胃', '开腹远端胃切除术', '男', '是', '是', 120, 70,
#     '否', 37.67, 150, 39.9, 9.9, 269.5, 128, 6.95, 0.98, 3.31, 4.46, 4.81, 0.97, 5.97, 113,
#     6.74, 51, 12, 19, '否', '是', '否', '否', '否', '否', '否', '否'
# ]
# # 调用预测功能
# ordered_predictions = predict_with_loaded_model2(input_features, voting_models, label_encoders, scaler, single_class_labels, columns)
# # 输出结果
# for label, probability in ordered_predictions.items():
#     print(f"标签列 {label} 出现的概率是: {probability * 100:.2f}%")
