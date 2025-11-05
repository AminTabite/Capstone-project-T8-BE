package amintabite.Capstone_backend.Services;

import amintabite.Capstone_backend.Entities.Utente;
import amintabite.Capstone_backend.Exceptions.UnauthorizedException;
import amintabite.Capstone_backend.Payloads.LoginPayload;
import amintabite.Capstone_backend.Security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthorizationService {

    @Autowired
    private UtenteService utenteService;
    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private PasswordEncoder bcrypt;


    public String CheckCredentialAndDoToken(LoginPayload body) {

        Utente found = this.utenteService.findByEmail(body.email());

        if (bcrypt.matches(body.password(), found.getPassword()))
        {
            return jwtTools.createToken(found);
        } else{
            throw  new UnauthorizedException("credenziali errate");


        }




    }




}
