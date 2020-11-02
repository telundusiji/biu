package site.teamo.magic.biu.util;

import net.coobird.thumbnailator.Thumbnails;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PhotoCompress {
    private static String inputRootPath = "D:\\备份\\照片";
    private static String outputRootPath="D:\\备份\\照片压缩";

    public static void main(String[] args) {
        Arrays.asList(new File(inputRootPath).listFiles()).stream().filter(File::isDirectory).forEach(x -> {
            handle(x.getAbsolutePath());
                }
        );
    }

    public static void handle(String path){
        String outputPath = path.replace(inputRootPath,outputRootPath);
        File[] of = new File(outputPath).listFiles();
        List<String> outputFile = Arrays.asList(of==null?new File[0]:of).stream().map(x -> x.getName()).collect(Collectors.toList());
        Arrays.asList(new File(path).listFiles()).stream().filter(x -> x.isFile() && !x.getName().endsWith(".mp4") && !outputFile.contains(x.getName()))
                .forEach(x -> {
                            System.out.println(x.getName() + ":" + x.length());
                            compress(outputPath, x);
                        }
                );
    }

    public static void compress(String outputPath, File x) {
        if(!new File(outputPath).exists()){
            new File(outputPath).mkdirs();
        }
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
