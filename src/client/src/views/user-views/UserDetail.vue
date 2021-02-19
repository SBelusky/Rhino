<template>
    <div id="form-view">
        <window-title :small-title="type == 'detail' ? '| detail používateľa' : '| editácia používateľa'" :big-title="data.name" />
        <div class="columns pt-1">
            <div class="column is-5 form-info">
                <div class="field">
                    <label class="label"><span class="has-text-danger" v-if="type == 'edit'">* </span>Meno:</label>
                    <div class="control has-icons-left">
                        <input
                            class="input"
                            :class="{ 'invalid-field': errors.name }"
                            type="text"
                            placeholder="Jožko Mrkvička"
                            :disabled="type == 'detail'"
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
                    <label class="label"><span class="has-text-danger" v-if="type == 'edit'">* </span>E-mail:</label>
                    <div class="control has-icons-left">
                        <input
                            class="input"
                            :class="{ 'invalid-field': errors.email }"
                            type="text"
                            placeholder="jozko.mrkvicka@gmail.com"
                            :disabled="type == 'detail'"
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
                            type="text"
                            placeholder="+421 999 888 777"
                            :disabled="type == 'detail'"
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

                <label class="label"><span class="has-text-danger" v-if="type == 'edit'">* </span>Rola:</label>
                <multiselect
                    v-model="role"
                    :options="options"
                    :searchable="false"
                    :close-on-select="true"
                    :show-labels="false"
                    :disabled="type == 'detail'"
                    placeholder="Výber role"
                    :allow-empty="false"
                    :class="type == 'detail' ? 'multi-select-detail' : 'multi-select'"
                >
                </multiselect>
                <div v-if="errors.role">
                    <p class="help is-danger">{{ this.errors.role }}</p>
                </div>
            </div>
        </div>
        <div class="columns pt-4">
            <div class="column is-5 form-info">
                <p class="form-section-title">Prihlasovacie údaje:</p>
                <div class="field">
                    <label class="label"><span class="has-text-danger" v-if="type == 'edit'">* </span>Login:</label>
                    <div class="control has-icons-left">
                        <input
                            class="input"
                            :class="{ 'invalid-field': errors.username }"
                            type="text"
                            placeholder="login"
                            :disabled="type == 'detail'"
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
                    <label class="label"><span class="has-text-danger" v-if="type == 'edit'">* </span>Heslo:</label>
                    <div class="control has-icons-left">
                        <input
                            class="input"
                            :class="{ 'invalid-field': errors.password }"
                            type="password"
                            placeholder="*********"
                            :disabled="type == 'detail'"
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
import Multiselect from "vue-multiselect";

export default {
    components: {
        WindowTitle,
        Multiselect
    },
    data() {
        return {
            data: [],
            id: null,
            email: null,
            name: null,
            telephone_number: null,
            role: null,
            username: null,
            password: null,
            errors: {},
            type: this.$route.params.type,
            options: ["Administrátor", "Programátor", "Tester"]
        };
    },
    methods: {
        submitForms() {
            let editData = {
                id: this.id,
                email: this.email,
                name: this.name,
                telephone_number: this.telephone_number,
                role: this.role,
                username: this.username,
                password: this.password
            };

            axios
                .post("http://localhost:8080/api/edit/user/" + this.id, editData, {
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
            /* this.$root.$emit("flash", "Editácia bola úspešná"); */
        }
    },
    mounted() {
        axios
            .get("http://localhost:8080/api/detail/user/" + this.$route.params.id, {
                headers: {
                    Authorization: "Bearer " + localStorage.getItem("token")
                }
            })
            .then(response => {
                this.data = response.data;
                this.id = this.data.id;
                this.email = this.data.email;
                this.name = this.data.name;
                this.telephone_number = this.data.telephone_number;
                this.role = this.data.role;
                this.username = this.data.username;
                this.password = this.data.password;
            });
    }
};
</script>

<style lang="scss" scoped></style>
