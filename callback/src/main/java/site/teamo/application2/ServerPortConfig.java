package site.teamo.application2;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

/**
 * @author 爱做梦的锤子
 * @create 2020/11/2
 */
@Component
@EnableAutoConfiguration
public class ServerPortConfig implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {


    @Override
    public void customize(ConfigurableWebServerFactory factory) {
        factory.setPort(8080);
    }
}
