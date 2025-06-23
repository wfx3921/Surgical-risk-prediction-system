<template>
  <div class="complication-details">
    <h1>患者实际并发症详情</h1>
    <h2>Details of the patient's actual complications</h2>
    <div v-if="loading" class="loading">
      加载中...
    </div>
    <div v-else-if="formData">
      <el-card class="detail-card">
        <div class="detail-item">
          <h3>实际住院天数(Actual Days of Hospitalization):</h3>
          <p>{{ formData.actualDays }} 天</p>
        </div>
        <div class="detail-item">
          <h3>实际并发症(Actual complications):</h3>
          <ul v-if="hasComplications">
            <li v-if="formData.ifMyocardialInfarct === 1">心肌梗死(Myocardial infarction)</li>
            <li v-if="formData.ifRespiratoryFailure === 1">呼吸衰竭(Respiratory failure)</li>
            <li v-if="formData.ifBrainRelatedComplications === 1">脑类并发症(Brain related complications)</li>
            <li v-if="formData.ifPneumonia === 1">肺炎(Pneumonia)</li>
            <li v-if="formData.ifLowerExtremityDeepVeinThrombosis === 1">下肢深静脉血栓(Lower extremity deep vein thrombosis)</li>
            <li v-if="formData.ifAnastomoticFistula === 1">吻合口瘘(Anastomotic fistula)</li>
            <li v-if="formData.ifSuperficialIncisionInfection === 1">浅表切口感染(Superficial incision infection)</li>
            <li v-if="formData.ifInfectionAroundOrgansWithinTheBodyCavity === 1">体腔内器官周围感染(Infection around organs within the body cavity)</li>
            <li v-if="formData.ifUnplannedReoperation === 1">非计划再手术(Unplanned reoperation)</li>
            <li v-if="formData.ifBleeding === 1">出血(Bleeding)</li>
            <li v-if="formData.ifIntestinalAdhesionsAndObstruction === 1">肠粘连和肠梗阻(Intestinal adhesions and obstruction)</li>
            <li v-if="formData.ifDeath === 1">死亡(Death)</li>
          </ul>
          <p v-else-if="formData.noProblem === 1">无并发症(No complications)</p>
          <p v-else>暂无并发症记录(No complications recorded)</p>
        </div>
      </el-card>
    </div>
    <div v-else>
      <el-alert
        title="还未上传实际住院天数及实际并发症"
        type="info"
        :closable="false">
      </el-alert>
    </div>
    <div class="button-container">
      <el-button type="primary" @click="goBack">返回</el-button>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import request from '@/utils/request';
import Cookie from 'js-cookie';
export default {
  name: 'ComplicationDetails',
  data() {
    return {
      loading: true,
      formData: null
    };
  },
  computed: {
    hasComplications() {
      if (!this.formData) return false;
      return Object.keys(this.formData).some(key => {
        return key !== 'actualDays' && 
               key !== 'noProblem' && 
               key.startsWith('if') && 
               this.formData[key] === 1;
      });
    }
  },
  created() {

    this.fetchData();
  },
  methods: {
    async fetchData() {
      try {
        const OutputId = Cookie.get("OutputId");
        if (!OutputId) {
          this.loading = false;
          return;
        }
        console.log(OutputId);
        const response = await request.get(`/Syndrome/getSyndromeById?id=${OutputId}`);
        if (response.data.code === 1) {
          console.log(response.data.data);
          this.formData = response.data.data;
        } else {
          console.log("失败")
          this.$message.error('获取数据失败：' + response.data.msg);
        }
      } catch (error) {
        console.error('获取数据失败:', error);
        this.$message.error('获取数据失败，请重试');
      } finally {
        this.loading = false;
      }
    },
    goBack() {
      this.$router.push('/about');
    }
  }
};
</script>

<style scoped>
.complication-details {
  padding: 20px;
  max-width: 800px;
  margin: 0 auto;
}

.loading {
  text-align: center;
  padding: 20px;
}

.detail-card {
  margin-top: 20px;
  margin-bottom: 20px;
  background-color: #fff;
  box-shadow: 0 2px 12px 0 rgba(0,0,0,0.1);
}

.detail-item {
  margin-bottom: 20px;
  padding: 0 20px;
}

.detail-item h3 {
  color: #2C5530;
  margin-bottom: 10px;
  font-size: 18px;
}

.detail-item ul {
  list-style-type: none;
  padding: 0;
}

.detail-item li {
  padding: 8px 0;
  border-bottom: 1px solid #eee;
  color: #666;
}

.detail-item li:last-child {
  border-bottom: none;
}

.button-container {
  margin-top: 20px;
  text-align: center;
}

h1 {
  color: #2C5530;
  text-align: center;
  margin-bottom: 30px;
  font-size: 24px;
}

h2 {
  color: #2C5530;
  text-align: center;
  margin-bottom: 30px;
  font-size: 15px;
}

.el-alert {
  margin: 20px 0;
}
</style> 