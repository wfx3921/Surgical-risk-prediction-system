<template>
  <div class="table-container">
    <div class="block-container"></div>
    <!--    <div class="block-container"></div>-->
    <div class="complication-row">
      <div style="width: 300px;font-weight: bold;font-family: 'Georgia';text-align: left;">预测结果(Prediction Results)</div>
      <div class="complication-chart">
      </div>
      <div>
        <span
            style="display: inline-block; width: 80px;text-align: left;font-family: 'Arial', sans-serif; font-size: 14px;">预测风险值(Predicted Risk Value)</span>
        <span
            style="display: inline-block; width: 80px;text-align: left;font-family: 'Arial', sans-serif; font-size: 14px;">平均风险值(Average Risk Value)</span>
        <span
            style="display: inline-block; width: 100px;text-align: center;font-family: 'Arial', sans-serif; font-size: 14px;">
          并发症发生率(Complication Incidence Rat)</span>
      </div>
    </div>
    <!--    <div class="block-container"></div>-->
    <div v-for="(item, index) in chartData" :key="index" class="complication-row">
      <div class="complication-title">{{ item.title }}</div>
      <div class="complication-chart" :style="{ backgroundColor: chartBackgroundColors[index] }">
        <div :id="'chart' + index" style="width: 100%; height: 50px;"></div>
      </div>
      <div>
        <span
            style="display: inline-block; width: 80px;text-align: right;font-family: 'Arial', sans-serif; font-size: 14px;">{{
            item.current.toFixed(2) }}%</span>
        <span
            style="display: inline-block; width: 80px;text-align: right;font-family: 'Arial', sans-serif; font-size: 14px;">{{
            item.expected.toFixed(2) }}%</span>
        <span
            style="display: inline-block; width: 120px;text-align: right;font-family: 'Arial', sans-serif; font-size: 14px;">{{
            item.comment }}</span>
      </div>
    </div>
    <div style="border: 1px solid #00008B; padding: 10px; text-align: center; color: #00008B; font-weight: bold;">
      <span style="display: inline-block">预测住院天数: {{ this.hospitalStayDays }} 天</span>
      <span
            style="display: block;">Predicted length of stay {{
            this.hospitalStayDays }} days</span>
    </div>
    <div class="block-container"></div>
    <!-- 按钮容器 -->
    <div class="button-container">
      <el-button type="warning" @click="prev">关闭(Close)</el-button>
    </div>
    <div class="block-container"></div>
    <!--    <div class="block-container"></div>-->
  </div>
</template>

<script>
import * as echarts from 'echarts';
import Cookies from 'js-cookie';
import request from '@/utils/request';

