import axios from 'axios'

// 取得所有用戶
export function getUsers() {
  return axios.get('/api/users').then(res => res.data)
}

// 新增用戶
export function createUser(user) {
  return axios.post('/api/users', user).then(res => res.data)
}

// 刪除用戶
export function deleteUser(id) {
  return axios.delete(`/api/users/${id}`).then(res => res.data)
}

// 編輯用戶
export function updateUser(id, user) {
  return axios.put(`/api/users/${id}`, user).then(res => res.data)
}
