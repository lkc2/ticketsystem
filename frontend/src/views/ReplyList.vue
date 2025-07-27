<template>
  <div class="reply-list">
    <div class="title-row">
      <h2>客服單回覆管理</h2>
      <el-button type="primary" @click="openForm" size="large">新增回覆</el-button>
    </div>
    <el-table :data="store.replies" stripe border style="width: 100%; font-size: 17px;">
      <el-table-column prop="id" label="ID" width="80" align="center" />
      <el-table-column prop="ticketId" label="客服單ID" align="center" />
      <el-table-column prop="userId" label="用戶ID" align="center" />
      <el-table-column prop="content" label="內容" align="center" />
      <el-table-column prop="createdAt" label="建立時間" align="center"
        v-slot="{ row }">
        {{ formatDate(row.createdAt) }}
      </el-table-column>
      <el-table-column label="操作" align="center" width="120">
        <template #default="scope">
          <el-popconfirm title="確認刪除此回覆？" @confirm="remove(scope.row.id, scope.row.ticketId)">
            <template #reference>
              <el-button type="danger" size="small">刪除</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <reply-form ref="formRef" @submit="handleAddReply" />
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import { useReplyStore } from '@/stores/reply'
import ReplyForm from '@/components/ReplyForm.vue'
import { ElMessage } from 'element-plus'

const store = useReplyStore()
const formRef = ref(null)

onMounted(() => {
  store.fetchReplies()
})

function openForm() {
  formRef.value.open()
}
async function handleAddReply(reply) {
  try {
    await store.addReply(reply)
    ElMessage.success('新增成功')
  } catch (e) {
    ElMessage.error('新增失敗：' + (e.response?.data?.message || e.message))
  }
}
async function remove(id, ticketId) {
  try {
    await store.deleteReply(id, ticketId)
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
.reply-list {
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
