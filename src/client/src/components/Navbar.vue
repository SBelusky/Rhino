<template>
    <div id="nav-bar">
        <nav class="level">
            <div class="level-left">
                <div class="main-logo">
                    <img src="../assets/img/rhino_logo.png" />
                </div>
            </div>
            <div class="level-right">
                <div class="level-item">
                    <span class="change-project-text">Aktuálny projekt:</span>
                    <div class="field">
                        <div class="control" @change="projectChangeHandler()">
                            <div class="select">
                                <select v-model="selected">
                                    <option v-for="project in projects" :key="project.id" v-bind:value="project.id">
                                        {{ project.name }}
                                    </option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="vertical-line"></div>
                <div class="level-item">
                    <b-button class="is-success" icon-left="mdi mdi-sticker-plus-outline icon-center">
                        Pridať bug
                    </b-button>
                </div>
            </div>
        </nav>
    </div>
</template>

<script>
import axios from "axios";

export default {
    data() {
        return {
            selected: this.$store.state.actualProject,
            isPublic: true,
            loggedUser: this.$store.getters.getLoggedUser,
            projects: []
        };
    },
    methods: {
        projectChangeHandler() {
            console.log(this.selected);
            this.$store.commit("CHANGE_PROJECT", this.selected);
            this.$store.commit("CHANGE_MAIN_MENU_ITEM", "bug");
            this.$router.push("/admin/project/" + this.selected + "/bug");
            this.$router.go();
        }
    },
    mounted() {
        axios
            .post("http://localhost:8080/api/assign-project", this.loggedUser, {
                headers: {
                    Authorization: "Bearer " + localStorage.getItem("token")
                }
            })
            .then(response => (this.projects = response.data));
        this.selected = this.$store.state.actualProject;
    },
    updated() {
        this.selected = this.$store.state.actualProject;
    }
};
</script>

<style lang="scss" scoped>
#nav-bar {
    background-color: #41586e;
    padding: 0.5em 2em !important;
    width: 100%;
}
#nav-bar .columns {
    min-height: 0px !important;
}
.main-logo {
    width: 10em;
}
#nav-bar .control:active {
    border: 0 !important;
}
.select select {
    width: 150px;
}
.select select:focus {
    border: none;
    box-shadow: none;
}
#nav-bar .change-project-text {
    color: white;
    font-size: 1em;
    margin-right: 0.8em;
    padding: 0.2em;
    border-bottom: 2px dotted white;
}
#nav-bar .select:not(.is-multiple):not(.is-loading)::after {
    border-color: #494e53;
}
#nav-bar .level-item .button {
    color: #000;
}
#nav-bar .level-item .button:hover {
    background-color: hsl(141, 53%, 43%);
}
.vertical-line {
    border-left: 1px solid #c2c7d0;
    margin-right: 0.8em;
    height: 3em;
}
</style>
