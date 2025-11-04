package amintabite.Capstone_backend.Services;

import amintabite.Capstone_backend.Entities.FavoriteMove;
import amintabite.Capstone_backend.Entities.Utente;
import amintabite.Capstone_backend.Exceptions.NotFoundException;
import amintabite.Capstone_backend.Payloads.FavMovePayload;
import amintabite.Capstone_backend.Repositories.FavMoveRepository;
import amintabite.Capstone_backend.Repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.UUID;

@Service
public class FavMoveService {

    @Autowired
    FavMoveRepository favMoveRepository;

    @Autowired
    UtenteRepository utenteRepository;

    public FavoriteMove addFavorite(UUID utenteId, FavMovePayload payload){

        Utente utente = utenteRepository.findById(utenteId)
                .orElseThrow(()-> new NotFoundException("utente con id" + utenteId + "non trovato"));

        FavoriteMove moves = new FavoriteMove();
        moves.setMove_input(payload.move_input());
        moves.setCharacter_name(payload.character_name());
        moves.setUtente(utente);

        utente.getFavoriteMoveList().add(moves);

        return favMoveRepository.save(moves);


    }


    public List<FavoriteMove> getFavorites(UUID utenteId) {

        Utente utente = utenteRepository.findById(utenteId)
                .orElseThrow(()-> new NotFoundException("utente con id" + utenteId + "non trovato"));

        return utente.getFavoriteMoveList();
    }


    public void removeFavorite(UUID moveId){

        FavoriteMove move= favMoveRepository.findById(moveId)
                .orElseThrow(()-> new NotFoundException("Mossa non trovata"));

        favMoveRepository.delete(move);

    }


}
