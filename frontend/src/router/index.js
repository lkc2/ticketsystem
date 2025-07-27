import { createRouter, createWebHistory } from 'vue-router'
import UserList from '@/views/UserList.vue'
import TicketList from '@/views/TicketList.vue'
import ReplyList from '@/views/ReplyList.vue'
import Login from '@/views/Login.vue' 

const routes = [
  { path: '/', redirect: '/login' },
  { path: '/login', component: Login },
  { path: '/users', component: UserList },
  { path: '/tickets', component: TicketList },
  { path: '/replies', component: ReplyList },
]

export default createRouter({
  history: createWebHistory(),
  routes,
})
