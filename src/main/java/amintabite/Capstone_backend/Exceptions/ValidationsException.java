package amintabite.Capstone_backend.Exceptions;

import java.util.List;

public class ValidationsException extends RuntimeException {

    private List<String> errors;
    public ValidationsException(List<String> errors) {
        super("Errori di validazione");
        this.errors = errors;
    }
    public List<String> getErrors() {
        return errors;
    }
}
