package site.teamo.application1.controller;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author 爱做梦的锤子
 * @create 2020/11/2
 */
@RestController
@RequestMapping("/callee")
@Slf4j
public class CalleeController {

    @GetMapping("/login")
    public String login(@RequestParam String username, @RequestParam String callbackUrl) throws IOException {
        log.info("接受登陆请求，用户名：{}", username);
        OkHttpClient client = new OkHttpClient();
        Request get = new Request.Builder()
                .url(callbackUrl + "?msg=" + username + "登陆成功")
                .get()
                .build();
        Response execute = client.newCall(get).execute();
        log.info("发送通知邮件：{}", execute.body().string());
        log.info("{}登陆成功", username);
        return username + "登陆成功";
    }

}
