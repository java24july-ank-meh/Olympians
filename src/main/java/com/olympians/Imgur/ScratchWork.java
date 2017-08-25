package com.olympians.Imgur;


import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;



public class ScratchWork {

      public static void main(String[] args) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException
  {
      try{
    	  /*String deleteHash = ImgurContent.uploadByLink("http://www.ikea.com/gb/en/images/products/ingolf-chair-brown-black__0355482_pe547815_s5.jpg");
    	  ImgurContent.delete(deleteHash);
    	  *//*
    	  String deleteHash = ImgurContent.uploadByFile("C:\\docs\\Revature\\pic\\kitty.jpg");
    	  ImgurContent.delete(deleteHash);*/
    	  
    	  
    	  
      }catch (Exception e) {
    	  e.printStackTrace();
      }
      
      
      Random random = new Random();
      byte[] salt = new byte[16];
      random.nextBytes(salt);
      KeySpec spec = new PBEKeySpec("password".toCharArray(), salt, 65536, 128);
      SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
      byte[] hash = f.generateSecret(spec).getEncoded();
      Base64.Encoder enc = Base64.getEncoder();
      System.out.println("salt: " + enc.encodeToString(salt));
      System.out.println("hash: " + enc.encodeToString(hash));
  }
}

