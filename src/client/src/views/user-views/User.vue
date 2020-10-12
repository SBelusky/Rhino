<template>
    <div id="user-view">
        <div class="columns">
            <div class="column is-12">
                <h1 class="testb title is-1 pb-6">Users</h1>
            </div>
        </div>
        <div class="columns padding-in-content">
            <div class="column is-9">
                <section>
                    <b-table
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
                        default-sort="user.first_name"
                        aria-next-label="Next page"
                        aria-previous-label="Previous page"
                        aria-page-label="Page"
                        aria-current-label="Current page"
                    >
                        <b-table-column field="id" label="ID" width="40" sortable numeric v-slot="props">
                            {{ props.row.id }}
                        </b-table-column>

                        <b-table-column field="user.first_name" label="First Name" sortable v-slot="props">
                            {{ props.row.name }}
                        </b-table-column>

                        <b-table-column field="user.last_name" label="Last Name" sortable v-slot="props">
                            {{ props.row.email }}
                        </b-table-column>

                        <b-table-column field="user.last_name" label="Last Name" sortable v-slot="props" v-bind:role="'role'">
                            <span :class="classObject">
                                {{ props.row.role }}
                            </span>
                        </b-table-column>

                        <b-table-column field="date" label="Date" sortable centered v-slot="props">
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
            //{ 'tag is-success': props.row.role === 'roleA' }
            data: [],
            isPaginated: true,
            isPaginationSimple: false,
            paginationPosition: "bottom",
            defaultSortDirection: "asc",
            sortIcon: "menu-up",
            sortIconSize: "",
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

<style lang="scss" scoped>
.testb {
    background-color: #323232;
    color: white;
    padding-left: 2em;
}
.testc {
    padding-left: 2em;
}
</style>
