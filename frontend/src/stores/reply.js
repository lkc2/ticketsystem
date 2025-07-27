import { defineStore } from 'pinia'
import * as replyApi from '@/api/reply'

export const useReplyStore = defineStore('reply', {
  state: () => ({
    replies: [],
    loading: false,
    error: null,
  }),
  actions: {
    async fetchReplies() {
      this.loading = true
      try {
        this.replies = await replyApi.getReplies()
      } catch (e) {
        this.error = e.message
      } finally {
        this.loading = false
      }
    },
    async fetchByTicket(ticketId) {
      this.loading = true
      try {
        this.replies = await replyApi.getRepliesByTicketId(ticketId)
      } catch (e) {
        this.error = e.message
      } finally {
        this.loading = false
      }
    },
    async addReply(reply) {
      await replyApi.addReply(reply)
      // 預設帶 ticketId 的話，刷新該單所有回覆
      if (reply.ticketId) {
        await this.fetchByTicket(reply.ticketId)
      } else {
        await this.fetchReplies()
      }
    },
    async deleteReply(id, ticketId) {
      await replyApi.deleteReply(id)
      // 刪除完自動刷新
      if (ticketId) {
        await this.fetchByTicket(ticketId)
      } else {
        await this.fetchReplies()
      }
    }
  }
})
