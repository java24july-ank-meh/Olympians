package com.olympians;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Base64;

import javax.imageio.ImageIO;



public class ImgurContent {
	
	 private static String clientID = "deab795319600c9";
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static String uploadByLink(String imgLink) throws Exception {
	
	    URL url;
	
	    url = new URL("https://api.imgur.com/3/image");
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	
	    String data = URLEncoder.encode("image", "UTF-8") + "="
	            + URLEncoder.encode(imgLink, "UTF-8");
	
	    //Send request
	    conn.setDoOutput(true);
	    conn.setDoInput(true);
	    conn.setRequestMethod("POST");
	    conn.setRequestProperty("Authorization", "Client-ID " + clientID);
	    conn.setRequestMethod("POST");
	    conn.setRequestProperty("Content-Type",
	            "application/x-www-form-urlencoded");
	
	    conn.connect();
	    StringBuilder stb = new StringBuilder();
	    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
	    wr.write(data);
	    wr.flush();
	
	    // Get the response
	    BufferedReader rd = new BufferedReader(
	            new InputStreamReader(conn.getInputStream()));
	    String line;
	    rd.skip(9);
	    stb.append('{');
	    while ((line = rd.readLine()) != null) {
	        stb.append(line).append("\n");
	    }
	    wr.close();
	    rd.close();
	    
	
	   //Parse response string
	   String[]tokens = stb.toString().split(",");

	   String link = tokens[25].substring(8, tokens[25].length()-2);
	   String deleteHash = tokens[23].substring(14, tokens[23].length()-1);
	
	   //System.out.println(deleteHash);
	   System.out.println(link);
	   
	   return deleteHash;
	}
	

//////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static String uploadByFile(String file) throws Exception {
		
	    //convert image to a base 64 string
		ByteArrayOutputStream baos = new ByteArrayOutputStream(1000);
		BufferedImage img = ImageIO.read(new File(file));
		ImageIO.write(img, "jpg", baos);
		baos.flush();

		String base64String = Base64.getEncoder().encodeToString(baos.toByteArray());
		baos.close();


		
		URL url;
		//System.out.println("img: " + base64String);
	    url = new URL("https://api.imgur.com/3/image");
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	
	    String data = URLEncoder.encode("image", "UTF-8") + "="
	            + URLEncoder.encode(base64String, "UTF-8");
	    
	    //Send request
	    conn.setDoOutput(true);
	    conn.setDoInput(true);
	    conn.setRequestMethod("POST");
	    conn.setRequestProperty("Authorization", "Client-ID " + clientID);
	    conn.setRequestMethod("POST");
	    conn.setRequestProperty("Content-Type",
	            "application/x-www-form-urlencoded");
	
	    conn.connect();
	    StringBuilder stb = new StringBuilder();
	    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
	    wr.write(data);
	    wr.flush();
	
	    // Get the response
	    BufferedReader rd = new BufferedReader(
	            new InputStreamReader(conn.getInputStream()));
	    String line;
	    rd.skip(9);
	    stb.append('{');
	    while ((line = rd.readLine()) != null) {
	        stb.append(line).append("\n");
	    }
	    wr.close();
	    rd.close();
	    
	
	   //Parse response string
	   String[]tokens = stb.toString().split(",");
	   
	   String link = tokens[25].substring(8, tokens[25].length()-2);
	   String deleteHash = tokens[23].substring(14, tokens[23].length()-1);
	
	  // System.out.println(deleteHash);
	   System.out.println(link);
	   
	   return deleteHash;
	}
	

//////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void delete(String deleteHash) throws Exception {
		
	    URL url;
	
	    url = new URL("https://api.imgur.com/3/image"+deleteHash);
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	
	    //Send request
	    conn.setDoOutput(true);
	    conn.setRequestMethod("DELETE");
	    conn.setRequestProperty("Authorization", "Client-ID " + clientID);
	    conn.setRequestMethod("DELETE");
	    conn.setRequestProperty("Content-Type",
	            "application/x-www-form-urlencoded");
	
	    conn.connect();
	}

}
