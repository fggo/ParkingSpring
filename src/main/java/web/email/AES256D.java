package web.email;


import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class AES256D {
	private String key = "Bar12345Bar12345"; // 128 bit key
  private String initVector = "RandomInitVector"; // 16 bytes IV
	 
	public String decrypt(String encrypted) {
	  try {
      IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
      SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
      cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

      byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));

      return new String(original);
    } catch (Exception ex) {
        ex.printStackTrace();
    }

    return null;
	}

}
