<template>
  <el-dialog v-model="visible" :title="isEdit ? '編輯用戶' : '新增用戶'" width="400px" @close="resetForm">
    <el-form :model="form" ref="elFormRef" :rules="rules" label-width="80px">
      <el-form-item label="帳號" prop="username">
        <el-input v-model="form.username" autocomplete="off" />
      </el-form-item>
      <el-form-item label="密碼" prop="password">
        <el-input v-model="form.password" type="password" autocomplete="off" />
      </el-form-item>
      <el-form-item label="角色" prop="role">
        <el-select v-model="form.role" placeholder="請選擇">
          <el-option label="管理員" value="admin" />
          <el-option label="客服" value="agent" />
          <el-option label="一般用戶" value="user" />
        </el-select>
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="submitForm">{{ isEdit ? '保存' : '新增' }}</el-button>
    </template>
  </el-dialog>
</template>

<script setup>
import { ref } from 'vue'
const visible = ref(false)
const isEdit = ref(false)
const elFormRef = ref(null)
const form = ref({
  username: '',
  password: '',
  role: 'user'
})
const rules = {
  username: [{ required: true, message: '請輸入帳號', trigger: 'blur' }],
  password: [{ required: true, message: '請輸入密碼', trigger: 'blur' }],
  role: [{ required: true, message: '請選擇角色', trigger: 'change' }]
}

const emit = defineEmits(['submit'])

function open(data) {
  visible.value = true
  isEdit.value = !!data
  if (data) {
    form.value = { ...data }
    // 編輯時密碼不顯示
    form.value.password = ''
  } else {
    form.value = { username: '', password: '', role: 'user' }
  }
}
function resetForm() {
  elFormRef.value && elFormRef.value.resetFields()
}
function submitForm() {
  elFormRef.value.validate(async (valid) => {
    if (valid) {
      emit('submit', { ...form.value, id: isEdit.value ? form.value.id : undefined })
      visible.value = false
      resetForm()
    }
  })
}

defineExpose({ open })
</script>
