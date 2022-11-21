package vnu.uet.prodmove;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EntityScan("vnu.uet.prodmove.entity")
public class ProdmoveApplication {

    public static void main(final String[] args) {
        SpringApplication.run(ProdmoveApplication.class, args);
    }

}
