package com.ouc.riskprediction.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
@Document(collection="PatientData")
public class PatientData {

    private static final long serialVersionUID = 1L;

    /**
     * 这个字段在前端页面不需要展示
     */
    @Id
    private String id;
    @Field("hospitalization_number")
    private String hospitalizationNumber;
    @Field("patient_name")
    private String patientName;
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
    @Field("Department")
    private String department;
    @Field("staff")
    private String staff;
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
    // 心电图部分
    @Field("ecg_atrial_premature")
    private String ecgAtrialPremature;

    @Field("ecg_ventricular_premature")
    private String ecgVentricularPremature;

    @Field("ecg_atrial_fibrillation")
    private String ecgAtrialFibrillation;

    @Field("ecg_bradycardia")
    private String ecgBradycardia;

    @Field("ecg_conduction_block")
    private String ecgConductionBlock;

    // 心超部分
    @Field("echo_lvef")
    private Double echoLVEF;

    @Field("echo_pasp")
    private Double echoPASP;

    @Field("echo_bnp")
    private Double echoBNP;

    // 冠脉部分
    @Field("coronary_cta")
    private String coronaryCTA;

    @Field("coronary_troponin")
    private Double coronaryTroponin;

    // 影像检查
    @Field("brain_ct")
    private String brainCT;

    @Field("hba1c")
    private Double hba1c;


    // 结肠直肠共有的额外指标
    @Field("red")
    private double red;          // 红细胞

    @Field("platelet")
    private double platelet;     // 血小板

    @Field("carcinoembryonic")
    private double carcinoembryonic;  // 癌胚抗原

    @Field("bloodcreatinine")
    private double bloodcreatinine;   // 血肌酐

    @Field("thyroid")
    private double thyroid;      // 甲状腺激素

    @Field("bloodlipid")
    private double bloodlipid;   // 血脂

    // 结肠单独的指标
    @Field("reactiveprotein")
    private double reactiveprotein;  // C反应蛋白_CRP

    @Field("bloodtest")
    private double bloodtest;    // 粪便隐血试验

    @Field("alphafetoprotein")
    private double alphafetoprotein; // 甲胎蛋白_AFP

    // 直肠单独的指标
    @Field("ca125")
    private double ca;        // CA125

    @Field("antigen19")
    private double antigen19;    // 19-9

    @Field("viral")
    private double viral;        // 病毒性肝炎标志物







    // 特殊补充
    @Field("special_supplement")
    private String specialSupplement;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHospitalizationNumber() {
        return hospitalizationNumber;
    }

    public void setHospitalizationNumber(String hospitalizationNumber) {
        this.hospitalizationNumber = hospitalizationNumber;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Integer getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(Integer patientAge) {
        this.patientAge = patientAge;
    }

    public String getPatientAdmissionDiagnosis() {
        return patientAdmissionDiagnosis;
    }

    public void setPatientAdmissionDiagnosis(String patientAdmissionDiagnosis) {
        this.patientAdmissionDiagnosis = patientAdmissionDiagnosis;
    }

    public Double getPatientBmi() {
        return patientBmi;
    }

    public void setPatientBmi(Double patientBmi) {
        this.patientBmi = patientBmi;
    }

    public String getCardiacFunctionNyhaClass() {
        return cardiacFunctionNyhaClass;
    }

    public void setCardiacFunctionNyhaClass(String cardiacFunctionNyhaClass) {
        this.cardiacFunctionNyhaClass = cardiacFunctionNyhaClass;
    }

    public String getPhysicalStatusAsaClass() {
        return physicalStatusAsaClass;
    }

    public void setPhysicalStatusAsaClass(String physicalStatusAsaClass) {
        this.physicalStatusAsaClass = physicalStatusAsaClass;
    }

    public Double getPhysicalStatusNrs2002Score() {
        return physicalStatusNrs2002Score;
    }

    public void setPhysicalStatusNrs2002Score(Double physicalStatusNrs2002Score) {
        this.physicalStatusNrs2002Score = physicalStatusNrs2002Score;
    }

    public String getPrimarySurgicalSite() {
        return primarySurgicalSite;
    }

    public void setPrimarySurgicalSite(String primarySurgicalSite) {
        this.primarySurgicalSite = primarySurgicalSite;
    }

    public String getSurgeryName() {
        return surgeryName;
    }

    public void setSurgeryName(String surgeryName) {
        this.surgeryName = surgeryName;
    }

    public String getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender;
    }

