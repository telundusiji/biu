package site.teamo.application2.controller;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author 爱做梦的锤子
 * @create 2020/11/2
 */
@RestController
@RequestMapping("/caller")
@Slf4j
public class CallerController {

    @GetMapping("/action")
    public String action() throws IOException {
        log.info("接受用户请求，调用服务进行登陆");
        OkHttpClient client = new OkHttpClient();
        Request get = new Request.Builder()
                .url("http://127.0.0.1:8081/callee/login?username=lisi&callbackUrl=http://127.0.0.1:8080/caller/email")
                .get()
                .build();
        Response execute = client.newCall(get).execute();
        log.info(execute.body().string());
        return "操作成功";
    }

    @GetMapping("/email")
    public String email(@RequestParam String msg) {
        log.info("接收到发送邮件请求，发送内容为：{}", msg);
        return "发送邮件成功";
    }

}
