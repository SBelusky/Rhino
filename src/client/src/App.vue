<template>
    <div id="app">
        <div class="level">
            <navbar />
        </div>
        <div class="columns">
            <div class="column is-narrow">
                <main-menu class="menu-min-height" />
            </div>
            <div class="column is-narrow view">
                <router-view />
            </div>
        </div>

        <my-footer />
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
    }
    /*     mounted() {
        this.$root.$emit("project-change-handler", "1");
    } */
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
.view {
    padding: 3em;
    width: 100%;
}
</style>
