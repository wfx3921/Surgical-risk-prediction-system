from fastapi import FastAPI, HTTPException, Depends, Header
from pydantic import BaseModel
from typing import List, Union, Dict

from Model1Output import load_model1_and_encoders, predict_with_loaded_model1
from Model2Output import predict_with_loaded_model2, load_model2_and_encoders

# 加载模型和编码器
logistic_models, rf_models, gb_models, voting_models, label_encoders_model2, scaler, single_class_labels, columns = load_model2_and_encoders()
best_model, label_encoders_model1 = load_model1_and_encoders()

# springboot访问时的密钥，固定值
API_KEY = "184132f04cc1e161625ef01b2a7e0fbf30582dbba3705ed2084718e2fcca32fb"

# 定义输入数据的模型
class PatientData(BaseModel):
    features: List[Union[int, float, str]]

# 定义输出数据的模型
class PredictionResult(BaseModel):
    ordered_predictions: Dict[str, str]
    predicted_hospitalization_days: int

# 初始化FastAPI应用
app = FastAPI()

# API密钥验证依赖项
def api_key_auth(api_key: str = Header(...)):
    if api_key != API_KEY:
        raise HTTPException(status_code=403, detail="Could not validate credentials")

# 在路由中使用依赖项拦截请求
@app.post("/predict", response_model=PredictionResult, dependencies=[Depends(api_key_auth)])
async def predict(data: PatientData):
    input_features = data.features

    try:
        # 模型2 输出的结果
        ordered_predictions = predict_with_loaded_model2(input_features, voting_models, label_encoders_model2, scaler,
                                                         single_class_labels, columns)

        # 模型1 输出的结果
        predictions = predict_with_loaded_model1(input_features, best_model, label_encoders_model1)

        # 格式化输出结果
        ordered_predictions_formatted = {label: f"{probability * 100:.2f}%" for label, probability in
                                         ordered_predictions.items()}
        result = PredictionResult(
            ordered_predictions=ordered_predictions_formatted,
            predicted_hospitalization_days=predictions
        )

        return result
    except Exception as e:
        raise HTTPException