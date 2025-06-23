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
INSERT INTO `output_data` VALUES (3, '1.53%', '0.00%', '0.01%', '0.61%', '15.12%', '13.77%', '26.14%', '1.92%', '8.38%', '16.57%', '15.99%', '12.90%', '0.00%', '1.47%', '1.10%', '7.50%', '8.33%', '9.27%', 34, NULL, NULL, NULL, NULL, 0);
INSERT INTO `output_data` VALUES (4, '1.53%', '0.00%', '0.01%', '0.61%', '15.12%', '13.77%', '26.14%', '1.92%', '8.38%', '16.57%', '15.99%', '12.90%', '0.00%', '1.47%', '1.10%', '7.50%', '8.33%', '9.27%', 34, NULL, NULL, NULL, NULL, 0);
INSERT INTO `output_data` VALUES (1838471004574203906, '1.81%', '0.00%', '0.33%', '1.07%', '74.38%', '55.23%', '26.99%', '1.58%', '10.73%', '9.28%', '9.23%', '15.86%', '0.00%', '3.43%', '1.28%', '8.18%', '9.18%', '11.59%', 33, '2024-09-24 14:50:19', NULL, NULL, NULL, 0);
INSERT INTO `output_data` VALUES (1838471425225146370, '37.40%', '0.00%', '34.00%', '16.08%', '77.00%', '55.46%', '59.50%', '69.80%', '52.10%', '14.54%', '37.02%', '12.18%', '33.67%', '7.22%', '39.23%', '10.20%', '5.72%', '9.98%', 33, '2024-09-24 14:52:00', NULL, NULL, NULL, 0);
INSERT INTO `output_data` VALUES (1838476558172438530, '2.47%', '0.00%', '0.33%', '1.10%', '74.36%', '55.23%', '27.82%', '1.56%', '10.93%', '12.78%', '8.99%', '15.85%', '0.00%', '3.66%', '1.32%', '8.22%', '9.26%', '11.37%', 33, '2024-09-24 15:12:24', NULL, NULL, NULL, 0);
INSERT INTO `output_data` VALUES (1877620898714963969, '3.29%', '0.00%', '0.34%', '1.10%', '76.37%', '55.72%', '30.08%', '1.56%', '13.53%', '12.09%', '8.98%', '16.19%', '0.00%', '4.23%', '1.61%', '7.96%', '14.08%', '11.38%', 33, '2025-01-10 15:38:02', NULL, NULL, NULL, 0);
INSERT INTO `output_data` VALUES (1877622097883246594, '3.15%', '0.00%', '0.35%', '0.79%', '79.41%', '55.80%', '5.66%', '1.01%', '8.15%', '9.62%', '7.09%', '16.11%', '0.00%', '5.67%', '1.61%', '6.66%', '11.81%', '9.89%', 33, '2025-01-10 15:42:47', NULL, NULL, NULL, 0);
INSERT INTO `output_data` VALUES (1877997727019790338, '2.47%', '0.00%', '0.33%', '1.09%', '74.37%', '55.23%', '27.57%', '1.57%', '10.83%', '12.86%', '9.10%', '15.85%', '0.00%', '3.66%', '1.28%', '8.20%', '9.22%', '11.65%', 33, '2025-01-11 16:35:25', NULL, NULL, NULL, 0);
INSERT INTO `output_data` VALUES (1877997895798583298, '2.57%', '0.00%', '0.33%', '1.28%', '4.69%', '28.30%', '12.96%', '1.43%', '7.68%', '29.86%', '5.32%', '17.17%', '0.33%', '4.28%', '4.50%', '10.76%', '13.77%', '25.77%', 33, '2025-01-11 16:36:05', NULL, NULL, NULL, 0);
INSERT INTO `output_data` VALUES (1877997949498257410, '2.47%', '0.00%', '0.33%', '1.09%', '74.37%', '55.23%', '27.57%', '1.57%', '10.83%', '12.86%', '9.10%', '15.85%', '0.00%', '3.66%', '1.28%', '8.20%', '9.22%', '11.65%', 33, '2025-01-11 16:36:17', NULL, NULL, NULL, 0);
INSERT INTO `output_data` VALUES (1877997989830684674, '3.29%', '0.00%', '0.34%', '1.10%', '76.37%', '55.72%', '30.08%', '1.56%', '13.53%', '12.09%', '8.98%', '16.19%', '0.00%', '4.23%', '1.61%', '7.96%', '14.08%', '11.38%', 33, '2025-01-11 16:36:27', NULL, NULL, NULL, 0);
INSERT INTO `output_data` VALUES (1877998017362096130, '3.29%', '0.00%', '0.34%', '1.10%', '76.37%', '55.72%', '30.08%', '1.56%', '13.53%', '12.09%', '8.98%', '16.19%', '0.00%', '4.23%', '1.61%', '7.96%', '14.08%', '11.38%', 33, '2025-01-11 16:36:34', NULL, NULL, NULL, 0);

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
INSERT INTO `patient_data` VALUES (1838217795662340098, '123456', 62, '胃恶性肿瘤', 1, 'I', '第二级', 13, '胃', '开腹远端胃切除术', '男', '是', '是', 120, 70, '否', 37.67, 1, 39.9, 9.9, 269.5, 128, 6.95, 0.98, 3.31, 4.46, 4.81, 0.97, 5.97, 1, 6.74, 51, 12, 19, '否', '是', '是', '否', '否', '否', '否', '否', '2024-09-23 22:04:10', 1, NULL, NULL, 0, NULL);
INSERT INTO `patient_data` VALUES (1838217814729646082, '123456', 62, '胃恶性肿瘤', 1, 'I', '第二级', 13, '胃', '开腹远端胃切除术', '男', '是', '是', 120, 70, '否', 37.67, 1, 39.9, 9.9, 269.5, 128, 6.95, 0.98, 3.31, 4.46, 4.81, 0.97, 5.97, 1, 6.74, 51, 12, 19, '否', '是', '是', '否', '否', '否', '否', '否', '2024-09-23 22:04:14', 1, NULL, NULL, 0, NULL);
INSERT INTO `patient_data` VALUES (1838217816516419586, '123456', 62, '胃恶性肿瘤', 1, 'I', '第二级', 13, '胃', '开腹远端胃切除术', '男', '是', '是', 120, 70, '否', 37.67, 1, 39.9, 9.9, 269.5, 128, 6.95, 0.98, 3.31, 4.46, 4.81, 0.97, 5.97, 1, 6.74, 51, 12, 19, '否', '是', '是', '否', '否', '否', '否', '否', '2024-09-23 22:04:15', 1, NULL, NULL, 0, NULL);
INSERT INTO `patient_data` VALUES (1838219877811314690, '123456', 62, '胃恶性肿瘤', 1, 'I', '第二级', 13, '胃', '开腹远端胃切除术', '男', '是', '是', 120, 70, '否', 37.67, 1, 39.9, 9.9, 269.5, 128, 6.95, 0.98, 3.31, 4.46, 4.81, 0.97, 5.97, 1, 6.74, 51, 12, 19, '否', '是', '是', '否', '否', '否', '否', '否', '2024-09-23 22:12:26', 1, NULL, NULL, 0, NULL);
INSERT INTO `patient_data` VALUES (1838219880361451521, '123456', 62, '胃恶性肿瘤', 1, 'I', '第二级', 13, '胃', '开腹远端胃切除术', '男', '是', '是', 120, 70, '否', 37.67, 1, 39.9, 9.9, 269.5, 128, 6.95, 0.98, 3.31, 4.46, 4.81, 0.97, 5.97, 1, 6.74, 51, 12, 19, '否', '是', '是', '否', '否', '否', '否', '否', '2024-09-23 22:12:27', 1, NULL, NULL, 0, NULL);
INSERT INTO `patient_data` VALUES (1838219881275809794, '123456', 62, '胃恶性肿瘤', 1, 'I', '第二级', 13, '胃', '开腹远端胃切除术', '男', '是', '是', 120, 70, '否', 37.67, 1, 39.9, 9.9, 269.5, 128, 6.95, 0.98, 3.31, 4.46, 4.81, 0.97, 5.97, 1, 6.74, 51, 12, 19, '否', '是', '是', '否', '否', '否', '否', '否', '2024-09-23 22:12:27', 1, NULL, NULL, 0, NULL);
INSERT INTO `patient_data` VALUES (1838219882643152897, '123456', 62, '胃恶性肿瘤', 1, 'I', '第二级', 13, '胃', '开腹远端胃切除术', '男', '是', '是', 120, 70, '否', 37.67, 1, 39.9, 9.9, 269.5, 128, 6.95, 0.98, 3.31, 4.46, 4.81, 0.97, 5.97, 1, 6.74, 51, 12, 19, '否', '是', '是', '否', '否', '否', '否', '否', '2024-09-23 22:12:27', 1, NULL, NULL, 0, NULL);
INSERT INTO `patient_data` VALUES (1838219931066392578, '123456', 62, '胃恶性肿瘤', 1, 'I', '第二级', 13, '胃', '开腹远端胃切除术', '男', '是', '是', 120, 70, '否', 37.67, 1, 39.9, 9.9, 269.5, 128, 6.95, 0.98, 3.31, 4.46, 4.81, 0.97, 5.97, 1, 6.74, 51, 12, 19, '否', '是', '是', '否', '否', '否', '否', '否', '2024-09-23 22:12:39', 1, NULL, NULL, 0, NULL);
INSERT INTO `patient_data` VALUES (1838468995880075266, '123456', 62, '胃恶性肿瘤', 1, 'I', '第二级', 13, '胃', '开腹远端胃切除术', '男', '是', '是', 120, 70, '否', 3, 1, 3, 9.9, 2, 12, 6.95, 0.98, 3.31, 4.46, 4.81, 0.97, 5.97, 1, 6.74, 51, 12, 19, '否', '是', '是', '是', '是', '否', '是', '否', '2024-09-24 14:42:20', 1, NULL, NULL, 0, NULL);
INSERT INTO `patient_data` VALUES (1838469550354481153, '123456', 62, '胃恶性肿瘤', 1, 'I', '第二级', 13, '胃', '开腹远端胃切除术', '男', '是', '是', 120, 70, '否', 3, 1, 3, 9.9, 2, 12, 6.95, 0.98, 3.31, 4.46, 4.81, 0.97, 5.97, 1, 6.74, 51, 12, 19, '否', '是', '是', '是', '是', '否', '是', '否', '2024-09-24 14:44:33', 1, NULL, NULL, 0, NULL);
INSERT INTO `patient_data` VALUES (1838470770699812865, '123456', 62, '胃恶性肿瘤', 1, 'I', '第二级', 13, '胃', '开腹远端胃切除术', '男', '是', '是', 120, 70, '否', 3, 1, 3, 9.9, 2, 12, 6.95, 0.98, 3.31, 4.46, 4.81, 0.97, 5.97, 1, 6.74, 51, 12, 19, '否', '是', '是', '是', '是', '否', '是', '否', '2024-09-24 14:49:24', 1, NULL, NULL, 0, NULL);
INSERT INTO `patient_data` VALUES (1838470842518880257, '123456', 62, '胃恶性肿瘤', 1, 'I', '第二级', 13, '胃', '开腹远端胃切除术', '男', '是', '是', 120, 70, '否', 3, 1, 3, 9.9, 2, 12, 6.95, 0.98, 3.31, 4.46, 4.81, 0.97, 5.97, 1, 6.74, 51, 12, 19, '否', '是', '是', '是', '是', '否', '是', '否', '2024-09-24 14:49:41', 1, NULL, NULL, 0, NULL);
INSERT INTO `patient_data` VALUES (1838471004574203906, '124', 62, '胃恶性肿瘤', 3, 'I', '第二级', 13, '胃', '开腹远端胃切除术', '男', '是', '是', 120, 70, '否', 37.67, 3, 39.9, 9.9, 269.5, 128, 6.95, 0.98, 3.31, 4.46, 4.81, 0.97, 5.97, 3, 6.74, 51, 12, 19, '否', '是', '是', '否', '否', '否', '否', '否', '2024-09-24 14:50:19', 1, NULL, NULL, 0, NULL);
INSERT INTO `patient_data` VALUES (1838471425225146370, '123456', 62, '胃恶性肿瘤', 100, 'I', '第二级', 13, '胃', '开腹远端胃切除术', '男', '是', '是', 1200, 70, '否', 3, 1, 3, 9.9, 26, 1, 6.95, 0.98, 3.31, 4.46, 4, 0.97, 5.97, 1, 6.74, 1, 1, 1, '否', '是', '是', '否', '否', '否', '否', '否', '2024-09-24 14:52:00', 1, NULL, NULL, 0, NULL);
INSERT INTO `patient_data` VALUES (1838476558172438530, '123456', 62, '胃恶性肿瘤', 1, 'I', '第二级', 13, '胃', '开腹远端胃切除术', '男', '是', '是', 120, 70, '否', 37.67, 1, 39.9, 9.9, 269.5, 128, 6.95, 0.98, 3.31, 4.46, 4.81, 0.97, 5.97, 1, 6.74, 51, 12, 19, '否', '是', '是', '否', '否', '否', '否', '否', '2024-09-24 15:12:23', 1, NULL, NULL, 0, NULL);
INSERT INTO `patient_data` VALUES (1877615465325412354, '12342', 62, '结肠肿瘤', 2, 'I', '第二级', 13, '胃', '开腹远端胃切除术', '男', '是', '是', 120, 70, '否', 37.67, 2, 39.9, 9.9, 269.5, 128, 6.95, 0.98, 3.31, 4.46, 4.81, 0.97, 5.97, 2, 6.74, 51, 12, 19, '偏重', '重度', '是', '否', '否', '否', '否', '否', '2025-01-10 15:16:26', 2, NULL, NULL, 0, NULL);
INSERT INTO `patient_data` VALUES (1877615697006182402, '12342', 62, '结肠肿瘤', 2, 'I', '第二级', 13, '胃', '开腹远端胃切除术', '男', '是', '是', 120, 70, '否', 37.67, 2, 39.9, 9.9, 269.5, 128, 6.95, 0.98, 3.31, 4.46, 4.81, 0.97, 5.97, 2, 6.74, 51, 12, 19, '偏重', '重度', '是', '否', '否', '否', '否', '否', '2025-01-10 15:17:21', 2, NULL, NULL, 0, NULL);
INSERT INTO `patient_data` VALUES (1877615939030126593, '12342', 62, '结肠肿瘤', 2, 'I', '第二级', 13, '胃', '开腹远端胃切除术', '男', '是', '是', 120, 70, '否', 37.67, 2, 39.9, 9.9, 269.5, 128, 6.95, 0.98, 3.31, 4.46, 4.81, 0.97, 5.97, 2, 6.74, 51, 12, 19, '偏重', '重度', '是', '否', '否', '否', '否', '否', '2025-01-10 15:18:19', 2, NULL, NULL, 0, NULL);
INSERT INTO `patient_data` VALUES (1877617416754728962, '12342', 62, '胃肿瘤', 2, 'I', '第二级', 13, '胃', '开腹远端胃切除术', '男', '是', '是', 120, 70, '否', 37.67, 2, 39.9, 9.9, 269.5, 128, 6.95, 0.98, 3.31, 4.46, 4.81, 0.97, 5.97, 2, 6.74, 51, 12, 19, '偏重', '重度', '偏重', '否', '否', '否', '否', '否', '2025-01-10 15:24:11', 2, NULL, NULL, 0, NULL);
INSERT INTO `patient_data` VALUES (1877618012534689793, '12342', 62, '胃肿瘤', 2, 'I', '第二级', 13, '胃', '开腹远端胃切除术', '男', '是', '是', 120, 70, '否', 37.67, 2, 39.9, 9.9, 269.5, 128, 6.95, 0.98, 3.31, 4.46, 4.81, 0.97, 5.97, 2, 6.74, 51, 12, 19, '偏重', '重度', '偏重', '否', '否', '否', '否', '否', '2025-01-10 15:26:33', 2, NULL, NULL, 0, NULL);
INSERT INTO `patient_data` VALUES (1877620898714963969, '12342', 62, '胃肿瘤', 2, 'I', '第二级', 13, '胃', '开腹远端胃切除术', '男', '是', '是', 120, 70, '否', 37.67, 2, 39.9, 9.9, 269.5, 128, 6.95, 0.98, 3.31, 4.46, 4.81, 0.97, 5.97, 2, 6.74, 51, 12, 19, '偏重', '重度', '偏重', '否', '否', '否', '否', '否', '2025-01-10 15:38:01', 2, NULL, NULL, 0, NULL);
INSERT INTO `patient_data` VALUES (1877622097883246594, '12342', 62, '胆管肿瘤', 2, 'I', '第二级', 13, '直肠', '胃切除术', '男', '是', '是', 120, 70, '否', 37.67, 2, 39.9, 9.9, 269.5, 128, 6.95, 0.98, 3.31, 4.46, 4.81, 0.97, 5.97, 2, 6.74, 51, 12, 19, '重度', '是', '是', '否', '否', '偏重', '否', '偏重', '2025-01-10 15:42:47', 2, NULL, NULL, 0, NULL);
INSERT INTO `patient_data` VALUES (1877997727019790338, '12342', 62, '胃恶性肿瘤', 2, 'I', '第二级', 13, '胃', '开腹远端胃切除术', '男', '是', '是', 120, 70, '否', 37.67, 2, 39.9, 9.9, 269.5, 128, 6.95, 0.98, 3.31, 4.46, 4.81, 0.97, 5.97, 2, 6.74, 51, 12, 19, '否', '重度', '是', '否', '否', '否', '否', '否', '2025-01-11 16:35:24', 2, NULL, NULL, 0, NULL);
INSERT INTO `patient_data` VALUES (1877997807579787265, '12342', 62, '胃恶性肿瘤', 2, 'I', '第二级', 13, '胃', '开腹远端胃切除术', '男', '是', '是', 120, 70, '否', 37.67, 2, 39.9, 9.9, 269.5, 128, 6.95, 0.98, 3.31, 4.46, 4.81, 0.97, 5.97, 2, 6.74, 51, 12, 19, '偏重', '重度', '重度', '偏重', '重度', '轻度', '轻度', '偏重', '2025-01-11 16:35:44', 2, NULL, NULL, 0, NULL);
INSERT INTO `patient_data` VALUES (1877997811887337473, '12342', 62, '胃恶性肿瘤', 2, 'I', '第二级', 13, '胃', '开腹远端胃切除术', '男', '是', '是', 120, 70, '否', 37.67, 2, 39.9, 9.9, 269.5, 128, 6.95, 0.98, 3.31, 4.46, 4.81, 0.97, 5.97, 2, 6.74, 51, 12, 19, '偏重', '重度', '重度', '偏重', '重度', '轻度', '轻度', '偏重', '2025-01-11 16:35:45', 2, NULL, NULL, 0, NULL);
INSERT INTO `patient_data` VALUES (1877997812935913474, '12342', 62, '胃恶性肿瘤', 2, 'I', '第二级', 13, '胃', '开腹远端胃切除术', '男', '是', '是', 120, 70, '否', 37.67, 2, 39.9, 9.9, 269.5, 128, 6.95, 0.98, 3.31, 4.46, 4.81, 0.97, 5.97, 2, 6.74, 51, 12, 19, '偏重', '重度', '重度', '偏重', '重度', '轻度', '轻度', '偏重', '2025-01-11 16:35:45', 2, NULL, NULL, 0, NULL);
INSERT INTO `patient_data` VALUES (1877997813783162881, '12342', 62, '胃恶性肿瘤', 2, 'I', '第二级', 13, '胃', '开腹远端胃切除术', '男', '是', '是', 120, 70, '否', 37.67, 2, 39.9, 9.9, 269.5, 128, 6.95, 0.98, 3.31, 4.46, 4.81, 0.97, 5.97, 2, 6.74, 51, 12, 19, '偏重', '重度', '重度', '偏重', '重度', '轻度', '轻度', '偏重', '2025-01-11 16:35:45', 2, NULL, NULL, 0, NULL);
INSERT INTO `patient_data` VALUES (1877997830166114305, '12342', 62, '胃恶性肿瘤', 2, 'I', '第二级', 13, '胃', '开腹远端胃切除术', '男', '是', '是', 120, 70, '否', 37.67, 2, 39.9, 9.9, 269.5, 128, 6.95, 0.98, 3.31, 4.46, 4.81, 0.97, 5.97, 2, 6.74, 51, 12, 19, '否', '重度', '重度', '偏重', '重度', '轻度', '轻度', '偏重', '2025-01-11 16:35:49', 2, NULL, NULL, 0, NULL);
INSERT INTO `patient_data` VALUES (1877997840173723649, '12342', 62, '胃恶性肿瘤', 2, 'I', '第二级', 13, '胃', '开腹远端胃切除术', '男', '是', '是', 120, 70, '否', 37.67, 2, 39.9, 9.9, 269.5, 128, 6.95, 0.98, 3.31, 4.46, 4.81, 0.97, 5.97, 2, 6.74, 51, 12, 19, '否', '重度', '重度', '偏重', '否', '轻度', '轻度', '偏重', '2025-01-11 16:35:51', 2, NULL, NULL, 0, NULL);
INSERT INTO `patient_data` VALUES (1877997849136951298, '12342', 62, '胃恶性肿瘤', 2, 'I', '第二级', 13, '胃', '开腹远端胃切除术', '男', '是', '是', 120, 70, '否', 37.67, 2, 39.9, 9.9, 269.5, 128, 6.95, 0.98, 3.31, 4.46, 4.81, 0.97, 5.97, 2, 6.74, 51, 12, 19, '否', '重度', '重度', '偏重', '否', '否', '轻度', '偏重', '2025-01-11 16:35:53', 2, NULL, NULL, 0, NULL);
INSERT INTO `patient_data` VALUES (1877997858586718209, '12342', 62, '胃恶性肿瘤', 2, 'I', '第二级', 13, '胃', '开腹远端胃切除术', '男', '是', '是', 120, 70, '否', 37.67, 2, 39.9, 9.9, 269.5, 128, 6.95, 0.98, 3.31, 4.46, 4.81, 0.97, 5.97, 2, 6.74, 51, 12, 19, '否', '重度', '重度', '偏重', '否', '否', '否', '偏重', '2025-01-11 16:35:56', 2, NULL, NULL, 0, NULL);
INSERT INTO `patient_data` VALUES (1877997867470254081, '12342', 62, '胃恶性肿瘤', 2, 'I', '第二级', 13, '胃', '开腹远端胃切除术', '男', '是', '是', 120, 70, '否', 37.67, 2, 39.9, 9.9, 269.5, 128, 6.95, 0.98, 3.31, 4.46, 4.81, 0.97, 5.97, 2, 6.74, 51, 12, 19, '否', '重度', '重度', '偏重', '否', '否', '否', '否', '2025-01-11 16:35:58', 2, NULL, NULL, 0, NULL);
INSERT INTO `patient_data` VALUES (1877997875544289282, '12342', 62, '胃恶性肿瘤', 2, 'I', '第二级', 13, '胃', '开腹远端胃切除术', '男', '是', '是', 120, 70, '否', 37.67, 2, 39.9, 9.9, 269.5, 128, 6.95, 0.98, 3.31, 4.46, 4.81, 0.97, 5.97, 2, 6.74, 51, 12, 19, '否', '否', '重度', '偏重', '否', '否', '否', '否', '2025-01-11 16:36:00', 2, NULL, NULL, 0, NULL);
INSERT INTO `patient_data` VALUES (1877997886071992321, '12342', 62, '胃恶性肿瘤', 2, 'I', '第二级', 13, '胃', '开腹远端胃切除术', '男', '是', '是', 120, 70, '否', 37.67, 2, 39.9, 9.9, 269.5, 128, 6.95, 0.98, 3.31, 4.46, 4.81, 0.97, 5.97, 2, 6.74, 51, 12, 19, '否', '否', '轻微', '偏重', '否', '否', '否', '否', '2025-01-11 16:36:02', 2, NULL, NULL, 0, NULL);
INSERT INTO `patient_data` VALUES (1877997895798583298, '12342', 62, '胃恶性肿瘤', 2, 'I', '第二级', 13, '胃', '开腹远端胃切除术', '男', '是', '是', 120, 70, '否', 37.67, 2, 39.9, 9.9, 269.5, 128, 6.95, 0.98, 3.31, 4.46, 4.81, 0.97, 5.97, 2, 6.74, 51, 12, 19, '否', '否', '轻微', '否', '否', '否', '否', '否', '2025-01-11 16:36:05', 2, NULL, NULL, 0, NULL);
INSERT INTO `patient_data` VALUES (1877997949498257410, '12342', 62, '胃恶性肿瘤', 2, 'I', '第二级', 13, '胃', '开腹远端胃切除术', '男', '是', '是', 120, 70, '否', 37.67, 2, 39.9, 9.9, 269.5, 128, 6.95, 0.98, 3.31, 4.46, 4.81, 0.97, 5.97, 2, 6.74, 51, 12, 19, '否', '重度', '是', '否', '否', '否', '否', '否', '2025-01-11 16:36:17', 2, NULL, NULL, 0, NULL);
INSERT INTO `patient_data` VALUES (1877997989830684674, '12342', 62, '胃恶性肿瘤', 2, 'I', '第二级', 13, '胃', '开腹远端胃切除术', '男', '是', '是', 120, 70, '否', 37.67, 2, 39.9, 9.9, 269.5, 128, 6.95, 0.98, 3.31, 4.46, 4.81, 0.97, 5.97, 2, 6.74, 51, 12, 19, '重度', '重度', '是', '否', '否', '否', '否', '否', '2025-01-11 16:36:27', 2, NULL, NULL, 0, NULL);
INSERT INTO `patient_data` VALUES (1877998017362096130, '12342', 62, '胃恶性肿瘤', 2, 'I', '第二级', 13, '胃', '开腹远端胃切除术', '男', '是', '是', 120, 70, '否', 37.67, 2, 39.9, 9.9, 269.5, 128, 6.95, 0.98, 3.31, 4.46, 4.81, 0.97, 5.97, 2, 6.74, 51, 12, 19, '重度', '重度', '重度', '否', '否', '否', '否', '否', '2025-01-11 16:36:34', 2, NULL, NULL, 0, NULL);
INSERT INTO `patient_data` VALUES (1877998052426477569, '12342', 62, '胃恶性肿瘤', 2, 'I', '第二级', 13, '胃', '开腹远端胃切除术', '男', '是', '是', 120, 70, '否', 37.67, 2, 39.9, 9.9, 269.5, 128, 6.95, 0.98, 3.31, 4.46, 4.81, 0.97, 5.97, 2, 6.74, 51, 12, 19, '重度', '重度', '重度', '重度', '否', '否', '否', '否', '2025-01-11 16:36:42', 2, NULL, NULL, 0, NULL);
INSERT INTO `patient_data` VALUES (1877998056520118274, '12342', 62, '胃恶性肿瘤', 2, 'I', '第二级', 13, '胃', '开腹远端胃切除术', '男', '是', '是', 120, 70, '否', 37.67, 2, 39.9, 9.9, 269.5, 128, 6.95, 0.98, 3.31, 4.46, 4.81, 0.97, 5.97, 2, 6.74, 51, 12, 19, '重度', '重度', '重度', '重度', '否', '否', '否', '否', '2025-01-11 16:36:43', 2, NULL, NULL, 0, NULL);
INSERT INTO `patient_data` VALUES (1877998057300258817, '12342', 62, '胃恶性肿瘤', 2, 'I', '第二级', 13, '胃', '开腹远端胃切除术', '男', '是', '是', 120, 70, '否', 37.67, 2, 39.9, 9.9, 269.5, 128, 6.95, 0.98, 3.31, 4.46, 4.81, 0.97, 5.97, 2, 6.74, 51, 12, 19, '重度', '重度', '重度', '重度', '否', '否', '否', '否', '2025-01-11 16:36:43', 2, NULL, NULL, 0, NULL);
INSERT INTO `patient_data` VALUES (1877998058101370882, '12342', 62, '胃恶性肿瘤', 2, 'I', '第二级', 13, '胃', '开腹远端胃切除术', '男', '是', '是', 120, 70, '否', 37.67, 2, 39.9, 9.9, 269.5, 128, 6.95, 0.98, 3.31, 4.46, 4.81, 0.97, 5.97, 2, 6.74, 51, 12, 19, '重度', '重度', '重度', '重度', '否', '否', '否', '否', '2025-01-11 16:36:43', 2, NULL, NULL, 0, NULL);

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
INSERT INTO `user` VALUES (21, 'John Doe1122', 'jdoe4', '$2a$12$cOwVKgcniR2OHfaRfOSycujQWqEfwyR4YLnkzAeWMAhrcT53K3rdS', 'Engineering', 'jdoe@example1.com', '15092232569', 'normal', 'inactive', '2024-08-01 15:18:47', NULL, NULL, NULL, 0, 17);
INSERT INTO `user` VALUES (31, 'John Doe', 'jdoe4389', '$2a$12$8FOwhMSJ6iMAa/uZgGYo6.8khuePj0UNI0yaM8l1O.TXjFRCgY6YO', 'Engineering', 'jdoe@example.com', '15789895757', 'normal', 'active', '2024-08-01 15:18:48', NULL, NULL, NULL, 0, 2);
INSERT INTO `user` VALUES (41, 'John Doe', 'jdoe4', '$2a$12$8FOwhMSJ6iMAa/uZgGYo6.8khuePj0UNI0yaM8l1O.TXjFRCgY6YO', 'Engineering', 'jdoe@example.com', '00000', 'normal', 'active', '2024-08-01 15:18:50', NULL, NULL, NULL, 0, 2);
INSERT INTO `user` VALUES (51, 'John Doe', 'jdoe458', '$2a$12$8FOwhMSJ6iMAa/uZgGYo6.8khuePj0UNI0yaM8l1O.TXjFRCgY6YO', 'Engineering', 'jdoe@example.com', '15119261054', 'normal', 'active', '2024-08-01 15:18:51', NULL, NULL, NULL, 0, 2);
INSERT INTO `user` VALUES (61, 'John Doe', 'jdoe4', '$2a$12$8FOwhMSJ6iMAa/uZgGYo6.8khuePj0UNI0yaM8l1O.TXjFRCgY6YO', 'Engineering', 'jdoe@example.com', '00000', 'normal', 'active', '2024-08-01 15:18:52', NULL, NULL, NULL, 0, 2);
INSERT INTO `user` VALUES (91, 'John Doe', 'jdoe4', '$2a$12$8FOwhMSJ6iMAa/uZgGYo6.8khuePj0UNI0yaM8l1O.TXjFRCgY6YO', 'Engineering', 'jdoe@example.com', '00000', 'normal', 'active', '2024-08-01 15:18:54', NULL, NULL, NULL, 0, 1);
INSERT INTO `user` VALUES (111, 'John Doe', 'jdoe4', '$2a$12$8FOwhMSJ6iMAa/uZgGYo6.8khuePj0UNI0yaM8l1O.TXjFRCgY6YO', 'Engineering', 'jdoe@example.com', '00000', 'normal', 'active', '2024-08-01 15:18:56', NULL, NULL, NULL, 0, 1);
INSERT INTO `user` VALUES (121, 'John Doe', 'jdoe4', '$2a$12$8FOwhMSJ6iMAa/uZgGYo6.8khuePj0UNI0yaM8l1O.TXjFRCgY6YO', 'Engineering', 'jdoe@example.com', '00000', 'normal', 'active', '2024-08-07 15:10:55', NULL, NULL, NULL, 0, 1);
INSERT INTO `user` VALUES (1818716739542343682, 'joe', 'joe', '$2a$12$lYvy2DlUvejElsC.aq6yxeeYDNeNRER5BMqJ06n.bPGb1wuYBlSZS', 'Engineering', '1@qq.com', '15192906107', 'normal', 'active', '2024-08-01 15:18:58', NULL, NULL, NULL, 0, 1);
INSERT INTO `user` VALUES (1818724939473772545, 'hoe', 'hoe', '$2a$12$C7Dw0odMRFcu/0WVONKtaO2NlwUJ0oPbVDRuF3eYwC89S3AdcDoTa', 'Engineering', '1@qq.cn', '15192906107', 'normal', 'active', '2024-08-01 15:18:59', NULL, NULL, NULL, 0, 1);
INSERT INTO `user` VALUES (1818908503360286722, 'saul', 'saul', '$2a$12$rDFtHPvKOw.oAUVqubBGGuF2RNoUOfhHrAVK1vkJ36ygAvo3foDne', 'Engineering', '2773785465@qq.com', '17685706897', 'admin', 'active', '2024-08-01 15:19:00', NULL, NULL, NULL, 0, 1);
INSERT INTO `user` VALUES (1821952147379953666, 'qqqqq', 'qqqqq', '$2a$12$H8D0livW2El8O/SRRA1ie.BFSDY3zI1jjUhcQD9xCHs3BZeik7eB.', 'Engineering', '2222@qqq.com', '16666666666', 'normal', 'inactive', '2024-08-10 00:50:17', NULL, NULL, NULL, 0, 1);
INSERT INTO `user` VALUES (1821958470561570817, 'qqq', 'qqq', '$2a$12$3JNeo/NCthgHQQBWFwyhJ.KGQS5jkJZQGx8BkTQLUOJMJFzVQCAzm', NULL, 'qqq@a.com', '15099333333', 'normal', 'inactive', '2024-08-10 01:15:24', NULL, NULL, NULL, 0, 0);

SET FOREIGN_KEY_CHECKS = 1;
