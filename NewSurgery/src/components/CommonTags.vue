<template>
    <div class="tabs">
        <el-tag v-for="(item, index) in visibleTags" :key="item.path" :closable="item.name !== 'home'"
            :effect="item.name === $route.name ? 'dark' : 'plain'" @click="changeMenu(item)"
            @close="handleClose(item, index)">
            {{ item.label }}
        </el-tag>
    </div>
</template>

<script>
import { mapState } from 'vuex'

export default {
    computed: {
        ...mapState({
            tags: state => state.tab.tabList,
            visibleTags() {
                return this.tags.filter(tag => tag.isVisible); // 只渲染可见的标签
            }
        })
    },
    methods: {
        changeMenu(item) {
            this.$router.push({ name: item.name })
        },
        handleClose(item, index) {
            this.$store.commit('closeTag', item)
            if (item.name === this.$route.name) {
                const nextIndex = index === this.tags.length - 1 ? index - 1 : index + 1;
                this.$router.push({ name: this.tags[nextIndex].name });
            }
        }
    }
}
</script>

<style lang="less" scoped>
.tabs {
    padding: 20px;
    // height: 110%;
    .el-tag {
        margin-right: 15px;
        cursor: pointer;
    }
}
</style>