package amintabite.Capstone_backend.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

//https://tekkendocs.com/api/t8/Anna/framedata
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

    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;




}
