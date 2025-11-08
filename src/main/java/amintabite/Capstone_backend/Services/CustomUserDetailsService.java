package amintabite.Capstone_backend.Services;

import amintabite.Capstone_backend.Entities.Utente;
import amintabite.Capstone_backend.Repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UtenteRepository utenteRepository;
    //per JWT filter, carica utenti tramite email
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return utenteRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utente non trovato"));
    }

    // per JWT filter, carica utenti tramite id
    public Utente loadUserById(UUID id) {
        return utenteRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Utente non trovato"));
    }
}

