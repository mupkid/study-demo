package org.ohx.studyeasyexcel.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import org.ohx.studyeasyexcel.common.validate.Excel;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

/**
 * 导入用户数据BO
 *
 * @author mudkip
 * @date 2023/3/18
 */
@Valid
@Validated
public class UserImportBO {
    @NotBlank(groups = {Excel.class})
    @ExcelProperty("姓名*")
    private String username;

    @Positive(groups = {Excel.class})
    @ExcelProperty("年龄*")
    private Integer age;

    @NotBlank(groups = {Excel.class})
    @ExcelProperty("性别*")
    private String sex;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "UserImportBO{" +
            "username='" + username + '\'' +
            ", age=" + age +
            ", sex='" + sex + '\'' +
            '}';
    }
}
