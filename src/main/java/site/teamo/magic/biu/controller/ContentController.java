package site.teamo.magic.biu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.teamo.magic.biu.service.ContentManager;

import java.io.IOException;

@RestController
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentManager contentManager;

    @GetMapping("/list")
    public Object list(@RequestParam(required = false) String path) throws IOException {
        return contentManager.list(path);
    }
}
