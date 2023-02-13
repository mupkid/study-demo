package org.ohx.studymapstruct.assembler;

import org.ohx.studymapstruct.dto.UserDTO;
import org.ohx.studymapstruct.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author mudkip
 * @date 2023/2/13
 */
@Component
public abstract class UserDecorator implements UserConvert {
    @Autowired
    @Qualifier("delegate")
    private UserConvert delegate;

    @Override
    public UserDTO toDto(User user) {
        UserDTO userDTO = delegate.toDto(user);
        userDTO.setName("姓名：" + userDTO.getName());

        return userDTO;
    }
}
