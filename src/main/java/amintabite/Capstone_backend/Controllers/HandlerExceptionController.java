package amintabite.Capstone_backend.Controllers;

import amintabite.Capstone_backend.Exceptions.BadRequestException;
import amintabite.Capstone_backend.Exceptions.NotFoundException;
import amintabite.Capstone_backend.Exceptions.ValidationsException;
import amintabite.Capstone_backend.Payloads.ErrorsPayload;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class HandlerExceptionController {

@ExceptionHandler(ValidationsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsPayload handleValidationErrors(ValidationsException ex) {

    return new ErrorsPayload(ex.getErrors().toString(), LocalDateTime.now());
}

@ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsPayload hsndleBadRequest(BadRequestException ex) {

    return new ErrorsPayload(ex.getMessage(), LocalDateTime.now());
}

@ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorsPayload handleNotFound(NotFoundException ex){


    return new ErrorsPayload(ex.getMessage(), LocalDateTime.now());

}


@ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorsPayload handleServerError(Exception ex){

    ex.printStackTrace();
    return new ErrorsPayload("Errore da lato server", LocalDateTime.now());

}


}
