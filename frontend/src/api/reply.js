import axios from 'axios'

// 取得所有回覆
export function getReplies() {
  return axios.get('/api/replies').then(res => res.data)
}

// 取得某張客服單的所有回覆
export function getRepliesByTicketId(ticketId) {
  return axios.get(`/api/replies/ticket/${ticketId}`).then(res => res.data)
}

// 新增回覆
export function addReply(reply) {
  return axios.post('/api/replies', reply).then(res => res.data)
}

// 刪除回覆
export function deleteReply(id) {
  return axios.delete(`/api/replies/${id}`).then(res => res.data)
}

// 查詢單一回覆
export function getReplyById(id) {
  return axios.get(`/api/replies/${id}`).then(res => res.data)
}
