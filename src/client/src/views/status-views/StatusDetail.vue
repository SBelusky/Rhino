<template>
    <div id="form-view">
        <window-title :small-title="type == 'detail' ? '| detail statusu' : '| editácia statusu'" :big-title="data.name" />
        <div class="columns pt-1">
            <div class="column is-5 form-info">
                <div class="field">
                    <label class="label">Názov:</label>
                    <div class="control has-icons-left">
                        <input
                            class="input"
                            type="text"
                            placeholder="Vyriešený"
                            :disabled="type == 'detail'"
                            :value="data.name"
                        />
                        <span class="icon is-left">
                            <i class="mdi mdi-tag"></i>
                        </span>
                    </div>
                </div>
                <div class="field">
                    <label class="label">Popis:</label>
                    <div class="control has-icons-left">
                        <textarea
                            :value="data.description"
                            class="textarea"
                            :disabled="type == 'detail'"
                            placeholder="Bližšie informácie o statuse..."
                        ></textarea>
                    </div>
                </div>
                <div class="field">
                    <label class="label">Farba: <span id="additional_info">(pre odlíšenie záznamov v okne Reporty)</span></label>
                    <div class="form__field input">
                        <div class="form__input color-picker mr-6">
                            <v-swatches v-model="data.color" show-fallback popover-x="left" :disabled="type == 'detail'">
                            </v-swatches>
                        </div>
                        <span class="ml-6" readonly>{{ data.color }}</span>
                    </div>
                </div>
            </div>
        </div>
        <div class="form-view-button pb-2">
            <button class="button mr-3" v-on:click="$router.back()">
                <i class="fas fa-long-arrow-alt-left icon-center"></i>Späť
            </button>
            <button v-if="type == 'edit'" class="button is-success" v-on:click="$router.back()">
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
            type: this.$route.params.type
        };
    },
    mounted() {
        axios
            .get("http://localhost:8080/api/detail/specification/" + this.$route.params.id)
            .then(response => (this.data = response.data));
    }
};
</script>

<style lang="scss" scoped></style>
