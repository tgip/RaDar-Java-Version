package org.academiadecodigo.socialsaver.command;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginForm {
    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @Size(min = 6, max = 128)
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    @Override
    public String toString() {
        return "LoginForm{" +
                "name='" + name + '\'' +
                "}";
    }
}
