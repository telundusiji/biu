package site.teamo.biu.demo;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author 爱做梦的锤子
 * @create 2020/8/24
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class})
public class Application implements ApplicationRunner {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class)
                .web(WebApplicationType.NONE) // .REACTIVE, .SERVLET
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }

    @Autowired
    private StringEncryptor encryptor;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String password = "123456";
        String encryptPwd = encryptor.encrypt(password);
        System.out.println("加密:：" + encryptPwd);
        System.out.println("解密：" + encryptor.decrypt(encryptPwd));
    }
}
