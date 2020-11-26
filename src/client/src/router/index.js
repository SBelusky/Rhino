import Vue from "vue";
import VueRouter from "vue-router";

//Bug
import Bug from "../views/bug-views/Bug.vue";
//Account
import Account from "../views/account-views/Account.vue";
//User
import User from "../views/user-views/User.vue";

Vue.use(VueRouter);

const routes = [
    {
        path: "/",
        redirect: "/All/bug"
    },
    {
        path: "**/bug",
        component: Bug
    },
    {
        path: "/account",
        component: Account
    },
    {
        path: "/user",
        component: User
    }
];

const router = new VueRouter({
    mode: "history",
    base: process.env.BASE_URL,
    routes
});

export default router;
