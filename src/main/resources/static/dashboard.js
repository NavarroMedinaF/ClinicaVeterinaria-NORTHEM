const app = Vue.
    createApp({
        data() {
            return {

                clients: [],
                pets: [],
                petShifts: [],
                pet: [],
                idPet: 0,
                arrayCarritoDeCompras: [],
                cards: [],
                dateActual: [],
                prepaid: [],
                shifts: [],
                onlyPetShifts:[],



            clienteDeShifts:[],
            clienteMascotasDeShifts:[],
            veterinariosDeShifts:[],
            veterinarioCardiologoDeShifts:[],
            veterinarioCirujanoDeShifts:[],
            veterinarioOftalmologoDeShifts:[],
            veterinarioClinicoDeShifts:[],
            veterinarioHorariosDeShifts:[],

            tipoTurnoDeShifts:"",

            especialidadElegidaDeShifts:"",
            medicoElegidoDeShifts:"",
            fechaDeShifts:"",
            tiempoDeShifts:"",
            nombrePersonaDeShifts:[],
            turnosDeShifts:[],
            idVeterinarioDeShifts:0,
            idMascotaDeShifts:0,


            motivoClienteDeShifts:"CLINICO",
            medicoClienteDeShifts:"",
            fechaClienteDeShifts:"",
            tiempoClienteDeShifts:"",
            infoMedicoClienteDeShifts:[],
            turnosClienteDeShifts:[],
            idVeterinarioClienteDeShifts:0,



            modifyClient:{
                name:"",
                lastName:"",
                address:"",
                city:"",
                state:"",
                email:"",
                password:"",
                postalCode:0,
            }
            }
        },
        created() {
            console.log("ssssssssss");
            this.getClients();
            this.getProductos();
            this.getPetShifts();
            this.getTurnos()
            this.getVeterinarios()
        },
        methods: {
        getClients() {
                axios.get("/api/current/clients")
                    .then(res => {
                        this.clients = res.data;
                        console.log(this.clients);
                        this.pets = this.clients.pets
                        this.purchases = this.clients.purchases
                        this.prepaid = this.clients.prepaid
                        console.log(this.prepaid[0]);
                        this.shifts = this.clients.shifts
                        console.log(this.shifts);


                console.log(res.data);
                this.clienteDeShifts = res.data;
                console.log(this.clienteDeShifts.pets);
                this.clienteMascotasDeShifts = this.clienteDeShifts.pets
                console.log("estoy autenticadazo")
                    })
        },

            getPetShifts(){
                axios.get("/api/current/clients")
                .then(res =>{
                    this.pets = res.data.pets
                    this.pets.forEach(pet => {
                        this.petShifts = pet.shifts
                    });
                })
            },
            getPet(id) {
                this.idPet = id
                this.pet = this.pets.filter(pet => pet.id == id)
                console.log(this.pet);
                this.onlyPetShifts = this.pet[0].shifts
                console.log(this.onlyPetShifts);
            },
            getProductos() {
                axios.get("/api/products")
                    .then((response) => {
                        this.arrayProductos = response.data;

                        let productosEnStorage = JSON.parse(localStorage.getItem('productosEnElCarrito')) // se usa el parse ya que sino no es un objeto, por lo tanto no podes aplicarle funciones de prden superior
                        if (productosEnStorage) {
                            this.arrayCarritoDeCompras = JSON.parse(localStorage.getItem('productosEnElCarrito'))
                        }//cargamos el localStorage en el carrito para tener los mismos valores en las paginas
                    })
            },
            dateTurn(creationDate) {
                creationDate = new Date(creationDate).toLocaleDateString();
                return creationDate;
            },
            hourTurn(time) {
                time = new Date(time).toLocaleTimeString('es-AR')
                return time
            },

            deletePrepaid(){
                swal({
                    title: "Estas seguro/a de dar de baja este plan?",
                    text: "Lo puedes volver a pedir si lo deseas",
                    icon: "warning",
                    buttons: true,
                    dangerMode: true,
                  })
                    .then((willDelete) => {
                        if (willDelete) {
                            swal("Se ha dado de baja correctamente!", {icon: "success",});
    
                                axios.delete("http://localhost:8080/api/clients/prepaid?idPrepaid=" + this.prepaid[0].associateNumber)
                                .then(res => {
                                    this.getClients()
                                })
                                .catch(function (error) {
                                    if (error.response) {
                                        console.log(error.response.data);
                                        swal({
                                            title: "error",
                                            text: error.response.data, 
                                            icon: "error",
                                        })
                                    }}
                                );
                        } else {
                            swal("No has dado de baja", {icon: "error",});
                        }
                    });
            },

            modificarCliente(){
                axios.patch("/api/client",{

                    name: this.modifyClient.name,
                    lastName: this.modifyClient.lastName,
                    address: this.modifyClient.address,
                    city: this.modifyClient.city,
                    state: this.modifyClient.state,
                    email: this.modifyClient.email,
                    password: this.modifyClient.password,
                    postalCode: this.modifyClient.postalCode
                
                },{headers:{'content-type':'application/x-www-form-urlencoded'}})
                .then(res =>
                    console.log("se modifico"))
                .catch(function (error) {
                    if (error.response) {
                        console.log(error.response.data);
                        swal({
                            title: "error",
                            text: error.response.data, 
                            icon: "error",
                        })
                    }}
                ); 
            },
        getTurnos(){
            axios.get("http://localhost:8080/api/shifts")
            .then((response)=>{
                console.log(response)
                console.log(response.data);
            })
            .catch(error => {
                console.log("se rompio algo, no trajo los turnos")
            })
        },
        getVeterinarios(){
            axios.get("http://localhost:8080/api/veterinaries")
            .then((response)=>{
                console.log(response)
                console.log(response.data);
                this.veterinariosDeShifts = response.data
                this.veterinarioHorariosDeShifts = this.veterinariosDeShifts.horarios
                console.log(this.veterinarioHorariosDeShifts);

                this.veterinarioClinicoDeShifts = this.veterinariosDeShifts.filter(vete => vete.veterinaryCategory == "CLINICO")
                console.log(this.veterinarioClinicoDeShifts);

                this.veterinarioOftalmologoDeShifts = this.veterinariosDeShifts.filter(vete => vete.veterinaryCategory == "OFTALMOLOGO")
                console.log(this.veterinarioOftalmologoDeShifts);

                this.veterinarioCirujanoDeShifts = this.veterinariosDeShifts.filter(vete => vete.veterinaryCategory == "CIRUJANO")
                console.log(this.veterinarioCirujanoDeShifts);

                this.veterinarioCardiologoDeShifts = this.veterinariosDeShifts.filter(vete => vete.veterinaryCategory == "CARDIOLOGO")
                console.log(this.veterinarioCardiologoDeShifts);

            })
            .catch(error => {
                console.log("se rompio algo, no trajo los veterinarios")
            })
        },
        peticionTurnoCliente(){
            axios.post("/api/clients/shifts", `idVet=${this.idVeterinarioClienteDeShifts}&date=${this.fechaClienteDeShifts + " " + this.tiempoClienteDeShifts+":00"}`,{headers:{'content-type':'application/x-www-form-urlencoded'}})
            .then(response =>{
                console.log("hhhhCliente")
                console.log(this.getTurnos())
                console.log(this.getVeterinarios());
                window.location.reload()

            })
            .catch(error =>console.log("jjjj"))
        },
        peticionTurnoMascota(){
            axios.post("/api/pets/shifts", `idVet=${this.idVeterinarioDeShifts}&idPet=${this.idMascotaDeShifts}&date=${this.fechaDeShifts+ " " + this.tiempoDeShifts+":00"}`,{headers:{'content-type':'application/x-www-form-urlencoded'}})/* ver como obtener el id de la mascota *////////////////////////////////////
            .then(response =>{
                console.log("hhhhMascota")
                console.log(this.getTurnos())
                console.log(this.getVeterinarios());
                window.location.reload()
            })
            .catch(error =>console.log("jjjjMascota"))
        },



        },
        computed:{

            FfffTipoTurno(){
                console.log(this.tipoTurnoDeShifts);
            },
    
            fffff(){
                console.log(this.tiempoDeShifts);
                console.log(this.fechaDeShifts);
                console.log(this.fechaDeShifts + "T" + this.tiempoDeShifts+":00");
            },
            FfffEspecialidad(){
                console.log(this.especialidadElegidaDeShifts);
                console.log(this.veterinarioCirujanoDeShifts);
            },
            FfffMedicoElegido(){
                console.log(this.medicoElegidoDeShifts);
                this.nombrePersonaDeShifts = this.veterinariosDeShifts.filter(vete => vete.name == this.medicoElegidoDeShifts)
                console.log(this.nombrePersonaDeShifts);
    
                console.log(this.veterinariosDeShifts);
                this.turnosDeShifts = this.nombrePersonaDeShifts.map(vete => vete.schedules)
                this.turnosDeShifts = this.turnosDeShifts[0]
                console.log(this.turnosDeShifts);
    
                this.idVeterinarioDeShifts = this.nombrePersonaDeShifts.map(vete => vete.id)
                console.log(this.idVeterinarioDeShifts);
                console.log(this.idMascotaDeShifts);
    
            },
    
            fffffCliente(){
                console.log(this.tiempoClienteDeShifts);
                console.log(this.fechaClienteDeShifts);
                console.log(this.fechaClienteDeShifts + "T" + this.tiempoClienteDeShifts+":00");
            },
            FfffEspecialidadCliente(){
                console.log(this.motivoClienteDeShifts);
            },
            FfffMedicoElegidoCliente(){
                console.log(this.medicoClienteDeShifts);
                this.infoMedicoClienteDeShifts = this.veterinariosDeShifts.filter(vete => vete.name == this.medicoClienteDeShifts)
                console.log(this.infoMedicoClienteDeShifts);
                
                console.log(this.veterinariosDeShifts);
                this.turnosClienteDeShifts = this.infoMedicoClienteDeShifts.map(vete => vete.schedules)
                this.turnosClienteDeShifts = this.turnosClienteDeShifts[0]
                console.log(this.turnosClienteDeShifts);
    
                this.idVeterinarioClienteDeShifts = this.infoMedicoClienteDeShifts.map(vete => vete.id)
                console.log(this.idVeterinarioClienteDeShifts);
            },
        },
    }).mount('#app')