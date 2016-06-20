package com.sme.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sme.core.service.InterfaceBaseService;
import com.sme.core.view.BaseController;
import com.sme.entity.PlgUser;
import com.sme.service.PlgUserService;

@Controller
@RequestMapping("/PlgUser")
public class PlgUserController extends BaseController<PlgUser>{
	@Autowired
	private PlgUserService plgUserServiceImpl;
	
	@RequestMapping(value="/user", method={RequestMethod.GET})
	public String PlgUser(){
		return "/pUserPush/user";
	}
	
	@RequestMapping("/{id}/info")
	public ModelAndView plgUserInfo(@PathVariable Integer id){
		ModelAndView mav = new ModelAndView("/plgUser/plgUserview");
		PlgUser plgUser = new PlgUser();
//		plgUser.setPlgUserId(id);
		plgUser = plgUserServiceImpl.getById(plgUser);
		mav.addObject("plgUser", plgUser);
		return mav;
	}
	
	@Override
	public InterfaceBaseService getService()
	{
		// TODO Auto-generated method stub
		return plgUserServiceImpl;
	}

	public PlgUserService getPlgUserServiceImpl()
	{
		return plgUserServiceImpl;
	}

	public void setPlgUserServiceImpl(PlgUserService plgUserServiceImpl)
	{
		this.plgUserServiceImpl = plgUserServiceImpl;
	}
	
	//================== begin ======================
 
	//================== end ======================
}
