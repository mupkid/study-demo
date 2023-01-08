package org.ohx.studyjunit5.service;

import org.ohx.studyjunit5.model.UserDO;
import org.ohx.studyjunit5.model.UserQuery;

import java.util.List;

/**
 * @author mudkip
 * @date 2022/11/28
 */
public interface UserService {
    List<UserDO> listUser(UserQuery userQuery);
}