export default {
  data() {
    return {
      chartInstance: null,
      chartData: [], // 初始值为空数组，后续将从接口获取数据
      hospitalStayDays: 0,
      chartBackgroundColors : [], // 新增数组，用于存放每个图表的背景颜色
      complications: [], // 假设从后端获取或在上传时保存
      hospitalizationDays: null // 假设从后端获取或在上传时保存
    };
  },
  mounted() {
    this.fetchChartData();
  },
  methods: {
    prev() {
      this.$router.go(-1);
    },
    async fetchChartData() {
      try {
        const OutputDataid = Cookies.get('OutputId'); // 假设从接口获取
        console.log(OutputDataid);
        const response = await request.get(`/patientdata/getOutputData/${OutputDataid}`);
        if (response.data && response.data.code === 1) {
          const data = response.data.data;
          console.log(data);
          this.chartData = [
            { title: '心肌梗死（Myocardial Infarction）', current: parseFloat(data.myocardialInfarction), expected: 31.8, comment: parseFloat(data.myocardialInfarction) > 31.8 ? 'Over Average' : 'Below Average' },
            { title: '脑出血（Cerebral Hemorrhage）', current: parseFloat(data.cerebralHemorrhage), expected: 31.8, comment: parseFloat(data.cerebralHemorrhage) > 31.8 ? 'Over Average' : 'Below Average' },
            { title: '脑栓塞（Cerebral Embolism）', current: parseFloat(data.cerebralEmbolism), expected: 42.4, comment: parseFloat(data.cerebralEmbolism) > 42.4 ? 'Over Average' : 'Below Average' },
            { title: '脑梗死（Cerebral Infarction）', current: parseFloat(data.cerebralInfarction), expected: 80.2, comment: parseFloat(data.cerebralInfarction) > 80.2 ? 'Over Average' : 'Below Average' },
            { title: '呼吸衰竭（Respiratory Failure）', current: parseFloat(data.respiratoryFailure), expected: 20.0, comment: parseFloat(data.respiratoryFailure) > 20.0 ? 'Over Average' : 'Below Average' },
            { title: '肺炎（Pneumonia）', current: parseFloat(data.pneumonia), expected: 51.0, comment: parseFloat(data.pneumonia) > 51.0 ? 'Over Average' : 'Below Average' },
            { title: '呼吸机依赖（Ventilator Dependence）', current: parseFloat(data.ventilatorDependence), expected: 51.0, comment: parseFloat(data.ventilatorDependence) > 51.0 ? 'Over Average' : 'Below Average' },
            { title: '肺栓塞（Pulmonary Embolism）', current: parseFloat(data.pulmonaryEmbolism), expected: 51.0, comment: parseFloat(data.pulmonaryEmbolism) > 51.0 ? 'Over Average' : 'Below Average' },
            { title: '深静脉血栓（Deep Vein Thrombosis）', current: parseFloat(data.deepVeinThrombosis), expected: 51.0, comment: parseFloat(data.deepVeinThrombosis) > 51.0 ? 'Over Average' : 'Below Average' },
            { title: '表浅感染（Superficial Infection）', current: parseFloat(data.superficialInfection), expected: 51.0, comment: parseFloat(data.superficialInfection) > 51.0 ? 'Over Average' : 'Below Average' },
            { title: '吻合口瘘（Anastomotic Leak）', current: parseFloat(data.anastomoticLeak), expected: 51.0, comment: parseFloat(data.anastomoticLeak) > 51.0 ? 'Over Average' : 'Below Average' },
          ];
          this.hospitalStayDays = data.hospitalStayDays;
          console.log(1);
          console.log(this.chartData);
          this.$nextTick(() => {
            this.chartData.forEach((item, index) => {
              this.initChart(item.current, item.expected, index);
            });
          });
          // this.initChart();
        } else {
          console.error('数据获取失败');
        }
      } catch (error) {
        console.error('请求失败:', error);
      }
    },

    //           const icons = {
    //             '心肌梗死': '{iconHeart|♥}',
    //             '脑出血': '{iconBrain|🧠}',
    //             '脑栓塞': '{iconBrain|🧠}',
    //             '脑梗死': '{iconBrain|🧠}',
    //             '呼吸衰竭': '{iconLung|🫁}',
    //             '肺炎': '{iconLung|🫁}',
    //             '呼吸机依赖': '{iconLung|🫁}',
    //             '肺栓塞': '{iconLung|🫁}',
    //             '深静脉血栓': '{iconVein|🩸}',
    //             '表浅感染': '{iconInfection|🦠}',
    //             '吻合口瘘': '{iconSuture|🔗}',
    //             '器官感染': '{iconOrgan|🦠}',
    //             '腹腔出血': '{iconBleed|🩸}',
    //             '腹腔积液': '{iconFluid|💧}',
    //             '非计划再手术': '{iconSurgery|🔪}',
    //             '血管出血': '{iconBleed|🩸}',
    //             '胃肠粘连': '{iconStomach|🥼}',
    //             '胃肠梗阻': '{iconStomach|🥼}',
    //             '住院天数': '{iconCalendar|📅}'
    //           };

    initChart(currentValue, expectedValue, index) {
      const chartDom = document.getElementById('chart' + index);
      const myChart = echarts.init(chartDom);

      // 根据 currentValue 和 expectedValue 的关系设置颜色
      let color;
      if (currentValue < expectedValue) {
        color = '#64B064'; // current 小于 expected
        this.$set(this.chartBackgroundColors,index,'#E0EFE0'); // 绿色背景
      } else if (Math.abs(currentValue - expectedValue) < 10) {
        color = '#FFFF91'; // current 与 expected 相差小于 10
        this.$set(this.chartBackgroundColors,index,'#FCFDE0'); // 橙色背景
      } else {
        color = '#F25767'; // 其他情况
        this.$set(this.chartBackgroundColors,index, '#FDDDE0'); // 红色背景
      }
      console.log(this.chartBackgroundColors);
      // 设置图表选项
      const option = {
        xAxis: {
          type: 'value',
          max: 100,
          splitNumber: 10, // 设置分割线数量为10
          axisLabel: {
            show: true,
            formatter: function (value) {
              return value === 0 ? '' : `${value}%`; // 隐藏0刻度数字
            },
            margin: -15 // 负值将标签
          },
          axisTick: {
            alignWithLabel: true
          },
          splitLine: {
            show: true, // 显示水平辅助线
            lineStyle: {
              type: 'dashed', // 可以设置为实线或虚线
              color: '#ccc' // 选择辅助线的颜色
            }
          },
          z: 10 // 设置一个较高的 z 值
          // position: 'top' // 将 x 轴位置设置为顶部
        },
        yAxis: {
          type: 'category',
          data: [''],
          axisLabel: { show: false },
          axisTick: {
            alignWithLabel: true
          },
          // splitLine: {
          //   show: true, // 显示水平辅助线
          //   lineStyle: {
          //     type: 'dashed', // 可以设置为实线或虚线
          //     color: '#ccc' // 选择辅助线的颜色
          //   }
          // },
        },
        series: [
          {
            type: 'bar',
            data: [currentValue], // 条形图数据
            barWidth: '70', // 条形图宽度
            itemStyle: {
              color: color,//'#64B064', // 条形图填充颜色
              borderColor: '#020000', // 条形图边框颜色
              borderWidth: 0.7, // 边框宽度
            },
            label: {
              show: false,
              position: 'right',
              formatter: '{c}%', // 在条形图右侧显示当前值的百分比
              fontSize: 5,
            },
            // 使用 markLine 添加红色竖线来表示期望值
            markLine: {
              symbol: 'none', // 去掉标线两端的箭头
              data: [
                {
                  xAxis: expectedValue, // 设置竖线的位置为期望值
                },
              ],
              lineStyle: {
                color: '#fc7575', // 竖线的颜色设置为红色
                width: 1.5, // 竖线的宽度
                type: 'solid', // 竖线类型为实线
              },
              label: {
                show: false, // 不显示竖线上的标签
              },
              z: 10 // 设置一个较高的 z 值
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
    viewComplications() {
      this.$router.push({
        path: '/complication-details',
        query: {
          complications: JSON.stringify(this.complications),
          hospitalizationDays: this.hospitalizationDays
        }
      });
    }
  }
};
</script>

<style scoped>
.table-container {
  width: 90%;
  margin: 0 auto;
  /* 使容器水平居中 */
}

.button-container {
  display: flex;
  justify-content: center;
}

.block-container {
  height: 30px;
}

.complication-row {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
}

.complication-title {
  width: 300px;
  font-weight: bold;
}

.complication-chart {
  flex: 1;
  margin: 0 10px;
  background-color:  #E0EFE0;
}

.complication-data {
  display: flex;
  gap: 20px;
  margin: 20px;
  width: 300px;
  text-align: right;
  /* background-color: #FCFDE0;
  background-color: #f8fc7d;
  background-color: #FDDDE0;
  background-color: #F25767; */
}
</style>
