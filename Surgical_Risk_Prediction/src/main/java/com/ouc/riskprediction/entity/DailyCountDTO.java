package com.ouc.riskprediction.entity;

import lombok.Data;

@Data
public class DailyCountDTO {
    private String date; // 日期字符串，如"2023-11-01"
    private long count;  // 当日新增数据条数
}