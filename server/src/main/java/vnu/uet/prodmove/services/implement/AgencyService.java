package vnu.uet.prodmove.services.implement;

import org.springframework.stereotype.Service;

import vnu.uet.prodmove.services.IAgencyService;

@Service
public class AgencyService implements IAgencyService {

    @Override
    public void importProductsFromFactory(Iterable<Integer> productIds, Integer factoryId, Integer warehouseId) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void recallProducts(Integer productlineId) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void receiveNeedRepairProducts(Iterable<Integer> productIds) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void receiveProductsFromWarrantyCenter(Iterable<Integer> productIds, Integer warrantyCenterId) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void returnProductsToFactory(Iterable<Integer> productIds, Integer factoryId) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void returnToCustomer(Integer productId, Integer customerId) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void sellProduct(Integer productId, Integer customerId) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void transferProductToWarrantyCenter(Iterable<Integer> productIds, Integer warrantyCenterId) {
        // TODO Auto-generated method stub
        
    }
    
}
