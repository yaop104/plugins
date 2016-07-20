package com.sme.view;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.sme.core.view.BaseController;
import com.sme.core.service.InterfaceBaseService;
import com.sme.entity.TstStatement;
import com.sme.service.TstStatementService;
import com.sme.util.RespUtil;

@Controller
@RequestMapping("/TstStatement")
public class TstStatementController extends BaseController<TstStatement>{
	@Autowired
	private TstStatementService tstStatementServiceImpl;
	
	private Log log = LogFactory.getLog(TstStatementController.class);
	
	@RequestMapping(value="/tstStatementlist", method={RequestMethod.GET})
	public String tstStatementList(TstStatement tstStatement, HttpServletRequest req) {
		try {
			log.info("<=====执行sysmenulist====>");
			List<TstStatement> tstStatements = tstStatementServiceImpl.select(tstStatement);
//			RespUtil.setResp(tstStatements, 10, 1, req);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return "/tstStatement/tstStatementlist";
	}
	
	@RequestMapping(value="/gettstStatementlists", method={RequestMethod.GET})
	@ResponseBody
	public List<TstStatement> tstStatementLists(TstStatement tstStatement, HttpServletRequest req) {
		try {
			log.info("<=====执行sysmenulist====>");
			List<TstStatement> tstStatements = tstStatementServiceImpl.select(tstStatement);
			return tstStatements;
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
		
	}
	
	 @RequestMapping(value="/{id}/delete", method={RequestMethod.GET})
	 public String tstStatementDelete(@PathVariable Integer id){
		 try
		{
			 log.info("<=====执行delete口====>" + id);
			 TstStatement tstStatement = new TstStatement();
//			 tstStatement.setTstStatementId(id);
			tstStatementServiceImpl.delete(tstStatement);
			return "redirect:/tstStatement/tstStatementlist.do";
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return "redirect:/tstStatement/tstStatementlist.do";
		}
	 }
	
	@RequestMapping(value="/save", method={RequestMethod.POST})
	public String tstStatementSave(TstStatement tstStatement,Model model, HttpServletRequest request, HttpServletResponse response){
		
		try
		{
//			if(tstStatement.getTstStatementId()!=null){
//
//				return "redirect:/tstStatement/tstStatementlist.do";
//			}else{
				
				return "redirect:/tstStatement/tstStatementlist.do";
//			}
			
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return tstStatementAdd(model,tstStatement);
		}
	}
	
	public String tstStatementAdd(Model model,TstStatement tstStatement){
			model.addAttribute("tstStatement", tstStatement);
			return "/tstStatement/tstStatementform";
	}
	
	@RequestMapping("/add")
	public ModelAndView tstStatementAdd(){
		ModelAndView mav = new ModelAndView("/tstStatement/tstStatementform","tstStatement", new TstStatement());
		return mav;
	}
	
	@RequestMapping(value="/{id}/update")
	public String tstStatementUpdate(@PathVariable Integer id, Model model){
		try
		{
			TstStatement tstStatement = new TstStatement();
//			tstStatement.setTstStatementId(id);
			tstStatement = tstStatementServiceImpl.getById(tstStatement);
			return tstStatementAdd(model,tstStatement);
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return "redirect:/tstStatement/tstStatementlist.do";
		}
		
	}
	
	@RequestMapping("/{id}/info")
	public ModelAndView tstStatementInfo(@PathVariable Integer id){
		ModelAndView mav = new ModelAndView("/tstStatement/tstStatementview");
		TstStatement tstStatement = new TstStatement();
//		tstStatement.setTstStatementId(id);
		tstStatement = tstStatementServiceImpl.getById(tstStatement);
		mav.addObject("tstStatement", tstStatement);
		return mav;
	}
	
	@Override
	public InterfaceBaseService getService()
	{
		// TODO Auto-generated method stub
		return tstStatementServiceImpl;
	}
	
	public TstStatementService getTstStatementServiceImpl() {
		return tstStatementServiceImpl;
	}

	public void setTstStatementServiceImpl(TstStatementService tstStatementServiceImpl) {
		this.tstStatementServiceImpl = tstStatementServiceImpl;
	}
	
	//================== begin ======================
 
	//================== end ======================
}
