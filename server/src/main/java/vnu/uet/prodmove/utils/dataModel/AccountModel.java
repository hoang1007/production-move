package vnu.uet.prodmove.utils.dataModel;

import lombok.Data;
import vnu.uet.prodmove.config.UserRole;

@Data
public class AccountModel {
    private String username;
    private String password;
    private UserRole role;

    @Override
    public String toString() {
        return "username: " + username + ", password: " + password + ", role: " + role;
    }
}
