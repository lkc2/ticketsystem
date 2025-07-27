import axios from 'axios'

// 取得所有客服單
export function getTickets() {
  return axios.get('/api/tickets').then(res => res.data)
}

// 新增客服單
export function createTicket(ticket) {
  return axios.post('/api/tickets', ticket).then(res => res.data)
}

// 刪除客服單
export function deleteTicket(id) {
  return axios.delete(`/api/tickets/${id}`).then(res => res.data)
}

// 編輯客服單
export function updateTicket(id, ticket) {
  return axios.put(`/api/tickets/${id}`, ticket).then(res => res.data)
}

// 搜尋客服單（標題或內容關鍵字）
export function searchTickets(keyword) {
  return axios.get(`/api/tickets/search?keyword=${encodeURIComponent(keyword)}`).then(res => res.data)
}

// 依狀態查詢
export function getTicketsByStatus(status) {
  return axios.get(`/api/tickets/status/${status}`).then(res => res.data)
}
