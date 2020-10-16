import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import "bulma/bulma.sass";
import Buefy from "buefy";
import "buefy/dist/buefy.css";
import "@mdi/font/css/materialdesignicons.css";
import axios from "axios";
import "@fortawesome/fontawesome-free/css/all.css";

Vue.config.productionTip = false;
Vue.prototype.$http = axios;
Vue.use(Buefy);
require("@/assets/style.css");

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount("#app");
