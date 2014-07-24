package io.ondemand.dal.repository.exception;

import java.util.Map;

/**
 * Exception thrown when a bean fails to validate.
 *
 * @author rvbiljouw
 */
public final class ValidationException extends IllegalArgumentException {
    private final Map<String, String> errors;

    public ValidationException(Throwable throwable, Map<String, String> errors) {
        super("Validation of bean failed.", throwable);
        this.errors = errors;
    }

    public ValidationException(Map<String, String> errors) {
        super("Validation of bean failed.");
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
