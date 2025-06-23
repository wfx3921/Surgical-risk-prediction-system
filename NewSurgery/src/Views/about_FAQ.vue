<template>
  <div>
    <div class="form-container">
      <div>
        <h3 style="text-align: center;font-size: 24px;">风险计算器常见问题解答 (TAHQU-PRPS Risk Calculator FAQ)</h3>
        <div style="height: 20px;"></div>

        <p><strong>1) 谁应该使用 TAHQU-PRPS 风险计算器？这只是给外科医生用的吗？<br>
          (Who should use the TAHQU-PRPS Risk Calculator? Is this just for surgeons?)</strong></p>
        <p>TAHQU-PRPS 风险计算器旨在供外科医生使用，并与其患者一起帮助患者了解他们的个体手术风险。TAHQU-PRPS 风险计算器分为三个部分，可能难以理解医生以外的内容。该计算器提供了一个患者部分，使他们可以与家人、其他医生和他们所涉及的其他个人分享。</p>
        <p>The TAHQU-PRPS Risk Calculator is designed for use by surgeons with their patients to help understand individual surgical risks. It has three sections that may be difficult for non-physicians to comprehend. It includes a patient-facing portion for sharing with families and other care providers.</p>

        <p><strong>2) TAHQU-PRPS 风险计算器如何工作？<br>
          (How does the TAHQU-PRPS Risk Calculator work?)</strong></p>
        <p>使用 TAHQU-PRPS 风险计算器，可以快速在线输入患者的20个特征（如手术类型、年龄、性别、健康信息等）。这些因素被放入统计模型中以预测患者的具体结果。该模型基于来自超过430万手术的数据进行构建，预测手术后30天内发生18种不同并发症的风险。还显示了预计的住院时间。有关更多详细信息，请参阅：<a href="#">TAHQU-PRPS 风险计算器出版物</a>。</p>
        <p>The calculator requires input of 20 patient characteristics (surgery type, age, gender, health metrics, etc.). These feed into a statistical model predicting 30-day postoperative risks for 18 complications and estimated hospital stay. The model was developed using data from over 4.3 million procedures. See our <a href="#">publication</a> for details.</p>

        <p><strong>3) 如果我不知道某个患者预测因子的答案怎么办？<br>
          (What if I don't know an answer for a patient predictor?)</strong></p>
        <p>TAHQU-PRPS 风险计算器会在输入所有预测因子信息时提供最精确的风险信息。但是，如果您不知道某些领域的答案，可以在默认设置中输入（通常为"否"）。模型将根据可获得的信息估算风险。</p>
        <p>While complete information yields the most accurate predictions, you may use default values (typically "No") for unknown fields. The model will estimate risks based on available data.</p>

        <p><strong>4) 为什么 TAHQU-PRPS 风险计算器仅提供手术后30天内的风险预测？<br>
          (Why does it only predict 30-day postoperative risks?)</strong></p>
        <p>计算器使用的模型是基于参与 TAHQU-PRPS的医院收集的来自并发症的数据构建的。TAHQU-PRPS项目仅收集手术后30天内的并发症数据。</p>
        <p>The model uses complication data collected by TAHQU-PRPS participating hospitals, which only tracks 30-day postoperative outcomes.</p>

        <p><strong>5) 如果患者有重要的风险因素但没有录入 TAHQU-PRPS 风险计算器怎么办？<br>
          (What if a patient has important risk factors not captured by the calculator?)</strong></p>
        <p>外科医生可以使用阴影区域的"风险调整"按钮调整预测风险。这应该在外科医生认为患者的风险因素尚未被预测因子充分捕获时使用。这种调整通过将每个结果的预测风险增加1到2个标准偏差来工作。</p>
        <p>Surgeons may use the "Risk Adjustment" button to increase predicted risks by 1-2 standard deviations when important factors aren't fully captured by the model.</p>

        <p><strong>6) 为什么并不是所有的 TAHQU-PRPS 变量都被用于风险计算器？<br>
          (Why aren't all TAHQU-PRPS variables used in the calculator?)</strong></p>
        <p>通用风险计算器不使用TAHQU-PRPS收集的每个变量来预测并发症。在某些情况下，不同变量对风险的贡献可能会有所重叠，故此选择的预测因子可能最能说明手术后的风险。</p>
        <p>The calculator uses the most predictive variables, avoiding redundancy from correlated factors that might overlap in risk contribution.</p>

        <p><strong>7) 通用风险计算器与其他 TAHQU-PRPS 风险计算器相比如何？<br>
          (How does this compare to other TAHQU-PRPS calculators?)</strong></p>
        <p>目前的通用风险计算器可以在所有手术类型中通用，而以前开发的TAHQU-PRPS基于风险计算器只能用于一组手术。通用风险计算器公开发行，并允许用户将输入与个人特定风险模型进行比较。</p>
        <p>This universal calculator works across all surgery types, unlike previous procedure-specific versions. It's publicly available and allows comparison with specialty-specific models.</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'RiskCalculatorFAQ'
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

p {
  margin-bottom: 15px;
}

strong {
  font-weight: 600;
}

a {
  color: #409EFF;
  text-decoration: none;
}

a:hover {
  text-decoration: underline;
}
</style>