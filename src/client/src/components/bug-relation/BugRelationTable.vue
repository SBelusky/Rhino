<template>
    <div id="bug-relation-table">
        <p class="bug-section-title">Súvisiace reporty</p>
        <div class="columns pt-1">
            <div class="column is-4">
                <b-table striped hoverable :data="bugRelations" default-sort="id.included" class="bug-components">
                    <b-table-column field="id" label="ID" width="40" v-slot="props">
                        {{ props.row.id.included }}
                    </b-table-column>
                    <b-table-column field="name" label="Popis" v-slot="props">
                        <router-link id="pm-zero" :to="`/admin/project/` + $route.params.projectId + `/detail/bug/` + props.row.id.included">
                            {{ props.row.summarize }}
                        </router-link>
                    </b-table-column>
                    <b-table-column field="category" label="Kategória" v-slot="props">
                        {{ props.row.category }}
                    </b-table-column>
                    <b-table-column field="state" label="Status" v-slot="props">
                        <span
                            class="tag"
                            :style="{
                                backgroundColor: getRelationBugStatus(props.row.state).color
                            }"
                        >
                            {{ getRelationBugStatus(props.row.state).name }}
                        </span>
                    </b-table-column>
                    <b-table-column field="status" label="Vzťah" v-slot="props">
                        {{ props.row.status }}
                    </b-table-column>
                    <b-table-column label="Vymazať" width="40" v-slot="props">
                        <a v-on:click="showModal(props.row.id.included)">
                            <i class="far fa-trash-alt icon-center"></i>
                        </a>
                    </b-table-column>
                    <td slot="empty" colspan="2">
                        Nie sú evidované žiadne záznamy.
                    </td>
                </b-table>
                <router-link id="pm-zero" :to="`/admin/project/` + this.$route.params.projectId + `/bug/` + this.$route.params.id + `/add/relation`">
                    <b-button
                        class="is-success data-table-button mt-5"
                        icon-left="mdi mdi-sticker-plus-outline icon-center"
                        v-on:click="$root.$emit('bug-data-before-save-relation', mainBugData)"
                    >
                        Pridať súvislosť
                    </b-button>
                </router-link>
            </div>
        </div>

        <div class="bug-components  modal" :class="{ 'is-active': showModalFlag }">
            <div class="modal-background"></div>
            <div class="modal-card">
                <header class="modal-card-head">
                    <p class="modal-card-title"><i class="fas fa-exclamation-triangle"></i>Vymazanie</p>
                    <button class="delete" aria-label="close" v-on:click="showModal()"></button>
                </header>
                <section class="modal-card-body">
                    <p>
                        Naozaj chcete vymazať vzťah medzi pôvodným reportom a reportom s ID: <span class="has-text-weight-bold">{{ idOfRelationToDelete }}</span> ?
                    </p>
                </section>
                <footer class="modal-card-foot container">
                    <b-button class="bbutton-remove-style button delete-button-color has-text-dark" icon-left="trash-alt pl-3" icon-pack="far" v-on:click="deleteRelation()">
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
            mainBugData: [],
            bugRelations: [],
            idOfRelationToDelete: null,
            showModalFlag: false
        };
    },
    methods: {
        deleteRelation() {
            let maintBugWithoutOneRelation = {
                id: this.mainBugData.id,
                category: this.mainBugData.category,
                seek_time: this.mainBugData.seekTime,
                summarize: this.mainBugData.summarize,
                description: this.mainBugData.description,
                additional_info: this.mainBugData.additional_info,
                bugHasVersions: this.mainBugData.bugHasVersions,
                bugHasSpecifications: this.mainBugData.bugHasSpecifications,
                bugHasUsers: this.mainBugData.bugHasUsers,
                bugHasBugsContains: []
            };

            for (let i = 0; i < maintBugWithoutOneRelation.bugHasVersions.length; i++) {
                maintBugWithoutOneRelation.bugHasVersions[i].type = maintBugWithoutOneRelation.bugHasVersions[i].id.type;
            }

            for (let i = 0; i < maintBugWithoutOneRelation.bugHasSpecifications.length; i++) {
                maintBugWithoutOneRelation.bugHasSpecifications[i].type = maintBugWithoutOneRelation.bugHasSpecifications[i].specification.type;
            }

            for (let i = 0; i < maintBugWithoutOneRelation.bugHasUsers.length; i++) {
                maintBugWithoutOneRelation.bugHasUsers[i].type = maintBugWithoutOneRelation.bugHasUsers[i].id.type;
            }

            for (let i = 0; i < this.mainBugData.bugHasBugsContains.length; i++) {
                if (this.mainBugData.bugHasBugsContains[i].id.included != this.idOfRelationToDelete) {
                    maintBugWithoutOneRelation.bugHasBugsContains.push({
                        included: { id: this.mainBugData.bugHasBugsContains[i].id.included },
                        status: this.mainBugData.bugHasBugsContains[i].status
                    });
                }
            }

            axios.post("http://localhost:8080/api/project/" + this.$route.params.projectId + "/edit/bug/" + this.$route.params.id, maintBugWithoutOneRelation).then(response => {
                if (response.status == 200) {
                    this.showModalFlag = !this.showModalFlag;
                    this.idOfRelationToDelete = null;
                }
            });
            this.$router.go();
        },
        showModal(row) {
            this.showModalFlag = !this.showModalFlag;
            this.idOfRelationToDelete = row;
        },
        getRelationBugStatus(bug) {
            for (let i = 0; i < bug.length; i++) {
                if (bug[i].specification.type == "Status") {
                    return bug[i].specification;
                }
            }
        }
    },
    mounted() {
        axios.get("http://localhost:8080/api/project/" + this.$route.params.projectId + "/detail/bug/" + this.$route.params.id).then(response => {
            this.mainBugData = response.data;
            this.bugRelations = response.data.bugHasBugsContains;
        });
    },
    watch: {
        $route: function() {
            this.$router.go();
        }
    }
};
</script>

<style lang="scss" scoped>
#bug-relation-table .data-table-button {
    color: #000;
}
</style>
