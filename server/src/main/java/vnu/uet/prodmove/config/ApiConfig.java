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
}
