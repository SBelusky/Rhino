<template>
    <div id="app">
        <div class="columns is-8 content-div">
            <div class="column is-2 menu-div">
                <main-menu class="menu-min-height" />
            </div>
            <div class="column is-10">
                <navbar class="pt-3 pr-3" />
                <router-view class="testa" />
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
    },
    mounted() {
        this.$root.$emit("project-change-handler", "All");
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
/* #app .menu-min-height {
    min-height: 80vh !important;
} */
#app .columns {
    margin-bottom: 0 !important;
}
.content-div {
    padding-bottom: 5em;
}
</style>
