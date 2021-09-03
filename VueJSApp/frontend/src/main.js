import Vue from 'vue'
import App from './App.vue'
import './registerServiceWorker'
import router from './router'
import store from './store'

//axios
import axios from 'axios'
import VueAxios from 'vue-axios'
import vuetify from './plugins/vuetify'

Vue.config.productionTip = false

//axios
Vue.use(VueAxios, axios)

//API URL
axios.defaults.baseURL = "http://localhost:8080/api/v1"

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
