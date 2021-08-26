package apoio;

import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;

import javax.crypto.Cipher;

public class CriptografiaAES {

    static String IV = "AAAAAAAAAAAAAAAA";
    static String chaveencriptacao = "0123456789abcdef";

    public static String encrypt(String textopuro) throws Exception {
        String textocriptografado = "";
        Cipher encripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(chaveencriptacao.getBytes("UTF-8"), "AES");
        encripta.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));
        byte[] textencrypted;
        textencrypted = encripta.doFinal(textopuro.getBytes("UTF-8"));
        for (int i = 0; i < textencrypted.length; i++) {
            textocriptografado += new Integer(textencrypted[i])+" ";
        }
        return textocriptografado;
    }
    
}