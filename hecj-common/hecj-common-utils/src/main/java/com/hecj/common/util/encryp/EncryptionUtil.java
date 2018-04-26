package com.hecj.common.util.encryp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;

/** 
 * @author JavaDigest 
 *  
 */  
public class EncryptionUtil {  

    /** 
     * String to hold name of the encryption algorithm. 
     */  
    public static final String ALGORITHM = "RSA";  

    /** 
     * String to hold name of the encryption padding. 
     */  
    public static final String PADDING = "RSA/NONE/NoPadding";  

    /** 
     * String to hold name of the security provider. 
     */  
    public static final String PROVIDER = "BC";  

    /** 
     * Generate key which contains a pair of private and public key using 1024 
     * bytes. Store the set of keys in Prvate.key and Public.key files. 
     *  
     * @throws NoSuchAlgorithmException 
     * @throws IOException 
     * @throws FileNotFoundException 
     */  
    public static void generateKey() {  
        try {  
           
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());  
            final KeyPairGenerator keyGen = KeyPairGenerator.getInstance(  
                    ALGORITHM, PROVIDER);  
            keyGen.initialize(256);  
            final KeyPair key = keyGen.generateKeyPair();  
            
            Key publicKey = key.getPublic();
            String publicKeyone = Base64.encodeBase64URLSafeString(publicKey.getEncoded());
            //得到私钥
            Key privateKey = key.getPrivate();
            String privateKeyone = Base64.encodeBase64URLSafeString(privateKey.getEncoded());
            System.out.println("publicKeyone"+publicKeyone);
            System.out.println("privateKeyone"+privateKeyone);
        } catch (Exception e) {  
            e.printStackTrace();  
        }  

    }  
    
    
    /**
     * 得到公钥
     * @param publicKey 密钥字符串（经过base64编码）
     * @throws NoSuchProviderException 
     * @throws Exception
     */
    public static RSAPublicKey getPublicKey(String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException {
        //通过X509编码的Key指令获得公钥对象
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM, PROVIDER);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKey));
        RSAPublicKey key = (RSAPublicKey) keyFactory.generatePublic(x509KeySpec);
        return key;
    }
    
    /**
     * 得到私钥
     * @param privateKey 密钥字符串（经过base64编码）
     * @throws NoSuchProviderException 
     * @throws Exception
     */
    public static RSAPrivateKey getPrivateKey(String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException {
        //通过PKCS#8编码的Key指令获得私钥对象
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM, PROVIDER);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
        RSAPrivateKey key = (RSAPrivateKey) keyFactory.generatePrivate(pkcs8KeySpec);
        return key;
    }

    /** 
     * Encrypt the plain text using public key. 
     *  
     * @param text 
     *            : original plain text 
     * @param key 
     *            :The public key 
     * @return Encrypted text 
     * @throws java.lang.Exception 
     */  
    public static byte[] encrypt(String text, PublicKey key) {  
        byte[] cipherText = null;  
        try {  
            // get an RSA cipher object and print the provider  
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());  
            final Cipher cipher = Cipher.getInstance(PADDING, PROVIDER);  

            // encrypt the plain text using the public key  
            cipher.init(Cipher.ENCRYPT_MODE, key);  
            cipherText = cipher.doFinal(text.getBytes());  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return cipherText;  
    }  

    /** 
     * Decrypt text using private key. 
     *  
     * @param text 
     *            :encrypted text 
     * @param key 
     *            :The private key 
     * @return plain text 
     * @throws java.lang.Exception 
     */  
    public static String decrypt(byte[] text, PrivateKey key) {  
        byte[] dectyptedText = null;  
        try {  
            // get an RSA cipher object and print the provider  
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());  
            final Cipher cipher = Cipher.getInstance(PADDING, PROVIDER);  

            // decrypt the text using the private key  
            cipher.init(Cipher.DECRYPT_MODE, key);  
            dectyptedText = cipher.doFinal(text);  

        } catch (Exception ex) {  
            ex.printStackTrace();  
        }  

        return new String(dectyptedText);  
    }  

    /** 
     * Test the EncryptionUtil 
     */  
    public static void main(String[] args) {  
    	/**
    	 * 生成秘钥对
    	 * */
    	generateKey();

        try {  
            final String originalText = "xyl";
            
            /**
             * 获得公钥
             * */
            String  publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCkC6LJaI_m7uYnLEhQQrnkVDLBES96wgXXc38EDX0FX9oQSoU1ZwIsDrZfWFKzNxUV5xfiFxsBIDbjDslg2lNM6YWDqzMvefPBuVjPB_XO3yH-l9X1bUFcFiyfLyp1EzvsbIwOE3hH9LHV3QZjtHvlqya4vypf-a8g-zf5b0V3pwIDAQAB";
            String  privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKQLosloj-bu5icsSFBCueRUMsERL3rCBddzfwQNfQVf2hBKhTVnAiwOtl9YUrM3FRXnF-IXGwEgNuMOyWDaU0zphYOrMy9588G5WM8H9c7fIf6X1fVtQVwWLJ8vKnUTO-xsjA4TeEf0sdXdBmO0e-WrJri_Kl_5ryD7N_lvRXenAgMBAAECgYBTUMmTq3RyoGDqAlaT1N_etFi2r8jCeypGW5Vl9IfLo_v3jDGNhsyWnb5IPG58MPyXjDAYFw4TC051EXx7oUbulT5PQPgzXCxHBRCBbZy35Ex01UEhg41NvH0z-Zsa1KDDmFYBHBtDeCZDGG1RO8w1nvAY5JJWSH87yF1x18GBCQJBAPEO_ubWzhfAhCtGu_i1xCNi6B4whW-L_k4gwii4NbJQD7j7SgKdLomX-zuVI3jrtV1d_Rjbhuwbn9alqdG_58UCQQCuNqHQeob1rkgyZ0Xzdf3R3wcz6Szbf-clofvAt7ngrKt-H2z2cwKwFcKs0nZFEuOPdgYOb845pHwAJPzDwmx7AkEAp9rcTlt3KPzqS8Q2ceoICOf_X75DvDfMPhT7fiU_ZsnvGM0KbSWMWU1WKNgh2jqvkLM44U1D9zcJJws2ZBLwTQJAEfDEVzDXByPsgWxLMtg6zEsYdcsUeIJCboiw4VbKSI3flzxrPzH22-VRx_Rgg5p_Xr27v-7ZoOwEbQtiEFLMUwJAIMqsWtaPZTuFcueLG1Sbqni5t3QXo8cQVbPEprO24ojOycldmCRmMeM5ztofutRAytyrKf7rSmEwTfQLIr83oQ";
            
            /**
             * 加密 
             * */
           final byte[] cipherText = encrypt(originalText, getPublicKey(publicKey));  
            
            /**
             * 将密文转换为64
             * */    
           
           Base64 base64 = new Base64();  
           String cipherTextBase64 = base64.encodeToString(cipherText);  
           
           //String cipherTextBase64 = "JMwnuk5GGU+IQzyQQF26mT4KtUAgJjW9rjjIBC0h6k5cMsaEJvb9Hbz73OagbtVL4CQT7IHA9s6h2whKbrO24We6d2V3f9LAFifNTWrpCavT8RuQCZ5mhLeMmepId9CannzI5jPX5o6gANi/4PQ6YOWlvpoP7yLVEjkh0PpEcRg=";
           //String  cipherTextBase64  ="Tu0o2gcR4C1t5ry13X2qb8L+jPlbpY5A+KM+9eQF/MA=";

           /**
             * 密文64解密
             * */  
            byte[] cipherTextArray = base64.decode(cipherTextBase64);  
            
            /**
             * 获得密文
             * */
            final String plainText = decrypt(cipherTextArray, getPrivateKey(privateKey));  

            // Printing the Original, Encrypted and Decrypted Text  
           System.out.println("Original=" + originalText);  
            System.out.println("Encrypted=" + cipherTextBase64);  
            System.out.println("Decrypted=" + plainText);  

        } catch (Exception e) {  
            e.printStackTrace();  
        }
    }  
}  