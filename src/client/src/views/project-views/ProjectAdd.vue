<template>
    <div id="form-view">
        <window-title small-title="| pridanie projektu" big-title="Nový záznam" />
        <div class="columns pt-4">
            <div class="column is-5 form-info">
                <div class="field">
                    <label class="label"><span class="has-text-danger">* </span>Názov:</label>
                    <div class="control has-icons-left">
                        <input class="input" type="text" placeholder="Netflix" :class="{ 'invalid-field': errors.name }" v-model="name" />
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
                        <textarea class="textarea" placeholder="Bližšie informácie o projekte..." v-model="description"> </textarea>
                    </div>
                </div>
                <div>
                    <label class="typo__label label">Priradení používatelia: </label>
                    <multiselect
                        class="multi-select"
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
                    ></multiselect>
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
            name: null,
            description: null,
            user_has_projects: null,
            errors: {},
            value: null,
            options: null
        };
    },
    mounted() {
        axios
            .get("http://localhost:8080/api/user/", {
                headers: {
                    Authorization: "Bearer " + localStorage.getItem("token")
                }
            })
            .then(response => (this.allUsers = response.data));
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
        submitForm() {
            let data = {
                name: this.name,
                description: this.description,
                user_has_projects: []
            };

            if (this.user_has_projects != null) {
                for (let i = 0; i < this.user_has_projects.length; i++) {
                    data.user_has_projects.push({ user: this.user_has_projects[i] });
                }
            }

            axios
                .post("http://localhost:8080/api/add/project", data, {
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
