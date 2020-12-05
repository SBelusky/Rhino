<template>
    <div id="form-view">
        <window-title small-title="| pridanie statusu" big-title="Nový záznam" />
        <div class="columns pt-1">
            <div class="column is-5 form-info">
                <div class="field">
                    <label class="label">Názov:</label>
                    <div class="control has-icons-left">
                        <input class="input" type="text" placeholder="Vyriešený" />
                        <span class="icon is-left">
                            <i class="mdi mdi-tag"></i>
                        </span>
                    </div>
                </div>
                <div class="field">
                    <label class="label">Popis:</label>
                    <div class="control has-icons-left">
                        <textarea class="textarea" placeholder="Bližšie informácie o statuse..."></textarea>
                    </div>
                </div>
                <div class="field">
                    <label class="label">Farba: <span id="additional_info">(pre odlíšenie záznamov v okne Reporty)</span></label>
                    <div class="form__field input">
                        <div class="form__input color-picker mr-6">
                            <v-swatches v-model="color" show-fallback popover-x="left"></v-swatches>
                        </div>
                        <span class="ml-6" readonly>{{ color }}</span>
                    </div>
                </div>
            </div>
        </div>
        <div class="form-view-button pb-2">
            <button class="button mr-3" v-on:click="$router.back()">
                <i class="fas fa-long-arrow-alt-left icon-center"></i>Späť
            </button>
            <button class="button is-success" v-on:click="$router.back()">
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
            color: "#3398DB"
        };
    }
};
</script>

<style lang="scss" scoped></style>
