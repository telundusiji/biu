package site.teamo.magic.biu.util;

import java.io.*;
import java.util.UUID;

/**
 * @author 爱做梦的锤子
 * @create 2020/7/17
 */
public class CreateData {
    public static void main(String[] args) throws IOException {
        Writer w = new FileWriter(new File("data.csv"));
        String[] names = new String[]{"张三","李四","王五","赵六"};
        for (int i = 0; i < names.length; i++) {
            String a = "爱好"+i+",爱好"+(i+1);
            String c = "科目一:"+i+",科目二:"+(i+1);
            w.write(UUID.randomUUID().toString()+"|"+names[i]+"|"+a+"|"+c+"\n");
        }
        w.flush();
        w.close();
    }
}
