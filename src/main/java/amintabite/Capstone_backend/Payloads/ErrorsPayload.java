package amintabite.Capstone_backend.Payloads;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor

public class ErrorsPayload {

    private Object messaggio; //per accettare le liste  map degli errori



    private LocalDateTime oraevento;






}


