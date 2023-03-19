package org.ohx.studyeasyexcel.common.exception;

import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author mudkip
 * @date 2023/3/18
 */
@RestControllerAdvice
public class ControllerExceptionAdvice {
    @ExceptionHandler({BindException.class})
    public String methodArgumentValidateException(BindException e) {
        return e.getBindingResult().getAllErrors().toString();
    }
}
