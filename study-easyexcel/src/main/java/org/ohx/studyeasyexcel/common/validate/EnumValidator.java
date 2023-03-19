package org.ohx.studyeasyexcel.common.validate;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Objects;

/**
 * @author mudkip
 * @date 2023/3/19
 */
public class EnumValidator implements ConstraintValidator<Enum, Object> {

    private Class<? extends java.lang.Enum<?>> enumClass;

    private String fieldName;

    private EnumValidMode enumValidMode;

    @Autowired
    private List<EnumValidateStrategy> strategies;

    @Override
    public void initialize(Enum constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);

        enumClass = constraintAnnotation.enumClass();
        fieldName = constraintAnnotation.field();
        enumValidMode = constraintAnnotation.mode();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (Objects.isNull(value)) {
            return true;
        }

        for (EnumValidateStrategy strategy : strategies) {
            if (strategy.canValidate(enumValidMode)) {
                try {
                    strategy.doValidate(value, fieldName, enumClass);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}
