<template>
  <el-dialog v-model="visible" :title="isEdit ? '編輯客服單' : '新增客服單'" width="500px" @close="resetForm">
    <el-form :model="form" ref="elFormRef" :rules="rules" label-width="100px">
      <el-form-item label="標題" prop="title">
        <el-input v-model="form.title" autocomplete="off" />
      </el-form-item>
      <el-form-item label="內容" prop="content">
        <el-input type="textarea" v-model="form.content" :rows="4"/>
      </el-form-item>
      <el-form-item label="狀態" prop="status">
        <el-select v-model="form.status">
          <el-option label="待處理" value="open"/>
          <el-option label="已完成" value="done"/>
        </el-select>
      </el-form-item>
      <el-form-item label="優先度" prop="priority">
        <el-select v-model="form.priority">
          <el-option label="高" value="high"/>
          <el-option label="中" value="mid"/>
          <el-option label="低" value="low"/>
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
import { useTicketStore } from '@/stores/ticket'
const visible = ref(false)
const isEdit = ref(false)
const elFormRef = ref(null)
const store = useTicketStore()
const form = ref({
  title: '',
  content: '',
  status: 'open',
  priority: 'mid',
})
const rules = {
  title: [{ required: true, message: '請輸入標題', trigger: 'blur' }],
  content: [{ required: true, message: '請輸入內容', trigger: 'blur' }]
}

const emit = defineEmits(['submit'])

async function handleSubmit() {
  await store.addTicket(form.value)
  // 送出後可清空表單或回列表頁
}

function open(data) {
  visible.value = true
  isEdit.value = !!data
  if (data) {
    form.value = { ...data }
  } else {
    form.value = { title: '', content: '', status: 'open', priority: 'mid' }
  }
}
function resetForm() {
  elFormRef.value && elFormRef.value.resetFields()
}
function submitForm() {
  elFormRef.value.validate((valid) => {
    if (valid) {
      emit('submit', { ...form.value, id: isEdit.value ? form.value.id : undefined })
      visible.value = false
      resetForm()
    }
  })
}

defineExpose({ open })
</script>
