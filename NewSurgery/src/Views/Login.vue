<template>
    <div class="login-container">
        <!-- 添加互动动态壁纸 -->

        <!-- <InteractiveWallpaper /> -->
      <el-radio-group v-model="login.userType" style="margin-bottom: 20px; margin-left: 1500px;margin-top: 10px;">
        <el-radio-button label="normal">普通用户登录</el-radio-button>
        <el-radio-button label="admin">管理员用户登录</el-radio-button>
      </el-radio-group>
        <!-- 登录表单 -->
        <el-form ref="form" class="login_container" :model="login" status-icon :rules="rules" label-width="150px">
          <!-- 新增：用户类型切换 Radio -->

            <h3 class="login_title">TAHQU-PRPS平台</h3>
            <el-form-item label="用户名(Username)" prop="username">
                <el-input v-model="login.username" autocomplete="off">
                    <i slot="suffix" class="el-input__icon el-icon-user"></i>
                </el-input>
            </el-form-item>

            <el-form-item label="密码(Password)" prop="password">
                <el-input type="password" v-model="login.password" autocomplete="off">
                    <i slot="suffix" class="el-input__icon el-icon-lock"></i>
                </el-input>
            </el-form-item>

            <el-button @click="submit" type="success" 
                :style="{ 
                    marginTop: '10px', 
                    marginLeft: login.userType === 'admin' ? '60px' : '90px' 
                }" 
                :type="login.userType === 'admin' ? 'danger' : 'success'">
                {{ login.userType === 'admin' ? '管理员登录' : '登录' }}<br>
                <span class="english-btn-text">Login</span>
            </el-button>
            <el-button @click="openForgotPasswordDialog" type="primary"
                style="margin-left:10px;margin-top:10px">忘记密码<br><span class="english-btn-text">Forget Password</span></el-button>

            <el-dialog :visible.sync="forgotPasswordDialogVisible" title="忘记密码(Forget Password)" :modal="false">
                <p>请输入您的邮箱以接收重置密码的链接(Please enter your email to receive the reset password link):</p>
                <el-input v-model="forgotPasswordEmail" autocomplete="off"></el-input>
                <span slot="footer" class="dialog-footer">
                    <el-button @click="forgotPasswordDialogVisible = false">取消(Cancel)</el-button>
                    <el-button type="primary" @click="sendForgotPasswordEmail">发送(Send)</el-button>
                </span>
            </el-dialog>
        </el-form>
    </div>
</template>

<script>
import InteractiveWallpaper from '@/components/InteractiveWallpaper.vue';
import Cookies from 'js-cookie';
import request from '@/utils/request';

