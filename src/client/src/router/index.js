import Vue from "vue";
import store from "../store";
import VueRouter from "vue-router";

/* //Default
import MainMenu from "../components/MainMenu.vue";
import Navbar from "../components/Navbar.vue";
import MyFooter from "../components/MyFooter.vue";
 */
//Login
import Login from "../components/auth/Login.vue";

//Error messages
import Forbidden from "../components/auth/Forbidden.vue";
import NotFound from "../components/auth/NotFound.vue";

//Bug
import Bug from "../views/bug-views/Bug.vue";
import BugAdd from "../views/bug-views/BugAdd.vue";
import BugDetail from "../views/bug-views/BugDetail.vue";

//Comment
import CommentEdit from "../components/comment/CommentEdit.vue";

//Attachment
import AttachmentUpload from "../components/attachment/AttachmentUpload.vue";

//Relation
import BugRelationAdd from "../components/bug-relation/BugRelationAdd.vue";

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
        redirect: "/login"
    },
    {
        path: "/admin/404",
        component: NotFound,
        meta: {
            requiresAuth: true
        }
    },
    {
        path: "/admin/403",
        component: Forbidden,
        meta: {
            requiresAuth: true
        }
    },
    //Login
    {
        path: "/login",
        component: Login
    },
    //Bug
    {
        path: "/admin/project/:id/bug",
        component: Bug,
        meta: {
            requiresAuth: true
        }
    },
    {
        path: "/admin/project/:id/add/bug",
        component: BugAdd,
        meta: {
            requiresAuth: true
        }
    },
    {
        path: "/admin/project/:projectId/:type(detail|edit)/bug/:id",
        component: BugDetail,
        meta: {
            requiresAuth: true
        }
    },
    //Comment
    {
        path: "/admin/project/:projectId/bug/:bugId/edit/comment/:commentId",
        component: CommentEdit,
        meta: {
            requiresAuth: true
        }
    },
    //Attachment
    {
        path: "/admin/project/:projectId/bug/:bugId/upload/attachment",
        component: AttachmentUpload,
        meta: {
            requiresAuth: true
        }
    },
    //Relation
    {
        path: "/admin/project/:projectId/bug/:bugId/add/relation",
        component: BugRelationAdd,
        meta: {
            requiresAuth: true
        }
    },
    //Account
    {
        path: "/admin/account",
        component: Account,
        meta: {
            requiresAuth: true
        }
    },
    {
        path: "/admin/detail/account",
        alias: "/admin/change-password/account/",
        component: AccoutDetail,
        meta: {
            requiresAuth: true
        }
    },
    //User
    {
        path: "/admin/user",
        component: User,
        meta: {
            requiresAuth: true
        }
    },
    {
        path: "/admin/:type(detail|edit)/user/:id",
        component: UserDetail,
        meta: {
            requiresAuth: true
        }
    },
    {
        path: "/admin/add/user/",
        component: UserAdd,
        meta: {
            requiresAuth: true
        }
    },
    //Project
    {
        path: "/admin/project",
        component: Project,
        meta: {
            requiresAuth: true
        }
    },
    {
        path: "/admin/:type(detail|edit)/project/:id",
        component: ProjectDetail,
        meta: {
            requiresAuth: true
        }
    },
    {
        path: "/admin/add/project/",
        component: ProjectAdd,
        meta: {
            requiresAuth: true
        }
    },
    //Priority
    {
        path: "/admin/priority",
        component: Priority,
        meta: {
            requiresAuth: true
        }
    },
    {
        path: "/admin/:type(detail|edit)/priority/:id",
        component: PriorityDetail,
        meta: {
            requiresAuth: true
        }
    },
    {
        path: "/admin/add/priority",
        component: PriorityAdd,
        meta: {
            requiresAuth: true
        }
    },
    //Status
    {
        path: "/admin/status",
        component: Status,
        meta: {
            requiresAuth: true
        }
    },
    {
        path: "/admin/:type(detail|edit)/status/:id",
        component: StatusDetail,
        meta: {
            requiresAuth: true
        }
    },
    {
        path: "/admin/add/status",
        component: StatusAdd,
        meta: {
            requiresAuth: true
        }
    },
    //Reproducibility
    {
        path: "/admin/reproducibility",
        component: Reproducibility,
        meta: {
            requiresAuth: true
        }
    },
    {
        path: "/admin/:type(detail|edit)/reproducibility/:id",
        component: ReproducibilityDetail,
        meta: {
            requiresAuth: true
        }
    },
    {
        path: "/admin/add/reproducibility",
        component: ReproducibilityAdd,
        meta: {
            requiresAuth: true
        }
    },
    //Category
    {
        path: "/admin/project/:projectId/category",
        component: Category,
        meta: {
            requiresAuth: true
        }
    },
    {
        path: "/admin/project/:projectId/:type(detail|edit)/category/:id",
        component: CategoryDetail,
        meta: {
            requiresAuth: true
        }
    },
    {
        path: "/admin/project/:projectId/add/category",
        component: CategoryAdd,
        meta: {
            requiresAuth: true
        }
    },
    //Version
    {
        path: "/admin/project/:id/version",
        component: Version,
        meta: {
            requiresAuth: true
        }
    },
    {
        path: "/admin/project/:projectId/:type(detail|edit)/version/:id",
        component: VersionDetail,
        meta: {
            requiresAuth: true
        }
    },
    {
        path: "/admin/project/:projectId/add/version",
        component: VersionAdd,
        meta: {
            requiresAuth: true
        }
    },
    {
        path: "*",
        redirect: "/admin/404"
    }
];
/* router.resolve({ name: "type", params: { type: ["edit", "detail"] } }).href; */
const router = new VueRouter({
    mode: "history",
    base: process.env.BASE_URL,
    routes
});

router.beforeEach((to, from, next) => {
    if (to.matched.some(record => record.meta.requiresAuth)) {
        // this route requires auth, check if logged in
        // if not, redirect to login page.
        if (store.getters.getLoggedUser == null) {
            next("/login");
        } else {
            next(); // go to wherever I'm going
        }
    } else {
        next(); // does not require auth, make sure to always call next()!
    }
});

export default router;
