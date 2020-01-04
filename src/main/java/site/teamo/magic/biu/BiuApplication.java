package site.teamo.magic.biu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "site.teamo.magic.biu.*")
public class BiuApplication {
    public static void main(String[] args){
        SpringApplication.run(BiuApplication.class,args);
    }
}
