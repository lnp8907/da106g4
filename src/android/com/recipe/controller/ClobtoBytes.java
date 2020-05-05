package android.com.recipe.controller;

import java.util.Base64;

public class ClobtoBytes {
	
 public static byte[] getPicToBytes(String photo){
	 byte[] pic = Base64.getDecoder().decode(photo.split(",")[1]);
	 return pic;
 	}
}
