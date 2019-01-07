package softLanch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;



@SpringBootApplication
@EnableJpaAuditing
public class SoftLaunchMain {

    public static void main(String[] args){
        SpringApplication.run(SoftLaunchMain.class,args);

    }

}
