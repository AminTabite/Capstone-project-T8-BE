package amintabite.Capstone_backend.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Users")
@NoArgsConstructor
@ToString
@Setter
@Getter
public class Utente {

    @GeneratedValue
    @Id
    UUID id;
    String username;
    String email;
    String password;
    @OneToMany(mappedBy = "utente")
    List<FavoriteMove> favoriteMoveList;




    public Utente(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
