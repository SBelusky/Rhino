<template>
  <div id="project-view">
    <window-title
      small-title="detail"
      :big-title="data.name"
    />
    <div class="columns pt-4">
      <div class="column is-5 user-info">
        <div class="field">
          <label class="label">Názov:</label>
          <div class="control has-icons-left">
            <input
              class="input"
              type="text"
              placeholder="Extra small"
              disabled
              :value="data.name"
            />
            <span class="icon is-left">
              <i class="mdi mdi-tag"></i>
            </span>
          </div>
        </div>
        <div class="field">
          <label class="label">Popis:</label>
          <div class="control has-icons-left">
            <input
              class="input"
              type="text"
              placeholder="Extra small"
              disabled
              :value="data.description"
            />
            <span class="icon is-left">
              <i class="mdi mdi-lead-pencil"></i>
            </span>
          </div>
        </div>
        <div class="skuska">
          <label class="typo__label label">Priradení používatelia: </label>
          <multiselect
            v-model="arrayOfUsers"
            :searchable="false"
            :close-on-select="false"
            tag-placeholder="Add this as new tag"
            label="name"
            track-by="id"
            :options="allUsers"
            :multiple="true"
            :taggable="true"
            @tag="addTag"
          ></multiselect>
          <!--  <pre class="language-json"><code>{{ value  }}</code></pre> -->
        </div>
        <!--                 {{ data.user_has_projects }} -->
      </div>
    </div>
    <div class="project-view-button pb-2">
      <button
        class="button"
        v-on:click="$router.back()"
      ><i class="fas fa-long-arrow-alt-left icon-center"></i>Späť</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import Multiselect from "vue-multiselect";
import WindowTitle from "../../components/WindowTitle.vue";

export default {
    components: {
        WindowTitle,
        Multiselect,
    },
    data() {
        return {
            data: [],
            allUsers: [],
            arrayOfUsers: [
                { id: 3, name: "Používateľ A" },
                { id: 4, name: "Používateľ B" },
            ],
            value: null,
            options: [
                { name: "Vue.js", code: "vu" },
                { name: "Javascript", code: "js" },
                { name: "Open Source", code: "os" },
            ],
        };
    },
    mounted() {
        axios.get("http://localhost:8080/api/project/" + this.$route.params.id).then((response) => (this.data = response.data));
        axios.get("http://localhost:8080/api/user/").then((response) => (this.allUsers = response.data));
        console.log("asd");
    },
    computed: {
        arrayToString: function (userHasProjects) {
            var text = "";

            for (i = 0; i < userHasProjects.length; i++) {
                text += userHasProjects[i].user.name + ", ";
            }
            return "asdasd";
        },
    },
    methods: {
        addTag(newTag) {
            const tag = {
                name: newTag,
                code: newTag.substring(0, 2) + Math.floor(Math.random() * 10000000),
            };
            this.options.push(tag);
            this.value.push(tag);
        },
    },
};
</script>

<style lang="scss" scoped>
#project-view .user-info {
    box-shadow: 0px 0px 20px 0px rgba(0, 0, 0, 0.1);
    padding: 2em;
    background-color: #fff;
    display: block !important;
}
#project-view .project-view-button button {
    color: black;
    background-color: #5199e4;
    border: none;
}
#project-view .project-view-button button:hover {
    background-color: hsl(211, 73%, 51%);
}
#project-view .project-view-button button:focus {
    box-shadow: none;
    border: none;
}
#project-view .control.has-icons-left .icon,
#project-view .control.has-icons-right .icon {
    color: #7a7a7a;
}
#project-view .columns {
    margin-left: 0;
}
#project-view .input {
    border: 1px solid #b8b8b8b3;
}
</style>
