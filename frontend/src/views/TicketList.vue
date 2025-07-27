<template>
  <div class="ticket-list">
    <div class="title-row">
      <h2>客服單管理</h2>
      <div>
        <el-input v-model="keyword" placeholder="搜尋標題/內容" style="width: 200px; margin-right: 12px" @keyup.enter="onSearch"/>
        <el-button @click="onSearch" type="info">搜尋</el-button>
        <el-button type="primary" @click="openAddForm" size="large">新增客服單</el-button>
      </div>
    </div>
    <el-table :data="store.tickets" stripe border style="width: 100%; font-size: 17px;">
      <el-table-column prop="id" label="ID" width="90" align="center" />
      <el-table-column prop="title" label="標題" align="center" />
      <el-table-column prop="content" label="內容" align="center" />
      <el-table-column prop="status" label="狀態" align="center" />
      <el-table-column prop="priority" label="優先度" align="center" />
      <el-table-column prop="createdAt" label="建立時間" align="center"
        v-slot="{ row }">
        {{ formatDate(row.createdAt) }}
      </el-table-column>
      <el-table-column label="操作" align="center" width="180">
        <template #default="scope">
          <el-button size="small" @click="openEditForm(scope.row)" type="success">編輯</el-button>
          <el-popconfirm title="確認刪除此客服單？" @confirm="remove(scope.row.id)">
            <template #reference>
              <el-button size="small" type="danger">刪除</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <ticket-form ref="formRef" @submit="handleSubmit" />
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useTicketStore } from '@/stores/ticket'
import TicketForm from '@/components/TicketForm.vue'
import { ElMessage } from 'element-plus'

const store = useTicketStore()
const formRef = ref(null)
const keyword = ref('')

onMounted(() => {
  store.fetchTickets()
})

function openAddForm() {
  formRef.value.open()
}
function openEditForm(ticket) {
  formRef.value.open(ticket)
}
async function handleSubmit(ticket) {
  try {
    if (ticket.id) {
      await store.updateTicket(ticket.id, ticket)
      ElMessage.success('修改成功')
    } else {
      await store.addTicket(ticket)
      ElMessage.success('新增成功')
    }
  } catch (e) {
    ElMessage.error('操作失敗：' + (e.response?.data?.message || e.message))
  }
}
async function remove(id) {
  try {
    await store.deleteTicket(id)
    ElMessage.success('刪除成功')
  } catch (e) {
    ElMessage.error('刪除失敗：' + (e.response?.data?.message || e.message))
  }
}
async function onSearch() {
  if (keyword.value) {
    await store.search(keyword.value)
  } else {
    await store.fetchTickets()
  }
}
function formatDate(str) {
  if (!str) return '-'
  return str.replace('T', ' ').slice(0, 19)
}
</script>

<style scoped>
.ticket-list {
  max-width: 1100px;
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
  font-size: 17px;
}
</style>
