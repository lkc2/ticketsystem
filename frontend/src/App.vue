<template>
  <el-container style="min-height: 100vh; background: #f6f6fa;">
    <el-header height="70px" class="main-header">
      <div class="header-content">
        <h1 class="logo">客服回報管理系統</h1>
        <nav>
          <el-menu mode="horizontal" :default-active="activeMenu" router>
            <el-menu-item index="/users">用戶管理</el-menu-item>
            <el-menu-item index="/tickets">客服單管理</el-menu-item>
            <el-menu-item index="/replies">回覆管理</el-menu-item>
          </el-menu>
        </nav>
      </div>
    </el-header>
    <el-main style="padding: 0">
      <router-view />
    </el-main>
  </el-container>
</template>

<script setup>
import { ref, watch } from 'vue'
import { useRoute } from 'vue-router'

const route = useRoute()
const activeMenu = ref(route.path)
watch(() => route.path, val => {
  activeMenu.value = val
})
</script>

<style scoped>
.main-header {
  background: linear-gradient(90deg, #4660f2 30%, #608bff 100%);
  box-shadow: 0 2px 16px #0001;
}
.header-content {
  max-width: 1200px;
  margin: 0 auto;
  display: flex;
  align-items: center;
  height: 100%;
  justify-content: space-between;
}
.logo {
  color: #fff;
  font-weight: bold;
  letter-spacing: 2px;
  font-size: 2rem;
  margin: 0;
  user-select: none;
}
nav {
  flex-shrink: 0;
}
.el-menu {
  background: transparent;
  border: none;
}
.el-menu-item {
  color: #fff;
  font-size: 17px;
}
.el-menu-item.is-active,
.el-menu-item:hover {
  background: #fff4 !important;
  color: #2940b5 !important;
}
</style>
