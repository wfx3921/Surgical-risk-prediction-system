<template>
  <div>
    <div class="form-container">
      <div>
        <h3 style="text-align: center;font-size: 24px;">关于术前风险预测器 (About Preoperative Risk Predictor)</h3>
        <div style="height: 20px;"></div>

        <!-- 目的/Purpose -->
        <strong>目的 (Purpose)</strong>
        <p>
          术前风险预测器旨在为医生和患者提供个性化的手术风险评估，以辅助手术决策和知情同意过程。通过提供准确的患者特异性风险信息，该工具帮助医生与患者共同制定最佳的治疗方案，并确保患者充分了解可能面临的风险。
        </p>
        <p>
          This tool provides personalized surgical risk assessments to support clinical decision-making and informed consent process. It helps doctors and patients develop optimal treatment plans through accurate risk evaluation.
        </p>

        <!-- 功能/Features -->
        <strong>功能 (Features)</strong>
        <ul>
          <li>
            <strong>患者信息收集 (Patient Data Collection)</strong><br>
            该预测器要求输入患者的20项关键信息，包括但不限于年龄、性别、体重指数（BMI）、高血压状态等。<br>
            Collects 20+ key parameters including age, gender, BMI, hypertension status, etc.
          </li>
          <li>
            <strong>手术类型选择 (Surgery Type Selection)</strong><br>
            用户需指定计划进行的具体手术类型（可通过通用程序术语CPT代码识别）。<br>
            Supports procedure specification via CPT codes.
          </li>
          <li>
            <strong>风险预测 (Risk Prediction)</strong><br>
            基于输入的信息，预测器能够估算患者在术后30天内发生一系列特定并发症的可能性。<br>
            Estimates 30-day postoperative risks including complications, readmission, mortality, etc.
          </li>
        </ul>

        <!-- 数据基础/Data Foundation -->
        <strong>数据基础 (Data Foundation)</strong>
        <p>
          术前风险预测器是基于医疗网络中多个医院多年累积的大量手术数据建立的。我们采用了先进的统计分析方法，确保预测模型的准确性和可靠性。<br>
          Developed using multi-hospital surgical data with advanced statistical modeling to ensure accuracy and reliability.
        </p>

        <!-- 使用说明/Instructions -->
        <strong>使用说明 (Instructions)</strong>
        <p>
          为了获得最准确的风险评估，建议尽可能提供完整的患者信息。如果某些信息不可用，预测器仍然能够提供一个大致的风险估计。<br>
          For optimal results, provide complete patient data. The system can still generate estimates with partial information.
        </p>

        <!-- 隐私保护/Privacy Protection -->
        <strong>隐私保护 (Privacy Protection)</strong>
        <p>
          我们承诺保护所有用户的数据安全和隐私。所有输入的信息仅用于风险预测目的。<br>
          We strictly protect user data privacy. All information is used solely for risk prediction.
        </p>

        <!-- 联系我们/Contact Us -->
        <strong>联系我们 (Contact Us)</strong>
        <p>
          如果您有任何疑问或需要技术支持，请联系我们的客服团队。<br>
          For questions or technical support, please contact our customer service team.
        </p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'AboutPage'
}
</script>

<style scoped>
.form-container {
  width: 90%;
  height: 100%;
  overflow: auto;
  margin: 0 auto;
  padding: 20px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0);
  display: flex;
  flex-direction: column;
  align-items: center;
  line-height: 1.6;
}

h3 {
  margin-bottom: 20px;
}

strong {
  font-weight: 600;

}

p, li {
  margin-bottom: 5px;
}

ul {
  padding-left: 20px;
}

li {
  margin-bottom: 12px;
}
</style>