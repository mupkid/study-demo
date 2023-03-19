package org.ohx.studyeasyexcel.common.validate;

/**
 * @author mudkip
 * @date 2023/3/19
 */
public interface EnumValidateStrategy {
    boolean doValidate(Object value, String fieldName, Class<? extends java.lang.Enum<?>> enumClass) throws Exception;

    boolean canValidate(EnumValidMode enumValidMode);
}
