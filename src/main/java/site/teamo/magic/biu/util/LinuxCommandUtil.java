package site.teamo.magic.biu.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class LinuxCommandUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(LinuxCommandUtil.class);

    /**
     * CommandResult
     *
     * @param command
     * @return
     * @throws Exception
     */
    public static CommandResult exec(String command) throws Exception {
        LOGGER.info("EXEC:{}", command);
        Runtime runtime = Runtime.getRuntime();
        Process pro = runtime.exec(command);
        CommandResult commandResult = new CommandResult(command);
        commandResult.setErrorInfo(pro.getErrorStream());
        commandResult.setStandardInfo(pro.getInputStream());
        LOGGER.info("Wait for the command[{}] to complete...", command);
        pro.waitFor();
        pro.destroy();
        return commandResult;
    }

    public static class CommandResult {
        private String command;
        private String standardInfo;
        private String errorInfo;

        public CommandResult(String command) {
            this.command = command;
        }

        public String getStandardInfo() {
            return standardInfo;
        }

        public void setStandardInfo(InputStream standardInfo) {
            new Thread(() -> {
                try {
                    this.standardInfo = StreamUtils.copyToString(standardInfo, Charset.defaultCharset());
                } catch (IOException e) {
                    this.errorInfo = "read standardInfo failed!\n" + e.getMessage();
                }
            }).start();
        }

        public String getErrorInfo() {
            return errorInfo;
        }

        public void setErrorInfo(InputStream errorInfo) {
            new Thread(() -> {
                try {
                    this.errorInfo = StreamUtils.copyToString(errorInfo, Charset.defaultCharset());
                } catch (IOException e) {
                    this.errorInfo = "read errorInfo failed!\n" + e.getMessage();
                }
            }).start();
        }

        @Override
        public String toString() {
            return "CommandResult{" + "command='" + command + '\'' +
                    ", standardInfo='" + standardInfo + '\'' + ", errorInfo='" + errorInfo + '\'' + '}';
        }
    }
}
