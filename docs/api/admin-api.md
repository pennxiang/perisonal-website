# 管理后台 API

> **注意**: 所有管理后台API都需要JWT认证，需在请求头中携带 `Authorization: Bearer <token>`

## 认证相关

### 1. 管理员登录

**接口地址**: `POST /api/v1/auth/login`

**请求体**:

```json
{
  "username": "admin",
  "password": "password"
}
```

**响应示例**:

```json
{
  "code": 200,
  "message": "success",
  "data": {
    "token": "eyJhbGciOiJIUzI1NiIs...",
    "expiresIn": 3600,
    "user": {
      "id": 1,
      "username": "admin",
      "name": "Penn"
    }
  }
}
```

---

## 数据概览

### 1. 获取仪表盘数据

获取管理后台首页的统计数据。

**接口地址**: `GET /api/v1/admin/dashboard`

**响应示例**:

```json
{
  "code": 200,
  "message": "success",
  "data": {
    "stats": {
      "totalArticles": 128,
      "totalVisits": 15234,
      "todayVisits": 342,
      "totalMoments": 89
    },
    "hotArticles": [
      {
        "id": 1,
        "title": "深入理解 Java 线程池原理",
        "viewCount": 1234,
        "publishDate": "2024-11-10"
      }
    ],
    "recentVisits": {
      "dates": ["11-10", "11-11", "11-12", "11-13", "11-14"],
      "visits": [234, 287, 312, 298, 342]
    }
  }
}
```

---

## 文章管理

### 1. 上传文章

上传 Markdown 文件创建文章。

**接口地址**: `POST /api/v1/admin/articles/upload`

**请求方式**: `multipart/form-data`

**请求参数**:

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| file | file | 是 | Markdown文件 |
| category | string | 是 | 分类 (技术/杂谈) |
| tags | string[] | 否 | 标签数组 |
| status | string | 否 | 状态 (published/draft)，默认draft |

**响应示例**:

```json
{
  "code": 200,
  "message": "文章上传成功",
  "data": {
    "id": 100,
    "title": "从文件解析的标题",
    "status": "draft"
  }
}
```

---

### 2. 发布/更新文章

创建或更新文章。

**接口地址**:
- 创建: `POST /api/v1/admin/articles`
- 更新: `PUT /api/v1/admin/articles/{id}`

**请求体**:

```json
{
  "title": "文章标题",
  "content": "文章内容(Markdown)",
  "summary": "文章摘要",
  "category": "技术",
  "tags": ["Java", "Spring"],
  "status": "published"
}
```

**响应示例**:

```json
{
  "code": 200,
  "message": "文章发布成功",
  "data": {
    "id": 100,
    "title": "文章标题",
    "publishDate": "2024-11-15T10:30:00"
  }
}
```

---

### 3. 获取文章列表（管理）

获取所有文章列表，包括草稿。

**接口地址**: `GET /api/v1/admin/articles`

**请求参数**:

| 参数名 | 类型 | 必填 | 默认值 | 说明 |
|--------|------|------|--------|------|
| status | string | 否 | - | 状态筛选 (published/draft) |
| category | string | 否 | - | 分类筛选 |
| keyword | string | 否 | - | 搜索关键词 |
| page | int | 否 | 1 | 页码 |
| size | int | 否 | 10 | 每页数量 |

**响应示例**:

```json
{
  "code": 200,
  "message": "success",
  "data": {
    "content": [
      {
        "id": 1,
        "title": "MySQL 索引优化实践",
        "category": "技术",
        "status": "published",
        "publishDate": "2024-11-12T10:00:00",
        "updateDate": "2024-11-12T10:00:00",
        "viewCount": 234
      }
    ],
    "page": 1,
    "size": 10,
    "total": 128,
    "totalPages": 13
  }
}
```

---

### 4. 删除文章

根据ID删除文章。

**接口地址**: `DELETE /api/v1/admin/articles/{id}`

**路径参数**:

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| id | long | 是 | 文章ID |

**响应示例**:

```json
{
  "code": 200,
  "message": "文章删除成功",
  "data": null
}
```

---

## 瞬间管理

### 1. 发布瞬间

创建新瞬间。

**接口地址**: `POST /api/v1/admin/moments`

**请求体**:

```json
{
  "title": "瞬间标题",
  "content": "瞬间内容",
  "tags": ["开发", "思考"]
}
```

**响应示例**:

```json
{
  "code": 200,
  "message": "瞬间发布成功",
  "data": {
    "id": 90,
    "publishDate": "2024-11-15T18:30:00"
  }
}
```

---

### 2. 更新瞬间

更新已有瞬间。

**接口地址**: `PUT /api/v1/admin/moments/{id}`

**请求体**: 同发布瞬间

---

### 3. 删除瞬间

删除指定瞬间。

**接口地址**: `DELETE /api/v1/admin/moments/{id}`

---

### 4. 获取瞬间列表（管理）

获取所有瞬间列表。

**接口地址**: `GET /api/v1/admin/moments`

**请求参数**:

| 参数名 | 类型 | 必填 | 默认值 | 说明 |
|--------|------|------|--------|------|
| keyword | string | 否 | - | 搜索关键词 |
| page | int | 否 | 1 | 页码 |
| size | int | 否 | 10 | 每页数量 |

