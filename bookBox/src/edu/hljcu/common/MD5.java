package edu.hljcu.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 这个类用来处理用户密码的加密
 * @author wanj
 *
 */
public class MD5
{
	public static String encrypt(String s) 
	{
		byte[] digest=null;
		try
		{
		    MessageDigest md = MessageDigest.getInstance("MD5");
		    md.update(s.getBytes());
		    digest = md.digest();			    
		}
		catch(NoSuchAlgorithmException e)
		{
			e.printStackTrace();			
		}
		return (String) byte2hex(digest);
    }

	private static Object byte2hex(byte[] b)
	{
		String hs = "";
		String stmp = "";
		for(int n=0; n < b.length; n++){
			stmp = Integer.toHexString(b[n] & 0xFF);
			if(stmp.length() == 1){
				hs = hs + "0" + stmp;
			}
			else
				hs = hs + stmp;
		}
		return hs.toUpperCase();
	}
	
	public static void main(String [] args)throws NoSuchAlgorithmException
	{
		System.out.println(MD5.encrypt("123456"));
	}
}