const app = Vue.createApp({
    data() {
        return {
            clients: [],
            categories:[],
            idCategory: 0,
        }
    },
    created() {
        this.getData()
    },
    methods: {
        getData() {
            axios.get("api/current/clients")
                .then((resp) => {
                    this.clients = resp.data;
                    console.log(this.clients);
                })
            axios.get("api/category")
                .then((resp) => {
                    this.categories = resp.data;
                    console.log(this.categories);
                })
        },
        getPrepaid(id) {
            axios.post("http://localhost:8080/api/clients/prepaid?idCategory=" + id).then(res => console.log(res))
                .catch(error => console.log(error))
        }
    },
    computed: {

    }
}).mount("#app")