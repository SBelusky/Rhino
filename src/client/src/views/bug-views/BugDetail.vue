<template>
    <div id="form-view">
        <window-title
            :small-title="type == 'detail' ? '| detail reportu' : '| editácia reportu'"
            :big-title="'Report: ' + this.formatBugId(this.$route.params.id) + ' '"
        />
        <div class="columns pt-4 pb-4">
            <div class="column is-3 form-info">
                <p class="form-section-title">Špecidifikácia:</p>
                <div class="field medium-width-cmb pr-3">
                    <label class="label"><span class="has-text-danger">* </span>Kategória:</label>
                    <multiselect
                        :class="{
                            'multi-select-detail': type == 'detail',
                            'multi-select': type == 'edit',
                            'invalid-field': errors.category
                        }"
                        :disabled="type == 'detail'"
                        v-model="bug.category"
                        label="name"
                        track-by="id"
                        :options="allCategories"
                        :searchable="false"
                        :close-on-select="true"
                        :show-labels="false"
                        placeholder="Výber kategórie"
                    ></multiselect>
                    <div v-if="errors.category">
                        <p class="help is-danger">{{ this.errors.category }}</p>
                    </div>
                </div>
                <div class="field medium-width-cmb pr-3">
                    <label class="label"><span class="has-text-danger">* </span>Priorita:</label>
                    <multiselect
                        :class="{
                            'multi-select-detail': type == 'detail',
                            'multi-select': type == 'edit',
                            'invalid-field': errors.priority
                        }"
                        v-model="bug.priority"
                        label="name"
                        track-by="id"
                        :disabled="type == 'detail'"
                        :options="allPriorities"
                        :searchable="false"
                        :close-on-select="true"
                        :show-labels="false"
                        placeholder="Výber priority"
                    ></multiselect>
                    <div v-if="errors.priority">
                        <p class="help is-danger">{{ this.errors.priority }}</p>
                    </div>
                </div>
                <div class="field medium-width-cmb pr-3">
                    <label class="label"><span class="has-text-danger">* </span>Status:</label>
                    <multiselect
                        :class="{
                            'multi-select-detail': type == 'detail',
                            'multi-select': type == 'edit',
                            'invalid-field': errors.status
                        }"
                        v-model="bug.status"
                        label="name"
                        track-by="id"
                        :disabled="type == 'detail'"
                        :options="allStatuses"
                        :searchable="false"
                        :close-on-select="true"
                        :show-labels="false"
                        placeholder="Výber statusu"
                    ></multiselect>
                    <div v-if="errors.status">
                        <p class="help is-danger">{{ this.errors.status }}</p>
                    </div>
                </div>
                <div class="field medium-width-cmb pr-3">
                    <label class="label"><span class="has-text-danger">* </span>Reprodukovateľnosť:</label>
                    <multiselect
                        :class="{
                            'multi-select-detail': type == 'detail',
                            'multi-select': type == 'edit',
                            'invalid-field': errors.reproducibility
                        }"
                        v-model="bug.reproducibility"
                        label="name"
                        track-by="id"
                        :disabled="type == 'detail'"
                        :options="allReproducibility"
                        :searchable="false"
                        :close-on-select="true"
                        :show-labels="false"
                        placeholder="Výber reprod."
                    ></multiselect>
                    <div v-if="errors.reproducibility">
                        <p class="help is-danger">{{ this.errors.reproducibility }}</p>
                    </div>
                </div>
            </div>
            <div class="column is-3 form-info">
                <p class="form-section-title">Bližšie informácie:</p>
                <div class="field medium-width-cmb pr-3">
                    <label class="label"><span class="has-text-danger">* </span>Verzia nájdenia:</label>
                    <multiselect
                        :class="{
                            'multi-select-detail': type == 'detail',
                            'multi-select': type == 'edit',
                            'invalid-field': errors.foundInVersion
                        }"
                        v-model="bug.foundInVersion"
                        label="name"
                        track-by="id"
                        :disabled="type == 'detail'"
                        :options="allVersions"
                        :searchable="false"
                        :close-on-select="true"
                        :show-labels="false"
                        placeholder="Výber verzie nájdenia"
                    ></multiselect>
                    <div v-if="errors.foundInVersion">
                        <p class="help is-danger">{{ this.errors.foundInVersion }}</p>
                    </div>
                </div>
                <div class="field medium-width-cmb pr-3">
                    <label class="label">Verzia vyriešenia:</label>
                    <multiselect
                        :class="{
                            'multi-select-detail': type == 'detail',
                            'multi-select': type == 'edit'
                        }"
                        v-model="bug.repairedInVersion"
                        label="name"
                        track-by="id"
                        :disabled="type == 'detail'"
                        :options="allVersions"
                        :searchable="false"
                        :close-on-select="true"
                        :show-labels="false"
                        placeholder="Výber verzie vyriešenia"
                    ></multiselect>
                </div>
                <div class="field medium-width-cmb pr-3">
                    <label class="label"><span class="has-text-danger">* </span>Priradený používateľ:</label>
                    <multiselect
                        :class="{
                            'multi-select-detail': type == 'detail',
                            'multi-select': type == 'edit',
                            'invalid-field': errors.associatedUser
                        }"
                        v-model="bug.associatedUser"
                        label="name"
                        track-by="id"
                        :disabled="type == 'detail'"
                        :options="allUsers"
                        :searchable="false"
                        :close-on-select="true"
                        :show-labels="false"
                        placeholder="Výber pužívateľa"
                    ></multiselect>
                    <div v-if="errors.associatedUser">
                        <p class="help is-danger">{{ this.errors.associatedUser }}</p>
                    </div>
                </div>
                <div class="field medium-width-cmb pr-3">
                    <label class="label">Doba hľadania [min]:</label>
                    <div class="control has-icons-left">
                        <input
                            class="input"
                            :class="{ 'invalid-field': errors.seekTime }"
                            type="text"
                            placeholder="65 min"
                            v-model="bug.seekTime"
                            :disabled="type == 'detail'"
                            maxlength="50"
                            oninput="this.value = this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');"
                        />
                        <span class="icon is-left">
                            <i class="mdi mdi-timer"></i>
                        </span>
                    </div>
                </div>
            </div>
        </div>
        <div class="columns pt-1">
            <div class="column is-6 is-6-plus-gap-size form-info">
                <p class="form-section-title">Opis reportu:</p>
                <div class="field">
                    <label class="label"><span class="has-text-danger">* </span>Sumarizácia:</label>
                    <div class="control has-icons-left">
                        <input
                            class="input"
                            :class="{ 'invalid-field': errors.summarize }"
                            v-model="bug.summarize"
                            :disabled="type == 'detail'"
                            type="text"
                            placeholder="Krátky opis reportu"
                            maxlength="100"
                        />
                        <span class="icon is-left">
                            <i class="mdi mdi-tag"></i>
                        </span>
                    </div>
                    <div v-if="errors.summarize">
                        <p class="help is-danger">{{ this.errors.summarize }}</p>
                    </div>
                </div>
                <div class="field">
                    <label class="label"><span class="has-text-danger">* </span>Popis:</label>
                    <div class="control has-icons-left">
                        <textarea
                            class="textarea"
                            :class="{ 'invalid-field': errors.description }"
                            v-model="bug.description"
                            :disabled="type == 'detail'"
                            placeholder="Popis reportu, postup nasimulovania chyby..."
                            rows="8"
                            maxlength="1000"
                        ></textarea>
                    </div>
                    <div v-if="errors.description">
                        <p class="help is-danger">{{ this.errors.description }}</p>
                    </div>
                </div>
                <div class="field">
                    <label class="label">Dodatočné informácie:</label>
                    <div class="control has-icons-left">
                        <textarea
                            class="textarea"
                            v-model="bug.additional_info"
                            :disabled="type == 'detail'"
                            placeholder="Opis vzťahov medzi reportami..."
                            maxlength="1000"
                        ></textarea>
                    </div>
                </div>
            </div>
        </div>
        <div class="form-view-button pb-2">
            <button class="button mr-3" v-on:click="$router.back()">
                <i v-if="type == 'detail'" class="fas fa-long-arrow-alt-left icon-center"></i>
                <span v-if="type == 'detail'"> Späť </span>
                <i v-if="type == 'edit'" class="fas fa-ban icon-center"></i>
                <span v-if="type == 'edit'"> Zrušiť </span>
            </button>
            <button v-if="type == 'edit'" class="button is-success" v-on:click="submitForm">
                <i class="fas fa-long-arrow-alt-left icon-center"></i>Uložiť
            </button>
        </div>
        <bug-relation-table v-if="type == 'detail'" />
        <attachment-table v-if="type == 'detail'" />
        <comment-table v-if="type == 'detail'" />
        <log v-if="type == 'detail'" />
    </div>
