import axios from 'axios'

// 创建axios实例
const service = axios.create({
  baseURL: '/api', // 设置统一的请求前缀
  timeout: 600000 // 设置超时时间为600秒，与nginx配置匹配
})

// // 请求拦截器
// service.interceptors.request.use(
//   config => {
//     // 可以在这里添加token等认证信息
//     return config
//   },
//   error => {
//     return Promise.reject(error)
//   }
// )

// // 响应拦截器
// service.interceptors.response.use(
//   response => {
//     return response.data
//   },
//   error => {
//     return Promise.reject(error)
//   }
// )

export default service 