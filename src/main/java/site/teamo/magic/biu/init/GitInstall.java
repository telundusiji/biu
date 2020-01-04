package site.teamo.magic.biu.init;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class GitInstall {
    @PostConstruct
    public boolean install(){
        return true;
    }

    private boolean check(){
        /**
         * 检测当前环境是否存在git
         */

        /**
         * 检测安装包是否存在
         */
        return true;
    }
}
