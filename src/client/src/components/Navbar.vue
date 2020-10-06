<template>
    <div id="nav-bar">
        <nav class="level">
            <div class="level-left" />
            <div class="level-right">
                <p class="level-item"><a class="button is-success">Add bug</a></p>
                <div class="level-item">
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

<style lang="scss" scoped></style>
