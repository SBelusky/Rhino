<template>
    <div id="login">
        <section class="hero is-fullheight">
            <div class="hero-body">
                <div class="container">
                    <div class="columns is-centered">
                        <div class="column is-5-tablet is-4-desktop is-3-widescreen">
                            <div class="login-form">
                                <p class="login-logo">
                                    <img src="@/assets/img/rhino_logo.png" />
                                </p>
                                <h1 class="login-title">Prihlásenie</h1>
                                <form action="" class="box" @submit.prevent="handleSubmit()">
                                    <div class="errorMessageBox" v-if="showErrorLoginMessage">
                                        <p>Nesprávne meno alebo heslo</p>
                                    </div>
                                    <div class="field">
                                        <label for="" class="label">Meno:</label>
                                        <div class="control has-icons-left">
                                            <input
                                                type="username"
                                                placeholder="Prihlasovacie meno"
                                                class="input"
                                                v-model="username"
                                                required
                                            />
                                            <span class="icon is-small is-left">
                                                <i class="fas fa-user ml-1"></i>
                                            </span>
                                        </div>
                                    </div>
                                    <div class="field">
                                        <label for="" class="label">Heslo:</label>
                                        <div class="control has-icons-left">
                                            <input type="password" placeholder="*******" class="input" v-model="password" required />
                                            <span class="icon is-small is-left">
                                                <i class="fa fa-lock ml-1"></i>
                                            </span>
                                        </div>
                                    </div>
                                    <div class="field">
                                        <label for="" class="checkbox">
                                            <input type="checkbox" />
                                            Zapamätať si ma
                                        </label>
                                    </div>
                                    <div class="field">
                                        <button class="button is-success">
                                            Prihlásiť
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
</template>

<script>
import axios from "axios";

export default {
    data() {
        return {
            data: null,
            username: "",
            password: "",
            loggedUser: {},
            admin: {
                id: 0,
                name: "Samuel Beluský",
                email: "samuel.belusky@rhino.sk",
                telephone_number: "202-555-0147",
                username: "admin",
                password: "admin",
                role: "manager",
                created_at: "2001-01-20 00:00:00",
                edited_at: "2021-02-19 20:39:42"
            },
            showErrorLoginMessage: false
        };
    },
    methods: {
        async handleSubmit() {
            let formData = {
                username: this.username,
                password: this.password
            };

            axios
                .post("http://localhost:8080/login", formData)
                .then(response => {
                    if (response.status == 200) {
                        localStorage.setItem("token", response.data.token);
                        this.showErrorLoginMessage = false;
                        this.loadLoggedUserData();
                    }
                })
                .catch(error => {
                    console.log(error);
                    this.showErrorLoginMessage = true;
                });
        },
        loadLoggedUserData() {
            let self = this;
            let user = {
                username: this.username
            };
            this.$store.commit("SET_LOGGED_USER", this.admin);

            axios
                .post("http://localhost:8080/api/logged-user", user, {
                    headers: {
                        Authorization: "Bearer " + localStorage.getItem("token")
                    }
                })
                .then(response => {
                    self.loggedUser = response.data;
                    this.$store.commit("SET_LOGGED_USER", response.data);
                    this.$store.commit("CHANGE_MAIN_MENU_ITEM", "account");
                })
                .catch(error => {
                    console.log(error);
                });
            location.replace("/admin/account");
        }
    }
};
</script>
<style scoped>
#login .button.is-success {
    color: black;
    margin-top: 1em;
    width: 100%;
}
#login .input {
    border: 1px solid #b8b8b8b3 !important;
    color: black;
}
#login .input:focus {
    box-shadow: none !important;
    border: 1px solid black !important;
}
#login i {
    color: #7a7a7a;
}
#login .login-title {
    font-size: 3em;
    margin: 0.5em 0;
    text-align: center;
    border-bottom: 1px solid #000;
}
#login .login-form {
    position: relative;
    top: -100px !important;
}
#login .errorMessageBox {
    padding: 0.5em 0.2em;
    background-color: rgba(228, 78, 88, 0.75);
    text-align: center;
    margin-bottom: 0.6em;
    color: #000;
    border-radius: 0.5em;
}
</style>
