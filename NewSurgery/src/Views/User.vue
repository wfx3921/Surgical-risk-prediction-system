<template>
  <div class="manage">
    <div class="manage-header">
      <el-form style="width: 220px;">
        <el-form-item>
          <!-- 新增按钮 -->
          <el-button type="success" @click="handlecreate">
            + 新增<br><span class="english-btn-text">Create</span>
          </el-button>
          <el-button type="danger" @click="moreDelete">
            × 删除<br><span class="english-btn-text">Delete</span>
          </el-button>
        </el-form-item>
      </el-form>

      <!-- 新建对话框 -->
      <el-dialog
          :title="'新建用户(Create User)'"
          :visible.sync="dialogVisible"
          width="60%"
          :before-close="closeDialog"
          :modal="false"
      >
        <el-form :inline="true" :model="form" :rules="rules" ref="form" label-width="200px">
          <el-form-item :label="'用户名称(User Name)'" prop="userName" style="margin-bottom: 50px;" >
            <el-input
                :placeholder="'请输入用户名称(Enter user name)'"
                style="width: 250px"
                v-model="form.userName"
            ></el-input>
          </el-form-item>
          <el-form-item :label="'登录名称(Login Name)'" prop="loginName" style="margin-bottom: 50px;">
            <el-input
                :placeholder="'请输入登录名称(Enter login name)'"
                style="width: 250px"
                v-model="form.loginName"
            ></el-input>
          </el-form-item>
          <el-form-item :label="'登录密码(Password)'" prop="password" style="margin-bottom: 50px;">
            <el-input
                type="text"
                :placeholder="'请输入密码(Enter password)'"
                style="width: 250px"
                v-model="form.password"
            ></el-input>
          </el-form-item>
          <el-form-item :label="'邮箱账号(Email)'" prop="email" style="margin-bottom: 50px;">
            <el-input
                :placeholder="'请输入邮箱(Enter email)'"
                style="width: 250px"
                v-model="form.email"
            ></el-input>
          </el-form-item>
          <el-form-item :label="'手机号码(Phone)'" prop="phoneNum" style="margin-bottom: 50px;">
            <el-input
                :placeholder="'请输入手机号(Enter phone number)'"
                style="width: 250px"
                v-model="form.phoneNum"
            ></el-input>
          </el-form-item>
          <el-form-item :label="'用户状态(Status)'" prop="status" style="margin-bottom: 50px;">
            <el-switch
                v-model="form.status"
                active-value="active"
                inactive-value="inactive"
                active-color="#13ce66"
                inactive-color="#ff4949"
            ></el-switch>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="closeDialog">取 消(Cancel)</el-button>
          <el-button type="primary" @click="submit">确 定(Confirm)</el-button>
        </div>
      </el-dialog>

      <!-- 编辑对话框 -->
      <el-dialog
          :title="'编辑用户(Edit User)'"
          :visible.sync="dialogVisible_edit"
          width="55%"
          :before-close="closeDialog"
          :modal="false"
      >
        <el-form :inline="true" :model="form" :rules="rules" ref="form" label-width="180px">
          <el-form-item :label="'用户名称(User Name)'" prop="userName" style="margin-bottom: 50px;">
            <el-input
                :placeholder="'请输入用户名称(Enter user name)'"
                v-model="form.userName"
            ></el-input>
          </el-form-item>
          <el-form-item :label="'登录密码(Password)'" prop="password" style="margin-bottom: 50px;">
            <el-input
                type="text"
                :placeholder="'请输入密码(Enter password)'"
                v-model="form.password"
            ></el-input>
          </el-form-item>
          <el-form-item :label="'邮箱账号(Email)'" prop="email" style="margin-bottom: 50px;">
            <el-input
                :placeholder="'请输入邮箱(Enter email)'"
                v-model="form.email"
            ></el-input>
          </el-form-item>
          <el-form-item :label="'手机号码(Phone)'" prop="phoneNum" style="margin-bottom: 50px;">
            <el-input
                :placeholder="'请输入手机号(Enter phone number)'"
                v-model="form.phoneNum"
            ></el-input>
          </el-form-item>
          <el-form-item :label="'用户状态(Status)'" prop="status" style="margin-bottom: 50px;">
            <el-switch
                v-model="form.status"
                active-value="active"
                inactive-value="inactive"
                active-color="#13ce66"
                inactive-color="#ff4949"
            ></el-switch>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="closeDialog">取 消(Cancel)</el-button>
          <el-button type="primary" @click="submit">确 定(Confirm)</el-button>
        </div>
      </el-dialog>

      <!-- 搜索框 -->
      <el-form :inline="true" style="margin-left:100px;margin-right: 0px">
        <el-form-item>
          <el-input
              v-model="searchForm.loginName"
              :placeholder="'登录名称(Login name)'"
              style="width: 170px"
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-input
              v-model="searchForm.phoneNum"
              :placeholder="'手机号(Phone number)'"
              style="width: 180px"
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-select
              v-model="searchForm.status"
              :placeholder="'请选择状态(Status)'"
              style="width: 120px"
          >
            <el-option :label="'全部(All)'" value=""></el-option>
            <el-option :label="'在线(Active)'" value="active"></el-option>
            <el-option :label="'离线(Inactive)'" value="inactive"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-date-picker
              type="datetime"
              :placeholder="'开始日期(Start date)'"
              v-model="searchForm.createTimeStart"
              value-format="yyyy-MM-dd HH:mm:ss"
              style="width: 190px"
          ></el-date-picker>
        </el-form-item>
        <el-form-item>
          <el-date-picker
              type="datetime"
              :placeholder="'结束日期(End date)'"
              v-model="searchForm.createTimeEnd"
              value-format="yyyy-MM-dd HH:mm:ss"
              style="width: 190px"
          ></el-date-picker>
        </el-form-item>
      </el-form>
      <el-form style="margin-left:0px;margin-right: 40px">
        <el-form-item style="margin-left:0px">
          <el-button icon='el-icon-search' type="primary" @click="search(1, size)">
            查询<br><span class="english-btn-text">Search</span>
          </el-button>
        </el-form-item>
      </el-form>
    </div>

    <!-- 表格 -->
    <div class="common-table">
      <el-table :data="filteredItems" stripe style="width: 100%" height="90%" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55"></el-table-column>

        <el-table-column prop="userName" width="150" align="center">
          <template slot="header">
            <span>用户名称<br><span class="english-header">User Name</span></span>
          </template>
        </el-table-column>

        <el-table-column prop="loginName" width="150" align="center">
          <template slot="header">
            <span>登录名称<br><span class="english-header">Login Name</span></span>
          </template>
        </el-table-column>

        <el-table-column prop="fakepassword" width="150" align="center">
          <template slot="header">
            <span>密码<br><span class="english-header">Password</span></span>
          </template>
        </el-table-column>

        <el-table-column prop="email" width="150" align="center">
          <template slot="header">
            <span>邮箱<br><span class="english-header">Email</span></span>
          </template>
        </el-table-column>

        <el-table-column prop="phoneNum" width="150" align="center">
          <template slot="header">
            <span>手机<br><span class="english-header">Phone</span></span>
          </template>
        </el-table-column>

        <el-table-column width="150" align="center">
          <template slot="header">
            <span>用户状态<br><span class="english-header">Status</span></span>
          </template>
          <template slot-scope="scope">
            <el-switch
                v-model="scope.row.status"
                active-value="active"
                inactive-value="inactive"
                active-color="#13ce66"
                inactive-color="#ff4949"
                disabled
            ></el-switch>
          </template>
        </el-table-column>

        <el-table-column prop="create_time" width="180" align="center">
          <template slot="header">
            <span>创建时间<br><span class="english-header">Create Time</span></span>
          </template>
        </el-table-column>

        <el-table-column label="操作">
          <template slot="header">
            <span>操作<br><span class="english-header">Actions</span></span>
          </template>
          <template slot-scope="scope">
            <el-button @click="handleEdit(scope.row)">
              编辑<br><span style="color: black; margin-top: 2px" class="english-btn-text">Edit</span>
            </el-button>
            <el-button type="danger" @click="handleDelete(scope.row)">
              删除<br><span style="margin-top: 2px" class="english-btn-text">Delete</span>
            </el-button>
          </template>
        </el-table-column>
      </el-table>

      <!-- 分页部分保持不变 -->
      <div class="pagination-container">
        <span>
          显示第 {{ (currentPage - 1) * size + 1 }} 条到第 {{ currentPage * size > total ? total : currentPage * size }} 条记录，
          共{{ total }} 条记录，
          <input
              type="number"
              v-model="size"
              @input="updatePageSize"
              :placeholder="'每页 / Page'"
              style="width: 70px;"
          />
        </span>
        <el-pagination
            layout="prev, pager, next"
            :total="total"
            :current-page.sync="currentPage"
            :page-size="size"
            @current-change="search(currentPage, size)"
            @prev-click="prevPage"
            @next-click="nextPage"
            class="pager"
        ></el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
