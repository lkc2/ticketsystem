import { defineStore } from 'pinia'
import * as ticketApi from '@/api/ticket'
import { useAuthStore } from './auth'

export const useTicketStore = defineStore('ticket', {
  state: () => ({
    tickets: [],
    loading: false,
    error: null,
  }),
  actions: {
    async fetchTickets() {
      this.loading = true
      try {
        this.tickets = await ticketApi.getTickets()
      } catch (e) {
        this.error = e.message
      } finally {
        this.loading = false
      }
    },
    async addTicket(ticket) {
      const auth = useAuthStore()
      const ticketWithUser = { ...ticket, userId: auth.user?.id }
      await ticketApi.createTicket(ticketWithUser)
      await this.fetchTickets()
    },
    async deleteTicket(id) {
      await ticketApi.deleteTicket(id)
      await this.fetchTickets()
    },
    async updateTicket(id, ticket) {
      await ticketApi.updateTicket(id, ticket)
      await this.fetchTickets()
    },
    async search(keyword) {
      this.loading = true
      try {
        this.tickets = await ticketApi.searchTickets(keyword)
      } catch (e) {
        this.error = e.message
      } finally {
        this.loading = false
      }
    }
  }
})
