import http from './http'

// 文章相关API
export const articleApi = {
  // 获取首页数据
  getHomeData: () => http.get('/home'),

  // 获取文章列表
  getArticles: (category, params) => http.get(`/articles/${category}`, { params }),

  // 获取文章详情
  getArticleById: (id) => http.get(`/articles/${id}`),

  // 搜索文章
  searchArticles: (keyword, params) => http.get('/articles/search', { params: { q: keyword, ...params } })
}

// 瞬间相关API
export const momentApi = {
  // 获取瞬间列表
  getMoments: (params) => http.get('/moments', { params }),

  // 获取瞬间详情
  getMomentById: (id) => http.get(`/moments/${id}`)
}

// 推荐相关API
export const recommendApi = {
  // 获取动漫推荐
  getAnimes: (params) => http.get('/recommend/anime', { params }),

  // 获取电影推荐
  getMovies: (params) => http.get('/recommend/movie', { params }),

  // 获取书籍推荐
  getBooks: (params) => http.get('/recommend/book', { params })
}

// 公共API
export const commonApi = {
  // 获取分类列表
  getCategories: () => http.get('/categories'),

  // 获取标签列表
  getTags: () => http.get('/tags'),

  // 根据标签获取文章
  getArticlesByTag: (tagName, params) => http.get(`/tags/${tagName}/articles`, { params }),

  // 获取归档
  getArchives: () => http.get('/archives'),

  // 获取关于信息
  getAbout: () => http.get('/about'),

  // 获取待办列表
  getTodos: () => http.get('/todolist'),

  // 获取统计信息
  getStats: () => http.get('/stats')
}

// 管理后台API
export const adminApi = {
  // 登录
  login: (credentials) => http.post('/auth/login', credentials),

  // 获取仪表盘数据
  getDashboard: () => http.get('/admin/dashboard'),

  // 文章管理
  uploadArticle: (formData) => http.post('/admin/articles/upload', formData, {
    headers: { 'Content-Type': 'multipart/form-data' }
  }),
  createArticle: (article) => http.post('/admin/articles', article),
  updateArticle: (id, article) => http.put(`/admin/articles/${id}`, article),
  deleteArticle: (id) => http.delete(`/admin/articles/${id}`),
  getAdminArticles: (params) => http.get('/admin/articles', { params }),

  // 瞬间管理
  createMoment: (moment) => http.post('/admin/moments', moment),
  updateMoment: (id, moment) => http.put(`/admin/moments/${id}`, moment),
  deleteMoment: (id) => http.delete(`/admin/moments/${id}`),
  getAdminMoments: (params) => http.get('/admin/moments', { params }),

  // 推荐管理
  createRecommend: (recommend) => http.post('/admin/recommend', recommend),
  updateRecommend: (id, recommend) => http.put(`/admin/recommend/${id}`, recommend),
  deleteRecommend: (id) => http.delete(`/admin/recommend/${id}`),
  getAdminRecommends: (params) => http.get('/admin/recommend', { params }),

  // 待办管理
  createTodo: (todo) => http.post('/admin/todos', todo),
  updateTodo: (id, todo) => http.put(`/admin/todos/${id}`, todo),
  deleteTodo: (id) => http.delete(`/admin/todos/${id}`),

  // 关于页编辑
  updateAbout: (about) => http.put('/admin/about', about),

  // IP管理
  getIpVisits: (params) => http.get('/admin/ip/visits', { params }),
  addToWhitelist: (ip, reason) => http.post('/admin/ip/whitelist', { ip, reason }),
  addToBlacklist: (ip, reason) => http.post('/admin/ip/blacklist', { ip, reason }),
  removeFromWhitelist: (ip) => http.delete(`/admin/ip/whitelist/${ip}`),
  removeFromBlacklist: (ip) => http.delete(`/admin/ip/blacklist/${ip}`),
  getWhitelist: () => http.get('/admin/ip/whitelist'),
  getBlacklist: () => http.get('/admin/ip/blacklist'),

  // 账号管理
  bindAccount: (platform, code) => http.post('/admin/account/bind', { platform, code }),
  unbindAccount: (platform) => http.delete(`/admin/account/unbind/${platform}`),
  changePassword: (passwords) => http.put('/admin/account/password', passwords)
}
