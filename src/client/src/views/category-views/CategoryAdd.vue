<template>
    <div id="form-view">
        <window-title small-title="| pridanie kategórie" big-title="Nový záznam" />
        <div class="columns pt-1">
            <div class="column is-5 form-info">
                <div class="field">
                    <label class="label"><span class="has-text-danger">* </span>Názov:</label>
                    <div class="control has-icons-left">
                        <input
                            class="input"
                            :class="{ 'invalid-field': errors.name }"
                            v-model="name"
                            type="text"
                            placeholder="Prezentačná stránka"
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
                            class="textarea"
                            v-model="description"
                            placeholder="Bližšie informácie o kategórii..."
                            maxlength="200"
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

export default {
    title: "Kategórie | pridanie",
    components: {
        WindowTitle
    },
    data() {
        return {
            name: null,
            description: null,
            actualProject: this.$store.state.actualProject,
            errors: {}
        };
    },
    methods: {
        submitForm() {
            let data = {
                name: this.name,
                description: this.description
            };

            axios
                .post("http://localhost:8080/api/project/" + this.actualProject + "/add/category", data, {
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
