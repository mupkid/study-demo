package org.ohx.studyeasyexcel.common.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 注意：不能交给Spring管理
 *
 * @author mudkip
 * @date 2023/3/18
 */
public class ExcelValidateEventListener<T> extends AnalysisEventListener<T> {
    private final ExcelValidator<T> excelValidator;

    private final List<T> cacheList = new ArrayList<>();

    private List<String> errorMessage;

    public ExcelValidateEventListener(ExcelValidator<T> excelValidator) {
        this.excelValidator = excelValidator;
    }

    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        cacheList.add(t);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        errorMessage = excelValidator.validate(cacheList);
    }

    public List<T> getCacheList() {
        return cacheList;
    }

    public List<String> getErrorMessage() {
        return errorMessage;
    }
}
