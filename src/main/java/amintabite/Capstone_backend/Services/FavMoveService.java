package amintabite.Capstone_backend.Services;

import amintabite.Capstone_backend.Entities.FavoriteMove;
import amintabite.Capstone_backend.Entities.Utente;
import amintabite.Capstone_backend.Exceptions.BadRequestException;
import amintabite.Capstone_backend.Exceptions.NotFoundException;
import amintabite.Capstone_backend.Exceptions.UnauthorizedException;
import amintabite.Capstone_backend.Payloads.FavMovePayload;
import amintabite.Capstone_backend.Repositories.FavMoveRepository;
import amintabite.Capstone_backend.Repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FavMoveService {

    @Autowired
    FavMoveRepository favMoveRepository;

    @Autowired
    UtenteRepository utenteRepository;

    public FavoriteMove addFavorite(Utente utente, FavMovePayload payload){

        boolean exists = favMoveRepository.existsByUtente_IdAndMoveInputAndCharacterName(
                utente.getId(), payload.moveInput(), payload.characterName());

        if (exists) {
            throw new BadRequestException(
                    "La mossa " + payload.moveInput() +
                            " del personaggio " + payload.characterName() +
                            " è già nei preferiti!"
            );
        }

        FavoriteMove move = new FavoriteMove();
        move.setMoveInput(payload.moveInput());
        move.setCharacterName(payload.characterName());
        move.setUtente(utente);

        return favMoveRepository.save(move);
    }


    public List<FavoriteMove> getFavorites(Utente utente) {
        return utente.getFavoriteMoveList();
    }



//    public void removeFavorite(UUID moveId){
//
//        FavoriteMove move= favMoveRepository.findById(moveId)
//                .orElseThrow(()-> new NotFoundException("Mossa non trovata"));
//
//        favMoveRepository.delete(move);
//
//    }



    public void removeFavorite(UUID moveId, Utente utente){
        FavoriteMove move = favMoveRepository.findById(moveId)
                .orElseThrow(() -> new NotFoundException("Mossa non trovata"));

        if (!move.getUtente().getId().equals(utente.getId())) {
            throw new UnauthorizedException("Non puoi eliminare le mosse di un altro utente");
        }

        favMoveRepository.delete(move);
    }



}
