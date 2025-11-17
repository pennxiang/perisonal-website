# 公共 API

## 1. 获取分类列表

获取所有文章分类及其文章数量。

**接口地址**: `GET /api/v1/categories`

**请求参数**: 无

**响应示例**:

```json
{
  "code": 200,
  "message": "success",
  "data": [
    {
      "id": 1,
      "name": "技术",
      "count": 45,
      "articles": [
        {
          "id": 1,
          "title": "MySQL 索引优化实践",
          "publishDate": "2024-11-12"
        }
      ]
    },
    {
      "id": 2,
      "name": "杂谈",
      "count": 28,
      "articles": [
        {
          "id": 2,
          "title": "周末的咖啡时光",
          "publishDate": "2024-11-14"
        }
      ]
    }
  ]
}
```

---

## 2. 获取标签列表

获取所有标签及使用次数。

**接口地址**: `GET /api/v1/tags`

**请求参数**: 无

**响应示例**:

```json
{
  "code": 200,
  "message": "success",
  "data": [
    {
      "id": 1,
      "name": "Java",
      "count": 25
    },
    {
      "id": 2,
      "name": "MySQL",
      "count": 15
    },
    {
      "id": 3,
      "name": "Redis",
      "count": 12
    }
  ]
}
```

---

## 3. 根据标签获取文章

根据标签名称获取相关文章列表。

**接口地址**: `GET /api/v1/tags/{tagName}/articles`

**路径参数**:

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| tagName | string | 是 | 标签名称 |

**请求参数**:

| 参数名 | 类型 | 必填 | 默认值 | 说明 |
|--------|------|------|--------|------|
| page | int | 否 | 1 | 页码 |
| size | int | 否 | 16 | 每页数量 |

**响应示例**:

```json
{
  "code": 200,
  "message": "success",
  "data": {
    "tag": "Java",
    "articles": {
      "content": [
        {
          "id": 1,
          "title": "深入学习 Java 后端的一周总结",
          "summary": "...",
          "publishDate": "2024-11-14",
          "category": "技术"
        }
      ],
      "page": 1,
      "size": 16,
      "total": 25,
      "totalPages": 2
    }
  }
}
```

---

## 4. 获取归档列表

按年月归档获取文章列表。

**接口地址**: `GET /api/v1/archives`

**请求参数**: 无

**响应示例**:

```json
{
  "code": 200,
  "message": "success",
  "data": [
    {
      "year": 2024,
      "months": [
        {
          "month": 11,
          "articles": [
            {
              "id": 1,
              "title": "MySQL 索引优化实践",
              "publishDate": "2024-11-12",
              "category": "技术"
            },
            {
              "id": 2,
              "title": "周末的咖啡时光",
              "publishDate": "2024-11-14",
              "category": "杂谈"
            }
          ]
        },
        {
          "month": 10,
          "articles": [...]
        }
      ]
    },
    {
      "year": 2023,
      "months": [...]
    }
  ]
}
```

---

## 5. 获取关于信息

获取关于页面的个人信息和博客统计。

**接口地址**: `GET /api/v1/about`

**请求参数**: 无

**响应示例**:

```json
{
  "code": 200,
  "message": "success",
  "data": {
    "about": {
      "name": "Penn",
      "role": "后端开发 · Java / Spring · 喜欢记录",
      "skills": "Java / Spring · MySQL · Redis · Kafka · Docker",
      "description": "喜欢把遇到的问题写下来，把解决过程拆成可复用的小块。写作既是复盘，也是学习的反馈循环。",
      "email": "blog@penn.dev",
      "github": "https://github.com/penn",
      "avatarUrl": "/images/avatar.jpg"
    },
    "stats": {
      "articleCount": 42,
      "noteCount": 156,
      "visitCount": "2.5K",
      "persistDays": 365
    }
  }
}
```

---

## 6. 获取待办列表

获取当前的待办事项列表。

**接口地址**: `GET /api/v1/todolist`

**请求参数**: 无

**响应示例**:

```json
{
  "code": 200,
  "message": "success",
  "data": [
    {
      "id": 1,
      "title": "完成毕业设计开题报告",
      "status": "进行中",
      "priority": "高",
      "deadline": "2024-12-01",
      "description": "需要完成开题报告的撰写和PPT制作"
    },
    {
      "id": 2,
      "title": "学习 Kubernetes 部署",
      "status": "待开始",
      "priority": "中",
      "deadline": "2024-12-15",
      "description": "学习K8s的基本概念和实践部署"
    }
  ]
}
```

---

## 7. 获取网站统计信息

获取网站的整体统计数据（用于首页展示）。

**接口地址**: `GET /api/v1/stats`

**请求参数**: 无

**响应示例**:

```json
{
  "code": 200,
  "message": "success",
  "data": {
    "totalArticles": 128,
    "totalVisits": 15234,
    "todayVisits": 342,
    "totalMoments": 89,
    "totalCategories": 2,
    "totalTags": 35
  }
}
```
