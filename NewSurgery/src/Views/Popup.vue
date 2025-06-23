<template>
  <div class="popup">
    <div class="popup-content">
      <el-row  class="custom-font-size" justify="center" align="middle">
        <span class="custom-font-size" style="display: block">上报患者实际并发症</span>
        <span class="custom-font-size" style="display: block">Report the patient's actual complications</span>
      </el-row>

      <div class="input-container">
        <el-input v-model="formData.actualDays" placeholder="请输入实际住院天数(Please enter the actual hospitalization days)" type="number" min="0" style="width: 600px"></el-input>
      </div>

      <div class="checkbox-group">
        <el-checkbox v-model="formData.ifMyocardialInfarct" :true-label="1" :false-label="0">心肌梗死(Myocardial Infarction)</el-checkbox>
        <el-checkbox v-model="formData.ifRespiratoryFailure" :true-label="1" :false-label="0">呼吸衰竭(Respiratory Failure)</el-checkbox>
        <el-checkbox v-model="formData.ifBrainRelatedComplications" :true-label="1" :false-label="0">脑类并发症(Brain-related Complications)</el-checkbox>
        <el-checkbox v-model="formData.ifPneumonia" :true-label="1" :false-label="0">肺炎(Pneumonia)</el-checkbox>
        <el-checkbox v-model="formData.ifLowerExtremityDeepVeinThrombosis" :true-label="1" :false-label="0">下肢深静脉血栓(Lower Extremity Deep Vein Thrombosis)</el-checkbox>
        <el-checkbox v-model="formData.ifAnastomoticFistula" :true-label="1" :false-label="0">吻合口瘘(Anastomotic Fistula)</el-checkbox>
        <el-checkbox v-model="formData.ifSuperficialIncisionInfection" :true-label="1" :false-label="0">浅表切口感染(Superficial Incision Infection)</el-checkbox>
        <el-checkbox v-model="formData.ifInfectionAroundOrgansWithinTheBodyCavity" :true-label="1" :false-label="0">体腔内器官周围感染(Infection Around Organs Within the Body Cavity)</el-checkbox>
        <el-checkbox v-model="formData.ifUnplannedReoperation" :true-label="1" :false-label="0">非计划再手术(Unplanned Reoperation)</el-checkbox>
        <el-checkbox v-model="formData.ifBleeding" :true-label="1" :false-label="0">出血(Bleeding)</el-checkbox>
        <el-checkbox v-model="formData.ifIntestinalAdhesionsAndObstruction" :true-label="1" :false-label="0">肠粘连和肠梗阻(Intestinal Adhesions and Obstruction)</el-checkbox>
        <el-checkbox v-model="formData.ifDeath" :true-label="1" :false-label="0">死亡(Death)</el-checkbox>
        <el-checkbox v-model="formData.noProblem" :true-label="1" :false-label="0" @change="handleNoProblemChange">无(No Problem)</el-checkbox>
      </div>

      <div class="button-container">
        <el-button type="success" class="close-button" @click="submitData">提交(Submit)</el-button>
        <el-button type="primary" class="close-button" @click="closePopup">关闭(Close)</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import Cookie from 'js-cookie';
export default {
  name: 'Popup',
  data() {
    return {
      formData: {
        ifMyocardialInfarct: 0,
        ifRespiratoryFailure: 0,
        ifBrainRelatedComplications: 0,
        ifPneumonia: 0,
        ifLowerExtremityDeepVeinThrombosis: 0,
        ifAnastomoticFistula: 0,
        ifSuperficialIncisionInfection: 0,
        ifInfectionAroundOrgansWithinTheBodyCavity: 0,
        ifUnplannedReoperation: 0,
        ifBleeding: 0,
        ifIntestinalAdhesionsAndObstruction: 0,
        ifDeath: 0,
        noProblem: 0,
        actualDays: ''
      }
    };
  },
  props: {
    visible: {
      type: Boolean,
      default: false
    },
    userId: {
      type: [String, Number],
      required: true
    }
  },
  methods: {
    closePopup() {
      this.$router.go(-1);
    },
    handleNoProblemChange(val) {
      if (val === 1) {
        // 如果选中"无"，则其他选项都设为0
        Object.keys(this.formData).forEach(key => {
          if (key !== 'noProblem' && key !== 'actualDays') {
            this.formData[key] = 0;
          }
        });
      }
    },
    submitData() {
      const OutputId = Cookie.get("OutputId");
      console.log('OutputDataid:', OutputId);
      // 发送数据到后端
      axios.post('/api/Syndrome/save', {
        id: OutputId,
        ...this.formData
      })
      .then(response => {
        console.log('数据上传成功:', response.data);
        // 成功后直接跳转到查看实际并发症页面
        this.$router.push({
          path: '/complication-details',
          query: {
            OutputId: OutputId
          }
        });
      })
      .catch(error => {
        console.error('数据上传失败:', error);
        this.$message.error('上传失败，请重试');
      });
    }
  }
};
</script>

<style scoped>
.popup {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 20px;
}

.popup-content {
  background: #fff;
  padding: 30px;
  border-radius: 5px;
  width: 80%;

}

.checkbox-group {

  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 15px;
  margin: 20px 0;
}

.input-container {
  margin: 20px 0;
  text-align: center;
}

.button-container {
  display: flex;
  justify-content: center;
  gap: 20px;
  margin-top: 20px;
}

.custom-font-size {
  font-size: 18px;
  text-align: center;
}
</style>