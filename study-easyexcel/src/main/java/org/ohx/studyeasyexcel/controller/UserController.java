package org.ohx.studyeasyexcel.controller;

import org.ohx.studyeasyexcel.common.validate.Add;
import org.ohx.studyeasyexcel.entity.UserDTO;
import org.ohx.studyeasyexcel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author mudkip
 * @date 2023/3/18
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/import")
    void importUserData(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return;
        }
        userService.importUserData(file);
    }

    @PostMapping("/save")
    void saveUser(@RequestBody @Validated({Add.class}) UserDTO user) {

    }
}
