package br.uema.application.util;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class ValidatorEntity {
    protected static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    protected static final Validator validator = factory.getValidator();

    public static <T> Set<ConstraintViolation<T>> validate(T entity) {
        return validator.validate(entity);
    }
}
