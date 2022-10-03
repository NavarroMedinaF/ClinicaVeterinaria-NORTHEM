
const app = Vue.createApp({
    data(){
        return{
            email: "",
            emailRegister:"",
            password:"",
            passwordRegister:"",
            personalData:{
                dni:0,
                name:"",
                lastName:"",
                city:"",
                state:"",
                address:"",
                postalCode:0,
                dateOfBirth:""
            }
        }
    },
    created(){
    },
    methods:{
        login(){
            axios.post("/api/login", "email=" + this.email + "&password=" + this.password, 
            { headers: { 'content-type': 'application/x-www-form-urlencoded' } })
            .then(response => {
                console.log("Te logiaste");

                if (this.email == "admin@admin") {
                  window.location.href = '/administrador.html'
                }
                if (this.email == "pedro@gmail.com") {
                  window.location.href = '/dashboard.html'
                }
                // else {
                //   window.location.href = '/veterinario.html'
                // }

              }).catch(response => {
                const swalWithBootstrapButtons = Swal.mixin({
                  customClass: {
                    cancelButton: 'btn btn-danger'
                  },
                  buttonsStyling: false
                })

                swalWithBootstrapButtons.fire({
                  title: "Hemos detectado un error",
                  text: "Email y/o contraseña incorrecto",
                  icon: "error",
                  showConfirmButton: false,
                  showCancelButton: true,
                  cancelButtonText: 'Aceptar',
                })
              })
        },
        logout(){
            axios.post('/api/logout').then(res => alert(res));
        },
        signup(){
          axios.post('/api/clients',{
             "dni": this.personalData.dni, 
             "name": this.personalData.name,
             "lastName": this.personalData.lastName,
             "city": this.personalData.city,
             "state": this.personalData.state,
             "address": this.personalData.address,
             "email": this.personalData.emailRegister,
             "password": this.personalData.passwordRegister,
             "postalCode": this.personalData.postalCode,
             "dateOfBirth": this.personalData.dateOfBirth
          }, {headers:{'content-type': 'application/json'}})
          .then(res =>{
              this.email = this.personalData.emailRegister
              this.password =  this.personalData.passwordRegister
              this.login()
              console.log("hola");
          })
          .catch(error => alert(error))
      },
  }
}).mount("#app")
