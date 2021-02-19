<template>
    <div id="comment-edit">
        <window-title small-title="| editácia komentáru" :big-title="'Report: ' + $route.params.bugId + ' '" />
        <div class="columns">
            <div class="column is-5 ">
                <article class="media">
                    <div class="media-content">
                        <div class="content">
                            <textarea
                                class="textarea"
                                :class="{ 'invalid-field': errors.description }"
                                rows="6"
                                placeholder="Pridaj komentár..."
                                v-model="description"
                            >
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
                                    <input
                                        class="input"
                                        :class="{ 'invalid-field': errors.spend_time }"
                                        v-model="spend_time"
                                        type="number"
                                    />
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
                            <button class="button back-button-color" v-on:click="$router.back()">
                                <i class="fas fa-ban icon-center"></i>
                                Zrušiť
                            </button>
                            <button class="button is-success" v-on:click="submitComment">
                                <i class="fas fa-long-arrow-alt-left icon-center"></i>Uložiť
                            </button>
                        </div>
                    </div>
                </div>
            </div>
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
            data: [],
            description: null,
            spend_time: null,
            errors: {}
        };
    },
    mounted() {
        window.scrollTo(0, 0);

        axios
            .get(
                "http://localhost:8080/api/project/" +
                    this.$route.params.projectId +
                    "/bug/" +
                    this.$route.params.bugId +
                    "/detail/comment/" +
                    this.$route.params.commentId,
                {
                    headers: {
                        Authorization: "Bearer " + localStorage.getItem("token")
                    }
                }
            )
            .then(response => {
                this.data = response.data;
                this.description = this.data.description;
                this.spend_time = this.data.spend_time;
            });
    },
    methods: {
        submitComment() {
            let editData = {
                id: this.data.id,
                description: this.description,
                spend_time: this.spend_time,
                user: { id: this.data.user.id }
            };

            axios
                .post(
                    "http://localhost:8080/api/project/" +
                        this.$route.params.projectId +
                        "/bug/" +
                        this.$route.params.bugId +
                        "/edit/comment/" +
                        this.$route.params.commentId,
                    editData,
                    {
                        headers: {
                            Authorization: "Bearer " + localStorage.getItem("token")
                        }
                    }
                )
                .then(response => {
                    if (response.status == 200) {
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

<style lang="scss" scoped>
#comment-edit .content {
    margin-bottom: 0 !important;
}
#comment-edit .button {
    margin: 0 0 0 1em;
    color: #000;
    border: 0;
    box-shadow: none;
}
#comment-edit .button.is-success:hover {
    background-color: hsl(141, 53%, 43%);
}
#comment-edit .field.spend-time {
    margin: 1em 0 0 0 !important;
}
#comment-edit input,
#comment-edit textarea {
    box-shadow: none;
    border: 1px solid #b8b8b8b3;
    color: black;
}

#comment-edit input:focus,
#comment-edit textarea:focus {
    /* box-shadow: 0 0 5px rgb(8, 89, 196) !important; */
    box-shadow: none;
    border: 1px solid black;
}
#comment-edit .icon i {
    color: #000;
}
#comment-edit .invalid-field {
    border-color: red !important;
}
</style>
