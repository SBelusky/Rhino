<template>
    <div id="form-view">
        <window-title small-title="| pridanie statusu" big-title="Nový záznam" />
        <div class="columns pt-1">
            <div class="column is-5 form-info">
                <div class="field">
                    <label class="label"><span class="has-text-danger">* </span>Názov:</label>
                    <div class="control has-icons-left">
                        <input
                            class="input"
                            :class="{ 'invalid-field': errors.name }"
                            type="text"
                            placeholder="Vyriešený"
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
                            class="textarea"
                            placeholder="Bližšie informácie o statuse..."
                            v-model="description"
                            maxlength="200"
                        ></textarea>
                    </div>
                </div>
                <div class="field">
                    <label class="label"
                        ><span class="has-text-danger">* </span>Farba:
                        <span id="additional_info">(pre odlíšenie záznamov v okne Reporty)</span></label
                    >
                    <div class="form__field input" :class="{ 'invalid-field': errors.color }">
                        <div class="form__input color-picker mr-6">
                            <v-swatches v-model="color" show-fallback popover-x="left"></v-swatches>
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
            <button class="button mr-3" v-on:click="$router.back()"><i class="fas fa-ban icon-center"></i>Zrušiť</button>
            <button class="button is-success" v-on:click="submitForm"><i class="fas fa-long-arrow-alt-left icon-center"></i>Uložiť</button>
        </div>
    </div>
</template>

<script>
import axios from "axios";
import WindowTitle from "../../components/WindowTitle.vue";
import VSwatches from "vue-swatches";

export default {
    title: "Statusy | pridanie",
    components: {
        WindowTitle,
        VSwatches
    },
    data() {
        return {
            name: null,
            description: null,
            color: "#" + Math.floor(Math.random() * 16777215).toString(16),
            errors: {}
        };
    },
    methods: {
        submitForm() {
            let data = {
                name: this.name,
                description: this.description,
                color: this.color,
                type: "Status"
            };
            axios
                .post("http://localhost:8080/api/add/specification", data, {
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
