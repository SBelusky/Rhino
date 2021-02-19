<template>
    <div id="form-view">
        <window-title small-title="| pridanie reportu" big-title="Nový záznam" />
        <div class="columns pt-4 pb-4">
            <div class="column is-3 form-info">
                <p class="form-section-title">Špecidifikácia:</p>
                <div class="field medium-width-cmb pr-3">
                    <label class="label"><span class="has-text-danger">* </span>Kategória:</label>
                    <multiselect
                        class="multi-select"
                        :class="{ 'invalid-field': errors.category }"
                        v-model="category"
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
                        v-model="priority"
                        label="name"
                        track-by="id"
                        :options="allPriorities"
                        :searchable="false"
                        :close-on-select="true"
                        :show-labels="false"
                        placeholder="Výber priority"
                        class="multi-select"
                        :class="{ 'invalid-field': errors.priority }"
                    ></multiselect>
                    <div v-if="errors.priority">
                        <p class="help is-danger">{{ this.errors.priority }}</p>
                    </div>
                </div>
                <div class="field medium-width-cmb pr-3">
                    <label class="label"><span class="has-text-danger">* </span>Status:</label>
                    <multiselect
                        v-model="status"
                        label="name"
                        track-by="id"
                        :options="allStatuses"
                        :searchable="false"
                        :close-on-select="true"
                        :show-labels="false"
                        placeholder="Výber statusu"
                        class="multi-select"
                        :class="{ 'invalid-field': errors.status }"
                    ></multiselect>
                    <div v-if="errors.status">
                        <p class="help is-danger">{{ this.errors.status }}</p>
                    </div>
                </div>
                <div class="field medium-width-cmb pr-3">
                    <label class="label"><span class="has-text-danger">* </span>Reprodukovateľnosť:</label>
                    <multiselect
                        v-model="reproducibility"
                        label="name"
                        track-by="id"
                        :options="allReproducibility"
                        :searchable="false"
                        :close-on-select="true"
                        :show-labels="false"
                        placeholder="Výber reprod."
                        class="multi-select"
                        :class="{ 'invalid-field': errors.reproducibility }"
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
                        v-model="foundInVersion"
                        label="name"
                        track-by="id"
                        :options="allVersions"
                        :searchable="false"
                        :close-on-select="true"
                        :show-labels="false"
                        placeholder="Výber verzie nájdenia"
                        class="multi-select"
                        :class="{ 'invalid-field': errors.foundInVersion }"
                    ></multiselect>
                    <div v-if="errors.foundInVersion">
                        <p class="help is-danger">{{ this.errors.foundInVersion }}</p>
                    </div>
                </div>
                <div class="field medium-width-cmb pr-3">
                    <label class="label">Verzia vyriešenia:</label>
                    <multiselect
                        v-model="repairedInVersion"
                        label="name"
                        track-by="id"
                        :options="allVersions"
                        :searchable="false"
                        :close-on-select="true"
                        :show-labels="false"
                        placeholder="Výber verzie vyriešenia"
                        class="multi-select"
                        :class="{ 'invalid-field': errors.repairedInVersion }"
                    ></multiselect>
                </div>
                <div class="field medium-width-cmb pr-3">
                    <label class="label"><span class="has-text-danger">* </span>Priradený používateľ:</label>
                    <multiselect
                        v-model="associatedUser"
                        label="name"
                        track-by="id"
                        :options="allUsers"
                        :searchable="false"
                        :close-on-select="true"
                        :show-labels="false"
                        placeholder="Výber pužívateľa"
                        class="multi-select"
                        :class="{ 'invalid-field': errors.associatedUser }"
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
                            v-model="seekTime"
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
                            v-model="summarize"
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
                            v-model="description"
                            placeholder="Popis reportu, postup nasimulovania chyby..."
                            maxlength="1000"
                            rows="8"
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
                            v-model="additional_info"
                            placeholder="Opis vzťahov medzi reportami..."
                            maxlength="1000"
                        ></textarea>
                    </div>
                </div>
            </div>
        </div>
        <div class="form-view-button pb-2">
            <button class="button mr-3" v-on:click="$router.back()"><i class="fas fa-ban icon-center"></i>Zrušiť</button>
            <button class="button is-success" v-on:click="submitForm"><i class="fas fa-long-arrow-alt-left icon-center"></i>Uložiť</button>
        </div>
    </div>
</template>

<script>
import axios from "axios";
import WindowTitle from "../../components/WindowTitle.vue";
import Multiselect from "vue-multiselect";

export default {
    title: "Reporty | pridanie",
    components: {
        WindowTitle,
        Multiselect
    },
    data() {
        return {
            allCategories: [],
            category: null,
            priority: null,
            allPriorities: [],
            status: null,
            allStatuses: [],
            reproducibility: null,
            allReproducibility: [],
            associatedUser: null,
            allUsers: [],
            foundInVersion: null,
            allVersions: [],
            repairedInVersion: null,
            seekTime: null,
            summarize: null,
            description: null,
            additional_info: null,
            actualProject: this.$store.state.actualProject,
            loggedUser: this.$store.getters.getLoggedUser,
            errors: {}
        };
    },
    mounted() {
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
            .get("http://localhost:8080/api/bug-user", {
                headers: {
                    Authorization: "Bearer " + localStorage.getItem("token")
                }
            })
            .then(response => (this.allUsers = response.data));

        axios
            .get("http://localhost:8080/api/bug-project" + this.actualProject + "/version", {
                headers: {
                    Authorization: "Bearer " + localStorage.getItem("token")
                }
            })
            .then(response => (this.allVersions = response.data));
    },
    methods: {
        submitForm() {
            let data = {
                category: this.category,
                seek_time: this.seekTime,
                summarize: this.summarize,
                description: this.description,
                additional_info: this.additional_info,
                bugHasVersions: [],
                bugHasSpecifications: [],
                bugHasUsers: [],
                bugHasBugsContains: []
            };

            data.bugHasVersions.push({ version: this.foundInVersion, type: "Found in version" });

            if (this.repairedInVersion != null) {
                data.bugHasVersions.push({ version: this.repairedInVersion, type: "Repaired in version" });
            }

            data.bugHasSpecifications.push({ specification: this.status, type: "Status" });
            data.bugHasSpecifications.push({ specification: this.priority, type: "Priority" });
            data.bugHasSpecifications.push({ specification: this.reproducibility, type: "Reproducibility" });

            data.bugHasUsers.push({ user: { id: this.loggedUser.id }, type: "Author" });
            data.bugHasUsers.push({ user: this.associatedUser, type: "Associated user" });

            axios
                .post("http://localhost:8080/api/project/" + this.actualProject + "/add/bug", data, {
                    headers: {
                        Authorization: "Bearer " + localStorage.getItem("token")
                    }
                })
                .then(response => {
                    if (response.status == 201) {
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
