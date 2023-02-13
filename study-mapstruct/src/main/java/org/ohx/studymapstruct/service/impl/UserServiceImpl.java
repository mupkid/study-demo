package org.ohx.studymapstruct.service.impl;

import org.ohx.studymapstruct.assembler.UserConvert;
import org.ohx.studymapstruct.dto.UserDTO;
import org.ohx.studymapstruct.entity.User;
import org.ohx.studymapstruct.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author mudkip
 * @date 2023/2/13
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserConvert userConvert;

    public UserDTO getUser() {
        User user = new User();
        user.setName("张三");
        user.setAge(20);

        return userConvert.toDto(user);
    }
}
