package org.ohx.studyeasyexcel.common.validate;

import org.springframework.stereotype.Component;

/**
 * @author mudkip
 * @date 2023/3/19
 */
@Component
public class EnumNameValidateStrategy implements EnumValidateStrategy{
    @Override
    public boolean doValidate(Object value, String fieldName, Class<? extends java.lang.Enum<?>> enumClass) {
        for (java.lang.Enum<?> constant : enumClass.getEnumConstants()) {
            if (constant.name().equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean canValidate(EnumValidMode enumValidMode) {
        return EnumValidMode.NAME == enumValidMode;
    }
}
