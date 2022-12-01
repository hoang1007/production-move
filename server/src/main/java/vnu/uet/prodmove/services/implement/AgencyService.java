package vnu.uet.prodmove.services.implement;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vnu.uet.prodmove.entity.Agency;
import vnu.uet.prodmove.entity.Product;
import vnu.uet.prodmove.entity.Warehouse;
import vnu.uet.prodmove.exception.NotFoundException;
import vnu.uet.prodmove.repos.AgencyRepository;
import vnu.uet.prodmove.repos.WarehouseRepository;
import vnu.uet.prodmove.services.IAgencyService;
import vnu.uet.prodmove.services.IProductService;
import vnu.uet.prodmove.services.IWarehouseService;
import vnu.uet.prodmove.utils.dataModel.WarehouseModel;

@Service
public class AgencyService implements IAgencyService {

    @Autowired
    private AgencyRepository agencyRepository;

    @Autowired
    private IWarehouseService warehouseService;

    @Autowired
    private IProductService productService;

    @Override
    public Agency findById(Integer id) {
        Optional<Agency> wrapperAgency = agencyRepository.findById(id);
        if (wrapperAgency.isPresent()) {
            return wrapperAgency.get();
        } else {
            return null;
        }
    }

    @Override
    public void storeProductsInWarehouse(int agencyId, int warehouseId, Collection<Integer> productIds) throws NotFoundException {
        Agency agency = this.findById(agencyId);
        Warehouse warehouse = agency.getWarehouses().stream()
                                    .filter(item -> item.getAgency().getId() == agencyId)
                                    .findAny().orElseGet(() -> null);
        if(warehouse == null) 
            throw new NotFoundException("ware house is not found");

        Collection<Product> products = productService.findAllProductsById(productIds);
        warehouseService.storeProductsInWarehouse(warehouse, products);
    }

   

    @Override
    public void createWarehouse(Agency agency, WarehouseModel warehouseModel) {
        
    }

    @Override
    public Set<Warehouse> getAllWarehouses(Integer agencyId) throws NotFoundException {
        Optional<Agency> wrapperAgency = agencyRepository.findById(agencyId);
        if (!wrapperAgency.isPresent()) {
            throw new NotFoundException("Agency is not found");
        }
        Set<Warehouse> warehouses = wrapperAgency.get().getWarehouses();
        return warehouses;
    }

    @Override
    public void saleProductsById(Collection<Integer> productIds) {
        
    }
}
