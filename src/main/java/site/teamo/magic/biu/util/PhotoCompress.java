package site.teamo.magic.biu.util;

import net.coobird.thumbnailator.Thumbnails;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PhotoCompress {
    public static void main(String[] args) {
        String outPath = "D:\\备份\\照片压缩";
        String inputPath ="D:\\备份\\照片";
        List<String> outFile = Arrays.asList(new File(outPath).listFiles()).stream().map(x->x.getName()).collect(Collectors.toList());
        Arrays.asList(new File(inputPath).listFiles()).stream().filter(x -> x.isFile()&&!x.getName().endsWith(".mp4")&&!outFile.contains(x.getName()))
                .forEach(x -> {
                            System.out.println(x.getName() + ":" + x.length());
                            compress(outPath,x);
                        }
                );
    }

    public static void compress(String outputPath,File x){
        try {

            if (x.length() > 10000000) {
                Thumbnails.of(x.getAbsolutePath()).scale(0.4f).toFile(outputPath + File.separator + x.getName());
            } else if (x.length() > 6000000) {
                Thumbnails.of(x.getAbsolutePath()).scale(0.6f).toFile(outputPath + File.separator + x.getName());
            } else if (x.length() > 4000000) {
                Thumbnails.of(x.getAbsolutePath()).scale(0.7f).toFile(outputPath + File.separator + x.getName());
            } else if (x.length() > 2000000) {
                Thumbnails.of(x.getAbsolutePath()).scale(0.8f).toFile(outputPath + File.separator + x.getName());
            } else if (x.length() > 1000000) {
                Thumbnails.of(x.getAbsolutePath()).scale(0.9f).toFile(outputPath + File.separator + x.getName());
            } else {
                Thumbnails.of(x.getAbsolutePath()).scale(1.0f).toFile(outputPath + File.separator + x.getName());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