    public String getAlcoholHistory() {
        return alcoholHistory;
    }

    public void setAlcoholHistory(String alcoholHistory) {
        this.alcoholHistory = alcoholHistory;
    }

    public String getSmokingHistory() {
        return smokingHistory;
    }

    public void setSmokingHistory(String smokingHistory) {
        this.smokingHistory = smokingHistory;
    }

    public Double getSystolicBloodPressure() {
        return systolicBloodPressure;
    }

    public void setSystolicBloodPressure(Double systolicBloodPressure) {
        this.systolicBloodPressure = systolicBloodPressure;
    }

    public Double getDiastolicBloodPressure() {
        return diastolicBloodPressure;
    }

    public void setDiastolicBloodPressure(Double diastolicBloodPressure) {
        this.diastolicBloodPressure = diastolicBloodPressure;
    }

    public String getHypertensionMedicationNeeded() {
        return hypertensionMedicationNeeded;
    }

    public void setHypertensionMedicationNeeded(String hypertensionMedicationNeeded) {
        this.hypertensionMedicationNeeded = hypertensionMedicationNeeded;
    }

    public Double getLiverFunctionAlbumin() {
        return liverFunctionAlbumin;
    }

    public void setLiverFunctionAlbumin(Double liverFunctionAlbumin) {
        this.liverFunctionAlbumin = liverFunctionAlbumin;
    }

    public Double getCoagulationDDimer() {
        return coagulationDDimer;
    }

    public void setCoagulationDDimer(Double coagulationDDimer) {
        this.coagulationDDimer = coagulationDDimer;
    }

    public Double getCoagulationAptt() {
        return coagulationAptt;
    }

    public void setCoagulationAptt(Double coagulationAptt) {
        this.coagulationAptt = coagulationAptt;
    }

    public Double getCoagulationPt() {
        return coagulationPt;
    }

    public void setCoagulationPt(Double coagulationPt) {
        this.coagulationPt = coagulationPt;
    }

    public Double getLiverFunctionPrealbumin() {
        return liverFunctionPrealbumin;
    }

    public void setLiverFunctionPrealbumin(Double liverFunctionPrealbumin) {
        this.liverFunctionPrealbumin = liverFunctionPrealbumin;
    }

    public Double getBloodRoutineHemoglobin() {
        return bloodRoutineHemoglobin;
    }

    public void setBloodRoutineHemoglobin(Double bloodRoutineHemoglobin) {
        this.bloodRoutineHemoglobin = bloodRoutineHemoglobin;
    }

    public Double getLiverFunctionTotalBilirubin() {
        return liverFunctionTotalBilirubin;
    }

    public void setLiverFunctionTotalBilirubin(Double liverFunctionTotalBilirubin) {
        this.liverFunctionTotalBilirubin = liverFunctionTotalBilirubin;
    }

    public Double getLiverFunctionDirectBilirubin() {
        return liverFunctionDirectBilirubin;
    }

    public void setLiverFunctionDirectBilirubin(Double liverFunctionDirectBilirubin) {
        this.liverFunctionDirectBilirubin = liverFunctionDirectBilirubin;
    }

    public Double getBloodRoutineWbcCount() {
        return bloodRoutineWbcCount;
    }

    public void setBloodRoutineWbcCount(Double bloodRoutineWbcCount) {
        this.bloodRoutineWbcCount = bloodRoutineWbcCount;
    }

    public Double getElectrolytePotassium() {
        return electrolytePotassium;
    }

    public void setElectrolytePotassium(Double electrolytePotassium) {
        this.electrolytePotassium = electrolytePotassium;
    }

    public Double getBloodGlucoseFasting() {
        return bloodGlucoseFasting;
    }

    public void setBloodGlucoseFasting(Double bloodGlucoseFasting) {
        this.bloodGlucoseFasting = bloodGlucoseFasting;
    }

    public Double getBloodLipidsTriglycerides() {
        return bloodLipidsTriglycerides;
    }

