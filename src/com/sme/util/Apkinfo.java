package com.sme.util;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.log4j.Logger;
import org.xmlpull.v1.XmlPullParser;

import android.content.res.AXmlResourceParser;
import android.util.TypedValue;



public class Apkinfo {
	public static Logger logger = Logger.getLogger("PROTAL-SERVICE");
	private static final String DEFAULT_XML = "AndroidManifest.xml";
	public String versionCode;
	public String versionName;
	public String packagename;
	private String apkPath="";
	public Apkinfo(String apkpath){
		this.apkPath=apkpath;
	}
	public boolean getIcon(String exportLogoPath) {   
	      byte b[] = new byte [1024];
	      int length; 
	      ZipFile zipFile;
	      try {
	          zipFile = new ZipFile( new File(apkPath));       
	          Enumeration<? extends ZipEntry> enumeration = zipFile.entries();
	          ZipEntry zipEntry = null ;           
	          while (enumeration.hasMoreElements()) {
	             zipEntry = (ZipEntry) enumeration.nextElement();           
	             if (!zipEntry.isDirectory()) {
	                 if(zipEntry.getName().contains("icon")){
	                     OutputStream outputStream = new FileOutputStream(exportLogoPath);
	                     InputStream inputStream = zipFile.getInputStream(zipEntry); 
	                     while ((length = inputStream.read(b)) > 0)
	                        outputStream.write(b, 0, length);
	                     return true;
	                 }
	             }
	          }
	      } catch (IOException e) {
	    	  e.printStackTrace();
	    	  
	      }
	      return false;
	}
	public String getMD5() throws Exception {   
	      byte b[] = new byte [1024*16];
	      int length; 
	      ZipFile zipFile;
	      try {
	          zipFile = new ZipFile( new File(apkPath));       
	          Enumeration<? extends ZipEntry> enumeration = zipFile.entries();
	          ZipEntry zipEntry = null ;           
	          while (enumeration.hasMoreElements()) {
	             zipEntry = (ZipEntry) enumeration.nextElement();           
	             if (!zipEntry.isDirectory()) {
	            	 logger.debug(zipEntry.getName());
	                 if(zipEntry.getName().contains("CERT.RSA")){
	                     InputStream inputStream = zipFile.getInputStream(zipEntry); 
	                     length = inputStream.read(b);
	                     byte[] src=new byte[length];
	                     System.arraycopy(b, 0, src, 0, length);
	                    // return MD5Util.MD5(src);  
	                 }
	             }
	          }
	      } catch (IOException e) {
	    	  e.printStackTrace();
	    	 throw e; 
	      }
	      return "";
	}
	

	public  Map<String,String> parseManifest(Set<String> regActionName) throws Exception{
		Map<String,String> src=new HashMap<String,String>();
			File apkFile = new File(apkPath);
			ZipFile file = new ZipFile(apkFile, ZipFile.OPEN_READ);
			ZipEntry entry = file.getEntry(DEFAULT_XML);
			AXmlResourceParser parser = new AXmlResourceParser();
			parser.open(file.getInputStream(entry));
			String priority_tmp=null;
			int type;
			while ((type = parser.next()) != XmlPullParser.END_DOCUMENT) {
				switch (type) {
				case XmlPullParser.START_DOCUMENT: {
					break;
				}
				case XmlPullParser.START_TAG: {
					for (int i = 0, size = parser.getAttributeCount(); i != size; ++i) {
						
						if(parser.getAttributeName(i).equalsIgnoreCase("priority")){
							priority_tmp=ParseUtil.getAttributeValue(parser, i);
						}
						if(priority_tmp!=null && parser.getAttributeName(i).equalsIgnoreCase("name")){
							String actionname=ParseUtil.getAttributeValue(parser, i);
							logger.debug(" find com="+getAttributeValue(parser, i)+"  priority="+priority_tmp);
							
							if(regActionName.contains(actionname))
								src.put(actionname, priority_tmp);
							priority_tmp=null;
						}
						
						logger.debug("text="+parser.getAttributeName(i)+"   "+getAttributeValue(parser, i));
					}
					break;
				}
				case XmlPullParser.END_TAG: {
					break;
				}
				case XmlPullParser.TEXT: {
					break;
				}
				}
			}
			parser.close();
			return src;
	}
	
