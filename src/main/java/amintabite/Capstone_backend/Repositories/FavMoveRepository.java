package amintabite.Capstone_backend.Repositories;

import amintabite.Capstone_backend.Entities.FavoriteMove;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FavMoveRepository extends JpaRepository<FavoriteMove, UUID> {

    boolean existsByUtente_IdAndMoveInputAndCharacterName(UUID utenteId,
            String moveInput,
            String characterName
    );
}