package amintabite.Capstone_backend.Repositories;

import amintabite.Capstone_backend.Entities.FavoriteMove;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FavMoveRepository extends JpaRepository<FavoriteMove, UUID> {

    boolean existsByUtente_IdAndMoveInputAndCharacterNameAndDamageAndStartupAndOnBlockAndOnHitAndHitLevelAndRecovery(
            UUID utenteId,
            String moveInput,
            String characterName,
            String damage,
            String startup,
            String onBlock,
            String onHit,
            String hitLevel,
            String recovery
    );


    List<FavoriteMove> findAllByUtente_Id(UUID id);
}