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
@ToString
@NoArgsConstructor
public class FavoriteMove {
    @GeneratedValue
    @Id
    UUID id;
    String move_input;
    String character_name;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;




}
