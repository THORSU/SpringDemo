package org.yjg.entity.protocal;

public class LogInJson {
    private String username;
    private String password;
    private String password_twice;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword_twice() {
        return password_twice;
    }

    public void setPassword_twice(String password_twice) {
        this.password_twice = password_twice;
    }
}
