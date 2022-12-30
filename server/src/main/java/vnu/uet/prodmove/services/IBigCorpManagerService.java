package vnu.uet.prodmove.services;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import vnu.uet.prodmove.entity.Account;
import vnu.uet.prodmove.entity.Product;
import vnu.uet.prodmove.exception.ConflictException;
import vnu.uet.prodmove.exception.NotFoundException;
import vnu.uet.prodmove.utils.dataModel.AccountModel;

public interface IBigCorpManagerService {

    public Collection<Account> getAllAccounts();

    public void createAccount(Map<String, String> body) throws ConflictException;
    
    public void updateAccount(Map<String, String> body) throws NotFoundException;
    
    public void deleteAccount(String username) throws NotFoundException;

    public List<Product> getProducts(String filter, int pageNumber, String sortBy, String typeSort);

    boolean checkPassword(String password) throws NotFoundException;
}
