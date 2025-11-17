import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: () => import('@/views/HomeView.vue')
    },
    {
      path: '/essay',
      name: 'essay',
      component: () => import('@/views/EssayView.vue')
    },
    {
      path: '/tech',
      name: 'tech',
      component: () => import('@/views/TechView.vue')
    },
    {
      path: '/moment',
      name: 'moment',
      component: () => import('@/views/MomentView.vue')
    },
    {
      path: '/article/:id',
      name: 'article',
      component: () => import('@/views/ArticleView.vue')
    },
    {
      path: '/recommend',
      name: 'recommend',
      redirect: '/recommend/anime',
      component: () => import('@/views/RecommendView.vue'),
      children: [
        {
          path: 'anime',
          name: 'recommend-anime',
          component: () => import('@/views/recommend/AnimeView.vue')
        },
        {
          path: 'movie',
          name: 'recommend-movie',
          component: () => import('@/views/recommend/MovieView.vue')
        },
        {
          path: 'book',
          name: 'recommend-book',
          component: () => import('@/views/recommend/BookView.vue')
        }
      ]
    },
    {
      path: '/category',
      name: 'category',
      component: () => import('@/views/CategoryView.vue')
    },
    {
      path: '/tags',
      name: 'tags',
      component: () => import('@/views/TagsView.vue')
    },
    {
      path: '/tags/:tagName',
      name: 'tag-detail',
      component: () => import('@/views/TagDetailView.vue')
    },
    {
      path: '/archive',
      name: 'archive',
      component: () => import('@/views/ArchiveView.vue')
    },
    {
      path: '/search',
      name: 'search',
      component: () => import('@/views/SearchView.vue')
    },
    {
      path: '/about',
      name: 'about',
      component: () => import('@/views/AboutView.vue')
    },
    {
      path: '/todolist',
      name: 'todolist',
      component: () => import('@/views/TodolistView.vue')
    }
  ]
})

export default router
