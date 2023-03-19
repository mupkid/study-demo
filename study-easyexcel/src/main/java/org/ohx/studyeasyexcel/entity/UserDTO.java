package org.ohx.studyeasyexcel.entity;

import org.ohx.studyeasyexcel.common.validate.Add;
import org.ohx.studyeasyexcel.common.validate.Enum;
import org.ohx.studyeasyexcel.common.validate.EnumValidMode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

/**
 * @author mudkip
 * @date 2023/3/19
 */
public class UserDTO {
    @NotBlank(groups = {Add.class})
    private String username;

    @PositiveOrZero(groups = {Add.class})
    private Integer age;

    @NotBlank
    @Enum(enumClass = Sex.class, field = "sexCN", mode = EnumValidMode.FIELD, groups = {Add.class})
    private String sex;

    public UserDTO() {
    }

    public UserDTO(String username, Integer age, String sex) {
        this.username = username;
        this.age = age;
        this.sex = sex;
    }

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
}
