<template>
    <b-menu id="main-menu" class="has-background-light">
        <img src="../assets/img/rhino_logo.png" width="250" class="mb-5" />
        <b-menu-list>
            <router-link id="pmzero" :to="'/' + projectUrl + '/bug'">
                <b-menu-item
                    v-on:click="selected = 'bug'"
                    v-bind:class="{ rip: selected == 'bug' }"
                    icon="bug-outline"
                    label="Bug"
                ></b-menu-item>
            </router-link>
            <b-menu-item icon="head-cog">
                <template slot="label" slot-scope="props">
                    Programmer
                    <b-icon class="is-pulled-right" :icon="props.expanded ? 'menu-down' : 'menu-up'"></b-icon>
                </template>
                <b-menu-list label="Bug specification" />
                <b-menu-item icon="format-list-bulleted-type" label="Category"></b-menu-item>
                <b-menu-item icon="counter" label="Version"></b-menu-item>
            </b-menu-item>
            <b-menu-item icon="account-tie">
                <template slot="label" slot-scope="props">
                    Administrator
                    <b-icon class="is-pulled-right" :icon="props.expanded ? 'menu-down' : 'menu-up'"></b-icon>
                </template>
                <b-menu-item icon="monitor-dashboard" label="Project"></b-menu-item>

                <router-link id="pmzero" to="/user">
                    <b-menu-item
                        v-on:click="selected = 'user'"
                        v-bind:class="{ rip: selected == 'user' }"
                        icon="account-network-outline"
                        label="User"
                    ></b-menu-item>
                </router-link>

                <b-menu-list label="Project specification"> </b-menu-list>
                <b-menu-item icon="alert-octagon-outline" label="Priority"></b-menu-item>
                <b-menu-item icon="shape-outline" label="Type"></b-menu-item>
                <b-menu-item icon="content-duplicate" label="Reproducibility"></b-menu-item>
            </b-menu-item>
            <router-link id="pmzero" to="/account">
                <b-menu-item
                    v-on:click="selected = 'account'"
                    v-bind:class="{ rip: selected == 'account' }"
                    icon="account-circle-outline"
                    label="Account"
                ></b-menu-item>
            </router-link>
        </b-menu-list>
        <b-menu-list label="Actions">
            <b-menu-item icon="logout" label="Logout"></b-menu-item>
        </b-menu-list>
    </b-menu>
</template>

<script>
export default {
    data() {
        return {
            selected: "",
            projectUrl: ""
        };
    },
    methods: {
        classChange(param) {
            this.selected = param;
        }
    },
    mounted() {
        this.$root.$on("project-change-handler", data => ((this.projectUrl = data), (this.selected = "bug")));
    }
};
</script>

<style lang="scss">
.is-horizontal-center {
    justify-content: center;
}
#main-menu {
    max-height: 100vh;
    padding: 2em 2em !important;
    margin-bottom: 10em;
    background-color: rgba(50, 50, 50, 0.1) !important;
}
#main-menu .mdi::before,
#main-menu .mdi-set {
    font-size: 1.5em;
    margin-right: 10px;
    position: relative;
    top: 3px;
}
#main-menu .icon.is-pulled-right {
    position: relative;
    top: -5px;
}
#main-menu .is-active {
    background-color: rgba(245, 245, 245, 0) !important;
}
#main-menu .is-active.is-expanded {
    background-color: rgba(245, 245, 245, 0) !important;
}
#main-menu .menu-list a.is-active {
    color: black !important;
}
#main-menu .menu-list a {
    color: black !important;
}
#main-menu .menu-list a:hover {
    background-color: #f8ac01 !important;
}
#main-menu .rip {
    background-color: #f8ac01 !important;
}
</style>
