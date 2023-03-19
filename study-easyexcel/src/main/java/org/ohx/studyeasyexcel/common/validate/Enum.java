package org.ohx.studyeasyexcel.common.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author mudkip
 * @date 2023/3/19
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {EnumValidator.class})
public @interface Enum {
    String message() default "{javax.validation.constraints.NotInEnum.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * 校验的目标枚举类
     *
     * @return
     */
    @NotNull
    Class<? extends java.lang.Enum<?>> enumClass();

    /**
     * 校验模式
     *
     * @return
     */
    @NotNull
    EnumValidMode mode() default EnumValidMode.NAME;

    /**
     * 校验枚举的字段
     *
     * @return
     */
    String field() default "";
}
