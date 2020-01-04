package site.teamo.magic.biu.init;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ContentManager {

    @PostConstruct
    public boolean init(){
        return true;
    }

    public boolean update(){
        return true;
    }
}
