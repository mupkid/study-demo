package org.ohx.studyjunit5.controller;

import org.ohx.studyjunit5.model.UserDO;
import org.ohx.studyjunit5.model.UserQuery;
import org.ohx.studyjunit5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author mudkip
 * @date 2022/11/28
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/list")
    List<UserDO> listUser(UserQuery userQuery) {
        return userService.listUser(userQuery);
    }
}
