<template>
    <div id="form-view">
        <window-title :small-title="type == 'detail' ? '| detail verzie' : '| editácia verzie'" :big-title="data.name" />
        <div class="columns pt-1">
            <div class="column is-5 form-info">
                <div class="field">
                    <label class="label">Číšlo:</label>
                    <div class="control has-icons-left">
                        <input
                            class="input"
                            type="text"
                            placeholder="Číslo verzie"
                            :disabled="type == 'detail'"
                            :value="data.name"
                        />
                        <span class="icon is-left">
                            <i class="mdi mdi-numeric"></i>
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
                            placeholder="Bližšie informácie o verzie..."
                        ></textarea>
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

export default {
    components: {
        WindowTitle
    },
    data() {
        return {
            data: [],
            type: this.$route.params.type
        };
    },
    mounted() {
        axios
            .get("http://localhost:8080/api/project/" + this.$route.params.projectId + "/detail/version/" + this.$route.params.id)
            .then(response => (this.data = response.data));
    }
};
</script>

<style lang="scss" scoped></style>
