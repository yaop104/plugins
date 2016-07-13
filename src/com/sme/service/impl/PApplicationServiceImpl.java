package com.sme.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sme.util.ApkinformatonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sme.core.dao.BaseDao;
import com.sme.core.service.BaseService;
import com.sme.dao.PAppDetailDao;
import com.sme.dao.PApplicationDao;
import com.sme.entity.PAppDetail;
import com.sme.entity.PApplication;
import com.sme.service.PApplicationService;
@Service("pApplicationService")
public class PApplicationServiceImpl extends BaseService<PApplication> implements PApplicationService{
	@Autowired
	private PApplicationDao pApplicationDao;
	@Autowired
	private PAppDetailDao pAppDetailDao;

	@Override
	public BaseDao<PApplication> getDao() {
		return pApplicationDao;
	}

	@Override
	public List<PApplication> pageForList(Map<String, Object> parm) {
		// TODO Auto-generated method stub
		return pApplicationDao.getOperateAppList(parm);
	}

	@Override
	public int countForList(Map<String, Object> parm) {
		// TODO Auto-generated method stub
		return pApplicationDao.getOperateAppSize(parm);
	}

	@Override
	public void offLine(Integer appId) {
		PApplication app = new PApplication();
		app.setpAppId(appId);
		app  = pApplicationDao.getById(app);
		if (app != null) {
			PAppDetail detail = new PAppDetail();
			detail.setpAppdetailApkactionid(appId);
			detail.setpAppdetailAuditstate("5");
			pAppDetailDao.offLineApp(detail);
		}
	}

	@Override
	public List<PApplication> getOrderApp() {
		return pApplicationDao.selectOrderApp();
	}

	@Override
	public void saveOrder(String ids) {
		List<PApplication> list = new ArrayList<PApplication>();
		PApplication p = null;
		int max = 999;
		if (ids != null) {
			for (String id : ids.split("&")) {
				p = new PApplication();
				p.setpAppDisplaysort(max--);
				p.setpAppId(Integer.parseInt(id));
				list.add(p);
			}
			pApplicationDao.batchUpdateAppSort(list);
		}
	}

    @Override
    public PAppDetail hasNewVersion() {
		String packageName = ApkinformatonUtil.PLUGIN_CERTER_PACKAGENAME;
        PAppDetail detail = pAppDetailDao.selectLatestVersion(packageName);
        return detail;
    }

    @Override
    public String getLatestApkName(String packageName) {
        return pAppDetailDao.selectLatestApkName(packageName);
    }

	@Override
	public List<PApplication> selectHot(PApplication pApplication) {
		return pAppDetailDao.selectHot(pApplication);
	}
}
