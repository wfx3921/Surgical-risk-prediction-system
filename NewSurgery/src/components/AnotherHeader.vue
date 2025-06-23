<template>
    <div>
        <div class="header">
            <!-- <el-button @click="handleMenu" icon="el-icon-menu" size="mini"></el-button> -->
            <img src="../assets/images/bbb.png" alt="Logo 1" class="header-logo">
            <span class="vertical-line"></span>
<!--            <h1 class="main-title" style="margin-left: 20px;margin-right:10px;">OUC</h1>-->
            <h2 class="subtitle" style="margin-right:20px;margin-left:20px;">TAHQU PRPS</h2>
            <span class="vertical-line"></span>
            <img src="../assets/images/Qduh.png" alt="Logo 2" class="header-logo">
        </div>

        <div class="nav">
            <a href="#/about" :class="{ active: activeLink === '#/about' }" @click="setActiveLink('#/about')" style="padding-right: 40px">
              <span style="display: block; text-align: center;">评估检索</span>
              <span style="display: block; text-align: center;">Retrieval of evaluation</span>
            </a>

            <!-- 修改下拉菜单的实现 -->
            <el-dropdown trigger="hover" @command="handlePredictionClick" style="padding-right: 40px">
                <span class="dropdown-link" :class="{ active: isPredictionActive }">
                    <span style="display: block; text-align: center;">新的预测</span>
                    <span style="display: block; text-align: center;">New Prediction<i class="el-icon-arrow-down el-icon--right"></i></span>
                </span>
                <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item command="#/about_GastricMalignantTumor">胃恶性肿瘤(gastric malignant tumor)</el-dropdown-item>
<!--                    <el-dropdown-item command="#/about_TumorOfLiver">肝肿瘤</el-dropdown-item>-->
                    <el-dropdown-item command="#/about_ColonTumor">结肠肿瘤(colon tumor)</el-dropdown-item>
<!--                    <el-dropdown-item command="#/about_Cholangiocarcinoma">胆管肿瘤</el-dropdown-item>-->
<!--                    <el-dropdown-item command="#/about_CholecysticTumor">胆囊肿瘤</el-dropdown-item>-->
                    <el-dropdown-item command="#/about_RectalTumor">直肠肿瘤(rectal tumor)</el-dropdown-item>
                </el-dropdown-menu>
            </el-dropdown>

            <a href="#/about_about" :class="{ active: activeLink === '#/about_about' }" @click="setActiveLink('#/about_about')"style="padding-right: 40px">
                <span style="display: block; text-align: center;">关于</span>
                <span style="display: block; text-align: center;">About</span>
            </a>
            <a href="#/about_FAQ" :class="{ active: activeLink === '#/about_FAQ' }" @click="setActiveLink('#/about_FAQ')"style="padding-right: 100px">
                <span style="display: block; text-align: center;">常见问题</span>
                <span style="display: block; text-align: center;">FAQ</span>
            </a>
            <!-- <a href="https://www.qduh.cn/">青大附医</a> -->

            <div class="user-name-container">
                <span class="user-name-container">
                    <i class="el-icon-user-solid user-icon" style="font-size: 24px;"></i>
                    <span class="user-name" style="color: #f0f0f0;font-size: 20px;">{{ userName }}</span>
                </span>
                <el-dropdown @command="handleClick" style="margin-right: 20px;">
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


    </div>
</template>

<script>
import { mapState } from 'vuex'
import Cookie from 'js-cookie'

export default {
    data() {
        return {
            userName: '',
            activeLink: ''
        }
    },
    created() {
        const userInfo = JSON.parse(Cookie.get('userInfo'));
        this.userName = userInfo.userName
        //this.userName = '1234'
    },
    computed: {
        ...mapState({
            tags: state => state.tab.tabList
        }),
        // 添加计算属性判断是否为预测相关页面
        isPredictionActive() {
            return this.activeLink.includes('about_GastricMalignantTumor') || 
                   this.activeLink.includes('about_TumorOfLiver') || 
                   this.activeLink.includes('about_ColonTumor') ||
                   this.activeLink.includes('about_Cholangiocarcinoma') ||
                   this.activeLink.includes('about_CholecysticTumor')||
                   this.activeLink.includes('about_RectalTumor');
        }
    },
    methods: {
        setActiveLink(link) {
            this.activeLink = link;
        },
        handleMenu() {
            // 相当于调用这个方法
            this.$store.commit('CollapseMenu')
        },
        handleClick(command) {
            if (command === 'logout') {
                Cookie.remove('token')
                localStorage.removeItem('token')
                this.$router.push('/login')
            }

            if (command === 'ClicktoPersonal') {
                this.$router.push('/about_PI')
            }
        },
        handlePredictionClick(command) {
            this.setActiveLink(command);
            // 移除开头的 # 号
            const path = command.replace('#', '');
            this.$router.push(path).catch(err => {
                if (err.name !== 'NavigationDuplicated') {
                    console.error(err);
                }
            });
        }
    }
}
</script>

<style lang="less" scoped>
.vertical-line {
    display: inline-block; /* 使其在行内显示 */
    width: 3px; /* 竖线宽度 */
    height: 60px; /* 竖线高度，可以根据需要调整 */
    background-color: #040000; /* 竖线颜色 */
    margin: 0 10px; /* 设置竖线两侧的间距 */
}
.user-name-container {
    display: flex;
    align-items: center;
    margin-right: 80px;
    position: absolute;
    /* 绝对定位 */
    right: 0;
    /* 左对齐 */
}

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

.body {
    margin: 0;
    font-family: Arial, sans-serif;
}

// .Theader {
//     height: 250px;
// }

.header {
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 10px;
}

.header-logo {
    margin: 0 40px;
    height: 68px;
}

.main-title {
    text-align: center;
    font-size: 24px;
    margin-top: -10px;
    color: #ff3300;
}

.subtitle {
    text-align: center;
    font-size: 30px;
    color: #003366;
}

.nav {
    background-image: url('../assets/images/background.jpg');
    background-size: cover;
    background-position: center;
    background-repeat: no-repeat;
    width: 100%;
    margin: 0;
    color: white;
    display: flex;
    justify-content: center;
    align-items: center;
    position: relative;
    padding: 0px 0;
    border: 2px solid #1A3875;
    border-radius: 0;
}

.nav a {
    text-decoration: none;
    color: #ffffff;
    font-size: 25px;
    margin: 0 20px;
    padding: 0px 10px;
    transition: color 0.3s, background-color 0.3s;
}

.nav a:hover {
    color: #000000;
    background-color: rgba(0, 0, 0, 0.1);
    border-radius: 12px;
}

.nav a.active {
    color: #000000;
    background-color: rgba(0, 0, 0, 0.1);
    border-radius: 12px;
}

/* 添加下拉菜单的样式 */
.dropdown-link {
    color: #ffffff;
    font-size: 25px;
    margin: 0 20px;
    padding: 0px 10px;
    cursor: pointer;
    text-shadow: 5px 5px 5px rgba(0, 0, 0, 0.7);

    &.active {
        color: #000000;
        background-color: rgba(0, 0, 0, 0.1);
        border-radius: 12px;
    }
}
</style>