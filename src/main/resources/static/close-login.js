const app = Vue.createApp({
    data() {
        return {
            data: [],
            clientTrue: "",
            products:[],
        }
    },
    created() {
        this.showClientAuth();
        this.getProducts();
    },
    methods: {
        getProducts(){
            axios.get("/api/products").then(res =>{
                this.products = res.data
                console.log(this.products);
            })
        },
        logout() {
            const swalWithBootstrapButtons = Swal.mixin({
                customClass: {
                    confirmButton: 'buttonAccept',
                    cancelButton: 'buttonCancel'
                },
                buttonsStyling: false
            })
            swalWithBootstrapButtons.fire({
                title: '¿Estás seguro?',
                text: "Estar por cerrar sesión",
                icon: 'question',
                showCancelButton: true,
                confirmButtonText: 'Aceptar',
                cancelButtonText: 'Cancelar',
                reverseButtons: true
            }).then((result) => {
                if (result.isConfirmed) {
                    axios.post('/api/logout')
                        .then((response) => {
                            swalWithBootstrapButtons.fire({
                                title: 'Logout',
                                text: "Has cerrado sesión correctamente",
                                icon: 'success',
                                showCancelButton: true,
                                confirmButtonText: 'Aceptar',
                                cancelButtonText: 'Cancelar',
                                reverseButtons: true
                            })
                            setTimeout(() => {
                                location.reload();
                            }, 2000);
                        })
                        .catch(response => {
                            const swalWithBootstrapButtons = Swal.mixin({
                                customClass: {
                                    cancelButton: 'buttonCancel'
                                },
                                buttonsStyling: false
                            })

                            swalWithBootstrapButtons.fire({
                                title: "Hemos detectado un error",
                                text: response.response.data,
                                icon: "error",
                                showConfirmButton: false,
                                showCancelButton: true,
                                cancelButtonText: 'Aceptar',
                            })
                        })
                }
            })
        },
        showClientAuth() {
            axios.get("/api/user/current")
                .then(response => {
                    this.data = response.data
                    this.clientTrue = this.data.authority
                })
        },

        dasboard() {

            if (this.clientTrue == "ADMIN") {
                window.location.href = '/administrador.html'
            } else if (this.clientTrue == "VETERINARY") {
                window.location.href = '/veterinario.html'
            } else {
                window.location.href = '/dashboard.html'
            }
        }

    }
}).mount("#app")
