package com.sme.util;

import org.apache.log4j.Logger;
import sun.security.pkcs.PKCS7;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;


public class ApkinformatonUtil {

    private static final Logger logger = Logger.getLogger(ApkinformatonUtil.class);

    //actionname的后缀，actionname由包名+后缀组成
    public static final String ACTION_NAME_POSTFIX = ".RCSEntryAction";
    //路径，目录名称
    public static final String ANDROID_CATEGORY_NAME = "android.intent.category.RCS_PLUGIN";

    //插件中心的包名
    public static final String PLUGIN_CERTER_PACKAGENAME = "com.cmri.prcs";
    private String versionCode;
    private String versionName;
    private String packagename;
    private String appname;
    private String iconpath;
    private String digest;
    private String iconname;
    //	private String apkUploadPath;
    private String actionname;
    private String osversion;

    private HttpServletRequest request;

    public ApkinformatonUtil() {
    }

    public ApkinformatonUtil(HttpServletRequest request) {
//		apkUploadPath=exportIconPath;
        this.request = request;
        mAaptPath = request.getServletContext().getRealPath("tools");
    }

    /**
     * aapt所在的目录。
     */
//	      private String mAaptPath = "D:\\";//winOS
    //private String mAaptPath = ApkUtil.class.getClassLoader().getResource("").getPath();//linux
    static String[] shellCommand;
    static String softName = "";
    static String mAaptPath = "";

    static {
        shellCommand = new String[2];
        final String anOSName = System.getProperty("os.name");
        if (anOSName.toLowerCase().startsWith("windows")) {
            // Windows XP, Vista ...
            shellCommand[0] = "cmd";
            shellCommand[1] = "/C";
            softName = "aapt.exe";
            mAaptPath = "D:\\";
        } else {
            // Unix, Linux ...
            shellCommand[0] = "/bin/sh";
            shellCommand[1] = "-c";
            softName = "aapt";
//            mAaptPath = "/Users/haoy/Documents/android-sdk-macosx/build-tools/23.0.1/";
        }
    }

    public String getinfo(String apkUploadPath) throws Exception {
        String command = mAaptPath + File.separator + softName + " d badging \"" + apkUploadPath + "\"";
//		Runtime runTime = Runtime.getRuntime();
//		Process ps = runTime.exec("aapt d badging "+apkUploadPath);
        Process ps = Runtime.getRuntime().exec(new String[]{shellCommand[0], shellCommand[1], command});
        String input = loadStream(ps.getInputStream());
        if (input == null)
            input = "";
        input = new String(input.getBytes("iso-8859-1"), "utf-8");
        logger.debug(input);
        ps.waitFor();
        ps.destroy();
        return input;
    }

    // read an input-stream into a String
    public static String loadStream(InputStream in) throws IOException {

        in = new BufferedInputStream(in);
        StringBuffer buffer = new StringBuffer();
        int ptr = 0;

        while ((ptr = in.read()) != -1) {
            buffer.append((char) ptr);
        }

        in.close();
        return buffer.toString();
    }

    public ProcessResponse processApk(String apkUploadPath) throws Exception {

        ProcessResponse response = new ProcessResponse();
        response.msg = getinfo(apkUploadPath);
        response.isRunSuccess = true;
        if (response.isRunSuccess) {
            initApkInfo(response.msg);

            getDigest(apkUploadPath);
        }
        return response;

    }

