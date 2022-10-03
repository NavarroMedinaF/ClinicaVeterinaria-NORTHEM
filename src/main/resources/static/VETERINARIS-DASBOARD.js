const { createApp } = Vue

createApp({
    data() {
        return {
            title: "Holaaaa",
            veterinary: [],
            shiftVeterinary: [],
            email: "jose.argento@gmail.com",
            password: "#Josearg3",
            name: "",
            lastName: "",
            phoneNumber: "",
            veterinaryCategory: "",
            dni: 0,
            client: {},
            shiftClient: [],
            petsClient: [],
            petSearch: 0,
            nameClient: "",
            lastNameClient: "",
            dniCLient: 0,
            pet: [],
            namePet: "",
            racePet: "",
            agePet: "",
            weight: "",
            vaccinesPet: [],
            shiftPet: [],
            medicalHistory: [],
            hospitalizations:[],
            weightPetModify:0,
            agePetModify:0,
            showPet: false,
            dniClientNew:0,
            namePetNew:"",
            racePetNew  :"",                 
            agePetNew :0,
            weightPetNew :0,       
            petTypeNew :"",
            observationNew:"",
            socialWork:"",
            schedulesVeterinary:{},
            contador:0,
        }
    },
    created() {

        this.getVeterinary()

    },

    methods: {
        getVeterinary() {
            axios.get(`/api/veterinaries/current`)
                .then((response) => {
                    this.veterinary = response.data
                    this.name = this.veterinary.name
                    this.lastName = this.veterinary.lastName
                    this.phoneNumber = this.veterinary.phoneNumber
                    this.veterinaryCategory = this.veterinary.veterinaryCategory
                    this.shiftVeterinary = this.veterinary.shifts
                    this.schedulesVeterinary=this.veterinary.schedules
                  
                   
                })
                .catch(response => {
                    const swalWithBootstrapButtons = Swal.mixin({
                        customClass: {
                            cancelButton: 'btn btn-danger'
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
        },

        searchClient() {

            id = this.dni
            axios.get(`/api/clients/${id}`)
                .then(response => {
                    this.client = response.data
                    this.shiftClient = this.client.shift
                    this.petsClient = this.client.pets
                    this.nameClient = this.client.name
                    this.lastNameClient = this.client.lastName
                    this.dniCLient = this.client.dni
                    this.socialWork = this.client.prepaid[0].category.name

                }).catch(response => {
                    const swalWithBootstrapButtons = Swal.mixin({
                        customClass: {
                            cancelButton: 'btn btn-danger'
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
        },

      
        hola(id) {
            this.petSearch = id;
            console.log(id);
            this.contador == 1;
            axios.get(`/api/pets/${id}`)
                .then(response=>{
                    this.pet=response.data
                    this.namePet=this.pet.name
                    this.racePet=this.pet.race
                    this.agePet=this.pet.age
                    this.weight=this.pet.weight
                    this.vaccinesPet=this.pet.vaccines
                    this.shiftPet=this.pet.shifts
                    this.medicalHistory= this.pet.medicalHistory
                    this.hospitalizations= this.medicalHistory.hospitalizations
                    
                })

            .then(response => {
                const swalWithBootstrapButtons = Swal.mixin({
                    customClass: {
                        cancelButton: 'btn btn-danger'
                    },
                    //buttonsStyling: false
                })
                swalWithBootstrapButtons.fire({
                    title: "Borrado",
                    text: response.response.data,
                    icon: "success",
                    showConfirmButton: false,
                    showCancelButton: true,
                    cancelButtonText: 'Aceptar',
                })
            })
        },
        createPet(){
        
            const swalWithBootstrapButtons = Swal.mixin({
                customClass: {
                    confirmButton: 'buttonAccept',
                    cancelButton: 'buttonCancel'
                },
                //buttonsStyling: false
            })
    
          

            swalWithBootstrapButtons.fire({
                title: '¿Estás seguro?',
                text: "Estas por agregar una mascota",
                icon: 'question',
                showCancelButton: true,
                confirmButtonText: 'Aceptar',
                cancelButtonText: 'Cancelar',
                reverseButtons: true
            }).then((result) => {
                if (result.isConfirmed) {
                    axios.post("/api/clients/pets" + "?dni="+this.dniCLient+"&name="+this.namePetNew+"&race="+this.racePetNew+
                    "&age="+this.agePetNew+"&weight="+this.weightPetNew+"&petType="+this.petTypeNew+"&observation="+this.observationNew)
                        .then((response) => {
                            swalWithBootstrapButtons.fire({
                                title: 'Creacion de Mascota',
                                text: "La mascota se ah añadido exitosamente",
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
        deletePet(id){
            console.log(id);
            const swalWithBootstrapButtons = Swal.mixin({
                customClass: {
                    confirmButton: 'buttonAccept',
                    cancelButton: 'buttonCancel'
                },
                //buttonsStyling: false
            })

            swalWithBootstrapButtons.fire({
                title: '¿Estás seguro?',
                text: "Estas por eliminar esta mascota.",
                icon: 'question',
                showCancelButton: true,
                confirmButtonText: 'Aceptar',
                cancelButtonText: 'Cancelar',
                reverseButtons: true
            }).then((result) => {
                if (result.isConfirmed) {
                    axios.delete("/api/clients/pets?id="+ id)
                        .then((response) => {
                            swalWithBootstrapButtons.fire({
                                title: 'Eliminar mascota',
                                text: "La mascota ha sido eliminada exitosamente",
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

        modifyPet(id){

            let bodyModifyPet={
                 id:id,
                 age:this.agePetModify,
                 weigth:this.weightPetModify
            }
            const swalWithBootstrapButtons = Swal.mixin({
                customClass: {
                    confirmButton: 'buttonAccept',
                    cancelButton: 'buttonCancel'
                },
                //buttonsStyling: false
            })

            swalWithBootstrapButtons.fire({
                title: '¿Estás seguro?',
                text: "Estas por modificar esta mascota.",
                icon: 'question',
                showCancelButton: true,
                confirmButtonText: 'Aceptar',
                cancelButtonText: 'Cancelar',
                reverseButtons: true
            }).then((result) => {
                if (result.isConfirmed) {
                    axios.patch("/api/clients/pets", bodyModifyPet)
                        .then((response) => {
                            swalWithBootstrapButtons.fire({
                                title: 'Modificar Mascota',
                                text: "La mascota ha sido modificada exitosamente",
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




    },

    computed: {
    }

}).mount('#app')
function mayus(e) {
    e.value = e.value.toUpperCase();
}