</template>

<script>
import axios from "axios";
import WindowTitle from "../../components/WindowTitle.vue";
import Multiselect from "vue-multiselect";
import CommentTable from "@/components/comment/CommentTable.vue";
import AttachmentTable from "@/components/attachment/AttachmentTable.vue";
import BugRelationTable from "@/components/bug-relation/BugRelationTable.vue";
import Log from "@/components/Log.vue";

export default {
    title: "Reporty | pridanie",
    components: {
        WindowTitle,
        Multiselect,
        CommentTable,
        AttachmentTable,
        BugRelationTable,
        BugRelationTable,
        Log
    },
    data() {
        return {
            data: [],
            type: this.$route.params.type,
            actualProject: this.$store.state.actualProject,
            bug: {
                id: null,
                category: null,
                priority: null,
                status: null,
                reproducibility: null,
                associatedUser: null,
                foundInVersion: null,
                repairedInVersion: null,
                seekTime: null,
                summarize: null,
                description: null,
                additional_info: null,
                bugRelations: null
            },
            allCategories: [],
            allPriorities: [],
            allStatuses: [],
            allReproducibility: [],
            allUsers: [],
            allVersions: [],
            errors: {}
        };
    },
    mounted() {
        window.scrollTo(0, 0);
        axios
            .get("http://localhost:8080/api/project/" + this.actualProject + "/bug-category", {
                headers: {
                    Authorization: "Bearer " + localStorage.getItem("token")
                }
            })
            .then(response => (this.allCategories = response.data));

        axios
            .get("http://localhost:8080/api/bug-specification/?type=Priority", {
                headers: {
                    Authorization: "Bearer " + localStorage.getItem("token")
                }
            })
            .then(response => (this.allPriorities = response.data));

        axios
            .get("http://localhost:8080/api/bug-specification/?type=Status", {
                headers: {
                    Authorization: "Bearer " + localStorage.getItem("token")
                }
            })
            .then(response => (this.allStatuses = response.data));

        axios
            .get("http://localhost:8080/api/bug-specification/?type=Reproducibility", {
                headers: {
                    Authorization: "Bearer " + localStorage.getItem("token")
                }
            })
            .then(response => (this.allReproducibility = response.data));

        axios
            .get("http://localhost:8080/api/bug-user/", {
                headers: {
                    Authorization: "Bearer " + localStorage.getItem("token")
                }
            })
            .then(response => (this.allUsers = response.data));

        axios
            .get("http://localhost:8080/api/project/" + this.actualProject + "/bug-version", {
                headers: {
                    Authorization: "Bearer " + localStorage.getItem("token")
                }
            })
            .then(response => (this.allVersions = response.data));

        axios
            .get("http://localhost:8080/api/project/" + this.actualProject + "/detail/bug/" + this.$route.params.id, {
                headers: {
                    Authorization: "Bearer " + localStorage.getItem("token")
                }
            })
            .then(response => {
                this.data = response.data;
                this.bug.id = this.data.id;
                this.bug.category = this.data.category;
                this.bug.seekTime = this.data.seek_time;
                this.bug.summarize = this.data.summarize;
                this.bug.description = this.data.description;
                this.bug.additional_info = this.data.additional_info;
                this.bug.bugRelations = this.data.bugHasBugsContains;

                for (let i = 0; i < this.data.bugHasSpecifications.length; i++) {
                    if (this.data.bugHasSpecifications[i].specification.type == "Status") {
                        this.bug.status = this.data.bugHasSpecifications[i].specification;
                    } else if (this.data.bugHasSpecifications[i].specification.type == "Priority") {
                        this.bug.priority = this.data.bugHasSpecifications[i].specification;
                    } else if (this.data.bugHasSpecifications[i].specification.type == "Reproducibility") {
                        this.bug.reproducibility = this.data.bugHasSpecifications[i].specification;
                    }
                }

                for (let i = 0; i < this.data.bugHasUsers.length; i++) {
                    if (this.data.bugHasUsers[i].id.type == "Associated user") {
                        this.bug.associatedUser = this.data.bugHasUsers[i].user;
                    }
                }

                for (let i = 0; i < this.data.bugHasVersions.length; i++) {
                    if (this.data.bugHasVersions[i].id.type == "Found in version") {
                        this.bug.foundInVersion = this.data.bugHasVersions[i].version;
                    } else if (this.data.bugHasVersions[i].id.type == "Repaired in version") {
                        this.bug.repairedInVersion = this.data.bugHasVersions[i].version;
                    }
                }
            });
    },
    methods: {
        submitForm() {
            let editData = {
                id: this.bug.id,
                category: this.bug.category,
                seek_time: this.bug.seekTime,
                summarize: this.bug.summarize,
                description: this.bug.description,
                additional_info: this.bug.additional_info,
                bugHasVersions: [],
                bugHasSpecifications: [],
                bugHasUsers: [],
                bugHasBugsContains: []
            };

            editData.bugHasVersions.push({ version: this.bug.foundInVersion, type: "Found in version" });

            if (this.bug.repairedInVersion != null) {
                editData.bugHasVersions.push({ version: this.bug.repairedInVersion, type: "Repaired in version" });
            }

            editData.bugHasSpecifications.push({ specification: this.bug.status, type: "Status" });
            editData.bugHasSpecifications.push({ specification: this.bug.priority, type: "Priority" });
            editData.bugHasSpecifications.push({ specification: this.bug.reproducibility, type: "Reproducibility" });

            editData.bugHasUsers.push({ user: { id: 1 }, type: "Author" });
            editData.bugHasUsers.push({ user: this.bug.associatedUser, type: "Associated user" });

            for (let i = 0; i < this.bug.bugRelations.length; i++) {
                editData.bugHasBugsContains.push({
                    included: { id: this.bug.bugRelations[i].id.included },
                    status: this.bug.bugRelations[i].status
                });
            }

            axios
                .post("http://localhost:8080/api/project/" + this.actualProject + "/edit/bug/" + this.bug.id, editData, {
                    headers: {
                        Authorization: "Bearer " + localStorage.getItem("token")
                    }
                })
                .then(response => {
                    if (response.status == 200) {
                        this.$router.back();
                    }
                })
                .catch(errors => {
                    this.errors = null;
                    this.errors = errors.response.data;
                });
        }
    }
};
</script>

<style lang="scss" scoped></style>
