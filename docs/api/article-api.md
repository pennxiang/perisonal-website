# 文章相关 API

## 1. 获取首页文章列表

获取首页展示的文章内容，包括最新文章、杂谈、技术文章和瞬间。

**接口地址**: `GET /api/v1/home`

**请求参数**: 无

**响应示例**:

```json
{
  "code": 200,
  "message": "success",
  "data": {
    "latestArticle": {
      "id": 1,
      "title": "深入学习 Java 后端的一周总结",
      "summary": "一周聚焦 Redis 与 MySQL...",
      "publishDate": "2024-11-14",
      "category": "技术"
    },
    "essays": [
      {
        "id": 2,
        "title": "周末的咖啡时光",
        "summary": "看着咖啡师调温、称豆、控水...",
        "publishDate": "2024-11-14"
      }
    ],
    "techArticles": [
      {
        "id": 3,
        "title": "MySQL 索引优化实践",
        "summary": "拆解 explain 输出...",
        "publishDate": "2024-11-12"
      }
    ],
    "moments": [
      {
        "id": 1,
        "title": "Bug 终于解决了",
        "content": "原来是 Redis TTL 序列化字段遗漏...",
        "publishDate": "2024-11-14T22:15:00"
      }
    ]
  }
}
```

---

## 2. 获取杂谈列表

获取杂谈分类下的文章列表，支持分页。

**接口地址**: `GET /api/v1/articles/essay`

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
    "content": [
      {
        "id": 2,
        "title": "周末的咖啡时光",
        "summary": "看着咖啡师调温、称豆、控水，像极了调优服务：都是对细节的执念。",
        "publishDate": "2024-11-14",
        "category": "杂谈",
        "tags": ["生活", "思考"]
      }
    ],
    "page": 1,
    "size": 16,
    "total": 28,
    "totalPages": 2
  }
}
```

---

## 3. 获取技术文章列表

获取技术分类下的文章列表，支持分页。

**接口地址**: `GET /api/v1/articles/tech`

**请求参数**:

| 参数名 | 类型 | 必填 | 默认值 | 说明 |
|--------|------|------|--------|------|
| page | int | 否 | 1 | 页码 |
| size | int | 否 | 16 | 每页数量 |

**响应示例**: 同杂谈列表

---

## 4. 获取文章详情

根据文章ID获取文章详细内容。

**接口地址**: `GET /api/v1/articles/{id}`

**路径参数**:

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| id | long | 是 | 文章ID |

**响应示例**:

```json
{
  "code": 200,
  "message": "success",
  "data": {
    "id": 1,
    "title": "MySQL 索引优化实践",
    "content": "# MySQL 索引优化实践\n\n这篇文章记录了我优化热点查询时的几个思路...",
    "summary": "拆解 explain 输出、索引覆盖程度，到如何避免回表与排序。",
    "category": "技术",
    "tags": ["MySQL", "索引优化"],
    "publishDate": "2024-11-12T10:00:00",
    "updateDate": "2024-11-12T10:00:00",
    "viewCount": 234,
    "status": "published"
  }
}
```

---

## 5. 搜索文章

根据关键词搜索文章。

**接口地址**: `GET /api/v1/articles/search`

**请求参数**:

| 参数名 | 类型 | 必填 | 默认值 | 说明 |
|--------|------|------|--------|------|
| q | string | 是 | - | 搜索关键词 |
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
        "summary": "拆解 explain 输出...",
        "publishDate": "2024-11-12",
        "category": "技术",
        "tags": ["MySQL", "索引优化"]
      }
    ],
    "page": 1,
    "size": 10,
    "total": 5,
    "totalPages": 1
  }
}
```

---

## 6. 获取所有文章列表

获取所有已发布的文章列表，支持按分类筛选和分页。

**接口地址**: `GET /api/v1/articles`

**请求参数**:

| 参数名 | 类型 | 必填 | 默认值 | 说明 |
|--------|------|------|--------|------|
| category | string | 否 | - | 分类筛选 (技术/杂谈) |
| page | int | 否 | 1 | 页码 |
| size | int | 否 | 16 | 每页数量 |

**响应示例**: 同杂谈列表
