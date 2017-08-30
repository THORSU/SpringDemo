package org.yjg.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yjg.dao.UserDao;
import org.yjg.entity.User;
import org.yjg.service.IUserService;
import org.yjg.util.Constant;
import org.yjg.util.StringUtil;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserDao userDao;

    public String login(String username, String password) {
        try {
            String passwordInDb = userDao.queryPasswordByUsername(username);
            if (passwordInDb == null) {
                return Constant.USER_NOT_EXIST;
            }
            if (!passwordInDb.equals(password)) {
                return Constant.PASSWORD_WRONG;
            } else {
                return Constant.OK;
            }
        } catch (Exception e) {
            return Constant.ERROR;
        }
    }

    public String register(String username, String password, String password_twice) {
        try {
            if (!password.equals(password_twice)) {
                return Constant.PASSWORD_NOT_THE_SAME;
            }
            String checkUsername = userDao.queryPasswordByUsername(username);
            if (!StringUtil.isNullOrBlank(checkUsername)) {
                return Constant.USER_EXIST;
            }
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setUid(StringUtil.createUUID());
            userDao.addNewUser(user);
            return Constant.OK;
        } catch (Exception e) {
            e.printStackTrace();
            return Constant.ERROR;
        }
    }
}
