<template>
    <el-row type="flex" justify="center">
        <el-col :span="10">
            <el-card></el-card>
            <!-- user卡片 -->
            <el-card>
                <div class="user">
                    <img src="../assets/images/user.png" alt="">
                    <div class="userInfo">
                        <p div class="name">{{ userInfo.loginName }}</p>
                        <p div class="access">{{ userType }}</p>
                        <p div class="access">{{ userInfo.deptName }}</p>
                    </div>
                </div>
                <div>
                    <p class="larger-text">用户名(Username)：<span class="centered-text0">{{ userInfo.userName }}</span></p>
                    <p class="larger-text">您的邮箱(Email)：<span class="centered-text1">{{ userInfo.email }}</span></p>
                    <p class="larger-text">您的手机(Phone)：<span class="centered-text2">{{ userInfo.phoneNum }}</span></p>
                </div>
<!--                <div class="block-container"></div>-->
                <div>
                    <el-button type="primary" @click="handleEdit" size="small" style="margin-left: 200px;margin-top: 30px;">修改密码<br>
                        <span class="english-button-text">Change Password</span></el-button>
                </div>
                    <el-dialog
                    :title="'修改密码(Change Password)'"
                    :visible.sync="dialogVisible"
                    width="35%"
                    :before-close="closeDialog"
                    :modal="false"
                >
                <el-form :inline="true" :model="form" :rules="rules" ref="form" label-width="220px">
                    <el-form-item :label="'原密码(Current Password)'" prop="prepassword">
                    <el-input
                        type="password"
                        :placeholder="'请输入原密码(Enter current password)'"
                        v-model="form.prepassword" style="width: 300px;"
                    ></el-input>
                    </el-form-item>
                    <el-form-item :label="'新密码(New Password)'" prop="password">
                    <el-input
                        type="password"
                        :placeholder="'请输入新密码(Enter new password)'"
                        v-model="form.password" style="width: 300px;"
                    ></el-input>
                    </el-form-item>
                    <el-form-item :label="'确认密码(Confirm Password)'" prop="confirmPassword">
                    <el-input
                        type="password"
                        :placeholder="'请再次输入密码(Re-enter password)'"
                        v-model="form.confirmPassword" style="width: 300px;"
                    ></el-input>
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="closeDialog">取 消(Cancel)</el-button>
                    <el-button type="primary" @click="handleupdate">确 定(Confirm)</el-button>
                </div>
                </el-dialog>
            </el-card>
            <!-- 功能卡片 -->
            <el-card></el-card>
            <div class="block-container1"></div>
        </el-col>
    </el-row>
</template>

