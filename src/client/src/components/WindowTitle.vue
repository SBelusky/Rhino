<template>
    <div class="window-title">
        <div class="columns">
            <div class="column is-5">
                <div class="title-block">
                    <h1 class="title is-1 pb-1 title-under-navBar">
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
@import url("https://fonts.googleapis.com/css?family=Raleway:200");

.window-title .title-block {
    padding-bottom: 1em;
    margin-bottom: 2em;
    border-bottom: 4px solid #5199e4;
}
.title-under-navBar {
    color: black;
}
.small-title {
    color: #a9a9a9;
    font-size: 0.5em;
}
</style>
