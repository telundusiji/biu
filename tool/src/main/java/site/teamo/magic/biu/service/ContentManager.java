package site.teamo.magic.biu.service;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.profiles.pegdown.Extensions;
import com.vladsch.flexmark.profiles.pegdown.PegdownOptionsAdapter;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.DataHolder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import site.teamo.magic.biu.util.CommandUtil;
import site.teamo.magic.biu.util.SystemUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class ContentManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContentManager.class);

    @Value("${content.repository.url}")
    private String contentRepositoryUrl;
    @Value("${content.file.path.windows}")
    private String contentFilePathWindows;
    @Value("${content.file.path.linux}")
    private String contentFilePathLinux;
    @Value("#{'${content.file.used}'.split(',')}")
    private List<String> contentFileUsed;
    @Value("${content.repository.username}")
    private String contentRepositoryUsername;
    @Value("${content.repository.password}")
    private String contentRepositoryPassword;

    @Autowired
    private Git git;

    public void init() throws Exception {
        init(getContentFilePath());
    }

    public void update() throws Exception {
        init();
    }

    private void init(String contentFilePath) throws Exception {
        if (StringUtils.isBlank(contentFilePath)) {
            throw new RuntimeException("content path is empty");
        }
        File file = new File(contentFilePath);
        file.deleteOnExit();
        file.mkdirs();
        String command = git.commandPrefix() + "clone " + contentRepositoryUrl + " " + contentFilePath;
        CommandUtil.exec(command);
    }

    public Object list(String path) throws IOException {
        if(StringUtils.isBlank(path)){
            path = getContentFilePath();
        }else {
            path = getContentFilePath()+path;
        }
        File file = new File(path);
        if (!file.exists()) {
            throw new RuntimeException("content file path not exits");
        }
        if(file.isFile()){
            return markdown2Html(StreamUtils.copyToString(new FileInputStream(file), Charset.defaultCharset()));
        }
        List<String> result = Arrays.asList(file.listFiles()).stream()
                .filter(f->{
                    if(f.isDirectory()){
                        return true;
                    }
                    for (String s : contentFileUsed) {
                        if(f.getName().contains(s)){
                            return true;
                        }
                    }
                    return false;
                })
                .map(x-> x.getAbsolutePath().replace(getContentFilePath(),"").replace("\\","/"))
                .collect(Collectors.toList());
        return result;
    }

    public String getContentFilePath() {
        if (SystemUtil.OS.WINDOWS.equals(SystemUtil.getOS())) {
            return contentFilePathWindows;
        } else {
            return contentFilePathLinux;
        }
    }

    private String markdown2Html(String content) throws IOException {
        DataHolder OPTIONS = PegdownOptionsAdapter.flexmarkOptions(true,
                Extensions.ALL
        );


        Parser parser = Parser.builder(OPTIONS).build();
        HtmlRenderer renderer = HtmlRenderer.builder(OPTIONS).build();

        Node document = parser.parse(content);
        return renderer.render(document);
    }


    public static void main(String[] args){
        Map<Object,Object> m3 = new HashMap<>(3);
        m3.put("a","1");
        m3.put("b",1);
        Map<Object,Object> m4 = new HashMap<>(4);
        Map<Object,Object> m7 = new HashMap<>(7);
        Map<Object,Object> m8 = new HashMap<>(8);
        System.out.println("1");
    }
}
