# 客服回報管理系統 (Customer Ticket System)

## 📝 專案介紹
本專案是一套模擬企業內部「客服單回報」的後端管理系統，包含用戶、客服單、回覆等功能，並整合 Redis 快取，對標台灣 Java 後端求職需求。

## 🚀 功能特色
- [x] 用戶 CRUD（註冊/查詢/更新/刪除）
- [x] 客服單 CRUD
- [x] 回覆 CRUD
- [x] Redis 快取（用戶資訊）
- [x] RESTful API 設計
- [x] Swagger 文件/測試
- [x] 支援 Postman 測試

## 🛠 技術棧
- Java 21
- Spring Boot 3.5.3
- MyBatis-Plus 3.5.12
- MySQL 8.x
- Redis 7.x
- Swagger
- Postman
- 前端：Vue 3（規劃中）

## 主要 API 路徑：
- /api/users 用戶管理
- /api/tickets 客服單管理
- /api/replies 回覆管理

## 🔧 安裝與執行步驟

1. 下載原始碼  
   ```bash
   git clone https://github.com/你的帳號/ticketsystem.git
   cd ticketsystem
