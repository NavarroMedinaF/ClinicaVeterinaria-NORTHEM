const app = Vue.
    createApp({
        data() {
            return {
                name: "",
                email: "",
                phone: "",
                message: "",


            }
        },
        created() {

        },
        methods: {

        mensajeDeAviso(event){
            event.preventDefault();
        if (this.name.length== 0 || this.email.length== 0 || this.phone.length== 0 || this.message.length== 0){ 
          Swal.fire({
            title: "Tu mensaje no fue enviado",
            text: "Debes rellenar todos los datos",
            confirmButtonColor: "#262b59",
            icon: "error",
            confirmButtonText: "OK!",
            

          }) 
        } else{
            Swal.fire({
            title: "Tu mensaje fue enviado",
            text: "Gracias por contactarte con nosotros",
            confirmButtonColor: "#262b59",
            confirmButtonText: "OK!",
            icon: "success",
          })
        }
    },
        },
        computed: {
        },
    }).mount('#app')