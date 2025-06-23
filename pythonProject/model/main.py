# main.py
from fastapi import FastAPI, Header, HTTPException, Depends
from pydantic import BaseModel, Field  # 注意：这里增加了 Field 导入
from typing import List, Any, Dict
import pandas as pd
import numpy as np
import joblib

# 定义请求数据模型
class PredictionRequest(BaseModel):
    # 使用 alias 使得当接收到键 "features" 时，会自动赋值给 input_features 字段
    input_features: List[Any] = Field(..., alias="features")

# 定义返回数据模型（可选）
class PredictionResponse(BaseModel):
    ordered_predictions: Dict[str, str]
    predicted_hospitalization_days: int

# 固定的特征列顺序（必须与训练时保持一致）
feature_columns = [
    '患者基本信息_年龄_岁',
    '患者基本信息_入院主诊断名称',
    '患者基本信息_BMI_kg/cm2',
    '心功能分级_NYHA分级',
    '患者身体状态_ASA分级',
    '患者身体状态_NRS2002(营养风险筛查评分)',  # 本字段将进行映射转换
    '主要手术部位',
    '手术名称',
    '患者基本信息_性别',
    '饮酒史_饮酒',
    '吸烟史_吸烟',
    '高压',
    '低压',
    '高血压_高血压是否需要药物治疗',
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
    '肝功能_AST_U/L',
    '胸部CT_肺部炎症',
    '心脏超声_左室舒张功能减低',
    '呼吸功能_严重COPD病史',
    '术前情况_手术前30天的充血性心力衰竭',
    '肾功能不全_是否透析',
    '肾功能不全_急性肾功能衰竭',
    '呼吸功能_呼吸困难',
    '术前情况_手术前48小时出现全身性败血症'
]

# 固定的分类标签名称（顺序与训练时保持一致）
class_labels = [
    '心肌梗死', '脑出血', '脑栓塞', '脑梗死', '呼吸衰竭',
    '肺炎', '呼吸机依赖', '肺栓塞', '下肢深静脉血栓',
    '浅表切口感染（切口感染_裂开）', '吻合口瘘', '体腔内器官周围感染',
    '腹部CT_腹腔出血', '腹部CT_腹腔积液', '非计划再手术',
    '出血（血管外科）', '肠粘连(胃肠外科)', '肠梗阻(胃肠外科)'
]

# 加载模型（启动时加载一次）
clf = joblib.load('xgb_classification_model.pkl')
reg = joblib.load('xgb_regression_model.pkl')

# 加载预先保存的 LabelEncoder 字典
try:
    label_encoders = joblib.load('label_encoders.pkl')
except Exception as e:
    raise RuntimeError(f"加载标签编码器失败，请检查文件 label_encoders.pkl: {e}")

app = FastAPI()


@app.post("/predict", response_model=PredictionResponse)
def predict(
        request: PredictionRequest,
        #api_key: str = Depends(verify_api_key)
):
    # 检查输入特征数量是否正确
    if len(request.input_features) != len(feature_columns):
        raise HTTPException(
            status_code=400,
            detail=f"输入特征数量错误，需提供 {len(feature_columns)} 个特征"
        )
    # 将输入特征转换为 DataFrame，保证列的顺序与训练时一致
    input_df = pd.DataFrame([request.input_features], columns=feature_columns)

    # -----------------------------
    # 对 '患者身体状态_NRS2002(营养风险筛查评分)' 进行映射，将数值 1,2,3,4 映射为 "一","二","三","四"
    if '患者身体状态_NRS2002(营养风险筛查评分)' in input_df.columns:
        nrs_mapping = {1: "一", 2: "二", 3: "三", 4: "四"}
        try:
            input_df['患者身体状态_NRS2002(营养风险筛查评分)'] = input_df['患者身体状态_NRS2002(营养风险筛查评分)'].apply(
                lambda x: nrs_mapping[int(x)] if int(x) in nrs_mapping else x
            )
        except Exception as e:
            raise HTTPException(
                status_code=400,
                detail=f"在对列 '患者身体状态_NRS2002(营养风险筛查评分)' 进行映射时出错：{e}"
            )
    # -----------------------------

    # 对二分类变量进行转换
    binary_columns = [
        '患者基本信息_性别',  # 针对性别采用 {"男": 1, "女": 0} 的映射
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
    for col in binary_columns:
        if col in input_df.columns:
            if col == '患者基本信息_性别':
                input_df[col] = input_df[col].map({"男": 1, "女": 0})
                expected_vals = ["男", "女"]
            else:
                input_df[col] = input_df[col].map({"是": 1, "否": 0})
                expected_vals = ["是", "否"]

            if input_df[col].isnull().any():
                raise HTTPException(
                    status_code=400,
                    detail=f"列 '{col}' 包含非预期的值，应该为 {expected_vals} 中的一种"
                )
            input_df[col] = input_df[col].astype(int)
    # -----------------------------

    # 对其他分类变量进行编码处理
    for col, encoder in label_encoders.items():
        if col in input_df.columns and col not in binary_columns:
            try:
                input_df[col] = encoder.transform(input_df[col])
            except Exception as e:
                raise HTTPException(
                    status_code=400,
                    detail=f"在对列 '{col}' 进行编码时出错：{e}"
                )

    # 分类预测
    try:
        predicted_class_probs = clf.predict_proba(input_df)
    except Exception as e:
        raise HTTPException(
            status_code=500,
            detail=f"分类模型预测失败：{e}"
        )
    ordered_predictions = {}
    for idx, label in enumerate(class_labels):
        prob = predicted_class_probs[idx][0, 1]
        ordered_predictions[label] = f"{prob * 100:.2f}%"

    # 回归预测
    try:
        predicted_days = reg.predict(input_df)
    except Exception as e:
        raise HTTPException(
            status_code=500,
            detail=f"回归模型预测失败：{e}"
        )
    predicted_hospitalization_days = int(np.rint(predicted_days[0]))

    return {
        "ordered_predictions": ordered_predictions,
        "predicted_hospitalization_days": predicted_hospitalization_days
    }


if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="0.0.0.0", port=9091)
