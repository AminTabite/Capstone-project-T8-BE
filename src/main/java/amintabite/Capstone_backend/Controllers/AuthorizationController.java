package amintabite.Capstone_backend.Controllers;

import amintabite.Capstone_backend.Entities.Utente;
import amintabite.Capstone_backend.Exceptions.UnauthorizedException;
import amintabite.Capstone_backend.Exceptions.ValidationsException;
import amintabite.Capstone_backend.Payloads.LoginPayload;
import amintabite.Capstone_backend.Payloads.TokenPayload;
import amintabite.Capstone_backend.Payloads.UtentePayload;
import amintabite.Capstone_backend.Services.AuthorizationService;
import amintabite.Capstone_backend.Services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173") //per accettare richieste da front-end
public class AuthorizationController {
    @Autowired
    private AuthorizationService authorizationService;
    @Autowired
    private UtenteService utenteService;


    @PostMapping("/login")
    public TokenPayload login(@RequestBody @Validated LoginPayload body, BindingResult validationResult){


        if(body.email() == null || body.password() == null) {
            throw new UnauthorizedException("Email o password mancanti");
        }

        if(validationResult.hasErrors()){


            throw new ValidationsException(validationResult.getFieldErrors()
                    .stream().map(fieldError -> fieldError.getDefaultMessage()).toList());

        }

        return new TokenPayload(authorizationService.CheckCredentialAndDoToken(body));

    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Utente createUtente(@RequestBody @Validated UtentePayload payload, BindingResult validationResult){

        if(validationResult.hasErrors()){


            throw new ValidationsException(validationResult.getFieldErrors()
                    .stream().map(fieldError -> fieldError.getDefaultMessage()).toList());

        }
            return this.utenteService.saveNewUtente(payload);


        }


    }






