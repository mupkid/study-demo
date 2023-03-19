package org.ohx.studyeasyexcel.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author mudkip
 * @date 2023/3/18
 */
public interface UserService {
    boolean importUserData(MultipartFile file) throws IOException;
}
