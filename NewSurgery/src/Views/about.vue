<template>
  <div class="about-container">
    <div class="content-wrapper">
      <div class="background-image"></div>
      <el-tabs v-model="activeTab" style="width: 100%;height: 100%;">
        <!-- 搜索框 -->
        <div class="form-container0">
          <el-form :inline="true" class="search-form" style="margin-left:0px;margin-right: 0px">
            <el-form-item>
              <el-input v-model="searchForm.hospitalizationNumber" placeholder="病案号(Hospitalization Number)" style="width: 240px; height: 32px;"></el-input>
            </el-form-item>
            <el-form-item>
              <el-select v-model="searchForm.primarySurgicalSite" placeholder="手术部位(surgery site)" style="width: 210px; height: 32px;">
                <el-option label="胃(Gastric)" value="胃"></el-option>
                <el-option label="直肠(Rectal)" value="直肠"></el-option>
                <el-option label="结肠(Colon)" value="结肠"></el-option>
              </el-select>
            </el-form-item>

            <el-form-item >
              <el-input v-model="searchForm.patientName" placeholder="患者姓名(patient name)" style="width: 190px; height: 32px;"></el-input>
            </el-form-item>

            <el-form-item>
              <el-select v-model="searchForm.patientGender" placeholder="患者性别(patient gender)" style="width: 215px; height: 32px;">
                <el-option label="男(Male)" value="男"></el-option>
                <el-option label="女(Female)" value="女"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item>
              <el-select v-model="searchForm.smokingHistory" placeholder="是否吸烟(smoking history)" style="width: 220px; height: 32px;">
                <el-option label="是(Yes)" value="是"></el-option>
                <el-option label="否(No)" value="否"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item>
              <el-select v-model="searchForm.alcoholHistory" placeholder="是否饮酒(alcohol history)" style="width: 220px; height: 32px;">
                <el-option label="是(Yes)" value="是"></el-option>
                <el-option label="否(No)" value="否"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="BMI区间(BMI range)" class="range-item">
              <div class="range-input">
                <el-input v-model="searchForm.patientBmi" placeholder="最小值(min)"  style="height: 40px; width: 110px;"></el-input>
                <span class="range-separator">~</span>
                <el-input v-model="searchForm.patientBmi2" placeholder="最大值(max)"  style="height: 40px; width: 110px;"></el-input>
              </div>
            </el-form-item>
            <el-form-item label="血红蛋白(blood hemoglobin)" class="range-item">
              <div class="range-input">
                <el-input v-model="searchForm.bloodRoutineHemoglobin" placeholder="最小值(min)"  style="height: 40px; width: 110px;"></el-input>
                <span class="range-separator">~</span>
                <el-input v-model="searchForm.bloodRoutineHemoglobin2" placeholder="最大值(max)" style="height: 40px; width: 110px;"></el-input>
              </div>
            </el-form-item>
            <el-form-item label="D-二聚体(D-dimer)" class="range-item">
              <div class="range-input">
                <el-input v-model="searchForm.coagulationDDimer" placeholder="最小值(min)" size="width" style="height: 40px; width: 110px;"></el-input>
                <span class="range-separator">~</span>
                <el-input v-model="searchForm.coagulationDDimer2" placeholder="最大值(max)" size="width" style="height: 40px; width: 110px;"></el-input>
              </div>
            </el-form-item>
            <el-form-item label="抗凝血酶III(antithrombin III)" class="range-item">
              <div class="range-input">
                <el-input v-model="searchForm.coagulationAntithrombinIii" placeholder="最小值(min)" size="width" style="height: 40px; width: 110px;"></el-input>
                <span class="range-separator">~</span>
                <el-input v-model="searchForm.coagulationAntithrombinIii2" placeholder="最大值(max)" size="width" style="height: 40px; width: 110px;"></el-input>
              </div>
            </el-form-item>
            <el-form-item label="直接胆红素(direct bilirubin)" class="range-item">
              <div class="range-input">
                <el-input v-model="searchForm.liverFunctionDirectBilirubin" placeholder="最小值(min)" size="width" style="height: 40px; width: 110px;"></el-input>
                <span class="range-separator">~</span>
                <el-input v-model="searchForm.liverFunctionDirectBilirubin2" placeholder="最大值(max)" size="width" style="height: 40px; width: 110px;"></el-input>
              </div>
            </el-form-item>
            <el-button type="primary" @click="search" class="action-button query-button" title="Query">查询</el-button>
            <el-button type="primary" @click="resetForm" class="action-button reset-button" title="Reset">重置</el-button>
            <el-button type="danger" @click="moreDelete" class="action-button" title="Delete">删除</el-button>
            <el-button type="primary" @click="fetchDataAndShowDialog">
              查看最近7天新增数据统计
            </el-button>



            <el-form-item label="录入时间范围">
              <el-date-picker
                  v-model="searchForm.startDate"
                  type="date"
                  placeholder="选择开始日期"
                  value-format="yyyy-MM-dd"
                  :clearable="true"
              />
            </el-form-item>

            <el-form-item label="">
              <el-date-picker
                  v-model="searchForm.endDate"
                  type="date"
                  placeholder="选择结束日期"
                  value-format="yyyy-MM-dd"
                  :clearable="true"
                  :disabled="!searchForm.startDate"

              />
            </el-form-item>

            <el-form-item label="录入人">
              <el-input v-model="searchForm.staff" clearable />
            </el-form-item>

            <el-dialog
                append-to-body
                :visible.sync="dialogVisible"
                title="最近7天新增数据统计"
                width="50%"
                :modal="false"
            >
              <div v-loading="loading">
                <div v-if="error" class="error-message">
                  {{ error }}
                </div>

                <el-table
                    :data="tableData"
                    style="width: 100%"
                    border
                    stripe
                    v-if="tableData.length > 0"
                >
                  <el-table-column
                      prop="date"
                      label="日期"
                      width="150"
                      align="center"
                  />
                  <el-table-column
                      prop="count"
                      label="新增数量"
                      align="center"
                  />
                </el-table>
                <div v-else class="no-data">
                  暂无数据
                </div>
              </div>

              <template #footer>
                <el-button @click="dialogVisible = false">关闭</el-button>
              </template>
            </el-dialog>
          </el-form>
        </div>
        <!-- 添加折叠按钮 -->
        <div class="collapse-button" @click="toggleSearchForm">
          <i :class="['el-icon-arrow-up', {'is-collapsed': isCollapsed}]"></i>
          <span>{{ isCollapsed ? '展开搜索(Expand search)' : '隐藏搜索(Hide search)' }}</span>
        </div>
        <div class="form-container" :class="{'collapsed-mode': isCollapsed}">
          <!-- 表格 -->
          <div class="common-table  " stripe style="width: 100%" height="100%">
            <!-- 用户数据Table -->
            <el-table :data="filteredItems" stripe style="width: 100%"
              @selection-change="handleSelectionChange">
              <el-table-column type="selection" width="45"></el-table-column>
              <el-table-column prop="hospitalizationNumber" label="病案号" width="95" align="center">
                <template slot="header">
                  <div>病案号</div>
                  <div style="font-size: 11px; color: #909399;">Case Number</div>
                </template>
              </el-table-column>
              <el-table-column prop="createTime" label="评估时间" width="120" align="center">
                <template slot="header">
                  <div>评估时间</div>
                  <div style="font-size: 11px; color: #909399;">Assessment Time</div>
                </template>
              </el-table-column>
              <el-table-column prop="department" label="录入部门" width="90" align="center">
                <template slot="header">
                  <div>录入部门</div>
                  <div style="font-size: 11px; color: #909399;">Department</div>
                </template>
              </el-table-column>
              <el-table-column prop="staff" label="录入人" width="90" align="center">
                <template slot="header">
                  <div>录入人</div>
                  <div style="font-size: 11px; color: #909399;">Staff</div>
                </template>
              </el-table-column>
              <el-table-column prop="primarySurgicalSite" label="手术部位" width="90" align="center">
                <template slot="header">
                  <div>手术部位</div>
                  <div style="font-size: 11px; color: #909399;">Surgery Site</div>
                </template>
              </el-table-column>
              <el-table-column prop="patientName" label="患者姓名" width="90" align="center">
                <template slot="header">
                  <div>患者姓名</div>
                  <div style="font-size: 11px; color: #909399;">Name</div>
                </template>
              </el-table-column>
              <el-table-column prop="patientGender" label="患者性别" width="70" align="center">
                <template slot="header">
                  <div>患者性别</div>
                  <div style="font-size: 11px; color: #909399;">Gender</div>
                </template>
              </el-table-column>
              <el-table-column prop="patientAge" label="患者年龄" width="70" align="center">
                <template slot="header">
                  <div>患者年龄</div>
                  <div style="font-size: 11px; color: #909399;">Age</div>
                </template>
              </el-table-column>
              <el-table-column prop="smokingHistory" label="是否吸烟" width="70" align="center">
                <template slot="header">
                  <div>是否吸烟</div>
                  <div style="font-size: 11px; color: #909399;">Smoking</div>
                </template>
              </el-table-column>
              <el-table-column prop="alcoholHistory" label="是否饮酒" width="70" align="center">
                <template slot="header">
                  <div>是否饮酒</div>
                  <div style="font-size: 11px; color: #909399;">Drinking</div>
                </template>
              </el-table-column>
              <el-table-column prop="patientBmi" label="BMI(kg/㎡)" width="115" align="center">
                <template slot="header">
                  <div>BMI(kg/㎡)</div>
                  <div style="font-size: 11px; color: #909399;">Body Mass Index</div>
                </template>
              </el-table-column>
              <el-table-column prop="bloodRoutineHemoglobin" label="血红蛋白(g/L)" width="105" align="center">
                <template slot="header">
                  <div>血红蛋白(g/L)</div>
                  <div style="font-size: 11px; color: #909399;">Hemoglobin</div>
                </template>
              </el-table-column>
              <el-table-column prop="coagulationDDimer" label="D-二聚体(ng-ml)" width="120" align="center">
                <template slot="header">
                  <div>D-二聚体(ng-ml)</div>
                  <div style="font-size: 11px; color: #909399;">D-Dimer</div>
                </template>
              </el-table-column>
              <el-table-column prop="coagulationAntithrombinIii" label="抗凝血酶III(%)" width="110" align="center">
                <template slot="header">
                  <div>抗凝血酶III(%)</div>
                  <div style="font-size: 11px; color: #909399;">Antithrombin III</div>
                </template>
              </el-table-column>
              <el-table-column prop="liverFunctionDirectBilirubin" label="直接胆红素(umol/L)" width="135" align="center">
                <template slot="header">
                  <div>直接胆红素(umol/L)</div>
                  <div style="font-size: 11px; color: #909399;">Direct Bilirubin</div>
                </template>
              </el-table-column>

              <el-table-column width="345" fixed="right" align="center">
                <template slot="header">
                  <div style="font-weight: bold; font-size: 12px;">操作</div>
                  <div style="font-size: 11px; color: #909399;">Operation</div>
                </template>
                <template slot-scope="scope">
                  <el-button 
                    type="primary" 
                    size="mini"
                    style="border-radius: 4px; border: 1.5px solid #1e5bb0; background-color: transparent; color: #1e5bb0; margin-right: 2px; padding: 4px 3px; width: 72px; font-weight: 500;"
                    @click="retest(scope.row)"
                    title="Re-assessment"
                  ><i class="el-icon-refresh" style="margin-right: 2px;"></i>重新评估<br>Reassess</el-button>
                  
                  <el-button 
                    type="primary" 
                    size="mini"
                    style="border-radius: 4px; border: 1.5px solid #1e5bb0; background-color: transparent; color: #1e5bb0; margin-right: 2px; padding: 4px 3px; width: 72px; font-weight: 500;"
                    @click="result(scope.row)"
                    title="Assessment Result"
                  ><i class="el-icon-document" style="margin-right: 2px;"></i>评估结果<br>Results</el-button>

                  <el-button 
                    type="primary" 
                    size="mini"
                    style="border-radius: 4px; border: 1.5px solid #1e5bb0; background-color: transparent; color: #1e5bb0; margin-right: 2px; padding: 4px 3px; width: 58px; font-weight: 500;"
                    @click="handlePopup(scope.row)"
                    title="Upload"
                  ><i class="el-icon-upload2" style="margin-right: 2px;"></i>上传<br>Upload</el-button>

                  <el-button 
                    type="primary" 
                    size="mini"
                    style="border-radius: 4px; border: 1.5px solid #1e5bb0; background-color: transparent; color: #1e5bb0; padding: 4px 3px; width: 58px; font-weight: 500;"
                    @click="viewComplications(scope.row)"
                    title="View"
                  ><i class="el-icon-view" style="margin-right: 2px;"></i>查看<br>View</el-button>
                </template>
              </el-table-column>

            </el-table>
            <div class="block-container"></div>
            <!-- 分页 -->
            <div class="pager">
              <el-pagination 
                layout="total,  prev, pager, next, sizes, jumper" 
                :total="total" 
                :current-page.sync="currentPage" 
                :page-size="size"
                :page-sizes="[10, 20, 50, 100]"
                @current-change="search(currentPage, size)" 
                @prev-click="prevPage" 
                @next-click="nextPage"
                @size-change="updatePageSize">
              </el-pagination>
            </div>
          </div>
        </div>

      </el-tabs>
      <div class="block-container"></div>

      <!-- 添加 Popup 组件 -->
      <Popup 
        v-if="popupVisible" 
        :userId="currentUserId"
        @close="popupVisible = false" 
      />
    </div>
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
      form: {
        staff: "",          // 存储姓名
        dateRange: [],     // 存储日期范围 [开始日期, 结束日期]
      },
      dialogVisible: false,
      loading: false,
      error: '',
      tableData: [],
      isCollapsed: false,
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
        staff:'',
        startDate:'',
        endDate:'',
        hospitalizationNumber: '',
        patientName: '',
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
        age: '',
        diagnosis: '',
        bmi: '',
        nrs: '',
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
      currentUserId: null,

      rules0: {
        caseNumber: [
          { required: true, message: '请输入住院号', trigger: 'blur' }
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
  },

  methods: {
    async fetchDataAndShowDialog() {
      this.dialogVisible = true
      this.loading = true
      this.error = ''

      try {
        // 调用真实API
        const response = await this.$axios.get('/api/patientdata/last7days-stats')

        // 处理返回数据
        if (response.data && response.data.data) {
          this.tableData = Object.entries(response.data.data).map(([date, count]) => ({
            date,
            count
          }))
        } else {
          this.error = '返回数据格式不正确'
        }
      } catch (err) {
        this.error = '获取数据失败: ' + (err.message || '未知错误')
        console.error('获取统计数据失败:', err)
      } finally {
        this.loading = false
      }
    },
    toggleSearchForm() {
      this.isCollapsed = !this.isCollapsed;
      const formContainer = document.querySelector('.form-container0');
      if (this.isCollapsed) {
        formContainer.style.display = 'none';
      } else {
        formContainer.style.display = 'flex';
      }
    },
    handlePopup(row) {
      Cookie.set('OutputId', row.userId);
      this.$router.push('/Popup');
    },
    viewComplications(row) {
      Cookie.set('OutputId', row.userId);
      this.$router.push({
        path: '/complication-details',
        query: {
          complications: JSON.stringify(this.complications),
          hospitalizationDays: this.hospitalizationDays
        }
      });
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
      this.searchForm.patientName = '';
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
    // 更新页面
    updatePageSize(val) {
      if (val) {
        this.size = val;
      }
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
    search() {
      const userId1 = JSON.parse(Cookie.get('userInfo'));
      this.searchForm.creator = userId1.userId;
      console.log(this.searchForm.dateRange);
      //console.log(this.searchForm.creator);
      //console.log(JSON.stringify(this.searchForm, null, 2));
      request.post(`/patientdata/getlist?page=${this.currentPage}&pageSize=${this.size}`, this.searchForm)
        .then(response => {
          if (response.data.code === 1) {
            const items = response.data.data.content.map(user => {
              return {
                userId: user.id,
                hospitalizationNumber: user.hospitalizationNumber,
                patientName: user.patientName,
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
                department:user.department,
                staff:user.staff
              };
            });

            this.filteredItems = items;
            this.total = response.data.data.totalElements;
            this.size = response.data.data.pageable.pageSize;
            this.currentPage = response.data.data.number+1;
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
          // this.activeTab = "riskAssessment";
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

                // 结肠直肠共有的额外指标
                this.labTests.red = response.data.data.red;                                   // 红细胞
          this.labTests.platelet = response.data.data.platelet;                         // 血小板
          this.labTests.carcinoembryonic = response.data.data.carcinoembryonic;             // 癌胚抗原
          this.labTests.thyroid = response.data.data.thyroid;                           // 甲状腺激素
          this.labTests.bloodlipid = response.data.data.bloodlipid;                       // 血脂
          this.labTests.bloodcreatinine = response.data.data.bloodcreatinine;               // 血肌酐

// 直肠单独的指标
          this.labTests.CA125 = response.data.data.ca;                               // CA125
          this.labTests.antigen19 = response.data.data.antigen19;                         // 19-9
          this.labTests.AFP = response.data.data.AFP;                                   // AFP
          this.labTests.viral = response.data.data.viral;                                 // 病毒性肝炎标志物

// 结肠单独的指标
          this.labTests.reactiveprotein = response.data.data.reactiveprotein;               // C反应蛋白_CRP
          this.labTests.bloodtest = response.data.data.bloodtest;                         // 粪便隐血试验
          this.labTests.alphafetoprotein = response.data.data.alphafetoprotein;

          this.auxiliaryExams.historyCOPD = response.data.data.respiratorySevereCopdHistory,
            this.auxiliaryExams.Dialysis = response.data.data.kidneyFunctionDialysis,
            this.auxiliaryExams.AcuteRenalFailure = response.data.data.kidneyFunctionAcuteFailure,
            this.auxiliaryExams.chestCT = response.data.data.chestCtLungInflammation,
            this.auxiliaryExams.heartEcho = response.data.data.cardiacEchoLeftVentricularDysfunction,//
            this.auxiliaryExams.acuteKidneyInjury = response.data.data.kidneyFunctionAcuteFailure,//
            this.auxiliaryExams.Congestiveheartfailure = response.data.data.preoperativeChfWithin30Days,
            this.auxiliaryExams.sepsisWithin48Hours = response.data.data.preoperativeSepsisWithin48Hours,
            this.auxiliaryExams.respiratoryDifficulties = response.data.data.respiratoryDifficulty,//
            this.auxiliaryExams.hypotensionWithin48Hours = response.data.data.hypotensionWithin48Hours,
            this.auxiliaryExams.preoperativePeritonitis = response.data.data.preoperativePeritonitis,
            
            // 心电图和实验室检查相关字段
            this.labTests.ecgAtrialPremature = response.data.data.ecgAtrialPremature,
            this.labTests.ecgVentricularPremature = response.data.data.ecgVentricularPremature,
            this.labTests.ecgAtrialFibrillation = response.data.data.ecgAtrialFibrillation,
            this.labTests.ecgBradycardia = response.data.data.ecgBradycardia,
            this.labTests.ecgConductionBlock = response.data.data.ecgConductionBlock,
            this.labTests.echoLVEF = response.data.data.echoLVEF,
            this.labTests.echoPASP = response.data.data.echoPASP,
            this.labTests.echoBNP = response.data.data.echoBNP,
            this.labTests.coronaryCTA = response.data.data.coronaryCTA,
            this.labTests.coronaryTroponin = response.data.data.coronaryTroponin,
            this.labTests.brainCT = response.data.data.brainCT,

            this.labTests.bloodGlucose = response.data.data.bloodGlucose,
            this.labTests.hba1c = response.data.data.hba1c,
            this.labTests.wbcCount = response.data.data.wbcCount,
            
            this.specialInfo = {
              supplement: response.data.data.specialSupplement || ''
            }

          //    this.isDisabled=true
          if(this.generalInfo.surgerySite == '胃'){
            this.$router.push({
            path: '/about_GastricMalignantTumor',
            query: { generalInfo: JSON.stringify(this.generalInfo),
                labTests: JSON.stringify(this.labTests),
                auxiliaryExams: JSON.stringify(this.auxiliaryExams),
                specialInfo: JSON.stringify(this.specialInfo)
             }
          });
          }
          if(this.generalInfo.surgerySite == '肝'){
            this.$router.push({
            path: '/about_TumorOfLiver',
            query: { generalInfo: JSON.stringify(this.generalInfo),
                labTests: JSON.stringify(this.labTests),
                auxiliaryExams: JSON.stringify(this.auxiliaryExams),
                specialInfo: JSON.stringify(this.specialInfo)
             }
          });
          }
          if(this.generalInfo.surgerySite == '结肠'){
            this.$router.push({
            path: '/about_ColonTumor',
            query: { generalInfo: JSON.stringify(this.generalInfo),
                labTests: JSON.stringify(this.labTests),
                auxiliaryExams: JSON.stringify(this.auxiliaryExams),
                specialInfo: JSON.stringify(this.specialInfo)
             }
          });
          }
          if(this.generalInfo.surgerySite == '胆管'){
            this.$router.push({
            path: '/about_Cholangiocarcinoma',
            query: { generalInfo: JSON.stringify(this.generalInfo),
                labTests: JSON.stringify(this.labTests),
                auxiliaryExams: JSON.stringify(this.auxiliaryExams),
                specialInfo: JSON.stringify(this.specialInfo)
             }
          });
          }
          if(this.generalInfo.surgerySite == '胆囊'){
            this.$router.push({
            path: '/about_CholecysticTumor',
            query: { generalInfo: JSON.stringify(this.generalInfo),
                labTests: JSON.stringify(this.labTests),
                auxiliaryExams: JSON.stringify(this.auxiliaryExams),
                specialInfo: JSON.stringify(this.specialInfo)
             }
          });
          }
          if(this.generalInfo.surgerySite == '直肠'){
            this.$router.push({
            path: '/about_RectalTumor',
            query: { generalInfo: JSON.stringify(this.generalInfo),
                labTests: JSON.stringify(this.labTests),
                auxiliaryExams: JSON.stringify(this.auxiliaryExams),
                specialInfo: JSON.stringify(this.specialInfo)
             }
          });
          }
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

      if (this.auxiliaryExams.chestCT == '轻微' || this.auxiliaryExams.chestCT == '轻度' || this.auxiliaryExams.chestCT == '否') {
        this.auxiliaryExams.chestCT = '否';
      } else {
        this.auxiliaryExams.chestCT = '是';
      }

      if (this.auxiliaryExams.heartEcho == '轻微' || this.auxiliaryExams.heartEcho == '轻度' || this.auxiliaryExams.heartEcho == '否') {
        this.auxiliaryExams.heartEcho = '否';
      } else {
        this.auxiliaryExams.heartEcho = '是';
      }

      if (this.auxiliaryExams.historyCOPD == '轻微' || this.auxiliaryExams.historyCOPD == '轻度' || this.auxiliaryExams.historyCOPD == '否') {
        this.auxiliaryExams.historyCOPD = '否';
      } else {
        this.auxiliaryExams.historyCOPD = '是';
      }

      if (this.auxiliaryExams.Congestiveheartfailure == '轻微' || this.auxiliaryExams.Congestiveheartfailure == '轻度' || this.auxiliaryExams.Congestiveheartfailure == '否') {
        this.auxiliaryExams.Congestiveheartfailure = '否';
      } else {
        this.auxiliaryExams.Congestiveheartfailure = '是';
      }

      if (this.auxiliaryExams.Dialysis == '轻微' || this.auxiliaryExams.Dialysis == '轻度' || this.auxiliaryExams.Dialysis == '否') {
        this.auxiliaryExams.Dialysis = '否';
      } else {
        this.auxiliaryExams.Dialysis = '是';
      }

      if (this.auxiliaryExams.AcuteRenalFailure == '轻微' || this.auxiliaryExams.AcuteRenalFailure == '轻度' || this.auxiliaryExams.AcuteRenalFailure == '否') {
        this.auxiliaryExams.AcuteRenalFailure = '否';
      } else {
        this.auxiliaryExams.AcuteRenalFailure = '是';
      }

      if (this.auxiliaryExams.respiratoryDifficulties == '轻微' || this.auxiliaryExams.respiratoryDifficulties == '轻度' || this.auxiliaryExams.respiratoryDifficulties == '否') {
        this.auxiliaryExams.respiratoryDifficulties = '否';
      } else {
        this.auxiliaryExams.respiratoryDifficulties = '是';
      }

      if (this.auxiliaryExams.sepsisWithin48Hours == '轻微' || this.auxiliaryExams.sepsisWithin48Hours == '轻度' || this.auxiliaryExams.sepsisWithin48Hours == '否') {
        this.auxiliaryExams.sepsisWithin48Hours = '否';
      } else {
        this.auxiliaryExams.sepsisWithin48Hours = '是';
      }


      request.post(`/api/predict`, {
        hospitalizationNumber: this.generalInfo.caseNumber,
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
  }
};
</script>

<style scoped>
.about-container {
  position: relative;
  width: 100%;
  min-height: 70vh;
  padding-top: 0;
}

.content-wrapper {
  position: relative;
  z-index: 1;
  width: 100%;
  margin-top: 100px;
}

.background-image {
  position: absolute;
  top: -400px;
  left: -50px;
  width: 104.4%;
  height: 600px;
  background-image: url('../assets/images/5.jpg');
  background-size: cover;
  background-position: left;
  background-repeat: no-repeat;
  opacity: 1;
  z-index: 0;
  pointer-events: none;
}

.search-form .el-form-item {
  margin-right: 30px;
  margin-bottom: 15px;
}

.range-item {
  margin-right: 15px;
}

.range-input {
  display: flex;
  align-items: center;
 
}

.range-input .el-input {
  width: 80px;
}

.range-separator {
  margin: 0 5px;
}

.action-button {
  padding: 9px 16px;
  border-radius: 4px;
  font-size: 13px;
  height: 40px;
  min-width: 70px;
  margin-left: 10px;
}

.query-button, .reset-button {
  background-color: #1e5bb0;
  border-color: #1e5bb0;
}

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
  height: 100%;
  overflow: auto;
  margin: 0 auto;
  margin-top: 10px;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: rgba(255, 255, 255, 0.95);
  position: relative;
  z-index: 2;
  transition: margin-top 0.3s;
}

.form-container.collapsed-mode {
  margin-top: 0;
}

.block-container {
  height: 20px;
  transition: height 0.3s;
}

.form-container0 {
  width: 95%;
  height: 90%;
  overflow: auto;
  margin: 0 auto;
  margin-top: 50px;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: rgba(255, 255, 255, 1);
  position: relative;
  z-index: 2;
}

.el-table {
  font-size: 12px;
}

.el-table th {
  font-size: 12px;
  padding: 6px 0;
}

.el-table td {
  padding: 4px 0;
}

h3 {
  margin-top: 20px;
}

.item-actions button {
  margin-left: 5px;
}

.pager {
  margin-top: 15px;
  display: flex;
  justify-content: flex-end;
  width: 100%;
}

/* 自定义分页组件中的文本 */
.pager >>> .el-pagination__jump {
  margin-left: 0;
}
.pager >>> .el-pagination__jump::before {
  content: '';
  margin-right: 0;
}
.pager >>> .el-pagination__jump .el-pagination__editor {
  margin: 0;
}
.pager >>> .el-pagination__jump span:first-child {
  display: inline;
}

.collapse-button {
  width: 97.5%;
  margin: 0 auto;
  padding: 8px 0;
  text-align: center;
  background-color: #f5f7fa;
  border-radius: 4px;
  cursor: pointer;
  user-select: none;
  color: #606266;
  font-size: 13px;
  transition: all 0.3s;
  position: relative;
  z-index: 2;
  box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.1);
}

.collapse-button:hover {
  background-color: #e4e7ed;
}

.collapse-button i {
  margin-right: 5px;
  transition: transform 0.3s;
}

.collapse-button .is-collapsed {
  transform: rotate(180deg);
}
</style>