<template>
    <div id="form-view">
        <window-title small-title="| pridanie priority" big-title="Nový záznam" />
        <div class="columns pt-1">
            <div class="column is-5 form-info">
                <div class="field">
                    <label class="label"><span class="has-text-danger">* </span>Názov:</label>
                    <div class="control has-icons-left">
                        <input
                            class="input"
                            :class="{ 'invalid-field': errors.name }"
                            type="text"
                            placeholder="Veľmi vysoká"
                            maxlength="50"
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
                        <textarea
                            class="textarea"
                            v-model="description"
                            placeholder="Bližšie informácie o priorite..."
                            maxlength="200"
                        ></textarea>
                    </div>
                </div>
            </div>
        </div>
        <div class="form-view-button pb-2">
            <button class="button mr-3" v-on:click="$router.back()"><i class="fas fa-ban icon-center"></i>Zrušiť</button>
            <button class="button is-success" v-on:click="submitForm">
                <i class="fas fa-long-arrow-alt-left icon-center"></i>Uložiť
            </button>
        </div>
    </div>
</template>

<script>
import axios from "axios";
import WindowTitle from "../../components/WindowTitle.vue";

export default {
    title: "Priority | pridanie",
    components: {
        WindowTitle
    },
    data() {
        return {
            name: null,
            description: null,
            errors: {}
        };
    },
    methods: {
        submitForm() {
            let data = {
                name: this.name,
                description: this.description,
                type: "Priority"
            };
            axios
                .post("http://localhost:8080/api/add/specification", data)
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
