<template>
    <div id="form-view">
        <window-title :small-title="type == 'detail' ? '| detail verzie' : '| editácia verzie'" :big-title="data.name" />
        <div class="columns pt-1">
            <div class="column is-5 form-info">
                <div class="field">
                    <label class="label"><span class="has-text-danger" v-if="type == 'edit'">* </span>Číšlo:</label>
                    <div class="control has-icons-left">
                        <input
                            class="input"
                            :class="{ 'invalid-field': errors.name }"
                            type="text"
                            placeholder="Číslo verzie"
                            :disabled="type == 'detail'"
                            v-model="name"
                            maxlength="50"
                        />
                        <span class="icon is-left">
                            <i class="mdi mdi-numeric"></i>
                        </span>
                    </div>
                    <div v-if="errors.name">
                        <p class="help is-danger">{{ this.errors.name }}</p>
                    </div>
                </div>
                <div class="field">
                    <label class="label">Popis:</label>
                    <div class="control has-icons-left">
                        <textarea
                            v-model="description"
                            class="textarea"
                            :disabled="type == 'detail'"
                            placeholder="Bližšie informácie o verzie..."
                            maxlength="200"
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
            <button v-if="type == 'edit'" class="button is-success" v-on:click="submitForms">
                <i class="fas fa-long-arrow-alt-left icon-center"></i>Uložiť
            </button>
        </div>
    </div>
</template>

<script>
import axios from "axios";
import WindowTitle from "../../components/WindowTitle.vue";

export default {
    title: "asds",
    components: {
        WindowTitle
    },
    data() {
        return {
            data: [],
            id: null,
            name: null,
            description: null,
            actualProject: this.$store.state.actualProject,
            type: this.$route.params.type,
            errors: {}
        };
    },
    mounted() {
        axios
            .get("http://localhost:8080/api/project/" + this.$route.params.projectId + "/detail/version/" + this.$route.params.id, {
                headers: {
                    Authorization: "Bearer " + localStorage.getItem("token")
                }
            })
            .then(response => {
                this.data = response.data;
                this.id = this.data.id;
                this.name = this.data.name;
                this.description = this.data.description;
            });
    },
    methods: {
        submitForms() {
            let editData = {
                id: this.id,
                name: this.name,
                description: this.description
            };

            axios
                .post("http://localhost:8080/api/project/" + this.actualProject + "/edit/version/" + this.$route.params.id, editData, {
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
