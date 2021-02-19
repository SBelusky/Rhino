<template>
    <div id="attachment">
        <p class="bug-section-title">Prílohy</p>
        <div class="columns pt-1">
            <div class="column is-4">
                <b-table striped hoverable :data="data" default-sort="id" class="bug-components">
                    <b-table-column field="id" label="ID" width="40" v-slot="props">
                        {{ props.row.id }}
                    </b-table-column>
                    <b-table-column field="name" label="Názov" v-slot="props">
                        <a @click="dowloadAttachment(props.row)">{{ props.row.name | makeSubstringFromName }}</a>
                    </b-table-column>
                    <b-table-column field="size" label="Veľkosť" width="100" v-slot="props">
                        {{ props.row.size }}
                    </b-table-column>
                    <b-table-column field="type" label="Typ" width="100" v-slot="props">
                        {{ props.row.type | makeSubstringFromType }}
                    </b-table-column>
                    <b-table-column label="Vymazať" width="40" v-slot="props">
                        <a v-on:click="showModal(props.row)">
                            <i class="far fa-trash-alt icon-center"></i>
                        </a>
                    </b-table-column>
                    <td slot="empty" colspan="2">
                        Nie sú evidované žiadne záznamy.
                    </td>
                </b-table>
            </div>
        </div>

        <div class="level">
            <div class="level-left">
                <div class="level-item">
                    <div class="file has-name">
                        <label class="file-label">
                            <input class="file-input" type="file" name="resume" v-on:change="handleFileUpload()" />
                            <span class="file-cta">
                                <span class="file-icon">
                                    <i class="fas fa-upload"></i>
                                </span>
                                <span class="file-label">
                                    Vyberte súbor…
                                </span>
                            </span>
                            <span class="file-name"></span>
                        </label>
                    </div>
                </div>
                <div class="level-item" v-if="fileToUpload != null">
                    <button class="button is-success" v-on:click="submitFile(fileToUpload)">
                        <i class="fas fa-long-arrow-alt-left icon-center mr-2"></i>Uložiť prílohu
                    </button>
                </div>
            </div>
        </div>

        <div class="bug-components modal" :class="{ 'is-active': showModalFlag }">
            <div class="modal-background"></div>
            <div class="modal-card">
                <header class="modal-card-head">
                    <p class="modal-card-title"><i class="fas fa-exclamation-triangle"></i>Vymazanie</p>
                    <button class="delete" aria-label="close" v-on:click="showModal"></button>
                </header>
                <section class="modal-card-body">
                    <p>
                        Naozaj chcete vymazať prílohu: <span class="has-text-weight-bold">{{ attachmentToDelete.name }}</span> ?
                    </p>
                </section>
                <footer class="modal-card-foot container">
                    <b-button
                        class="bbutton-remove-style button delete-button-color has-text-dark"
                        icon-left="trash-alt pl-3"
                        icon-pack="far"
                        v-on:click="deleteRow(attachmentToDelete)"
                    >
                        Áno
                    </b-button>
                    <b-button
                        class="bbutton-remove-style button back-button-color has-text-dark"
                        icon-left="ban pl-3"
                        icon-pack="fas"
                        v-on:click="showModal"
                    >
                        Nie
                    </b-button>
                </footer>
            </div>
        </div>
    </div>
</template>

<script>
import axios from "axios";
import ExportDataToXls from "../ExportDataToXls.vue";

export default {
    components: { ExportDataToXls },
    data() {
        return {
            data: [],
            showModalFlag: false,
            attachmentToDelete: { name: null },
            fileToUpload: null
        };
    },
    filters: {
        makeSubstringFromType: function(value) {
            var result = value.substring(value.lastIndexOf("/") + 1).toUpperCase();

            return result;
        },
        makeSubstringFromName: function(value) {
            var result = value.substring(0, value.indexOf("."));

            return result;
        }
    },
    mounted() {
        axios
            .get("http://localhost:8080/api/project/" + this.$route.params.projectId + "/bug/" + this.$route.params.id + "/attachment", {
                headers: {
                    Authorization: "Bearer " + localStorage.getItem("token")
                }
            })
            .then(response => (this.data = response.data));
    },
    methods: {
        dowloadAttachment(attachment) {
            axios
                .get(
                    "http://localhost:8080/api/project/" +
                        this.$route.params.projectId +
                        "/bug/" +
                        this.$route.params.id +
                        "/download/attachment/" +
                        attachment.id,
                    {
                        headers: {
                            Authorization: "Bearer " + localStorage.getItem("token")
                        }
                    },
                    {
                        responseType: "blob"
                    }
                )
                .then(response => {
                    const blob = new Blob([response.data], { type: attachment.type });
                    const link = document.createElement("a");
                    link.href = URL.createObjectURL(blob);
                    link.download = attachment.name;
                    link.click();
                    URL.revokeObjectURL(link.href);
                });
        },
        showModal(row) {
            this.showModalFlag = !this.showModalFlag;
            this.attachmentToDelete = row;
        },
        deleteRow(row) {
            axios.delete(
                "http://localhost:8080/api/project/" +
                    this.$route.params.projectId +
                    "/bug/" +
                    this.$route.params.id +
                    "/delete/attachment/" +
                    row.id,
                {
                    headers: {
                        Authorization: "Bearer " + localStorage.getItem("token")
                    }
                }
            );
            this.attachmentToDelete.name = null;
            this.$router.go();
        },
        handleFileUpload() {
            const fileInput = document.querySelector("#attachment input[type=file]");
            if (fileInput.files.length > 0) {
                const fileName = document.querySelector("#attachment .file-name");
                fileName.textContent = fileInput.files[0].name;
                this.fileToUpload = fileInput.files[0];
            }
        },
        submitFile(file) {
            let data = {
                file_: file,
                name: file.name,
                user: { id: 1 },
                bug: { id: this.$route.params.id }
            };

            let formData = new FormData();
            formData.append("file_", file);
            formData.append("name", file.name);
            formData.append("user.id", 1);
            formData.append("bug.id", this.$route.params.id);

            axios.post(
                "http://localhost:8080/api/project/" +
                    this.$route.params.projectId +
                    "/bug/" +
                    this.$route.params.id +
                    "/upload/attachment",
                formData,
                {
                    headers: {
                        Authorization: "Bearer " + localStorage.getItem("token"),
                        "Content-Type": "multipart/form-data"
                    }
                }
            );
            this.fileToUpload = null;
            this.$router.go();
        }
    }
};
</script>

<style lang="scss">
#attachment .button.is-success {
    color: #000;
}
#attachment .button.is-success:hover {
    background-color: hsl(141, 53%, 43%);
}
#attachment .file-name {
    width: 20em;
}
#attachment .file-label .file-cta {
    background-color: #5199e4;
    color: #000;
}
#attachment .file-label .file-cta:hover {
    background-color: hsl(211, 73%, 51%);
}
</style>
