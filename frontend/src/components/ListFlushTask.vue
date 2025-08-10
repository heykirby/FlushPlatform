<template>
  <div>
    <button @click="gotoFlushTemplates" class="bg-blue-500 text-white px-4 py-2 rounded mb-4">
      刷数模版页面
    </button>
    <h3>进行中任务</h3>
    <ul>
      <li v-for="task in inProgress" :key="task.id">
        {{ task.id }}: {{ task.templateId }}: {{ task.processRows }}/{{ task.totalRows }} - {{ task.status }} - {{ task.startTime}}} : {{ task.endTime}}
      </li>
    </ul>

    <h3>已完成任务</h3>
    <ul>
      <li v-for="task in finished" :key="task.id">
        {{ task.id }}: {{ task.templateId }}: {{ task.processRows }}/{{ task.totalRows }} - {{ task.status }} - {{ task.startTime}}} : {{ task.endTime}}
      </li>
    </ul>
  </div>
</template>

<script setup>
import { ref, onMounted, onUnmounted } from 'vue'
import SockJS from 'sockjs-client'
import { Client } from '@stomp/stompjs'
import axios from "axios";
import {useRouter} from "vue-router";
const router = useRouter()

const inProgress = ref([])
const finished = ref([])

async function loadTasks() {
  try {
    const [inRes, finRes] = await Promise.all([
      axios.get('/api/task/running'),
      axios.get('/api/task/finished')
    ])
    inProgress.value = inRes.data
    finished.value   = finRes.data
  } catch (e) {
    console.error('完整url:', e.config?.url)
    console.error('状态码:', e.response?.status)
  }
}

const gotoFlushTemplates = () => {
  router.push('/templates')
}


// 连接 WebSocket
let stompClient = null
const connectWebSocket = () => {
  stompClient = new Client({
    webSocketFactory: () => new SockJS('http://localhost:8081/ws/progress'),
    reconnectDelay: 5000,
    onConnect: () => {
      console.log('✅ WebSocket connected')

      // 订阅服务器推送的任务进度
      stompClient.subscribe('/topic/progress', (message) => {
        const progress = JSON.parse(message.body)
        updateTaskProgress(progress)
      })
    }
  })
  stompClient.activate()
}

// 更新任务进度
const updateTaskProgress = (progress) => {
  const task = inProgress.value.find(t => t.id === progress.id)
  if (task) {
    task.templateId = progress.templateId
    task.processRows = progress.processRows
    task.totalRows = progress.totalRows
    task.status = progress.status
    task.startTime = progress.startTime
    task.endTime = progress.endTime
    if (task.status === 'SUCCESS') {
      loadTasks()
    }
  } else {
    // 有可能是新任务开始了
    inProgress.value.push(progress)
  }
}

// 断开连接
onUnmounted(() => {
  if (stompClient) {
    stompClient.deactivate()
  }
})

// 启动加载
onMounted(() => {
  loadTasks()
  connectWebSocket()
})

</script>
