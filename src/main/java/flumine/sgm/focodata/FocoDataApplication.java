package flumine.sgm.focodata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class FocoDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(FocoDataApplication.class, args);
    }

}
