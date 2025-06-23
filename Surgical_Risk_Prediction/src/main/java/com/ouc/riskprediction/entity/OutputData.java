package com.ouc.riskprediction.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection="Output")
public class OutputData {
    private static final long serialVersionUID = 1L;


    /**
     * 这个字段在前端页面不需要展示
     */
    private String id;



    private String myocardialInfarction;
    private String cerebralHemorrhage;
    private String cerebralEmbolism;
    private String cerebralInfarction;
    private String respiratoryFailure;
    private String pneumonia;
    private String ventilatorDependence;
    private String pulmonaryEmbolism;
    private String deepVeinThrombosis;
    private String superficialInfection;
    private String anastomoticLeak;
    private String organInfection;
    private String abdominalCtBleeding;
    private String abdominalCtFluidAccumulation;
    private String unplannedReoperation;
    private String vascularBleeding;
    private String gastrointestinalAdhesion;
    private String gastrointestinalObstruction;
    private Integer hospitalStayDays;
    private Integer deleteFlag;



    @TableField(fill = FieldFill.INSERT)//插入时填充字段
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)//插入和更新时填充字段
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT)//插入时填充字段
    private Long createBy;

    @TableField(fill = FieldFill.INSERT_UPDATE)//插入和更新时填充字段
    private Long updateBy;

}
