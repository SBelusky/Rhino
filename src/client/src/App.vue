<template>
    <div id="app">
        <div class="level">
            <navbar v-if="this.$route.path !== '/login'" />
        </div>
        <div class="columns">
            <div class="column is-narrow">
                <main-menu class="menu-min-height" v-if="this.$route.path !== '/login'" />
            </div>
            <div class="column is-narrow" :class="classObject">
                <router-view />
            </div>
        </div>

        <my-footer v-if="this.$route.path !== '/login'" />
    </div>
</template>

<script>
import MainMenu from "./components/MainMenu.vue";
import Navbar from "./components/Navbar.vue";
import MyFooter from "./components/MyFooter.vue";

export default {
    components: {
        MainMenu,
        Navbar,
        MyFooter
    },
    computed: {
        classObject() {
            return {
                "view-admin": this.$route.path !== "/login",
                "view-login": this.$route.path === "/login"
            };
        }
    }
};
</script>

<style scoped>
body {
    margin: 0;
    padding: 0;
}
#app {
    background-color: #f8f8f8;
}
#app .level {
    margin: 0;
}
#app .columns {
    margin-bottom: 0 !important;
}
.content-div {
    padding-bottom: 5em;
}
main-menu {
    width: 500px !important;
}
.view-admin {
    padding: 3em;
    width: 100%;
}
.view-login {
    padding: 0em;
    margin: 0;
    width: 100%;
}
</style>
