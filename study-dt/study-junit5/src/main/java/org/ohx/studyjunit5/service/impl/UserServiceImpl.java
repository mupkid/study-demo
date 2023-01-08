package org.ohx.studyjunit5.service.impl;

import org.ohx.studyjunit5.dao.UserDao;
import org.ohx.studyjunit5.model.UserDO;
import org.ohx.studyjunit5.model.UserQuery;
import org.ohx.studyjunit5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mudkip
 * @date 2022/11/28
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<UserDO> listUser(UserQuery userQuery) {
        return userDao.listUser(userQuery);
    }
}
