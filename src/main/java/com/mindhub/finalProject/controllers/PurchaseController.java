package com.mindhub.finalProject.controllers;

import com.mindhub.finalProject.DTO.PurchaseDTO;
import com.mindhub.finalProject.DTO.PurchaseDetailDTO;
import com.mindhub.finalProject.Services.ProductService;
import com.mindhub.finalProject.Services.PurchaseService;
import com.mindhub.finalProject.Services.UserService;
import com.mindhub.finalProject.Utils.APIUtils;
import com.mindhub.finalProject.models.Client;
import com.mindhub.finalProject.models.Product;
import com.mindhub.finalProject.models.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PurchaseController {

    @Autowired
    PurchaseService purchaseService;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @GetMapping("/purchases")
    public Set<PurchaseDTO> getAllPurchases() {
        return purchaseService.findAllPurchases().stream().map(PurchaseDTO::new).collect(Collectors.toSet());
    }
    @GetMapping("/purchases/{id}")
    public PurchaseDTO getPurchaseById(@PathVariable Long id){
        return new PurchaseDTO(purchaseService.findPurchaseById(id));
    }

    @Transactional
    @PostMapping("/clients/purchases")
    public ResponseEntity<Object> createPurchase(@RequestParam String url, @RequestBody PurchaseDetailDTO purchaseDetailDTO,
                                                 Authentication authentication){

        Client authClient = userService.findClientByEmail(authentication.getName());

        if (authClient==null){
            return new ResponseEntity<>("Usted no puede realizar esta operacion, necesita iniciar secion", HttpStatus.FORBIDDEN);
        }
        if (purchaseDetailDTO.getAmount() < 0){
            return new ResponseEntity<>("Error en el precio, no pueden ser valores negativos", HttpStatus.FORBIDDEN);
        }
        if (purchaseDetailDTO.getProducts().size() < 1){
            return new ResponseEntity<>("Usted no tiene ningun producto en esta compra", HttpStatus.FORBIDDEN);
        }

        List<Product> products = purchaseDetailDTO.getProducts();
        products.forEach(product -> {
            Product product1 =  productService.findProductById(product.getId());
            product1.setStock(product1.getStock()-product.getQuantity());
            productService.saveProduct(product1);
        });

        getConnection(url);

        int statusCode = getConnection(url).getStatusCodeValue();

        if (statusCode != 200){
            return  new ResponseEntity<>("Ha habido un error: " + statusCode, HttpStatus.BAD_REQUEST);
        }

        Purchase purchase = new Purchase(authClient, purchaseDetailDTO.getAmount(), LocalDateTime.now().withNano(0));
        purchaseService.savePurchase(purchase);

        return new ResponseEntity<>("La compra fue realizada con exito.", HttpStatus.CREATED);
    }

    @GetMapping("/data")
    public ResponseEntity<Object> getConnection(@RequestParam String url){

        APIUtils.apiConnection(url);
        Object statusCode = APIUtils.apiConnection(url).getBody();

        return new ResponseEntity<>(statusCode, HttpStatus.OK);
    }
}
