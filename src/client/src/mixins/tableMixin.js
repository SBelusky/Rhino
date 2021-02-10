export default {
    methods: {
        formatBugId(id) {
            let numberOfDigits = id.toString().length;
            let zeros = "";

            for (let i = 0; i < 5 - numberOfDigits; i++) {
                zeros += "0";
            }
            return "R" + zeros + id;
        }
    }
};
