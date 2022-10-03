package com.mindhub.finalProject.Services.Implements;

import com.mindhub.finalProject.Services.UserService;
import com.mindhub.finalProject.models.Admin;
import com.mindhub.finalProject.models.Client;
import com.mindhub.finalProject.models.Usuario;
import com.mindhub.finalProject.models.Veterinary;
import com.mindhub.finalProject.Services.repository.AdminRepository;
import com.mindhub.finalProject.Services.repository.ClientRepository;
import com.mindhub.finalProject.Services.repository.UserRepository;
import com.mindhub.finalProject.Services.repository.VeterinaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    VeterinaryRepository veterinaryRepository;

    @Autowired
    AdminRepository adminRepository;

    /* USUARIO */
    @Override
    public List<Usuario> findAllUsers() {
        return userRepository.findAll();
    }
    @Override
    public Usuario findUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public Usuario findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUser(Usuario usuario){
        userRepository.save(usuario);
    }

    @Override
    public void deleteUser(Usuario usuario){
        userRepository.delete(usuario);
    }

    /*ADMINS*/
    @Override
    public List<Admin> findAllAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public Admin findAdminById(Long id) {
        return adminRepository.findById(id).orElse(null);
    }

    @Override
    public Admin findAdminByEmail(String email) {
        return adminRepository.findAdminByEmail(email);
    }

    @Override
    public void saveAdmin(Admin admin) {
        adminRepository.save(admin);
    }

    @Override
    public void deleteUser(Admin admin) {
        adminRepository.delete(admin);
    }


    /* CLIENT */
    @Override
    public List<Client> findAllClients(){
        return clientRepository.findAll();
    }
    @Override
    public Client findClientById(Long id){
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public Client findClientByEmail(String email){
        return clientRepository.findByEmail(email);
    }

    @Override
    public void saveClient(Client Client){
        userRepository.save(Client);
    }

    @Override
    public void deleteClient(Client Client){
        userRepository.delete(Client);
    }


    /*VETERINARY*/
    @Override
    public List<Veterinary> findAllVeterinaries(){
        return veterinaryRepository.findAll();
    }

    @Override
    public Veterinary findVeterinaryById(Long id){
        return veterinaryRepository.findById(id).orElse(null);
    }

    @Override
    public Veterinary findVeterinaryByNumber(String number){
        return veterinaryRepository.findByPhoneNumber(number);
    }

    @Override
    public Veterinary findVeterinaryByEmail(String email) {
        return veterinaryRepository.findByEmail(email);
    }

    @Override
    public void saveVeterinary(Veterinary veterinary){
        veterinaryRepository.save(veterinary);
    }

    @Override
    public void deleteVeterinary(Veterinary veterinary){
        veterinaryRepository.delete(veterinary);
    }

    /*METOHS*/

    // Contraseña de 8-16 caracteres con al menos un dígito, al menos uno
    // letra minúscula, al menos una letra mayúscula, al menos una
    // caracter especial sin espacios en blanco
    @Override
    public boolean validator(String password) {

        final  String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,16}$";

        final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);

        return PASSWORD_PATTERN.matcher(password).matches();

    }
}
