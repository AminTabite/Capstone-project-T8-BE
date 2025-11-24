package amintabite.Capstone_backend.Exceptions;

import java.util.List;
import java.util.Map;

public class ValidationsException extends RuntimeException {

    private Map<String,String> errors;
    public ValidationsException(Map<String,String> errors) {



        super("Errori di validazione");
        this.errors = errors;
    }
    public Map<String,String> getErrors() {
        return errors;
    }
}
