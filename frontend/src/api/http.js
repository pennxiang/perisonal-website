import axios from 'axios'

const http = axios.create({
  baseURL: '/api/v1',
  timeout: 10000,
  headers: {
    'Content-Type': 'application/json'
  }
})

// 请求拦截器
http.interceptors.request.use(
  config => {
    // 如果有token，添加到请求头
    const token = localStorage.getItem('token')
    if (token) {
      config.headers.Authorization = `Bearer ${token}`
    }
    return config
  },
  error => {
    return Promise.reject(error)
  }
)

// 响应拦截器
http.interceptors.response.use(
  response => {
    const { data } = response
    if (data.code === 200) {
      return data.data
    } else {
      console.error('API Error:', data.message)
      return Promise.reject(new Error(data.message))
    }
  },
  error => {
    console.error('Request Error:', error)
    return Promise.reject(error)
  }
)

export default http
