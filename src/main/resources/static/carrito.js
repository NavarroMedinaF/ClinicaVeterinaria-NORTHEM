const app = Vue.
    createApp({
        data() {
            return {
                arrayProductos: [],
                arrayProductosRespaldo: [],
                productoInfoModal: [],
                arrayCarritoDeCompras: [],
                idProductosSolicitados: [],
                precioTotalCompra: 0,
                clients: [],
                total: 0,
                cardNumber: "",
                url: "",
                producto:{
                    id: 0,
                    name:"",
                    price:0,
                    quantity:0,
                    stock:0,
                    productCategory:""
                },
            }
        },
        created() {
            this.getProductos();
            this.getClients();
            window.setTimeout(() => {
                this.getTotal()
            }, 1000)
        },
        methods: {
            getClients() {
                axios.get("api/current/clients")
                    .then((resp) => {
                        this.clients = resp.data;
                    })
            },
            getProductos() {
                axios.get("/api/products")
                    .then((response) => {
                        this.arrayProductos = response.data;


                        this.modalProducto()


                        let productosEnStorage = JSON.parse(localStorage.getItem('productosEnElCarrito')) // se usa el parse ya que sino no es un objeto, por lo tanto no podes aplicarle funciones de prden superior
                        if (productosEnStorage) {
                            this.arrayCarritoDeCompras = JSON.parse(localStorage.getItem('productosEnElCarrito'))
                        }//cargamos el localStorage en el carrito para tener los mismos valores en las paginas
                    })
            },
            dinero(monto) {
                newMonto = new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD', maximumFractionDigits: 0 }).format(monto);
                return newMonto;
            },
            modalProducto(id) {
                this.productoInfoModal = this.arrayCarritoDeCompras.filter(producto => producto.id == id)
            },
            agregarAlCarrito(producto) {
                this.idProductosSolicitados = this.arrayCarritoDeCompras.map(product => product.id)
                if (!this.idProductosSolicitados.includes(producto.id)) {
                    this.arrayCarritoDeCompras.push(producto)
                    producto.quantity = 1
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
            },
            quitarUnidadProducto(producto) {
                this.idProductosSolicitados = this.arrayCarritoDeCompras.map(producto => producto.id)
                if (producto.quantity > 0) {
                    let productoModificado = this.arrayCarritoDeCompras.filter(pro => pro.id == producto.id)[0]
                    productoModificado.quantity--
                    this.arrayCarritoDeCompras.forEach(pro => {//actualiza el contador en el carrito
                        if (productoModificado.id == pro.id) {
                            pro = productoModificado;
                            producto.quantity = productoModificado.quantity
                        }
                    });
                    if (producto.quantity == 0) {
                        this.quitarDelCarrito(producto)
                    }
                    localStorage.setItem('productosEnElCarrito', JSON.stringify(this.arrayCarritoDeCompras))
                }
            },
            quitarDelCarrito(producto) {
                this.idProductosSolicitados = this.arrayCarritoDeCompras.map(producto => producto.id)
                if (this.idProductosSolicitados.includes(producto.id)) {
                    this.arrayCarritoDeCompras = this.arrayCarritoDeCompras.filter(pro => pro.id != producto.id)
                    producto.quantity = 0
                    localStorage.setItem('productosEnElCarrito', JSON.stringify(this.arrayCarritoDeCompras))
                }
            },
            comprarCancelar() {
                localStorage.removeItem('productosEnElCarrito')
                this.arrayCarritoDeCompras = []
            },
            getTotal() {
                this.arrayCarritoDeCompras.forEach(pro => {
                    this.total += pro.price * pro.quantity
                })
            },
            comprar() {
                this.url = "http://localhost:8080/api/clients/purchases?url=http://localhost:8090/api/pay?amount=" + this.total + "%26cardNumber=" + this.cardNumber
                axios.post(this.url, {
                    "amount": this.total,
                    "products": this.arrayCarritoDeCompras
                }).then(res => console.log(res)).catch(error => console.log(error))
            }
        },
        computed: {
            sumaTotalCompra() {
                this.precioTotalCompra = 0
                this.precioProductoQuantity = 0
                this.arrayCarritoDeCompras.forEach(producto => {
                    this.precioTotalCompra += producto.price * producto.quantity
                })
            },//suma el precio total de cada producto en el carrito
        },
    }).mount('#app')


let button = document.getElementById("print")
let element = document.getElementById("container-print")

button.addEventListener("click", () => {
    html2pdf().from(element).save();
    element.classList.toggle("no-show")

})