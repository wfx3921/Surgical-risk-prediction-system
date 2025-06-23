<template>
  <div>
    <el-tabs v-model="activeTab" style="width: 100%;height: 100%;">
      <div class="form-container">
        <el-form ref="generalInfo" :model="generalInfo" :rules="rules0">
          <!-- 一般信息 -->
          <h3>一般信息</h3>
          <el-row :gutter="30">
            <el-col :span="6">
              <el-form-item label="住院号" prop="caseNumber">
                <el-input v-model="generalInfo.caseNumber" :disabled="isDisabled"></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="6">
              <el-form-item label="患者姓名" prop="patientName">
                <el-input v-model="generalInfo.patientName" placeholder="请输入"></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="6">
              <el-form-item label="患者年龄" prop="age">
                <el-input v-model="generalInfo.age"></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="6">
              <el-form-item label="BMI" prop="bmi">
                <el-input v-model="generalInfo.bmi"></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="6">
              <el-form-item label="NRS2002" prop="nrs">
                <el-select v-model="generalInfo.nrs" placeholder="请选择nrs分级">
                  <el-option label="一级" value=1></el-option>
                  <el-option label="二级" value=2></el-option>
                  <el-option label="三级" value=3></el-option>
                  <el-option label="四级" value=4></el-option>
                </el-select>
              </el-form-item>
            </el-col>

            <el-col :span="6">
              <el-form-item label="手术部位" prop="surgerySite">
                <el-select v-model="generalInfo.surgerySite">
                  <el-option label="肝" value="肝"></el-option>
                </el-select>
              </el-form-item>
            </el-col>

            <el-col :span="6">
              <el-form-item label="手术名称" prop="surgeryName">
                <el-select v-model="generalInfo.surgeryName" placeholder="请选择手术名称">
                  <el-option label="肝切除术" value="肝切除术"></el-option>
                  <el-option label="其他手术" value="其他手术"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            
            <el-col :span="6">
              <el-form-item label="NYHA分级" prop="nyhaGrade">
                <el-select v-model="generalInfo.nyhaGrade">
                  <el-option label="I" value="I"></el-option>
                  <el-option label="II" value="II"></el-option>
                  <el-option label="III" value="III"></el-option>
<!--                  <el-option label="IV" value="IV"></el-option>-->
                </el-select>
              </el-form-item>
            </el-col>

            <el-col :span="6">
              <el-form-item label="ASA分级" prop="asaGrade">
                <el-select v-model="generalInfo.asaGrade">
                  <el-option label="第一级" value="第一级"></el-option>
                  <el-option label="第二级" value="第二级"></el-option>
                  <el-option label="第三级" value="第三级"></el-option>
                  <el-option label="第四级" value="第四级"></el-option>
