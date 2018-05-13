package com.sme.view;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sme.core.model.StringJSON;
import com.sme.core.service.InterfaceBaseService;
import com.sme.core.view.BaseController;
import com.sme.entity.Feedback;
import com.sme.service.FeedbackService;
import com.sme.util.FileSaver;
import com.sme.util.IFileSaver;
import com.sme.util.ImageCheckProxy;

@Controller
@RequestMapping("/feedback")
public class FeedbackController extends BaseController<Feedback>{

	@Autowired
	public FeedbackService feedbackService;
	@RequestMapping(value="/feedbacklist", method={RequestMethod.GET})
	public String feedbacklist(HttpServletRequest req) {
		return "/tptPosition/feedback";
	}
	@RequestMapping(value = "/insertData", method = {RequestMethod.POST})
	@ResponseBody
	public StringJSON insertData(@RequestParam(value = "pic", required = false) MultipartFile[] files,
			long userId,String text,HttpServletRequest request,HttpServletResponse response){
		Feedback feedback = new Feedback();
		feedback.setUserId(userId);
		feedback.setText(text);
		feedback.setCreatTime(new Date());
		String savePath = request.getServletContext().getRealPath("upload");
		try {
			StringBuilder sb = new StringBuilder();
			//判断file数组不能为空并且长度大于0  
			if(files!=null&&files.length>0){  
				for(int i = 0;i<files.length;i++){
					MultipartFile file = files[i];  
					IFileSaver fileUtil = new ImageCheckProxy(new FileSaver(savePath));
					if(!file.isEmpty()){
						//保存文件  
						fileUtil.save(file, file.getOriginalFilename());
						sb.append(file.getOriginalFilename()).append(",");
					}
				}  
				sb.deleteCharAt(sb.lastIndexOf(","));
				feedback.setPic(sb.toString());
			}
			feedbackService.insert(feedback);
			return getSuccess(true, "反馈成功");
		} catch (Exception e) {
			e.printStackTrace();
			return getSuccess(false, "反馈异常");
		}
	}

	@Override
	public InterfaceBaseService<Feedback> getService() {
		return feedbackService;
	}
	
}
