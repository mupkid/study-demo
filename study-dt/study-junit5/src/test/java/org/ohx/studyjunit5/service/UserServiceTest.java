package org.ohx.studyjunit5.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.ohx.studyjunit5.dao.UserDao;
import org.ohx.studyjunit5.model.UserDO;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * @author mudkip
 * @date 2022/11/28
 */
@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    UserDao userDao;

    @InjectMocks
    UserService userService;

    @BeforeAll
    void mockData() {
        List<UserDO> zhangsan = new ArrayList<>();
        zhangsan.add(new UserDO());
        zhangsan.add(new UserDO());

        when(userDao.listUser()).thenReturn(zhangsan);
    }

    @Test
    void test_listUser() {
    }
}
