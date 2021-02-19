export default {
    methods: {
        formatBugId(id) {
            let numberOfDigits = id.toString().length;
            let zeros = "";

            for (let i = 0; i < 5 - numberOfDigits; i++) {
                zeros += "0";
            }
            return "R" + zeros + id;
        },
        formatUserRole(data) {
            if (data == "manager") return "Manažér";
            else if (data == "programmer") return "Programátor";
            else return "Tester";
        },
        formatUserRoleForDB(data) {
            if (data == "Manažér") return "manager";
            else if (data == "Programátor") return "programmer";
            else return "tester";
        }
    }
};
