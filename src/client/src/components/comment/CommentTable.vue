<template>
    <div id="comments">
        <p class="bug-section-title">Komentáre</p>
        <div class="columns">
            <br />
            <div class="column is-5 ">
                <ul>
                    <li v-for="comment in comments" :key="comment.id">
                        <article class="media">
                            <figure class="media-left">
                                <p class="image is-64x64">
                                    <img src="@/assets/img/comment.jpg" />
                                </p>
                            </figure>
                            <div class="media-content">
                                <div class="content">
                                    <strong>{{ comment.user.name }}</strong> |
                                    <small>{{ comment.created_at | moment("DD. MM. YYYY HH:mm") }}</small>
                                    <br />
                                    <textarea class="textarea" rows="6" placeholder="Add a comment..." disabled :value="comment.description"> </textarea>
                                </div>
                                <nav class="level is-mobile">
                                    <div class="level-left">
                                        <a class="level-item">
                                            <span class="icon is-small">
                                                <router-link id="pm-zero" :to="`/admin/project/${actualProject}/bug/${$route.params.id}/edit/comment/${comment.id}`">
                                                    <i class="far fa-edit" v-on:click="showModal(comment.id)"></i>
                                                </router-link>
                                            </span>
                                        </a>
                                        <a class="level-item">
                                            <span class="icon is-small"><i class="far fa-trash-alt" v-on:click="showModal(comment.id)"></i></span>
                                        </a>
                                    </div>
                                    <div class="level-right">
                                        <p><strong>Čas hľadania:</strong> {{ comment.spend_time }} min</p>
                                    </div>
                                </nav>
                            </div>
                        </article>
                    </li>
                    <li class="add-comment">
                        <article class="media">
                            <figure class="media-left">
                                <p class="image is-64x64">
                                    <img src="@/assets/img/comment.jpg" />
                                </p>
                            </figure>
                            <div class="media-content">
                                <div class="content">
                                    <textarea class="textarea" :class="{ 'invalid-field': errors.description }" rows="6" placeholder="Pridaj komentár..." v-model="description">
                                    </textarea>
                                </div>
                                <div v-if="errors.description">
                                    <p class="help is-danger">{{ this.errors.description }}</p>
                                </div>
                            </div>
                        </article>
                        <div class="level">
                            <div class="level-left">
                                <div class="level-item">
                                    <div class="field spend-time">
                                        <label class="label"><span class="has-text-danger">* </span>Čas hľadania [min]: </label>
                                        <div class="control has-icons-left">
                                            <input class="input" :class="{ 'invalid-field': errors.spend_time }" v-model="spend_time" type="number" />
                                            <span class="icon is-left">
                                                <i class="mdi mdi-alarm"></i>
                                            </span>
                                        </div>
                                        <div v-if="errors.spend_time">
                                            <p class="help is-danger">{{ this.errors.spend_time }}</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="level-right">
                                <div class="level-item">
                                    <button class="button is-success" v-on:click="submitComment"><i class="fas fa-long-arrow-alt-left icon-center"></i>Uložiť</button>
                                </div>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
        <div class="bug-components modal" :class="{ 'is-active': showModalFlag }">
            <div class="modal-background"></div>
            <div class="modal-card">
                <header class="modal-card-head">
                    <p class="modal-card-title"><i class="fas fa-exclamation-triangle"></i>Vymazanie</p>
                    <button class="delete" aria-label="close" v-on:click="showModal"></button>
                </header>
                <section class="modal-card-body">
                    <p>Naozaj chcete vymazať komentár?</p>
                </section>
                <footer class="modal-card-foot container">
                    <b-button class="bbutton-remove-style button delete-button-color has-text-dark" icon-left="trash-alt pl-3" icon-pack="far" v-on:click="deleteComment">
                        Áno
                    </b-button>
                    <b-button class="bbutton-remove-style button back-button-color has-text-dark" icon-left="ban pl-3" icon-pack="fas" v-on:click="showModal">
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
            comments: [],
            actualProject: this.$store.state.actualProject,
            description: null,
            spend_time: 0,
            errors: {},
            showModalFlag: false,
            commentToDelete: null
        };
    },
    mounted() {
        axios.get("http://localhost:8080/api/project/" + this.actualProject + "/bug/" + this.$route.params.id + "/comment").then(response => {
            response.data.forEach(comment => {
                this.comments.push(comment);
            });
        });
    },
    methods: {
        submitComment() {
            let editData = {
                description: this.description,
                spend_time: this.spend_time,
                user: { id: 1 }
            };

            axios
                .post("http://localhost:8080/api/project/" + this.actualProject + "/bug/" + this.$route.params.id + "/add/comment", editData)
                .then(response => {
                    if (response.status == 201) {
                        this.$router.go();
                    }
                })
                .catch(errors => {
                    this.errors = null;
                    this.errors = errors.response.data;
                });
        },
        showModal(id) {
            this.showModalFlag = !this.showModalFlag;
            this.commentToDelete = id;
        },
        deleteComment() {
            axios.delete("http://localhost:8080/api/project/" + this.actualProject + "/bug/" + this.$route.params.id + "/delete/comment/" + this.commentToDelete);

            this.$router.go();
        }
    }
};
</script>

<style lang="scss" scoped>
#comments li {
    margin-top: 2em;
}
#comments .add-comment .content {
    margin-bottom: 0 !important;
}
#comments .button.is-success {
    margin: 1em 0 0 5em;
    color: #000;
}
#comments .button.is-success:hover {
    background-color: hsl(141, 53%, 43%);
}
#comments .control {
    max-width: 10em;
}
#comments .field.spend-time {
    margin: 1em 0 0 5em !important;
}
#comments .level-left i {
    color: black;
    padding: 0 !important;
    margin: 0 !important;
}
#comments .level-left i:hover {
    color: #4b85c4;
}
#comments .level-left .level-item:not(:last-child) {
    margin-right: 0.5em !important;
}
#comments .action-button-container,
#comments .delete-button {
    padding: 0.3em;
    display: inline-block;
    border-radius: 3px;
    width: 2.5em;
    margin: 0.1em;
}
#comments .delete-button {
    height: 33.6px;
    border: none;
}
</style>