    private void initApkInfo(String msg) throws Exception {
        logger.debug("initApkInfo=:" + msg);
        Pattern pp = Pattern.compile("icon='.*'");
        Matcher mp = pp.matcher(msg);
        if (mp.find()) {
            String s0 = mp.group().trim();
            this.iconname = s0.replace("icon=", "").replace("'", "");
            logger.debug("get iconname:" + iconname);
        }

        pp = Pattern.compile("name='\\S+?'");
        mp = pp.matcher(msg);
        if (mp.find()) {
            String s0 = mp.group().trim();
            this.packagename = s0.replace("name=", "").replace("'", "");

            //如果是插件中心则再次获得actionname
            if (PLUGIN_CERTER_PACKAGENAME.equals(this.packagename)) {
                pp = Pattern.compile("launchable[\\s-:]+activity[\\s-:]+name='\\S+?'");
                mp = pp.matcher(msg);
                if (mp.find()) {
                    String s1 = mp.group().trim();
                    this.actionname = s1.substring(s1.indexOf("=") + 1).replace("'", "");
                }

            } else {
                this.actionname = packagename + ACTION_NAME_POSTFIX;
            }

            logger.debug("get packagename:" + packagename);
        }

        pp = Pattern.compile("versionCode='\\S+?'");
        mp = pp.matcher(msg);
        if (mp.find()) {
            String s0 = mp.group().trim();
            this.versionCode = s0.replace("versionCode=", "").replace("'", "");
            logger.debug("get versionCode=" + versionCode);
        }

        pp = Pattern.compile("versionName='[\\S\\s]+?'");
        mp = pp.matcher(msg);
        if (mp.find()) {
            String s0 = mp.group().trim();
            this.versionName = s0.replace("versionName=", "").replace("'", "");
            logger.debug("get versionName:" + versionName);
        }
        pp = Pattern.compile("label='.*' ");
        mp = pp.matcher(msg);
        if (mp.find()) {
            String s0 = mp.group().trim();
            this.appname = s0.replace("label=", "").replace("'", "");
            logger.debug("get label:" + new String(appname.getBytes(), "utf-8"));
        }

        pp = Pattern.compile("targetSdkVersion:'\\S+?'");
        mp = pp.matcher(msg);
        if (mp.find()) {
            String s0 = mp.group().trim();
            this.osversion = s0.replace("targetSdkVersion:", "").replace("'", "");
            logger.debug("get targetSdkVersion:" + new String(osversion.getBytes(), "utf-8"));
        }
    }


