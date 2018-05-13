package com.sme.view;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("file/")
public class FileUpLoadController {

	// {
	// System.out.println("init UserController");
	// }

	private String getFilename(){
		/*Calendar time = Calendar.getInstance();
		Random rd = new Random();
		return String.valueOf(time
				.get(Calendar.YEAR))
				+ String.valueOf(time.get(Calendar.MONTH))
				+ String.valueOf(time
						.get(Calendar.DAY_OF_MONTH))
				+ String.valueOf(time.get(Calendar.HOUR_OF_DAY))
				+ String.valueOf(time.get(Calendar.MINUTE))
				+ String.valueOf(time.get(Calendar.SECOND))
				+ String.valueOf(rd.nextInt(100));*/
		return UUID.randomUUID().toString();
	}

	@RequestMapping(value = "fileUpload", method = RequestMethod.POST)
	public String fileUpload(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
		System.out
				.print("++++++++++++++++++++++++开始文件上传++++++++++++++++++++++++++++++");
		// 图片上传路径
		String uploadPath = req.getSession().getServletContext()
				.getRealPath("/img")
				 ;
		System.out.print("上传路径为：" + uploadPath);
		// 图片临时上传路径
		String tempPath = uploadPath+File.separatorChar+"temp";
		// 图片网络相对路径
		String imagePath = req.getScheme() + "://" + req.getServerName() + ":"
				+ req.getServerPort() + req.getContextPath() + "/img/";
		// 文件夹不存在就自动创建：
		if (!new File(uploadPath).isDirectory())
			new File(uploadPath).mkdirs();
		if (!new File(tempPath).isDirectory())
			new File(tempPath).mkdirs();
		try {
			DiskFileUpload fu = new DiskFileUpload();
			// 设置最大文件尺寸，这里是3MB
			fu.setSizeMax(4194304);
			// 设置缓冲区大小，这里是4kb
			fu.setSizeThreshold(4096);
			// 设置临时目录：
			fu.setRepositoryPath(tempPath);
			// 得到所有的文件：
			List fileItems = fu.parseRequest(req);
			Iterator i = fileItems.iterator();
			// 依次处理每一个文件：
			while (i.hasNext()) {
				FileItem file = (FileItem) i.next();
				// 获得文件名，这个文件名是用户上传时用户的绝对路径：
				String sourcefileName = file.getName();
				if (sourcefileName != null
						&& (sourcefileName.endsWith(".jpg") || sourcefileName
								.endsWith(".gif") || sourcefileName.endsWith(".png"))) {
					// 在这里可以记录用户和文件信息,生成上传后的文件名
					String destinationfileName = null;
					
					
					if (sourcefileName.endsWith(".jpg")) {
						destinationfileName = getFilename() + ".jpg";
					} else if (sourcefileName.endsWith(".gif")) {
						destinationfileName = getFilename() + ".gif";
					} else if (sourcefileName.endsWith(".png")) {
						destinationfileName = getFilename() + ".png";
					} 
					File f1 = new File(uploadPath,destinationfileName);
					file.write(f1);
					/*
					 * out.print(uploadPath+"成功上传！") ;
					 * out.print("<img src="+imagePath
					 * +"upload/images/"+destinationfileName+">");
					 */
					req.setAttribute("errorMsg", "上传成功！");
					req.setAttribute("imagePath", imagePath+destinationfileName);
					req.setAttribute("imageDBPath", "img/"+destinationfileName);
					/*req.getRequestDispatcher("openUpload.jsp").forward(req,
							resp);*/
				/*	RequestDispatcher dispatcher=req.getRequestDispatcher("/openUpload.jsp");
					dispatcher.forward(req,resp);*/
					
					
				} else {
					req.setAttribute("errorMsg", "上传文件出错，只能上传 *.jpg , *.gif , *.png ");
				/*	RequestDispatcher dispatcher=req.getRequestDispatcher("/openUpload.jsp");
					dispatcher.forward(req,resp);*/
				}
			}
			// 跳转到上传成功提示页面
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("errorMsg", "上传失败,请确认上传的文件大小不能超过4M");
			/*RequestDispatcher dispatcher=req.getRequestDispatcher("/openUpload.jsp");
			dispatcher.forward(req,resp);*/
		}
		return "openUpload";
	}
	@RequestMapping(value = "openUpload", method = RequestMethod.GET)
	public String openUpload() {
		return "openUpload";
	}
	
	/**
	 * 下载
	 * @param res
	 * @param res
	 * @param filePath
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/download")
	public void download(HttpServletRequest req, HttpServletResponse res, String filePath) throws Exception {
		res.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("UTF-8");
		
		File file = new File(filePath);
		if (!file.exists() || !file.isFile()) 
			return;
		
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		
		try {
			long fileLength = file.length();   
			res.setContentType("application/x-msdownload;");   
			res.setHeader("Content-disposition", "attachment; filename="  
			        + new String(file.getName().getBytes("utf-8"), "ISO8859-1"));   
			res.setHeader("Content-Length", String.valueOf(fileLength));   
			
			bis = new BufferedInputStream(new FileInputStream(file));
			bos = new BufferedOutputStream(res.getOutputStream());
			
			byte[] buffer = new byte[2048];
			int length;
			while ((length = bis.read(buffer, 0, buffer.length)) != -1) {
				bos.write(buffer, 0, length);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bis != null) {
				bis.close();
			}
			if (bos != null) {
				bos.close();
			}
		}
	}
}
