package es.gtorres.backend.security.service;

import es.gtorres.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Implementación de UserDetailsService utilizada para cargar los detalles del usuario desde el repositorio.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Carga los detalles del usuario basados en el nombre de usuario proporcionado.
     * @param username Nombre de usuario para buscar en el repositorio.
     * @return Detalles del usuario encontrado.
     * @throws UsernameNotFoundException Si el usuario no se encuentra en el repositorio.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        es.gtorres.backend.entities.User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),user.getPassword(),new ArrayList<>());
    }
}
