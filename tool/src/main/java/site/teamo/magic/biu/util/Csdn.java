package site.teamo.magic.biu.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author 爱做梦的锤子
 * @create 2020/7/21
 */
public class Csdn {
    private static final Logger logger = LoggerFactory.getLogger(Csdn.class);
    //本地博客地址文本中的文章数量
    private static int blogUrlSize = 0;
    //本地博客地址文本装入HashMap中
    private static List<String> LocalBlogUrl = null;
    //本地博客地址访问统计
//    private static HashMap<Integer, Integer> LocalBlogUrlCount = null;
    //访问总量统计
    private static int count = 0;

    public static void main(String[] args) throws InterruptedException, IOException {

        List<MyIp> ipList = new ArrayList<>();
        ipList.add(new MyIp("60.179.201.207","3000"));
        ipList.add(new MyIp("60.188.16.15","3000"));
        ipList.add(new MyIp("113.194.28.99","9999"));
        ipList.add(new MyIp("60.188.66.158","3000"));
        ipList.add(new MyIp("58.253.155.11","9999"));
        ipList.add(new MyIp("60.188.9.91","3000"));
        ipList.add(new MyIp("60.188.19.174","3000"));
        ipList.add(new MyIp("113.121.92.113","9999"));
        ipList.add(new MyIp("60.188.11.226","3000"));
        ipList.add(new MyIp("60.188.2.46","3000"));
        ipList.add(new MyIp("58.218.25.244","9000"));
        ipList.add(new MyIp("110.243.16.20","9999"));
        ipList.add(new MyIp("36.249.109.59","9999"));
        ipList.add(new MyIp("110.243.4.112","9999"));
        ipList.add(new MyIp("60.188.17.23","3000"));
        ipList.add(new MyIp("61.140.28.228","4216"));
        ipList.add(new MyIp("60.188.1.27","3000"));
        ipList.add(new MyIp("113.121.92.113","9999"));
        ipList.add(new MyIp("60.188.11.226","3000"));
        ipList.add(new MyIp("60.188.2.46","3000"));
        ipList.add(new MyIp("58.218.25.244","9000"));
        ipList.add(new MyIp("110.243.16.20","9999"));
        ipList.add(new MyIp("36.249.109.59","9999"));
        ipList.add(new MyIp("60.188.17.23","3000"));
        ipList.add(new MyIp("61.140.28.228","4216"));
        ipList.add(new MyIp("60.188.1.27","3000"));
        ipList.add(new MyIp("60.188.17.23","3000"));
        ipList.add(new MyIp("61.140.28.228","4216"));
        ipList.add(new MyIp("58.218.25.244","9000"));
        ipList.add(new MyIp("60.188.19.174","3000"));
        ipList.add(new MyIp("60.188.11.226","3000\t"));
        Random random = new Random();
        LocalBlogUrl = MyIp.getBlogUrl();
        LocalBlogUrl.remove("https://blog.csdn.net/telundusiji/article/details/58083227");
        LocalBlogUrl.add("https://zhuanlan.zhihu.com/p/165511234");
        while (true) {
            blogUrlSize = LocalBlogUrl.size();
//            LocalBlogUrlCount = initLocalBlogUrlCount();
            MyIp myIp = ipList.get(random.nextInt(ipList.size()));
            System.setProperty("http.maxRedirects", "50");
            System.getProperties().setProperty("proxySet", "true");
            System.getProperties().setProperty("http.proxyHost", myIp.getAddress());
            System.getProperties().setProperty("http.proxyPort", myIp.getPort());


            try {
                int id = randomBlogUrl();
                Document doc = Jsoup.connect(LocalBlogUrl.get(id))
                        .userAgent("Mozilla")
                        .cookie("auth", "token")
                        .timeout(3000)
                        .get();
                if (doc != null) {
                    count++;
                    System.out.print("ID： " + id + "\tAddress： " + (LocalBlogUrl.get(id) + "\t成功刷新次数: " + count + "\t") + "Proxy： " + myIp.toString() + "\t");
                }
            } catch (IOException e) {
                logger.error(myIp.getAddress() + ":" + myIp.getPort() + "报错");
            }

            sleepThread(1 + random.nextInt(3));
//                    show();

        }
    }


    //访问文章的随机函数，用来模拟真实的访问量操作，以免所有的文章访问量都是一样的，很明显是刷的，此操作随机访问文章，制造访问假象
    public static int randomBlogUrl() {
        int id = new Random().nextInt(blogUrlSize);
        return id;
    }

    //时间的随机函数，用来模拟真实的访问量操作，以防被博客后台识别，模拟操作60-200秒内的随机秒数,
    public static int randomClick() {
        int time = (new Random().nextInt(200)) + 60;
        return time;
    }


    //获取本地BlogUrl.txt文本中的博客地址，并装入hashMap中，key=Integer,value=博客地址
    public static HashMap<Integer, String> getLocalBlogUrl() throws IOException {

        File filePath = new File(System.getProperty("user.dir"), "BlogUrl.txt");
        FileReader in = new FileReader(filePath);
        BufferedReader br = new BufferedReader(in);
        HashMap<Integer, String> hashMap = new HashMap<Integer, String>();
        String line = null;
        int id = 0;
        while ((line = br.readLine()) != null) {
            hashMap.put(id++, line);
        }
        br.close();
        return hashMap;
    }

    //休眠进程，单位是分钟,CSDN的规则好像是：每个IP访问一个博客地址的时间间隔是5-15分钟，计数一次
    public static void sleepThread(int s) throws InterruptedException {
        long ms = s * 1000;
        Thread.sleep(ms);
        System.out.println("睡眠： " + s + "s");
    }

    //访问统计
    public static HashMap<Integer, Integer> initLocalBlogUrlCount() {
        HashMap<Integer, Integer> temp = new HashMap<Integer, Integer>();
        for (int i = 0; i < blogUrlSize; i++) {
            temp.put(i, 0);
        }
        return temp;
    }

//    //展示访问统计总量
//    public static void show(){
//        System.out.println("访问量统计：");
//        for(int i = 0; i<LocalBlogUrlCount.size(); i++){
//            System.out.print("博客【" + i + "】:" + LocalBlogUrlCount.get(i) + "次\t");
//        }
//        System.out.println();
//        System.out.println("总计："+count+"次");
//        System.out.println();
//    }
}
