import Vue from "vue";
import VueRouter from "vue-router";

//Bug
import Bug from "../views/bug-views/Bug.vue";

//Account
import Account from "../views/account-views/Account.vue";

//User
import User from "../views/user-views/User.vue";
import UserDetail from "../views/user-views/UserDetail.vue";
import UserAdd from "../views/user-views/UserAdd.vue";

//Project
import Project from "../views/project-views/Project.vue";
import ProjectDetail from "../views/project-views/ProjectDetail.vue";
import ProjectAdd from "../views/project-views/ProjectAdd.vue";

//Priority
import Priority from "../views/priority-views/Priority.vue";
import PriorityDetail from "../views/priority-views/PriorityDetail.vue";
import PriorityAdd from "../views/priority-views/PriorityAdd.vue";

//Status
import Status from "../views/status-views/Status.vue";
import StatusDetail from "../views/status-views/StatusDetail.vue";
import StatusAdd from "../views/status-views/StatusAdd.vue";

//Reproducibility
import Reproducibility from "../views/reproducibility-views/Reproducibility.vue";
import ReproducibilityDetail from "../views/reproducibility-views/ReproducibilityDetail.vue";
import ReproducibilityAdd from "../views/reproducibility-views/ReproducibilityAdd.vue";
//Category
import Category from "../views/category-views/Category.vue";

//Version
import Version from "../views/version-views/Version.vue";

Vue.use(VueRouter);

const routes = [
    {
        path: "/",
        redirect: "/admin/project/1/bug"
    },
    //Bug
    {
        path: "/admin/project/:id/bug",
        component: Bug
    },
    //Account
    {
        path: "/admin/account",
        component: Account
    },
    //User
    {
        path: "/admin/user",
        component: User
    },
    {
        path: "/admin/:type/user/:id",
        component: UserDetail
    },
    {
        path: "/admin/add/user/",
        component: UserAdd
    },
    //Project
    {
        path: "/admin/project",
        component: Project
    },
    {
        path: "/admin/:type/project/:id",
        component: ProjectDetail
    },
    {
        path: "/admin/add/project/",
        component: ProjectAdd
    },
    //Priority
    {
        path: "/admin/priority",
        component: Priority
    },
    {
        path: "/admin/:type/priority/:id",
        component: PriorityDetail
    },
    {
        path: "/admin/add/priority",
        component: PriorityAdd
    },
    //Status
    {
        path: "/admin/status",
        component: Status
    },
    {
        path: "/admin/:type/status/:id",
        component: StatusDetail
    },
    {
        path: "/admin/add/status",
        component: StatusAdd
    },
    //Reproducibility
    {
        path: "/admin/reproducibility",
        component: Reproducibility
    },
    {
        path: "/admin/:type/reproducibility/:id",
        component: ReproducibilityDetail
    },
    {
        path: "/admin/add/reproducibility",
        component: ReproducibilityAdd
    },
    //Category
    {
        path: "/admin/project/:id/category",
        component: Category
    },
    //Version
    {
        path: "/admin/project/:id/version",
        component: Version
    }
];
/* router.resolve({ name: "type", params: { type: ["edit", "detail"] } }).href; */
const router = new VueRouter({
    mode: "history",
    base: process.env.BASE_URL,
    routes
});

export default router;
