package site.teamo.magic.biu.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import site.teamo.magic.biu.util.CommandUtil;
import site.teamo.magic.biu.util.SystemUtil;

@Component
public class Git {
    @Value("${git.install.path.windows}")
    private String gitInstallPathWindows;
    @Value("${git.install.path.linux}")
    private String gitInstallPathLinux;

    public void check() throws Exception {
        /**
         * 检测当前环境是否存在git
         */
        String command = commandPrefix()+"--version";
        if(!StringUtils.containsIgnoreCase(CommandUtil.exec(command).getStandardInfo(),"git version")){
            throw new Exception("Git is missing on this device");
        }
    }

    public String commandPrefix(){
        SystemUtil.OS os = SystemUtil.getOS();
        StringBuilder command = new StringBuilder();
        if(SystemUtil.OS.WINDOWS.equals(os)){
            if(StringUtils.isBlank(gitInstallPathWindows)){
                command.append("git ");
            }else {
                command.append(gitInstallPathWindows);
                command.append("\\bin\\git ");
            }
        }else {
            if(StringUtils.isBlank(gitInstallPathLinux)){
                command.append("git ");
            }else {
                command.append(gitInstallPathLinux);
                command.append("/bin/git ");
            }
        }
        return command.toString();
    }
}
