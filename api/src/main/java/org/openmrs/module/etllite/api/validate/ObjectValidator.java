package org.openmrs.module.etllite.api.validate;

import org.apache.commons.collections.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ObjectValidator<T> {
    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public void validate(T objectToValidate, Class validationStep) {
        checkConfigViolations(validator.validate(objectToValidate, validationStep));
    }

    private void checkConfigViolations(Set<ConstraintViolation<T>> violations) {
        List<String> violationMessages = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(violations)) {
            for (ConstraintViolation violation : violations) {
                violationMessages.add(violation.getMessage());
            }
            throw new IllegalArgumentException(violationMessages.toString());
        }
    }
}
