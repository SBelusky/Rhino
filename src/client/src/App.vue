<template>
    <div id="app">
        <div class="columns">
            <main-menu class="column is-2" />
            <div class="column is-10 px-6">
                <navbar class="mt-3" />
                <router-view class="mt-5" />
            </div>
        </div>
        <my-footer />
    </div>

    <!--div id="app" class="columns">
        <main-menu class="column is-2" />
        <div class="column is-10 px-6">
            <navbar class="mt-3" />
            <router-view class="mt-5" />
        </div>
        <my-footer />
    </div-->
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

<style>
#app .columns {
    min-height: 100vh !important;
}

.columns {
    margin-bottom: 0 !important;
}
</style>
