<template>
  <div>
    <el-tabs v-model="activeTab" style="width: 100%;height: 100%;">
      <div class="form-container">
        <el-form ref="generalInfo" :model="generalInfo" :rules="rules0">
          <!-- 一般信息 -->
          <h3>一般信息(General Information)</h3>
          <el-row :gutter="30">
            <el-col :span="6">
              <el-form-item label="住院号(admission number)" prop="caseNumber">
                <el-input v-model="generalInfo.caseNumber" :disabled="isDisabled" placeholder="请输入"></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="6">
              <el-form-item label="患者姓名(patient name)" prop="patientName">
                <el-input v-model="generalInfo.patientName" placeholder="请输入"></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="6">
              <el-form-item label="患者年龄(patient age)" prop="age">
                <el-input v-model="generalInfo.age" placeholder="请输入"></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="6">
              <el-form-item label="BMI(bmi)" prop="bmi">
                <el-input v-model="generalInfo.bmi" placeholder="请输入"></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="6">
              <el-form-item label="NRS2002" prop="nrs">
                <el-select v-model="generalInfo.nrs" placeholder="请选择nrs分级">
                  <el-option label="一级(I)" value=1></el-option>
                  <el-option label="二级(II)" value=2></el-option>
                  <el-option label="三级(III)" value=3></el-option>
                  <el-option label="四级(IV)" value=4></el-option>
                </el-select>
              </el-form-item>
            </el-col>

            <el-col :span="6">
              <el-form-item label="手术部位(surgery site)" prop="surgerySite">
                <el-select v-model="generalInfo.surgerySite">
                  <el-option label="结肠(colon)" value="结肠"></el-option>
                </el-select>
              </el-form-item>
            </el-col>

            <el-col :span="6">
              <el-form-item label="手术名称(surgery name)" prop="surgeryName">
                <el-select v-model="generalInfo.surgeryName" placeholder="请选择手术名称">
                  <el-option label="结肠切除术(colectomy)" value="结肠切除术"></el-option>
                  <el-option label="其他手术(other surgery)" value="其他手术"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            
            <el-col :span="6">
              <el-form-item label="NYHA分级(NYHA classification)" prop="nyhaGrade">
                <el-select v-model="generalInfo.nyhaGrade">
                  <el-option label="I" value="I"></el-option>
                  <el-option label="II" value="II"></el-option>
                  <el-option label="III" value="III"></el-option>
                </el-select>
              </el-form-item>
            </el-col>

            <el-col :span="6">
              <el-form-item label="ASA分级(ASA classification)" prop="asaGrade">
                <el-select v-model="generalInfo.asaGrade">
                  <el-option label="第一级(I)" value="第一级"></el-option>
                  <el-option label="第二级(II)" value="第二级"></el-option>
                  <el-option label="第三级(III)" value="第三级"></el-option>
                  <el-option label="第四级(IV)" value="第四级"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="高压(high pressure)" prop="highPressure">
                <el-input v-model="generalInfo.highPressure" placeholder="请输入"></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="6">
              <el-form-item label="低压(low pressure)" prop="lowPressure">
                <el-input v-model="generalInfo.lowPressure" placeholder="请输入"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="6">
              <el-form-item label="患者性别(patient gender)" prop="gender">
                <el-radio-group v-model="generalInfo.gender">
                  <el-radio :label="'男'">男(male)</el-radio>
                  <el-radio :label="'女'">女(female)</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
            <el-col :span="5">
              <el-form-item label="饮酒(drinking)" prop="drinking">
                <el-radio-group v-model="generalInfo.drinking">
                  <el-radio :label="'是'">是(yes)</el-radio>
                  <el-radio :label="'否'">否(no)</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
            <el-col :span="5">
              <el-form-item label="吸烟(smoking)" prop="smoking">
                <el-radio-group v-model="generalInfo.smoking">
                  <el-radio :label="'是'">是(yes)</el-radio>
                  <el-radio :label="'否'">否(no)</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="高血压是否需要药物治疗(hypertension treatment)" prop="hypertensionTreatment">
                <el-radio-group v-model="generalInfo.hypertensionTreatment">
                  <el-radio :label="'是'">是(yes)</el-radio>
                  <el-radio :label="'否'">否(no)</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
            

          </el-row>
        </el-form>
        <el-form ref="labTests" :model="labTests" :rules="rules1" label-position="top">
          <!-- 实验室检查 -->
          <h3>实验室检查(Laboratory Tests)</h3>
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="白蛋白(albumin) g/L" prop="albumin">
                <el-input v-model="labTests.albumin" placeholder="请输入"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="D-二聚体(D-dimer) ng/ml" prop="dDimer">
                <el-input v-model="labTests.dDimer" placeholder="请输入"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="APTT(aptt) sec" prop="aptt">
                <el-input v-model="labTests.aptt" placeholder="请输入"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="PTsec(ptsec) sec" prop="ptsec">
                <el-input v-model="labTests.ptsec" placeholder="请输入"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="前白蛋白(pre-albumin) mg/L" prop="preAlbumin">
                <el-input v-model="labTests.preAlbumin" placeholder="请输入"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="血红蛋白(hemoglobin) g/L" prop="hemoglobin">
                <el-input v-model="labTests.hemoglobin" placeholder="请输入"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="总胆红素(total bilirubin) umol/L" prop="totalBilirubin">
                <el-input v-model="labTests.totalBilirubin" placeholder="请输入"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="直接胆红素(direct bilirubin) umol/L" prop="directBilirubin">
                <el-input v-model="labTests.directBilirubin" placeholder="请输入"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="白细胞(leukocyte) 10^9/L" prop="leukocyte">
                <el-input v-model="labTests.leukocyte" placeholder="请输入"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="电解质钾(electrolyte potassium) mmol/L" prop="electrolytePotassium">
                <el-input v-model="labTests.electrolytePotassium" placeholder="请输入"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="空腹血糖值(blood sugar) mmol/L" prop="bloodSugar">
                <el-input v-model="labTests.bloodSugar" placeholder="请输入"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="甘油三酯(triglyceride) mmol/L" prop="triglyceride">
                <el-input v-model="labTests.triglyceride" placeholder="请输入"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="间接胆黄素(indirect bilirubin)" prop="Indirectbilirubin">
                <el-input v-model="labTests.Indirectbilirubin" placeholder="请输入"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="抗凝血酶Ⅲ(antithrombinⅢ) g/L" prop="antithrombin">
                <el-input v-model="labTests.antithrombin" placeholder="请输入"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="尿素氮(blood urea nitrogen) mmmol/L" prop="bloodUreaNitrogen">
                <el-input v-model="labTests.bloodUreaNitrogen" placeholder="请输入"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="肾功能_Cr(renal Cr) umol/L" prop="RenalCr">
                <el-input v-model="labTests.RenalCr" placeholder="请输入"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="肝功能_ALT(ALT) U/L" prop="ALT">
                <el-input v-model="labTests.ALT" placeholder="请输入"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="肝功能_AST(AST) U/L" prop="AST">
                <el-input v-model="labTests.AST" placeholder="请输入"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="红细胞(red blood cell) 10^12/L" prop="red">
                <el-input v-model="labTests.red" placeholder="请输入"></el-input>
              </el-form-item>
            </el-col>


            <el-col :span="8">
              <el-form-item label="血小板(platelet) 10^9/L" prop="platelet">
                <el-input v-model="labTests.platelet" placeholder="请输入"></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item label="癌胚抗原_CEA(carcinoembryonic antigen) ng/mL" prop="carcinoembryonic">
                <el-input v-model="labTests.carcinoembryonic" placeholder="请输入"></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item label="血肌酐(blood creatinine) μmol/L" prop="bloodcreatinine">
                <el-input v-model="labTests.bloodcreatinine" placeholder="请输入"></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item label="甲状腺激素(thyroid hormone) (μIU/mL" prop="thyroid">
                <el-input v-model="labTests.thyroid" placeholder="请输入"></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item label="血脂(blood lipid) mmol/L" prop="bloodlipid">
                <el-input v-model="labTests.bloodlipid" placeholder="请输入"></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item label="C反应蛋白_CRP(C-reactive protein) mg/L" prop="reactiveprotein">
                <el-input v-model="labTests.reactiveprotein" placeholder="请输入"></el-input>
              </el-form-item>
            </el-col>


            <el-col :span="8">
              <el-form-item label="粪便隐血试验(fecal occult blood test)" prop="bloodtest">
                <el-input v-model="labTests.bloodtest" placeholder="请输入"></el-input>
              </el-form-item>
            </el-col>


            <el-col :span="8">
              <el-form-item label="甲胎蛋白_AFP(alpha-fetoprotein) μg/L" prop="alphafetoprotein">
                <el-input v-model="labTests.alphafetoprotein" placeholder="请输入"></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row :gutter="20">
            <el-col :span="6">
              <el-form-item label="心电图-房早" prop="ecgAtrialPremature">
                <el-select v-model="labTests.ecgAtrialPremature" placeholder="请选择">
                  <el-option label="无" :value="null"></el-option>
                  <el-option label="是" value="是"></el-option>
                  <el-option label="否" value="否"></el-option>
                </el-select>
              </el-form-item>
            </el-col>

            <el-col :span="6">
              <el-form-item label="心电图-室早" prop="ecgVentricularPremature">
                <el-select v-model="labTests.ecgVentricularPremature" placeholder="请选择">
                  <el-option label="无" :value="null"></el-option>
                  <el-option label="是" value="是"></el-option>
                  <el-option label="否" value="否"></el-option>
                </el-select>
              </el-form-item>
            </el-col>

            <el-col :span="6">
              <el-form-item label="心电图-房颤" prop="ecgAtrialFibrillation">
                <el-select v-model="labTests.ecgAtrialFibrillation" placeholder="请选择">
                  <el-option label="无" :value="null"></el-option>
                  <el-option label="是" value="是"></el-option>
                  <el-option label="否" value="否"></el-option>
                </el-select>
              </el-form-item>
            </el-col>

            <el-col :span="6">
              <el-form-item label="心电图-窦缓" prop="ecgBradycardia">
                <el-select v-model="labTests.ecgBradycardia" placeholder="请选择">
                  <el-option label="无" :value="null"></el-option>
                  <el-option label="是" value="是"></el-option>
                  <el-option label="否" value="否"></el-option>
                </el-select>
              </el-form-item>
            </el-col>

            <el-col :span="6">
              <el-form-item label="心电图-传导阻滞" prop="ecgConductionBlock">
                <el-select v-model="labTests.ecgConductionBlock" placeholder="请选择">
                  <el-option label="无" :value="null"></el-option>
                  <el-option label="是" value="是"></el-option>
                  <el-option label="否" value="否"></el-option>
                </el-select>
              </el-form-item>
            </el-col>

            <!-- 心超部分 -->
            <el-col :span="6">
              <el-form-item label="心超-LVEF (%)" prop="echoLVEF">
                <el-input v-model="labTests.echoLVEF" placeholder="请输入数值" type="number"></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="6">
              <el-form-item label="心超-PASP (mmHg)" prop="echoPASP">
                <el-input v-model="labTests.echoPASP" placeholder="请输入数值" type="number"></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="6">
              <el-form-item label="心超检验-BNP (pg/mL)" prop="echoBNP">
                <el-input v-model="labTests.echoBNP" placeholder="请输入数值" type="number"></el-input>
              </el-form-item>
            </el-col>

            <!-- 冠脉部分 -->
            <el-col :span="6">
              <el-form-item label="冠脉CTA" prop="coronaryCTA">
                <el-select v-model="labTests.coronaryCTA" placeholder="请选择">
                  <el-option label="无" :value="null"></el-option>
                  <el-option label="正常" value="正常"></el-option>
                  <el-option label="轻度狭窄" value="轻度狭窄"></el-option>
                  <el-option label="中度狭窄" value="中度狭窄"></el-option>
                  <el-option label="重度狭窄" value="重度狭窄"></el-option>
                  <el-option label="三支病变" value="三支病变"></el-option>
                </el-select>
              </el-form-item>
            </el-col>

            <el-col :span="6">
              <el-form-item label="冠脉检验-高敏肌钙蛋白 (ng/L)" prop="coronaryTroponin">
                <el-input v-model="labTests.coronaryTroponin" placeholder="请输入数值" type="number"></el-input>
              </el-form-item>
            </el-col>

            <!-- 颅脑CT -->
            <el-col :span="6">
              <el-form-item label="颅脑CT" prop="brainCT">
                <el-select v-model="labTests.brainCT" placeholder="请选择">
                  <el-option label="无" :value="null"></el-option>
                  <el-option label="正常" value="正常"></el-option>
                  <el-option label="脑软化灶" value="脑软化灶"></el-option>
                  <el-option label="其他异常" value="其他异常"></el-option>
                </el-select>
              </el-form-item>
            </el-col>



            <el-col :span="6">
              <el-form-item label="糖化血红蛋白 (%)" prop="hba1c">
                <el-input v-model="labTests.hba1c" placeholder="请输入数值" type="number"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
        <el-form ref="auxiliaryExams" :model="auxiliaryExams" :rules="rules2">
          <!-- 辅助检查 -->
          <h3>辅助检查(Auxiliary Examination)</h3>
          <el-row :gutter="20">
            <el-col :span="10">
              <el-form-item label="胸部CT 肺部炎症(Chest CT pulmonary inflammation)" prop="chestCT"><br/>
                <el-select v-model="auxiliaryExams.chestCT" placeholder="请选择">
                  <el-option label="否(No)" value="否"></el-option>
                  <el-option label="轻微(Mild)" value="轻微"></el-option>
                  <el-option label="轻度(Low-grade)" value="轻度"></el-option>
                  <el-option label="中度(Severe)" value="中度"></el-option>
                  <el-option label="偏重(Moderately Severe)" value="偏重"></el-option>
                  <el-option label="重度(Severe)" value="重度"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="14">
              <el-form-item label="心脏超声 左室舒张功能减低(Heart ultrasound left ventricular diastolic dysfunction)" prop="heartEcho">
                <el-select v-model="auxiliaryExams.heartEcho" placeholder="请选择">
                  <el-option label="否(No)" value="否"></el-option>
                  <el-option label="轻微(Mild)" value="轻微"></el-option>
                  <el-option label="轻度(Low-grade)" value="轻度"></el-option>
                  <el-option label="中度(​Moderate)" value="中度"></el-option>
                  <el-option label="偏重(Moderately Severe)" value="偏重"></el-option>
                  <el-option label="重度(Severe)" value="重度"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="10">
              <el-form-item label="呼吸功能 严重COPD病史(Severe COPD history)" prop="historyCOPD"><br/>
                <el-select v-model="auxiliaryExams.historyCOPD" placeholder="请选择">
                  <el-option label="否(No)" value="否"></el-option>
                  <el-option label="轻微(Mild)" value="轻微"></el-option>
                  <el-option label="轻度(Low-grade)" value="轻度"></el-option>
                  <el-option label="中度(​Moderate)" value="中度"></el-option>
                  <el-option label="偏重(Moderately Severe)" value="偏重"></el-option>
                  <el-option label="重度(Severe)" value="重度"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="14">
              <el-form-item label="手术前30天的充血性心力衰竭(Congestive heart failure within 30 days before surgery)" prop="Congestiveheartfailure">
                <el-select v-model="auxiliaryExams.Congestiveheartfailure" placeholder="请选择">
                  <el-option label="否(No)" value="否"></el-option>
                  <el-option label="轻微(Mild)" value="轻微"></el-option>
                  <el-option label="轻度(Low-grade)" value="轻度"></el-option>
                  <el-option label="中度(​Moderate)" value="中度"></el-option>
                  <el-option label="偏重(Moderately Severe)" value="偏重"></el-option>
                  <el-option label="重度(Severe)" value="重度"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="10">
              <el-form-item label="肾功能不全 是否透析(Renal insufficiency whether dialysis)" prop="Dialysis"><br/>
                <el-select v-model="auxiliaryExams.Dialysis" placeholder="请选择">
                  <el-option label="否(No)" value="否"></el-option>
                  <el-option label="轻微(Mild)" value="轻微"></el-option>
                  <el-option label="轻度(Low-grade)" value="轻度"></el-option>
                  <el-option label="中度(​Moderate)" value="中度"></el-option>
                  <el-option label="偏重(Moderately Severe)" value="偏重"></el-option>
                  <el-option label="重度(Severe)" value="重度"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="肾功能不全 急性肾功能衰竭(Renal insufficiency Acute renal failure)" prop="AcuteRenalFailure"><br/>
                <el-select v-model="auxiliaryExams.AcuteRenalFailure" placeholder="请选择">
                  <el-option label="否(No)" value="否"></el-option>
                  <el-option label="轻微(Mild)" value="轻微"></el-option>
                  <el-option label="轻度(Low-grade)" value="轻度"></el-option>
                  <el-option label="中度(​Moderate)" value="中度"></el-option>
                  <el-option label="偏重(Moderately Severe)" value="偏重"></el-option>
                  <el-option label="重度(Severe)" value="重度"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="10">
              <el-form-item label="呼吸困难(Respiratory difficulties)" prop="respiratoryDifficulties"><br/>
                <el-select v-model="auxiliaryExams.respiratoryDifficulties" placeholder="请选择">
                  <el-option label="否(No)" value="否"></el-option>
                  <el-option label="轻微(Mild)" value="轻微"></el-option>
                  <el-option label="轻度(Low-grade)" value="轻度"></el-option>
                  <el-option label="中度(​Moderate)" value="中度"></el-option>
                  <el-option label="偏重(Moderately Severe)" value="偏重"></el-option>
                  <el-option label="重度(Severe)" value="重度"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="手术前48小时内出现全身性败血症(Sepsis within 48 hours before surgery)" prop="sepsisWithin48Hours">
                <el-select v-model="auxiliaryExams.sepsisWithin48Hours" placeholder="请选择">
                  <el-option label="否(No)" value="否"></el-option>
                  <el-option label="轻微(Mild)" value="轻微"></el-option>
                  <el-option label="轻度(Low-grade)" value="轻度"></el-option>
                  <el-option label="中度(​Moderate)" value="中度"></el-option>
                  <el-option label="偏重(Moderately Severe)" value="偏重"></el-option>
                  <el-option label="重度(Severe)" value="重度"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>

        <el-form ref="specialForm" :model="specialInfo">
          <h3>特殊补充(Special Supplement)</h3>
          <el-form-item label="特殊补充信息(Special supplementary information)" prop="supplement">
            <el-input 
              type="textarea" 
              v-model="specialInfo.supplement" 
              :rows="6" 
              placeholder="请输入任何需要补充的特殊情况或备注信息(Please enter any special circumstances or notes that need to be supplemented)">
            </el-input>
          </el-form-item>
        </el-form>
        <el-input v-model="generalInfo.department" style="width: 400px" placeholder="请输入录入部门"></el-input>
        <el-input v-model="generalInfo.staff" style="width: 400px;margin-left: 20px" placeholder="请输入录入人姓名"></el-input>
        <el-button type="primary" @click="handle()" style="display: block; margin: 50px auto;">风险评估(Risk Assessment)</el-button>
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
        department:'',
        staff:'',
        caseNumber: '',
        patientName: '',
        age: '',
        diagnosis: "结肠恶性肿瘤",
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
        albumin: '',//白蛋白
        preAlbumin: '',//前白蛋白
        bloodSugar: '',//空腹血糖值
        dDimer: '',//D-二聚体
        aptt: '',//APTT
        ptsec: '',//PTsec
        totalBilirubin: '',//总胆红素
        directBilirubin: '',//直接胆红素
        electrolytePotassium: '',//电解质钾
        bloodUreaNitrogen: '',//尿素氮
        hemoglobin: '',//血红蛋白
        leukocyte: '',//白细胞
        triglyceride: '',//甘油三酯
        Indirectbilirubin: '',//间接胆黄素
        antithrombin: '',//抗凝血酶Ⅲ
        RenalCr: '',//肾功能_Cr
        ALT: '',//肝功能__ALT
        AST: '',//肝功能_AST
        ecgAtrialPremature: '',//1
        ecgVentricularPremature: '',//1
        ecgAtrialFibrillation: '',//1
        ecgBradycardia: '',//1
        ecgConductionBlock: '',//1
        echoLVEF: '',//1
        echoPASP: '',//1
        echoBNP: '',//1
        coronaryCTA: '',//冠脉CTA
        coronaryTroponin: '',//冠脉检验-高敏肌钙蛋白
        brainCT: '',//颅脑CT
        hba1c: '',//糖化血红蛋白



        //结肠直肠共有的额外指标
        red:'',//红细胞
        platelet:'',//血小板
        carcinoembryonic:'',//癌胚抗原
        bloodcreatinine:'',//血肌酐
        thyroid:'',//甲状腺激素
        bloodlipid:'',//血脂
        alphafetoprotein:'',//甲胎蛋白_AFP

        //结肠单独的指标
        reactiveprotein:'',//C反应蛋白_CRP
        bloodtest:'',//粪便隐血试验


        //直肠单独的指标(设置为空)
        CA125:'',//ca125
        antigen19:'',//19-9
        viral:'',//病毒性肝炎标志物
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
        specialSupplement: '',
      },
      specialInfo: {
        supplement: ''
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
          { required: true, message: '请输入红细胞', trigger: 'blur' }
        ],
        dDimer: [
          { required: true, message: '请输入血红蛋白', trigger: 'blur' }
        ],
        aptt: [
          { required: true, message: '请输入白细胞', trigger: 'blur' }
        ],
        ptsec: [
          { required: true, message: '请输入血小板', trigger: 'blur' }
        ],
        preAlbumin: [
          { required: true, message: '请输入凝血酶原时间_PT', trigger: 'blur' }
        ],
        hemoglobin: [
          { required: true, message: '请输入活化部分凝血活酶时间_APTT', trigger: 'blur' }
        ],
        totalBilirubin: [
          { required: true, message: '请输入空腹血糖值', trigger: 'blur' }
        ],
        directBilirubin: [
          { required: true, message: '请输入电解质钾', trigger: 'blur' }
        ],
        leukocyte: [
          { required: true, message: '请输入C反应蛋白_CRP', trigger: 'blur' }
        ],
        electrolytePotassium: [
          { required: true, message: '请输入癌胚抗原_CEA', trigger: 'blur' }
        ],
        bloodSugar: [
          { required: true, message: '请输入癌胚抗原_CEA', trigger: 'blur' }
        ],
        triglyceride: [
          { required: true, message: '请输入粪便隐血试验', trigger: 'blur' }
        ],
        Indirectbilirubin: [
          { required: true, message: '请输入甲状腺激素', trigger: 'blur' }
        ],
        antithrombin: [
          { required: true, message: '请输入血脂', trigger: 'blur' }
        ],
        bloodUreaNitrogen: [
          { required: true, message: '请输入血肌酐', trigger: 'blur' }
        ],
        RenalCr: [
          { required: true, message: '请输入尿素氮', trigger: 'blur' }
        ],
        ALT: [
          { required: true, message: '请输入丙氨酸转氨酶_ALT', trigger: 'blur' }
        ],
        AST: [
          { required: true, message: '请输入天门冬氨酸转氨酶_AST', trigger: 'blur' }
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

    if (this.$route.query.generalInfo) {
      const param = JSON.parse(this.$route.query.generalInfo);
      // console.log(param);
      this.generalInfo = param;
    }
    if (this.$route.query.labTests) {
      const param = JSON.parse(this.$route.query.labTests);
      this.labTests = param;
    }
    if (this.$route.query.auxiliaryExams) {
      const param = JSON.parse(this.$route.query.auxiliaryExams);
      this.auxiliaryExams = param;
    }
    if (this.$route.query.specialInfo) {
      const param = JSON.parse(this.$route.query.specialInfo);
      this.specialInfo = param;
    }
  },
  mounted() {
    this.generalInfo.staff=localStorage.getItem("staff");
    this.generalInfo.department=localStorage.getItem("department");
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
            this.generalInfo.nrs = response.data.data.physicalStatusNrs2002Score,

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

              // 心电图和影像检查
              this.labTests.ecgAtrialPremature = response.data.data.ecgAtrialPremature;       // 心电图-房早
          this.labTests.ecgVentricularPremature = response.data.data.ecgVentricularPremature; // 心电图-室早
          this.labTests.ecgAtrialFibrillation = response.data.data.ecgAtrialFibrillation;   // 心电图-房颤
          this.labTests.ecgBradycardia = response.data.data.ecgBradycardia;               // 心电图-窦缓
          this.labTests.ecgConductionBlock = response.data.data.ecgConductionBlock;         // 心电图-传导阻滞
          this.labTests.echoLVEF = response.data.data.echoLVEF;                         // 心超-LVEF (%)
          this.labTests.echoPASP = response.data.data.echoPASP;                         // 心超-PASP (mmHg)
          this.labTests.echoBNP = response.data.data.echoBNP;                           // 心超检验-BNP (pg/mL)
          this.labTests.coronaryCTA = response.data.data.coronaryCTA;                     // 冠脉CTA
          this.labTests.coronaryTroponin = response.data.data.coronaryTroponin;             // 冠脉检验-高敏肌钙蛋白
          this.labTests.brainCT = response.data.data.brainCT;                           // 颅脑CT
          this.labTests.hba1c = response.data.data.hba1c;                               // 糖化血红蛋白 (%)

// 结肠直肠共有的额外指标
          this.labTests.red = response.data.data.red;                                   // 红细胞
          this.labTests.platelet = response.data.data.platelet;                         // 血小板
          this.labTests.carcinoembryonic = response.data.data.carcinoembryonic;             // 癌胚抗原
          this.labTests.thyroid = response.data.data.thyroid;                           // 甲状腺激素
          this.labTests.bloodlipid = response.data.data.bloodlipid;                       // 血脂
          this.labTests.bloodcreatinine = response.data.data.bloodcreatinine;               // 血肌酐

// 直肠单独的指标
          this.labTests.CA125 = response.data.data.CA125;                               // CA125
          this.labTests.antigen19 = response.data.data.antigen19;                         // 19-9
          this.labTests.AFP = response.data.data.AFP;                                   // AFP
          this.labTests.viral = response.data.data.viral;                                 // 病毒性肝炎标志物

// 结肠单独的指标
          this.labTests.reactiveprotein = response.data.data.reactiveprotein;               // C反应蛋白_CRP
          this.labTests.bloodtest = response.data.data.bloodtest;                         // 粪便隐血试验
          this.labTests.alphafetoprotein = response.data.data.alphafetoprotein;               // 甲胎蛋白_AFP

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
            
            // 加载特殊补充信息
            this.specialInfo = {
              supplement: response.data.data.specialSupplement || ''
            };

        })
        .catch(error => {
        });

    },
    handle() {
      localStorage.setItem("staff",this.generalInfo.staff);
      localStorage.setItem("department",this.generalInfo.department);
      console.log(this.labTests.alphafetoprotein);
      console.log(this.labTests.CA125);
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
        department:this.generalInfo.department,
        staff:this.generalInfo.staff,
        hospitalizationNumber: this.generalInfo.caseNumber,
        patientName: this.generalInfo.patientName,
        patientAge: this.generalInfo.age,
        patientAdmissionDiagnosis: this.generalInfo.diagnosis,
        patientBmi: this.generalInfo.bmi,
        cardiacFunctionNyhaClass: this.generalInfo.nyhaGrade,
        physicalStatusAsaClass: this.generalInfo.asaGrade,
        physicalStatusNrs2002Score: this.generalInfo.nrs,
        primarySurgicalSite: this.generalInfo.surgerySite,
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
        ecgAtrialPremature: this.labTests.ecgAtrialPremature,
        ecgVentricularPremature: this.labTests.ecgVentricularPremature,
        ecgAtrialFibrillation: this.labTests.ecgAtrialFibrillation,
        ecgBradycardia: this.labTests.ecgBradycardia,
        ecgConductionBlock: this.labTests.ecgConductionBlock,
        echoLVEF: this.labTests.echoLVEF,
        echoPASP: this.labTests.echoPASP,
        echoBNP: this.labTests.echoBNP,
        coronaryCTA: this.labTests.coronaryCTA,
        coronaryTroponin: this.labTests.coronaryTroponin,
        brainCT: this.labTests.brainCT,
        hba1c: this.labTests.hba1c,



        // 结肠直肠共有的额外指标
        red: this.labTests.red,               // 红细胞(red blood cell) 10^12/L
        platelet: this.labTests.platelet,     // 血小板(platelet) 10^9/L
        carcinoembryonic: this.labTests.carcinoembryonic,  // 癌胚抗原_CEA(carcinoembryonic antigen) μg/L
        bloodcreatinine: this.labTests.bloodcreatinine,    // 血肌酐(blood creatinine) μmol/L
        thyroid: this.labTests.thyroid,       // 甲状腺激素(thyroid hormone) μIU/mL
        bloodlipid: this.labTests.bloodlipid, // 血脂(blood lipid) mmol/L

// 结肠单独的指标
        reactiveprotein: this.labTests.reactiveprotein,  // C反应蛋白_CRP(C-reactive protein) mg/L
        bloodtest: this.labTests.bloodtest,             // 粪便隐血试验(fecal occult blood test)
        alphafetoprotein: this.labTests.alphafetoprotein, // 甲胎蛋白_AFP(alpha-fetoprotein) μg/L

// 直肠单独的指标(设置为空)
        ca: this.labTests.CA125,           // CA125(cancer antigen 125) U/mL
        antigen19: this.labTests.antigen19,   // 糖类抗原19-9_CA19-9(carbohydrate antigen 19-9) U/mL
        AFP: this.labTests.AFP,               // 甲胎蛋白_AFP(alpha-fetoprotein) μg/L
        viral: this.labTests.viral ,           // 病毒性肝炎标志物(viral hepatitis markers) IU/ml









        respiratorySevereCopdHistory: this.auxiliaryExams.historyCOPD,
        preoperativeChfWithin30Days: this.auxiliaryExams.Congestiveheartfailure,
        kidneyFunctionDialysis: this.auxiliaryExams.Dialysis,
        kidneyFunctionAcuteFailure: this.auxiliaryExams.AcuteRenalFailure,
        chestCtLungInflammation: this.auxiliaryExams.chestCT,
        cardiacEchoLeftVentricularDysfunction: this.auxiliaryExams.heartEcho,
        preoperativeSepsisWithin48Hours: this.auxiliaryExams.sepsisWithin48Hours,
        respiratoryDifficulty: this.auxiliaryExams.respiratoryDifficulties,
        specialSupplement: this.specialInfo.supplement,
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
  background: linear-gradient(180deg, #1A3875 0%, #1A3875 200px, #f5f7fa 200px, #f5f7fa 100%);
}

.page-title {
  color: #ffffff;
  font-size: 24px;
  font-weight: bold;
  text-align: center;
  padding: 30px 0;
  margin: 0;
}

.form-container {
  width: 95%;
  max-width: 1200px;
  height: auto;
  overflow: auto;
  margin: 0 auto;
  padding: 30px;
  background-color: rgba(255, 255, 255, 0.95);
  border-radius: 8px;
  border: 1px solid #dcdfe6;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.form-section {
  margin-bottom: 30px;
  padding: 20px;
  background-color: #f8f9fa;
  border-radius: 6px;
}

h3 {
  color: #333333;
  font-size: 20px;
  font-weight: bold;
  text-align: left;
  margin-bottom: 25px;
  position: relative;
  padding-left: 20px;
}

h3::before {
  content: '';
  position: absolute;
  left: -30px;
  top: 0;
  width: 35px;
  height: 100%;
  background-color: rgb(34,67,148);
}

.el-form-item {
  margin-bottom: 22px;
}

.el-form-item__label {
  color: #1A3875;
  font-weight: 500;
}

.el-input__inner {
  border: 1px solid #dcdfe6;
  border-radius: 4px;
  &:focus {
    border-color: #1A3875;
  }
}

.el-select .el-input__inner {
  &:focus {
    border-color: #1A3875;
  }
}

.el-radio__input.is-checked .el-radio__inner {
  border-color: rgb(34,67,148);
  background: rgb(34,67,148);
}

.el-radio__input.is-checked + .el-radio__label {
  color: rgb(34,67,148);
}

.el-radio__inner:hover {
  border-color: rgb(34,67,148);
}

.el-button--primary {
  background-color: #1A3875;
  border-color: #1A3875;
  padding: 12px 30px;
  font-size: 16px;
  border-radius: 4px;
  margin-top: 30px;
  
  &:hover {
    background-color: #2C5530;
    border-color: #2C5530;
  }
}

.el-row {
  margin-bottom: 15px;
}

.el-col {
  padding: 0 10px;
}

.block-container {
  height: 20px;
}

.form-container0 {
  width: 90%;
  height: 90%;
  overflow: auto;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #dcdfe6;
  border-radius: 5px;
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: rgb(255, 255, 255);
}
</style>