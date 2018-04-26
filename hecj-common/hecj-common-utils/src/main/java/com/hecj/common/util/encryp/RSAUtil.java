package com.hecj.common.util.encryp;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

/**
 * RAS非对称加密 
 * 公钥-私钥
 * @author hecj
 *
 */
public class RSAUtil {

    public static final String CHARSET = "UTF-8";
    public static final String RSA_ALGORITHM = "RSA";


    public static Map<String, String> createKeys(int keySize){
        //为RSA算法创建一个KeyPairGenerator对象
        KeyPairGenerator kpg;
        try{
           // kpg = KeyPairGenerator.getInstance(RSA_ALGORITHM);
        	kpg = KeyPairGenerator.getInstance(RSA_ALGORITHM);
        }catch(NoSuchAlgorithmException e){
            throw new IllegalArgumentException("No such algorithm-->[" + RSA_ALGORITHM + "]");
        }

        //初始化KeyPairGenerator对象,密钥长度
        kpg.initialize(keySize);
        //生成密匙对
        KeyPair keyPair = kpg.generateKeyPair();
        //得到公钥
        Key publicKey = keyPair.getPublic();
        String publicKeyStr = Base64.encodeBase64URLSafeString(publicKey.getEncoded());
        //得到私钥
        Key privateKey = keyPair.getPrivate();
        String privateKeyStr = Base64.encodeBase64URLSafeString(privateKey.getEncoded());
        Map<String, String> keyPairMap = new HashMap<String, String>();
        keyPairMap.put("publicKey", publicKeyStr);
        keyPairMap.put("privateKey", privateKeyStr);

        return keyPairMap;
    }

