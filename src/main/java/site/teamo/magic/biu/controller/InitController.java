package site.teamo.magic.biu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/init")
public class InitController {

    @GetMapping("/install_git")
    public Object installGit() {
        return "true";
    }

    @GetMapping("/init_content")
    public Object initContent() {
        return "true";
    }

    @GetMapping("/update_content")
    public Object updateContent() {
        return true;
    }
}
