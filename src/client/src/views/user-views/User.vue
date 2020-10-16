<template>
    <div id="user-view">
        <div class="columns">
            <div class="column is-12">
                <h1 class="testb title is-1 pb-2">Users</h1>
            </div>
        </div>
        <div class="columns padding-in-content">
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
                        default-sort="data.user.id"
                        aria-next-label="Next page"
                        aria-previous-label="Previous page"
                        aria-page-label="Page"
                        aria-current-label="Current page"
                    >
                        <b-table-column field="adas" label="Action" width="110">
                            <a href="">
                                <i class="far fa-eye has-text-info"></i>
                            </a>
                            <a href="">
                                <i class="far fa-edit has-text-dark"></i>
                            </a>
                            <a href="">
                                <i class="far fa-trash-alt has-text-danger"></i>
                            </a>
                        </b-table-column>

                        <b-table-column field="id" label="ID" width="40" sortable v-slot="props">
                            {{ props.row.id }}
                        </b-table-column>

                        <b-table-column field="name" label="Name" sortable v-slot="props">
                            {{ props.row.name }}
                        </b-table-column>

                        <b-table-column field="email" label="E-mail" sortable v-slot="props">
                            {{ props.row.email }}
                        </b-table-column>

                        <b-table-column field="role" label="Role" sortable v-slot="props" v-bind:role="'role'">
                            <span :class="classObject">
                                {{ props.row.role }}
                            </span>
                        </b-table-column>

                        <b-table-column field="created_at" label="Create time" sortable v-slot="props">
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

export default {
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
            classObject: {
                "tag is-success": true
            }
        };
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
/* #user-view a {
    color: black;
} */
#user-view .b-table .is-unselectable,
.table thead td,
.table thead th {
    background-color: rgba($color: #c2c7d0, $alpha: 0.7);
}
#user-view .pagination {
    padding: 0.7em 2em 2em 1em;
}
#user-view .pagination-link.is-current {
    background-color: #c2c7d0;
    border-color: #c2c7d0;
}
</style>
