<template>
    <div id="account-form">
        <window-title small-title="| detail účtu" :big-title="loggedUser.name" />
        <router-link id="pm-zero" :to="`/admin/detail/account`">
            <b-button
                class="data-table-button mr-5"
                icon-left="mdi mdi-account-edit-outline icon-center"
                v-on:click="accountButtonTypeSet('basicInfo')"
            >
                Editovať účet
            </b-button>
        </router-link>
        <router-link id="pm-zero" :to="`/admin/change-password/account/`">
            <b-button
                class="data-table-button change-password-button"
                icon-left="mdi mdi-key icon-center"
                v-on:click="accountButtonTypeSet('password')"
            >
                Zmeniť prihlasovacie údaje
            </b-button>
        </router-link>
        <div class="columns pt-4">
            <div class="column is-5 account-info">
                <div class="field">
                    <label class="label">E-mail:</label>
                    <div class="control has-icons-left">
                        <input class="input" type="email" placeholder="Extra small" disabled :value="loggedUser.email" />
                        <span class="icon is-left">
                            <i class="mdi mdi-at"></i>
                        </span>
                    </div>
                </div>
                <div class="field">
                    <label class="label">Telefónne číslo:</label>
                    <div class="control has-icons-left">
                        <input class="input" type="text" placeholder="Extra small" disabled :value="loggedUser.email" />
                        <span class="icon is-left">
                            <i class="mdi mdi-cellphone"></i>
                        </span>
                    </div>
                </div>
                <div class="field">
                    <label class="label">Rola:</label>
                    <div class="control has-icons-left">
                        <input class="input" type="text" placeholder="Extra small" disabled :value="formatUserRole(loggedUser.role)" />
                        <span class="icon is-left">
                            <i class="mdi mdi-account-star-outline"></i>
                        </span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import WindowTitle from "../../components/WindowTitle.vue";

export default {
    title: "Môj účet | prehľad",
    data() {
        return {
            loggedUser: this.$store.getters.getLoggedUser
        };
    },
    components: {
        WindowTitle
    },
    methods: {
        accountButtonTypeSet(type) {
            this.$store.commit("ACCOUNT_BUTTON_TYPE", type);
        }
    }
};
</script>

<style lang="scss" scoped>
#account-form .control.has-icons-left .icon,
#account-form .control.has-icons-right .icon {
    color: #7a7a7a;
}
#account-form .columns {
    margin-left: 0;
}
#account-form .input {
    border: 1px solid #b8b8b8b3;
}
#account-form .account-info {
    box-shadow: 0px 0px 20px 0px rgba(0, 0, 0, 0.1);
    padding: 2em;
    background-color: #fff;
}
#account-form .account-button button {
    color: black;
}
#account-form .account-button button:hover {
    background-color: hsl(48, 100%, 57%);
}
</style>
