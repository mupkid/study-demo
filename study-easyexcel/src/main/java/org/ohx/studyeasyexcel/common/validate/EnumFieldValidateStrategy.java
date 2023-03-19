package org.ohx.studyeasyexcel.common.validate;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author mudkip
 * @date 2023/3/19
 */
@Component
public class EnumFieldValidateStrategy implements EnumValidateStrategy {
    @Override
    public boolean doValidate(Object value, String fieldName, Class<? extends java.lang.Enum<?>> enumClass) throws Exception {
        Field[] fields = enumClass.getDeclaredFields();
        List<String> fieldNames = Arrays.stream(fields).map(Field::getName).collect(Collectors.toList());
        if (!fieldNames.contains(fieldName)) {
            return true;
        }

        Field field = enumClass.getDeclaredField(fieldName);
        field.setAccessible(true);
        for (java.lang.Enum<?> constant : enumClass.getEnumConstants()) {
            Object fieldValue = field.get(constant);
            if (fieldValue.equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isThis(EnumValidMode enumValidMode) {
        return false;
    }
}
