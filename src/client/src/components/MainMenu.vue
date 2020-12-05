<template>
  <b-menu
    id="main-menu"
    class="has-background-light"
  >
    <img src="../assets/img/user-avatar.png" />
    <div class="user-info">
      <p>Samuel Beluský</p>
    </div>
    <b-menu-list>
      <router-link
        id="pm-zero"
        :to="'/admin/project/' + projectUrl + '/bug'"
      >
        <b-menu-item
          v-on:click="selected = 'bug'"
          v-bind:class="{ 'active-menu-item': selected == 'bug' }"
          icon="bug"
          icon-pack="fas"
          label="Reporty"
        ></b-menu-item>
      </router-link>
      <b-menu-item
        icon="user-cog"
        icon-pack="fas"
      >
        <template
          slot="label"
          slot-scope="props"
        >
          Programátor
          <i
            class="is-pulled-right"
            id="expand-icon"
            :class="props.expanded ? 'fas fa-minus' : 'fas fa-plus'"
          ></i>
        </template>
        <b-menu-list label="Špecifikácia projektu" />
        <router-link
          id="pm-zero"
          :to="'/admin/project/' + projectUrl + '/category'"
        >
          <b-menu-item
            icon="list-ul"
            icon-pack="fas"
            label="Kategórie"
            v-on:click="selected = 'category'"
            v-bind:class="{ 'active-menu-item': selected == 'category' }"
          ></b-menu-item>
        </router-link>
        <router-link
          id="pm-zero"
          :to="'/admin/project/' + projectUrl + '/version'"
        >
          <b-menu-item
            icon="sort-numeric-up-alt"
            icon-pack="fas"
            label="Verzie"
            v-on:click="selected = 'version'"
            v-bind:class="{ 'active-menu-item': selected == 'version' }"
          ></b-menu-item>
        </router-link>
      </b-menu-item>
      <b-menu-item
        icon="user-tie"
        icon-pack="fas"
      >
        <template
          slot="label"
          slot-scope="props"
        >
          Administrátor
          <i
            class="is-pulled-right"
            id="expand-icon"
            :class="props.expanded ? 'fas fa-minus' : 'fas fa-plus'"
          ></i>
        </template>
        <router-link
          id="pm-zero"
          to="/admin/project"
        >
          <b-menu-item
            v-on:click="selected = 'project'"
            v-bind:class="{ 'active-menu-item': selected == 'project' }"
            icon="project-diagram"
            label="Projekty"
            icon-pack="fas"
          ></b-menu-item>
        </router-link>
        <router-link
          id="pm-zero"
          to="/admin/user"
        >
          <b-menu-item
            v-on:click="selected = 'user'"
            v-bind:class="{ 'active-menu-item': selected == 'user' }"
            icon="user-friends"
            icon-pack="fas"
            label="Používatelia"
          ></b-menu-item>
        </router-link>

        <b-menu-list label="Špecifikácia projektov"> </b-menu-list>
        <router-link
          id="pm-zero"
          to="/admin/priority"
        >
          <b-menu-item
            v-on:click="selected = 'priority'"
            v-bind:class="{ 'active-menu-item': selected == 'priority' }"
            icon="bolt"
            icon-pack="fas"
            label="Priority"
          >
          </b-menu-item>
        </router-link>
        <router-link
          id="pm-zero"
          to="/admin/status"
        >
          <b-menu-item
            v-on:click="selected = 'status'"
            v-bind:class="{ 'active-menu-item': selected == 'status' }"
            label="Statusy"
            icon="check-circle"
            icon-pack="fas"
          >
          </b-menu-item>
        </router-link>
        <router-link
          id="pm-zero"
          to="/admin/reproducibility"
        >
          <b-menu-item
            v-on:click="selected = 'reproducibility'"
            v-bind:class="{ 'active-menu-item': selected == 'reproducibility' }"
            label="Reprodukovateľnosti"
            icon="copy"
            icon-pack="fas"
          ></b-menu-item>
        </router-link>
      </b-menu-item>
      <router-link
        id="pm-zero"
        to="/admin/account"
      >
        <b-menu-item
          v-on:click="selected = 'account'"
          v-bind:class="{ rip: selected == 'account' }"
          icon="user-circle"
          icon-pack="far"
          label="Môj účet"
        ></b-menu-item>
      </router-link>
    </b-menu-list>
    <b-menu-list label="Akcie">
      <b-menu-item
        icon="sign-out-alt"
        icon-pack="fas"
        label="Odhlásiť sa"
      ></b-menu-item>
    </b-menu-list>
  </b-menu>
</template>

<script>
export default {
    data() {
        return {
            selected: "",
            projectUrl: "",
        };
    },
    methods: {
        classChange(param) {
            this.selected = param;
        },
    },
    mounted() {
        this.$root.$on("project-change-handler", (data) => ((this.projectUrl = data), (this.selected = "bug")));
    },
};
</script>

<style lang="scss">
.is-horizontal-center {
    justify-content: center;
}
#main-menu img {
    width: 6em;
    display: block;
    margin-left: auto;
    margin-right: auto;
}
#main-menu .user-info {
    padding-bottom: 1em;
    margin-bottom: 1em;
    border-bottom: 1px solid snow;
}
#main-menu .user-info p {
    text-align: center;
    color: #c2c7d0;
    font-weight: 700;
}
#main-menu {
    max-height: 100vh;
    min-height: 80vh;
    min-width: 250px;
    padding: 1em 0.7em !important;
    padding-bottom: 15em !important;
    margin-bottom: 10em;
    background-color: #343a40 !important;
    font-size: 0.9em;
}
.fa,
.fas,
.far {
    font-size: 1em;
    margin-right: 10px;
    position: relative;
    top: 0px;
}
#main-menu #expand-icon {
    position: relative;
    top: 4px;
    font-size: 0.6em;
}
#main-menu .icon.is-pulled-right {
    position: relative;
    top: -5px;
}
#main-menu .is-active {
    background-color: rgba(245, 245, 245, 0) !important;
}
#main-menu .is-active.is-expanded {
    background-color: rgba(245, 245, 245, 0) !important;
}
#main-menu .menu-list a.is-active {
    color: #c2c7d0 !important;
}
#main-menu .menu-list a {
    color: #c2c7d0 !important;
}
#main-menu .menu-list a:hover {
    background-color: #494e53 !important;
}
#main-menu .menu-label {
    color: #c2c7d0;
}
#main-menu .active-menu-item {
    background-color: #494e53 !important;
}
</style>
