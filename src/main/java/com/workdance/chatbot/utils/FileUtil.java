package com.workdance.chatbot.utils;

import org.springframework.util.ClassUtils;

import java.io.File;
import java.util.HashSet;
import java.util.UUID;

public class FileUtil {

    private static final HashSet<String> suffixSet;

    static {
        suffixSet = new HashSet<>();
        suffixSet.add(".jpeg");
        suffixSet.add(".jpg");
        suffixSet.add(".png");
        suffixSet.add(".gif");
        suffixSet.add(".svg");
        suffixSet.add(".bmp");
        suffixSet.add(".ico");
        suffixSet.add(".tiff");
    }

    public static File generatePicFile(String suffixName, String time, String IP) {
        String path = getPicStoreDir() + IP + "/" + time;
        String fileName = UUID.randomUUID() + suffixName;
        return new File(path + fileName);
    }

    public static String getPicStoreDir() {
        return ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static/uploadImages/";
    }

    public static boolean isPicFile(String fileName) {
        return suffixSet.contains(fileName);
    }


    public static String getSuffixName(String filename) {
        if (StringUtil.isEmpty(filename)) {
            return "unknown";
        }
        String suffixName = filename.substring(filename.lastIndexOf("."));
        suffixName = suffixName.toLowerCase();
        return suffixName;
    }


}
