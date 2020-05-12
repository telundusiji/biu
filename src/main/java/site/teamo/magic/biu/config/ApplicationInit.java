package site.teamo.magic.biu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import site.teamo.magic.biu.service.ContentManager;
import site.teamo.magic.biu.service.Git;

import javax.annotation.PostConstruct;

@Component
public class ApplicationInit {

    @Autowired
    private ContentManager contentManager;
    @Autowired
    private Git git;

    @PostConstruct
    public void init() throws Exception {
        git.check();
        contentManager.init();
    }
}
