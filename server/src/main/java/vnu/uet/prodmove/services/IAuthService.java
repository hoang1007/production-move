package vnu.uet.prodmove.services;

import vnu.uet.prodmove.entity.Account;
import vnu.uet.prodmove.exception.ConflictException;
import vnu.uet.prodmove.utils.dataModel.AccountModel;

public interface IAuthService {
    public Account signup(AccountModel accountModel) throws ConflictException;

    public String login(AccountModel accountModel);
}
