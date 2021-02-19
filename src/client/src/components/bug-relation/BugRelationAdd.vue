<template>
    <div id="add-bug-relation">
        <window-title small-title="| pridanie súvislosti" :big-title="'Report: ' + this.$route.params.bugId + ' '" />
        <div class="columns">
            <div class="column is-2 ">
                <div class="field">
                    <label class="label"><span class="has-text-danger">* </span>ID reportu:</label>
                    <div class="control has-icons-left">
                        <input
                            class="input"
                            :class="{ 'invalid-field': errors.relationBugId }"
                            type="number"
                            placeholder="000"
                            v-model="relationBug.contains.id"
                        />
                        <span class="icon is-left">
                            <i class="mdi mdi-numeric"></i>
                        </span>
                    </div>
                    <div v-if="errors.relationBugId">
                        <p class="help is-danger">{{ this.errors.relationBugId }}</p>
                    </div>
                </div>
                <div class="field">
                    <label class="label"><span class="has-text-danger">* </span>Vzťah:</label>
                    <div class="control has-icons-left">
                        <input
                            class="input"
                            :class="{ 'invalid-field': errors.status }"
                            type="text"
                            placeholder="Blokujúci"
                            maxlength="50"
                            v-model="relationBug.status"
                        />
                        <span class="icon is-left">
                            <i class="mdi mdi-tag"></i>
                        </span>
                    </div>
                    <div v-if="errors.status">
                        <p class="help is-danger">{{ this.errors.status }}</p>
                    </div>
                </div>
            </div>
        </div>
        <button class="button back-button-color" v-on:click="$router.back()">
            <i class="fas fa-ban icon-center"></i>
            Zrušiť
        </button>
        <button class="button is-success" v-on:click="validateAndSaveRelation()">
            <i class="fas fa-long-arrow-alt-left icon-center"></i>Uložiť
        </button>
    </div>
</template>

<script>
import axios from "axios";
import WindowTitle from "../../components/WindowTitle.vue";

export default {
    components: {
        WindowTitle
    },
    props: [""],
    data() {
        return {
            defaultBug: {},
            relationBug: {
                contains: {
                    id: null
                },
                status: null
            },
            errors: {}
        };
    },
    methods: {
        validateAndSaveRelation() {
            let editData = {
                contains: { id: this.relationBug.contains.id },
                status: this.relationBug.status
            };
            axios
                .post(
                    "http://localhost:8080/api/project/" +
                        this.$route.params.projectId +
                        "/bug/" +
                        this.$route.params.bugId +
                        "/validate-relation",
                    editData,
                    {
                        headers: {
                            Authorization: "Bearer " + localStorage.getItem("token")
                        }
                    }
                )
                .then(response => {
                    if (response.status == 200) {
                        this.saveRelation();
                    }
                })
                .catch(errors => {
                    this.errors = {};
                    this.errors = errors.response.data;
                });
        },
        saveRelation() {
            let newRelation = {
                included: { id: this.relationBug.contains.id },
                status: this.relationBug.status
            };

            let bugWithNewRelation = {
                id: this.defaultBug.id,
                category: this.defaultBug.category,
                seek_time: this.defaultBug.seekTime,
                summarize: this.defaultBug.summarize,
                description: this.defaultBug.description,
                additional_info: this.defaultBug.additional_info,
                bugHasVersions: this.defaultBug.bugHasVersions,
                bugHasSpecifications: this.defaultBug.bugHasSpecifications,
                bugHasUsers: this.defaultBug.bugHasUsers,
                bugHasBugsContains: []
            };

            for (let i = 0; i < bugWithNewRelation.bugHasVersions.length; i++) {
                bugWithNewRelation.bugHasVersions[i].type = bugWithNewRelation.bugHasVersions[i].id.type;
            }

            for (let i = 0; i < bugWithNewRelation.bugHasSpecifications.length; i++) {
                bugWithNewRelation.bugHasSpecifications[i].type = bugWithNewRelation.bugHasSpecifications[i].specification.type;
            }

            for (let i = 0; i < bugWithNewRelation.bugHasUsers.length; i++) {
                bugWithNewRelation.bugHasUsers[i].type = bugWithNewRelation.bugHasUsers[i].id.type;
            }

            for (let i = 0; i < this.defaultBug.bugHasBugsContains.length; i++) {
                bugWithNewRelation.bugHasBugsContains.push({
                    included: { id: this.defaultBug.bugHasBugsContains[i].id.included },
                    status: this.defaultBug.bugHasBugsContains[i].status
                });
            }

            bugWithNewRelation.bugHasBugsContains.push(newRelation);

            axios
                .post(
                    "http://localhost:8080/api/project/" + this.$route.params.projectId + "/edit/bug/" + this.$route.params.bugId,
                    bugWithNewRelation,
                    {
                        headers: {
                            Authorization: "Bearer " + localStorage.getItem("token")
                        }
                    }
                )
                .then(response => {
                    if (response.status == 200) {
                        this.$router.back();
                    }
                    return 0;
                });
        }
    },
    mounted() {
        axios
            .get("http://localhost:8080/api/project/" + this.$route.params.projectId + "/detail/bug/" + this.$route.params.bugId, {
                headers: {
                    Authorization: "Bearer " + localStorage.getItem("token")
                }
            })
            .then(response => {
                this.defaultBug = response.data;
            });
        window.scrollTo(0, 0);
    }
};
</script>

<style lang="scss" scoped>
#add-bug-relation .invalid-field {
    border-color: red !important;
}
#add-bug-relation input {
    box-shadow: none;
    border: 1px solid #b8b8b8b3;
    color: black;
}
#add-bug-relation .icon i {
    color: #000;
}
#add-bug-relation .button {
    color: #000;
    border: 0;
    box-shadow: none;
}
#add-bug-relation .button:nth-child(even) {
    margin-left: 1em !important;
}
</style>
