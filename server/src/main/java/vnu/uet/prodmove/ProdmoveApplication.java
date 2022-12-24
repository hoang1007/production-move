package vnu.uet.prodmove;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("vnu.uet.prodmove.entity")
public class ProdmoveApplication {

    public static void main(final String[] args) {
        SpringApplication.run(ProdmoveApplication.class, args);
        // Me me = new Me("asd");
        // Me.change(me);
        // System.out.println(me.name);
    }

}

class Me {
    public String name;

    public Me(String name) {
        this.name = name;
    }

    public static void  change(Me me) {
        me.name = "change name";
    }
}