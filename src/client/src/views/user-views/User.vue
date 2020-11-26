<template>
    <div id="user-view">
        <window-title small-title="prehľad" big-title="Používatelia" />
        <b-button class="is-success user-buttons" icon-left="mdi mdi-sticker-plus-outline">
            Pridať používateľa
        </b-button>
        <div class="columns pt-4">
            <div class="column is-9">
                <section>
                    <b-table
                        striped
                        hoverable
                        :data="data"
                        :columns="columns"
                        :paginated="isPaginated"
                        :per-page="perPage"
                        :current-page="currentPage"
                        :pagination-simple="isPaginationSimple"
                        :pagination-position="paginationPosition"
                        :default-sort-direction="defaultSortDirection"
                        :sort-icon="sortIcon"
                        :sort-icon-size="sortIconSize"
                        default-sort="data.user.id"
                        aria-next-label="Next page"
                        aria-previous-label="Previous page"
                        aria-page-label="Page"
                        aria-current-label="Current page"
                    >
                        <b-table-column label="Akcie" width="200">
                            <table-action-buttons view-link="" edit-link="" delete-link="" />
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
                            <span :class="classObject">
                                {{ props.row.role }}
                            </span>
                        </b-table-column>

                        <b-table-column field="created_at" label="Vytvorenie" sortable v-slot="props">
                            <span>
                                {{ new Date(props.row.created_at).toLocaleDateString() }}
                            </span>
                        </b-table-column>
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
    computed: {
        classObject: function() {
            return {
                /*  active: this.isActive && !this.error,
                'text-danger': this.error && this.error.type === 'fatal' */

                "tag admin": this.role === "Tester"
            };
        }
    },
    mounted() {
        axios.get("http://localhost:8080/api/user").then(response => (this.data = response.data));
    }
};
</script>

<style lang="scss">
#user-view .b-table {
    box-shadow: 0px 0px 20px 0px rgba(0, 0, 0, 0.1);
}
#user-view .level.top {
    max-height: 50px !important;
}
#user-view .b-table .is-unselectable,
.table thead td,
.table thead th {
    background-color: rgba(65, 88, 110, 0.7);
    color: white !important;
}
#user-view .pagination {
    padding: 0.7em 2em 2em 1em;
}
#user-view .pagination-link.is-current {
    background-color: rgba(65, 88, 110, 0.7);
    border-color: rgba(65, 88, 110, 0.7);
}
#user-view .user-buttons {
    color: black;
}
#user-view .user-buttons:hover {
    background-color: hsl(141, 53%, 43%);
}
</style>