<script>
import request from '@/utils/request';
import Cookie from 'js-cookie';
export default {
    data() {
        return {
            dialogVisible: false,
            userInfo: {},
            userType: '',
            form: {
                prepassword: '',
                password: '',
                confirmPassword: '',
            },
            rules: {
                prepassword: [
                    { required: true, message: '请输入原密码', trigger: 'blur' },
                    { validator: this.validatePrepassword, trigger: 'blur' }
                ],
                password: [
                    { required: true, message: '请输入新密码', trigger: 'blur' }
                ],
                confirmPassword: [
                    { required: true, message: '请再次输入密码', trigger: 'blur' },
                    { validator: this.validateConfirmPassword, trigger: 'blur' }
                ],
            }
        }
    },
    created() {
        // form记录userinfo
        //this.userInfo = this.$store.state.tab.userInfo
        this.userInfo = JSON.parse(Cookie.get('userInfo'));
        const tempuserType = this.userInfo.userType
        console.log('Cookies');
        console.log(this.userInfo);

        // const tea = JSON.parse(Cookie.get('userInfo'));
        // console.log(tea);

        if (tempuserType === 'admin') {
            this.userType = '部门管理员'
        } else if (tempuserType === 'normal') {
            this.userType = '普通用户'
        } else {
            this.userType = '超级管理员'
        }
    },
    methods: {
        validatePrepassword(rule, value, callback) {
            if (value !== this.userInfo.prepassword) {
                callback(new Error('原密码输入不正确'));
            } else {
                callback();
            }
        },
        validateConfirmPassword(rule, value, callback) {
            if (value !== this.form.password) {
                callback(new Error('两次输入的密码不一致'));
            } else {
                callback();
            }
        },
        // 修改密码
        handleupdate() {
            // 构建提交的数据对象
            const submitData = {
                userId: this.userInfo.userId,
                userName: this.userInfo.userName,
                loginName: this.userInfo.loginName,
                password: this.form.password,
                deptName: this.userInfo.department,
                email: this.userInfo.email,
                phoneNum: this.userInfo.phoneNum,
                userType: this.userInfo.userType,
                status: this.userInfo.status,
                create_time: this.userInfo.create_time,
                update_time: this.userInfo.update_time,
                create_by: this.userInfo.create_by,
                update_by: this.userInfo.update_by
            };
            const flag = 1;

            console.log(`提交的数据：${JSON.stringify(submitData)}`);
            request.put(`/user?flag=${flag}`, submitData)
                .then((response) => {
                    console.log(response);
                    if (response.data.code === 1) {
                        this.$message.success('用户信息更新成功');
                        this.closeDialog();
                    } else {
                        this.$message.error(`更新失败: ${response.data.msg}`);
                    }
                })
                .catch(error => {
                    console.error(error);
                    alert("请求失败");
                    this.closeForm();
                })
        },
        // 编辑按钮
        handleEdit() {
            this.dialogVisible = true;
        },
        closeDialog() {
            this.dialogVisible = false;
        },
    }
}

</script>

<style lang="less" scoped>
.user {
    // 垂直居中
    display: flex;
    align-items: center;

    // 外边距:分割线距离loginInfo的距离
    margin-bottom: 20px;
    // 内边距:分割线距离User的距离
    padding-bottom: 20px;
    border-bottom: 1px solid #ccc;

    img {
        width: 150px;
        height: 150px;
        border-radius: 50%;
        margin-right: 40px;
    }

    .userInfo {
        .name {
            font-size: 32px;
            margin-bottom: 10px;
        }

        .access {
            color: #999999;
        }
    }
}

.loginInfo {
    p {
        line-height: 28px;
        font-size: 14px;
        color: #999999;

        span {
            color: #666666;
            margin-left: 60px;
        }
    }
}

.num {
    display: flex;
    // 要换行
    flex-wrap: wrap;
    // 从头到尾均匀排列
    justify-content: space-between;
    margin-left: 20px;

    .el-card {
        width: 32%;
        margin-bottom: 20px;

        .icon {
            width: 80px;
            height: 80px;
            line-height: 80px;
            text-align: center;
            font-size: 30px;
            color: #fff;
        }

        .details {
            // 竖着排且居中
            display: flex;
            flex-direction: column;
            justify-content: center;

            margin-left: 15px;

            .price {
                font-size: 30px;
                margin-bottom: 10px;
                line-height: 30px;
                height: 30px;
            }

            .desc {
                font-size: 14px;
                color: #999;
                text-align: center;
            }
        }
    }
}

.graph {
    display: flex;
    // 两个靠边
    justify-content: space-between;
    margin-top: 20px;

    .el-card {
        width: 49%;
    }
}

.larger-text {
    font-size: 18px;
    /* 根据需要调整字体大小 */
    padding: 5px 10px; /* 内边距 */
}



.centered-text0 {
    display: inline-block;
    /* 使 span 元素成为块级元素，以应用文本对齐 */
    margin-left: 20px;
    /* 使用文本对齐 */
    width: 60%;
}

.centered-text1 {
    display: inline-block;
    /* 使 span 元素成为块级元素，以应用文本对齐 */
    margin-left: 42px;
    /* 使用文本对齐 */
    width: 60%;
}

.centered-text2 {
    display: inline-block;
    /* 使 span 元素成为块级元素，以应用文本对齐 */
    margin-left: 33px;
    /* 使用文本对齐 */
    width: 60%;
}

.block-container {
    height: 20px;
}

.block-container1 {
    height: 300px;
}
</style>