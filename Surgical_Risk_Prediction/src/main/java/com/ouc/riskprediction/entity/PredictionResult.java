package com.ouc.riskprediction.entity;

import java.util.Map;


/**
 * springboot框架与FastAPI框架数据交换时数据转换实体类
 */
public class PredictionResult {

    private Map<String, String> ordered_predictions;
    private Integer predicted_hospitalization_days;


    // Getters and setters
    public Map<String, String> getOrdered_predictions() {
        return ordered_predictions;
    }

    public void setOrdered_predictions(Map<String, String> ordered_predictions) {
        this.ordered_predictions = ordered_predictions;
    }

    public Integer getPredicted_hospitalization_days() {
        return predicted_hospitalization_days;
    }

    public void setPredicted_hospitalization_days(Integer predicted_hospitalization_days) {
        this.predicted_hospitalization_days = predicted_hospitalization_days;
    }
}
