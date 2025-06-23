<template>
    <!-- 使用 Element UI 的布局组件 -->
    <el-container v-if="isUserRoute">
        <el-header style="height: auto; padding: 0;">
            <AnotherHeader />
        </el-header>
        <div class="page-title" v-if="showPageTitle">
            <span v-html="pageTitle"></span>
        </div>
        <el-main>
            <router-view></router-view>
        </el-main>
        <el-footer style="padding: 0;">
            <AnotherFooter />
        </el-footer>
    </el-container>

    <el-container v-else class="admin-layout">
        <el-header style="padding: 0;">
            <CommonHeader/>
        </el-header>
        <el-container>
            <el-aside width="auto">
                <CommonAside/>
            </el-aside>
            <el-container>
                <CommonTags/>
                <el-main>
                    <router-view></router-view>
                </el-main>
            </el-container>
        </el-container>
    </el-container>
</template>

<script>
import CommonAside from '../components/CommonAside.vue'
import CommonHeader from '../components/CommonHeader.vue'
import AnotherHeader from '../components/AnotherHeader.vue'
import AnotherFooter from '@/components/AnotherFooter.vue'
import CommonTags from '../components/CommonTags.vue'

export default {
    components: {
        CommonHeader,
        CommonAside,
        AnotherHeader,
        AnotherFooter,
        CommonTags
    },
    computed: {
        routeName() {
            return this.$route.name;
        },
        isPredictionPage() {
            return ['about_GastricMalignantTumor', 'about_ColonTumor', 'about_RectalTumor'].includes(this.routeName);
        },
        isUserRoute() {
            const userRoutes = [
                'about', 
                'about_about',
                'about_GastricMalignantTumor', 
                'about_FAQ', 
                'HomePage', 
                'about_PI',
                'about_TumorOfLiver',
                'about_ColonTumor',
                'about_Cholangiocarcinoma',
                'about_CholecysticTumor',
                'about_RectalTumor',
                'ComplicationDetails',
                'Popup'
            ];
            return userRoutes.includes(this.routeName);
        },
        pageTitles() {
            switch(this.routeName) {
                case 'about_GastricMalignantTumor':
                    return [
                        { chinese: '胃恶性肿瘤手术风险评估', english: 'Surgical risk assessment of gastric malignant tumor' }
                    ];
                case 'about_TumorOfLiver':
                    return [
                        { chinese: '肝肿瘤手术风险评估', english: 'Surgical risk assessment of liver tumors' }
                    ];
                case 'about_ColonTumor':
                    return [
                        { chinese: '结肠肿瘤手术风险评估', english: 'Surgical risk assessment of colon tumors' }
                    ];
                case 'about_Cholangiocarcinoma':
                    return [
                        { chinese: '胆管肿瘤手术风险评估', english: 'Surgical risk assessment of cholangiocarcinoma' }
                    ];
                case 'about_CholecysticTumor':
                    return [
                        { chinese: '胆囊肿瘤手术风险评估', english: 'Surgical risk assessment of cholecystic tumors' }
                    ];
                case 'about_RectalTumor':
                    return [
                        { chinese: '直肠肿瘤手术风险评估', english: 'Surgical risk assessment of rectal tumors' }
                    ];
                default:
                    return [];
            }
        },
        pageTitle() {
            const titles = this.pageTitles;
            if (titles.length > 0) {
                return titles[0].chinese + '<br><span style="font-size: 0.7em;">' + titles[0].english + '</span>';
            }
            return '';
        },
        showPageTitle() {
            return ['about_GastricMalignantTumor', 'about_TumorOfLiver', 'about_ColonTumor', 'about_Cholangiocarcinoma', 'about_CholecysticTumor', 'about_RectalTumor'].includes(this.routeName);
        }
    }
}
</script>

<style lang="less" scoped>
.el-container {
    min-height: 100vh;
}

.admin-layout {
    display: flex;
    flex-direction: column;

    .el-header {
        position: sticky;
        top: 0;
        z-index: 1000;
        background: #fff;
    }

    & > .el-container {
        flex: 1;
        display: flex;
        flex-direction: row;
    }
}

.el-main {
    flex: 1;
    overflow-y: auto;
    padding: 0 20px;
    margin-top: v-bind('isPredictionPage ? "-160px" : "0"');
    position: relative;
    z-index: 2;
}

.el-footer {
    padding: 0;
}

.el-main::-webkit-scrollbar {
    width: 6px;
}

.el-main::-webkit-scrollbar-thumb {
    background-color: #ddd;
    border-radius: 3px;
}

.el-main::-webkit-scrollbar-track {
    background-color: #f1f1f1;
}

.page-title {
    text-align: center;
    font-size: 30px;
    font-weight: bold;
    padding: 25px 0;
    background-image: url('../assets/images/1.jpg');
    background-size: cover;
    background-position: center;
    background-repeat: no-repeat;
    color: #ffffff;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    margin: 0;
    letter-spacing: 2px;
    width: 100%;
    border-radius: 0;
    text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);
    height: 300px;
    display: flex;
    align-items: center;
    justify-content: center;
    position: relative;
    z-index: 1;
}

.page-title::before {
    content: '';
    display: inline-block;
    height: 100%;
    vertical-align: middle;
}

.page-title::after {
    content: attr(data-content);
    display: none;
}

.page-title > span {
    position: relative;
    top: -80px;
}
</style>
