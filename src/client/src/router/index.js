import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import Bug from "../views/bug-views/Bug.vue";
import Account from "../views/account-views/Account.vue";
import User from "../views/user-views/User.vue";

Vue.use(VueRouter);

const routes = [
    {
        path: "/",
        redirect: "/All/bug"
    },
    {
        path: "/",
        name: "Home",
        component: Home
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
