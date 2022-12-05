package vnu.uet.prodmove.services;

import java.util.List;
import java.util.Map;

import vnu.uet.prodmove.entity.Product;
import vnu.uet.prodmove.exception.ConflictException;
import vnu.uet.prodmove.utils.dataModel.AccountModel;

public interface IBigCorpManagerService {
    public void createAccount(AccountModel accountModel) throws ConflictException;
    
    public void updateAccount(Map<String, String> body);
    
    public void deleteAccount(String accountId);

    public List<Product> getProducts(String filter, int pageNumber, String sortBy, String typeSort);
}
