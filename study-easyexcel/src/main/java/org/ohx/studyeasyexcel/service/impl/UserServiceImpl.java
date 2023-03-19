package org.ohx.studyeasyexcel.service.impl;

import com.alibaba.excel.EasyExcel;
import org.ohx.studyeasyexcel.common.excel.EasyExcelEventListener;
import org.ohx.studyeasyexcel.common.excel.ExcelValidator;
import org.ohx.studyeasyexcel.entity.UserImportBO;
import org.ohx.studyeasyexcel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Validator;
import java.io.IOException;

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
        EasyExcel.read(file.getInputStream(), UserImportBO.class,
            new EasyExcelEventListener<>(new ExcelValidator<UserImportBO>(validator, 1))).headRowNumber(1).sheet().doRead();

        return false;
    }
}
