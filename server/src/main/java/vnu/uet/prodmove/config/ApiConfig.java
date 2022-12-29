package vnu.uet.prodmove.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan("vnu.uet.prodmove.domain")
@EnableJpaRepositories("vnu.uet.prodmove.repos")
@EnableTransactionManagement
public class ApiConfig {
    public static final String LOG_IN = "/login";
    public static final String SIGN_UP = "/signup";
    public static final String LOG_OUT = "/log-out";

    // moderator
    public static final String MODERATOR = "/moderator";
    public static final String MODERATOR_CREATE_ACCOUNT = "/create_account";
    public static final String MODERATOR_DELETE_ACCOUNT = "/delete_account";
    public static final String MODERATOR_UPDATE_ACCOUNT = "/update_account";
    public static final String MODERATOR_STATISTICAL_ANALYSIS = "/statistical_analysis";

    // agency
    public static final String AGENCY = "/agency";
    public static final String AGENCY_IMPORT_PRODUCTS = "/import_products";
    public static final String AGENCY_PENDING_PRODUCTS = "/pending_products";
    public static final String AGENCY_CREATE_WAREHOUSE = "/create_warehouse";
    public static final String AGENCY_ALL_WAREHOUSE = "/warehouses";
    public static final String AGENCY_SELL_PRODUCT = "/sell_product";
    public static final String AGENCY_ALL_ORDERS = "/orders";
    public static final String AGENCY_TRANSFER_TO_WARRANTY = "/transfer_to_warranty";
    public static final String AGENCY_ALL = "/all";
    public static final String AGENCY_DISTRIBUTED_PRODUCTS = "/distributed_products";

    // warehouse
    public static final String WAREHOUSE = "/warehouse";
    public static final String WAREHOUSE_CREATE = "/create";
    public static final String WAREHOUSE_UPDATE = "/update";
    public static final String NEW_PRODUCTS = "/new_products";

    // factory
    public static final String FACTORY = "/factory";
    public static final String EXPORT_TO_AGENCY = "/export";
    public static final String IMPORT_PRODUCTS = "/import";
    public static final String RECEIVE_RETURNED_PRODUCTS = "/receive_returned_products";
    public static final String FACTORY_ALL_WAREHOUSES = "/warehouses";
    public static final String FACTORY_ALL_CREATED_PRODUCTS = "/all_created";

    // productline
    public static final String PRODUCTLINE = "/productline";
    public static final String GET_PRODUCTLINE = "/";
    public static final String ALL_PRODUCTLINES = "/all";

    // warranty center
    public static final String WARRANTY_CENTER = "/warranty_center";
    public static final String RECEIVE_FROM_AGENCY = "/receive";
    public static final String RETURN_TO_AGENCY = "/return_to_agency";
    public static final String RETURN_TO_FACTORY = "/return_to_factory";
    public static final String ALL_NEED_REPAIR_PRODUCTS = "/need_repair";
    public static final String ALL_REPAIRING_PRODUCTS = "/repairing";
}
