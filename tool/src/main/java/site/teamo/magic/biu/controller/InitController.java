package site.teamo.magic.biu.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.teamo.magic.biu.service.ContentManager;

@RestController
@RequestMapping("/init")
public class InitController {

    private static Logger LOGGER = LoggerFactory.getLogger(InitController.class);

    @Autowired
    private ContentManager contentManager;

    @GetMapping("/init_content")
    public Object initContent() {
        try {
            contentManager.init();
            return true;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e);
            return e.getMessage();
        }
    }

    @GetMapping("/update_content")
    public Object updateContent() {
        try {
            contentManager.init();
            return true;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e);
            return e.getMessage();
        }
    }
}
