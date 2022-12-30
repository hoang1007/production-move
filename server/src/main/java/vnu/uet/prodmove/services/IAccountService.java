package vnu.uet.prodmove.services;


import java.util.List;

import vnu.uet.prodmove.entity.Account;
import vnu.uet.prodmove.enums.UserRole;
import vnu.uet.prodmove.exception.ConflictException;
import vnu.uet.prodmove.exception.NotFoundException;
import vnu.uet.prodmove.utils.dataModel.AccountModel;

public interface IAccountService {
    public Account create(AccountModel accountModel) throws ConflictException;

    @Deprecated
    public Account update(String accountId, AccountModel accountModel);

    public Account update(String username, String password) throws NotFoundException;

    public void delete(String id);

    public Account findByUsername(String username) throws NotFoundException;

    public List<Account> findAccountsByRoleNot(UserRole role);
}
