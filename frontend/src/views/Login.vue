<template>
  <div class="login-wrap">
    <el-card class="login-card">
      <h2>用戶登入</h2>
      <el-form :model="form" @keyup.enter="login">
        <el-form-item label="帳號">
          <el-input v-model="form.username" autocomplete="username" />
        </el-form-item>
        <el-form-item label="密碼">
          <el-input type="password" v-model="form.password" autocomplete="current-password" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="login" :loading="loading">登入</el-button>
        </el-form-item>
        <div v-if="error" style="color: red;">{{ error }}</div>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import axios from 'axios'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import { useAuthStore } from '@/stores/auth'

const auth = useAuthStore()

const form = ref({
  username: '',
  password: ''
})
const loading = ref(false)
const error = ref('')
const router = useRouter()

async function login() {
  error.value = ''
  loading.value = true
  try {
    const res = await axios.post('/api/users/login', form.value)
    auth.setUser(res.data)
    ElMessage.success('登入成功')
    router.push('/tickets')
  } catch (e) {
    error.value = e.response?.data?.message || '登入失敗'
    ElMessage.error(error.value)
  } finally {
    loading.value = false
  }
}
</script>

<style scoped>
.login-wrap {
  min-height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background: #f5f6fa;
}
.login-card {
  min-width: 340px;
  padding: 40px 30px 20px 30px;
}
</style>
