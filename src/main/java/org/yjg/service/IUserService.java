package org.yjg.service;

public interface IUserService {
    String login(String username, String password);

    String register(String username, String password, String password_twice);
}