	 static String getManifestXMLFromAPK(String apkPath) {
		ZipFile file = null;
		AXmlResourceParser parser = null;
		StringBuilder xmlSb = new StringBuilder(100);
		try {
			File apkFile = new File(apkPath);
			file = new ZipFile(apkFile, ZipFile.OPEN_READ);
			ZipEntry entry = file.getEntry(DEFAULT_XML);
			
			parser = new AXmlResourceParser();
			parser.open(file.getInputStream(entry));
			
			StringBuilder sb = new StringBuilder(10);
			final String indentStep = "	";

			int type;
			while ((type = parser.next()) != XmlPullParser.END_DOCUMENT) {
				switch (type) {
				case XmlPullParser.START_DOCUMENT: {
					log(xmlSb, "<?xml version=\"1.0\" encoding=\"utf-8\"?>");
					break;
				}
				case XmlPullParser.START_TAG: {
					log(false, xmlSb, "%s<%s%s", sb, getNamespacePrefix(parser.getPrefix()), parser.getName());
					sb.append(indentStep);

					int namespaceCountBefore = parser.getNamespaceCount(parser.getDepth() - 1);
					int namespaceCount = parser.getNamespaceCount(parser.getDepth());

					for (int i = namespaceCountBefore; i != namespaceCount; ++i) {
						log(xmlSb, "%sxmlns:%s=\"%s\"", i == namespaceCountBefore ? "  " : sb, parser.getNamespacePrefix(i), parser.getNamespaceUri(i));
					}

					for (int i = 0, size = parser.getAttributeCount(); i != size; ++i) {
						log(false, xmlSb, "%s%s%s=\"%s\"", " ", getNamespacePrefix(parser.getAttributePrefix(i)), parser.getAttributeName(i), getAttributeValue(parser, i));
						logger.debug("feaket = value="+parser.getAttributeName(i)+"   "+getAttributeValue(parser, i));
					}
					// log("%s>",sb);
					log(xmlSb, ">");
					break;
				}
				case XmlPullParser.END_TAG: {
					sb.setLength(sb.length() - indentStep.length());
					log(xmlSb, "%s</%s%s>", sb, getNamespacePrefix(parser.getPrefix()), parser.getName());
					break;
				}
				case XmlPullParser.TEXT: {
					log(xmlSb, "%s%s", sb, parser.getText());
					break;
				}
				}
			}
			parser.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				if(null != parser)
				{
					parser.close();
				}
	
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				if(null != file)
				{
					file.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return xmlSb.toString();
	}

	private static String getNamespacePrefix(String prefix) {
		if (prefix == null || prefix.length() == 0) {
			return "";
		}
		return prefix + ":";
	}

	private static String getAttributeValue(AXmlResourceParser parser, int index) {
		int type = parser.getAttributeValueType(index);
		int data = parser.getAttributeValueData(index);
		if (type == TypedValue.TYPE_STRING) {
			return parser.getAttributeValue(index);
		}
		if (type == TypedValue.TYPE_ATTRIBUTE) {
			return String.format("?%s%08X", getPackage(data), data);
		}
		if (type == TypedValue.TYPE_REFERENCE) {
			return String.format("@%s%08X", getPackage(data), data);
		}
		if (type == TypedValue.TYPE_FLOAT) {
			return String.valueOf(Float.intBitsToFloat(data));
		}
		if (type == TypedValue.TYPE_INT_HEX) {
			return String.format("0x%08X", data);
		}
		if (type == TypedValue.TYPE_INT_BOOLEAN) {
			return data != 0 ? "true" : "false";
		}
		if (type == TypedValue.TYPE_DIMENSION) {
			return Float.toString(complexToFloat(data)) + DIMENSION_UNITS[data & TypedValue.COMPLEX_UNIT_MASK];
		}
		if (type == TypedValue.TYPE_FRACTION) {
			return Float.toString(complexToFloat(data)) + FRACTION_UNITS[data & TypedValue.COMPLEX_UNIT_MASK];
		}
		if (type >= TypedValue.TYPE_FIRST_COLOR_INT && type <= TypedValue.TYPE_LAST_COLOR_INT) {
			return String.format("#%08X", data);
		}
		if (type >= TypedValue.TYPE_FIRST_INT && type <= TypedValue.TYPE_LAST_INT) {
			return String.valueOf(data);
		}
		return String.format("<0x%X, type 0x%02X>", data, type);
	}

	private static String getPackage(int id) {
		if (id >>> 24 == 1) {
			return "android:";
		}
		return "";
	}

	private static void log(StringBuilder xmlSb, String format, Object... arguments) {
		log(true, xmlSb, format, arguments);
	}

	private static void log(boolean newLine, StringBuilder xmlSb, String format, Object... arguments) {

		xmlSb.append(String.format(format, arguments));
		if (newLine)
			xmlSb.append("\n");
	}

	// ///////////////////////////////// ILLEGAL STUFF, DONT LOOK :)

	public static float complexToFloat(int complex) {
		return (float) (complex & 0xFFFFFF00) * RADIX_MULTS[(complex >> 4) & 3];
	}

	private static final float RADIX_MULTS[] = { 0.00390625F, 3.051758E-005F, 1.192093E-007F, 4.656613E-010F };
	private static final String DIMENSION_UNITS[] = { "px", "dip", "sp", "pt", "in", "mm", "", "" };
	private static final String FRACTION_UNITS[] = { "%", "%p", "", "", "", "", "", "" };
	
}