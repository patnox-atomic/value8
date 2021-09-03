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

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
