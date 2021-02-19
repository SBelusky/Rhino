import Vue from "vue";
import App from "./App.vue";
import store from "./store";
import router from "./router";
import JsonExcel from "vue-json-excel";
import Buefy from "buefy";
import axios from "axios";
import "bulma/bulma.sass";
import "buefy/dist/buefy.css";
import "@mdi/font/css/materialdesignicons.css";
/* import "@fortawesome/fontawesome-free/css/all.css";*/
import "vue-multiselect/dist/vue-multiselect.min.css";
import "vue-swatches/dist/vue-swatches.css";
import tableMixin from "./mixins/tableMixin";

Vue.mixin(tableMixin);
Vue.component("downloadExcel", JsonExcel);
Vue.use(Buefy);
Vue.use(require("vue-moment"));
require("@/assets/style.css");
require("@fortawesome/fontawesome-free/css/all.css");
require("@mdi/font/css/materialdesignicons.css");

/* axios.defaults.baseURL = "http://localhost:8080/";
 */
/* axios.defaults.headers.common["Access-Control-Allow-Origin"] = "*"; */
/* axios.defaults.headers.common["Authorization"] = "Bearer " + localStorage.getItem("token"); */

Vue.config.productionTip = false;
Vue.prototype.$http = axios;

new Vue({
    store,
    router,
    render: h => h(App)
}).$mount("#app");
