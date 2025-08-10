<template>
  <div class="p-4">
    <h2 class="text-xl font-bold mb-4">刷数模版列表</h2>
    <button @click="createTemplate" class="bg-blue-500 text-white px-4 py-2 rounded mb-4">
      新建模版
    </button>

    <button @click="gotoFlushTask" class="bg-blue-500 text-white px-4 py-2 rounded mb-4">
      刷数任务页面
    </button>

    <table class="table-auto w-full border">
      <thead>
      <tr>
        <th class="border px-4 py-2">模版名称</th>
        <th class="border px-4 py-2">name</th>
        <th class="border px-4 py-2">sqlText</th>
        <th class="border px-4 py-2">Kafka cluster</th>
        <th class="border px-4 py-2">Kafka Topic</th>
        <th class="border px-4 py-2">限速</th>
        <th class="border px-4 py-2">操作</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="tpl in templates" :key="tpl.id">
        <td class="border px-4 py-2">{{ tpl.name }}</td>sqlText
        <td class="border px-4 py-2">{{ tpl.sqlText }}</td>
        <td class="border px-4 py-2">{{ tpl.kafkaCluster }}</td>
        <td class="border px-4 py-2">{{ tpl.kafkaTopic }}</td>
        <td class="border px-4 py-2">{{ tpl.rateLimit }}</td>
        <td class="border px-4 py-2 space-x-2">
          <button class="text-green-600" @click="executeTemplate(tpl.id)">执行</button>
          <button class="text-blue-600" @click="editTemplate(tpl.id)">编辑</button>
        </td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import axios from "axios";

const router = useRouter()
const templates = ref([])

const loadTemplates = async () => {
  try {
    const res = await axios.get(`api/templates`)
    templates.value = res.data
  } catch (err) {
    console.error('加载模版失败', err)
  }
}

const gotoFlushTask = () => {
  router.push('/templates/task')
}

const createTemplate = () => {
  router.push('/templates/create')
}

const editTemplate = (id) => {
  router.push({ path: '/templates/update', query: { id } })
}

const executeTemplate = (id) => {
  axios.post('/api/task', id, { headers: { 'Content-Type': 'application/json' }})
  router.push('/templates/task')
}

onMounted(loadTemplates)
</script>
