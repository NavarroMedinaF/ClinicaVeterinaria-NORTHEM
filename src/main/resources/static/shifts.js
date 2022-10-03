const app = Vue.
createApp({
    data(){
        return{
            diaFecha:"",
            password:"",
            email:"",
            ya3:"",
        }
    },
    created(){
        console.log("ssssssssss");
        console.log(this.diaFecha);
        console.log(this.ahora());
        //this.getProductos()
                
    },
    methods:{
        /*login(){//email=admin@admin&password=123
            axios.post("http://localhost:8080/api/login", `email=${this.email}&password=${this.password}`,{headers:{'content-type':'application/x-www-form-urlencoded'}})//'Access-Control-Allow-Origin':'*',//,'origin':'http://localhost:8080'
            .then(response =>{
                console.log("me logie");
                this.getProductos()
            })
            .catch(setTimeout(function(){
                swal({
                title: "error",
                text: "Verifique los perametros ingresados o si es nuevo registrese", 
                icon: "error",
                })
            },1500))
        },*/
        login() {
            axios.post("/api/login", "email=" + this.email + "&password=" + this.password, {
                        headers: { 'content-type':'application/x-www-form-urlencoded' },
            }).then(response => {
               console.log("ingresaste");
            })
            .catch(function (error) {

                if (error.response) {

                  console.log(error.response.data);
                  console.log(error.response.status);
                  console.log(error.response.headers);

                } else if (error.request) {

                  console.log(error.request);
                } else {


                  console.log('Error', error.message);
                }
                console.log(error.config);
              });


        },

        getProductos(){
            axios.get("http://localhost:8080/api/shifts")
            .then((response)=>{
                console.log(response)
                console.log(response.data);
                console.log(response.data.response);

            })
            .catch(error => {
                console.log("se rompio algo, no trajo los turnos")
            })
        },



        ahora(){
            ya1 = new Date()
            ya2 = new Date()
            ya1 = ya1.toLocaleDateString()
            //ya2 = ya2.toLocaleTimeString()
            this.ya3 = ya1 //+ ya2
            /* ya3 = new Date()*/
            //ya3.setSeconds(00);
            console.log(this.ya3);
            return this.ya3
        },

        confirmacionTurno(){
            swal({
                title: "Estas seguro/a de reservar este turno?",
                text: "Recorda que el turno no se podra cancelar.",
                icon: "warning",
                buttons: true,
                dangerMode: true,
              })
                .then((willReserv) => {
                    if (willReserv) {
                        swal("La turno se ha reservado correctamente!", {icon: "success",});

                        /*axios.)
                            .then(response =>{
                                gghjgjgjghgsasdadaqerut

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
                            );*/
                    } else {
                        swal("Has cancelado el turno", {icon: "error",});
                    }
                })
        },

    },
    computed:{
        funcionDiaFecha(){
            console.log(this.diaFecha);
            fechaElegida = new Date(this.diaFecha)
            fechaElegida = fechaElegida.toLocaleDateString() //+  fechaElegida.toLocaleTimeString()
            console.log(fechaElegida);
            return fechaElegida;
            
        }
    },
}).mount('#app')


