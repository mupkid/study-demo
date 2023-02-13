package org.ohx.studymapstruct.service.impl;

import org.junit.jupiter.api.Test;
import org.ohx.studymapstruct.dto.UserDTO;
import org.ohx.studymapstruct.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author mudkip
 * @date 2023/2/13
 */
@SpringBootTest
class UserServiceImplTest {
    @Autowired
    private UserService userService;

    @Test
    void getUser() {
        UserDTO user = userService.getUser();
        System.out.println(user.getName());
    }
}
