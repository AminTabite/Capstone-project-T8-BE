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

        boolean exists = favMoveRepository.existsByUtente_IdAndMoveInputAndCharacterNameAndDamageAndStartupAndOnBlockAndOnHitAndHitLevelAndRecovery(  utente.getId(),
                payload.moveInput(),
                payload.characterName(),
                payload.damage(),
                payload.startup(),
                payload.onBlock(),
                payload.onHit(),
                payload.hitLevel(),
                payload.recovery()
        );

        if (exists == true) {
            throw new BadRequestException(
                    "La mossa " + payload.moveInput() +
                            " del personaggio " + payload.characterName() +
                            " è già nei preferiti!"
            );
        }

        FavoriteMove move = new FavoriteMove();
        move.setMoveInput(payload.moveInput());
        move.setCharacterName(payload.characterName());
        move.setDamage(payload.damage());
        move.setHitLevel(payload.hitLevel());
        move.setOnHit(payload.onHit());
        move.setOnBlock(payload.onBlock());
        move.setStartup(payload.startup());
        move.setRecovery(payload.recovery());
        move.setUtente(utente);

        return favMoveRepository.save(move);
    }


    public List<FavoriteMove> getFavorites(Utente utente) {

            return favMoveRepository.findAllByUtente_Id(utente.getId());
        }



    /**
     * Rimuove una mossa specifica dell'utente.
     * Lancia UnauthorizedException se l'utente non possiede la mossa.
     */
    public void removeFavorite(UUID moveId, Utente utente) {
        FavoriteMove move = favMoveRepository.findById(moveId)
                .orElseThrow(() -> new NotFoundException("Mossa non trovata"));

        if (!move.getUtente().getId().equals(utente.getId())) {
            throw new UnauthorizedException("Non puoi eliminare le mosse di un altro utente");
        }

        favMoveRepository.delete(move);
    }


    }

