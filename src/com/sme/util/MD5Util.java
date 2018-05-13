package com.sme.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
public class MD5Util {  
	static char hexDigits[] = { '0', '1', '2', '3', '4',  
			'5', '6', '7', '8', '9',  
			'A', 'B', 'C', 'D', 'E', 'F' };  
    public final static String MD5(byte[] btInput) {  
        try {  
        	MessageDigest mdInst = MessageDigest.getInstance("MD5");  
//            byte[] btInput = s.getBytes();  
     //获得MD5摘要算法的 MessageDigest 对象  
     //使用指定的字节更新摘要  
            mdInst.update(btInput);  
     //获得密文  
            byte[] md = mdInst.digest();  
     //把密文转换成十六进制的字符串形式  
            int j = md.length;  
            char str[] = new char[j * 2];  
            int k = 0;  
            for (int i = 0; i < j; i++) {  
                byte byte0 = md[i];  
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];  
                str[k++] = hexDigits[byte0 & 0xf];  
            }  
            return new String(str);  
        }  
        catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
    }  
    public final static String getFileMD5String(String filepath) {  
    	FileInputStream in =null;
    	ByteArrayOutputStream out= null;
    	try {  
        	File file=new File( filepath);
     	    in = new FileInputStream(file);
     	   
     	    out = new ByteArrayOutputStream((int)file.length());  
     	  
     		byte[] cache = new byte[1048576];  
	     	for(int i = in.read(cache);i != -1;i = in.read(cache)){  
	     	  out.write(cache, 0, i);  
	     	}


        	MessageDigest mdInst = MessageDigest.getInstance("MD5");  
//            byte[] btInput = s.getBytes();  
		     //获得MD5摘要算法的 MessageDigest 对象  
		     //使用指定的字节更新摘要  
            mdInst.update(out.toByteArray());  
     //获得密文  
            byte[] md = mdInst.digest();  
     //把密文转换成十六进制的字符串形式  
            int j = md.length;  
            char str[] = new char[j * 2];  
            int k = 0;  
            for (int i = 0; i < j; i++) {  
                byte byte0 = md[i];  
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];  
                str[k++] = hexDigits[byte0 & 0xf];  
            }  
            return new String(str);  
        }  
        catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }
        finally
        {
        	if(out != null)
        	{
        		try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        	if(in != null)
        	{
        		try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        }
    } 

}  
