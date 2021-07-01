package com.hwc.excel.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class TestFileUtil {


    public static InputStream getResourcesFileInputStream(String fileName) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream("" + fileName);
    }

    public static String getPath() {
//        String path = TestFileUtil.class.getResource("/").getPath();
        String path = "C:\\Users\\Dotdee\\Desktop\\excel\\";
        System.out.println("path: " + path);
        return path;
    }

    public static File createNewFile(String pathName) {
        File file = new File(getPath() + pathName);
        if (file.exists()) {
            file.delete();
        } else {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
        }
        return file;
    }

    public static File readFile(String pathName) {
        return new File(getPath() + pathName);
    }

    public static File readUserHomeFile(String pathName) {
        return new File(System.getProperty("user.home") + File.separator + pathName);
    }

    public static String read(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return null;
        }
        FileInputStream inputStream = null;
        String content = null;
        try {
            inputStream = new FileInputStream(file);
            int length = inputStream.available();
            byte bytes[] = new byte[length];
            inputStream.read(bytes);
            inputStream.close();
            content = new String(bytes, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return content;
    }

    public static boolean write(String filePath, String content) {
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        boolean isSuccess = false;
        try {
            fos = new FileOutputStream(filePath);
            bos = new BufferedOutputStream(fos);
            bos.write(content.getBytes(), 0, content.getBytes().length);
            bos.flush();
            bos.close();
            isSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                if (bos != null) {
                    bos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return isSuccess;
    }

}
