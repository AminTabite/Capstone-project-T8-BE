package amintabite.Capstone_backend.Controllers;

import amintabite.Capstone_backend.Entities.Utente;
import amintabite.Capstone_backend.Exceptions.ValidationsException;
import amintabite.Capstone_backend.Payloads.UtentePayload;
import amintabite.Capstone_backend.Repositories.UtenteRepository;
import amintabite.Capstone_backend.Services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/utenti")
public class UtenteController {
    @Autowired
    UtenteRepository utenteRepository;

    @Autowired
    UtenteService utenteService;

    @GetMapping //utenti paginati
    public Page<Utente> getAllUtenti(

            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "username") String sort

    ){
        return utenteService.getAllUsers(page, size, sort);
    }


    //ritorna utente singolo
    @GetMapping("/{utenteId}")

    public Utente getUtenteById(@PathVariable UUID utenteId){

        return utenteService.findById(utenteId);

    }

    //creazione utente
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)

    public Utente createNewUtente(@RequestBody @Validated UtentePayload payload, BindingResult validationResult ) {

        if(validationResult.hasErrors()){

            throw new ValidationsException(validationResult.getFieldErrors()
                    .stream().map(fieldError -> fieldError.getDefaultMessage()).toList());

        }  else {
            return utenteService.saveNewUtente(payload);
        }
    }


    //modifica utente
    @PutMapping("/{utenteId}")

    public Utente updateNewUtente(@PathVariable @Validated UUID utenteid, @RequestBody UtentePayload payload, BindingResult validationResult){

        if(validationResult.hasErrors()){

            throw new ValidationsException(validationResult.getFieldErrors()
                    .stream().map(fieldError -> fieldError.getDefaultMessage()).toList());

        }  else {
            return utenteService.findByIdAndUpdate(utenteid, payload);
        }

    }


    //rimozione utente

    @DeleteMapping("/{utenteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUtente(@PathVariable UUID utenteId){

        utenteService.findByIdAndDelete(utenteId);

    }




}
