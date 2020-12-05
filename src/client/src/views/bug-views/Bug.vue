<template>
    <div id="data-table">
        <window-title small-title="| prehľad evidencie" big-title="Bugov" />
        <b-button class="is-success data-table-button" icon-left="mdi mdi-sticker-plus-outline icon-center">
            Pridať bug
        </b-button>
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
                        default-sort="created_at"
                        aria-next-label="Next page"
                        aria-previous-label="Previous page"
                        aria-page-label="Page"
                        aria-current-label="Current page"
                    >
                        <b-table-column label="Akcie" width="200" v-slot="props">
                            <table-action-buttons :resource="'project/' + projectId + '/bug'" :id="props.row.id" />
                        </b-table-column>

                        <b-table-column field="name" label="Autor" sortable v-slot="props">
                            {{ props.row.user.name }}
                        </b-table-column>

                        <b-table-column field="name" label="Autor" sortable v-slot="props">
                            {{ props.row.user.name }}
                        </b-table-column>

                        <b-table-column field="category" label="Kategória" sortable v-slot="props">
                            {{ props.row.category.name }}
                        </b-table-column>

                        <b-table-column field="created_at" label="Vytvorenie" sortable v-slot="props">
                            <span>
                                {{ new Date(props.row.created_at).toLocaleDateString() }}
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
            projectId: this.$route.params.id,
            isPaginated: true,
            isPaginationSimple: false,
            paginationPosition: "bottom",
            defaultSortDirection: "desc",
            sortIcon: "arrow-up",
            sortIconSize: "is-small",
            currentPage: 1,
            perPage: 20
        };
    },
    methods: {},
    mounted() {
        axios.get("http://localhost:8080/api/project/" + this.projectId + "/bug").then(response => (this.data = response.data));
    }
};
</script>

<style lang="scss"></style>
