import Vue from "vue";
import VueRouter from "vue-router";

//Bug
import Bug from "../views/bug-views/Bug.vue";
import BugAdd from "../views/bug-views/BugAdd.vue";

//Account
import Account from "../views/account-views/Account.vue";
import AccoutDetail from "../views/account-views/AccoutDetail.vue";

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
import CategoryDetail from "../views/category-views/CategoryDetail.vue";
import CategoryAdd from "../views/category-views/CategoryAdd.vue";

//Version
import Version from "../views/version-views/Version.vue";
import VersionDetail from "../views/version-views/VersionDetail.vue";
import VersionAdd from "../views/version-views/VersionAdd.vue";

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
    {
        path: "/admin/project/:id/add/bug",
        component: BugAdd
    },
    //Account
    {
        path: "/admin/account",
        component: Account
    },
    {
        path: "/admin/detail/account",
        alias: "/admin/change-password/account/",
        component: AccoutDetail
    },
    //User
    {
        path: "/admin/user",
        component: User
    },
    {
        path: "/admin/:type(detail|edit)/user/:id",
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
        path: "/admin/:type(detail|edit)/project/:id",
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
        path: "/admin/:type(detail|edit)/priority/:id",
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
        path: "/admin/:type(detail|edit)/status/:id",
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
        path: "/admin/:type(detail|edit)/reproducibility/:id",
        component: ReproducibilityDetail
    },
    {
        path: "/admin/add/reproducibility",
        component: ReproducibilityAdd
    },
    //Category
    {
        path: "/admin/project/:projectId/category",
        component: Category
    },
    {
        path: "/admin/project/:projectId/:type(detail|edit)/category/:id",
        component: CategoryDetail
    },
    {
        path: "/admin/project/:projectId/add/category",
        component: CategoryAdd
    },
    //Version
    {
        path: "/admin/project/:id/version",
        component: Version
    },
    {
        path: "/admin/project/:projectId/:type(detail|edit)/version/:id",
        component: VersionDetail
    },
    {
        path: "/admin/project/:projectId/add/version",
        component: VersionAdd
    }
];
/* router.resolve({ name: "type", params: { type: ["edit", "detail"] } }).href; */
const router = new VueRouter({
    mode: "history",
    base: process.env.BASE_URL,
    routes
});

export default router;
