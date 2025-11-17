package amintabite.Capstone_backend.Controllers;//package amintabite.Capstone_backend.Controllers;
//
//import amintabite.Capstone_backend.Entities.FavoriteMove;
//import amintabite.Capstone_backend.Payloads.FavMovePayload;
//import amintabite.Capstone_backend.Services.FavMoveService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("/favorites")
//public class FavMoveController {
//
//    @Autowired
//    private FavMoveService favMoveService;
//
//    @PostMapping("/{utenteId}")
//    public FavoriteMove addFavorite(@PathVariable UUID utenteId,
//                                    @RequestBody FavMovePayload payload) {
//        return favMoveService.addFavorite(utenteId, payload);
//    }
//
//    @GetMapping("/{utenteId}")
//    public List<FavoriteMove> getFavorites(@PathVariable UUID utenteId) {
//        return favMoveService.getFavorites(utenteId);
//    }
//
//    @DeleteMapping("/{moveId}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteFavorite(@PathVariable UUID moveId) {
//        favMoveService.removeFavorite(moveId);
//    }
//}


import amintabite.Capstone_backend.Entities.FavoriteMove;
import amintabite.Capstone_backend.Entities.Utente;
import amintabite.Capstone_backend.Payloads.FavMovePayload;
import amintabite.Capstone_backend.Services.FavMoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/favorites")
public class FavMoveController {

    @Autowired
    private FavMoveService favMoveService;

    @PostMapping
    public FavoriteMove addFavorite(@RequestBody FavMovePayload payload,
                                    @AuthenticationPrincipal Utente utente) {
        return favMoveService.addFavorite(utente, payload);
    }

    @GetMapping
    public List<FavoriteMove> getFavorites(@AuthenticationPrincipal Utente utente) {
        System.out.println("PRINCIPAL = " + utente);
        return favMoveService.getFavorites(utente);
    }

    @DeleteMapping("/{moveId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFavorite(@PathVariable UUID moveId,
                               @AuthenticationPrincipal Utente utente) {
        favMoveService.removeFavorite(moveId, utente);
    }
}