---

## 推荐管理

### 1. 添加推荐

添加动漫/电影/书籍推荐。

**接口地址**: `POST /api/v1/admin/recommend`

**请求体**:

```json
{
  "type": "anime",
  "title": "进击的巨人",
  "author": "谏山创",
  "genre": "热血、悬疑",
  "rating": 9.8,
  "year": 2013,
  "description": "宏大的世界观设定...",
  "coverUrl": "/images/anime/shingeki.jpg"
}
```

**响应示例**:

```json
{
  "code": 200,
  "message": "推荐添加成功",
  "data": {
    "id": 15
  }
}
```

---

### 2. 更新推荐

更新推荐信息。

**接口地址**: `PUT /api/v1/admin/recommend/{id}`

**请求体**: 同添加推荐

---

### 3. 删除推荐

删除推荐。

**接口地址**: `DELETE /api/v1/admin/recommend/{id}`

---

### 4. 获取推荐列表（管理）

获取所有推荐列表。

**接口地址**: `GET /api/v1/admin/recommend`

**请求参数**:

| 参数名 | 类型 | 必填 | 默认值 | 说明 |
|--------|------|------|--------|------|
| type | string | 否 | - | 类型筛选 (anime/movie/book) |
| keyword | string | 否 | - | 搜索关键词 |
| page | int | 否 | 1 | 页码 |
| size | int | 否 | 10 | 每页数量 |

---

## 待办管理

### 1. 添加待办

创建新待办事项。

**接口地址**: `POST /api/v1/admin/todos`

**请求体**:

```json
{
  "title": "完成毕业设计开题报告",
  "description": "需要完成开题报告的撰写和PPT制作",
  "status": "进行中",
  "priority": "高",
  "deadline": "2024-12-01"
}
```

---

### 2. 更新待办

更新待办事项。

**接口地址**: `PUT /api/v1/admin/todos/{id}`

---

### 3. 删除待办

删除待办事项。

**接口地址**: `DELETE /api/v1/admin/todos/{id}`

---

## 关于页编辑

### 1. 更新关于信息

更新关于页面的信息。

**接口地址**: `PUT /api/v1/admin/about`

**请求体**:

```json
{
  "name": "Penn",
  "role": "后端开发 · Java / Spring · 喜欢记录",
  "skills": "Java / Spring · MySQL · Redis · Kafka · Docker",
  "description": "喜欢把遇到的问题写下来...",
  "email": "blog@penn.dev",
  "github": "https://github.com/penn"
}
```

---

## IP 管理

### 1. 获取访问记录

获取IP访问记录。

**接口地址**: `GET /api/v1/admin/ip/visits`

**请求参数**:

| 参数名 | 类型 | 必填 | 默认值 | 说明 |
|--------|------|------|--------|------|
| dateRange | string | 否 | today | 时间范围 (today/week/month) |
| ip | string | 否 | - | IP地址搜索 |
| page | int | 否 | 1 | 页码 |
| size | int | 否 | 20 | 每页数量 |

**响应示例**:

```json
{
  "code": 200,
  "message": "success",
  "data": {
    "content": [
      {
        "ip": "192.168.1.100",
        "location": "中国-广东-深圳",
        "visitCount": 45,
        "lastVisit": "2024-11-16T15:30:00"
      }
    ],
    "page": 1,
    "size": 20,
    "total": 150,
    "totalPages": 8
  }
}
```

---

### 2. 添加白名单/黑名单

添加IP到白名单或黑名单。

**接口地址**:
- 白名单: `POST /api/v1/admin/ip/whitelist`
- 黑名单: `POST /api/v1/admin/ip/blacklist`

**请求体**:

```json
{
  "ip": "192.168.1.100",
  "reason": "信任IP/恶意访问"
}
```

---

### 3. 移除白名单/黑名单

从白名单或黑名单移除IP。

**接口地址**:
- 白名单: `DELETE /api/v1/admin/ip/whitelist/{ip}`
- 黑名单: `DELETE /api/v1/admin/ip/blacklist/{ip}`

---

### 4. 获取白名单/黑名单

获取白名单或黑名单列表。

**接口地址**:
- 白名单: `GET /api/v1/admin/ip/whitelist`
- 黑名单: `GET /api/v1/admin/ip/blacklist`

**响应示例**:

```json
{
  "code": 200,
  "message": "success",
  "data": [
    {
      "ip": "127.0.0.1",
      "reason": "本地调试地址",
      "addTime": "2024-11-01T10:00:00"
    }
  ]
}
```

---

## 账号管理

### 1. 绑定第三方账号

绑定GitHub、Google等第三方账号。

**接口地址**: `POST /api/v1/admin/account/bind`

**请求体**:

```json
{
  "platform": "github",
  "code": "oauth_code_from_github"
}
```

---

### 2. 解绑第三方账号

解绑第三方账号。

**接口地址**: `DELETE /api/v1/admin/account/unbind/{platform}`

---

### 3. 修改密码

修改管理员密码。

**接口地址**: `PUT /api/v1/admin/account/password`

**请求体**:

```json
{
  "oldPassword": "old_password",
  "newPassword": "new_password"
}
```
