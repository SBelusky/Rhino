<template>
    <div id="form-view">
        <window-title small-title="| detail" :big-title="data.name" />
        <div class="columns pt-4">
            <div class="column is-5 form-info">
                <div class="field">
                    <label class="label">Názov:</label>
                    <div class="control has-icons-left">
                        <input
                            class="input"
                            :class="{ 'invalid-field': errors.name }"
                            type="text"
                            placeholder="Netflix"
                            :disabled="type == 'detail'"
                            v-model="name"
                        />
                        <span class="icon is-left">
                            <i class="mdi mdi-tag"></i>
                        </span>
                    </div>
                    <div v-if="errors.name">
                        <p class="help is-danger">{{ this.errors.name }}</p>
                    </div>
                </div>
                <div class="field">
                    <label class="label">Popis:</label>
                    <div class="control has-icons-left">
                        <input
                            class="input"
                            type="text"
                            placeholder="Bližšie informácie o projekte..."
                            :disabled="type == 'detail'"
                            v-model="description"
                        />
                        <span class="icon is-left">
                            <i class="mdi mdi-lead-pencil"></i>
                        </span>
                    </div>
                </div>
                <div class="skuska">
                    <label class="typo__label label">Priradení používatelia: </label>
                    <multiselect
                        :class="type == 'detail' ? 'multi-select-detail' : 'multi-select'"
                        :searchable="false"
                        :close-on-select="false"
                        label="name"
                        track-by="id"
                        :options="allUsers"
                        v-model="user_has_projects"
                        placeholder="Výber používateľov"
                        select-label="Pridať"
                        selectedLabel="Pridaný"
                        deselectLabel="Odobrať"
                        :multiple="true"
                        :taggable="true"
                        @tag="addTag"
                        :disabled="type == 'detail'"
                    ></multiselect>
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
            <button v-if="type == 'edit'" class="button is-success" v-on:click="submitForms">
                <i class="fas fa-long-arrow-alt-left icon-center"></i>Uložiť
            </button>
        </div>
    </div>
</template>

<script>
import axios from "axios";
import Multiselect from "vue-multiselect";
import WindowTitle from "../../components/WindowTitle.vue";

export default {
    components: {
        WindowTitle,
        Multiselect
    },
    data() {
        return {
            data: [],
            allUsers: [],
            id: null,
            name: null,
            description: null,
            user_has_projects: [],
            value: null,
            options: null,
            errors: {},
            type: this.$route.params.type
        };
    },
    mounted() {
        axios.get("http://localhost:8080/api/detail/project/" + this.$route.params.id).then(response => {
            this.data = response.data;
            this.id = this.data.id;
            this.name = this.data.name;
            this.description = this.data.description;
            this.pickSelectedUsers();
        });
        axios.get("http://localhost:8080/api/user/").then(response => (this.allUsers = response.data));
    },
    methods: {
        addTag(newTag) {
            const tag = {
                name: newTag,
                code: newTag.substring(0, 2) + Math.floor(Math.random() * 10000000)
            };
            this.options.push(tag);
            this.value.push(tag);
        },
        pickSelectedUsers() {
            if (this.data.user_has_projects != null) {
                for (let i = 0; i < this.data.user_has_projects.length; i++) {
                    this.user_has_projects.push(this.data.user_has_projects[i].user);
                }
            }
        },
        submitForms() {
            let editData = {
                id: this.id,
                name: this.name,
                description: this.description,
                user_has_projects: []
            };
            if (this.user_has_projects != null) {
                for (let i = 0; i < this.user_has_projects.length; i++) {
                    editData.user_has_projects.push({ user: this.user_has_projects[i] });
                }
            }

            axios
                .post("http://localhost:8080/api/edit/project/" + this.id, editData)
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
