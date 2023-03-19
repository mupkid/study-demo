package org.ohx.studyeasyexcel.common.validate;

import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author mudkip
 * @date 2023/3/19
 */
@Component
public class EnumOrdinalValidateStrategy implements EnumValidateStrategy {
    @Override
    public boolean doValidate(Object value, String fieldName, Class<? extends java.lang.Enum<?>> enumClass) throws Exception {
        Method method = enumClass.getMethod("ordinal");
        for (java.lang.Enum<?> constant : enumClass.getEnumConstants()) {
            Object invoke = method.invoke(constant);
            if (invoke.equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean canValidate(EnumValidMode enumValidMode) {
        return EnumValidMode.ORDINAL == enumValidMode;
    }
}
