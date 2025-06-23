<template>
  <div>
    <button @click="prev">返回</button>
    <button @click="showPopup">显示弹窗</button>
    
    <div v-if="chartData.length" class="chart-container">
      <div v-for="(item, index) in chartData" :key="index" class="chart-row">
        <div class="chart-title">{{ item.title }}</div>
        <div class="chart" :id="'chart' + index" style="width: 100%; height: 50px;"></div>
        <div class="chart-data">
          <span>{{ item.current }}%</span>
          <span>{{ item.expected }}%</span>
          <span>{{ item.comment }}</span>
        </div>
      </div>
    </div>

    <Popup v-if="popupVisible" @close="closePopup" />
  </div>
</template>

<script>
import axios from 'axios';
import * as echarts from 'echarts';
import Popup from './Popup.vue';
import Cookies from 'js-cookie';

export default {
  data() {
    return {
      popupVisible: false,
      chartData: []  // 初始值为空数组，后续将从接口获取数据
    };
  },
  mounted() {
    this.fetchChartData();
  },
  components: {
    Popup,
  },
  methods: {
    prev() {
      this.$router.go(-1);
    },
    showPopup() {
      this.popupVisible = true;
    },
    closePopup() {
      this.popupVisible = false;
    },
    async fetchChartData() {
      try {
        const OutputDataid = Cookies.get('OutputId'); // 从Cookies中获取ID
        const response = await axios.get(`/Path/patientdata/getOutputData/${OutputDataid}`);
        if (response.data && response.data.code === 1) {
          const data = response.data.data;
          this.chartData = [
            { title: '心肌梗死', current: parseFloat(data.myocardialInfarction), expected: 31.8, comment: 'Below Average' },
            { title: '脑出血', current: parseFloat(data.cerebralHemorrhage), expected: 31.8, comment: 'Below Average' },
            { title: '脑栓塞', current: parseFloat(data.cerebralEmbolism), expected: 42.4, comment: 'Below Average' },
            { title: '脑梗死', current: parseFloat(data.cerebralInfarction), expected: 80.2, comment: 'Below Average' },
            { title: '呼吸衰竭', current: parseFloat(data.respiratoryFailure), expected: 20.0, comment: 'Below Average' },
            { title: '肺炎', current: parseFloat(data.pneumonia), expected: 51.0, comment: 'Below Average' },
            { title: '呼吸机依赖', current: parseFloat(data.ventilatorDependence), expected: 51.0, comment: 'Below Average' },
            { title: '肺栓塞', current: parseFloat(data.pulmonaryEmbolism), expected: 51.0, comment: 'Below Average' },
            { title: '深静脉血栓', current: parseFloat(data.deepVeinThrombosis), expected: 51.0, comment: 'Below Average' },
            { title: '表浅感染', current: parseFloat(data.superficialInfection), expected: 51.0, comment: 'Below Average' },
            { title: '吻合口瘘', current: parseFloat(data.anastomoticLeak), expected: 51.0, comment: 'Below Average' },
            { title: '器官感染', current: parseFloat(data.organInfection), expected: 51.0, comment: 'Below Average' },
            { title: '腹腔出血', current: parseFloat(data.abdominalCtBleeding), expected: 51.0, comment: 'Below Average' },
            { title: '腹腔积液', current: parseFloat(data.abdominalCtFluidAccumulation), expected: 51.0, comment: 'Below Average' },
            { title: '非计划再手术', current: parseFloat(data.unplannedReoperation), expected: 51.0, comment: 'Below Average' },
            { title: '血管出血', current: parseFloat(data.vascularBleeding), expected: 51.0, comment: 'Below Average' },
            { title: '胃肠粘连', current: parseFloat(data.gastrointestinalAdhesion), expected: 51.0, comment: 'Below Average' },
            { title: '胃肠梗阻', current: parseFloat(data.gastrointestinalObstruction), expected: 51.0, comment: 'Below Average' },
          ];
          this.chartData.forEach((item, index) => {
            this.initChart(item.current, item.expected, index);
          });
        }
      } catch (error) {
        console.error('请求失败:', error);
      }
    },
    initChart(currentValue, expectedValue, index) {
      const chartDom = document.getElementById('chart' + index);
      const myChart = echarts.init(chartDom);
      const option = {
        xAxis: {
          type: 'value',
          max: 100,
          splitNumber: 10,
          axisLabel: {
            show: true,
            formatter: function(value) {
              return value === 0 ? '' : `${value}%`;
            },
            margin: -15,
          },
          axisTick: {
            alignWithLabel: true,
          },
          splitLine: {
            show: true,
            lineStyle: {
              type: 'dashed',
              color: '#ccc',
            },
          },
          z : 10 // 设置一个较高的 z 值
        },
        yAxis: {
          type: 'category',
          data: [''],
          axisLabel: { show: false },
          axisTick: {
            alignWithLabel: true,
          },
          splitLine: {
            show: true,
            lineStyle: {
              type: 'dashed',
              color: '#ccc',
            },
          },
        },
        series: [
          {
            type: 'bar',
            data: [currentValue],
            barWidth: '40',
            itemStyle: {
              color: '#98fb98',
              borderColor: '#020000',
              borderWidth: 0.7,
            },
            label: {
              show: false,
              position: 'right',
              formatter: '{c}%',
            },
            markLine: {
              symbol: 'none',
              data: [
                {
                  xAxis: expectedValue,
                },
              ],
              lineStyle: {
                color: '#020000',
                width: 3,
                type: 'solid',
              },
              label: {
                show: false,
              },
            },
          },
        ],
        grid: {
          top: '7',
          left: '10',
          right: '15',
          bottom: '10',
          containLabel: true,
        },
      };
      myChart.setOption(option);
    },
  },
};
</script>

<style scoped>
.chart-container {
  margin: 20px 0;
}

.chart-row {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
}

.chart-title {
  width: 200px;
  font-weight: bold;
}

.chart {
  flex: 1;
  margin: 0 10px;
  background-color: #f0fff0;
}

.chart-data {
  display: flex;
  gap: 10px;
  width: 200px;
  text-align: right;
}

.chart-data span {
  width: 50px;
}
</style>
