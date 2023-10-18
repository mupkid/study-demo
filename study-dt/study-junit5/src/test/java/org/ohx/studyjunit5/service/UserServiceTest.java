package org.ohx.studyjunit5.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.ohx.studyjunit5.dao.UserDao;
import org.ohx.studyjunit5.model.UserDO;
import org.ohx.studyjunit5.model.UserQuery;
import org.ohx.studyjunit5.service.impl.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mudkip
 * @date 2022/11/28
 */
@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    UserDao userDao;

    @InjectMocks
    UserServiceImpl userService;

    @BeforeAll
    static void setUp() {
    }

    @Test
    void test_listUser() {
        List<UserDO> zhangsan = new ArrayList<>();
        zhangsan.add(new UserDO());
        zhangsan.add(new UserDO());

        Mockito.when(userDao.listUser(Mockito.any())).thenReturn(zhangsan);

        List<UserDO> result = userService.listUser(new UserQuery());

        Assertions.assertEquals(2, result.size());
    }
}
