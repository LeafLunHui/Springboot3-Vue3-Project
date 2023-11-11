import './assets/main.scss'
import 'element-plus/dist/index.css'

import { createApp } from 'vue'
import router from './router/index'
import ElementPlus from 'element-plus'
import App from './App.vue'
import { createPinia } from 'pinia'
import { createPersistedState } from 'pinia-persistedstate-plugin'
import locale from 'element-plus/dist/locale/zh-cn.js'


const persist = createPersistedState()
const pinia = createPinia()
pinia.use(persist)

const app = createApp(App)

app.use(router)
app.use(ElementPlus,{locale})
app.use(pinia)


app.mount('#app')

// createApp(App).use(router).use(ElementPlus).mount('#app')
