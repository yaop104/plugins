package com.sme.view;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sme.core.model.StringJSON;
import com.sme.core.service.InterfaceBaseService;
import com.sme.core.spring.Log;
import com.sme.core.view.BaseController;
import com.sme.entity.PlgGroup;
import com.sme.entity.SysAcc;
import com.sme.service.PlgGroupService;
import com.sme.util.RespUtil;

@Controller
@RequestMapping("/PlgGroup")
public class PlgGroupController extends BaseController<PlgGroup>{
	@Autowired
	private PlgGroupService plgGroupServiceImpl;
	
	private Logger log = LoggerFactory.getLogger(PlgGroupController.class);
	
	@RequestMapping(value="/pUserPush", method={RequestMethod.GET})
	public String pUserPush(){
		return "/pUserPush/group";
	}
	
	@RequestMapping(value = "/deleteById")
	@ResponseBody
	@Log(type = "群组管理", desc = "删除群组")
	public StringJSON sysMenuDelete(String ids) {
		try {
			if (ids != null && ids.length() > 0) {
				String[] idStrings = ids.split(",");

				for (String id : idStrings) {
					PlgGroup plgGroup = new PlgGroup();
					plgGroup.setPgpUnid(Integer.valueOf(id));
					plgGroupServiceImpl.delete(plgGroup);
				}

				return getSuccess(true, "删除成功！", null);
			} else {
				return getSuccess(false, "删除内容为空！", null);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			return getSuccess(false, "系统异常");
		}
	}
	
	@RequestMapping(value="/save", method={RequestMethod.POST})
	@ResponseBody
	public StringJSON plgGroupSave(PlgGroup plgGroup, HttpServletRequest request){
		SysAcc session = (SysAcc) request.getSession().getAttribute("loginUser");
		try
		{
			if(plgGroup.getPgpUnid()!=null){
				PlgGroup plgGroup2 = new PlgGroup();
				
				plgGroup2 = plgGroupServiceImpl.getById(plgGroup);
				
				plgGroup2.setPgpName(plgGroup.getPgpName());
				plgGroup2.setPgpOrder(plgGroup.getPgpOrder());
				plgGroup2.setPgpState(plgGroup.getPgpState());
				plgGroup2.setPgpUdate(new Date());
				plgGroup2.setPgpUuser(session.getSysAccName());
				plgGroupServiceImpl.update(plgGroup2);
				
				return getSuccess(true, "修改成功");
			}else{
				plgGroup.setPgpCdate(new Date());
				plgGroup.setPgpCuser(session.getSysAccName());
				plgGroupServiceImpl.insert(plgGroup);
				return getSuccess(true, "添加成功");
			}
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return getSuccess(false, "系统异常");
		}
	}
	
	@RequestMapping("/{id}/info")
	public ModelAndView plgGroupInfo(@PathVariable Integer id){
		ModelAndView mav = new ModelAndView("/plgGroup/plgGroupview");
		PlgGroup plgGroup = new PlgGroup();
//		plgGroup.setPlgGroupId(id);
		plgGroup = plgGroupServiceImpl.getById(plgGroup);
		mav.addObject("plgGroup", plgGroup);
		return mav;
	}

	@Override
	public InterfaceBaseService getService()
	{
		// TODO Auto-generated method stub
		return plgGroupServiceImpl;
	}

	public PlgGroupService getPlgGroupServiceImpl()
	{
		return plgGroupServiceImpl;
	}

	public void setPlgGroupServiceImpl(PlgGroupService plgGroupServiceImpl)
	{
		this.plgGroupServiceImpl = plgGroupServiceImpl;
	}
	
	//================== begin ======================
 
	//================== end ======================
}
