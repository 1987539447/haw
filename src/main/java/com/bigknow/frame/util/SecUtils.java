package com.bigknow.frame.util;

import com.bigknow.frame.security.SecInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.UUID;

/**
 * 加密的工具类
 *
 * @author caomei
 */
public final class SecUtils {

    /**
     * 根据加密类型和相关的salt对密码明文进行加密处理
     * 不可逆的加密算法
     *
     * @param info     存储加密类型和hash次数
     * @param password
     * @param salt     加密用的salt,按照规则生成,加密时加入salt进行混淆
     * @return
     */
    public final static String encryptPassword(SecInfo info, String password, String salt) {
        return new SimpleHash(info.getHashAlgorithmName(), password,
                ByteSource.Util.bytes(salt), Integer.parseInt(info.getHashIterations())).toHex();
    }


    /**
     * 加密
     *
     * @param content  需要加密的内容
     * @param key 加密密码
     * @return
     */
    public final static String encrypt(String content, String key) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(key.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(secretKey.getEncoded(), "AES"));// 初始化
            return new BASE64Encoder().encode(cipher.doFinal(content.getBytes("utf-8")));//加密
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }




    /**解密
     * @param content  待解密内容
     * @param key 解密密钥
     * @return
     */
    public final static String decrypt(String content, String key) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(key.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(secretKey.getEncoded(), "AES"));// 初始化
            return new String(cipher.doFinal(new BASE64Decoder().decodeBuffer(content))); // 加密
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 获取随机的UUID字符串
     *
     * @return String
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
