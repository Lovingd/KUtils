package cn.kutils.security;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FFMD5Util {

    private static char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b',
            'c', 'd', 'e', 'f'};

    private static MessageDigest messagedigest = null;

    static {
        try {
            messagedigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException nsaex) {
            System.err.println(FFMD5Util.class.getName() + "初始化失败，MessageDigest不支持MD5Util。");
            nsaex.printStackTrace();
        }
    }

    /**
     * 传入文件获取该文件的MD5
     * @param file
     * @return
     * @throws IOException
     */
    public static String getFileMD5String(File file) throws IOException {
        FileInputStream in = new FileInputStream(file);
        FileChannel ch = in.getChannel();
        MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
        messagedigest.update(byteBuffer);
        in.close();
        return bufferToHex(messagedigest.digest());
    }

    /**
     * 字符串算MD5
     * @param s
     * @return
     */
    public static String getMD5String(String s) {
        String result = "";
        try {
            result = getMD5String(s.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Byte数组算MD5
     * @param bytes
     * @return
     */
    public static String getMD5String(byte[] bytes) {
        messagedigest.update(bytes);
        return bufferToHex(messagedigest.digest());
    }

    private static String bufferToHex(byte bytes[]) {
        return bufferToHex(bytes, 0, bytes.length);
    }

    private static String bufferToHex(byte bytes[], int m, int n) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++) {
            appendHexPair(bytes[l], stringbuffer);
        }
        return stringbuffer.toString();
    }

    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
        char c0 = hexDigits[(bt & 0xf0) >> 4];
        char c1 = hexDigits[bt & 0xf];
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }

    public static boolean checkPassword(String password, String md5PwdStr) {
        String s = getMD5String(password);
        return s.equals(md5PwdStr);
    }

    public static boolean verify(String text, String sign, String key) {
        text = text + key;
        String mysign = getMD5String(text);
        if (mysign.equals(sign)) {
            return true;
        } else {
            return false;
        }
    }

    public static String getStreamMD5String(InputStream input) {
        try {
            MessageDigest messagedigest = MessageDigest.getInstance("MD5");
            byte[] b = new byte[1024 * 10];
            int length = -1;

            while ((length = input.read(b)) > -1) {
                messagedigest.update(b, 0, length);
            }
            return bufferToHex(messagedigest.digest());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("InputStream md5 name error");
        }
    }
}