package vnu.uet.prodmove.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vnu.uet.prodmove.config.ApiConfig;
import vnu.uet.prodmove.entity.Agency;
import vnu.uet.prodmove.entity.Product;
import vnu.uet.prodmove.entity.Warehouse;
import vnu.uet.prodmove.exception.NotFoundException;
import vnu.uet.prodmove.repos.AgencyRepository;
import vnu.uet.prodmove.repos.ProductRepository;
import vnu.uet.prodmove.services.IAgencyService;
import vnu.uet.prodmove.services.implement.ProductService;
import vnu.uet.prodmove.utils.dataModel.AgencyModel;
import vnu.uet.prodmove.utils.dataModel.WarehouseModel;
import vnu.uet.prodmove.utils.specification.CustomSpecification;
import vnu.uet.prodmove.utils.specification.Filter;
import vnu.uet.prodmove.utils.specification.QueryOperator;

@RestController
@RequestMapping(ApiConfig.AGENCY)
public class AgencyController {

    @Autowired
    private IAgencyService agencyService;

    /**
     * @param agencyId the id of agency where stores list of products
     * @param warehouseId the warehouse is chose to store products
     * @param products list ID of products
     * @return
     * @throws NotFoundException
     */
    @PostMapping(ApiConfig.AGENCY_IMPORT_PRODUCTS)
    public ResponseEntity<?> importProducts(@RequestBody Map<String, Object> body) throws NotFoundException {
        int agencyId = (Integer) body.get("agencyId");
        int warehouseId = (Integer) body.get("warehouseId");
        List<Integer> productIds = (List<Integer>) body.get("productIds");
        agencyService.storeProductsInWarehouse(agencyId, warehouseId, productIds);
        return ResponseEntity.ok().body("Import products in warehouse successfully");
    }
    
    @PostMapping(ApiConfig.AGENCY_CREATE_WAREHOUSE)
    public ResponseEntity<?> createWarehouse(@RequestBody Map<String, Object> body) throws NotFoundException {
        Agency agency = agencyService.findById((Integer) body.get("agencyId"));
        if (agency == null) {
            throw new NotFoundException("Agency is not found");
        }
        agencyService.createWarehouse(agency, new WarehouseModel((String) body.get("warehouse_address")));
        return ResponseEntity.ok().body("Create warehouse successfully");
    }

    @GetMapping(ApiConfig.AGENCY_ALL_WAREHOUSE)
    public ResponseEntity<?> getAllWarehouses(@RequestParam(name="agencyId") String agencyId) throws NumberFormatException, NotFoundException {
        Set<Warehouse> warehouses = agencyService.getAllWarehouses(Integer.parseInt(agencyId));
        return ResponseEntity.ok().body(warehouses);
    }

    @PostMapping(ApiConfig.AGENCY_SALE_PRODUCT)
    public ResponseEntity<?> saleProducts(@RequestBody Set<Integer> productIds) {
        agencyService.saleProductsById(productIds);
        return ResponseEntity.ok().build();
    }


    // // test

    @Autowired
    private AgencyRepository agencyRep;

    @Autowired
    private ProductRepository productRep;

    @Autowired
    private ProductService productService;

    @PostMapping("/test")
    public ResponseEntity<?> createAgency(@RequestBody AgencyModel agencyModel) {
        Filter filter = Filter.builder().field("ID").queryOperator(QueryOperator.EQUALS).value("5");
        CustomSpecification<Product> spe = new CustomSpecification<>();
        List<Product> product = productService.findProductWithAttributes();
        return ResponseEntity.ok().body(product);
    }
}
