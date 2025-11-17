# 推荐相关 API

## 1. 获取动漫推荐列表

获取动漫推荐列表。

**接口地址**: `GET /api/v1/recommend/anime`

**请求参数**:

| 参数名 | 类型 | 必填 | 默认值 | 说明 |
|--------|------|------|--------|------|
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
        "type": "anime",
        "title": "进击的巨人",
        "genre": "热血、悬疑",
        "rating": 9.8,
        "year": 2013,
        "description": "宏大的世界观设定，扣人心弦的剧情发展，每一集都充满惊喜。",
        "coverUrl": "/images/anime/shingeki.jpg"
      }
    ],
    "page": 1,
    "size": 10,
    "total": 15,
    "totalPages": 2
  }
}
```

---

## 2. 获取电影推荐列表

获取电影推荐列表。

**接口地址**: `GET /api/v1/recommend/movie`

**请求参数**:

| 参数名 | 类型 | 必填 | 默认值 | 说明 |
|--------|------|------|--------|------|
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
        "type": "movie",
        "title": "肖申克的救赎",
        "director": "弗兰克·德拉邦特",
        "genre": "剧情",
        "rating": 9.7,
        "year": 1994,
        "description": "关于希望、自由和救赎的经典之作。",
        "coverUrl": "/images/movie/shawshank.jpg"
      }
    ],
    "page": 1,
    "size": 10,
    "total": 20,
    "totalPages": 2
  }
}
```

---

## 3. 获取书单推荐列表

获取书籍推荐列表。

**接口地址**: `GET /api/v1/recommend/book`

**请求参数**:

| 参数名 | 类型 | 必填 | 默认值 | 说明 |
|--------|------|------|--------|------|
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
        "type": "book",
        "title": "深入理解Java虚拟机（第3版）",
        "author": "周志明",
        "genre": "技术",
        "rating": 9.5,
        "publishYear": 2019,
        "description": "Java 开发者必读经典，深入讲解 JVM 原理、垃圾回收、性能调优等核心知识。",
        "coverUrl": "/images/book/jvm.jpg"
      }
    ],
    "page": 1,
    "size": 10,
    "total": 25,
    "totalPages": 3
  }
}
```

---

## 4. 获取所有推荐

获取所有类型的推荐（动漫、电影、书籍）。

**接口地址**: `GET /api/v1/recommend`

**请求参数**:

| 参数名 | 类型 | 必填 | 默认值 | 说明 |
|--------|------|------|--------|------|
| type | string | 否 | - | 类型筛选 (anime/movie/book) |
| page | int | 否 | 1 | 页码 |
| size | int | 否 | 10 | 每页数量 |

**响应示例**: 根据type返回对应类型的推荐列表，格式同上
