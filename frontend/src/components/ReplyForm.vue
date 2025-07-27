<template>
  <el-dialog v-model="visible" title="新增回覆" width="500px" @close="resetForm">
    <el-form :model="form" ref="elFormRef" :rules="rules" label-width="90px">
      <el-form-item label="回覆內容" prop="content">
        <el-input type="textarea" v-model="form.content" :rows="4"/>
      </el-form-item>
      <el-form-item label="用戶ID" prop="userId">
        <el-input v-model="form.userId" autocomplete="off" />
      </el-form-item>
      <el-form-item label="客服單ID" prop="ticketId">
        <el-input v-model="form.ticketId" autocomplete="off" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="submitForm">新增</el-button>
    </template>
  </el-dialog>
</template>

<script setup>
import { ref } from 'vue'
const visible = ref(false)
const elFormRef = ref(null)
const form = ref({
  content: '',
  userId: '',
  ticketId: ''
})
const rules = {
  content: [{ required: true, message: '請輸入回覆內容', trigger: 'blur' }],
  userId: [{ required: true, message: '請輸入用戶ID', trigger: 'blur' }],
  ticketId: [{ required: true, message: '請輸入客服單ID', trigger: 'blur' }],
}

const emit = defineEmits(['submit'])
function open(ticketId = '', userId = '') {
  visible.value = true
  form.value = {
    content: '',
    ticketId: ticketId || '',
    userId: userId || '',
  }
}
function resetForm() {
  elFormRef.value && elFormRef.value.resetFields()
}
function submitForm() {
  elFormRef.value.validate((valid) => {
    if (valid) {
      emit('submit', { ...form.value })
      visible.value = false
      resetForm()
    }
  })
}
defineExpose({ open })
</script>
