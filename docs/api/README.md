# Penn 的生活日报 - API 文档

## 概述

本文档描述了个人博客网站的所有后端API接口设计。

## 基础信息

- **Base URL**: `http://localhost:8080/api/v1`
- **认证方式**: JWT Token (管理接口需要)
- **响应格式**: JSON
- **字符编码**: UTF-8

## 统一响应格式

### 成功响应

```json
{
  "code": 200,
  "message": "success",
  "data": {
    // 实际数据
  }
}
```

### 分页响应

```json
{
  "code": 200,
  "message": "success",
  "data": {
    "content": [],
    "page": 1,
    "size": 16,
    "total": 100,
    "totalPages": 7
  }
}
```

### 错误响应

```json
{
  "code": 400/404/500,
  "message": "错误信息",
  "data": null
}
```

## API 模块

### 1. 文章相关 API
详见: [article-api.md](./article-api.md)
- 首页文章列表
- 文章列表（杂谈、技术）
- 文章详情
- 文章搜索

### 2. 瞬间相关 API
详见: [moment-api.md](./moment-api.md)
- 瞬间列表
- 瞬间详情

### 3. 推荐相关 API
详见: [recommend-api.md](./recommend-api.md)
- 动漫推荐列表
- 电影推荐列表
- 书单推荐列表

### 4. 公共 API
详见: [common-api.md](./common-api.md)
- 分类列表
- 标签列表
- 归档列表
- 关于信息
- 待办列表

### 5. 管理后台 API
详见: [admin-api.md](./admin-api.md)
- 文章管理
- 瞬间管理
- 推荐管理
- IP管理
- 数据统计

## 状态码说明

| 状态码 | 说明 |
|--------|------|
| 200 | 请求成功 |
| 201 | 创建成功 |
| 400 | 请求参数错误 |
| 401 | 未认证 |
| 403 | 无权限 |
| 404 | 资源不存在 |
| 500 | 服务器内部错误 |

## 数据模型

### Article (文章)

```json
{
  "id": 1,
  "title": "文章标题",
  "summary": "文章摘要",
  "content": "文章内容(Markdown)",
  "category": "技术/杂谈",
  "tags": ["Java", "Spring"],
  "publishDate": "2024-11-15T10:30:00",
  "updateDate": "2024-11-15T10:30:00",
  "viewCount": 100,
  "status": "published/draft"
}
```

### Moment (瞬间)

```json
{
  "id": 1,
  "title": "瞬间标题",
  "content": "瞬间内容",
  "publishDate": "2024-11-15T18:30:00"
}
```

### Recommend (推荐)

```json
{
  "id": 1,
  "type": "anime/movie/book",
  "title": "推荐标题",
  "author": "作者/导演",
  "genre": "类型",
  "rating": 9.5,
  "year": 2024,
  "description": "推荐描述",
  "coverUrl": "封面图片URL"
}
```

## 认证机制

管理后台API需要在请求头中携带JWT Token:

```
Authorization: Bearer <token>
```

登录接口:
```
POST /api/v1/auth/login
{
  "username": "admin",
  "password": "password"
}
```

响应:
```json
{
  "code": 200,
  "message": "success",
  "data": {
    "token": "eyJhbGciOiJIUzI1NiIs...",
    "expiresIn": 3600
  }
}
```
