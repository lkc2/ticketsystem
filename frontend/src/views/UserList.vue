<template>
  <div class="user-list">
    <div class="title-row">
      <h2>用戶列表</h2>
      <el-button type="primary" @click="openAddForm" size="large">新增用戶</el-button>
    </div>
    <el-table :data="store.users" stripe border style="width: 100%; font-size: 18px;">
      <el-table-column prop="id" label="ID" width="100" align="center" />
      <el-table-column prop="username" label="帳號" align="center" />
      <el-table-column prop="role" label="角色" align="center" />
      <el-table-column prop="createdAt" label="建立時間" align="center" width="180"
        v-slot="{ row }">
        {{ formatDate(row.createdAt) }}
      </el-table-column>
      <el-table-column label="操作" align="center" width="150">
        <template #default="scope">
          <el-button size="small" type="success" @click="openEditForm(scope.row)">編輯</el-button>
          <el-popconfirm title="確認刪除此用戶？" @confirm="remove(scope.row.id)">
            <template #reference>
              <el-button type="danger" size="small">刪除</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <user-form ref="formRef" @submit="handleSubmit" />
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import { useUserStore } from '@/stores/user'
import UserForm from '@/components/UserForm.vue'
import { ElMessage } from 'element-plus'

const store = useUserStore()
const formRef = ref(null)

onMounted(() => {
  store.fetchUsers()
})

function openAddForm() {
  formRef.value.open()
}
function openEditForm(user) {
  formRef.value.open(user)
}
async function handleSubmit(user) {
  try {
    if (user.id) {
      await store.updateUser(user.id, user)
      ElMessage.success('修改成功')
    } else {
      await store.addUser(user)
      ElMessage.success('新增成功')
    }
  } catch (e) {
    ElMessage.error('操作失敗：' + (e.response?.data?.message || e.message))
  }
}
async function remove(id) {
  try {
    await store.deleteUser(id)
    ElMessage.success('刪除成功')
  } catch (e) {
    ElMessage.error('刪除失敗：' + (e.response?.data?.message || e.message))
  }
}
function formatDate(str) {
  if (!str) return '-'
  return str.replace('T', ' ').slice(0, 19)
}
</script>

<style scoped>
.user-list {
  max-width: 900px;
  margin: 48px auto;
  padding: 32px 40px;
  background: #fff;
  border-radius: 20px;
  box-shadow: 0 4px 30px #0002;
}
.title-row {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}
.el-table th, .el-table td {
  font-size: 18px;
}
</style>
