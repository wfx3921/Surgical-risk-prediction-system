const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave:false,
  // ... 其他配置
  devServer: {
    port: 9092,
    proxy: {
      '/api': {
        target: 'http://localhost:9090',
        changeOrigin: true,
        pathRewrite: {
          '^/api': ''
        }
      }
    }
  }
})
