package amintabite.Capstone_backend.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;
@Table(
        uniqueConstraints = @UniqueConstraint(
                columnNames = { "utente_id", "move_input", "character_name", "damage", "startup", "on_block", "on_hit", "hit_level", "recovery" }
        )
)

@Entity
@Setter
@Getter
@ToString(exclude = "utente")
@NoArgsConstructor
public class FavoriteMove {
    @GeneratedValue
    @Id
    UUID id;


    @Column(name = "move_input")
    String moveInput;


    @Column(name = "character_name")
    String characterName;

@Column(name = "damage")

String damage;


    @Column(name = "start_up")

    String startup;


    @Column(name = "on_block")

    String onBlock;


    @Column(name = "on_hit")

    String onHit;


    @Column(name = "hit_level")

    String hitLevel;



    @Column(name = "recovery")

String recovery;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;




}
