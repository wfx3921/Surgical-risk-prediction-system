package com.ouc.riskprediction.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.Version;
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;


    @TableId(value = "user_id")
    private Long userId;
    private String userName;
    private String loginName;
    private String password;
    private String deptName;
    private String email;
    private String phoneNum;
    private String userType;
    private String status;
    private Integer deleteFlag;
//    @Version
//    private  Integer version = 0;

    /**
     * 下面这四个字段是公共字段。使用@TableField字段来指定
     * Mybatis-Plus提供了公共字段填充功能，需要在公共字段上加入注解@TableField，指定自动填充策略。
     */
    @JsonProperty("create_time")
    @TableField(fill = FieldFill.INSERT)//插入时填充字段
    private LocalDateTime createTime;

    @JsonProperty("update_time")
    @TableField(fill = FieldFill.INSERT_UPDATE)//插入和更新时填充字段
    private LocalDateTime updateTime;

    @JsonProperty("create_by")
    @TableField(fill = FieldFill.INSERT)//插入时填充字段
    private Long createBy;

    @JsonProperty("update_by")
    @TableField(fill = FieldFill.INSERT_UPDATE)//插入和更新时填充字段
    private Long updateBy;


}
