package cn.kutils.security;


import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Administrator on 2016/5/19.
 */
public class K3DESUtils {
    private static String encryptTypeName = null;/*算法名称  DESede /..*/
    private static int keySize = -1;/*密钥长度*/
    private static String workMode = null;//工作模式和填充模式

    /**
     * @param encryptTypeName 算法名称
     * @param keySize         密钥长度
     * @param workMode        工作模式和填充模式
     * @return
     */
    public static boolean setKeyInfo(String encryptTypeName, int keySize, String workMode) {
        boolean isOk = false;
        K3DESUtils.encryptTypeName = encryptTypeName;
        K3DESUtils.keySize = keySize;
        K3DESUtils.workMode = workMode;
        if (K3DESUtils.encryptTypeName != null && !K3DESUtils.encryptTypeName.equals("")
                && keySize > 0
                && K3DESUtils.workMode != null && !K3DESUtils.workMode.equals("")) {
            isOk = true;
//            V.d("3Des加密前参数设置成功\n算法名称设置为：" + encryptTypeName + "\n密钥长度设置为：" + keySize);
        }
        return isOk;
    }


    public static byte[] initKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(encryptTypeName);
            keyGenerator.init(keySize);
            SecretKey secretKey = keyGenerator.generateKey();

//            V.d("密钥生成成功,(secretKey.getFormat()):" + secretKey.getFormat());
//            V.d("-------"+secretKey.toString());
                    return secretKey.getEncoded();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
//            V.e("3Des加密密钥生成失败，" + e.getMessage());
            return null;
        }

    }


    public static byte[] encrypt(byte[] key, byte[] data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        SecretKey secretKey = new SecretKeySpec(key, encryptTypeName);
        Cipher c = Cipher.getInstance(workMode);
        c.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptData = c.doFinal(data);
        return encryptData;
    }


    public static byte[] decrypt(byte[] key, byte[] data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        SecretKey secretKey = new SecretKeySpec(key, encryptTypeName);
        Cipher c = Cipher.getInstance(workMode);
        c.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptData = c.doFinal(data);
        return decryptData;
    }
}
