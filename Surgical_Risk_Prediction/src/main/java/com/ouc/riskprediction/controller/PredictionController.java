package com.ouc.riskprediction.controller;

import com.ouc.riskprediction.common.R;
import com.ouc.riskprediction.entity.OutputData;
import com.ouc.riskprediction.entity.PatientData;
import com.ouc.riskprediction.entity.PredictionResult;
import com.ouc.riskprediction.service.OutputDataService;
import com.ouc.riskprediction.service.PatientDataService;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;


@CrossOrigin
@RestController
@RequestMapping("/api")
public class PredictionController {

    @Autowired
    private PatientDataService patientDataService;

    @Autowired
    private OutputDataService outputDataService;

    @Autowired
    private RestTemplate restTemplate;
    @Data
    public static class LegacyPatientData {
        @Id
        private String id;
        @Field("hospitalization_number")
        private String hospitalizationNumber;
        @Field("patient_age")
        private Integer patientAge;
        @Field("patient_admission_diagnosis")
        private String patientAdmissionDiagnosis;
        @Field("patient_bmi")
        private Double patientBmi;
        @Field("cardiac_function_nyha_class")
        private String cardiacFunctionNyhaClass;
        @Field("physical_status_asa_class")
        private String physicalStatusAsaClass;
        @Field("physical_status_nrs2002_score")
        private Double physicalStatusNrs2002Score;
        @Field("primary_surgical_site")
        private String primarySurgicalSite;
        @Field("surgery_name")
        private String surgeryName;
        @Field("patient_gender")
        private String patientGender;
        @Field("alcohol_history")
        private String alcoholHistory;
        @Field("smoking_history")
        private String smokingHistory;
        @Field("systolic_blood_pressure")
        private Double systolicBloodPressure;
        @Field("diastolic_blood_pressure")
        private Double diastolicBloodPressure;
        @Field("hypertension_medication_needed")
        private String hypertensionMedicationNeeded;
        @Field("liver_function_albumin")
        private Double liverFunctionAlbumin;
        @Field("coagulation_d_dimer")
        private Double coagulationDDimer;
        @Field("coagulation_aptt")
        private Double coagulationAptt;
        @Field("coagulation_pt")
        private Double coagulationPt;
        @Field("liver_function_prealbumin")
        private Double liverFunctionPrealbumin;
        @Field("blood_routine_hemoglobin")
        private Double bloodRoutineHemoglobin;
        @Field("liver_function_total_bilirubin")
        private Double liverFunctionTotalBilirubin;
        @Field("liver_function_direct_bilirubin")
        private Double liverFunctionDirectBilirubin;
        @Field("blood_routine_wbc_count")
        private Double bloodRoutineWbcCount;
        @Field("electrolyte_potassium")
        private Double electrolytePotassium;
        @Field("blood_glucose_fasting")
        private Double bloodGlucoseFasting;
        @Field("blood_lipids_triglycerides")
        private Double bloodLipidsTriglycerides;
        @Field("liver_function_indirect_bilirubin")
        private Double liverFunctionIndirectBilirubin;
        @Field("coagulation_antithrombin_iii")
        private Double coagulationAntithrombinIii;
        @Field("kidney_function_bun")
        private Double kidneyFunctionBun;
        @Field("kidney_function_creatinine")
        private Double kidneyFunctionCreatinine;
        @Field("liver_function_alt")
        private Double liverFunctionAlt;
        @Field("liver_function_ast")
        private Double liverFunctionAst;
        @Field("chest_ct_lung_inflammation")
        private String chestCtLungInflammation;
        @Field("cardiac_echo_left_ventricular_dysfunction")
        private String cardiacEchoLeftVentricularDysfunction;
        @Field("respiratory_severe_copd_history")
        private String respiratorySevereCopdHistory;
        @Field("delete_flag")
        private Integer deleteFlag = 0;

