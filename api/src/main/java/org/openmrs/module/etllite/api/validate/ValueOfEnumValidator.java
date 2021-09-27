package org.openmrs.module.etllite.api.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ValueOfEnumValidator implements ConstraintValidator<ValueOfEnum, CharSequence> {
    private List<String> acceptedValues;

    @Override
    public void initialize(ValueOfEnum annotation) {
        acceptedValues = new ArrayList<>();
        for (Enum enumElement : annotation.enumClass().getEnumConstants()) {
            acceptedValues.add(enumElement.name());
        }
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        return value == null ? true : acceptedValues.contains(value.toString());
    }
}
