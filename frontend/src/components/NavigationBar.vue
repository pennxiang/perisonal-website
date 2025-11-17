<template>
  <nav class="navigation-bar">
    <ul>
      <li><router-link to="/" :class="{ active: isActive('/') }">首页</router-link></li>
      <li><router-link to="/essay" :class="{ active: isActive('/essay') }">杂谈</router-link></li>
      <li><router-link to="/moment" :class="{ active: isActive('/moment') }">瞬间</router-link></li>
      <li><router-link to="/tech" :class="{ active: isActive('/tech') }">技术</router-link></li>
      <li><router-link to="/recommend" :class="{ active: isActive('/recommend') }">推荐</router-link></li>
      <li><router-link to="/todolist" :class="{ active: isActive('/todolist') }">待办</router-link></li>
      <li><router-link to="/category" :class="{ active: isActive('/category') }">分类</router-link></li>
      <li><router-link to="/tags" :class="{ active: isActive('/tags') }">标签</router-link></li>
      <li><router-link to="/search" :class="{ active: isActive('/search') }">搜索</router-link></li>
      <li><router-link to="/archive" :class="{ active: isActive('/archive') }">归档</router-link></li>
      <li><router-link to="/about" :class="{ active: isActive('/about') }">关于</router-link></li>
    </ul>
  </nav>
</template>

<script setup>
import { useRoute } from 'vue-router'
import { computed } from 'vue'

const route = useRoute()

const isActive = (path) => {
  return computed(() => route.path === path || route.path.startsWith(path + '/')).value
}
</script>
