# Penn 的生活日报

个人博客项目，记录技术学习与生活点滴。

## 项目架构

- **前端**: Vue 3 + Vite
- **后端**: Spring Boot 3.4.5 + MyBatis
- **数据库**: MySQL 8.0+
- **缓存**: Redis
- **认证**: JWT

## 快速开始

### 环境要求

- **前端**:
  - Node.js >= 22.19.0
  - pnpm >= 10.22.3

- **后端**:
  - JDK 17
  - Maven 3.6+
  - MySQL 8.0+
  - Redis 6.0+

### 1. 克隆项目

```bash
git clone <repository-url>
cd perisonal-website
```

### 2. 初始化数据库

```bash
mysql -u root -p < docs/database/schema.sql
```

### 3. 配置后端

编辑 `src/main/resources/application.yml`：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/personal_blog
    username: root
    password: your_password

  data:
    redis:
      host: localhost
      port: 6379
      password: your_password

jwt:
  secret: your_jwt_secret_key
```

### 4. 启动后端

```bash
# 安装依赖并启动
mvn clean install
mvn spring-boot:run
```

后端服务将运行在 http://localhost:8080

### 5. 启动前端

```bash
cd frontend
pnpm install
pnpm dev
```

前端服务将运行在 http://localhost:3000

## 项目结构

```
perisonal-website/
├── frontend/                         # Vue前端项目
│   ├── src/
│   │   ├── api/                     # API接口
│   │   ├── assets/                  # 静态资源
│   │   ├── components/              # 公共组件
│   │   ├── router/                  # 路由配置
│   │   ├── views/                   # 页面组件
│   │   └── main.js                  # 入口文件
│   ├── vite.config.js               # Vite配置
│   └── package.json                 # 前端依赖
├── src/main/
│   ├── java/com/shuiqingzheng/
│   │   ├── common/                  # 公共模块
│   │   ├── module/                  # 业务模块
│   │   │   ├── article/            # 文章模块
│   │   │   ├── moment/             # 瞬间模块
│   │   │   ├── recommend/          # 推荐模块
│   │   │   ├── todo/               # 待办模块
│   │   │   └── about/              # 关于模块
│   │   └── infrastructure/          # 基础设施
│   │       └── bangumi/            # Bangumi API集成
│   └── resources/
│       ├── mapper/                  # MyBatis XML
│       ├── static/                  # 静态资源（前端构建输出）
│       ├── templates/               # Thymeleaf模板（已废弃）
│       └── application.yml          # 应用配置
├── docs/                            # 文档
│   ├── api/                         # API文档
│   ├── database/                    # 数据库脚本
│   └── BACKEND_README.md           # 后端详细说明
└── pom.xml                          # Maven配置
```

## 功能特性

### 已实现

- ✅ Vue前端项目框架搭建
- ✅ 后端RESTful API架构
- ✅ MyBatis数据访问层
- ✅ 统一响应格式
- ✅ 全局异常处理
- ✅ 跨域配置
- ✅ Bangumi API集成模板
- ✅ 数据库表结构设计
- ✅ API文档

### 待完善

前端页面需要参考 `HomeView.vue` 完善：
- [ ] 杂谈列表页
- [ ] 技术文章列表页
- [ ] 瞬间列表页
- [ ] 文章详情页
- [ ] 推荐页面（动漫/电影/书单）
- [ ] 分类页
- [ ] 标签页
- [ ] 归档页
- [ ] 搜索页
- [ ] 关于页
- [ ] 待办事项页

后端模块需要参考 `ArticleMapper/Service/Controller` 完善：
- [ ] Moment模块（瞬间）
- [ ] Recommend模块（推荐）
- [ ] Todo模块（待办）
- [ ] About模块（关于）
- [ ] Admin模块（管理后台）
- [ ] JWT认证实现
- [ ] IP管理功能
- [ ] Bangumi API具体实现

其他：
- [ ] 补充依赖版本号
- [ ] Redis缓存实现
- [ ] 数据库索引优化
- [ ] 单元测试
- [ ] 接口文档生成（Swagger/Knife4j）

## 文档

- [前端开发文档](frontend/README.md)
- [后端开发文档](docs/BACKEND_README.md)
- [API接口文档](docs/api/README.md)

## Bangumi API集成

项目集成了Bangumi API用于获取动漫、书籍等推荐数据。

- **模板文件**: `src/main/java/com/shuiqingzheng/infrastructure/bangumi/BangumiService.java`
- **API文档**: https://bangumi.github.io/api/
- **使用说明**: 详见 [后端开发文档](docs/BACKEND_README.md)

## 开发模式

### 前后端分离开发

1. 启动后端服务 (端口8080)
2. 启动前端开发服务器 (端口3000)
3. 前端通过Vite代理访问后端API

### 前后端一体化部署

1. 构建前端: `cd frontend && pnpm build`
2. 前端资源自动输出到 `src/main/resources/static`
3. 启动Spring Boot，访问 http://localhost:8080

## 技术亮点

- **前后端分离**: Vue SPA + Spring Boot RESTful API
- **模块化设计**: 清晰的包结构和模块划分
- **统一响应**: 规范的API响应格式和分页结构
- **异常处理**: 全局异常捕获和统一错误响应
- **类型安全**: MyBatis TypeHandler处理JSON类型
- **第三方集成**: Bangumi API集成模板，易于扩展

## 贡献指南

1. Fork项目
2. 创建特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 提交Pull Request

## 许可证

[MIT License](LICENSE)

## 联系方式

- Email: blog@penn.dev
- GitHub: https://github.com/penn

---

**注意**: 本项目部分功能尚在开发中，具体实现请参考各模块的TODO注释。
