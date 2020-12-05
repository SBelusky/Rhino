<template>
    <div class="window-title">
        <div class="columns">
            <div class="column is-5">
                <div class="title-block">
                    <h1 class="title is-1 pb-4 title-under-navBar">
                        {{ bigTitle }}<span class="small-title"> {{ smallTitle }} </span>
                    </h1>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    props: ["bigTitle", "smallTitle"]
};
</script>

<style lang="scss" scoped>
.window-title .title-block {
    /* background-color: rgb(122, 138, 153); */
    padding-bottom: 1em;
    margin-bottom: 1em;
    border-bottom: 2px solid #41586e;
    /* max-width: 1000px; */
}
.title-under-navBar {
    color: black;
}
.small-title {
    color: #a9a9a9;
    font-size: 0.5em;
}
</style>
