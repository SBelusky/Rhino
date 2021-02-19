<template>
    <div id="form-view">
        <window-title small-title="| pridanie používateľa" big-title="Nový záznam" />
        <div class="columns pt-1">
            <div class="column is-5 form-info">
                <div class="field">
                    <label class="label"><span class="has-text-danger">* </span>Meno:</label>
                    <div class="control has-icons-left">
                        <input
                            class="input"
                            :class="{ 'invalid-field': errors.name }"
                            type="text"
                            placeholder="Jožko Mrkvička"
                            v-model="name"
                            maxlength="50"
                        />
                        <span class="icon is-left">
                            <i class="mdi mdi-account-circle"></i>
                        </span>
                    </div>
                    <div v-if="errors.name">
                        <p class="help is-danger">{{ this.errors.name }}</p>
                    </div>
                </div>
                <div class="field">
                    <label class="label"><span class="has-text-danger">* </span>E-mail:</label>
                    <div class="control has-icons-left">
                        <input
                            class="input"
                            :class="{ 'invalid-field': errors.email }"
                            type="text"
                            placeholder="jozko.mrkvicka@gmail.com"
                            v-model="email"
                            maxlength="50"
                        />
                        <span class="icon is-left">
                            <i class="mdi mdi-at"></i>
                        </span>
                    </div>
                    <div v-if="errors.email">
                        <p class="help is-danger">{{ this.errors.email }}</p>
                    </div>
                </div>
                <div class="field">
                    <label class="label">Telefónne číslo:</label>
                    <div class="control has-icons-left">
                        <input
                            class="input"
                            :class="{ 'invalid-field': errors.telephone_number }"
                            type="text"
                            placeholder="+421 999 888 777"
                            v-model="telephone_number"
                            maxlength="50"
                        />
                        <span class="icon is-left">
                            <i class="mdi mdi-cellphone"></i>
                        </span>
                    </div>
                    <div v-if="errors.telephone_number">
                        <p class="help is-danger">{{ this.errors.telephone_number }}</p>
                    </div>
                </div>
                <div class="field">
                    <label class="label"><span class="has-text-danger">* </span>Rola:</label>
                    <multiselect
                        v-model="role"
                        :options="options"
                        :searchable="false"
                        :close-on-select="true"
                        :show-labels="false"
                        placeholder="Výber role"
                        class="multi-select"
                        :class="{ 'invalid-field': errors.role }"
                    ></multiselect>
                    <div v-if="errors.role">
                        <p class="help is-danger">{{ this.errors.role }}</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="columns pt-4">
            <div class="column is-5 form-info">
                <p class="form-section-title">Prihlasovacie údaje:</p>
                <div class="field">
                    <label class="label"><span class="has-text-danger">* </span>Login:</label>
                    <div class="control has-icons-left">
                        <input
                            class="input"
                            :class="{ 'invalid-field': errors.username }"
                            type="text"
                            placeholder="login"
                            v-model="username"
                            maxlength="50"
                        />
                        <span class="icon is-left">
                            <i class="mdi mdi-lock"></i>
                        </span>
                    </div>
                    <div v-if="errors.username">
                        <p class="help is-danger">{{ this.errors.username }}</p>
                    </div>
                </div>
                <div class="field">
                    <label class="label"><span class="has-text-danger">* </span>Heslo:</label>
                    <div class="control has-icons-left">
                        <input
                            class="input"
                            :class="{ 'invalid-field': errors.password }"
                            type="password"
                            placeholder="*********"
                            v-model="password"
                            maxlength="50"
                        />
                        <span class="icon is-left">
                            <i class="mdi mdi-key"></i>
                        </span>
                    </div>
                    <div v-if="errors.password">
                        <p class="help is-danger">{{ this.errors.password }}</p>
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
import Multiselect from "vue-multiselect";

export default {
    title: "Používateľia | pridanie",
    components: {
        WindowTitle,
        Multiselect
    },
    data() {
        return {
            email: null,
            name: null,
            telephone_number: null,
            role: null,
            username: null,
            password: null,
            errors: {},
            options: ["Administrátor", "Programátor", "Tester"]
        };
    },
    methods: {
        submitForm() {
            let data = {
                email: this.email,
                name: this.name,
                telephone_number: this.telephone_number,
                role: this.role,
                username: this.username,
                password: this.password
            };

            axios
                .post("http://localhost:8080/api/add/user", data, {
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
