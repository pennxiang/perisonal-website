# 后端项目说明

## 技术栈

- **Spring Boot**: 3.4.5
- **Java**: 17
- **数据库**: MySQL 8.0+
- **ORM**: MyBatis
- **连接池**: Druid
- **缓存**: Redis
- **认证**: JWT
- **工具**: Lombok, Hutool

## 项目结构

```
src/main/java/com/shuiqingzheng/
├── common/                          # 公共模块
│   ├── config/                      # 配置类
│   │   ├── CorsConfig.java         # 跨域配置
│   │   └── RestTemplateConfig.java # RestTemplate配置
│   ├── exception/                   # 异常处理
│   │   ├── BusinessException.java  # 业务异常
│   │   └── GlobalExceptionHandler.java # 全局异常处理
│   ├── result/                      # 响应结果封装
│   │   ├── Result.java             # 统一响应格式
│   │   └── PageResult.java         # 分页响应格式
│   └── util/                        # 工具类
│       └── JsonTypeHandler.java    # MyBatis JSON类型处理器
├── module/                          # 业务模块
│   ├── article/                     # 文章模块
│   │   ├── entity/Article.java
│   │   ├── mapper/ArticleMapper.java
│   │   ├── service/ArticleService.java
│   │   └── controller/ArticleController.java
│   ├── moment/                      # 瞬间模块
│   ├── recommend/                   # 推荐模块
│   ├── todo/                        # 待办模块
│   └── about/                       # 关于模块
└── infrastructure/                  # 基础设施
    └── bangumi/                     # Bangumi API集成
        └── BangumiService.java     # Bangumi服务（模板）

src/main/resources/
├── mapper/                          # MyBatis XML映射文件
│   └── ArticleMapper.xml
└── application.yml                  # 应用配置文件
```

## 配置说明

### 1. 数据库配置

编辑 `application.yml`，填写数据库连接信息：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/personal_blog
    username: root
    password: your_password
```

### 2. Redis配置

```yaml
spring:
  data:
    redis:
      host: localhost
      port: 6379
      password: your_password  # 如果没有密码可以留空
```

### 3. JWT配置

```yaml
jwt:
  secret: your_jwt_secret_key_here  # 建议使用至少32位的随机字符串
  expiration: 3600
```

### 4. 数据库初始化

执行 `docs/database/schema.sql` 创建数据库表结构：

```bash
mysql -u root -p < docs/database/schema.sql
```

## Bangumi API集成

项目已经为Bangumi API集成准备了模板文件：`BangumiService.java`

### API文档

- 官方文档: https://bangumi.github.io/api/
- 新API地址: https://api.bgm.tv

### 主要功能模板

1. **获取番剧详情**: `getSubjectById(Long subjectId)`
2. **搜索番剧**: `searchSubjects(String keyword, Integer type)`
3. **获取用户收藏**: `getUserCollections(String username, Integer subjectType, Integer collectionType)`

### 番剧类型说明

- 1 = 书籍
- 2 = 动画
- 3 = 音乐
- 4 = 游戏
- 6 = 真人

### 使用示例

```java
@Autowired
private BangumiService bangumiService;

// 获取番剧信息
BangumiSubject subject = bangumiService.getSubjectById(12345L);

// 搜索动画
BangumiSearchResult result = bangumiService.searchSubjects("进击的巨人", 2);
```

### TODO事项

在 `BangumiService.java` 中需要完成：

1. 实现实际的HTTP请求调用
2. 根据Bangumi API响应结构完善数据模型
3. 添加错误处理和重试机制
4. 实现API限流策略
5. 添加缓存机制（Redis）

## API文档

详细的API文档请参考：`docs/api/README.md`

## 开发建议

### 1. 依赖版本管理

在 `pom.xml` 中补充以下依赖的版本号：

- mybatis-spring-boot-starter
- druid-spring-boot-starter
- jjwt-api / jjwt-impl / jjwt-jackson
- hutool-all
- flexmark-all

### 2. 其他模块开发

参考 `ArticleMapper`、`ArticleService` 和 `ArticleController` 的实现方式，完成其他模块：

- Moment（瞬间）
- Recommend（推荐）
- Todo（待办）
- About（关于）

### 3. 安全性

- 实现JWT认证拦截器
- 添加密码加密（BCrypt）
- 实现IP黑白名单过滤器
- 添加请求日志记录

### 4. 性能优化

- 使用Redis缓存热点数据
- 添加数据库索引
- 实现分页查询优化
- 添加连接池监控

## 运行项目

```bash
# 1. 安装依赖
mvn clean install

# 2. 启动后端服务
mvn spring-boot:run

# 3. 访问
# - API接口: http://localhost:8080/api/v1
# - Druid监控: http://localhost:8080/druid
```

## 前后端联调

前端项目位于 `frontend/` 目录，前端通过Vite代理访问后端API：

```javascript
// vite.config.js
server: {
  proxy: {
    '/api': {
      target: 'http://localhost:8080',
      changeOrigin: true
    }
  }
}
```

## 注意事项

1. **敏感信息**：不要将数据库密码、JWT密钥等敏感信息提交到Git
2. **端口占用**：确保8080端口未被占用
3. **数据库字符集**：确保使用utf8mb4字符集
4. **时区设置**：MySQL连接URL中已配置 `serverTimezone=Asia/Shanghai`
