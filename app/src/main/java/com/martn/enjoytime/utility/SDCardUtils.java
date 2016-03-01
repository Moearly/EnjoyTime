package com.martn.enjoytime.utility;

import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Title: ZeaApp
 * Package: com.martn.zeaapp.utils
 * Description: ("SDcard操作工具类")
 * Date 2014/10/5 23:32
 *
 * @author MartnLei MartnLei_163_com
 * @version V1.0
 */
public class SDCardUtils {

    public static final String APP_DIR = "enjoytime";

    public static final String CACHE_DIR = "cache";
    public static final String DOWNLOAD_DIR = "Download";
    public static final String IMAGES_DIR = "images";
    public static final String TEMP_IMAGE = "temp.jpg";

    /**
     * Don't let anyone instantiate this class.
     */
    private SDCardUtils() {
        throw new Error("Do not need instantiate!");
    }

    /**
     * 获取sd卡中应用目录--appSd--root
     * @return
     */
    public static File getAppDir() {
        File file = null;
        if (hasSdcard()) {
            //获取sd卡根目录，跟应用的是否卸载无关
            File fileSD = Environment.getExternalStorageDirectory();
            file = new File(fileSD, APP_DIR);
            if (!file.exists()) {
                file.mkdir();
            }
        }
        return file;
    }

    /**
     * 缓存
     * @return
     */
    public static File getCacheDir() {

        File root = getAppDir();
        File file = null;
        if (root != null) {
            file = new File(root, CACHE_DIR);
            if (!file.exists())
                file.mkdir();
        }
        return file;
    }

    /**
     * 获取文件下载sd--目录
     * @return
     */
    public static File getDownloadDir() {

        File file = null;
        if (hasSdcard()) {
            File root = Environment.getExternalStorageDirectory();
            file = new File(root, DOWNLOAD_DIR);
            if (!file.exists())
                file.mkdir();
        }
        return file;
    }


    public static File getImagesDir() {

        File root = getAppDir();
        File file = null;
        if (root != null) {
            file = new File(root, IMAGES_DIR);
            if (!file.exists())
                file.mkdir();
        }
        return file;
    }

    public static File getTempImage() {

        File root = getAppDir();
        File file = null;
        if (root != null) {
            file = new File(root, "temp.jpg");
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }


    /**
     * Check if the file is exists
     *
     * @param filePath 文件路径
     * @param fileName 文件名
     * @return 是否存在文件
     */
    public static boolean isFileExistsInSDCard(String filePath, String fileName) {
        boolean flag = false;
        if (hasSdcard()) {
            File file = new File(filePath, fileName);
            if (file.exists()) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * Write file to SD card
     *
     * @param filePath 文件路径
     * @param filename 文件名
     * @param content  内容
     * @return 是否保存成功
     * @throws Exception
     */
    public static boolean saveFileToSDCard(String filePath, String filename,
                                           String content) throws Exception {
        boolean flag = false;
        if (hasSdcard()) {
            File dir = new File(filePath);
            if (!dir.exists()) {
                dir.mkdir();
            }
            File file = new File(filePath, filename);
            FileOutputStream outStream = new FileOutputStream(file);
            outStream.write(content.getBytes());
            outStream.close();
            flag = true;
        }
        return flag;
    }

    /**
     * Read file as stream from SD card
     *
     * @param fileName String PATH =
     *                 Environment.getExternalStorageDirectory().getAbsolutePath() +
     *                 "/dirName";
     * @return Byte数组
     */
    public static byte[] readFileFromSDCard(String filePath, String fileName) {
        byte[] buffer = null;
        try {
            if (hasSdcard()) {
                String filePaht = filePath + "/" + fileName;
                FileInputStream fin = new FileInputStream(filePaht);
                int length = fin.available();
                buffer = new byte[length];
                fin.read(buffer);
                fin.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buffer;
    }

    /**
     * Delete file
     *
     * @param filePath 文件路径
     * @param fileName filePath =
     *                 android.os.Environment.getExternalStorageDirectory().getPath()
     * @return 是否删除成功
     */
    public static boolean deleteSDFile(String filePath, String fileName) {
        File file = new File(filePath + "/" + fileName);
        if (!file.exists() || file.isDirectory())
            return false;
        return file.delete();
    }


    /**
     * 检查是否有sd
     * @return
     */
    public static boolean hasSdcard() {

        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }
}
