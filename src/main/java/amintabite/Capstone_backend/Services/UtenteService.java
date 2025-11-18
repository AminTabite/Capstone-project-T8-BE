package amintabite.Capstone_backend.Services;

import amintabite.Capstone_backend.Entities.FavoriteMove;
import amintabite.Capstone_backend.Entities.Utente;
import amintabite.Capstone_backend.Enums.Roles;
import amintabite.Capstone_backend.Exceptions.NotFoundException;
import amintabite.Capstone_backend.Payloads.UtentePayload;
import amintabite.Capstone_backend.Repositories.FavMoveRepository;
import amintabite.Capstone_backend.Repositories.UtenteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Slf4j
@Service
public class UtenteService {

    @Autowired
    UtenteRepository utenteRepository;

    @Autowired
    FavMoveRepository favMoveRepository;

    @Autowired
    PasswordEncoder bcrypt;

    //metodo per recuperare tutti gli utenti paginati
    public Page<Utente> getAllUsers( int pageNumber, int pageSize, String sortBy){

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));

        return utenteRepository.findAll(pageable);

    }

    //salvataggio utente

    public Utente saveNewUtente(UtentePayload payload) {
        // Se il ruolo non è specificato, di default è USER
        Roles assignRole = (payload.role() != null) ? payload.role() : Roles.USER;

        Utente newUtente = new Utente(
                payload.username(),
                payload.email(),
                bcrypt.encode(payload.password()),
                assignRole
        );

        Utente savedUtente = utenteRepository.save(newUtente);
        log.info("Utente {} salvato correttamente con ruolo {}", savedUtente.getUsername(), savedUtente.getRole());

        return savedUtente;
    }

   //findById

    public Utente findById(UUID utenteId){

        return utenteRepository.findById(utenteId)
                .orElseThrow(()-> new NotFoundException("utente con id" + utenteId + " non trovato " ));

    }


    //findByIdAndUpdate

    public Utente findByIdAndUpdate(UUID utenteId, UtentePayload payload){

        Utente found = utenteRepository.findById(utenteId)
                .orElseThrow(() -> new NotFoundException("utente con id " + utenteId + " non trovato"));

                found.setUsername(payload.username());
                found.setEmail(payload.email());
                found.setRole(payload.role());
        found.setPassword(bcrypt.encode(payload.password()));

        return utenteRepository.save(found);

    }


    //update/me

    public Utente updateMyProfile(Utente currentUser, UtentePayload payload) {

        currentUser.setUsername(payload.username());
        currentUser.setEmail(payload.email());

        if (payload.password() != null && !payload.password().isBlank()) {
            currentUser.setPassword(bcrypt.encode(payload.password()));
        }
        Utente updated = utenteRepository.save(currentUser);
        log.info("Profilo aggiornato per utente: " + updated.getUsername());
        return updated;
    }





    //delete

    public void findByIdAndDelete(UUID utenteId){

        Utente found = utenteRepository.findById(utenteId)
                .orElseThrow(() -> new NotFoundException("utente con id " + utenteId + " non trovato"));


        favMoveRepository.deleteAll(found.getFavoriteMoveList());

        utenteRepository.delete(found);
        log.info("utente con ID " + utenteId + " eliminato correttamente.");


    }


    //trova tramite mail, serve per autenticazione

    public  Utente findByEmail(String email){

        return  this.utenteRepository.findByEmail(email).orElseThrow( ()-> new NotFoundException("utente con l' email " + email + " non e' stato trovato"));

    }





}
