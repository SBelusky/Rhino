import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        actualProject: 1,
        mainMenuSelectedItem: "bug",
        accountButtonType: null,
        componentKey: 0
    },
    plugins: [createPersistedState()],
    mutations: {
        //hodnota sa zmení keď nastane zmena projektu v navbare
        CHANGE_PROJECT(state, projectId) {
            state.actualProject = projectId;
        },
        //Zmena podfarbenia aktívneho itemu v hľavnom menu
        CHANGE_MAIN_MENU_ITEM(state, itemName) {
            state.mainMenuSelectedItem = itemName;
        },
        ACCOUNT_BUTTON_TYPE(state, type) {
            state.accountButtonType = type;
        }
    },
    actions: {},
    modules: {}
});
