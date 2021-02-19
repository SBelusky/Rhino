<template>
    <div id="data-table">
        <window-title small-title="| prehľad evidencie" big-title="Reporty" />
        <router-link id="pm-zero" :to="`/admin/project/` + projectId + `/add/bug`">
            <b-button class="is-success data-table-button" icon-left="mdi mdi-sticker-plus-outline icon-center">
                Pridať bug
            </b-button>
        </router-link>
        <div class="columns pt-4">
            <div class="column is-9">
                <section>
                    <b-table
                        :data="data"
                        :paginated="isPaginated"
                        :per-page="perPage"
                        :current-page="currentPage"
                        :pagination-simple="isPaginationSimple"
                        :pagination-position="paginationPosition"
                        :default-sort-direction="defaultSortDirection"
                        :sort-icon="sortIcon"
                        :sort-icon-size="sortIconSize"
                        default-sort="created_at"
                        aria-next-label="Next page"
                        aria-previous-label="Previous page"
                        aria-page-label="Page"
                        aria-current-label="Current page"
                    >
                        <b-table-column label="Akcie" width="200" v-slot="props">
                            <table-action-buttons
                                resource="bug"
                                :project="'project/' + projectId + '/'"
                                :id="props.row.id"
                                typeForDelete="report s ID"
                                :nameForDelete="props.row.id"
                            />
                        </b-table-column>

                        <b-table-column field="id" label="ID" sortable v-slot="props">
                            {{ formatBugId(props.row.id) }}
                        </b-table-column>

                        <b-table-column field="priority" label="Priorita" sortable v-slot="props">
                            {{ getSpecificationFromArray(props.row.bugHasSpecifications, "Priority").name }}
                        </b-table-column>

                        <b-table-column field="status" label="Status" sortable v-slot="props">
                            <span
                                class="tag"
                                :style="{
                                    backgroundColor: getSpecificationFromArray(props.row.bugHasSpecifications, 'Status').color
                                }"
                            >
                                {{ getSpecificationFromArray(props.row.bugHasSpecifications, "Status").name }}
                            </span>
                        </b-table-column>

                        <b-table-column field="summarize" label="Zhrnutie" sortable v-slot="props">
                            {{ props.row.summarize }}
                        </b-table-column>

                        <b-table-column field="category" label="Kategória" sortable v-slot="props">
                            {{ props.row.category.name }}
                        </b-table-column>
                        <b-table-column field="FoundInVersion" label="Verzia nájdenia" sortable v-slot="props">
                            {{ getVersionFromArray(props.row.bugHasVersions, "Found in version").name }}
                        </b-table-column>

                        <b-table-column field="RepairedInVersion" label="Verzia vyriešenia" sortable v-slot="props">
                            {{ getVersionFromArray(props.row.bugHasVersions, "Repaired in version").name }}
                        </b-table-column>

                        <b-table-column field="AssociatedUser" label="Priradený" sortable v-slot="props">
                            {{ getAssociatedUserFromArray(props.row.bugHasUsers, "Associated user").name }}
                        </b-table-column>

                        <b-table-column field="created_at" label="Vytvorenie" sortable v-slot="props">
                            <span>
                                {{ new Date(props.row.created_at).toLocaleDateString() }}
                            </span>
                        </b-table-column>
                        <b-table-column field="edited_at" label="Editácia" sortable v-slot="props">
                            <span>
                                {{ new Date(props.row.edited_at).toLocaleDateString() }}
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
    title: "Reporty | prehľad",
    components: {
        TableActionButtons,
        WindowTitle
    },
    data() {
        return {
            data: [],
            projectId: this.$store.state.actualProject,
            isPaginated: true,
            isPaginationSimple: false,
            paginationPosition: "bottom",
            defaultSortDirection: "desc",
            sortIcon: "arrow-up",
            sortIconSize: "is-small",
            currentPage: 1,
            perPage: 15
        };
    },
    methods: {
        getSpecificationFromArray(obj, type) {
            for (let i = 0; i < obj.length; i++) {
                if (obj[i].specification.type == type) return obj[i].specification;
            }
            return "";
        },
        getAssociatedUserFromArray(obj, type) {
            for (let i = 0; i < obj.length; i++) {
                if (obj[i].id.type == type) return obj[i].user;
            }
            return "";
        },
        getVersionFromArray(obj, type) {
            for (let i = 0; i < obj.length; i++) {
                if (obj[i].id.type == type) return obj[i].version;
            }
            return "";
        }
    },
    mounted() {
        axios
            .get("http://localhost:8080/api/project/" + this.projectId + "/bug", {
                headers: {
                    Authorization: "Bearer " + localStorage.getItem("token")
                }
            })
            .then(response => (this.data = response.data));
    }
};
</script>

<style lang="scss">
.x {
    background-color: #fff !important;
}
</style>
