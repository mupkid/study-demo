package org.ohx.studyjunit5.dao;

import org.ohx.studyjunit5.model.UserDO;
import org.ohx.studyjunit5.model.UserQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author mudkip
 * @date 2022/11/28
 */
@Repository
public interface UserDao {
    List<UserDO> listUser(UserQuery userQuery);
}



