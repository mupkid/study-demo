package org.ohx.studyeasyexcel.common.excel;

import org.ohx.studyeasyexcel.common.validate.Excel;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author mudkip
 * @date 2023/3/18
 */
public class ExcelValidator<T> {
    private final Validator validator;

    private final int beginIndex;

    private final String errorMessageTemplate = "第{}行：{}";

    public ExcelValidator(Validator validator, int beginIndex) {
        this.validator = validator;
        this.beginIndex = beginIndex;
    }

    public List<String> validate(Collection<T> dataList) {
        List<String> errorMessageList = new ArrayList<>();
        int index = beginIndex + 1;
        for (T data : dataList) {
            String errorMessage = this.doValidate(index, data);
            if (errorMessage.isEmpty()) {
                errorMessageList.add(errorMessage);
            }
            index++;
        }
        return errorMessageList;
    }

    private String doValidate(int index, T data) {
        Set<ConstraintViolation<T>> validate = validator.validate(data, Excel.class);
        return validate.size() > 0 ? MessageFormat.format(errorMessageTemplate, index,
            validate.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining("，"))) : "";
    }
}