import request from '@/utils/request'
import Cookies from 'js-cookie';
export default {
  data() {
    return {
      // filteredItems: [{
      //   userId: 1,
      //   userName: 'John Doe',
      //   loginName: 'jdoe4',
      //   password: '123456',
      //   deptName: 'Engineering',
      //   email: 'jdoe@example.com',
      //   phoneNum: '123-456-7890',
      //   userType: 'user.userType',
      //   status: 'active',
      //   create_time: null,
      //   update_time: null,
      //   create_by: null,
      //   update_by: null,
      //   fakepassword: '********',
      // },],

      // *******************//

      // 新增表单   绑定的数据deptName，userType
      form: {
        userName: '',
        loginName: '',
        password: '',
        deptName: '',
        email: '',
        phoneNum: '',
        userType: '',
        status: '',
        createTime: null,
        createBy: null,
        updateTime: null,
        updateBy: null
      },
      // 编辑表单 ，即filteredItems中元素
      updateform: {
        // userId: 1,
        // userName: '',
        // loginName: '',
        // password: '',
        // deptName: '',
        // email: '',
        // phoneNum: '',
        // userType: '',
        // status: '',
        // create_time: null,
        // update_time: null,
        // create_by: null,
        // update_by: null,
        //  fakepassword: '********',
      },
      // 表单验证规则
      rules: {
        userName: [{
          required: true,
          message: '请输入用户名称 / User name is required',
          trigger: 'blur'
        }],
        loginName: [{
          required: true,
          message: '请输入登录名称 / Login name is required',
          trigger: 'blur'
        }],
        password: [{
          required: true,
          message: '请输入密码 / Password is required',
          trigger: 'blur'
        }],
        email: [
          { required: false },
          {
            type: 'email',
            message: '请输入有效的邮箱地址 / Please enter a valid email',
            trigger: 'blur'
          }
        ],
        phoneNum: [
          { required: false },
          {
            pattern: /^1[3456789]\d{9}$/,
            message: '请输入有效的手机号码 / Please enter a valid phone number',
            trigger: 'blur'
          }
        ],
      },
      // 表单是否打开
      dialogVisible: false,
      // 表单是否打开
      dialogVisible_edit: false,
      // 打开表单:新建0,编辑1
      modalType: 0,

      // 搜索框表单
      searchForm: {
        loginName: '',
        phoneNum: '',
        status: '',
        createTimeStart: '',
        createTimeEnd: '',
        deptName: '',
        userType: 'admin'
      },

      // 过滤后的列表数据
      filteredItems: [],

      total: 0,
      size: 10,
      currentPage: 1,
      pages: 0,

      // 选中的行
      checkedItems: {},
    }
  },
  created() {
    // form记录userinfo
    this.form.deptName = this.$store.state.tab.userInfo.deptName;
    this.searchForm.deptName = this.$store.state.tab.userInfo.deptName;
    this.form.userType = 'normal';
    this.searchForm.deptName = Cookies.get('deptName');
    this.search(1, this.size);
  },
  methods: {
    //检测版
    search(page, pageSize) {
      request.post(`/user/getlist?page=${page}&pageSize=${pageSize}`, this.searchForm)

        .then(response => {

          if (response.data.code === 1) {
            const items = response.data.data.records.map(user => {
              return {
                userId: user.userId,
                userName: user.userName,
                loginName: user.loginName,
                password: user.password,
                deptName: user.deptName,
                email: user.email,
                phoneNum: user.phoneNum,
                userType: user.userType,
                status: user.status,
                create_time: user.create_time,
                create_by: user.create_by,
                update_time: user.update_time,
                update_by: user.update_by,
                fakepassword: '********',
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

    // 将日期字符串转换为时间戳
    dateToTimestamp(dateStr) {
      if (!dateStr) return null;
      const date = new Date(dateStr);
      return date.getTime(); // 返回毫秒时间戳
    },

    // 记录所选行的 userId
    handleSelectionChange(selection) {
      this.checkedItems = {}; // 清空之前的选择
      selection.forEach(item => {
        this.checkedItems[item.userId] = true; // 记录所选行的 userId
        //console.log("dl");
        //console.log(this.checkedItems);
      });
    },

    //多项删除
    moreDelete() {
      this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        const filteredIds = Object.keys(this.checkedItems).filter(id => this.checkedItems[id] === true)
        console.log(`确定删除选中的${filteredIds.length}项用户吗？`);//
        console.log(`确定删除选中的${filteredIds}吗？`);//
        const deletePromises = filteredIds.map(id => {
          return request.delete(`/user/${id}`)
            .then(response => {
              if (response.data.code === 0) {
                throw new Error(`Failed to delete user with id ${id}`);
              } else {
                console.log(`Deleted user with id ${id}`);
              }
            });
        });
        Promise.all(deletePromises)
          .then(() => {
            this.$message({
              type: 'success',
              message: '删除成功!'
            });
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

    // 删除
    handleDelete(index) {
      this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        // 删除操作:根据后端接口,参数是对象,id是唯一标识符
        request.delete(`/user/${index.userId}`)
          .then(response => {
            if (response.data.code === 0) {
              throw new Error(`Failed to delete user with id ${id}`);
            } else {
              this.$message({
                type: 'success',
                message: '删除成功!'
              })
              this.updatePageSize();
            }
          });
      }).catch(() => {
        // 点击取消:不删除了
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
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

    // 表单提交
    submit() {
      // 要用箭头函数,若用function会报错,不知道为什么
      this.$refs.form.validate((valid) => {
        // 符合校验
        if (valid) {
          // 提交数据
          if (this.modalType === 0) {
            // 新增
            request.post("/user", this.form)
              .then(response => {
                console.log(response);
                const responsedatacode = response.data.code;
                console.log(responsedatacode);
                if (responsedatacode === 1) {
                  console.log("用户新增成功");
                  this.$message({
                    message: "用户新增成功",
                    type: 'success'
                  });
                  // 刷新页面
                  this.search(this.currentPage, this.size);
                } else {
                  console.error(`更新失败: ${response.data.msg}`);
                  this.$message.error(`用户新增失败: ${response.data.msg}`);
                }
              })
              .catch(error => {
                if (error.response) {
                  alert(`服务器错误: ${error.response.status}`);
                } else if (error.request) {
                  alert('网络错误，请检查网络连接。');
                } else {
                  alert('请求设置错误，请重试。');
                }
              });
          } else {
            // 编辑
            this.submitForm();
            
          }
          // 清空,关闭
          this.closeDialog()
        }
      })
    },

    submitForm() {
      // 构建提交的数据对象
      const submitData = {
        userId: this.updateform.userId,
        userName: this.form.userName,
        loginName: this.form.loginName,
        password: this.form.password,
        deptName: this.updateform.department,
        email: this.form.email,
        phoneNum: this.form.phoneNum,
        userType: this.updateform.userType,
        status: this.form.status,
        create_time: this.updateform.create_time,
        update_time: this.updateform.update_time,
        create_by: this.updateform.create_by,
        update_by: this.updateform.update_by,
      };
      let flag = 1;
      if (submitData.password === this.updateform.password) {
        flag = 0;
      }
      console.log(`提交的数据：${JSON.stringify(submitData)}`);
      request.put(`/user?flag=${flag}`, submitData)
        .then((response) => {
          console.log(response);
          if (response.data.code === 1) {
            this.$message.success('用户信息更新成功');
            this.search(this.currentPage, this.size);
          } else {
            this.$message.error(`更新失败: ${response.data.msg}`);
          }
        })
        .catch(error => {
          console.error(error);
          alert("请求失败,请重新选择");
          this.closeForm();
        })
    },

    // 关闭对话框
    closeDialog() {
      // 先重置
      this.$refs.form.resetFields()
      // 后关闭
      this.dialogVisible = false
      this.dialogVisible_edit = false
    },
    // 编辑按钮
    handleEdit(index) {
      this.modalType = 1
      this.openForm_edit()
      // 深拷贝
      this.updateform = JSON.parse(JSON.stringify(index))
      this.form = {
        userName: this.updateform.userName,
        loginName: this.updateform.loginName,
        password: "********",//
        deptName: this.updateform.deptName,
        email: this.updateform.email,
        phoneNum: this.updateform.phoneNum,
        userType: this.updateform.userType,
        status: this.updateform.status,
        create_time: this.updateform.create_time,
        create_by: this.updateform.create_by,
        update_time: this.updateform.update_time,
        update_by: this.updateform.update_by,
      }
      //console.log('this.form');
      //console.log(this.updateform);
    },

    // 新建按钮
    handlecreate() {
      this.modalType = 0
      this.openForm()
    },
    // 打开表单
    openForm() {
      this.dialogVisible = true
    },
    // 打开表单
    openForm_edit() {
      this.dialogVisible_edit = true
    },
  },

}
</script>

<style lang="less" scoped>
.manage {
  height: 820px;

  .manage-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }

  .common-table {
    height: 95%;
    position: relative;

    .pager {
      position: absolute;
      right: 20px;
      // bottom: 0;
    }
  }
  .pagination-container {
    display: flex;
    justify-content: space-between; /* 或者使用 'flex-start' 根据需要调整 */
    align-items: center; /* 垂直居中对齐 */
    margin-top: 10px; /* 可选，增加顶部间距 */
  }
}
.english-header {
  font-size: 12px;
  color: #666;
  font-weight: normal;
}

.english-btn-text {
  font-size: 10px;
  color: rgba(255,255,255,0.8);
  display: block;
  line-height: 1.2;
}

.dialog-footer .el-button span {
  &::after {
    content: "/";
    margin: 0 4px;
    color: #999;
  }
  &:last-child::after {
    content: none;
  }
}

</style>