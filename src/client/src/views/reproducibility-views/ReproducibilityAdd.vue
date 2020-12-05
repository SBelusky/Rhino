<template>
    <div id="form-view">
        <window-title small-title="| pridanie reprodukovateľnosti" big-title="Nový záznam" />
        <div class="columns pt-1">
            <div class="column is-5 form-info">
                <div class="field">
                    <label class="label">Názov:</label>
                    <div class="control has-icons-left">
                        <input class="input" type="text" placeholder="Veľmi často" />
                        <span class="icon is-left">
                            <i class="mdi mdi-tag"></i>
                        </span>
                    </div>
                </div>
                <div class="field">
                    <label class="label">Popis:</label>
                    <div class="control has-icons-left">
                        <textarea class="textarea" placeholder="Bližšie informácie o reprodukovateľnosti..."></textarea>
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

export default {
    components: {
        WindowTitle
    },
    data() {
        return {};
    }
};
</script>

<style lang="scss" scoped></style>
