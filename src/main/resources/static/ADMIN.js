const { createApp } = Vue

createApp({
    data() {
        return {
            email:"",
            password:"",
            name:"",
            lastName:"",
            direction:"",
            phoneNumber:"",
            veterinaryCategory:"",
            dni:0,
            list:"",
            veterinaries:[],
            products:[],
            nameProduct:"",
            brand:"",
            description:"",
            image:"",
            stock:0,
            price:0,
            productCategory:"",
            data:[],
            clientTrue:"",

        }
    },
    created() {
    this.getVeterinaries()
    this.getProducts()  
     this.showClientAuth()
    },

    methods: {
      createVeterinary(){
            let listShift= this.list.split(',')
console.log(listShift);
            const swalWithBootstrapButtons = Swal.mixin({
                customClass: {
                    confirmButton: 'buttonAccept',
                    cancelButton: 'buttonCancel'
                },
                //buttonsStyling: false
            })

            swalWithBootstrapButtons.fire({
                title: '¿Estás seguro?',
                text: "Estas por agregar un veterinario a la base de datos.",
                icon: 'question',
                showCancelButton: true,
                confirmButtonText: 'Aceptar',
                cancelButtonText: 'Cancelar',
                reverseButtons: true
            }).then((result) => {
                if (result.isConfirmed) {
                    axios.post("/api/veterinaries", "email="+this.email+"&name="+this.name+"&lastName="
                    +this.lastName+"&direction="+this.direction+"&phoneNumber="+this.phoneNumber+"&veterinaryCategory="
                    +this.veterinaryCategory +"&dni="+this.dni+"&password=" +this.password+"&list="+listShift )
                        .then((response) => {
                            swalWithBootstrapButtons.fire({
                                title: 'Crear Veterinario',
                                text: "El veterinario a sido creado exitosamente",
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
                                //buttonsStyling: false
                            })

                            swalWithBootstrapButtons.fire({
                                title: "Hemos detectado un error",
                                text: response.response.data,
                                icon: "error",
                                showConfirmButton: false,
                                showCancelButton: true,
                                cancelButtonText: 'Aceptar',
                            })
                            console.log(response);
                        })
                }
            })
},
      getVeterinaries(){
        axios.get("/api/veterinaries")
        .then(response =>{
            this.veterinaries=response.data
            console.log(this.veterinaries);
        })
      },

      deleteVeterinari(id){
        const swalWithBootstrapButtons = Swal.mixin({
            customClass: {
                confirmButton: 'buttonAccept',
                cancelButton: 'buttonCancel'
            },
            //buttonsStyling: false
        })

        swalWithBootstrapButtons.fire({
            title: '¿Estás seguro?',
            text: "Estas por eliminar este veterinario.",
            icon: 'question',
            showCancelButton: true,
            confirmButtonText: 'Aceptar',
            cancelButtonText: 'Cancelar',
            reverseButtons: true
        }).then((result) => {
            if (result.isConfirmed) {
                axios.delete(`/api/veterinaries/${id}`)
                    .then((response) => {
                        swalWithBootstrapButtons.fire({
                            title: 'Eliminar veterinario',
                            text: "El veterinario ha sido eliminada exitosamente",
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
                            //buttonsStyling: false
                        })

                        swalWithBootstrapButtons.fire({
                            title: "Hemos detectado un error",
                            text: response.response.data,
                            icon: "error",
                            showConfirmButton: false,
                            showCancelButton: true,
                            cancelButtonText: 'Aceptar',
                        })
                        console.log(response);
                    })
            }
        })
    },
      getProducts(){
        axios.get("/api/products")
        .then(response =>{
            this.products=response.data
            console.log(this.products);
        })
     },
      createProduct(){
        
        const swalWithBootstrapButtons = Swal.mixin({
            customClass: {
                confirmButton: 'buttonAccept',
                cancelButton: 'buttonCancel'
            },
            //buttonsStyling: false
        })

        swalWithBootstrapButtons.fire({
            title: '¿Estás seguro?',
            text: "Estas por crear un Producto",
            icon: 'question',
            showCancelButton: true,
            confirmButtonText: 'Aceptar',
            cancelButtonText: 'Cancelar',
            reverseButtons: true
        }).then((result) => {
            if (result.isConfirmed) {
                axios.post("/api/admin/product" + "?name="+this.nameProduct+"&brand=" +this.brand+"&description="+this.description+"&image="
                +this.image+"&stock="+this.stock+"&price="+this.stock+"&productCategory="+this.productCategory )
                    .then((response) => {
                        swalWithBootstrapButtons.fire({
                            title: 'Creacion de Nuevo Producto',
                            text: "El producto se ah creado exitosamente",
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
                            //buttonsStyling: false
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
      deleteProduct(id){
        const swalWithBootstrapButtons = Swal.mixin({
            customClass: {
                confirmButton: 'buttonAccept',
                cancelButton: 'buttonCancel'
            },
            //buttonsStyling: false
        })

        swalWithBootstrapButtons.fire({
            title: '¿Estás seguro?',
            text: "Estas por eliminar este producto.",
            icon: 'question',
            showCancelButton: true,
            confirmButtonText: 'Aceptar',
            cancelButtonText: 'Cancelar',
            reverseButtons: true
        }).then((result) => {
            if (result.isConfirmed) {
                
                axios.delete(`/api/admin/product/${id}`)
                    .then((response) => {
                        swalWithBootstrapButtons.fire({
                            title: 'Eliminar producto',
                            text: "El producto ha sido eliminada exitosamente",
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
                            //buttonsStyling: false
                        })

                        swalWithBootstrapButtons.fire({
                            title: "Hemos detectado un error",
                            text: response.response.data,
                            icon: "error",
                            showConfirmButton: false,
                            showCancelButton: true,
                            cancelButtonText: 'Aceptar',
                        })
                        console.log(response);
                    })
            }
        })
    },

          logout(){
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
                                  //buttonsStyling: false
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
    showClientAuth(){
            axios.get("/api/user/current")
            .then(response=>{
              this.data=response.data
              this.clientTrue = this.data.authority

              console.log(this.clientTrue);
            })
          },

},
  




    

    computed: {
    }

}).mount('#app')
function mayus(e) {
    e.value = e.value.toUpperCase();
}
