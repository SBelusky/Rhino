<template>
    <div id="data-table">
        <window-title small-title="| prehľad evidencie" big-title="Projekty" />
        <router-link id="pm-zero" :to="`/admin/add/project`">
            <b-button class="is-success data-table-button" icon-left="mdi mdi-sticker-plus-outline icon-center">
                Pridať projekt
            </b-button>
        </router-link>
        <div class="columns pt-4">
            <div class="column is-9">
                <section>
                    <b-table
                        striped
                        hoverable
                        :data="data"
                        :paginated="isPaginated"
                        :per-page="perPage"
                        :current-page="currentPage"
                        :pagination-simple="isPaginationSimple"
                        :pagination-position="paginationPosition"
                        :default-sort-direction="defaultSortDirection"
                        :sort-icon="sortIcon"
                        :sort-icon-size="sortIconSize"
                        default-sort="id"
                        aria-next-label="Next page"
                        aria-previous-label="Previous page"
                        aria-page-label="Page"
                        aria-current-label="Current page"
                    >
                        <b-table-column label="Akcie" width="200" v-slot="props">
                            <table-action-buttons
                                resource="project"
                                :id="props.row.id"
                                project=""
                                typeForDelete="projekt"
                                :nameForDelete="props.row.name"
                            />
                        </b-table-column>

                        <b-table-column field="id" label="ID" width="60" sortable v-slot="props">
                            {{ props.row.id }}
                        </b-table-column>

                        <b-table-column field="name" label="Názov" sortable v-slot="props">
                            {{ props.row.name }}
                        </b-table-column>

                        <b-table-column field="email" label="Popis" sortable v-slot="props">
                            {{ props.row.description }}
                        </b-table-column>

                        <b-table-column field="role" label="Priradení používatelia" sortable v-slot="props" v-bind:role="'role'">
                            {{ props.row.user_has_projects.length }}
                        </b-table-column>

                        <b-table-column field="created_at" label="Vytvorenie" sortable v-slot="props">
                            <span>
                                {{ props.row.created_at | moment("DD. MM. YYYY hh:mm") }}
                            </span>
                        </b-table-column>
                        <b-table-column field="edited_at" label="Editácia" sortable v-slot="props">
                            <span>
                                {{ props.row.edited_at | moment("DD. MM. YYYY hh:mm") }}
                            </span>
                        </b-table-column>
                        <td slot="empty" colspan="2">
                            Nie sú evidované žiadne záznamy.
                        </td>
                    </b-table>
                </section>
            </div>
        </div>
    </div>
</template>

<script>
import axios from "axios";
import TableActionButtons from "../../components/TableActionButtons.vue";
import WindowTitle from "../../components/WindowTitle.vue";

export default {
    components: {
        TableActionButtons,
        WindowTitle
    },
    data() {
        return {
            data: [],
            isPaginated: true,
            isPaginationSimple: false,
            paginationPosition: "bottom",
            defaultSortDirection: "asc",
            sortIcon: "arrow-up",
            sortIconSize: "is-small",
            currentPage: 1,
            perPage: 20
        };
    },
    mounted() {
        axios
            .get("http://localhost:8080/api/project", {
                headers: {
                    Authorization: "Bearer " + localStorage.getItem("token")
                }
            })
            .then(response => (this.data = response.data));
    }
};
</script>

<style lang="scss"></style>
