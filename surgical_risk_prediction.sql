/*
 Navicat Premium Data Transfer

 Source Server         : 1234
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : surgical_risk_prediction

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 09/04/2025 13:55:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for output_data
-- ----------------------------
DROP TABLE IF EXISTS `output_data`;
CREATE TABLE `output_data`  (
  `id` bigint(0) NOT NULL COMMENT 'patient_data表中所对应数据的id',
  `myocardial_infarction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '心肌梗死',
  `cerebral_hemorrhage` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '脑出血',
  `cerebral_embolism` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '脑栓塞',
  `cerebral_infarction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '脑梗死',
  `respiratory_failure` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '呼吸衰竭',
  `pneumonia` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '肺炎',
  `ventilator_dependence` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '呼吸机依赖',
  `pulmonary_embolism` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '肺栓塞',
  `deep_vein_thrombosis` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '下肢深静脉血栓',
  `superficial_infection` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '浅表切口感染（切口感染_裂开）',
  `anastomotic_leak` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '吻合口瘘',
  `organ_infection` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '体腔内器官周围感染',
  `abdominal_ct_bleeding` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '腹部CT_腹腔出血',
  `abdominal_ct_fluid_accumulation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '腹部CT_腹腔积液',
  `unplanned_reoperation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '非计划再手术',
  `vascular_bleeding` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出血（血管外科）',
  `gastrointestinal_adhesion` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '肠粘连(胃肠外科)',
  `gastrointestinal_obstruction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '肠梗阻(胃肠外科)',
  `hospital_stay_days` bigint(0) NULL DEFAULT NULL COMMENT '住院情况_住院天数',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `create_by` bigint(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `update_by` bigint(0) NULL DEFAULT NULL,
  `delete_flag` tinyint(1) NOT NULL COMMENT '删除标志'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of output_data
-- ----------------------------
INSERT INTO `output_data` VALUES (1, '1.53%', '0.00%', '0.01%', '0.61%', '15.12%', '13.77%', '26.14%', '1.92%', '8.38%', '16.57%', '15.99%', '12.90%', '0.00%', '1.47%', '1.10%', '7.50%', '8.33%', '9.27%', 34, '2024-07-25 16:08:31', NULL, '2024-07-25 16:08:31', 1, 0);
INSERT INTO `output_data` VALUES (2, '1.53%', '0.00%', '0.01%', '0.61%', '15.12%', '13.77%', '26.14%', '1.92%', '8.38%', '16.57%', '15.99%', '12.90%', '0.00%', '1.47%', '1.10%', '7.50%', '8.33%', '9.27%', 34, NULL, NULL, NULL, NULL, 0);

-- ----------------------------
-- Table structure for patient_data
-- ----------------------------
DROP TABLE IF EXISTS `patient_data`;
CREATE TABLE `patient_data`  (
  `id` bigint(0) NOT NULL COMMENT 'id',
  `hospitalization_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '住院号',
  `patient_age` double NULL DEFAULT NULL COMMENT '患者基本信息_年龄_岁',
  `patient_admission_diagnosis` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '患者基本信息_入院主诊断名称',
  `patient_bmi` double NULL DEFAULT NULL COMMENT '患者基本信息_BMI_kg/cm2',
  `cardiac_function_nyha_class` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '心功能分级_NYHA分级',
  `physical_status_asa_class` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '患者身体状态_ASA分级',
  `physical_status_nrs2002_score` double NULL DEFAULT NULL COMMENT '患者身体状态_NRS2002(营养风险筛查评分)',
  `primary_surgical_site` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主要手术部位',
  `surgery_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手术名称',
  `patient_gender` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '患者基本信息_性别',
  `alcohol_history` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '饮酒史_饮酒',
  `smoking_history` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '吸烟史_吸烟',
  `systolic_blood_pressure` double NULL DEFAULT NULL COMMENT '高压',
  `diastolic_blood_pressure` double NULL DEFAULT NULL COMMENT '低压',
  `hypertension_medication_needed` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '高血压_高血压是否需要药物治疗',
  `liver_function_albumin` double NULL DEFAULT NULL COMMENT '肝功能_白蛋白_g/L',
  `coagulation_d_dimer` double NULL DEFAULT NULL COMMENT '血凝常规_D-二聚体_ng/ml',
  `coagulation_aptt` double NULL DEFAULT NULL COMMENT '血凝常规_APTT_sec',
  `coagulation_pt` double NULL DEFAULT NULL COMMENT '血凝常规_PT_sec',
  `liver_function_prealbumin` double NULL DEFAULT NULL COMMENT '肝功能_前白蛋白_mg/L',
  `blood_routine_hemoglobin` double NULL DEFAULT NULL COMMENT '血常规_血红蛋白_g/L',
  `liver_function_total_bilirubin` double NULL DEFAULT NULL COMMENT '肝功能_总胆红素_umol/L',
  `liver_function_direct_bilirubin` double NULL DEFAULT NULL COMMENT '肝功能_直接胆红素_umol/L',
  `blood_routine_wbc_count` double NULL DEFAULT NULL COMMENT '血常规_白细胞_10^9/L',
  `electrolyte_potassium` double NULL DEFAULT NULL COMMENT '电解质_钾_mmol/L',
  `blood_glucose_fasting` double NULL DEFAULT NULL COMMENT '血糖_空腹血糖值_mmol/L',
  `blood_lipids_triglycerides` double NULL DEFAULT NULL COMMENT '血脂_甘油三酯_mmol/L',
  `liver_function_indirect_bilirubin` double NULL DEFAULT NULL COMMENT '肝功能_间接胆红素_umol/L',
  `coagulation_antithrombin_iii` double NULL DEFAULT NULL COMMENT '血凝常规_抗凝血酶III_%',
  `kidney_function_bun` double NULL DEFAULT NULL COMMENT '肾功能_尿素氮_mmmol/L',
  `kidney_function_creatinine` double NULL DEFAULT NULL COMMENT '肾功能_Cr_umol/L',
  `liver_function_alt` double NULL DEFAULT NULL COMMENT '肝功能_ALT_U/L',
  `liver_function_ast` double NULL DEFAULT NULL COMMENT '肝功能_AST_U/L',
  `chest_ct_lung_inflammation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '胸部CT_肺部炎症',
  `cardiac_echo_left_ventricular_dysfunction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '心脏超声_左室舒张功能减低',
  `respiratory_severe_copd_history` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '呼吸功能_严重COPD病史',
  `preoperative_chf_within_30_days` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '术前情况_手术前30天的充血性心力衰竭',
  `kidney_function_dialysis` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '肾功能不全_是否透析',
  `kidney_function_acute_failure` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '肾功能不全_急性肾功能衰竭',
  `respiratory_difficulty` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '呼吸功能_呼吸困难',
  `preoperative_sepsis_within_48_hours` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '术前情况_手术前48小时出现全身性败血症',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `create_by` bigint(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `update_by` bigint(0) NULL DEFAULT NULL,
  `delete_flag` tinyint(1) NOT NULL COMMENT '删除标志',
  `version` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '患者数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of patient_data
-- ----------------------------
INSERT INTO `patient_data` VALUES (1, '123456', 62, '胃恶性肿瘤', 1, 'I', '第二级', 13, '胃', '开腹远端胃切除术', '男', '是', '是', 120, 70, '否', 37.67, 1, 39.9, 9.9, 269.5, 128, 6.95, 0.98, 3.31, 4.46, 4.81, 0.97, 5.97, 1, 6.74, 51, 12, 19, '否', '是', '是', '否', '否', '否', '否', '否', '2024-07-25 16:08:31', 1, '2024-07-25 16:08:31', 1, 0, 6);
INSERT INTO `patient_data` VALUES (2, '12342', 62, '胃恶性肿瘤', 2, 'I', '第二级', 13, '胃', '开腹远端胃切除术', '男', '是', '是', 120, 70, '否', 37.67, 2, 39.9, 9.9, 269.5, 128, 6.95, 0.98, 3.31, 4.46, 4.81, 0.97, 5.97, 2, 6.74, 51, 12, 19, '否', '是', '是', '否', '否', '否', '否', '否', '2024-07-25 16:08:31', 2, '2024-07-25 16:08:31', NULL, 0, 2);
INSERT INTO `patient_data` VALUES (3, '124', 62, '胃恶性肿瘤', 3, 'I', '第二级', 13, '胃', '开腹远端胃切除术', '男', '是', '是', 120, 70, '否', 37.67, 3, 39.9, 9.9, 269.5, 128, 6.95, 0.98, 3.31, 4.46, 4.81, 0.97, 5.97, 3, 6.74, 51, 12, 19, '否', '是', '是', '否', '否', '否', '否', '否', '2024-07-25 16:08:31', 1, '2024-07-25 16:08:31', NULL, 0, 2);
INSERT INTO `patient_data` VALUES (4, '55', 62, '胃恶性肿瘤', 4, 'I', '第二级', 13, '胃', '开腹远端胃切除术', '女', '是', '是', 120, 70, '否', 37.67, 4, 39.9, 9.9, 269.5, 128, 6.95, 0.98, 3.31, 4.46, 4.81, 0.97, 5.97, 4, 6.74, 51, 12, 19, '否', '是', '是', '否', '否', '否', '否', '否', '2024-07-25 16:08:31', 1, '2024-07-25 16:08:31', NULL, 0, 2);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` bigint(0) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `login_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dept_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone_num` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `create_by` bigint(0) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL,
  `update_by` bigint(0) NULL DEFAULT NULL,
  `delete_flag` tinyint(1) NOT NULL,
  `version` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1821958470561570817 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', 'admin', '$2a$12$s0yeQRje7JtsNNCVZa2er.qTn4fLrdpGheMa438.q1beHPG304KYa', 'Engineering', 'jdoe@example.com', '123-456-7890', 'admin', 'active', '2024-08-10 15:18:30', NULL, NULL, NULL, 0, 1);
INSERT INTO `user` VALUES (2, 'normal', 'normal', '$2a$12$cPxGpOZElUM0TeOxx.6.bOnU7YQ2dSEYQwdVqetHm3kyQkCnFQKBO', 'Engineering', 'jdoe@example.com', '15092222233', 'normal', 'active', '2024-08-11 15:18:40', NULL, NULL, NULL, 0, 6);
INSERT INTO `user` VALUES (5, 'sup_admin', 'sup_admin', '$2a$12$s0yeQRje7JtsNNCVZa2er.qTn4fLrdpGheMa438.q1beHPG304KYa', 'Engineering', 'jdoe@example.com', '123-456-7890', 'sup_admin', 'active', '2024-08-08 15:18:42', NULL, NULL, NULL, 0, 1);

SET FOREIGN_KEY_CHECKS = 1;