    /**
     * 得到公钥
     * @param publicKey 密钥字符串（经过base64编码）
     * @throws Exception
     */
    public static RSAPublicKey getPublicKey(String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        //通过X509编码的Key指令获得公钥对象
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKey));
        RSAPublicKey key = (RSAPublicKey) keyFactory.generatePublic(x509KeySpec);
        return key;
    }

    /**
     * 得到私钥
     * @param privateKey 密钥字符串（经过base64编码）
     * @throws Exception
     */
    public static RSAPrivateKey getPrivateKey(String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        //通过PKCS#8编码的Key指令获得私钥对象
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
        RSAPrivateKey key = (RSAPrivateKey) keyFactory.generatePrivate(pkcs8KeySpec);
        return key;
    }

    /**
     * 公钥加密
     * @param data
     * @param publicKey
     * @return
     */
    public static String publicEncrypt(String data, RSAPublicKey publicKey){
        try{
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return Base64.encodeBase64URLSafeString(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes(CHARSET), publicKey.getModulus().bitLength()));
        }catch(Exception e){
            throw new RuntimeException("加密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * 私钥解密
     * @param data
     * @param privateKey
     * @return
     */

    public static String privateDecrypt(String data, RSAPrivateKey privateKey){
        try{
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.decodeBase64(data), privateKey.getModulus().bitLength()), CHARSET);
        }catch(Exception e){
            throw new RuntimeException("解密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * 私钥加密
     * @param data
     * @param privateKey
     * @return
     */

    public static String privateEncrypt(String data, RSAPrivateKey privateKey){
        try{
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            return Base64.encodeBase64URLSafeString(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes(CHARSET), privateKey.getModulus().bitLength()));
        }catch(Exception e){
            throw new RuntimeException("加密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * 公钥解密
     * @param data
     * @param publicKey
     * @return
     */

    public static String publicDecrypt(String data, RSAPublicKey publicKey){
        try{
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.decodeBase64(data), publicKey.getModulus().bitLength()), CHARSET);
        }catch(Exception e){
            throw new RuntimeException("解密字符串[" + data + "]时遇到异常", e);
        }
    }

    private static byte[] rsaSplitCodec(Cipher cipher, int opmode, byte[] datas, int keySize){
        int maxBlock = 0;
        if(opmode == Cipher.DECRYPT_MODE){
            maxBlock = keySize / 8;
        }else{
            maxBlock = keySize / 8 - 11;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] buff;
        int i = 0;
        try{
            while(datas.length > offSet){
                if(datas.length-offSet > maxBlock){
                    buff = cipher.doFinal(datas, offSet, maxBlock);
                }else{
                    buff = cipher.doFinal(datas, offSet, datas.length-offSet);
                }
                out.write(buff, 0, buff.length);
                i++;
                offSet = i * maxBlock;
            }
        }catch(Exception e){
            throw new RuntimeException("加解密阀值为["+maxBlock+"]的数据时发生异常", e);
        }
        byte[] resultDatas = out.toByteArray();
        IOUtils.closeQuietly(out);
        return resultDatas;
    }
    
    public static void main (String[] args) throws Exception {
        Map<String, String> keyMap = RSAUtil.createKeys(1024);
        String  publicKey = keyMap.get("publicKey");
        String  privateKey = keyMap.get("privateKey");
       // String  publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCkrHpXQobL3MMEbZtHvojCwkDqx-4awVbmLqXRNXgk4K-FQQIA-Z0fnSJ3pZ_pfOe5eO12wBkhq-p4PGdU9MrBZz077HyEsUHSTyzpsnMMOaoRwSPuRMPK-GKOQ0zZGR_PEI1YDpBS4rakWeve0Pn2JwhKkmsO4KluKcfIukoR-wIDAQAB";
       // String  privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKSseldChsvcwwRtm0e-iMLCQOrH7hrBVuYupdE1eCTgr4VBAgD5nR-dIneln-l857l47XbAGSGr6ng8Z1T0ysFnPTvsfISxQdJPLOmycww5qhHBI-5Ew8r4Yo5DTNkZH88QjVgOkFLitqRZ697Q-fYnCEqSaw7gqW4px8i6ShH7AgMBAAECgYBWY3mG_4qRtk3Eq--TUrySV7AQuzQJazY4aW5p84AE8K6D3Je9hTXNmPS1Cfl7VyCFp5AEBUDp7jOCQkX_RrB6rd3PnBmZkc7_7nx8k2aQRuMHmFKTX4QU1TEh4zGba1Qpaqhe2XlNKAeKSvEAHAH-KTIYWSbSn0xwORAIL9WpsQJBAO6ftLz_eJBpbEEm6Hl01_uiAdNk4ZD_QfaSUHqsEbnj4z_jf3EmSx8SxslxJ_S8SuJ3Q4S7DJUTm86CQpE6VRMCQQCwqjwbwUy0RXY1e-dizRP0BhAoKobtW-MKsHYaRPrzY8Q2GBZjSi_IqxunGwDdo9V-F5od9pGVrBa22usP0DR5AkB0UTE-96EDdAAq-hZULhqiNGSDcSdrEKJM2SGVyo6ReH5rxC95Lltr-DEiPKBncelm3w_wgWUuvfIzBdgBBtg1AkAi6KCUA3A6tvcSvncMaOWmdIRAy5Z51Ixakl_j9Pl5mp8s6TmTSReEGXuPW6CiKzuGfPdxMEW4Q5cBbuS_7S5RAkEAibTYRrQE2QjujIxe34M6mTdTMx92FpGYzp_JftAYkVC-mYyrT-OUN67dPwz5pfqORisyCLVzPF6TY-i5merf7Q";
        System.out.println("公钥: \n\r" + publicKey);
        System.out.println("私钥： \n\r" + privateKey);

        System.out.println("公钥加密——私钥解密");
        String str = "xyl";
        System.out.println("\r明文：\r\n" + str);
        System.out.println("\r明文大小：\r\n" + str.getBytes().length);
        String encodedData = RSAUtil.publicEncrypt(str, RSAUtil.getPublicKey(publicKey));
        /**
         * 安卓給的 
         * */
        //String encodedData ="s8ozrf8Q25M8SmyevXyOMp8_KOsRUiy8SAEkJoinochJjnNtAdTCYWLbEuttvdraa29b_qopUV7DRnDJ_BPwlawsOPtNbbA7Uw4qD-2r3uAUsvCe0g7rxcSvnz-lZ_5c0ceTDjT03Ds5GyzDzZ_ZfWb24V65nvsqlUuauYm0mao";
        
        /**
         * 自己的秘聞
         * */
       // String  encodedData="SuwXmENJn66oxoL4hJ2tuGxKK1wXaAkLv75aKUmOdmNiLaR0qaxLNgIDw9PbC_wW4x5cw6JudIBrUkpAt6jNUWI0vhzP85QDmIUBMCPkDxj2B6xsQTjDC0BrKLz7I09PtAcAw8PYyEXsCn8c9Dmv8GJ5_AHkSvTzjq5Xw1ZS42M";
        //String  encodedData=  "a9rxMdd0qEG-IBVagsEY-AGGcR571GblcZgeU-W4UOH_ElGvjj6HzR6fW0O0Lwj0AVI4KlSYGEo7CQWuRtTeDUK8TpQv5PaQ8aQ-iKyEJswEC8ObWYzUToJJiwPPPXFLVYsCdBaIRE5anO9-DtcZlD43UyuT6SWtogXlR8zok0k";
        System.out.println("密文：\r\n" + encodedData);
        String decodedData = RSAUtil.privateDecrypt(encodedData, RSAUtil.getPrivateKey(privateKey));
        System.out.println("解密后文字: \r\n" + decodedData);
    }

}