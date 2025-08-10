<template>
  <div class="p-4">
    <h2 class="text-xl font-bold mb-4">新建刷数模版</h2>

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
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'

const router = useRouter()
const form = ref({ name: '', sqlText: '', kafkaCluster: '', kafkaTopic: '', rateLimit: '' })

const saveTemplate = async () => {
  try {
    await axios.post('/api/templates', form.value)
    alert('保存成功')
    await router.push('/templates')
  } catch (err) {
    console.error('保存失败', err)
    alert('保存失败')
  }
}
</script>
