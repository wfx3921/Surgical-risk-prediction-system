<template>
  <div class="form-container">
    <!-- ECharts 图表容器 -->
    <div ref="chart" class="chart-container"></div>

    <!-- 按钮容器 -->
    <div class="button-container">
      <el-button type="success" @click="showPopup">上传并发症</el-button>
      <el-button type="warning" @click="prev">关闭</el-button>
    </div>
    <Popup :visible="popupVisible" @close="closePopup" />
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
      chartInstance: null,
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
        const OutputDataid = Cookies.get('OutputId'); // 假设从接口获取
        //console.log(OutputDataid);
        const response = await axios.get(`/Path/patientdata/getOutputData/${OutputDataid}`);
        if (response.data && response.data.code === 1) {
          const data = response.data.data;
          this.chartData = [
            { name: '心肌梗死', value: parseFloat(data.myocardialInfarction) },
            { name: '脑出血', value: parseFloat(data.cerebralHemorrhage) },
            { name: '脑栓塞', value: parseFloat(data.cerebralEmbolism) },
            { name: '脑梗死', value: parseFloat(data.cerebralInfarction) },
            { name: '呼吸衰竭', value: parseFloat(data.respiratoryFailure) },
            { name: '肺炎', value: parseFloat(data.pneumonia) },
            { name: '呼吸机依赖', value: parseFloat(data.ventilatorDependence) },
            { name: '肺栓塞', value: parseFloat(data.pulmonaryEmbolism) },
            { name: '深静脉血栓', value: parseFloat(data.deepVeinThrombosis) },
            { name: '表浅感染', value: parseFloat(data.superficialInfection) },
            { name: '吻合口瘘', value: parseFloat(data.anastomoticLeak) },
            { name: '器官感染', value: parseFloat(data.organInfection) },
            { name: '腹腔出血', value: parseFloat(data.abdominalCtBleeding) },
            { name: '腹腔积液', value: parseFloat(data.abdominalCtFluidAccumulation) },
            { name: '非计划再手术', value: parseFloat(data.unplannedReoperation) },
            { name: '血管出血', value: parseFloat(data.vascularBleeding) },
            { name: '胃肠粘连', value: parseFloat(data.gastrointestinalAdhesion) },
            { name: '胃肠梗阻', value: parseFloat(data.gastrointestinalObstruction) },
            { name: '住院天数', value: data.hospitalStayDays }
          ];
          this.initChart();
        } else {
          console.error('数据获取失败');
        }
      } catch (error) {
        console.error('请求失败:', error);
      }
    },
    processChartData() {
      const hospitalStayData = this.chartData.find(item => item.name === '住院天数');
      const otherData = this.chartData.filter(item => item.name !== '住院天数');
      return [...otherData, hospitalStayData];
    },
    getChartOption(sortedData, hospitalStayData) {
      return {
        tooltip: {
          trigger: 'item',
          formatter: params => {
            if (params.name === '住院天数') {
              return `${params.name}：${params.value}天`;
            } else {
              return `${params.name}：${params.value}%`;
            }
          }
        },
        xAxis: {
          type: 'value',
          max: 100,
          splitNumber: 10, // 设置分割线数量为10
          axisLabel: {
            show: true,
            formatter: '{value}%', // X轴的百分比格式
          },

          splitLine: {
            show: true, // 显示网格线
          },
        },
        yAxis: {
          type: 'category',
          data: sortedData.map(item => item.name),
          axisLabel: {
            formatter: (value, index) => {
              const icons = {
                '心肌梗死': '{iconHeart|♥}',
                '脑出血': '{iconBrain|🧠}',
                '脑栓塞': '{iconBrain|🧠}',
                '脑梗死': '{iconBrain|🧠}',
                '呼吸衰竭': '{iconLung|🫁}',
                '肺炎': '{iconLung|🫁}',
                '呼吸机依赖': '{iconLung|🫁}',
                '肺栓塞': '{iconLung|🫁}',
                '深静脉血栓': '{iconVein|🩸}',
                '表浅感染': '{iconInfection|🦠}',
                '吻合口瘘': '{iconSuture|🔗}',
                '器官感染': '{iconOrgan|🦠}',
                '腹腔出血': '{iconBleed|🩸}',
                '腹腔积液': '{iconFluid|💧}',
                '非计划再手术': '{iconSurgery|🔪}',
                '血管出血': '{iconBleed|🩸}',
                '胃肠粘连': '{iconStomach|🥼}',
                '胃肠梗阻': '{iconStomach|🥼}',
                '住院天数': '{iconCalendar|📅}'
              };
              // 住院天数使用特定样式，其他使用默认样式
              const labelStyle = value === '住院天数' ? 'specialLabel' : 'label';

              return `{${labelStyle}|${value}} ${icons[value]}`;
            },
            rich: {
              label: {
                align: 'right',
                padding: [-16, 0, 0, 0]  // 调整 padding 使标签上移 10px
              },
              specialLabel: {
                align: 'right',
                padding: [15, 0, 0, 0]  // 调整 padding 使标签上移 10px
              },
              iconHeart: { align: 'left', padding: [-16, 0, 0, 0] },
              iconBrain: { align: 'left', padding: [-16, 0, 0, 0] },
              iconLung: { align: 'left', padding: [-16, 0, 0, 0] },
              iconVein: { align: 'left', padding: [-16, 0, 0, 0] },
              iconInfection: { align: 'left', padding: [-16, 0, 0, 0] },
              iconSuture: { align: 'left', padding: [-16, 0, 0, 0] },
              iconOrgan: { align: 'left', padding: [-16, 0, 0, 0] },
              iconBleed: { align: 'left', padding: [-16, 0, 0, 0] },
              iconFluid: { align: 'left', padding: [-16, 0, 0, 0] },
              iconSurgery: { align: 'left', padding: [-16, 0, 0, 0] },
              iconStomach: { align: 'left', padding: [-16, 0, 0, 0] },
              iconCalendar: { align: 'left', padding: [17, 0, 0, 0] }
            }
          },
          axisLine: {
            show: false
          },
          axisTick: {
            show: false
          }
        },
        series: [
          {
            name: '数据',
            type: 'bar',
            data: sortedData.map(item => (item.name === '住院天数' ? null : item.value)),
            label: {
              show: true,
              position: 'right',
              formatter: params =>
                  sortedData[params.dataIndex].name !== '住院天数'
                      ? `${params.value}%`
                      : ''
            },
            itemStyle: {
              color: params => {
                const value = sortedData[params.dataIndex].value;
                let colorStart = '#ffffff';
                let colorEnd = '#ffffff';

                if (value <= 50) {
                  colorStart = '#a0f085';
                  colorEnd = '#53c43f';
                } else if (value <= 75) {
                  colorStart = '#FFFACD';
                  colorEnd = '#FFD700';
                } else if (value <= 90) {
                  colorStart = '#FFD39B';
                  colorEnd = '#FF8C00';
                } else {
                  colorStart = '#FF7F50';
                  colorEnd = '#FF4500';
                }

                return new echarts.graphic.LinearGradient(0, 0, 1, 0, [
                  { offset: 0, color: colorStart },
                  { offset: 1, color: colorEnd }
                ]);
              },
              // barBorderRadius: [15, 15, 15, 15],
              // shadowBlur: 10,
              // shadowColor: 'rgba(0, 0, 0, 0.5)'
            },
            barWidth: 15,
            emphasis: {
              scale: true,  // 启用放大效果
              focus: 'series'
            }
          },
          {
            name: '住院天数',
            type: 'bar',
            data: sortedData.map(item => (item.name === '住院天数' ? item.value : null)),
            label: {
              show: true,
              position: 'right',
              formatter: params =>
                  sortedData[params.dataIndex].name === '住院天数'
                      ? `${params.value}天`
                      : ''
            },
            itemStyle: {
              color: '#426fd0',
              barBorderRadius: [15, 15, 15, 15],
              shadowBlur: 10,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            },
            barWidth: 15,
            emphasis: {
              scale: true,  // 启用放大效果
              focus: 'series'
            }
          }
        ],
        // backgroundColor: new echarts.graphic.LinearGradient(0, 0, 1, 0, [
        //   { offset: 0, color: '#ffffff' },    // 0% 到 50% 的背景色为白色
        //   { offset: 0.5, color: '#f2f2f2' },  // 50% 到 75% 的背景色为浅灰色
        //   { offset: 0.75, color: '#d9d9d9' }, // 75% 到 90% 的背景色为中灰色
        //   { offset: 1, color: '#bfbfbf' }     // 90% 到 100% 的背景色为灰色
        // ]),
        grid: {
          top: 10,
          bottom: 50,
          left: 100,
          right: 50
        }
      };
    },
    initChart() {
      const processedData = this.processChartData();
      const hospitalStayData = this.chartData.find(item => item.name === '住院天数');

      this.chartInstance = echarts.init(this.$refs.chart);
      const chartOption = this.getChartOption(processedData, hospitalStayData);
      this.chartInstance.setOption(chartOption);
    }
  }
};
</script>

<style scoped>
.chart-container {
  width: 100%;
  height: 1000px;
}

.button-container {
  margin-top: 20px;
  text-align: center;
}

.form-container {
  margin: 20px;
}
</style>
