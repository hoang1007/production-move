package vnu.uet.prodmove.utils.dataModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vnu.uet.prodmove.enums.UserRole;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountModel {
    private String username;
    private String password;
    private UserRole role;

    @Override
    public String toString() {
        return "username: " + username + ", password: " + password + ", role: " + role;
    }
}
