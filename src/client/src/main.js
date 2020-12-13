import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import JsonExcel from "vue-json-excel";
import Buefy from "buefy";
import axios from "axios";
import "bulma/bulma.sass";
import "buefy/dist/buefy.css";
import "@mdi/font/css/materialdesignicons.css";
import "@fortawesome/fontawesome-free/css/all.css";
import "vue-multiselect/dist/vue-multiselect.min.css";
import "vue-swatches/dist/vue-swatches.css";
import titleMixin from "./mixins/titleMixin";

Vue.mixin(titleMixin);
Vue.component("downloadExcel", JsonExcel);
Vue.use(Buefy);
Vue.use(require("vue-moment"));
require("@/assets/style.css");

Vue.config.productionTip = false;
Vue.prototype.$http = axios;

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount("#app");
