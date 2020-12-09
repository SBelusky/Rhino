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
import "vue-multiselect/dist/vue-multiselect.min.css";
import "vue-swatches/dist/vue-swatches.css";
import JsonExcel from "vue-json-excel";

Vue.component("downloadExcel", JsonExcel);
Vue.use(Buefy);
require("@/assets/style.css");

Vue.config.productionTip = false;
Vue.prototype.$http = axios;

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount("#app");
