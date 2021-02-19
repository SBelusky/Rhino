<template>
    <transition name="fade">
        <div class="notification is-success" v-show="show">
            <button class="delete" @click="hideNoti()"></button>
            {{ text }}
        </div>
    </transition>
</template>

<script>
export default {
    data() {
        return {
            show: false,
            text: null
        };
    },
    created() {
        this.$root.$on("flash", message => {
            this.showNoti();
            this.text = message;
        });
        /*         if (this.text != null) {
            this.showNoti();
        } */
    },
    methods: {
        showNoti() {
            this.show = true;
            setTimeout(() => {
                this.hideNoti();
            }, 2000);
        },
        hideNoti() {
            this.show = false;
        }
    }
};
</script>

<style lang="scss" scoped>
.notification {
    position: fixed;
    right: 2em;
    top: 5em;
    color: #000 !important;
}
.fade-enter-active,
.fade-leave-active {
    transition: opacity 0.35s;
}
.fade-enter,
.fade-leave-to {
    opacity: 0;
}
</style>
