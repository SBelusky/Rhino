<template>
    <div id="log">
        <p class="bug-section-title">Logy</p>
        <div class="columns pt-3 pb-4">
            <div class="column is-5">
                <b-table striped hoverable :data="bugLogs" default-sort="created_at" default-sort-direction="desc" class="bug-components">
                    <b-table-column field="id" label="ID" width="40" v-slot="props">
                        <strong>{{ props.row.id }}</strong>
                    </b-table-column>
                    <b-table-column field="user.name" label="Používateľ" v-slot="props">
                        {{ props.row.user.name }}
                    </b-table-column>
                    <b-table-column field="size" label="Akcia" v-slot="props">
                        {{ props.row.status }}
                    </b-table-column>
                    <b-table-column field="message" label="Správa" v-slot="props">
                        {{ props.row.message }}
                    </b-table-column>
                    <b-table-column field="created_at" label="Vytvorené" v-slot="props">
                        {{ props.row.created_at | moment("DD. MM. YYYY HH:mm") }}
                    </b-table-column>
                    <td slot="empty" colspan="2">
                        Nie sú evidované žiadne záznamy.
                    </td>
                </b-table>
            </div>
        </div>
    </div>
</template>

<script>
import axios from "axios";

export default {
    data() {
        return {
            bugLogs: []
        };
    },
    mounted() {
        axios
            .get("http://localhost:8080/api/project/" + this.$route.params.projectId + "/bug/" + this.$route.params.id + "/log", {
                headers: {
                    Authorization: "Bearer " + localStorage.getItem("token")
                }
            })
            .then(response => (this.bugLogs = response.data));
    }
};
</script>

<style lang="scss" scoped>
#log {
    font-size: 0.9em;
}
</style>
