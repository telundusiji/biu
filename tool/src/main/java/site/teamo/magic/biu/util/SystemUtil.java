package site.teamo.magic.biu.util;

import org.apache.commons.lang3.StringUtils;

import java.nio.charset.Charset;

public class SystemUtil {

    public static OS getOS(){
        String osName = System.getProperty("os.name");
        if(StringUtils.isBlank(osName)){
            return OS.OTHER;
        }
        if(StringUtils.containsIgnoreCase(osName,"win")){
            return OS.WINDOWS;
        }
        if(StringUtils.containsIgnoreCase(osName,"linux")){
            return OS.LINUX;
        }
        return OS.OTHER;
    }

    public static Charset getCharset(){
        String charset = System.getProperty("sun.jnu.encoding");
        if(StringUtils.isBlank(charset)||!Charset.isSupported(charset)){
            return Charset.defaultCharset();
        }
        return Charset.forName(charset);
    }

    public static enum OS{
        WINDOWS,
        LINUX,
        OTHER;
    }
}
