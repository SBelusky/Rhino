<template>
    <div id="form-view">
        <window-title small-title="| pridanie používateľa" big-title="Nový záznam" />
        <div class="columns pt-1">
            <div class="column is-5 form-info">
                <div class="field">
                    <label class="label">Meno:</label>
                    <div class="control has-icons-left">
                        <input class="input" type="text" placeholder="Jožko Mrkvička" />
                        <span class="icon is-left">
                            <i class="mdi mdi-account-circle"></i>
                        </span>
                    </div>
                </div>
                <div class="field">
                    <label class="label">E-mail:</label>
                    <div class="control has-icons-left">
                        <input class="input" type="text" placeholder="jozko.mrkvicka@gmail.com" />
                        <span class="icon is-left">
                            <i class="mdi mdi-at"></i>
                        </span>
                    </div>
                </div>
                <div class="field">
                    <label class="label">Telefónne číslo:</label>
                    <div class="control has-icons-left">
                        <input class="input" type="text" placeholder="+421 999 888 777" />
                        <span class="icon is-left">
                            <i class="mdi mdi-cellphone"></i>
                        </span>
                    </div>
                </div>
                <div class="field">
                    <label class="label">Rola:</label>
                    <multiselect
                        v-model="data.role"
                        :options="options"
                        :searchable="false"
                        :close-on-select="true"
                        :show-labels="false"
                        placeholder="Výber role"
                        class="multi-select"
                    ></multiselect>
                </div>
            </div>
        </div>
        <div class="columns pt-4">
            <div class="column is-5 form-info">
                <p class="form-section-title">Prihlasovacie údaje:</p>
                <div class="field">
                    <label class="label">Login:</label>
                    <div class="control has-icons-left">
                        <input class="input" type="text" placeholder="login" />
                        <span class="icon is-left">
                            <i class="mdi mdi-lock"></i>
                        </span>
                    </div>
                </div>
                <div class="field">
                    <label class="label">Heslo:</label>
                    <div class="control has-icons-left">
                        <input class="input" type="password" placeholder="*********" />
                        <span class="icon is-left">
                            <i class="mdi mdi-key"></i>
                        </span>
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
import Multiselect from "vue-multiselect";

export default {
    components: {
        WindowTitle,
        Multiselect
    },
    data() {
        return {
            data: [],
            options: ["Administrátor", "Programátor", "Tester"]
        };
    }
};
</script>

<style lang="scss" scoped></style>
