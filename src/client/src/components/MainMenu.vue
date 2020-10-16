<template>
    <b-menu id="main-menu" class="has-background-light">
        <img src="../assets/img/rhino_logo.png" width="250" class="mb-5" />
        <b-menu-list>
            <router-link id="pm-zero" :to="'/' + projectUrl + '/bug'">
                <b-menu-item
                    v-on:click="selected = 'bug'"
                    v-bind:class="{ rip: selected == 'bug' }"
                    icon="bug"
                    icon-pack="fas"
                    label="Bug"
                ></b-menu-item>
            </router-link>
            <b-menu-item icon="user-cog" icon-pack="fas">
                <template slot="label" slot-scope="props">
                    Programmer
                    <i class="is-pulled-right" id="expand-icon" :class="props.expanded ? 'fas fa-minus' : 'fas fa-plus'"></i>
                </template>
                <b-menu-list label="Bug specification" />
                <b-menu-item icon="list-ul" label="Category" icon-pack="fas"></b-menu-item>
                <b-menu-item icon="sort-numeric-up-alt" label="Version" icon-pack="fas"></b-menu-item>
            </b-menu-item>
            <b-menu-item icon="user-tie" icon-pack="fas">
                <template slot="label" slot-scope="props">
                    Administrator
                    <i class="is-pulled-right" id="expand-icon" :class="props.expanded ? 'fas fa-minus' : 'fas fa-plus'"></i>
                </template>
                <b-menu-item icon="monitor-dashboard" label="Project" icon-pack="fas"></b-menu-item>

                <router-link id="pm-zero" to="/user">
                    <b-menu-item
                        v-on:click="selected = 'user'"
                        v-bind:class="{ rip: selected == 'user' }"
                        icon="account-network-outline"
                        icon-pack="fas"
                        label="User"
                    ></b-menu-item>
                </router-link>

                <b-menu-list label="Project specification"> </b-menu-list>
                <b-menu-item icon="alert-octagon-outline" label="Priority" icon-pack="fas"></b-menu-item>
                <b-menu-item icon="shape-outline" label="Type" icon-pack="fas"></b-menu-item>
                <b-menu-item icon="content-duplicate" label="Reproducibility" icon-pack="fas"></b-menu-item>
            </b-menu-item>
            <router-link id="pm-zero" to="/account">
                <b-menu-item
                    v-on:click="selected = 'account'"
                    v-bind:class="{ rip: selected == 'account' }"
                    icon="user-circle"
                    icon-pack="far"
                    label="Account"
                ></b-menu-item>
            </router-link>
        </b-menu-list>
        <b-menu-list label="Actions">
            <b-menu-item icon="sign-out-alt" icon-pack="fas" label="Logout"></b-menu-item>
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
    min-height: 80vh;
    max-width: 350px;
    padding: 2em 0.7em !important;
    padding-bottom: 15em !important;
    margin-bottom: 10em;
    background-color: #343a40 !important;
    border-bottom-right-radius: 15px;
}
.fa,
.fas,
.far {
    font-size: 1.1em;
    margin-right: 10px;
    position: relative;
    top: 0px;
}
#main-menu #expand-icon {
    position: relative;
    top: 4px;
    font-size: 0.6em;
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
    color: #c2c7d0 !important;
}
#main-menu .menu-list a {
    color: #c2c7d0 !important;
}
#main-menu .menu-list a:hover {
    background-color: #494e53 !important;
}
#main-menu .menu-label {
    color: #c2c7d0;
}
#main-menu .rip {
    //background-color: #f8ac01 !important;
    background-color: #494e53 !important;
}
</style>
