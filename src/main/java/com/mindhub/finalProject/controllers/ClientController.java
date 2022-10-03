package com.mindhub.finalProject.controllers;

import com.mindhub.finalProject.DTO.ClientDTO;
import com.mindhub.finalProject.DTO.ModifyClientDTO;
import com.mindhub.finalProject.DTO.PersonalDataDTO;
import com.mindhub.finalProject.Services.*;
import com.mindhub.finalProject.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ShiftService shiftService;

    @Autowired
    private CardService cardService;

    @Autowired
    private PetService petService;

    @Autowired
    private PrepaidService prepaidService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/clients")
    public Set<ClientDTO> getAllClients() {
        return userService.findAllClients().stream().map(ClientDTO::new).collect(Collectors.toSet());
    }

    @GetMapping("/clients/{id}")
    public ClientDTO getClientById(@PathVariable Long id) {
        return new ClientDTO(userService.findClientById(id));
    }

    @GetMapping("/current/clients")
    public ClientDTO getAuthentication(Authentication authentication){
        return new ClientDTO(userService.findClientByEmail(authentication.getName()));
    };

    @GetMapping("/clients/login")
    public ResponseEntity<Object> checkNewDNIClient(@RequestParam long dni){

        Client client1=userService.findClientById(dni);

        if(client1!=null){
            return new ResponseEntity<>("El DNI que intenta registrar ya existe en nuestra base de datos", HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>("El DNI es valido", HttpStatus.CREATED);
    }
    @PostMapping("/clients")
    public ResponseEntity<Object> createNewClient(@RequestBody PersonalDataDTO personalDataDTO) {
        Client clientExist= userService.findClientById(personalDataDTO.getDni());
        if( clientExist != null){
            return new ResponseEntity<>("El cliente con el DNI "+ personalDataDTO.getDni() + " ya existe, compruebe sus datos",HttpStatus.FORBIDDEN );
        }
        if (!personalDataDTO.getEmail().contains("@") || personalDataDTO.getEmail().equalsIgnoreCase("admin@admin")) {
            return new ResponseEntity<>("El email esta incompleto o es incorrecto", HttpStatus.BAD_REQUEST);
        }
        if (personalDataDTO.getName().length() < 4 || personalDataDTO.getLastName().length() < 3) {
            return new ResponseEntity<>("El nombre y/o el apellido no cumplen con los caracteres minimos", HttpStatus.BAD_REQUEST);
        }
        if (!userService.validator(personalDataDTO.getPassword())) {
            return new ResponseEntity<>("Contraseña invalida", HttpStatus.BAD_REQUEST);
        }
        if (personalDataDTO.getAddress().isEmpty() || personalDataDTO.getCity().isEmpty() || personalDataDTO.getState().isEmpty()) {
            return new ResponseEntity<>("Llene correctamente los campos q indican su direccion.", HttpStatus.BAD_REQUEST);
        }
        if (personalDataDTO.getDateOfBirth().compareTo(LocalDate.now()) > 18) {
            return new ResponseEntity<>("Debe ser mayor de 18 años para poder crear una cuenta.", HttpStatus.FORBIDDEN);
        }
        if (personalDataDTO.getPostalCode() < 1000) {
            return new ResponseEntity<>("Ingrese un codigo postal valido.", HttpStatus.BAD_REQUEST);
        }
        if (personalDataDTO.getDni() < 1000000) {
            return new ResponseEntity<>("DNI incorrecto.", HttpStatus.FORBIDDEN);
        }

        Client newClient = new Client(personalDataDTO.getDni(), personalDataDTO.getEmail(),
                passwordEncoder.encode(personalDataDTO.getPassword()), Authority.CLIENT,
                personalDataDTO.getName(), personalDataDTO.getLastName(), personalDataDTO.getAddress(), personalDataDTO.getCity(),
                personalDataDTO.getState(), personalDataDTO.getDateOfBirth(), personalDataDTO.getPostalCode());
        userService.saveClient(newClient);

        return new ResponseEntity<>("Bienvenido/a a Clinica Northem", HttpStatus.CREATED);
    }

    @PatchMapping("/client")
    public ResponseEntity<Object> modifyClient(@RequestBody ModifyClientDTO modifyClientDTO, Authentication authentication) {

        Client modifyClient = userService.findClientByEmail(authentication.getName());

        if (!userService.validator(modifyClientDTO.getPassword())) {
            return new ResponseEntity<>("Contraseña invalida", HttpStatus.BAD_REQUEST);
        }
        if (!modifyClientDTO.getEmail().contains("@") || modifyClientDTO.getEmail().equalsIgnoreCase("admin@admin")) {
            return new ResponseEntity<>("El email esta incompleto o es incorrecto", HttpStatus.BAD_REQUEST);
        }
        if (modifyClientDTO.getName().length() < 4 || modifyClientDTO.getLastName().length() < 3) {
            return new ResponseEntity<>("El nombre y/o el apellido no cumplen con los caracteres minimos", HttpStatus.BAD_REQUEST);
        }

        if (modifyClientDTO.getAddress().isEmpty() || modifyClientDTO.getCity().isEmpty() || modifyClientDTO.getState().isEmpty()) {
            return new ResponseEntity<>("Debe rellenar los datos de su ubicación.", HttpStatus.BAD_REQUEST);
        }
        if (modifyClientDTO.getPostalCode() < 1000) {
            return new ResponseEntity<>("Ingrese un codigo posta valido", HttpStatus.BAD_REQUEST);
        }
        modifyClient.setName(modifyClientDTO.getName());
        modifyClient.setLastName(modifyClientDTO.getLastName());
        modifyClient.setAddress(modifyClientDTO.getAddress());
        modifyClient.setCity(modifyClientDTO.getCity());
        modifyClient.setState(modifyClientDTO.getState());
        modifyClient.setEmail(modifyClientDTO.getEmail());
        modifyClient.setPassword(modifyClientDTO.getPassword());
        modifyClient.setPostalCode(modifyClientDTO.getPostalCode());
        userService.saveClient(modifyClient);

        return new ResponseEntity<>("Los cambios han sido exitosos", HttpStatus.CREATED);
    }

    @DeleteMapping("/client/{id}")
    @Transactional
    public ResponseEntity<Object> deleteClient(@PathVariable long id, Authentication authentication){
        Admin admin = userService.findAdminByEmail(authentication.getName());
        Client deleteClient = userService.findClientById(id);

        if (admin ==null){
            return new ResponseEntity<>("Usted no es admin y no puede eliminar el cliente",HttpStatus.FORBIDDEN);
        }
        if (deleteClient==null){
            return new ResponseEntity<>("No se ah encontrado el cliente q quieres borrar",HttpStatus.FORBIDDEN);
        }

        Set<Prepaid> prepaids =deleteClient.getPrepaid();
        prepaids.forEach(prepaid ->categoryService.deleteCategory(prepaid.getCategory()));
        deleteClient.getPrepaid().forEach(prepaid -> prepaidService.deletePrepaid(prepaid));

        deleteClient.getShifts().forEach(shift -> shiftService.deleteShift(shift));

        deleteClient.getCards().forEach(card -> cardService.deleteCard(card));

        deleteClient.getPets().forEach(pet -> petService.deletePet(pet));

        deleteClient.getPurchases().forEach(purchase -> purchaseService.deletePurchase(purchase));
        userService.deleteClient(deleteClient);

        return new ResponseEntity<>("El cliente a sido eliminado",HttpStatus.CREATED);
    }
}