export default {
    data() {
        return {
            login: {
                username: '',
                password: '',
                userType: 'normal' // 默认是普通用户
            },
            rules: {
                username: [{ required: 'true', message: '请输入用户名', trigger: 'blur' }],
                password: [{ required: 'true', message: '请输入密码', trigger: 'blur' }]
            },
            forgotPasswordDialogVisible: false,
            forgotPasswordEmail: ''
        };
    },
    created() {
        console.log("resetTabList");
        this.$store.dispatch('resetTabList');
    },
    methods: {
        submit() {
            const request_data = {
                userName: "",
                loginName: this.login.username,
                password: this.login.password,
                deptName: "",
                email: "",
                phoneNum: "",
                userType: "",
                status: "",
                create_time: null,
                update_time: null,
                create_by: null,
                update_by: null
            };

            this.$refs.form.validate((valid) => {
                if (valid) {
                    request.post("/login", request_data)
                        .then(response => {
                            const userType = response.data.data.userType;
                            const status = response.data.data.status;

                            if (status === "active") {
                              if (this.login.userType !== userType) {
                                alert(`请使用${userType === 'admin' ? '管理员' : '普通用户'}身份登录`);
                                return; // 不匹配直接返回，不执行后续登录逻辑
                              }
                                if (userType === "admin") {
                                    Cookies.set('deptName', response.data.data.deptName);
                                    Cookies.set('token', 'admin-logged-in');
                                    localStorage.setItem('token', 'admin-logged-in');
                                    this.$store.commit('setMenu', [
                                        {
                                            path: '/home',
                                            name: 'home',
                                            label: '首页',
                                            icon: 's-home',
                                            url: 'Home.vue'
                                        },
                                        {
                                            path: '/user',
                                            name: 'user',
                                            label: '用户管理',
                                            icon: 'user',
                                            url: 'User.vue'
                                        },
                                    ]);
                                    this.$store.commit('addMenu', this.$router);
                                    this.$store.commit('setUserInfo', {
                                        userId: response.data.data.userId,
                                        userName: response.data.data.userName,
                                        loginName: response.data.data.loginName,
                                        password: response.data.data.password,
                                        deptName: response.data.data.deptName,
                                        email: response.data.data.email,
                                        phoneNum: response.data.data.phoneNum,
                                        userType: response.data.data.userType,
                                        status: response.data.data.status,
                                        create_time: response.data.data.create_time,
                                        update_time: response.data.data.update_time,
                                        create_by: response.data.data.create_by,
                                        update_by: response.data.data.update_by,
                                        prepassword: request_data.password
                                    });
                                    this.$router.push('/User');
                                } else {
                                    Cookies.set('token', 'user-logged-in');
                                    localStorage.setItem('token', 'user-logged-in');
                                    this.$store.commit('setMenu', [
                                        {
                                            path: '/home',
                                            name: 'home',
                                            label: '首页',
                                            icon: 's-home',
                                            url: 'Home.vue'
                                        },
                                        {
                                            path: '/about',
                                            name: 'about',
                                            label: '风险评估',
                                            icon: 'video-play',
                                            url: 'about.vue'
                                        },
                                    ]);
                                    this.$store.commit('addMenu', this.$router);
                                    this.$store.commit('setUserInfo', {
                                        userId: response.data.data.userId,
                                        userName: response.data.data.userName,
                                        loginName: response.data.data.loginName,
                                        password: response.data.data.password,
                                        deptName: response.data.data.deptName,
                                        email: response.data.data.email,
                                        phoneNum: response.data.data.phoneNum,
                                        userType: response.data.data.userType,
                                        status: response.data.data.status,
                                        create_time: response.data.data.create_time,
                                        update_time: response.data.data.update_time,
                                        create_by: response.data.data.create_by,
                                        update_by: response.data.data.update_by,
                                        prepassword: request_data.password
                                    });
                                    this.$router.push('/about');
                                }
                            } else {
                                alert("账号已被禁用，请联系管理员");
                            }
                        })
                        .catch(error => {
                            console.error("There was an error!", error);
                            alert("登录失败，请检查账户和密码");
                        });
                }
            });
        },
        openForgotPasswordDialog() {
            this.forgotPasswordDialogVisible = true;
        },
        sendForgotPasswordEmail() {
            if (!this.forgotPasswordEmail) {
                alert('请输入邮箱');
                return;
            }
            alert('重置密码的链接已发送到您的邮箱');
            this.forgotPasswordDialogVisible = false;
        },
    },
    components: {
        InteractiveWallpaper,
    },
};
</script>

<style lang="less" scoped>
.login-container {
    background: url('@/assets/images/login.jpg') no-repeat center center; /* 使用正确的路径和语法 */
    background-size: cover;
    position: relative;
    width: 100%;
    height: 100vh;
    overflow: hidden;
}

.interactive-wallpaper {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    z-index: -1;
    /* 确保Canvas位于背景层 */
}

.english-btn-text {
    font-size: 10px;
    color: rgba(255,255,255,0.8);
    display: block;
    line-height: 1.2;
  }
//.login_container {
//  width: 350px;
//  border: 1px solid #eaeaea;
//
//  margin: 180px auto;
//  padding: 35px 35px 15px 35px;
//  box-sizing: border-box;
//
//  border-radius: 15px;
//  background-color: #fff;
//  box-shadow: 0 0 25px #cac6c6;
//  position: relative; /* 保证表单在Canvas之上 */
//  z-index: 1; /* 确保表单位于Canvas之上 */
//
//  .login_title {
//    color: #505458;
//    text-align: center;
//    margin-bottom: 40px;
//  }
//
//  .el-input {
//    width: 198px;
//  }
//}

.login_container {
    width: 430px;
    border: 1px solid #eaeaea;

    margin: 180px auto;
    padding: 35px 35px 15px 35px;
    box-sizing: border-box;

    border-radius: 15px;
    background-color: #f5f6f6;
    //transparent; /* 设置背景颜色为透明 */
    box-shadow: 0 0 25px #cac6c6;
    position: relative;
    /* 保证表单在Canvas之上 */
    z-index: 1;
    /* 确保表单位于Canvas之上 */

    .login_title {
        color: #000000;
        text-align: center;
        margin-bottom: 40px;
    }

    .el-input {
        width: 198px;
    }
}
</style>



<!-- -->