        // 使用 @Field 注解来指定MongoDB中的字段名称
        @Field("preoperative_chf_within_30_days")
        private String preoperativeChfWithin30Days;
        @Field("kidney_function_dialysis")
        private String kidneyFunctionDialysis;
        @Field("kidney_function_acute_failure")
        private String kidneyFunctionAcuteFailure;
        @Field("respiratory_difficulty")
        private String respiratoryDifficulty;

        @Field("preoperative_sepsis_within_48_hours")
        private String preoperativeSepsisWithin48Hours;

        // 自动填充的时间戳和用户ID字段
        @CreatedDate
        @Field("create_time")
        private LocalDateTime createTime;
        @Field("create_by")
        private Long createBy;

        @LastModifiedDate
        @Field("update_time")
        private LocalDateTime updateTime;
        @Field("update_by")
        private Long updateBy;

        @Version
        @Field("version")
        private Integer version;

    }
    private LegacyPatientData convertToLegacy(PatientData newData) {
        LegacyPatientData legacy = new LegacyPatientData();
        // ID相关字段
        legacy.setId(newData.getId());
        // 基础信息
        legacy.setHospitalizationNumber(newData.getHospitalizationNumber());
        legacy.setPatientAge(newData.getPatientAge());
        legacy.setPatientAdmissionDiagnosis(newData.getPatientAdmissionDiagnosis());
        legacy.setPatientBmi(newData.getPatientBmi());
        legacy.setPatientGender(newData.getPatientGender());
        // 心功能评估
        legacy.setCardiacFunctionNyhaClass(newData.getCardiacFunctionNyhaClass());
        legacy.setCardiacEchoLeftVentricularDysfunction(newData.getCardiacEchoLeftVentricularDysfunction());
        legacy.setPreoperativeChfWithin30Days(newData.getPreoperativeChfWithin30Days());
        // 身体状况评分
        legacy.setPhysicalStatusAsaClass(newData.getPhysicalStatusAsaClass());
        legacy.setPhysicalStatusNrs2002Score(newData.getPhysicalStatusNrs2002Score());
        // 手术信息
        legacy.setPrimarySurgicalSite(newData.getPrimarySurgicalSite());
        legacy.setSurgeryName(newData.getSurgeryName());
        // 生活习惯
        legacy.setAlcoholHistory(newData.getAlcoholHistory());
        legacy.setSmokingHistory(newData.getSmokingHistory());

        // 血压相关
        legacy.setSystolicBloodPressure(newData.getSystolicBloodPressure());
        legacy.setDiastolicBloodPressure(newData.getDiastolicBloodPressure());
        legacy.setHypertensionMedicationNeeded(newData.getHypertensionMedicationNeeded());

        // 肝功能
        legacy.setLiverFunctionAlbumin(newData.getLiverFunctionAlbumin());
        legacy.setLiverFunctionPrealbumin(newData.getLiverFunctionPrealbumin());
        legacy.setLiverFunctionTotalBilirubin(newData.getLiverFunctionTotalBilirubin());
        legacy.setLiverFunctionDirectBilirubin(newData.getLiverFunctionDirectBilirubin());
        legacy.setLiverFunctionIndirectBilirubin(newData.getLiverFunctionIndirectBilirubin());
        legacy.setLiverFunctionAlt(newData.getLiverFunctionAlt());
        legacy.setLiverFunctionAst(newData.getLiverFunctionAst());

        // 凝血功能
        legacy.setCoagulationDDimer(newData.getCoagulationDDimer());
        legacy.setCoagulationAptt(newData.getCoagulationAptt());
        legacy.setCoagulationPt(newData.getCoagulationPt());
        legacy.setCoagulationAntithrombinIii(newData.getCoagulationAntithrombinIii());

        // 血常规
        legacy.setBloodRoutineHemoglobin(newData.getBloodRoutineHemoglobin());
        legacy.setBloodRoutineWbcCount(newData.getBloodRoutineWbcCount());

        // 电解质和代谢
        legacy.setElectrolytePotassium(newData.getElectrolytePotassium());
        legacy.setBloodGlucoseFasting(newData.getBloodGlucoseFasting());
        legacy.setBloodLipidsTriglycerides(newData.getBloodLipidsTriglycerides());

        // 肾功能
        legacy.setKidneyFunctionBun(newData.getKidneyFunctionBun());
        legacy.setKidneyFunctionCreatinine(newData.getKidneyFunctionCreatinine());
        legacy.setKidneyFunctionDialysis(newData.getKidneyFunctionDialysis());
        legacy.setKidneyFunctionAcuteFailure(newData.getKidneyFunctionAcuteFailure());

        // 呼吸系统
        legacy.setChestCtLungInflammation(newData.getChestCtLungInflammation());
        legacy.setRespiratorySevereCopdHistory(newData.getRespiratorySevereCopdHistory());
        legacy.setRespiratoryDifficulty(newData.getRespiratoryDifficulty());

        // 感染相关
        legacy.setPreoperativeSepsisWithin48Hours(newData.getPreoperativeSepsisWithin48Hours());


        // 系统字段
        legacy.setDeleteFlag(newData.getDeleteFlag());
        legacy.setCreateTime(newData.getCreateTime());
        legacy.setCreateBy(newData.getCreateBy());
        legacy.setUpdateTime(newData.getUpdateTime());
        legacy.setUpdateBy(newData.getUpdateBy());
        legacy.setVersion(newData.getVersion());

        return legacy;
    }
    /**
     * 接收前端传来的预测值存入数据库,然后传送给FastAPI进行预测,预测完成后返回预测值并存入数据库返回给前端
     * @param patientData
     * @return
     */
    @PostMapping("/predict")
    @Transactional
    public R<OutputData> predict(@RequestBody PatientData patientData) {
        LegacyPatientData legacyData = convertToLegacy(patientData);
        try {
            Date Pdate=new Date();
            LocalDateTime PlocalDateTime = Pdate.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
            patientData.setCreateTime(PlocalDateTime);
            legacyData.setCreateTime(PlocalDateTime);

            // 不设置id值，使用数据库自增
            if ("轻微".equals(legacyData.getChestCtLungInflammation()) || "轻度".equals(legacyData.getChestCtLungInflammation()) || "否".equals(legacyData.getChestCtLungInflammation())) {
                legacyData.setChestCtLungInflammation("否") ;
            } else {
                legacyData.setChestCtLungInflammation("是") ;
            }

            if ("轻微".equals(legacyData.getCardiacEchoLeftVentricularDysfunction()) || "轻度".equals(legacyData.getCardiacEchoLeftVentricularDysfunction()) || "否".equals(legacyData.getCardiacEchoLeftVentricularDysfunction())) {
                legacyData.setCardiacEchoLeftVentricularDysfunction("否");
            } else {
                legacyData.setCardiacEchoLeftVentricularDysfunction("是");
            }

            if ("轻微".equals(legacyData.getRespiratorySevereCopdHistory())  || "轻度".equals(legacyData.getRespiratorySevereCopdHistory()) || "否".equals(legacyData.getRespiratorySevereCopdHistory()) ) {
                legacyData.setRespiratorySevereCopdHistory("否") ;
            } else {
                legacyData.setRespiratorySevereCopdHistory("是") ;
            }

            if ("轻微".equals(legacyData.getPreoperativeChfWithin30Days()) || "轻度".equals(legacyData.getPreoperativeChfWithin30Days()) || "否".equals(legacyData.getPreoperativeChfWithin30Days())) {
                legacyData.setPreoperativeChfWithin30Days("否") ;
            } else {
                legacyData.setPreoperativeChfWithin30Days("是") ;
            }

            if ("轻微".equals(legacyData.getKidneyFunctionDialysis())  || "轻度".equals(legacyData.getKidneyFunctionDialysis()) || "否".equals(legacyData.getKidneyFunctionDialysis())) {
                legacyData.setKidneyFunctionDialysis("否") ;
            } else {
                legacyData.setKidneyFunctionDialysis("是") ;
            }

            if ("轻微".equals(legacyData.getKidneyFunctionAcuteFailure())  || "轻度".equals(legacyData.getKidneyFunctionAcuteFailure()) || "否".equals(legacyData.getKidneyFunctionAcuteFailure())) {
                legacyData.setKidneyFunctionAcuteFailure("否") ;
            } else {
                legacyData.setKidneyFunctionAcuteFailure("是") ;
            }

            if ("轻微".equals(legacyData.getRespiratoryDifficulty())  || "轻度".equals(legacyData.getRespiratoryDifficulty()) || "否".equals(legacyData.getRespiratoryDifficulty())) {
                legacyData.setRespiratoryDifficulty("否");
            } else {
                legacyData.setRespiratoryDifficulty("是");
            }

            if ("轻微".equals(legacyData.getPreoperativeSepsisWithin48Hours()) || "轻度".equals(legacyData.getPreoperativeSepsisWithin48Hours())  || "否".equals(legacyData.getPreoperativeSepsisWithin48Hours()) ) {
                legacyData.setPreoperativeSepsisWithin48Hours("否") ;
            } else {
                legacyData.setPreoperativeSepsisWithin48Hours("是") ;
            }
            // 获取保存后的PatientData对象的ID
            System.out.println(patientData);
            String patientDataId = patientData.getId();//PatientData类的id字段上加上一个@Id和@GeneratedValue(strategy = GenerationType.IDENTITY)注解

            // Prepare request to FastAPI
            String url = "http://localhost:9091/predict";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
//            headers.add("api-key", "184132f04cc1e161625ef01b2a7e0fbf30582dbba3705ed2084718e2fcca32fb");

            // Convert PatientData to FastAPI format
            Map<String, Object> fastApiData = convertToFastApiFormat(legacyData);
            HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(fastApiData, headers);
            ResponseEntity<PredictionResult> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, PredictionResult.class);
            //FastAPI返回来的数据转换成前端可以接收的格式
            /**
             * FastAPI返回的数据格式
             * {
             *     "ordered_predictions": {"心肌梗死": "0.42%","脑出血": "0.00%","脑栓塞": "0.00%","脑梗死": "0.06%","呼吸衰竭": "70.16%","肺炎": "54.31%",
             *     "呼吸机依赖": "8.01%","肺栓塞": "1.76%","下肢深静脉血栓": "10.36%","浅表切口感染（切口感染_裂开）": "2.94%","吻合口瘘": "10.10%",
             *     "体腔内器官周围感染": "11.32%","腹部CT_腹腔出血": "0.00%","腹部CT_腹腔积液": "0.09%","非计划再手术": "0.47%","出血（血管外科）": "4.18%",
             *     "肠粘连(胃肠外科)": "0.40%","肠梗阻(胃肠外科)": "5.94%"},
             *     "predicted_hospitalization_days": 21
             * }
             *
             *
             *前端接收的数据格式
             * {
             *     "code": 1,
             *     "msg": null,
             *     "data": {"id": "1816385022765084673","myocardialInfarction": "1.53%","cerebralHemorrhage": "0.00%","cerebralEmbolism": "0.01%",
             *     "cerebralInfarction": "0.61%","respiratoryFailure": "75.12%","pneumonia": "53.77%","ventilatorDependence": "26.14%",
             *     "pulmonaryEmbolism": "1.92%","deepVeinThrombosis": "8.38%","superficialInfection": "16.57%","anastomoticLeak": "15.99%",
             *     "organInfection": "12.90%","abdominalCtBleeding": "0.00%","abdominalCtFluidAccumulation": "1.47%","unplannedReoperation": "1.10%","vascularBleeding": "7.50%",
             *     "gastrointestinalAdhesion": "8.33%","gastrointestinalObstruction": "9.27%","hospitalStayDays": 34,"createTime": "2024-07-25 16:08:31",
             *     "updateTime": "2024-07-25 16:08:31","createBy": null,"updateBy": 1},
             *     "map": {}
             * }
             */
            PredictionResult predictionResult = responseEntity.getBody();

            OutputData outputData = new OutputData();
            outputData.setId(patientDataId);
            outputData.setMyocardialInfarction(predictionResult.getOrdered_predictions().get("心肌梗死"));
            outputData.setCerebralHemorrhage(predictionResult.getOrdered_predictions().get("脑出血"));
            outputData.setCerebralEmbolism(predictionResult.getOrdered_predictions().get("脑栓塞"));
            outputData.setCerebralInfarction(predictionResult.getOrdered_predictions().get("脑梗死"));
            outputData.setRespiratoryFailure(predictionResult.getOrdered_predictions().get("呼吸衰竭"));
            outputData.setPneumonia(predictionResult.getOrdered_predictions().get("肺炎"));
            outputData.setVentilatorDependence(predictionResult.getOrdered_predictions().get("呼吸机依赖"));
            outputData.setPulmonaryEmbolism(predictionResult.getOrdered_predictions().get("肺栓塞"));
            outputData.setDeepVeinThrombosis(predictionResult.getOrdered_predictions().get("下肢深静脉血栓"));
            outputData.setSuperficialInfection(predictionResult.getOrdered_predictions().get("浅表切口感染（切口感染_裂开）"));
            outputData.setAnastomoticLeak(predictionResult.getOrdered_predictions().get("吻合口瘘"));
            outputData.setOrganInfection(predictionResult.getOrdered_predictions().get("体腔内器官周围感染"));
            outputData.setAbdominalCtBleeding(predictionResult.getOrdered_predictions().get("腹部CT_腹腔出血"));
            outputData.setAbdominalCtFluidAccumulation(predictionResult.getOrdered_predictions().get("腹部CT_腹腔积液"));
            outputData.setUnplannedReoperation(predictionResult.getOrdered_predictions().get("非计划再手术"));
            outputData.setVascularBleeding(predictionResult.getOrdered_predictions().get("出血（血管外科）"));
            outputData.setGastrointestinalAdhesion(predictionResult.getOrdered_predictions().get("肠粘连(胃肠外科)"));
            outputData.setGastrointestinalObstruction(predictionResult.getOrdered_predictions().get("肠梗阻(胃肠外科)"));
            outputData.setHospitalStayDays(predictionResult.getPredicted_hospitalization_days());
            outputData.setDeleteFlag(0);

            Date date=new Date();
            LocalDateTime localDateTime = date.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
            outputData.setCreateTime(localDateTime);

            outputDataService.saveOutput(outputData);
            patientDataService.insert(patientData);
            // 返回给前端
            return R.success(outputData);

        } catch (DataAccessException e) {
            // Handle database access errors
            e.printStackTrace();
            return R.error("Database error: " + e.getMessage());
        } catch (HttpClientErrorException e) {
            // Handle HTTP client errors
            e.printStackTrace();
            return R.error("HTTP error: " + e.getMessage());
        } catch (Exception e) {
            // Handle all other exceptions
            e.printStackTrace();
            return R.error("Error: " + e.getMessage());
        }
    }


    private Map<String, Object> convertToFastApiFormat(LegacyPatientData patientData) {
        Map<String, Object> dataMap = new HashMap<>();
        List<Object> features = Arrays.asList(
                patientData.getPatientAge() != null ? patientData.getPatientAge() : 0,
                patientData.getPatientAdmissionDiagnosis() != null ? patientData.getPatientAdmissionDiagnosis() : "",
                patientData.getPatientBmi() != null ? patientData.getPatientBmi() : 0.0,
                patientData.getCardiacFunctionNyhaClass() != null ? patientData.getCardiacFunctionNyhaClass() : "",
                patientData.getPhysicalStatusAsaClass() != null ? patientData.getPhysicalStatusAsaClass() : "",
                patientData.getPhysicalStatusNrs2002Score() != null ? patientData.getPhysicalStatusNrs2002Score() : 0.0,
                patientData.getPrimarySurgicalSite() != null ? patientData.getPrimarySurgicalSite() : "",
                patientData.getSurgeryName() != null ? patientData.getSurgeryName() : "",
                patientData.getPatientGender() != null ? patientData.getPatientGender() : "",
                patientData.getAlcoholHistory() != null ? patientData.getAlcoholHistory() : "",
                patientData.getSmokingHistory() != null ? patientData.getSmokingHistory() : "",
                patientData.getSystolicBloodPressure() != null ? patientData.getSystolicBloodPressure() : 0.0,
                patientData.getDiastolicBloodPressure() != null ? patientData.getDiastolicBloodPressure() : 0.0,
                patientData.getHypertensionMedicationNeeded() != null ? patientData.getHypertensionMedicationNeeded() : "",
                patientData.getLiverFunctionAlbumin() != null ? patientData.getLiverFunctionAlbumin() : 0.0,
                patientData.getCoagulationDDimer() != null ? patientData.getCoagulationDDimer() : 0.0,
                patientData.getCoagulationAptt() != null ? patientData.getCoagulationAptt() : 0.0,
                patientData.getCoagulationPt() != null ? patientData.getCoagulationPt() : 0.0,
                patientData.getLiverFunctionPrealbumin() != null ? patientData.getLiverFunctionPrealbumin() : 0.0,
                patientData.getBloodRoutineHemoglobin() != null ? patientData.getBloodRoutineHemoglobin() : 0.0,
                patientData.getLiverFunctionTotalBilirubin() != null ? patientData.getLiverFunctionTotalBilirubin() : 0.0,
                patientData.getLiverFunctionDirectBilirubin() != null ? patientData.getLiverFunctionDirectBilirubin() : 0.0,
                patientData.getBloodRoutineWbcCount() != null ? patientData.getBloodRoutineWbcCount() : 0.0,
                patientData.getElectrolytePotassium() != null ? patientData.getElectrolytePotassium() : 0.0,
                patientData.getBloodGlucoseFasting() != null ? patientData.getBloodGlucoseFasting() : 0.0,
                patientData.getBloodLipidsTriglycerides() != null ? patientData.getBloodLipidsTriglycerides() : 0.0,
                patientData.getLiverFunctionIndirectBilirubin() != null ? patientData.getLiverFunctionIndirectBilirubin() : 0.0,
                patientData.getCoagulationAntithrombinIii() != null ? patientData.getCoagulationAntithrombinIii() : 0.0,
                patientData.getKidneyFunctionBun() != null ? patientData.getKidneyFunctionBun() : 0.0,
                patientData.getKidneyFunctionCreatinine() != null ? patientData.getKidneyFunctionCreatinine() : 0.0,
                patientData.getLiverFunctionAlt() != null ? patientData.getLiverFunctionAlt() : 0.0,
                patientData.getLiverFunctionAst() != null ? patientData.getLiverFunctionAst() : 0.0,
                patientData.getChestCtLungInflammation() != null ? patientData.getChestCtLungInflammation() : "",
                patientData.getCardiacEchoLeftVentricularDysfunction() != null ? patientData.getCardiacEchoLeftVentricularDysfunction() : "",
                patientData.getRespiratorySevereCopdHistory() != null ? patientData.getRespiratorySevereCopdHistory() : "",
                patientData.getPreoperativeChfWithin30Days() != null ? patientData.getPreoperativeChfWithin30Days() : "",
                patientData.getKidneyFunctionDialysis() != null ? patientData.getKidneyFunctionDialysis() : "",
                patientData.getKidneyFunctionAcuteFailure() != null ? patientData.getKidneyFunctionAcuteFailure() : "",
                patientData.getRespiratoryDifficulty() != null ? patientData.getRespiratoryDifficulty() : "",
                patientData.getPreoperativeSepsisWithin48Hours() != null ? patientData.getPreoperativeSepsisWithin48Hours() : ""
        );
        dataMap.put("features", features);
        return dataMap;
    }
}





