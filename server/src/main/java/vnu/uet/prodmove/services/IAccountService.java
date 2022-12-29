package vnu.uet.prodmove.services;


import vnu.uet.prodmove.entity.Account;
import vnu.uet.prodmove.exception.ConflictException;
import vnu.uet.prodmove.exception.NotFoundException;
import vnu.uet.prodmove.utils.dataModel.AccountModel;

public interface IAccountService {
    public Account create(AccountModel accountModel) throws ConflictException;

    public Account update(String accountId, AccountModel accountModel);

    public void delete(String id);

    public Account findByUsername(String username) throws NotFoundException;
}
