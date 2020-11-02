package site.teamo.biu.download.core;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import site.teamo.biu.download.util.Command;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author 爱做梦的锤子
 * @create 2020/9/22
 */
@Service
//@ConfigurationProperties(prefix = "command")
public class CommandManager {

    @Value("${download.git}")
    private String test;

    private List<Command> commands;


    public Command getCommand(String name) {
        return null;
//        return commands.get(name);
    }

    @PostConstruct
    public void init() {
//        List commands = environment.getProperty("commands", List.class);
        System.out.println(test);
    }

}
