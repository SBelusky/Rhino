<template>
    <div id="form-view">
        <window-title :small-title="type == 'detail' ? '| detail statusu' : '| editácia statusu'" :big-title="data.name" />
        <div class="columns pt-1">
            <div class="column is-5 form-info">
                <div class="field">
                    <label class="label"><span class="has-text-danger" v-if="type == 'edit'">* </span>Názov:</label>
                    <div class="control has-icons-left">
                        <input
                            class="input"
                            :class="{ 'invalid-field': errors.name }"
                            type="text"
                            placeholder="Vyriešený"
                            :disabled="type == 'detail'"
                            v-model="name"
                            maxlength="50"
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
                        <textarea
                            v-model="description"
                            class="textarea"
                            :disabled="type == 'detail'"
                            placeholder="Bližšie informácie o statuse..."
                            maxlength="200"
                        ></textarea>
                    </div>
                </div>
                <div class="field">
                    <label class="label"
                        ><span class="has-text-danger" v-if="type == 'edit'">* </span>Farba:
                        <span id="additional_info">(pre odlíšenie záznamov v okne Reporty)</span></label
                    >
                    <div class="form__field input" :class="{ 'invalid-field': errors.color }">
                        <div class="form__input color-picker mr-6">
                            <v-swatches v-model="color" show-fallback popover-x="left" :disabled="type == 'detail'"> </v-swatches>
                        </div>
                        <span class="ml-6" readonly>{{ color }}</span>
                    </div>
                    <div v-if="errors.color">
                        <p class="help is-danger">{{ this.errors.color }}</p>
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
import VSwatches from "vue-swatches";

export default {
    components: {
        WindowTitle,
        VSwatches
    },
    data() {
        return {
            data: [],
            id: null,
            name: null,
            description: null,
            color: null,
            errors: {},
            type: this.$route.params.type
        };
    },
    mounted() {
        axios
            .get("http://localhost:8080/api/detail/specification/" + this.$route.params.id, {
                headers: {
                    Authorization: "Bearer " + localStorage.getItem("token")
                }
            })
            .then(response => {
                this.data = response.data;
                this.id = this.data.id;
                this.name = this.data.name;
                this.description = this.data.description;
                this.color = this.data.color;
            });
    },
    methods: {
        submitForms() {
            let editData = {
                id: this.id,
                name: this.name,
                description: this.description,
                color: this.color,
                type: "Status"
            };
            axios
                .post("http://localhost:8080/api/edit/specification/" + this.id, editData, {
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
