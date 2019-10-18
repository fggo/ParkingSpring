package com.parking.common.filter.wrapper;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
//import java.nio.charset.Charset;
//import java.security.MessageDigest;
//import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncryptWrapper extends HttpServletRequestWrapper {

  public EncryptWrapper(HttpServletRequest request) {
    super(request);
  }
  
  @Override
  public String getParameter(String name) {
    String value="";
    if(name!=null && (//name.equals("userpw") 
                     name.equals("pwEnroll")
                     || name.equals("newPwChk"))) {
      try {
        value=generateHash(super.getParameter(name));
      } catch(NoSuchAlgorithmException e) {
        e.printStackTrace();
      } catch(InvalidKeySpecException e) {
        e.printStackTrace();
      }
//      System.out.println("암호화된 비번: " + value);
      return value;
    }
    else {
      return super.getParameter(name);
    }
  }
  
  private String generateHash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
      int iterations = 1000;
      char[] chars = password.toCharArray();
      byte[] salt = getSalt();
       
      PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
      SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
      byte[] hash = skf.generateSecret(spec).getEncoded();

//      System.out.println((iterations + ":" + toHex(salt) + ":" + toHex(hash)).length());
      return iterations + ":" + toHex(salt) + ":" + toHex(hash);
  }
   
  private byte[] getSalt() throws NoSuchAlgorithmException {
      SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
      byte[] salt = new byte[16];
      sr.nextBytes(salt);
      return salt;
  }
   
  private String toHex(byte[] array) throws NoSuchAlgorithmException {
      BigInteger bi = new BigInteger(1, array);
      String hex = bi.toString(16);
      int paddingLength = (array.length * 2) - hex.length();
      if(paddingLength > 0)
      {
          return String.format("%0"  +paddingLength + "d", 0) + hex;
      }else{
          return hex;
      }
  }

//  private String getSha512(String val) {
//    String encPwd="";
//    MessageDigest md=null;
//    System.out.println(val);
//    try {
//      md=MessageDigest.getInstance("SHA-512");
//      
//    }catch(NoSuchAlgorithmException e) {
//      e.printStackTrace();
//    }
//    byte[] bytes=val.getBytes(Charset.forName("UTF-8"));
//    
//    md.update(bytes);
//    
//    encPwd=Base64.getEncoder().encodeToString(md.digest());   
//    return encPwd;
//  }

}
