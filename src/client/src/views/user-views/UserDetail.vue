<template>
    <div id="user-view">
        <window-title small-title="detail" :big-title="data.name" />
        <div class="columns pt-4">
            <div class="column is-5 user-info">
                <div class="field">
                    <label class="label">Meno:</label>
                    <div class="control has-icons-left">
                        <input class="input" type="text" placeholder="Extra small" disabled :value="data.name" />
                        <span class="icon is-left">
                            <i class="mdi mdi-account-circle"></i>
                        </span>
                    </div>
                </div>
                <div class="field">
                    <label class="label">E-mail:</label>
                    <div class="control has-icons-left">
                        <input class="input" type="text" placeholder="Extra small" disabled :value="data.email" />
                        <span class="icon is-left">
                            <i class="mdi mdi-at"></i>
                        </span>
                    </div>
                </div>
                <div class="field">
                    <label class="label">Telefónne číslo:</label>
                    <div class="control has-icons-left">
                        <input class="input" type="text" placeholder="Extra small" disabled :value="data.telephone_number" />
                        <span class="icon is-left">
                            <i class="mdi mdi-cellphone"></i>
                        </span>
                    </div>
                </div>
                <div class="field">
                    <label class="label">Rola:</label>
                    <div class="control has-icons-left">
                        <input class="input" type="text" placeholder="Extra small" disabled :value="data.role" />
                        <span class="icon is-left">
                            <i class="mdi mdi-account-star-outline"></i>
                        </span>
                    </div>
                </div>
            </div>
        </div>
        <div class="columns pt-4">
            <div class="column is-5 user-info">
                <p class="form-section-title">Prihlasovacie údaje:</p>
                <div class="field">
                    <label class="label">Login:</label>
                    <div class="control has-icons-left">
                        <input class="input" type="text" placeholder="Extra small" disabled :value="data.login_name" />
                        <span class="icon is-left">
                            <i class="mdi mdi-lock"></i>
                        </span>
                    </div>
                </div>
                <div class="field">
                    <label class="label">Heslo:</label>
                    <div class="control has-icons-left">
                        <input class="input" type="text" placeholder="Extra small" disabled :value="data.login_password" />
                        <span class="icon is-left">
                            <i class="mdi mdi-key"></i>
                        </span>
                    </div>
                </div>
            </div>
        </div>
        <div class="user-view-button pb-2">
            <button class="button" v-on:click="$router.back()"><i class="fas fa-long-arrow-alt-left icon-center"></i>Späť</button>
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
            data: []
        };
    },
    mounted() {
        axios.get("http://localhost:8080/api/user/" + this.$route.params.id).then(response => (this.data = response.data));
    }
};
</script>

<style lang="scss" scoped>
#user-view .user-info {
    box-shadow: 0px 0px 20px 0px rgba(0, 0, 0, 0.1);
    padding: 2em;
    background-color: #fff;
    display: block !important;
}
#user-view .user-view-button button {
    color: black;
    background-color: #5199e4;
    border: none;
}
#user-view .user-view-button button:hover {
    background-color: hsl(211, 73%, 51%);
}
#user-view .user-view-button button:focus {
    box-shadow: none;
    border: none;
}
#user-view .control.has-icons-left .icon,
#user-view .control.has-icons-right .icon {
    color: #7a7a7a;
}
#user-view .columns {
    margin-left: 0;
}
#user-view .input {
    border: 1px solid #b8b8b8b3;
}
</style>
