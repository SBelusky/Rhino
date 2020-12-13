<template>
    <div id="data-table">
        <window-title small-title="| prehľad evidencie" big-title="Používatelia" resource="user" />
        <div class="columns">
            <div class="column is-9 pt-0 pb-0">
                <div class="level">
                    <div class="level-left">
                        <router-link id="pm-zero" :to="`/admin/add/user`">
                            <b-button class="is-success data-table-button" icon-left="mdi mdi-sticker-plus-outline icon-center">
                                Pridať používateľa
                            </b-button>
                        </router-link>
                    </div>
                    <div class="level-right">
                        <export-data-to-xls :jsonData="data" :jsonFields="jsonFields" fileName="pouzivatelia" />
                    </div>
                </div>
            </div>
        </div>

        <div class="columns pt-1">
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
                                resource="user"
                                :id="props.row.id"
                                project=""
                                typeForDelete="používateľa"
                                :nameForDelete="props.row.name"
                            />
                        </b-table-column>

                        <b-table-column field="id" label="ID" width="60" sortable v-slot="props">
                            {{ props.row.id }}
                        </b-table-column>

                        <b-table-column field="name" label="Meno" sortable v-slot="props">
                            {{ props.row.name }}
                        </b-table-column>

                        <b-table-column field="email" label="E-mail" sortable v-slot="props">
                            {{ props.row.email }}
                        </b-table-column>

                        <b-table-column field="telephone" label="Telefón" sortable v-slot="props">
                            {{ props.row.telephone_number }}
                        </b-table-column>

                        <b-table-column field="role" label="Rola" sortable v-slot="props" v-bind:role="'role'">
                            <span :class="classObject(props.row.role)">
                                {{ props.row.role }}
                            </span>
                        </b-table-column>

                        <b-table-column field="created_at" label="Vytvorený" sortable v-slot="props">
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
import ExportDataToXls from "../../components/ExportDataToXls.vue";

export default {
    components: {
        TableActionButtons,
        WindowTitle,
        ExportDataToXls
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
            perPage: 20,
            jsonFields: {
                ID: "id",
                Meno: "name",
                "E-mail": "email",
                "Telefónne číslo": "telephone_number",
                "Prihlasovacie meno": "login_name",
                "Prihlasovacie heslo": "login_password",
                Rola: "role",
                Vytvorený: "created_at",
                Editovaný: "edited_at"
            }
        };
    },
    methods: {
        classObject: function(role) {
            return {
                "tag admin": role === "Administrátor",
                "tag tester": role === "Tester",
                "tag programmer": role === "Programátor"
            };
        }
    },
    mounted() {
        axios.get("http://localhost:8080/api/user").then(response => (this.data = response.data));
    }
};
</script>

<style lang="scss"></style>