    public void setBloodLipidsTriglycerides(Double bloodLipidsTriglycerides) {
        this.bloodLipidsTriglycerides = bloodLipidsTriglycerides;
    }

    public Double getLiverFunctionIndirectBilirubin() {
        return liverFunctionIndirectBilirubin;
    }

    public void setLiverFunctionIndirectBilirubin(Double liverFunctionIndirectBilirubin) {
        this.liverFunctionIndirectBilirubin = liverFunctionIndirectBilirubin;
    }

    public Double getCoagulationAntithrombinIii() {
        return coagulationAntithrombinIii;
    }

    public void setCoagulationAntithrombinIii(Double coagulationAntithrombinIii) {
        this.coagulationAntithrombinIii = coagulationAntithrombinIii;
    }

    public Double getKidneyFunctionBun() {
        return kidneyFunctionBun;
    }

    public void setKidneyFunctionBun(Double kidneyFunctionBun) {
        this.kidneyFunctionBun = kidneyFunctionBun;
    }

    public Double getKidneyFunctionCreatinine() {
        return kidneyFunctionCreatinine;
    }

    public void setKidneyFunctionCreatinine(Double kidneyFunctionCreatinine) {
        this.kidneyFunctionCreatinine = kidneyFunctionCreatinine;
    }

    public Double getLiverFunctionAlt() {
        return liverFunctionAlt;
    }

    public void setLiverFunctionAlt(Double liverFunctionAlt) {
        this.liverFunctionAlt = liverFunctionAlt;
    }

    public Double getLiverFunctionAst() {
        return liverFunctionAst;
    }

    public void setLiverFunctionAst(Double liverFunctionAst) {
        this.liverFunctionAst = liverFunctionAst;
    }

    public String getChestCtLungInflammation() {
        return chestCtLungInflammation;
    }

    public void setChestCtLungInflammation(String chestCtLungInflammation) {
        this.chestCtLungInflammation = chestCtLungInflammation;
    }

    public String getCardiacEchoLeftVentricularDysfunction() {
        return cardiacEchoLeftVentricularDysfunction;
    }

    public void setCardiacEchoLeftVentricularDysfunction(String cardiacEchoLeftVentricularDysfunction) {
        this.cardiacEchoLeftVentricularDysfunction = cardiacEchoLeftVentricularDysfunction;
    }

    public String getRespiratorySevereCopdHistory() {
        return respiratorySevereCopdHistory;
    }

    public void setRespiratorySevereCopdHistory(String respiratorySevereCopdHistory) {
        this.respiratorySevereCopdHistory = respiratorySevereCopdHistory;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getPreoperativeChfWithin30Days() {
        return preoperativeChfWithin30Days;
    }

    public void setPreoperativeChfWithin30Days(String preoperativeChfWithin30Days) {
        this.preoperativeChfWithin30Days = preoperativeChfWithin30Days;
    }

    public String getKidneyFunctionDialysis() {
        return kidneyFunctionDialysis;
    }

    public void setKidneyFunctionDialysis(String kidneyFunctionDialysis) {
        this.kidneyFunctionDialysis = kidneyFunctionDialysis;
    }

    public String getKidneyFunctionAcuteFailure() {
        return kidneyFunctionAcuteFailure;
    }

    public void setKidneyFunctionAcuteFailure(String kidneyFunctionAcuteFailure) {
        this.kidneyFunctionAcuteFailure = kidneyFunctionAcuteFailure;
    }

    public String getRespiratoryDifficulty() {
        return respiratoryDifficulty;
    }

    public void setRespiratoryDifficulty(String respiratoryDifficulty) {
        this.respiratoryDifficulty = respiratoryDifficulty;
    }

    public String getPreoperativeSepsisWithin48Hours() {
        return preoperativeSepsisWithin48Hours;
    }

    public void setPreoperativeSepsisWithin48Hours(String preoperativeSepsisWithin48Hours) {
        this.preoperativeSepsisWithin48Hours = preoperativeSepsisWithin48Hours;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
    
    public String getSpecialSupplement() {
        return specialSupplement;
    }

    public void setSpecialSupplement(String specialSupplement) {
        this.specialSupplement = specialSupplement;
    }
}