<!--                  <el-option label="第五级" value="第五级"></el-option>-->
                </el-select>
              </el-form-item>
            </el-col>

          </el-row>
          <el-row :gutter="20">
            <el-col :span="6">
              <el-form-item label="患者性别" prop="gender">
                <el-radio-group v-model="generalInfo.gender">
                  <el-radio :label="'男'">男</el-radio>
                  <el-radio :label="'女'">女</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="饮酒" prop="drinking">
                <el-radio-group v-model="generalInfo.drinking">
                  <el-radio :label="'是'">是</el-radio>
                  <el-radio :label="'否'">否</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="吸烟" prop="smoking">
                <el-radio-group v-model="generalInfo.smoking">
                  <el-radio :label="'是'">是</el-radio>
                  <el-radio :label="'否'">否</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="高血压是否需要药物治疗" prop="hypertensionTreatment">
                <el-radio-group v-model="generalInfo.hypertensionTreatment">
                  <el-radio :label="'是'">是</el-radio>
                  <el-radio :label="'否'">否</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="高压" prop="highPressure">
                <el-input v-model="generalInfo.highPressure"></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="6">
              <el-form-item label="低压" prop="lowPressure">
                <el-input v-model="generalInfo.lowPressure"></el-input>
              </el-form-item>
            </el-col>

          </el-row>
        </el-form>
        <el-form ref="labTests" :model="labTests" :rules="rules1">
          <!-- 实验室检查 -->
          <h3>实验室检查</h3>
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="红细胞" prop="aaa">
                <el-input v-model="labTests.albumin"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="血红蛋白" prop="bbb">
                <el-input v-model="labTests.dDimer"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="白细胞" prop="ccc">
                <el-input v-model="labTests.aptt"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="血小板" prop="ddd">
                <el-input v-model="labTests.ptsec"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="凝血酶原时间_PT" prop="eee">
                <el-input v-model="labTests.preAlbumin"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="活化部分凝血活酶时间_APTT" prop="fff">
                <el-input v-model="labTests.hemoglobin"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="纤维蛋白原_FIB" prop="ggg">
                <el-input v-model="labTests.totalBilirubin"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="电解质钾" prop="hhh">
                <el-input v-model="labTests.electrolytePotassium"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="甲胎蛋白_AFP" prop="iii">
                <el-input v-model="labTests.directBilirubin"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="癌胚抗原_CEA" prop="jjj">
                <el-input v-model="labTests.leukocyte"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="糖类抗原 19 - 9_CA19 - 9" prop="kkk">
                <el-input v-model="labTests.bloodSugar"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="血氨" prop="lll">
                <el-input v-model="labTests.triglyceride"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="乙肝抗体" prop="mmm">
                <el-input v-model="labTests.Indirectbilirubin"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="乙肝病毒DNA" prop="ooo">
                <el-input v-model="labTests.antithrombin"></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item label="丙肝抗体" prop="ppp">
                <el-input v-model="labTests.bloodUreaNitrogen"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="肾功能_Cr" prop="RenalCr">
                <el-input v-model="labTests.RenalCr"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="肝功能_ALT" prop="ALT">
                <el-input v-model="labTests.ALT"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="肝功能_AST" prop="AST">
                <el-input v-model="labTests.AST"></el-input>

              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
        <el-form ref="auxiliaryExams" :model="auxiliaryExams" :rules="rules2">
          <!-- 辅助检查 -->
          <h3>辅助检查</h3>
          <el-row :gutter="20">
            <el-col :span="6">
              <el-form-item label="胸部CT 肺部炎症" prop="chestCT">
                <!-- <el-radio-group v-model="auxiliaryExams.chestCT">
                    <el-radio :label="true">是</el-radio>
                    <el-radio :label="false">否</el-radio>
                  </el-radio-group> -->
                <el-select v-model="auxiliaryExams.chestCT">
                  <el-option label="否" value="否"></el-option>
                  <el-option label="轻微" value="轻微"></el-option>
                  <el-option label="轻度" value="轻度"></el-option>
                  <el-option label="中度" value="中度"></el-option>
                  <el-option label="偏重" value="偏重"></el-option>
                  <el-option label="重度" value="重度"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="心脏超声 左室舒张功能减低" prop="heartEcho">
                <!-- <el-radio-group v-model="auxiliaryExams.heartEcho">
                    <el-radio :label="true">是</el-radio>
                    <el-radio :label="false">否</el-radio>
                  </el-radio-group> -->
                <el-select v-model="auxiliaryExams.heartEcho">
                  <el-option label="否" value="否"></el-option>
                  <el-option label="轻微" value="轻微"></el-option>
                  <el-option label="轻度" value="轻度"></el-option>
                  <el-option label="中度" value="中度"></el-option>
                  <el-option label="偏重" value="偏重"></el-option>
                  <el-option label="重度" value="重度"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="呼吸功能 严重COPD病史" prop="historyCOPD">
                <!-- <el-radio-group v-model="auxiliaryExams.historyCOPD">
                    <el-radio :label="true">是</el-radio>
                    <el-radio :label="false">否</el-radio>
                  </el-radio-group> -->
                <el-select v-model="auxiliaryExams.historyCOPD">
                  <el-option label="否" value="否"></el-option>
                  <el-option label="轻微" value="轻微"></el-option>
                  <el-option label="轻度" value="轻度"></el-option>
                  <el-option label="中度" value="中度"></el-option>
                  <el-option label="偏重" value="偏重"></el-option>
                  <el-option label="重度" value="重度"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="手术前30天的充血性心力衰竭" prop="Congestiveheartfailure">
                <!-- <el-radio-group v-model="auxiliaryExams.Congestiveheartfailure">
                    <el-radio :label="true">是</el-radio>
                    <el-radio :label="false">否</el-radio>
                  </el-radio-group> -->
                <el-select v-model="auxiliaryExams.Congestiveheartfailure">
                  <el-option label="否" value="否"></el-option>
                  <el-option label="轻微" value="轻微"></el-option>
                  <el-option label="轻度" value="轻度"></el-option>
                  <el-option label="中度" value="中度"></el-option>
                  <el-option label="偏重" value="偏重"></el-option>
                  <el-option label="重度" value="重度"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="肾功能不全 是否透析" prop="Dialysis">
                <!-- <el-radio-group v-model="auxiliaryExams.Dialysis">
                    <el-radio :label="true">是</el-radio>
                    <el-radio :label="false">否</el-radio>
                  </el-radio-group> -->
                <el-select v-model="auxiliaryExams.Dialysis">
                  <el-option label="否" value="否"></el-option>
                  <el-option label="轻微" value="轻微"></el-option>
                  <el-option label="轻度" value="轻度"></el-option>
                  <el-option label="中度" value="中度"></el-option>
                  <el-option label="偏重" value="偏重"></el-option>
                  <el-option label="重度" value="重度"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="肾功能不全 急性肾功能衰竭" prop="AcuteRenalFailure">
                <!-- <el-radio-group v-model="auxiliaryExams.AcuteRenalFailure">
                    <el-radio :label="true">是</el-radio>
                    <el-radio :label="false">否</el-radio>
                  </el-radio-group> -->
                <el-select v-model="auxiliaryExams.AcuteRenalFailure">
                  <el-option label="否" value="否"></el-option>
                  <el-option label="轻微" value="轻微"></el-option>
                  <el-option label="轻度" value="轻度"></el-option>
                  <el-option label="中度" value="中度"></el-option>
                  <el-option label="偏重" value="偏重"></el-option>
                  <el-option label="重度" value="重度"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="呼吸困难" prop="respiratoryDifficulties">
                <!-- <el-radio-group v-model="auxiliaryExams.respiratoryDifficulties">
                    <el-radio :label="true">是</el-radio>
                    <el-radio :label="false">否</el-radio>
                  </el-radio-group> -->
                <el-select v-model="auxiliaryExams.respiratoryDifficulties">
                  <el-option label="否" value="否"></el-option>
                  <el-option label="轻微" value="轻微"></el-option>
                  <el-option label="轻度" value="轻度"></el-option>
                  <el-option label="中度" value="中度"></el-option>
                  <el-option label="偏重" value="偏重"></el-option>
                  <el-option label="重度" value="重度"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="手术前48小时内出现全身性败血症" prop="sepsisWithin48Hours">
                <!-- <el-radio-group v-model="auxiliaryExams.sepsisWithin48Hours">
                    <el-radio :label="true">是</el-radio>
                    <el-radio :label="false">否</el-radio>
                  </el-radio-group> -->
                <el-select v-model="auxiliaryExams.sepsisWithin48Hours">
                  <el-option label="否" value="否"></el-option>
                  <el-option label="轻微" value="轻微"></el-option>
                  <el-option label="轻度" value="轻度"></el-option>
                  <el-option label="中度" value="中度"></el-option>
                  <el-option label="偏重" value="偏重"></el-option>
                  <el-option label="重度" value="重度"></el-option>
                </el-select>
              </el-form-item>
            </el-col>

          </el-row>

          <el-button type="primary" @click="handle()" style="display: block; margin: 50px auto;">风险评估</el-button>
        </el-form>
      </div>
    </el-tabs>
    <div class="block-container"></div>
