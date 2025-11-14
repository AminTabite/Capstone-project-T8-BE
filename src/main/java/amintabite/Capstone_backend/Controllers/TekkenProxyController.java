package amintabite.Capstone_backend.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/proxy")
public class TekkenProxyController {

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/tekken/{character}")
    public ResponseEntity<?> getCharacterMoves(@PathVariable String character) {
        String url = "https://tekkendocs.com/api/t8/" + character + "/framedata";

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Errore nel fetch da TekkenDocs: " + e.getMessage());
        }
    }
}
