package com.mindhub.finalProject.Services;

import com.mindhub.finalProject.models.Admin;
import com.mindhub.finalProject.models.Client;
import com.mindhub.finalProject.models.Usuario;
import com.mindhub.finalProject.models.Veterinary;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public interface UserService {

    /* USUARIO */
    List<Usuario> findAllUsers();

    Usuario findUserById(Long id);

    Usuario findUserByEmail(String email);

    void saveUser(Usuario usuario);

    void deleteUser(Usuario usuario);

    /*ADMIN*/
    List<Admin> findAllAdmins();

    Admin findAdminById(Long id);

    Admin findAdminByEmail(String email);

    void saveAdmin(Admin admin);

    void deleteUser(Admin admin);

    boolean validator(String password);
    /*CLIENT*/

    List<Client> findAllClients();

    Client findClientById(Long id);

    Client findClientByEmail(String email);

    void saveClient(Client Client);

    void deleteClient(Client Client);


    /* VETERINARY */
    List<Veterinary> findAllVeterinaries();

    Veterinary findVeterinaryById(Long id);

    Veterinary findVeterinaryByNumber(String number);

    Veterinary findVeterinaryByEmail(String email);
    void saveVeterinary(Veterinary veterinary);

    void deleteVeterinary(Veterinary veterinary);
}
