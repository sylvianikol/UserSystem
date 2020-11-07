package com.springintro.usersystem.utils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class ValidationUtilImpl implements ValidationUtil {

    private Validator validator;

    public ValidationUtilImpl() {
        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();
    }

    @Override
    public <T> boolean isValid(T entity) {
        return this.validator.validate(entity).isEmpty();
    }

    @Override
    public <T> Set<ConstraintViolation<T>> violations(T entity) {
        return this.validator.validate(entity);
    }

    @Override
    public <T> String getViolations(T entity) {
        StringBuilder messages = new StringBuilder();
        this.violations(entity).stream()
                .map(ConstraintViolation::getMessage)
                .forEach(m -> messages.append(m).append(System.lineSeparator()));

        return messages.toString();
    }
}