<!--    <div class="block-container"></div>-->
<!--    <div class="block-container"></div>-->
<!--    <div class="block-container"></div>-->
  </div>
</template>

<script>
import Popup from './Popup.vue';
import Cookie from 'js-cookie';
import request from '@/utils/request';

export default {
  name: "About",// 组件名称
  data() {
    return {
      is_search: false,
      is_search_content: '显示搜索',
      isDisabled: false,
      total: 0,
      size: 10,
      currentPage: 1,
      pages: 0,
      hospitalizationNumber: '',
      createTime: '',
      updateTime: '',
      primarySurgicalSite: '',
      patientGender: '',
      patientAge: '',
      smokingHistory: '',
      alcoholHistory: '',
      patientBmi: '',
      bloodRoutineHemoglobin: '',
      coagulationDDimer: '',
      coagulationAntithrombinIii: '',
      liverFunctionDirectBilirubin: '',

      searchForm: {
        hospitalizationNumber: '',
        primarySurgicalSite: '',
        patientGender: '',
        smokingHistory: '',
        alcoholHistory: '',
        patientBmi: '',
        patientBmi2: '',
        bloodRoutineHemoglobin: '',
        bloodRoutineHemoglobin2: '',
        coagulationDDimer: '',
        coagulationDDimer2: '',
        coagulationAntithrombinIii: '',
        coagulationAntithrombinIii2: '',
        liverFunctionDirectBilirubin: '',
        liverFunctionDirectBilirubin2: '',
        creator: ''
      },

      filteredItems: [],

      checkedItems: {},
      selectAll: false,
      activeTab: "riskAssessment",
      generalInfo: {
        caseNumber: '',
        patientName: '',
        age: '',
        diagnosis: "肝肿瘤",
        bmi: '',
        nrs: 0,
        height: '',
        highPressure: '',
        lowPressure: '',
        nyhaGrade: '',
        asaGrade: '',
        surgeryName: '',
        surgerySite: '',
        gender: '',
        drinking: '',
        smoking: '',
        hypertensionTreatment: '',
      },
      labTests: {
        albumin: '',
        preAlbumin: '',
        bloodSugar: '',
        dDimer: '',
        aptt: '',
        ptsec: '',
        totalBilirubin: '',
        directBilirubin: '',
        electrolytePotassium: '',
        bloodUreaNitrogen: '',
        hemoglobin: '',
        leukocyte: '',
        triglyceride: '',
        Indirectbilirubin: '',
        antithrombin: '',
        RenalCr: '',
        ALT: '',
        AST: '',
      },
      auxiliaryExams: {
        historyCOPD: '',
        Congestiveheartfailure: '',
        Dialysis: '',
        AcuteRenalFailure: '',
        chestCT: '',
        heartEcho: '',
        acuteKidneyInjury: '',
        ascitesWithin30Days: '',
        sepsisWithin48Hours: '',
        respiratoryDifficulties: '',
      },
      popupVisible: false,
      popupContent: '这是一个弹窗',

      rules0: {
        caseNumber: [
          { required: true, message: '请输入住院号', trigger: 'blur' }
        ],
        patientName: [
          { required: true, message: '请输入患者姓名', trigger: 'blur' }
        ],
        age: [
          { required: true, message: '请输入患者年龄', trigger: 'blur' }
        ],
        diagnosis: [
          { required: true, message: '请选择入院主诊断名称', trigger: 'change' }
        ],
        bmi: [
          { required: false, message: '请输入BMI', trigger: 'blur' }
        ],
        nrs: [
          { required: true, message: '请输入NRS2002', trigger: 'blur' }
        ],
        surgerySite: [
          { required: true, message: '请选择手术部位', trigger: 'change' }
        ],
        surgeryName: [
          { required: true, message: '请选择手术名称', trigger: 'change' }
        ],
        nyhaGrade: [
          { required: true, message: '请选择NYHA分级', trigger: 'change' }
        ],
        asaGrade: [
          { required: true, message: '请选择ASA分级', trigger: 'change' }
        ],
        gender: [
          { required: true, message: '请选择患者性别', trigger: 'change' }
        ],
        drinking: [
          { required: true, message: '请选择是否饮酒', trigger: 'change' }
        ],
        smoking: [
          { required: true, message: '请选择是否吸烟', trigger: 'change' }
        ],
        hypertensionTreatment: [
          { required: false, message: '请选择高血压是否需要药物治疗', trigger: 'change' }
        ],
        highPressure: [
          { required: false, message: '请输入高压', trigger: 'blur' }
        ],
        lowPressure: [
          { required: false, message: '请输入低压', trigger: 'blur' }
        ],
      },
      rules1: {
        albumin: [
          { required: true, message: '请输入白蛋白', trigger: 'blur' }
        ],
        dDimer: [
          { required: true, message: '请输入D-二聚体', trigger: 'blur' }
        ],
        aptt: [
          { required: true, message: '请输入APTT', trigger: 'blur' }
        ],
        ptsec: [
          { required: true, message: '请输入PTsec', trigger: 'blur' }
        ],
        preAlbumin: [
          { required: true, message: '请输入前白蛋白', trigger: 'blur' }
        ],
        hemoglobin: [
          { required: true, message: '请输入血红蛋白', trigger: 'blur' }
        ],
        totalBilirubin: [
          { required: true, message: '请输入总胆红素', trigger: 'blur' }
        ],
        directBilirubin: [
          { required: true, message: '请输入直接胆红素', trigger: 'blur' }
        ],
        leukocyte: [
          { required: true, message: '请输入白细胞', trigger: 'blur' }
        ],
        electrolytePotassium: [
          { required: true, message: '请输入电解质钾', trigger: 'blur' }
        ],
        bloodSugar: [
          { required: true, message: '请输入空腹血糖值', trigger: 'blur' }
        ],
        triglyceride: [
          { required: true, message: '请输入甘油三酯', trigger: 'blur' }
        ],
        Indirectbilirubin: [
          { required: true, message: '请输入间接胆黄素', trigger: 'blur' }
        ],
        antithrombin: [
          { required: true, message: '请输入抗凝血酶Ⅲ', trigger: 'blur' }
        ],
        bloodUreaNitrogen: [
          { required: true, message: '请输入尿素氮', trigger: 'blur' }
        ],
        RenalCr: [
          { required: true, message: '请输入肾功能_Cr', trigger: 'blur' }
        ],
        ALT: [
          { required: true, message: '请输入肝功能_ALT', trigger: 'blur' }
        ],
        AST: [
          { required: true, message: '请输入肝功能_AST', trigger: 'blur' }
        ],
      },
      rules2: {
        chestCT: [
          { required: true, message: '请选择胸部CT 肺部炎症', trigger: 'change' }
        ],
        heartEcho: [
          { required: true, message: '请选择心脏超声 左室舒张功能减低', trigger: 'change' }
        ],
        historyCOPD: [
          { required: true, message: '请选择呼吸功能 严重COPD病史', trigger: 'change' }
        ],
        Congestiveheartfailure: [
          { required: true, message: '请选择手术前30天的充血性心力衰竭', trigger: 'change' }
        ],
        Dialysis: [
          { required: true, message: '请选择肾功能不全 是否透析', trigger: 'change' }
        ],
        AcuteRenalFailure: [
          { required: true, message: '请选择肾功能不全 急性肾功能衰竭', trigger: 'change' }
        ],
        respiratoryDifficulties: [
          { required: true, message: '请选择呼吸困难', trigger: 'change' }
        ],
        sepsisWithin48Hours: [
          { required: true, message: '请选择手术前48小时内出现全身性败血症', trigger: 'change' }
        ]
      }

    }

  },
  created() {
    this.search(1, this.size);
    console.log(this.generalInfo);
  },
  mounted() {
    if (this.$route.query.generalInfo) {
      this.generalInfo = JSON.parse(this.$route.query.generalInfo);
    }
    if (this.$route.query.labTests) {
      this.labTests = JSON.parse(this.$route.query.labTests);
    }
    if (this.$route.query.auxiliaryExams) {
      this.auxiliaryExams = JSON.parse(this.$route.query.auxiliaryExams);
    }
  },
  methods: {
    handlePopup(row) {
      Cookie.set('OutputId', row.userId);
      this.$router.push('/Homepage');
    },
    handleSelectionChange(selection) {
      this.checkedItems = {}; // 清空之前的选择
      selection.forEach(item => {
        this.checkedItems[item.userId] = true; // 记录所选行的 userId

      });
    },
    moreDelete() {
      this.$confirm('确认删除？?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        const filteredIds = Object.keys(this.checkedItems).filter(id => this.checkedItems[id] === true)
        const deletePromises = filteredIds.map(id => {
          return request.delete(`/patientdata/delete/${id}`)
            .then(response => {
              //console.log('1'); //
              //console.log(response.data); //
              if (response.data.code === 0) {
                throw new Error(`Failed to delete user with id ${id}`);
              } else {
                console.log(`Deleted user with id ${id}`);
              }
            });
        });
        Promise.all(deletePromises)
          .then(() => {

            this.updatePageSize();
            this.checkedItems = {};
          })
          .catch(error => {
            console.error(error);
            alert('删除用户时发生错误，请重试。');
          });
      })
        .catch(() => {
          // 点击取消:不删除了
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });

    },
    resetForm() {
      this.searchForm.hospitalizationNumber = '';
      this.searchForm.primarySurgicalSite = '';
      this.searchForm.patientGender = '';
      this.searchForm.smokingHistory = '';
      this.searchForm.alcoholHistory = '';
      this.searchForm.patientBmi = '';
      this.searchForm.patientBmi2 = '';
      this.searchForm.bloodRoutineHemoglobin = '';
      this.searchForm.bloodRoutineHemoglobin2 = '';
      this.searchForm.coagulationDDimer = '';
      this.searchForm.coagulationDDimer2 = '';
      this.searchForm.coagulationAntithrombinIii = '';
      this.searchForm.coagulationAntithrombinIii2 = '';
      this.searchForm.liverFunctionDirectBilirubin = '';
      this.searchForm.liverFunctionDirectBilirubin2 = '';

    },
    //更新页面
    updatePageSize() {
      this.search(1, this.size);
    },
    // 前一页
    prevPage() {

      if (this.currentPage > 1) {
        this.search(this.currentPage - 1, this.size);
      }
    },
    // 下一页
    nextPage() {
      if (this.currentPage < this.pages) {
        this.search(this.currentPage + 1, this.size);
      }
    },
    search_YoN() {
      this.is_search = !this.is_search; // 切换搜索状态
      console.log(this.is_search_content);
      this.is_search_content = this.is_search ? '隐藏搜索' : '显示搜索'; // 根据状态更新内容
      console.log(this.is_search_content);

    },
    search() {
      const userId1 = JSON.parse(Cookie.get('userInfo'));
      this.searchForm.creator = userId1.userId;
      //console.log(this.searchForm.creator);
      //console.log(JSON.stringify(this.searchForm, null, 2));
      
      request.post(`/patientdata/getlist?page=${this.currentPage}&pageSize=${this.size}`, this.searchForm)
        .then(response => {
          //console.log(response.data);
          if (response.data.code === 1) {
            const items = response.data.data.content.map(user => {
              return {
                userId: user.id,
                hospitalizationNumber: user.hospitalizationNumber,
                primarySurgicalSite: user.primarySurgicalSite,
                patientGender: user.patientGender,
                smokingHistory: user.smokingHistory,
                alcoholHistory: user.alcoholHistory,
                patientBmi: user.patientBmi,
                patientBmi2: user.patientBmi2,
                patientAge: user.patientAge,
                createTime: user.createTime,
                updateTime: user.updateTime,
                bloodRoutineHemoglobin: user.bloodRoutineHemoglobin,
                bloodRoutineHemoglobin2: user.bloodRoutineHemoglobin2,
                coagulationDDimer: user.coagulationDDimer,
                coagulationDDimer2: user.coagulationDDimer2,
                coagulationAntithrombinIii: user.coagulationAntithrombinIii,
                coagulationAntithrombinIii2: user.coagulationAntithrombinIii2,
                liverFunctionDirectBilirubin: user.liverFunctionDirectBilirubin,
                liverFunctionDirectBilirubin2: user.liverFunctionDirectBilirubin2,

              };
            });

            this.filteredItems = items;
            this.total = response.data.data.total;
            this.size = response.data.data.size;
            this.currentPage = response.data.data.current;
            this.pages = Math.ceil(this.total / this.size);
          } else {
            throw new Error('Failed to fetch users');
          }
        })
        .catch(error => {
          console.error(error);
        });

    },

    result(row) {
      Cookie.set('OutputId', row.userId);
      this.$router.push('/Homepage');
    },
    retest(row) {
      request.get(`/patientdata/getPatientData/${row.userId}`)
        .then(response => {
          this.activeTab = "riskAssessment";
          //console.log(response.data.data);

          this.generalInfo.caseNumber = response.data.data.hospitalizationNumber,
            this.generalInfo.patientName = response.data.data.patientName,
            this.generalInfo.age = response.data.data.patientAge,
            this.generalInfo.diagnosis = response.data.data.patientAdmissionDiagnosis,
            this.generalInfo.bmi = response.data.data.patientBmi,
            this.generalInfo.nrs = response.data.data.physicalStatusNrs2002Score

            this.generalInfo.highPressure = response.data.data.systolicBloodPressure,
            this.generalInfo.lowPressure = response.data.data.diastolicBloodPressure,
            this.generalInfo.nyhaGrade = response.data.data.cardiacFunctionNyhaClass,
            this.generalInfo.asaGrade = response.data.data.physicalStatusAsaClass,
            this.generalInfo.surgeryName = response.data.data.surgeryName,
            this.generalInfo.surgerySite = response.data.data.primarySurgicalSite,
            this.generalInfo.gender = response.data.data.patientGender,
            this.generalInfo.drinking = response.data.data.alcoholHistory,
            this.generalInfo.smoking = response.data.data.smokingHistory,
            this.generalInfo.hypertensionTreatment = response.data.data.hypertensionMedicationNeeded,//
            this.labTests.albumin = response.data.data.liverFunctionAlbumin,
            this.labTests.preAlbumin = response.data.data.liverFunctionPrealbumin,
            this.labTests.bloodSugar = response.data.data.bloodGlucoseFasting,//
            this.labTests.dDimer = response.data.data.coagulationDDimer,
            this.labTests.aptt = response.data.data.coagulationAptt,
            this.labTests.ptsec = response.data.data.coagulationPt,
            this.labTests.totalBilirubin = response.data.data.liverFunctionTotalBilirubin,
            this.labTests.directBilirubin = response.data.data.liverFunctionDirectBilirubin,
            this.labTests.electrolytePotassium = response.data.data.electrolytePotassium,
            this.labTests.bloodUreaNitrogen = response.data.data.kidneyFunctionBun,//
            this.labTests.hemoglobin = response.data.data.bloodRoutineHemoglobin,
            this.labTests.leukocyte = response.data.data.bloodRoutineWbcCount,//白细胞//
            this.labTests.triglyceride = response.data.data.bloodLipidsTriglycerides,//甘油三酯
            this.labTests.Indirectbilirubin = response.data.data.liverFunctionIndirectBilirubin,//间接胆黄素
            this.labTests.antithrombin = response.data.data.coagulationAntithrombinIii,//抗凝血酶Ⅲ
            this.labTests.RenalCr = response.data.data.kidneyFunctionCreatinine,//肾功能Cr//
            this.labTests.ALT = response.data.data.liverFunctionAlt,
            this.labTests.AST = response.data.data.liverFunctionAst,
            this.auxiliaryExams.historyCOPD = response.data.data.respiratorySevereCopdHistory,
            this.auxiliaryExams.Dialysis = response.data.data.kidneyFunctionDialysis,
            this.auxiliaryExams.AcuteRenalFailure = response.data.data.kidneyFunctionAcuteFailure,
            this.auxiliaryExams.chestCT = response.data.data.chestCtLungInflammation,
            this.auxiliaryExams.heartEcho = response.data.data.cardiacEchoLeftVentricularDysfunction,//
            this.auxiliaryExams.acuteKidneyInjury = response.data.data.kidneyFunctionAcuteFailure,//
            this.auxiliaryExams.Congestiveheartfailure = response.data.data.preoperativeChfWithin30Days,
            this.auxiliaryExams.sepsisWithin48Hours = response.data.data.preoperativeSepsisWithin48Hours,
            this.auxiliaryExams.respiratoryDifficulties = response.data.data.respiratoryDifficulty//

          //    this.isDisabled=true

        })
        .catch(error => {
        });

    },
    handle() {
      let a, b, c;
      this.$refs.generalInfo.validate(valid => {
        if (!valid) {
          console.log('表单验证失败0');
          return false;
        } else {
          a = true;
        }

      });
      this.$refs.labTests.validate(valid => {
        if (!valid) {
          console.log('表单验证失败1');
          return false;
        } else {
          b = true;
        }
      });
      this.$refs.auxiliaryExams.validate(valid => {
        if (!valid) {
          console.log('表单验证失败2');
          return false;
        } else {
          c = true;
        }
      });
      if (!a || !b || !c) {
        return false;
      }
      let userId1 = JSON.parse(Cookie.get('userInfo'));
      this.searchForm.createBy = userId1.userId;

      // if (this.auxiliaryExams.chestCT == '轻微' || this.auxiliaryExams.chestCT == '轻度' || this.auxiliaryExams.chestCT == '否') {
      //   this.auxiliaryExams.chestCT = '否';
      // } else {
      //   this.auxiliaryExams.chestCT = '是';
      // }

      // if (this.auxiliaryExams.heartEcho == '轻微' || this.auxiliaryExams.heartEcho == '轻度' || this.auxiliaryExams.heartEcho == '否') {
      //   this.auxiliaryExams.heartEcho = '否';
      // } else {
      //   this.auxiliaryExams.heartEcho = '是';
      // }

      // if (this.auxiliaryExams.historyCOPD == '轻微' || this.auxiliaryExams.historyCOPD == '轻度' || this.auxiliaryExams.historyCOPD == '否') {
      //   this.auxiliaryExams.historyCOPD = '否';
      // } else {
      //   this.auxiliaryExams.historyCOPD = '是';
      // }

      // if (this.auxiliaryExams.Congestiveheartfailure == '轻微' || this.auxiliaryExams.Congestiveheartfailure == '轻度' || this.auxiliaryExams.Congestiveheartfailure == '否') {
      //   this.auxiliaryExams.Congestiveheartfailure = '否';
      // } else {
      //   this.auxiliaryExams.Congestiveheartfailure = '是';
      // }

      // if (this.auxiliaryExams.Dialysis == '轻微' || this.auxiliaryExams.Dialysis == '轻度' || this.auxiliaryExams.Dialysis == '否') {
      //   this.auxiliaryExams.Dialysis = '否';
      // } else {
      //   this.auxiliaryExams.Dialysis = '是';
      // }

      // if (this.auxiliaryExams.AcuteRenalFailure == '轻微' || this.auxiliaryExams.AcuteRenalFailure == '轻度' || this.auxiliaryExams.AcuteRenalFailure == '否') {
      //   this.auxiliaryExams.AcuteRenalFailure = '否';
      // } else {
      //   this.auxiliaryExams.AcuteRenalFailure = '是';
      // }

      // if (this.auxiliaryExams.respiratoryDifficulties == '轻微' || this.auxiliaryExams.respiratoryDifficulties == '轻度' || this.auxiliaryExams.respiratoryDifficulties == '否') {
      //   this.auxiliaryExams.respiratoryDifficulties = '否';
      // } else {
      //   this.auxiliaryExams.respiratoryDifficulties = '是';
      // }

      // if (this.auxiliaryExams.sepsisWithin48Hours == '轻微' || this.auxiliaryExams.sepsisWithin48Hours == '轻度' || this.auxiliaryExams.sepsisWithin48Hours == '否') {
      //   this.auxiliaryExams.sepsisWithin48Hours = '否';
      // } else {
      //   this.auxiliaryExams.sepsisWithin48Hours = '是';
      // }





      request.post(`/api/predict`, {
        hospitalizationNumber: this.generalInfo.caseNumber,
        patientName: this.generalInfo.patientName,
        patientAge: this.generalInfo.age,
        // patientAdmissionDiagnosis: "胃恶性肿瘤",
        patientAdmissionDiagnosis: this.generalInfo.diagnosis,
        patientBmi: this.generalInfo.bmi,
        cardiacFunctionNyhaClass: this.generalInfo.nyhaGrade,
        physicalStatusAsaClass: this.generalInfo.asaGrade,
        physicalStatusNrs2002Score: this.generalInfo.nrs,
        // primarySurgicalSite: "胃",
        primarySurgicalSite: this.generalInfo.surgerySite,
        // surgeryName: "开腹远端胃切除术",
        surgeryName: this.generalInfo.surgeryName,
        patientGender: this.generalInfo.gender,
        alcoholHistory: this.generalInfo.drinking,
        smokingHistory: this.generalInfo.smoking,
        systolicBloodPressure: this.generalInfo.highPressure,
        diastolicBloodPressure: this.generalInfo.lowPressure,
        hypertensionMedicationNeeded: this.generalInfo.hypertensionTreatment,
        liverFunctionAlbumin: this.labTests.albumin,
        coagulationDDimer: this.labTests.dDimer,
        coagulationAptt: this.labTests.aptt,
        coagulationPt: this.labTests.ptsec,
        liverFunctionPrealbumin: this.labTests.preAlbumin,
        bloodGlucoseFasting: this.labTests.bloodSugar,
        bloodLipidsTriglycerides: this.labTests.triglyceride,
        liverFunctionTotalBilirubin: this.labTests.totalBilirubin,
        liverFunctionDirectBilirubin: this.labTests.directBilirubin,
        electrolytePotassium: this.labTests.electrolytePotassium,
        kidneyFunctionBun: this.labTests.bloodUreaNitrogen,
        bloodRoutineHemoglobin: this.labTests.hemoglobin,
        bloodRoutineWbcCount: this.labTests.leukocyte,
        liverFunctionIndirectBilirubin: this.labTests.Indirectbilirubin,
        coagulationAntithrombinIii: this.labTests.antithrombin,
        kidneyFunctionCreatinine: this.labTests.RenalCr,
        liverFunctionAlt: this.labTests.ALT,
        liverFunctionAst: this.labTests.AST,
        respiratorySevereCopdHistory: this.auxiliaryExams.historyCOPD,
        preoperativeChfWithin30Days: this.auxiliaryExams.Congestiveheartfailure,
        kidneyFunctionDialysis: this.auxiliaryExams.Dialysis,
        kidneyFunctionAcuteFailure: this.auxiliaryExams.AcuteRenalFailure,
        chestCtLungInflammation: this.auxiliaryExams.chestCT,
        cardiacEchoLeftVentricularDysfunction: this.auxiliaryExams.heartEcho,
        preoperativeSepsisWithin48Hours: this.auxiliaryExams.sepsisWithin48Hours,
        respiratoryDifficulty: this.auxiliaryExams.respiratoryDifficulties,
        createTime: null,
        createBy: this.searchForm.createBy,
        updateBy: null,
        updateTime: null,
      })
        .then(response => {
          if (response.data.code === 1) {
            Cookie.set('OutputId', response.data.data.id);
            console.log(Cookie.get('OutputId'));
            this.$router.push('/Homepage');
          } else {
          }
        })
        .catch(error => {
          console.error(error);
        });
      // this.$router.push('/Homepage');
    },
  },
  components: {
    Popup
  },
  computed: {
    currentSearchContent() {
      return this.is_search_content;
    }
  }
};
</script>

<style scoped>
.manage {
  height: 100%;

  .manage-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }

  .common-table {
    height: 90%;
    position: relative;
    width: 90%;
    line-height: 40px;

    .pager {
      position: absolute;
      right: 20px;
      bottom: 0;
    }
  }
}

.form-container {
  width: 95%;
  max-width: 1200px;
  height: auto;
  overflow: auto;
  margin: 20px auto;
  padding: 20px;
  border: 2px solid #1A3875;
  border-radius: 8px;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
  display: flex;
  flex-direction: column;
  align-items: stretch;
}

.block-container {
  height: 20px;
}

.form-container0 {
  width: 90%;
  height: 90%;
  /* max-width: 1400px;
  max-height: 550px; */
  overflow: auto;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: rgb(255, 255, 255);
}


h3 {
  margin-top: 20px;
}



.item-actions button {
  margin-left: 5px;
}

.el-tabs {
  margin-bottom: 20px;
}

.el-form-item {
  margin-bottom: 15px;
}

/* .el-button {
  margin-top: 30px;
  width: 100%;
} */
</style>