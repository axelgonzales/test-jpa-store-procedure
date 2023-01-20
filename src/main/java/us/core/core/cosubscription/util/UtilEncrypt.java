package us.core.core.cosubscription.util;

import java.security.spec.AlgorithmParameterSpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public  class UtilEncrypt {

	private static final String IV = "5D9r9ZVzEYYgha93/aUK2w==";
	private static final String KEY = "u/Gu5posvwDsXUnV5Zaq4g==";
	private static final String KEY_ALGORITHM = "AES";
	private static final String DEFAULT_CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
	private static final String ENCODING = "UTF-8";

	public static String encrypt(String scrypted)   {

		try {
			String plainText = scrypted;
			SecretKey key = new SecretKeySpec(Base64.getDecoder().decode(KEY), KEY_ALGORITHM);
			AlgorithmParameterSpec iv = new IvParameterSpec(Base64.getDecoder().decode(IV)); 
			Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, key, iv);

			return Base64.getEncoder().encodeToString(cipher.doFinal(plainText.getBytes(ENCODING)));
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());

		}
		scrypted = scrypted.replaceAll("/", "\\*");
		return scrypted;
	}

	
	public static String decrypted(String encrypted) {
		encrypted =  encrypted.replaceAll("\\*","/");
	    try {
	        SecretKey key = new SecretKeySpec(Base64.getDecoder().decode(KEY), KEY_ALGORITHM);
	        AlgorithmParameterSpec iv = new IvParameterSpec(Base64.getDecoder().decode(IV));
	        byte[] decodeBase64 = Base64.getDecoder().decode(encrypted);

	        Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
	        cipher.init(Cipher.DECRYPT_MODE, key, iv);

	        return new String(cipher.doFinal(decodeBase64), ENCODING);
	    } catch (Exception e) {
	    	log.error("decrypted " +e.getLocalizedMessage());
	    	return "";
	    }
	}
}
