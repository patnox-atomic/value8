import Vue from 'vue'
import App from './App.vue'
import './registerServiceWorker'
import router from './router'
import store from './store'
//import api from "@/service/apiService";

//axios
import axios from 'axios'
import VueAxios from 'vue-axios'
import vuetify from './plugins/vuetify'
// Sync router with store
import { sync } from 'vuex-router-sync'
Vue.prototype.$http = axios

Vue.config.productionTip = false

//axios
Vue.use(VueAxios, axios)

//API URL
axios.defaults.baseURL = "http://localhost:8080/api/v1"

axios.defaults.headers.get['Accept'] = 'application/json'

const token = localStorage.getItem('token')
if (token) {
  axios.defaults.headers.common['Authorization'] = 'Bearer ' + token
}

// Sync store with router
sync(store, router)

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
