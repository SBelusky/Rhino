<template>
    <div id="attachment-upload">
        <window-title small-title="| pridanie prílohy" :big-title="'Report: ' + $route.params.bugId + ' '" />
        <div class="columns pt-1">
            <div class="column is-4">
                <div class="file is-large is-boxed has-name">
                    <label class="file-label">
                        <input class="file-input" type="file" name="resume" />
                        <span class="file-cta">
                            <span class="file-icon">
                                <i class="fas fa-upload"></i>
                            </span>
                            <span class="file-label">
                                Large file…
                            </span>
                        </span>
                        <span class="file-name">
                            Screen Shot 2017-07-29 at 15.54.25.png
                        </span>
                    </label>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import WindowTitle from "../../components/WindowTitle.vue";

export default {
    components: {
        WindowTitle
    },
    mounted() {
        window.scrollTo(0, 0);
    }
};
</script>

<style lang="scss" scoped></style>
