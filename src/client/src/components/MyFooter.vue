<template>
    <div id="my-footer">
        <footer class="footer">
            <div class="content has-text-centered is-vcentered">
                <p>
                    <img src="../assets/img/rhino_logo_small.png" width="80" />
                    <strong>Rhino</strong> bug tracking system - by <strong>Samuel Beluský</strong>
                </p>
            </div>
        </footer>
    </div>
</template>

<script>
export default {};
</script>

<style lang="scss" scoped>
.footer {
    padding: 6rem 6rem 6rem;
    background-color: rgba(65, 88, 110, 0.8) !important;
}
.footer p,
.footer strong {
    color: white;
}
.footer strong {
    text-decoration: underline;
}
#my-footer img {
    position: relative;
    top: 19px;
    margin-right: 1em;
}
</style>
