package site.teamo.biu.download.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.teamo.biu.download.core.CommandManager;
import site.teamo.biu.download.util.BiuJSONResult;
import site.teamo.biu.download.util.Command;

/**
 * @author 爱做梦的锤子
 * @create 2020/9/18
 */
@Api(value = "git下载服务", tags = "git下载服务")
@RestController
@RequestMapping("/git")
public class GitDownloadController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GitDownloadController.class);

    @Autowired
    private CommandManager commandManager;

    @ApiOperation(value = "git一般下载", notes = "git一般下载")
    @GetMapping("/download")
    public BiuJSONResult git(@ApiParam(name = "url", value = "git下载url")
                             @RequestParam String url) {
        LOGGER.info("git url:{}", url);
        Command gitClone = commandManager.getCommand("git_clone");
        return BiuJSONResult.ok(url);
    }

}
