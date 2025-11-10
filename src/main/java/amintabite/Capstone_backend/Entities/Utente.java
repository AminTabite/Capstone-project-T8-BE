package amintabite.Capstone_backend.Entities;

import amintabite.Capstone_backend.Enums.Roles;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Users")
@NoArgsConstructor
@ToString
@Setter
@Getter
public class Utente implements UserDetails {

    @GeneratedValue
    @Id
    UUID id;
    String username;
    String email;
    String password;
    @Enumerated(EnumType.STRING)
    Roles role = Roles.USER;
    @JsonIgnore
    @OneToMany(mappedBy = "utente")
    List<FavoriteMove> favoriteMoveList;




    public Utente(String username, String email, String password, Roles role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
