<template>
    <div id="action-buttons">
        <div class="action-button-container">
            <a href="">
                <i class="far fa-eye has-text-dark"></i>
            </a>
        </div>
        <div class="action-button-container">
            <a href="">
                <i class="far fa-edit has-text-dark"></i>
            </a>
        </div>
        <div class="action-button-container">
            <a href="">
                <i class="far fa-trash-alt has-text-dark"></i>
            </a>
        </div>
    </div>
</template>

<script>
export default {};
</script>

<style lang="scss" scoped>
#action-buttons .action-button-container {
    padding: 0.3em;
    display: inline-block;
    border-radius: 3px;
    width: 2.5em;
    margin: 0.1em;
}
#action-buttons .action-button-container i {
    text-align: center;
    display: inline-block;
    width: 100%;
}
#action-buttons .action-button-container:nth-child(1) {
    background-color: #5199e4;
}
#action-buttons .action-button-container:nth-child(2) {
    background-color: #ffdd57;
}
#action-buttons .action-button-container:nth-child(3) {
    background-color: #e65b65;
}
</style>
