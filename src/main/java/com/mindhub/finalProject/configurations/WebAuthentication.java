package com.mindhub.finalProject.configurations;

import com.mindhub.finalProject.models.Authority;
import com.mindhub.finalProject.models.Usuario;
import com.mindhub.finalProject.Services.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebAuthentication extends GlobalAuthenticationConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(email -> {
            Usuario usuario = usuarioRepository.findUserByEmail(email);

            if (usuario != null) {
                if(usuario.getAuthority() == Authority.ADMIN){

                    return new User(usuario.getEmail(),
                            usuario.getPassword(), AuthorityUtils.createAuthorityList("ADMIN"));

                } else if (usuario.getAuthority() == Authority.VETERINARY) {

                    return new User(usuario.getEmail(),
                            usuario.getPassword(), AuthorityUtils.createAuthorityList("VETERINARY"));

                } else {

                    return new User(usuario.getEmail(),
                            usuario.getPassword(), AuthorityUtils.createAuthorityList("CLIENT"));

                }
            } else {
                throw new UsernameNotFoundException("Este email: " + email + " no está registrado.");
            }
        });
    }
}
