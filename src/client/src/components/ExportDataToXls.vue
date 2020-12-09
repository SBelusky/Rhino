<template>
    <div id="export-to-excel-button">
        <b-button class="export-button" icon-left="mdi mdi-download">
            <download-excel :data="jsonData" :name="fileName + '.xls'" :fields="jsonFields">
                Export
            </download-excel>
        </b-button>
    </div>
</template>

<script>
import JsonExcel from "vue-json-excel";
export default {
    components: {
        JsonExcel
    },
    props: ["jsonData", "jsonFields", "fileName"]
};
</script>

<style lang="scss" scoped>
#export-to-excel-button .export-button {
    background-color: hsl(35, 35%, 63%);
    border: none;
    box-shadow: none;
}
#export-to-excel-button .export-button:hover {
    background-color: hsl(35, 35%, 53%);
}
</style>
