package org.yjg.dao;

import org.yjg.entity.User;

public interface UserDao {
    String queryPasswordByUsername(String username);

    int addNewUser(User user);
}
