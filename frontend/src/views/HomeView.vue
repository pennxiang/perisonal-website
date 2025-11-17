<template>
  <NavigationBar />

  <header class="masthead">
    <h1>Penn 的生活日报</h1>
    <div class="tagline">记录后端技术 · 记录生活点滴</div>
  </header>

  <section v-if="homeData?.latestArticle" class="latest-hero">
    <div class="category">
      <h3>最新</h3>
    </div>
    <h2>{{ homeData.latestArticle.title }}</h2>
    <div class="byline">{{ homeData.latestArticle.publishDate }}</div>
    <p>
      <span class="dropcap">这</span>
      {{ homeData.latestArticle.summary }}
    </p>
    <div class="card-footer">
      <router-link class="read-more" :to="`/article/${homeData.latestArticle.id}`">查看全文 →</router-link>
    </div>
  </section>

  <main class="home-columns">
    <section class="column-block">
      <h2>杂谈</h2>
      <article v-for="essay in homeData?.essays" :key="essay.id" class="article">
        <h3>{{ essay.title }}</h3>
        <div class="byline">{{ essay.publishDate }}</div>
        <p>{{ essay.summary }}</p>
        <div class="card-footer">
          <router-link class="read-more" :to="`/article/${essay.id}`">查看全文 →</router-link>
        </div>
      </article>
    </section>

    <section class="column-block">
      <h2>技术</h2>
      <article v-for="tech in homeData?.techArticles" :key="tech.id" class="article">
        <h3>{{ tech.title }}</h3>
        <div class="byline">{{ tech.publishDate }}</div>
        <p>{{ tech.summary }}</p>
        <div class="card-footer">
          <router-link class="read-more" :to="`/article/${tech.id}`">查看全文 →</router-link>
        </div>
      </article>
    </section>

    <section class="column-block">
      <h3>瞬间</h3>
      <article v-for="moment in homeData?.moments" :key="moment.id" class="article">
        <h2>{{ moment.title }}</h2>
        <div class="byline">{{ moment.publishDate }}</div>
        <p>{{ moment.content }}</p>
        <div class="card-footer">
          <router-link class="read-more" :to="`/moment/${moment.id}`">查看全文 →</router-link>
        </div>
      </article>
    </section>
  </main>

  <FooterBar />
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { articleApi } from '@/api'
import NavigationBar from '@/components/NavigationBar.vue'
import FooterBar from '@/components/FooterBar.vue'

const homeData = ref(null)

onMounted(async () => {
  try {
    homeData.value = await articleApi.getHomeData()
  } catch (error) {
    console.error('Failed to load home data:', error)
  }
})
</script>
