<template>
    <div class="header-container">
        <div class="l-content">
            <el-button @click="handleMenu" icon="el-icon-menu" size="mini"></el-button>
            <!-- 面包屑 -->
            <!-- <el-breadcrumb separator="/">
                <el-breadcrumb-item v-for="item in tags" :key="item.path" :to="{ path: item.path }">{{ item.label }}
                </el-breadcrumb-item>
            </el-breadcrumb> -->
        </div>

        <div class="user-name-container">
            <span class="user-name-container">
                <i class="el-icon-user-solid user-icon"></i>
                <span class="user-name" style="color: #f0f0f0;">{{ userName }}</span>
            </span>
            <el-dropdown @command="handleClick">
                <span class="el-dropdown-link">
                    <img class="user" src="../assets/images/user.png" alt="">
                </span>
                <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item command="ClicktoPersonal">个人信息</el-dropdown-item>
                    <el-dropdown-item command="logout">退出</el-dropdown-item>
                </el-dropdown-menu>
            </el-dropdown>
        </div>
    </div>
</template>

<script>
import { mapState } from 'vuex'
import Cookie from 'js-cookie'
export default {
    data() {
        return {
            userName: '',
        }
    },
    created() {
        const userInfo = JSON.parse(Cookie.get('userInfo'));
        this.userName = userInfo.userName
        this.$store.commit('CollapseMenu')
        //this.userName = '1234'
    },
    methods: {
        handleMenu() {
            // 相当于调用这个方法
            this.$store.commit('CollapseMenu')
        },
        handleClick(command) {
            if (command === 'logout') {
                Cookie.remove('token')
                this.$router.push('/login')
            }

            if (command === 'ClicktoPersonal') {
                this.$router.push('/home')
            }
        }
    },
    computed: {
        ...mapState({
            tags: state => state.tab.tabList
        })
    }
}
</script>

<style lang="less" scoped>
.user-name-container {
    display: flex;
    align-items: center;
    margin-right: 20px;}
.header-container {
    background-color: #333;
    height: 60px;

    // 让按钮和头像居中
    display: flex;
    justify-content: space-between;
    align-items: center;
    // 不要紧贴边框
    padding: 0 20px;

    .el-dropdown-link {
        cursor: pointer;
        color: #409EFF;

        .user {
            width: 40px;
            height: 40px;
            // 50%变圆形
            border-radius: 50%;
        }
    }
}

.l-content {
    display: flex;
    // 上下居中
    align-items: center;

    .el-breadcrumb {
        margin-left: 15px;

        // deep 强制生效
        /deep/.el-breadcrumb__item {
            .el-breadcrumb__inner {
                &.is-link {
                    color: #666;
                }
            }

            &:last-child {
                .el-breadcrumb__inner {
                    color: #fff;
                }
            }
        }
    }
}
</style>