package org.ohx.studyeasyexcel.common.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

/**
 * @author mudkip
 * @date 2023/3/18
 */
public class EasyExcelEventListener<T> extends AnalysisEventListener<T> {
    private Class<T> clazz;

    private ExcelValidator<T> excelValidator;

    public EasyExcelEventListener(ExcelValidator<T> excelValidator) {
        this.excelValidator = excelValidator;
    }

    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        System.out.println(t);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println(analysisContext);
    }
}
