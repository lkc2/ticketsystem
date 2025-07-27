import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import { createPinia } from 'pinia'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import '@/assets/style.css'
import JSONBig from 'json-bigint'
import axios from 'axios'

const app = createApp(App)
app.use(createPinia())
app.use(router)
app.use(ElementPlus)
app.mount('#app')

axios.defaults.transformResponse = [function (data) {
  try {
    return JSONBig({ storeAsString: true }).parse(data)
  } catch (e) {
    return data
  }
}]