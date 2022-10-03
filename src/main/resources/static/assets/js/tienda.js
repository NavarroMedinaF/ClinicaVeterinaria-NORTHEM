const app = Vue.
createApp({
    data(){
        return{
            arrayProductos:[],
            arrayProductosRespaldo:[],
            nombreA:[],
            nombrez:[],
            productosDescuento:[],
            precioMayor:[],
            precioMenor:[],
            tipos:[],
            tipoSelec:[],
            rango:32000,
            arrayJuguetes:[],
            arrayJuguetesRespaldo:[],
            arrayMedicamentos:[],
            arrayMedicamentosRespaldo:[],
            arrayAlimentos:[],
            arrayAlimentosRespaldo:[],
            arrayVacunas:[],
            arrayVacunasRespaldo:[],
            buscador:"",
            arrayProductosBuscados:[],
            productoInfoModal:[],
            opcionElegida:"",

            //productoBoton:{},

            arrayCarritoDeCompras: [],
            idProductosSolicitados:[],


        }
    },
    created(){
        this.getProductos()
    },
    methods:{
        getProductos(){
            axios.get("/api/products")
            .then((response)=>{
                this.arrayProductos = response.data;
                this.arrayProductosRespaldo = this.arrayProductos


                //this.eeeee()
                this.filtroJuguete()
                this.filtroMedicamento()
                this.filtroAlimentos()
                this.filtroVacunas()
                this.functGetCategorias()
                


                if (this.tipoSelec == "") {
                    this.functFiltroNombre(this.arrayProductos)
                } else if(this.tipoSelec == "TOY"){
                    this.functFiltroNombreJ(this.arrayJuguetes)
                } else if(this.tipoSelec == "MEDICINE"){
                    this.functFiltroNombreM(this.arrayMedicamentos)
                }else if(this.tipoSelec == "BALANCED_MEAL"){
                    this.functFiltroNombreA(this.arrayAlimentos)
                }else if(this.tipoSelec == "VACCINE"){
                    this.functFiltroNombreV(this.arrayVacunas)
                }else{
                    this.functFiltroNombre(this.arrayProductos)
                }
                
                this.arrayJuguetesRespaldo = this.arrayJuguetes
                this.arrayMedicamentosRespaldo = this.arrayMedicamentos
                this.arrayAlimentosRespaldo = this.arrayAlimentos
                this.arrayVacunasRespaldo = this.arrayVacunas



                this.filtroNombreA()
                this.filtroNombreZ()
                this.filtroDescuento()
                this.filtroPrecioMayor()
                this.filtroPrecioMenor()
                //this.filtroCategoria()
                this.modalProducto()


                let productosEnStorage = JSON.parse(localStorage.getItem('productosEnElCarrito')) // se usa el parse ya que sino no es un objeto, por lo tanto no podes aplicarle funciones de prden superior
                if (productosEnStorage) {
                    this.arrayCarritoDeCompras = JSON.parse(localStorage.getItem('productosEnElCarrito'))
                }//cargamos el localStorage en el carrito para tener los mismos valores en las paginas
               
            })
        },
        filtroPrecioMayor(){
            let arrayPrecios = this.arrayProductos.map(producto => producto.price)
            
            this.precioMayor = arrayPrecios.sort((a, b) => b - a)
     
        },
        filtroPrecioMenor(){
            let arrayPrecios = this.arrayProductos.map(producto => producto.price)
            
            this.precioMenor = arrayPrecios.sort((a, b) => a - b)
            
        },
        filtroNombreA(){
            this.nombreA = this.arrayProductos.map(producto => producto.name)
          
            this.nombreA = this.nombreA.sort(function(a, b) {
                return a.localeCompare(b);
            })
           
        },
        filtroNombreZ(){
            this.nombreZ = this.arrayProductos.map(producto => producto.name)
            this.nombreZ = this.nombreZ.sort(function(a, b) {
                return b.localeCompare(a);
            })
         
        },
        filtroDescuento(){
            this.productosDescuento = this.arrayProductos.filter(producto => producto.price < 3500)//////////////////////
          
        },
        /*filtroCategoria(){
            this.tipos = this.arrayProductos.map(producto => producto.tipo)
            this.tipos = new Set(this.tipos)
            console.log(this.tipos);
        },*/
        modalProducto(id){
         
            this.productoInfoModal = this.arrayProductos.filter(producto=>producto.id == id)
            
        },
        filtroJuguete(){
            this.arrayJuguetes = this.arrayProductos.filter(producto=> producto.productCategory == "TOY")
         
        },
        filtroMedicamento(){
            this.arrayMedicamentos = this.arrayProductos.filter(producto=> producto.productCategory == "MEDICINE")
         
        },
        filtroAlimentos(){
            this.arrayAlimentos = this.arrayProductos.filter(producto=> producto.productCategory == "BALANCED_MEAL")
           
        },
        filtroVacunas(){
            this.arrayVacunas = this.arrayProductos.filter(producto=> producto.productCategory == "VACCINE")
         
        },

        functFiltroNombre(array){
            this.arrayProductosRespaldo = array.filter(producto => {
                return producto.name.toLowerCase().includes(this.buscador.toLowerCase())

            })
           
        },
        functFiltroNombreJ(array){
            this.arrayJuguetesRespaldo = array.filter(producto => {
                return producto.name.toLowerCase().includes(this.buscador.toLowerCase())

            })
         
        },
        functFiltroNombreM(array){
            this.arrayMedicamentosRespaldo = array.filter(producto => {
                return producto.name.toLowerCase().includes(this.buscador.toLowerCase())

            })
           
        },
        functFiltroNombreA(array){
            this.arrayAlimentosRespaldo = array.filter(producto => {
                return producto.name.toLowerCase().includes(this.buscador.toLowerCase())

            })
           
        },
        functFiltroNombreV(array){
            this.arrayVacunasRespaldo = array.filter(producto => {
                return producto.name.toLowerCase().includes(this.buscador.toLowerCase())

            })
            
        },

        functGetCategorias(){
            this.tipos = this.arrayProductosRespaldo.map(producto => producto.productCategory)
            this.tipos = new Set(this.tipos)
           
        }, 

        agregarAlCarrito(producto) {
            this.idProductosSolicitados = this.arrayCarritoDeCompras.map(product => product.id)
            if (!this.idProductosSolicitados.includes(producto.id)) {
                this.arrayCarritoDeCompras.push(producto)
                producto.quantity = 1 //==???
                localStorage.setItem('productosEnElCarrito', JSON.stringify(this.arrayCarritoDeCompras))
            } else if (producto.quantity < producto.stock) {
                let productoModificado = this.arrayCarritoDeCompras.filter(pro => pro.id == producto.id)[0]
                productoModificado.quantity++
                this.arrayCarritoDeCompras.forEach(pro => {
                    if (productoModificado.id == pro.id) {
                        pro = productoModificado;
                        producto.quantity = productoModificado.quantity
                    }
                })
                localStorage.setItem('productosEnElCarrito', JSON.stringify(this.arrayCarritoDeCompras))
            }
            
        },//agrega producto al carro 


        quitarDelCarrito(producto) {
            this.idProductosSolicitados = this.arrayCarritoDeCompras.map(producto => producto.id)
            if (this.idProductosSolicitados.includes(producto.id)) {
                this.arrayCarritoDeCompras = this.arrayCarritoDeCompras.filter(pro => pro.id != producto.id)
                producto.quantity = 0
                localStorage.setItem('productosEnElCarrito', JSON.stringify(this.arrayCarritoDeCompras))
            }
            

        },//quita del carro


        /* actualizarBoton(id){
            let lllllll = this.arrayCarritoDeCompras
            this.productoBoton = lllllll.find(producto => producto._id == id)
            console.log(this.productoBoton);
        }, */


        /*eeeee(){
            for (let producto = 0; producto < this.arrayProductos.length; producto++) {
                //const element = array[index];
                if (this.arrayCarritoDeCompras.includes(producto)) {
                    producto.quantity==0
                }
            }
        },*/

    },
    computed:{
        buscado(){ 
            if (this.tipoSelec == "") {
                this.arrayProductosRespaldo = this.arrayProductos
            }
            if (this.buscador != '') {
                this.functFiltroNombre(this.arrayProductos)
            } 
        },
        buscadoJ(){ 
            if (this.tipoSelec == "TOY") {
                this.arrayJuguetesRespaldo = this.arrayJuguetes
            }
            if (this.buscador != '') {
                this.functFiltroNombreJ(this.arrayJuguetes)
            } 
        },
        buscadoM(){ 
            if (this.tipoSelec == "MEDICINE") {
                this.arrayMedicamentosRespaldo = this.arrayMedicamentos
            }
            if (this.buscador != '') {
                this.functFiltroNombreM(this.arrayMedicamentos)
            } 
        },
        buscadoA(){ 
            if (this.tipoSelec == "BALANCED_MEAL") {
                this.arrayAlimentosRespaldo = this.arrayAlimentos
            }
            if (this.buscador != '') {
                this.functFiltroNombreA(this.arrayAlimentos)
            } 
        },
        buscadoV(){ 
            if (this.tipoSelec == "VACCINE") {
                this.arrayVacunasRespaldo = this.arrayVacunas
            }
            if (this.buscador != '') {
                this.functFiltroNombreV(this.arrayVacunas)
            } 
        },
        buscadoOtro(){ 
            if (this.tipoSelec != "" && this.tipoSelec != "TOY" && this.tipoSelec != "MEDICINE" && this.tipoSelec != "BALANCED_MEAL") {
                this.arrayProductosRespaldo = this.arrayProductos
            }
            if (this.buscador != '') {
                this.functFiltroNombre(this.arrayProductos)
            } 
        },

        seleccionado(){
            if (this.opcionElegida == "mayor") {
                if (this.tipoSelec == "") {
                    this.arrayProductosRespaldo = this.arrayProductos
                    this.arrayProductosRespaldo.sort((a, b) => {
                        return b.price - a.price;
                    });
                }else if(this.tipoSelec == "TOY"){
                    this.arrayJuguetesRespaldo = this.arrayJuguetes
                    this.arrayJuguetesRespaldo.sort((a, b) => {
                        return b.price - a.price;
                    });
                }else if(this.tipoSelec == "MEDICINE"){
                    this.arrayMedicamentosRespaldo = this.arrayMedicamentos
                    this.arrayMedicamentosRespaldo.sort((a, b) => {
                        return b.price - a.price;
                    });
                }else if(this.tipoSelec == "BALANCED_MEAL"){
                    this.arrayAlimentosRespaldo = this.arrayAlimentos
                    this.arrayAlimentosRespaldo.sort((a, b) => {
                        return b.price - a.price;
                    });
                }else if(this.tipoSelec == "VACCINE"){
                    this.arrayVacunasRespaldo = this.arrayVacunas
                    this.arrayVacunasRespaldo.sort((a, b) => {
                        return b.price - a.price;
                    });
                }else{
                    this.arrayProductosRespaldo = this.arrayProductos
                    this.arrayProductosRespaldo.sort((a, b) => {
                        return b.price - a.price;
                    });
                }

            }else if (this.opcionElegida == "menor") {
                if (this.tipoSelec == "") {
                    this.arrayProductosRespaldo = this.arrayProductos
                    this.arrayProductosRespaldo.sort((a, b) => {
                        return a.price - b.price;
                    });
                }else if(this.tipoSelec == "TOY"){
                    this.arrayJuguetesRespaldo = this.arrayJuguetes
                    this.arrayJuguetesRespaldo.sort((a, b) => {
                        return a.price - b.price;
                    });
                }else if(this.tipoSelec == "MEDICINE"){
                    this.arrayMedicamentosRespaldo = this.arrayMedicamentos
                    this.arrayMedicamentosRespaldo.sort((a, b) => {
                        return a.price - b.price;
                    });
                }else if(this.tipoSelec == "BALANCED_MEAL"){
                    this.arrayAlimentosRespaldo = this.arrayAlimentos
                    this.arrayAlimentosRespaldo.sort((a, b) => {
                        return a.price - b.price;
                    });
                }else if(this.tipoSelec == "VACCINE"){
                    this.arrayVacunasRespaldo = this.arrayVacunas
                    this.arrayVacunasRespaldo.sort((a, b) => {
                        return a.price - b.price;
                    });
                }else{
                    this.arrayProductosRespaldo = this.arrayProductos
                    this.arrayProductosRespaldo.sort((a, b) => {
                        return a.price - b.price;
                    });
                }

            }else if (this.opcionElegida == "a-z") {
                if (this.tipoSelec == "") {
                    this.arrayProductosRespaldo = this.arrayProductos
                    this.arrayProductosRespaldo.sort(function(a, b) {
                        return a.name.localeCompare(b.name);
                    })
                }else if(this.tipoSelec == "TOY"){
                    this.arrayJuguetesRespaldo = this.arrayJuguetes
                    this.arrayJuguetesRespaldo.sort(function(a, b) {
                        return a.name.localeCompare(b.name);
                    })
                }else if(this.tipoSelec == "MEDICINE"){
                    this.arrayMedicamentosRespaldo = this.arrayMedicamentos
                    this.arrayMedicamentosRespaldo.sort(function(a, b) {
                        return a.name.localeCompare(b.name);
                    })
                }else if(this.tipoSelec == "BALANCED_MEAL"){
                    this.arrayAlimentosRespaldo = this.arrayAlimentos
                    this.arrayAlimentosRespaldo.sort(function(a, b) {
                        return a.name.localeCompare(b.name);
                    })
                }else if(this.tipoSelec == "VACCINE"){
                    this.arrayVacunasRespaldo = this.arrayVacunas
                    this.arrayVacunasRespaldo.sort(function(a, b) {
                        return a.name.localeCompare(b.name);
                    })
                }else{
                    this.arrayProductosRespaldo = this.arrayProductos
                    this.arrayProductosRespaldo.sort(function(a, b) {
                        return a.name.localeCompare(b.name);
                    })
                }

            }else if (this.opcionElegida == "z-a") {
                if (this.tipoSelec == "") {
                    this.arrayProductosRespaldo = this.arrayProductos
                    this.arrayProductosRespaldo.sort(function(a, b) {
                        return b.name.localeCompare(a.name);
                    })
                }else if(this.tipoSelec == "TOY"){
                    this.arrayJuguetesRespaldo = this.arrayJuguetes
                    this.arrayJuguetesRespaldo.sort(function(a, b) {
                        return b.name.localeCompare(a.name);
                    })
                }else if(this.tipoSelec == "MEDICINE"){
                    this.arrayMedicamentosRespaldo = this.arrayMedicamentos
                    this.arrayMedicamentosRespaldo.sort(function(a, b) {
                        return b.name.localeCompare(a.name);
                    })
                }else if(this.tipoSelec == "BALANCED_MEAL"){
                    this.arrayAlimentosRespaldo = this.arrayAlimentos
                    this.arrayAlimentosRespaldo.sort(function(a, b) {
                        return b.name.localeCompare(a.name);
                    })
                }else if(this.tipoSelec == "VACCINE"){
                    this.arrayVacunasRespaldo = this.arrayVacunas
                    this.arrayVacunasRespaldo.sort(function(a, b) {
                        return b.name.localeCompare(a.name);
                    })
                }else{
                    this.arrayProductosRespaldo = this.arrayProductos
                    this.arrayProductosRespaldo.sort(function(a, b) {
                        return b.name.localeCompare(a.name);
                    })
                }

            }else if (this.opcionElegida == "descuentos") {
                if (this.tipoSelec == "") {
                    this.arrayProductosRespaldo = this.arrayProductos
                    this.arrayProductosRespaldo = this.arrayProductosRespaldo.filter(producto => producto.price < 3500)

                }else if(this.tipoSelec == "TOY"){
                    this.arrayJuguetesRespaldo = this.arrayJuguetes
                    this.arrayJuguetesRespaldo = this.arrayJuguetesRespaldo.filter(producto => producto.price < 3500)

                }else if(this.tipoSelec == "MEDICINE"){
                    this.arrayMedicamentosRespaldo = this.arrayMedicamentos
                    this.arrayMedicamentosRespaldo = this.arrayMedicamentosRespaldo.filter(producto => producto.price < 3500)
                }else if(this.tipoSelec == "BALANCED_MEAL"){
                    this.arrayAlimentosRespaldo = this.arrayAlimentos
                    this.arrayAlimentosRespaldo = this.arrayAlimentosRespaldo.filter(producto => producto.price < 3500)
                }else if(this.tipoSelec == "VACCINE"){
                    this.arrayVacunasRespaldo = this.arrayVacunas
                    this.arrayVacunasRespaldo = this.arrayVacunasRespaldo.filter(producto => producto.price < 3500)
                }else{
                    this.arrayProductosRespaldo = this.arrayProductos
                    this.arrayProductosRespaldo = this.arrayProductosRespaldo.filter(producto => producto.price < 3500)

                }

            }
        },
        dsdsd(){
            for (let producto = 0; producto < this.arrayProductos.length; producto++) {
                //const element = array[index];
                if (this.arrayCarritoDeCompras.includes(producto)) {
                    producto.quantity==1
                }
            }
        },

    },
}).mount('#app')