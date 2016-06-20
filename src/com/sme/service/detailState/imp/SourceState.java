package com.sme.service.detailState.imp;


import com.sme.dao.PAppDetailDao;
import com.sme.entity.PAppDetail;
import com.sme.service.detailState.Istate;
import com.sme.util.JSONObject;
import com.sme.util.JSONUtil;
import com.sme.util.SpringContextUtil;

public class SourceState implements Istate {
	
//	IDetailDao dDao = DaoFactory.getDetail();
//	
//	IPatchDao pDao = DaoFactory.getPatch();
//	
//	IMessageService service = MessageServiceFactory.getService();
	
	@Override
	public JSONObject testPublish(PAppDetail detail) {
		try {
			
//			if(!dDao.canTestPublis(detail))
//			{
//				return JSONUtil.checkResult(501, "请按顺序测试发布！");
//			}
//			// 跟新数据库
//			dDao.update(detail);
//			
//			if("apk".equals(detail.getPlugintype())) {
//				this.diffPatch(detail);
//			}
//			
//			service.testPublish(detail);
//			
//			// 记录日志
//			LogProxy.log(new ReviewLogBuilder(detail, null, LogBeanInterface.REVIEW_PLUGIN, "测试发布|"));
			return JSONUtil.checkResult(500, "测试发布成功！");
			
		}
		catch(Exception e) {

//			log.error("testPublis throw e",e);
			
			return JSONUtil.checkResult(501, "测试发布失败！");
		}
	}
	
	@Override
	public JSONObject formalPublis(PAppDetail app) {
		return JSONUtil.checkResult(501, "请先测试发布！");
		
	}
	
	@Override
	public JSONObject noPass(PAppDetail detail) {
		
		try {
//			// 跟新数据库
//			dDao.update(detail);
//			
//			// 记录日志
//			LogProxy.log(new ReviewLogBuilder(detail, null, LogBeanInterface.REVIEW_PLUGIN, "审核不通过|"));
			return JSONUtil.checkResult(500,"审核成功！");

		}
		catch(Exception e) {
			
//			log.error("noPass throw e",e);
			
			return JSONUtil.checkResult(501, "审核失败！");

		}
		
	}
	
	@Override
	public JSONObject stop(PAppDetail app) {
		
		return JSONUtil.checkResult(501,"请勿非法操作！");
		
	}
	
	@Override
	public JSONObject start(PAppDetail app) {
		
		return JSONUtil.checkResult(501, "请勿非法操作！");
		
	}
	
	@Override
	public JSONObject del(PAppDetail detail) {
		try {
			
			PAppDetailDao	pAppDetailDao = (PAppDetailDao)SpringContextUtil.getBean("pAppDetailDao");
			pAppDetailDao.delete(detail);
			//后期增加log记录
//			LogProxy.log(new ApplicationLogBuilder(detail, "删除插件|", null));
			return JSONUtil.checkResult(500, "删除成功");

		}
		catch(Exception e) {
			// TODO Auto-generated catch block
			
			return JSONUtil.checkResult(501, "删除插件出错！");
			
		}
	}
	
	/**
	 * @方法名：diffPatch
	 * @描述：(生成差分包，如果报错不生成日志，就当没有生成差分包)
	 * @param detail
	 * @输出：void
	 * @作者：hejh
	 */
	private void diffPatch(PAppDetail detail) {
//		if(Config.DIFF_SWITCH.equals("true")) {
//			
//			try {
//				
//				if(log.isDebugEnabled())
//				{
//					log.debug("disffPatch delete patch");
//				}
//				
//				pDao.deleteExitsPatch(detail.getId());
//			}
//			catch(Exception e) {
//				// TODO Auto-generated catch block
//				log.error("diffPatch deleteExitsPath throw e",e);
//				
//				return;
//			}
//			
//			List<AppDetail> lower_apps = dDao.getLowversionDetails(detail);
//			
//			if(log.isDebugEnabled())
//			{
//				if(lower_apps == null)
//				{
//					log.debug("diffPatch lower_apps is null");					
//				}
//				else
//				{
//					log.debug("diffPatch lower_apps size is " + lower_apps.size());
//				}
//			}
//			
//			if(lower_apps != null && lower_apps.size() > 0) {
//				
//				for(AppDetail oldappversion : lower_apps) {
//					
//					AppPatch patch = DiffAPKUtil.diffutil(detail, oldappversion);
//					
//					if(log.isDebugEnabled())
//					{
//						log.debug("diffPatch patch state is "+patch.getPatchstate());
//					}
//					
//					if(!patch.getPatchstate()) {
//						continue;
//					}
//					
//					patch.setCurrentsize(detail.getApp_size());
//					patch.setCurrentversion(detail.getApp_version());
//					patch.setCurrentversionid(detail.getId());
//					patch.setPatchversionid(oldappversion.getId());
//					patch.setPatchversion(oldappversion.getApp_version());
//					patch.setOldversionsize(oldappversion.getApp_size());
//					patch.setPatchfilepath(patch.getPatchfilepath().replace(Config.FILE_PATH, Config.URL_PREFIX));
//					
//					try {
//						pDao.insert(patch);
//					}
//					catch(Exception e) {
//						// TODO Auto-generated catch block
//						log.error("insert path throw e",e);
//						continue;
//					}
//					
//				}
//			}
//		}
	}
}
