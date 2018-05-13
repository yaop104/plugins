package com.sme.view;

import com.sme.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 文件上传
 * @author yao
 *
 */
@Controller
@RequestMapping("/imagefile")
public class FileController
{
	/**
	 * 上传logo
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/updateLogo", method={RequestMethod.POST})
	@ResponseBody
    public JSONObject updateLogo(@RequestParam("upload_one_file") MultipartFile file,HttpServletRequest request,HttpServletResponse response){
            System.out.println("file="+file.getOriginalFilename());// 得到上传文件的文件名
			String savePath = request.getServletContext().getRealPath("upload");
            IFileSaver fileUtil = new ImageCheckProxy(new FileSaver(savePath));
    		
    		fileUtil.save(file, file.getOriginalFilename());
    		
    		
    		JSONObject json = fileUtil.getResult();
    		return json;
    }


	/**
	 * 上传审核图片
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/updateThumbnail", method={RequestMethod.POST})
	@ResponseBody
    public JSONObject updateThumbnail(@RequestParam("upload_one_file") MultipartFile file,HttpServletRequest request,HttpServletResponse response){
            System.out.println("file="+file.getOriginalFilename());// 得到上传文件的文件名
//			String savePath = request.getServletContext().getRealPath("upload");
			String savePath = Config.DEFAULT_APK_IMGPATH;
            IFileSaver fileUtil = new ImageCheckProxy(new ImageSizeProxy(new ImageFileSaver(savePath)));
    		
    		fileUtil.save(file, file.getOriginalFilename());
    		
    		
    		JSONObject json = fileUtil.getResult();
    		return json;
    }


	/**
	 * 上传pic
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/updatePic", method={RequestMethod.POST})
	@ResponseBody
	public JSONObject updatePic(@RequestParam("file") MultipartFile file,HttpServletRequest request,HttpServletResponse response){
		String savePath = Config.DEFAULT_APK_IMGPATH;
		IFileSaver fileUtil = new ImageCheckProxy(new FileSaver(savePath));

		fileUtil.save(file, file.getOriginalFilename());


		JSONObject json = fileUtil.getResult();
		return json;
	}

	
//	//获得原始文件名
//    String fileName = file.getOriginalFilename();
//    System.out.println("fileName:"+ fileName);
//    
//    //新文件名
//    String newFileName = UUID.randomUUID()+fileName;
//    
//    //上传到什么地方
//    String path = "D:\\Download\\apk";
//    File f = new File(path);
//    if(!f.exists())f.mkdirs();
//    if(!file.isEmpty()){
//        try {
//            FileOutputStream fos = new FileOutputStream(path+newFileName);
//            InputStream in = file.getInputStream();
//            int b = 0;
//            while((b=in.read())!=-1){
//                fos.write(b);
//            }
//            fos.close();
//            in.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    System.out.println("imgurl:"+ path+newFileName);
}