    public void getDigest(String apkPath) throws Exception {
        ZipFile zipFile;
//        String path = request.getServletContext().getRealPath("upload");
        String path = Config.DEFAULT_APK_IMGPATH;
        try {
            zipFile = new ZipFile(new File(apkPath));
            Enumeration<? extends ZipEntry> enumeration = zipFile.entries();
            ZipEntry zipEntry = null;
            while (enumeration.hasMoreElements()) {
                zipEntry = (ZipEntry) enumeration.nextElement();
                try {
                    logger.debug("all file name:" + zipEntry.getName());
                    if (!zipEntry.isDirectory()) {

                        if (zipEntry != null && zipEntry.getName().contains("META-INF") && zipEntry.getName().contains(".RSA")) {
                            InputStream inputStream = zipFile.getInputStream(zipEntry);
                            PKCS7 pkcs7 = new PKCS7(inputStream);
                            X509Certificate publicKey = pkcs7.getCertificates()[0];
                            digest = MD5Util.MD5(publicKey.getEncoded());
                            logger.debug(" file digest:" + digest);
                            inputStream.close();
                        }

                        if (this.iconname != null && !this.iconname.equals("") && zipEntry != null && zipEntry.getName() != null && zipEntry.getName().contains(this.iconname)) {
                            InputStream inputStream = zipFile.getInputStream(zipEntry);
                            iconpath = System.currentTimeMillis() + ".png";
                            OutputStream out = new FileOutputStream(new File(path + "/" + iconpath));
                            logger.debug("icon file name:" + path + "/" + iconpath);
                            byte[] tmp = new byte[4096];
                            int k = 0;
                            while ((k = inputStream.read(tmp)) != -1) {
                                out.write(tmp, 0, k);
                            }
                            out.close();
                            inputStream.close();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
            zipEntry.clone();
            zipFile.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    public static ProcessResponse exec(String cmd) throws IOException {
        logger.debug("exec cmd:" + cmd);
        ReadInputStream msgReadding = null;
        ReadInputStream errorMsgReadding = null;
        ProcessResponse respone = new ProcessResponse();
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(cmd);
            // 获取进程的错误流  
            errorMsgReadding = new ReadInputStream(process.getErrorStream());
            errorMsgReadding.start();
            // 获取正常流信息  
            msgReadding = new ReadInputStream(process.getInputStream());
            msgReadding.start();
            // respone  

            respone.setRunSuccess(process.waitFor() == 0);
            respone.setMsg(msgReadding.getMsg());
            logger.debug("respone msg:" + msgReadding.getMsg());
            respone.setErrorMsg(errorMsgReadding.getMsg());
        } catch (Exception e) {
            respone.setRunSuccess(false);
            respone.setErrorMsg(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (errorMsgReadding != null)
                    errorMsgReadding.toStop();
                if (msgReadding != null)
                    msgReadding.toStop();
                process.destroy();
            } catch (Exception e) {
            }
        }
        return respone;
    }

    /**
     * 检查AndroidManifest.xml文件中是否有规定的actionname和category地址
     *
     * @param path
     * @return
     */
    public boolean checkActionAndPackage(String path) {
        if (null != packagename && packagename.equals(PLUGIN_CERTER_PACKAGENAME)) {
            return true;
        }
        String xml = Apkinfo.getManifestXMLFromAPK(path);
//        if (xml != null && xml.contains(actionname) && xml.contains(ANDROID_CATEGORY_NAME)) {
        if (xml != null) {
            return true;
        }
        return false;
    }

    public static ProcessResponse exec(Process process, String command) {
        ReadInputStream msgReadding = null;
        ReadInputStream errorMsgReadding = null;
        ProcessResponse respone = new ProcessResponse();
        if (process == null) {
            respone.setRunSuccess(false);
            return respone;
        }
        DataOutputStream dos = null;
        try {
            dos = new DataOutputStream(process.getOutputStream());
            dos.writeBytes(command + "\n");
            dos.writeBytes("exit\n");
            dos.flush();
            // 获取进程的错误流  
            errorMsgReadding = new ReadInputStream(process.getErrorStream());
            errorMsgReadding.start();
            // 获取正常流信息  
            msgReadding = new ReadInputStream(process.getInputStream());
            msgReadding.start();
            // respone  
            respone.setRunSuccess(process.waitFor() == 0);
            respone.setMsg(msgReadding.getMsg());
            respone.setErrorMsg(errorMsgReadding.getMsg());
        } catch (Exception e) {
            respone.setRunSuccess(false);
            respone.setErrorMsg(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (dos != null)
                    dos.close();
                if (errorMsgReadding != null)
                    errorMsgReadding.toStop();
                if (msgReadding != null)
                    msgReadding.toStop();
                process.destroy();
            } catch (Exception e) {
            }
        }
        return respone;
    }

    static class ReadInputStream extends Thread {
        private boolean running = true;
        private StringBuffer readBuffer = new StringBuffer();
        private BufferedReader readBr;
        private boolean isReading = false;

        public ReadInputStream(InputStream is) {
            if (is != null)
                readBr = new BufferedReader(new InputStreamReader(is));
        }

        @Override
        public void run() {
            try {
                while (running) {
                    readding();
                    sleep(250);
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (readBr != null)
                    try {
                        readBr.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
            super.run();

        }

        private void readding() throws IOException {
            synchronized (this) {
                if (isReading)
                    return;
                isReading = true;
            }
            if (readBr == null)
                return;
            while (readBr.ready()) {
                readBuffer.append(readBr.readLine());
                readBuffer.append("\n");
            }
            synchronized (this) {
                isReading = false;
            }
        }

        public void toStop() {
            running = false;

        }

        public String getMsg() {
            try {
                readding();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return readBuffer.toString();
        }
    }

    public static class ProcessResponse {
        private boolean isRunSuccess;
        private String msg;
        private String errorMsg;

        public boolean isRunSuccess() {
            return isRunSuccess;
        }

        public void setRunSuccess(boolean isRunSuccess) {
            this.isRunSuccess = isRunSuccess;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getErrorMsg() {
            return errorMsg;
        }

        public void setErrorMsg(String errorMsg) {
            this.errorMsg = errorMsg;
        }

    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getPackagename() {
        return packagename;
    }

    public void setPackagename(String packagename) {
        this.packagename = packagename;
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public String getIconpath() {
        return iconpath;
    }

    public void setIconpath(String iconpath) {
        this.iconpath = iconpath;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getIconname() {
        return iconname;
    }

    public void setIconname(String iconname) {
        this.iconname = iconname;
    }

    public String getActionname() {
        return actionname;
    }

    public void setActionname(String actionname) {
        this.actionname = actionname;
    }

    public String getOsversion() {
        return osversion;
    }

    public void setOsversion(String osversion) {
        this.osversion = osversion;
    }

    public static void main(String[] args) {
//		Pattern pp = Pattern.compile("versionName='[\\S\\s]+?'"); 
//	    Matcher mp = pp.matcher("package: name='com.techbridge.yueliao' versionCode='124' versionName='Yueliao 1.2.4'adfa'"); 
//	    System.out.println(mp.find());
//	    System.out.println(mp.group());
        try {
            String apkPath = "D:\\Download\\apk\\_1466082340279.apk";
            ApkinformatonUtil util = new ApkinformatonUtil();
            util.processApk(apkPath);
            System.out.println(util.actionname);
            System.out.println(util.appname);
            System.out.println(util.digest);
            System.out.println(util.iconname);
            System.out.println(util.iconpath);
            System.out.println(util.osversion);
            System.out.println(util.packagename);
            System.out.println(util.versionCode);
            System.out.println(util.versionName);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
} 
