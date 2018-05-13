package com.sme.view;

import com.sme.core.service.InterfaceBaseService;
import com.sme.core.view.BaseController;
import com.sme.entity.SysAcc;
import com.sme.entity.TsmSendMessage;
import com.sme.service.SysAccService;
import com.sme.service.TsmSendMessageService;
import com.sme.util.StringUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/TsmSendMessage")
public class TsmSendMessageController extends BaseController<TsmSendMessage> {
	@Autowired
	private TsmSendMessageService tsmSendMessageServiceImpl;
	@Autowired
	private SysAccService sysAccServiceImpl;
	
	private Log log = LogFactory.getLog(TsmSendMessageController.class);
	
	@RequestMapping(value="/tsmSendMessagelist", method={RequestMethod.GET})
	public String tsmSendMessageList(TsmSendMessage tsmSendMessage, HttpServletRequest req) {
		try {
			log.info("<=====执行sysmenulist====>");
			List<TsmSendMessage> tsmSendMessages = tsmSendMessageServiceImpl.select(tsmSendMessage);
//			RespUtil.setResp(tsmSendMessages, 10, 1, req);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return "/tsmSendMessage/tsmSendMessagelist";
	}
	
	@RequestMapping(value="/gettsmSendMessagelists", method={RequestMethod.GET})
	@ResponseBody
	public List<TsmSendMessage> tsmSendMessageLists(TsmSendMessage tsmSendMessage, HttpServletRequest req) {
		try {
			log.info("<=====执行sysmenulist====>");
			List<TsmSendMessage> tsmSendMessages = tsmSendMessageServiceImpl.select(tsmSendMessage);
			return tsmSendMessages;
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
		
	}
	
	 @RequestMapping(value="/{id}/delete", method={RequestMethod.GET})
	 public String tsmSendMessageDelete(@PathVariable Integer id){
		 try
		{
			 log.info("<=====执行delete口====>" + id);
			 TsmSendMessage tsmSendMessage = new TsmSendMessage();
//			 tsmSendMessage.setTsmSendMessageId(id);
			tsmSendMessageServiceImpl.delete(tsmSendMessage);
			return "redirect:/tsmSendMessage/tsmSendMessagelist.do";
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return "redirect:/tsmSendMessage/tsmSendMessagelist.do";
		}
	 }
	
	@RequestMapping(value="/save", method={RequestMethod.POST})
	public String tsmSendMessageSave(TsmSendMessage tsmSendMessage, Model model, HttpServletRequest request, HttpServletResponse response){
		
		try
		{
//			if(tsmSendMessage.getTsmSendMessageId()!=null){
				
				return "redirect:/tsmSendMessage/tsmSendMessagelist.do";
//			}else{
//				
//				return "redirect:/tsmSendMessage/tsmSendMessagelist.do";
//			}
			
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return tsmSendMessageAdd(model,tsmSendMessage);
		}
	}
	
	public String tsmSendMessageAdd(Model model, TsmSendMessage tsmSendMessage){
			model.addAttribute("tsmSendMessage", tsmSendMessage);
			return "/tsmSendMessage/tsmSendMessageform";
	}
	
	@RequestMapping("/add")
	public ModelAndView tsmSendMessageAdd(){
		ModelAndView mav = new ModelAndView("/tsmSendMessage/tsmSendMessageform","tsmSendMessage", new TsmSendMessage());
		return mav;
	}
	
	@RequestMapping(value="/{id}/update")
	public String tsmSendMessageUpdate(@PathVariable Integer id, Model model){
		try
		{
			TsmSendMessage tsmSendMessage = new TsmSendMessage();
//			tsmSendMessage.setTsmSendMessageId(id);
			tsmSendMessage = tsmSendMessageServiceImpl.getById(tsmSendMessage);
			return tsmSendMessageAdd(model,tsmSendMessage);
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return "redirect:/tsmSendMessage/tsmSendMessagelist.do";
		}
		
	}
	
	@RequestMapping("/{id}/info")
	public ModelAndView tsmSendMessageInfo(@PathVariable Integer id){
		ModelAndView mav = new ModelAndView("/tsmSendMessage/tsmSendMessageview");
		TsmSendMessage tsmSendMessage = new TsmSendMessage();
//		tsmSendMessage.setTsmSendMessageId(id);
		tsmSendMessage = tsmSendMessageServiceImpl.getById(tsmSendMessage);
		mav.addObject("tsmSendMessage", tsmSendMessage);
		return mav;
	}
	
	@Override
	public InterfaceBaseService getService()
	{
		// TODO Auto-generated method stub
		return tsmSendMessageServiceImpl;
	}
	
	public TsmSendMessageService getTsmSendMessageServiceImpl() {
		return tsmSendMessageServiceImpl;
	}

	public void setTsmSendMessageServiceImpl(TsmSendMessageService tsmSendMessageServiceImpl) {
		this.tsmSendMessageServiceImpl = tsmSendMessageServiceImpl;
	}
	
	//================== begin ======================
//	@RequestMapping(value="/gettsmSendMessageCode")
//	@ResponseBody
//	public MsgUtil gettsmSendMessageCode(TsmSendMessage tsmSendMessage, HttpServletRequest req) {
//		MsgUtil msgUtil = new MsgUtil();
//		try {
//			log.info("<=====执行gettsmSendMessageCode====>");
//			//检查数据完整性
//			if(!StringUtil.isMobileNO(tsmSendMessage.getTsmSendMobile())){
//				msgUtil.setCode(MsgUtil.RESPONSECODE_ERR);
//				msgUtil.setData(null);
//				msgUtil.setMessage("请正确输入手机号码");
//				return msgUtil;
//			}
//
//			SysAcc sysAccphone = new SysAcc();
//			sysAccphone.setSysAccPhone(tsmSendMessage.getTsmSendMobile());
//			List<SysAcc> list = sysAccServiceImpl.select(sysAccphone);
//			if(list.size() > 0 ){
//				if(!list.get(0).getSysAccState().equals("1")){
//					msgUtil.setCode("000003");//账号还在审核中
//					msgUtil.setData(null);
//					msgUtil.setMessage("您的账号还在审核中！请联系0571-26271115");
//					return msgUtil;
//				}
//			}else{
//				msgUtil.setCode(MsgUtil.RESPONSECODE_ERR);
//				msgUtil.setData(null);
//				msgUtil.setMessage("该手机号码未注册");
//				return msgUtil;
//			}
//
//			String code = StringUtil.getRandomNumber(4);
//			tsmSendMessage.setTsmSendCode(code);
//			tsmSendMessage.setTsmSendType("1");
//			String flag = tsmSendMessageServiceImpl.sendMessage(tsmSendMessage);
//
//			if("1".endsWith(flag)){
//				msgUtil.setCode(MsgUtil.RESPONSECODE_SUC);
//				msgUtil.setData(null);
//				msgUtil.setMessage("发送成功");
//				return msgUtil;
//			}else{
//				msgUtil.setCode(MsgUtil.RESPONSECODE_ERR);
//				msgUtil.setData(null);
//				msgUtil.setMessage("发送失败,已达到发送上限次数");
//				return msgUtil;
//			}
//
//		} catch (Exception e) {
//			log.error(e.getMessage());
//			msgUtil.setCode(MsgUtil.RESPONSECODE_SERVER_EXCEPTION);
//			msgUtil.setData(null);
//			msgUtil.setMessage("失败，系统异常");
//			return msgUtil;
//		}
//
//	}
//
//
//	@RequestMapping(value="/getChangePhoneCode")
//	@ResponseBody
//	public MsgUtil getChangePhoneCode(TsmSendMessage tsmSendMessage, HttpServletRequest req) {
//		MsgUtil msgUtil = new MsgUtil();
//		try {
//			log.info("<=====执行getChangePhoneCode====>");
//			//检查数据完整性
//			if(!StringUtil.isMobileNO(tsmSendMessage.getTsmSendMobile())){
//				msgUtil.setCode(MsgUtil.RESPONSECODE_ERR);
//				msgUtil.setData(null);
//				msgUtil.setMessage("请正确输入手机号码");
//				return msgUtil;
//			}
//
//			SysAcc sysAccphone = new SysAcc();
//			sysAccphone.setSysAccPhone(tsmSendMessage.getTsmSendMobile());
//
//			if(!sysAccServiceImpl.getSysAcc(sysAccphone)){
//				msgUtil.setCode(MsgUtil.RESPONSECODE_ERR);
//				msgUtil.setData(null);
//				msgUtil.setMessage("该手机号码未注册");
//				return msgUtil;
//			}
//
//			String code = StringUtil.getRandomNumber(4);
//			tsmSendMessage.setTsmSendCode(code);
//			tsmSendMessage.setTsmSendType("2");
//			String flag = tsmSendMessageServiceImpl.sendMessage(tsmSendMessage);
//
//			if("1".endsWith(flag)){
//				msgUtil.setCode(MsgUtil.RESPONSECODE_SUC);
//				msgUtil.setData(null);
//				msgUtil.setMessage("发送成功");
//				return msgUtil;
//			}else{
//				msgUtil.setCode(MsgUtil.RESPONSECODE_ERR);
//				msgUtil.setData(null);
//				msgUtil.setMessage("发送失败");
//				return msgUtil;
//			}
//
//		} catch (Exception e) {
//			log.error(e.getMessage());
//			msgUtil.setCode(MsgUtil.RESPONSECODE_SERVER_EXCEPTION);
//			msgUtil.setData(null);
//			msgUtil.setMessage("失败，系统异常");
//			return msgUtil;
//		}
//
//	}
	//================== end ======================
}
