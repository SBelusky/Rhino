import Vue from "vue";
import VueRouter from "vue-router";

//Bug
import Bug from "../views/bug-views/Bug.vue";
//Account
import Account from "../views/account-views/Account.vue";
//User
import User from "../views/user-views/User.vue";
import UserDetail from "../views/user-views/UserDetail.vue";
//Project
import Project from "../views/project-views/Project.vue";
import ProjectDetail from "../views/project-views/ProjectDetail.vue";
//Priority
import Priority from "../views/priority-views/Priority.vue";

//Status
import Status from "../views/status-views/Status.vue";

//Reproducibility
import Reproducibility from "../views/reproducibility-views/Reproducibility.vue";

//Category
import Category from "../views/category-views/Category.vue";

//Version
import Version from "../views/version-views/Version.vue";

Vue.use(VueRouter);

const routes = [
    {
        path: "/",
        redirect: "/www/project/1/bug"
    },
    //Bug
    {
        path: "/www/project/:id/bug",
        component: Bug
    },
    //Account
    {
        path: "/www/account",
        component: Account
    },
    //User
    {
        path: "/www/user",
        component: User
    },
    {
        path: "/www/user/:id",
        component: UserDetail
    },
    //Project
    {
        path: "/www/project",
        component: Project
    },
    {
        path: "/www/project/:id",
        component: ProjectDetail
    },
    //Priority
    {
        path: "/www/priority",
        component: Priority
    },
    //Status
    {
        path: "/www/status",
        component: Status
    },
    //Reproducibility
    {
        path: "/www/reproducibility",
        component: Reproducibility
    },
    //Category
    {
        path: "/www/project/:id/category",
        component: Category
    },
    //Version
    {
        path: "/www/project/:id/version",
        component: Version
    }
];

const router = new VueRouter({
    mode: "history",
    base: process.env.BASE_URL,
    routes
});

export default router;
