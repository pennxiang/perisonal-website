# Penn 的生活日报 - 前端项目

基于 Vue 3 + Vite 构建的个人博客前端。

## 技术栈

- **Vue 3**: 渐进式JavaScript框架
- **Vite**: 下一代前端构建工具
- **Vue Router**: 官方路由管理器
- **Pinia**: 新一代状态管理
- **Axios**: HTTP客户端

## 环境要求

- Node.js: >=22.19.0
- pnpm: >=10.22.3

## 项目结构

```
frontend/
├── public/                 # 静态资源
├── src/
│   ├── api/               # API接口定义
│   │   ├── http.js       # Axios配置
│   │   └── index.js      # API方法
│   ├── assets/            # 资源文件
│   │   └── css/          # 样式文件
│   ├── components/        # 公共组件
│   │   ├── NavigationBar.vue
│   │   └── FooterBar.vue
│   ├── router/            # 路由配置
│   │   └── index.js
│   ├── stores/            # Pinia状态管理（待扩展）
│   ├── utils/             # 工具函数（待扩展）
│   ├── views/             # 页面组件
│   │   ├── HomeView.vue
│   │   ├── EssayView.vue
│   │   ├── TechView.vue
│   │   ├── MomentView.vue
│   │   ├── ArticleView.vue
│   │   ├── RecommendView.vue
│   │   ├── CategoryView.vue
│   │   ├── TagsView.vue
│   │   ├── ArchiveView.vue
│   │   ├── SearchView.vue
│   │   ├── AboutView.vue
│   │   └── TodolistView.vue
│   ├── App.vue            # 根组件
│   └── main.js            # 入口文件
├── index.html             # HTML模板
├── vite.config.js         # Vite配置
└── package.json           # 项目配置

```

## 安装依赖

```bash
cd frontend
pnpm install
```

## 开发模式

```bash
# 启动开发服务器 (http://localhost:3000)
pnpm dev
```

开发服务器会自动代理 `/api` 请求到后端服务 (http://localhost:8080)

## 生产构建

```bash
# 构建生产版本
pnpm build

# 预览生产构建
pnpm preview
```

构建后的文件会输出到 `../src/main/resources/static` 目录，可以直接被Spring Boot服务。

## 路由说明

| 路径 | 组件 | 说明 |
|------|------|------|
| / | HomeView | 首页 |
| /essay | EssayView | 杂谈列表 |
| /tech | TechView | 技术文章列表 |
| /moment | MomentView | 瞬间列表 |
| /article/:id | ArticleView | 文章详情 |
| /recommend | RecommendView | 推荐页面 |
| /recommend/anime | AnimeView | 动漫推荐 |
| /recommend/movie | MovieView | 电影推荐 |
| /recommend/book | BookView | 书单推荐 |
| /category | CategoryView | 分类页 |
| /tags | TagsView | 标签列表 |
| /tags/:tagName | TagDetailView | 标签详情 |
| /archive | ArchiveView | 归档页 |
| /search | SearchView | 搜索页 |
| /about | AboutView | 关于页 |
| /todolist | TodolistView | 待办事项 |

## API调用示例

```javascript
import { articleApi, momentApi, recommendApi, commonApi } from '@/api'

// 获取首页数据
const homeData = await articleApi.getHomeData()

// 获取文章列表
const articles = await articleApi.getArticles('tech', { page: 1, size: 16 })

// 获取文章详情
const article = await articleApi.getArticleById(1)

// 搜索文章
const results = await articleApi.searchArticles('Java', { page: 1, size: 10 })

// 获取瞬间列表
const moments = await momentApi.getMoments({ page: 1, size: 16 })

// 获取推荐列表
const animes = await recommendApi.getAnimes({ page: 1, size: 10 })
```

## 组件说明

### NavigationBar.vue

导航栏组件，支持当前路由高亮显示。

```vue
<NavigationBar />
```

### FooterBar.vue

页脚组件，包含社交链接。

```vue
<FooterBar />
```

## 待完善的页面

以下页面组件需要根据 `HomeView.vue` 的模式完善：

- [ ] EssayView.vue
- [ ] TechView.vue
- [ ] MomentView.vue
- [ ] ArticleView.vue
- [ ] RecommendView.vue
- [ ] recommend/AnimeView.vue
- [ ] recommend/MovieView.vue
- [ ] recommend/BookView.vue
- [ ] CategoryView.vue
- [ ] TagsView.vue
- [ ] TagDetailView.vue
- [ ] ArchiveView.vue
- [ ] SearchView.vue
- [ ] AboutView.vue
- [ ] TodolistView.vue

## 开发建议

1. **组件复用**: 提取公共组件如ArticleCard、PaginationBar等
2. **状态管理**: 使用Pinia管理全局状态（如用户信息、主题设置等）
3. **错误处理**: 完善API调用的错误处理和用户提示
4. **加载状态**: 添加loading状态和骨架屏
5. **性能优化**: 实现路由懒加载、图片懒加载等
6. **SEO优化**: 添加meta标签、SSR等

## 样式说明

项目使用原有的 `style.css`，保持报纸风格的设计。主要类名：

- `.newspaper` - 容器
- `.navigation-bar` - 导航栏
- `.masthead` - 页面头部
- `.article` - 文章卡片
- `.card-footer` - 卡片底部
- `.read-more` - 阅读更多链接
- `.footer` - 页脚

## 注意事项

1. 确保后端服务已启动 (http://localhost:8080)
2. 首次运行需要执行 `pnpm install` 安装依赖
3. 开发时修改代码会自动热重载
4. 生产构建前确保所有功能已测试完毕
