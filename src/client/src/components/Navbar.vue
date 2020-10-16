<template>
    <div id="nav-bar">
        <nav class="level">
            <div class="level-left" />
            <div class="level-right">
                <div class="level-item">
                    <span id="change-project-text">Change project:</span>
                    <div class="field">
                        <div class="control" @change="projectChangeHandler()">
                            <div class="select">
                                <select v-model="selected">
                                    <option v-for="project in projects" :key="project">
                                        {{ project }}
                                    </option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="vl"></div>
                <div class="level-item">
                    <b-button class="is-info" icon-left="mdi mdi-sticker-plus-outline">
                        Add bug
                    </b-button>
                </div>
            </div>
        </nav>
    </div>
</template>

<script>
export default {
    data() {
        return {
            selected: "",
            isPublic: true,
            projects: ["All", "Project"]
        };
    },
    methods: {
        projectChangeHandler() {
            this.$root.$emit("project-change-handler", event.target.value);
            this.$router.push("/" + event.target.value + "/bug");
        }
    },
    mounted() {
        this.$root.$on("project-change-handler", data => (this.selected = data));
    }
};
</script>

<style lang="scss" scoped>
#nav-bar {
    background-color: #343a40;
    padding: 2em 4em !important;
    margin-bottom: 2em;
    border-bottom-left-radius: 15px;
}
#nav-bar .columns {
    min-height: 0px !important;
}
.select select {
    width: 150px;
}
#nav-bar #change-project-text {
    color: #c2c7d0;
    font-size: 1.1em;
    margin-right: 0.8em;
    padding: 0.2em;
    border-bottom: 2px dotted #c2c7d0;
}
#nav-bar .select:not(.is-multiple):not(.is-loading)::after {
    border-color: #494e53;
}
</style>
