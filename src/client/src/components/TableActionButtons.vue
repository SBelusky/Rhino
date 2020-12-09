<template>
    <div id="action-buttons">
        <div class="action-button-container">
            <router-link id="pm-zero" :to="`/admin/${project}detail/${resource}/${id}`">
                <i class="far fa-eye has-text-dark icon-center"></i>
            </router-link>
        </div>
        <div class="action-button-container">
            <router-link id="pm-zero" :to="`/admin/${project}edit/${resource}/${id}`">
                <i class="far fa-edit has-text-dark icon-center"></i>
            </router-link>
        </div>
        <b-button class="delete-button delete-button-color" v-on:click="showModal">
            <i class="far fa-trash-alt has-text-dark icon-center"></i>
        </b-button>
        <div class="modal" :class="{ 'is-active': showModalFlag }">
            <div class="modal-background"></div>
            <div class="modal-card">
                <header class="modal-card-head">
                    <p class="modal-card-title"><i class="fas fa-exclamation-triangle"></i>Vymazanie</p>
                    <button class="delete" aria-label="close" v-on:click="showModal"></button>
                </header>
                <section class="modal-card-body">
                    <p>Naozaj chcete vymazať používateľa?</p>
                </section>
                <footer class="modal-card-foot container">
                    <b-button
                        class="bbutton-remove-style button is-success has-text-dark"
                        icon-left="trash-alt pl-3"
                        icon-pack="far"
                        v-on:click="deleteRow"
                    >
                        Áno
                    </b-button>
                    <b-button
                        class="bbutton-remove-style button back-button-color has-text-dark"
                        icon-left="ban pl-3"
                        icon-pack="fas"
                        v-on:click="showModal"
                    >
                        Nie
                    </b-button>
                </footer>
            </div>
        </div>
    </div>
</template>

<script>
import axios from "axios";

export default {
    data() {
        return {
            showModalFlag: false
        };
    },
    props: ["resource", "id", "project"],
    methods: {
        deleteRow() {
            axios.delete("http://localhost:8080/api/delete/user/" + this.id).then(this.$router.back());
        },
        showModal() {
            this.showModalFlag = !this.showModalFlag;
        }
    }
};
</script>

<style lang="scss" scoped>
#action-buttons .action-button-container,
#action-buttons .delete-button {
    padding: 0.3em;
    display: inline-block;
    border-radius: 3px;
    width: 2.5em;
    margin: 0.1em;
}
#action-buttons .delete-button {
    height: 35px;
}
#action-buttons .delete-button:focus {
    border: none;
    box-shadow: none;
}
#action-buttons .action-button-container:nth-child(1) {
    background-color: hsl(211, 73%, 61%);
}
#action-buttons .action-button-container:nth-child(2) {
    background-color: #ffdd57;
}
#action-buttons .action-button-container:nth-child(1):hover {
    background-color: hsl(211, 73%, 51%);
}
#action-buttons .action-button-container:nth-child(2):hover {
    background-color: hsl(48, 100%, 57%);
}
#action-buttons .delete-button-color {
    background-color: hsl(356, 74%, 65%);
}
#action-buttons .delete-button-color:hover {
    background-color: hsl(356, 74%, 60%);
}
#action-buttons .modal-card {
    width: inherit;
    position: absolute !important;
    top: 50px;
}
#action-buttons .modal-card-body {
    padding: 2em 3em;

    text-align: center;
}
#action-buttons .modal-card-foot {
    margin: 0;
}
#action-buttons .is-success:hover {
    background-color: hsl(141, 53%, 43%);
}
</style>
