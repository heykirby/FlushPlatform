<template>

  <div>
    <form @submit.prevent="addUser">
      <input v-model="username" placeholder="Name" />
      <input v-model.lazy="password" placeholder="PassWord" type="password" />
      <input v-model.lazy="email" placeholder="Email" />
      <input v-model.lazy="phone" placeholder="phone" />
      <input v-model.lazy="status" placeholder="status" />
      <button type="submit">Add</button>
    </form>
    <ul>
      <li v-for="user in users" :key="user.id">
        {{ user.username }} {{ user.email }} {{ user.status }} <button @click="deleteUser(user.id)">删除</button>
      </li>
    </ul>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const users = ref([])
const username = ref('')
const password = ref('')
const email = ref('')
const phone = ref('')
const status = ref(1)

const loadUsers = async () => {
  const res = await axios.get('/api/users')
  users.value = res.data
}

const addUser = async () => {
  await axios.post('/api/users', {
    username: username.value,
    password: password.value,
    email: email.value,
    phone: phone.value,
    status: status.value
  })
  username.value = ''
  password.value = ''
  email.value = ''
  phone.value = ''
  status.value = null
  await loadUsers()
}
const deleteUser = async (id) => {
  await axios.delete(`/api/users/${id}`)
  await loadUsers()
}
onMounted(loadUsers)
</script>
