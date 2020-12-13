import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        actualProject: 1,
        mainMenuSelectedItem: "bug",
        componentKey: 0
    },
    plugins: [createPersistedState()],
    mutations: {
        CHANGE_PROJECT(state, projectId) {
            state.actualProject = projectId;
        },
        CHANGE_MAIN_MENU_ITEM(state, itemName) {
            state.mainMenuSelectedItem = itemName;
        }
    },
    actions: {},
    modules: {}
});
