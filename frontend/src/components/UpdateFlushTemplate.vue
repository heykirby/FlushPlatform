<template>
  <div>
    <h2>更新刷数模版</h2>

    <div v-if="loading">加载中...</div>
    <div v-else>
      <form @submit.prevent="saveTemplate" class="space-y-4">
        <div>
          <label class="block mb-1">模版名称：</label>
          <input v-model="form.name" class="border px-2 py-1 w-full" required />
        </div>
        <div>
          <label class="block mb-1">sql：</label>
          <input v-model="form.sqlText" class="border px-2 py-1 w-full" required />
        </div>
        <div>
          <label class="block mb-1">kafka cluster：</label>
          <input v-model="form.kafkaCluster" class="border px-2 py-1 w-full" required />
        </div>
        <div>
          <label class="block mb-1">Kafka Topic：</label>
          <input v-model="form.kafkaTopic" class="border px-2 py-1 w-full" required />
        </div>
        <div>
          <label class="block mb-1">限速：</label>
          <input v-model="form.rateLimit" class="border px-2 py-1 w-full" required />
        </div>
        <button type="submit" class="bg-green-500 text-white px-4 py-2 rounded">保存</button>
      </form>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import axios from 'axios'

const route = useRoute()
const router = useRouter()
const id = route.query.id
const form = ref({ id: '', name: '', sqlText: '', kafkaCluster: '', kafkaTopic: '', rateLimit: '' })
const loading = ref(true)

// 获取模版详情
async function fetchTemplateDetail() {
  try {
    const res = await axios.get(`/api/templates/${id}`)
    form.value = res.data
  } catch (err) {
    alert('获取模版失败')
  } finally {
    loading.value = false
  }
}

onMounted(fetchTemplateDetail)

// 提交更新
async function saveTemplate() {
  try {
    await axios.put(`/api/templates`, form.value)
    alert('更新成功')
    await router.push('/templates')
  } catch (err) {
    alert('更新失败')
  }
}
</script>
