<template>
  <div class="list-container" style="background-color:azure">
    <div class="header" style="">
      <div class="list-item" style="">
        <div class="checkbox-container" style="padding-left: 40px;border-radius: 0px; ">
          <input style="padding-left: 400px;" type="checkbox" v-model="selectAll" @click="toggleSelectAll">
        </div>
        <div class="item-content">
    <table >
       
      <table style="margin: 0 auto;">
    <tr>
        <td style="width: 120px; font-weight: bold; color: blue; text-align: center;">病案号</td>
        <td style="width: 120px; font-weight: bold; color: blue; text-align: center;">创建时间</td>
        <td style="width: 120px; font-weight: bold; color: blue; text-align: center;">评估时间</td>
        <td style="width: 120px; font-weight: bold; color: blue; text-align: center;">手术部位</td>
        <td style="width: 120px; font-weight: bold; color: blue; text-align: center;">患者性别</td>
        <td style="width: 120px; font-weight: bold; color: blue; text-align: center;">患者年龄</td>
        <td style="width: 100px; font-weight: bold; color: blue; text-align: center;">是否吸烟</td>
        <td style="width: 120px; font-weight: bold; color: blue; text-align: center;">是否饮酒</td>
        <td style="width: 120px; font-weight: bold; color: blue; text-align: center;">BMI(kg/㎡)</td>
        <td style="width: 120px; font-weight: bold; color: blue; text-align: center;">血红蛋白(g/L)</td>           
        <td style="width: 100px; font-weight: bold; color: blue; text-align: center;">D-二聚体(ng-ml)</td>
        <td style="width: 100px; font-weight: bold; color: blue; text-align: center;">抗凝血酶III(%)</td>
        <td style="width: 100px; font-weight: bold; color: blue; text-align: center;">直接胆红素(umol/L)</td>
        <td style="width: 100px; font-weight: bold; color: blue; text-align: center;">谷丙转氨酶(U/L)</td>
    </tr>
</table>

       
    </table>


</div>


      </div>
    </div>
    
    <div class="list-view-container" style="">
      <ul class="list-view" ref="list" style="">
        <!-- 数据行 -->
        <li v-for="item in items" :key="item.id" class="list-item" style="">
          <div class="checkbox-container">
            <input type="checkbox" v-model="checkedItems[item.id]" @click="toggleCheckbox(item)">
          </div>
        <div class="item-info" style=""> 
        <div class="item-title"style="width:100px;text-align: center;">{{ item.hao }}</div>
        <div class="item-description"style="width:100px">{{ item.cre_time }}</div>
        <div class="item-other"style="width:100px">{{ item.test_time }}</div>
        <div class="item-other"style="width:100px">{{ item.bw }}</div>
        <div class="item-other"style="width:100px">{{ item.sex }}</div>
        <div class="item-other"style="width:80px;">{{ item.age }}</div>
        <div class="item-other"style="width:80px">{{ item.smk }}</div>
        <div class="item-other"style="width:80px">{{ item.wine }}</div>
        <div class="item-other"style="width:100px">{{ item.bmi }}</div>
        <div class="item-other"style="width:100px">{{ item.xhdb }}</div>
        <div class="item-other"style="width:100px;">{{ item.d }}</div>
        <div class="item-other"style="width:100px;">{{ item.mei }}</div>
        <div class="item-other"style="width:100px;">{{ item.dhs }}</div>
        <div class="item-other"style="width:100px;">{{ item.zam }}</div>
        <div class="item-actions">
              <button @click="handleAction1(item)">评估结果</button>
              <button @click="handleAction2(item)">删除</button>
              <button @click="handleAction3(item)">上报并发症</button>
              <button @click="handleAction3(item)">重新评估</button>
            </div>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      items: [
        { id: 1, hao: '1', cre_time: '2024-7-12 20:22:43', test_time: '2024-7-12 20:22:43',bw:'结肠',sex:'男',age:'45',smk:'是',wine:'否',bmi:'53',xhdb:'57',d:'101',mei:'56',dhs:'109',zam:'45' },
        { id: 2, hao: '2', cre_time: '2024-7-12 20:22:43', test_time: '2024-7-12 20:22:43',bw:'胃',sex:'女',age:'35',smk:'是',wine:'否',bmi:'53',xhdb:'57',d:'101',mei:'56',dhs:'109',zam:'45' },
        { id: 3, hao: '3', cre_time: '2024-7-12 20:22:43', test_time: '2024-7-12 20:22:43',bw:'胆囊',sex:'男',age:'65',smk:'否',wine:'否',bmi:'53',xhdb:'57',d:'101',mei:'56',dhs:'109',zam:'45' },
        { id: 4, hao: '4', cre_time: '2024-7-12 20:22:43', test_time: '2024-7-12 20:22:43',bw:'结肠',sex:'男',age:'45',smk:'是',wine:'否',bmi:'53',xhdb:'57',d:'101',mei:'56',dhs:'109',zam:'45' },
        { id: 5, hao: '5', cre_time: '2024-7-12 20:22:43', test_time: '2024-7-12 20:22:43',bw:'结肠',sex:'男',age:'45',smk:'是',wine:'否',bmi:'53',xhdb:'57',d:'101',mei:'56',dhs:'109',zam:'45' }

      ],
      checkedItems: {}, 
      selectAll: false 
    };
  },
  methods: {
    toggleCheckbox(item) {
      this.$set(this.checkedItems, item.id, !this.checkedItems[item.id]);
    },
    toggleSelectAll() {
      this.selectAll = !this.selectAll;
      if (this.selectAll) {
        this.items.forEach(item => {
          this.$set(this.checkedItems, item.id, true);
        });
      } else {
        this.checkedItems = {};
      }
    }
  }
};
</script>
<style scoped>
.list-container {
border: 1px solid #ccc;
border-radius: 0px;
overflow: auto;
}

.header {
position: sticky;
top: 0;
background-color:azure;
z-index: 1;
padding: 10px 0;

border-bottom: 1px solid #eee;
}

.list-view-container {
max-height: 200px;
}

.list-view {
margin-top: 0px;

min-width: 700px;
}

.list-item {
display: flex;
align-items: center;
padding: 10px;
border-bottom: 1px solid #eee;
}

.checkbox-container {
margin-right: 10px;
}

.item-info {
flex: 1;
display: flex;
justify-content: space-between;
align-items: center;
}

.item-content {
display: flex;
align-items: center;
}

.item-title {
font-size: 0.9em;
color: #888;
margin-right: 10px;
}

.item-description {
color: #666;
margin-right: 10px;
}

.item-other {
font-size: 0.9em;
color: #888;
margin-right: 0px;
}

.item-actions {
display: flex;
align-items: center;
width: 300px;
}

.item-actions button {
margin-left: 5px;
}
</style>