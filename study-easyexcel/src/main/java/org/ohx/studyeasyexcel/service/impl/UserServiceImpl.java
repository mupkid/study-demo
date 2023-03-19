package org.ohx.studyeasyexcel.service.impl;

import com.alibaba.excel.EasyExcel;
import org.ohx.studyeasyexcel.common.excel.ExcelValidateEventListener;
import org.ohx.studyeasyexcel.common.excel.ExcelValidator;
import org.ohx.studyeasyexcel.entity.UserImportBO;
import org.ohx.studyeasyexcel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Validator;
import java.io.IOException;
import java.util.List;

/**
 * @author mudkip
 * @date 2023/3/18
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private Validator validator;

    @Override
    public boolean importUserData(MultipartFile file) throws IOException {
        ExcelValidateEventListener<UserImportBO> eventListener = new ExcelValidateEventListener<>(
            new ExcelValidator<>(validator, 1));
        EasyExcel.read(file.getInputStream(), UserImportBO.class, eventListener).headRowNumber(1).sheet().doRead();

        List<UserImportBO> cacheList = eventListener.getCacheList();
        cacheList.forEach(System.out::println);

        List<String> errorMessage = eventListener.getErrorMessage();
        errorMessage.forEach(System.out::println);

        return false;
    }
}
