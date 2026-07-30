package com.matlasystems.chat.common.validation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/** Combines validators and returns the errors from every validator. */
public final class CompositeValidator<T> implements Validator<T> {
    private final List<Validator<T>> validators = new ArrayList<>();
    public CompositeValidator() { }
    public CompositeValidator(Collection<? extends Validator<T>> validators) {
        if (validators != null) { this.validators.addAll(validators); }
    }
    public CompositeValidator<T> add(Validator<T> validator) {
        if (validator != null) { validators.add(validator); }
        return this;
    }
    @Override public ValidationResult validate(T value) {
        ValidationResult result = ValidationResult.valid();
        validators.forEach(validator -> result.merge(validator.validate(value)));
        return result;
    }
}
