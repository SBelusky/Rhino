<template>
    <div id="account-form" class="columns">
        <div class="column is-5">
            <h1 class="title is-1 mb-6"><span id="small-title">account | </span>Samuel Beluský</h1>
            <div class="field">
                <label class="label is-medium">E-mail:</label>
                <div class="control has-icons-left">
                    <input
                        class="input is-medium"
                        type="email"
                        placeholder="Extra small"
                        disabled
                        value="samuel.belusky@gmail.com"
                    />
                    <span class="icon is-left">
                        <i class="mdi mdi-at"></i>
                    </span>
                </div>
            </div>
            <div class="field">
                <label class="label is-medium">Phone number:</label>
                <div class="control has-icons-left">
                    <input class="input is-medium" type="email" placeholder="Extra small" disabled value="+421 948 775 092" />
                    <span class="icon is-left">
                        <i class="mdi mdi-cellphone"></i>
                    </span>
                </div>
            </div>
            <div class="field">
                <label class="label is-medium">Role:</label>
                <div class="control has-icons-left">
                    <input class="input is-medium" type="email" placeholder="Extra small" disabled value="Administrator" />
                    <span class="icon is-left">
                        <i class="mdi mdi-account-star-outline  "></i>
                    </span>
                </div>
            </div>
            <div class="buttons">
                <button class="button is-info is-active">
                    <i class="mdi mdi-grease-pencil mr-2"></i>
                    Edit
                </button>
                <button class="button is-danger is-active">
                    <i class="mdi mdi-delete mr-2"></i>
                    Delete
                </button>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {};
    }
};
</script>

<style lang="scss" scoped>
#account-form {
    margin-top: 1em !important;
}
#account-form .control.has-icons-left .icon,
#account-form .control.has-icons-right .icon {
    color: #7a7a7a;
}
</style>
