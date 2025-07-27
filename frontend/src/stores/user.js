import { defineStore } from 'pinia'
import * as userApi from '@/api/user'

export const useUserStore = defineStore('user', {
  state: () => ({
    users: [],
    loading: false,
    error: null,
  }),
  actions: {
    async fetchUsers() {
      this.loading = true
      try {
        this.users = await userApi.getUsers()
      } catch (e) {
        this.error = e.message
      } finally {
        this.loading = false
      }
    },
    async addUser(user) {
      await userApi.createUser(user)
      await this.fetchUsers()
    },
    async deleteUser(id) {
      await userApi.deleteUser(id)
      await this.fetchUsers()
    },
    async updateUser(id, user) {
      await userApi.updateUser(id, user)
      await this.fetchUsers()
    }
  }
})
