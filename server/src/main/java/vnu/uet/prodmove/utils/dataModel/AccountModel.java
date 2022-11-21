package vnu.uet.prodmove.utils.dataModel;

import lombok.Data;

@Data
public class AccountModel {
    private String username;
    private String password;
    private String role;

    @Override
    public String toString() {
        return "username: " + username + ", password: " + password + ", role: " + role;
    }
}
