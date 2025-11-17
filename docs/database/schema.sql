-- 个人博客数据库表结构

CREATE DATABASE IF NOT EXISTS personal_blog DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE personal_blog;

-- 文章表
CREATE TABLE IF NOT EXISTS article (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '文章ID',
    title VARCHAR(255) NOT NULL COMMENT '文章标题',
    summary TEXT COMMENT '文章摘要',
    content LONGTEXT COMMENT '文章内容(Markdown)',
    category VARCHAR(50) NOT NULL COMMENT '分类(技术/杂谈)',
    tags JSON COMMENT '标签数组',
    publish_date DATETIME NOT NULL COMMENT '发布时间',
    update_date DATETIME NOT NULL COMMENT '更新时间',
    view_count INT DEFAULT 0 COMMENT '浏览次数',
    status VARCHAR(20) NOT NULL DEFAULT 'draft' COMMENT '状态(published/draft)',
    INDEX idx_category (category),
    INDEX idx_status (status),
    INDEX idx_publish_date (publish_date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章表';

-- 瞬间表
CREATE TABLE IF NOT EXISTS moment (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '瞬间ID',
    title VARCHAR(255) NOT NULL COMMENT '瞬间标题',
    content TEXT NOT NULL COMMENT '瞬间内容',
    publish_date DATETIME NOT NULL COMMENT '发布时间',
    tags JSON COMMENT '标签数组',
    INDEX idx_publish_date (publish_date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='瞬间表';

-- 推荐表
CREATE TABLE IF NOT EXISTS recommend (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '推荐ID',
    type VARCHAR(20) NOT NULL COMMENT '类型(anime/movie/book)',
    title VARCHAR(255) NOT NULL COMMENT '标题',
    author VARCHAR(255) COMMENT '作者/导演',
    genre VARCHAR(255) COMMENT '类型/风格',
    rating DECIMAL(3,1) COMMENT '评分',
    year INT COMMENT '年份',
    description TEXT COMMENT '描述',
    cover_url VARCHAR(500) COMMENT '封面图片URL',
    bangumi_id BIGINT COMMENT 'Bangumi平台ID',
    bangumi_url VARCHAR(500) COMMENT 'Bangumi平台URL',
    INDEX idx_type (type)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='推荐表';

-- 待办事项表
CREATE TABLE IF NOT EXISTS todo (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '待办ID',
    title VARCHAR(255) NOT NULL COMMENT '待办标题',
    description TEXT COMMENT '描述',
    status VARCHAR(20) NOT NULL COMMENT '状态(待开始/进行中/已完成)',
    priority VARCHAR(20) NOT NULL COMMENT '优先级(高/中/低)',
    deadline DATE COMMENT '截止日期',
    create_date DATETIME NOT NULL COMMENT '创建时间',
    update_date DATETIME NOT NULL COMMENT '更新时间',
    INDEX idx_status (status),
    INDEX idx_deadline (deadline)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='待办事项表';

-- 关于信息表
CREATE TABLE IF NOT EXISTS about (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    name VARCHAR(100) NOT NULL COMMENT '姓名',
    role VARCHAR(255) NOT NULL COMMENT '职业/身份',
    skills TEXT NOT NULL COMMENT '技能栈',
    description TEXT NOT NULL COMMENT '个人简介',
    email VARCHAR(255) COMMENT '邮箱',
    github VARCHAR(255) COMMENT 'GitHub地址',
    avatar_url VARCHAR(500) COMMENT '头像URL',
    update_date DATETIME NOT NULL COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='关于信息表';

-- 统计信息表
CREATE TABLE IF NOT EXISTS stats (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    article_count INT DEFAULT 0 COMMENT '文章总数',
    note_count INT DEFAULT 0 COMMENT '笔记总数',
    visit_count VARCHAR(50) COMMENT '访问量',
    persist_days INT DEFAULT 0 COMMENT '坚持天数',
    update_date DATETIME NOT NULL COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='统计信息表';

-- 分类表（可选，也可以从文章表动态统计）
CREATE TABLE IF NOT EXISTS category (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '分类ID',
    name VARCHAR(50) NOT NULL UNIQUE COMMENT '分类名称',
    article_count INT DEFAULT 0 COMMENT '文章数量'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='分类表';

-- 标签表（可选，也可以从文章表动态统计）
CREATE TABLE IF NOT EXISTS tag (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '标签ID',
    name VARCHAR(50) NOT NULL UNIQUE COMMENT '标签名称',
    use_count INT DEFAULT 0 COMMENT '使用次数'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='标签表';

-- 管理员表
CREATE TABLE IF NOT EXISTS admin (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '管理员ID',
    username VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名',
    password VARCHAR(255) NOT NULL COMMENT '密码(加密)',
    name VARCHAR(100) COMMENT '姓名',
    email VARCHAR(255) COMMENT '邮箱',
    create_date DATETIME NOT NULL COMMENT '创建时间',
    last_login DATETIME COMMENT '最后登录时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理员表';

-- IP访问记录表
CREATE TABLE IF NOT EXISTS ip_visit (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '记录ID',
    ip VARCHAR(50) NOT NULL COMMENT 'IP地址',
    location VARCHAR(255) COMMENT '地理位置',
    visit_count INT DEFAULT 1 COMMENT '访问次数',
    last_visit DATETIME NOT NULL COMMENT '最后访问时间',
    create_date DATETIME NOT NULL COMMENT '首次访问时间',
    INDEX idx_ip (ip),
    INDEX idx_last_visit (last_visit)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='IP访问记录表';

-- IP白名单表
CREATE TABLE IF NOT EXISTS ip_whitelist (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    ip VARCHAR(50) NOT NULL UNIQUE COMMENT 'IP地址',
    reason VARCHAR(255) COMMENT '原因',
    add_time DATETIME NOT NULL COMMENT '添加时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='IP白名单表';

-- IP黑名单表
CREATE TABLE IF NOT EXISTS ip_blacklist (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    ip VARCHAR(50) NOT NULL UNIQUE COMMENT 'IP地址',
    reason VARCHAR(255) COMMENT '原因',
    add_time DATETIME NOT NULL COMMENT '添加时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='IP黑名单表';

-- 初始化数据（可选）
INSERT INTO about (name, role, skills, description, email, github, avatar_url, update_date) VALUES
('Penn', '后端开发 · Java / Spring · 喜欢记录', 'Java / Spring · MySQL · Redis · Kafka · Docker',
 '喜欢把遇到的问题写下来，把解决过程拆成可复用的小块。写作既是复盘，也是学习的反馈循环。',
 'blog@penn.dev', 'https://github.com/penn', NULL, NOW());

INSERT INTO stats (article_count, note_count, visit_count, persist_days, update_date) VALUES
(0, 0, '0', 0, NOW());

INSERT INTO category (name, article_count) VALUES
('技术', 0),
('杂谈', 0);
