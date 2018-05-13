package com.sme.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sme.core.dao.BaseDao;
import com.sme.core.service.BaseService;
import com.sme.dao.PlgSendDao;
import com.sme.entity.PlgSend;
import com.sme.service.PlgSendService;
import com.sme.util.HttpRequester;
import com.sme.util.HttpResponse;
@Service
public class PlgSendServiceImpl extends BaseService<PlgSend> implements PlgSendService{
	@Autowired
	private PlgSendDao plgSendDao;

	@Override
	public BaseDao<PlgSend> getDao() {
		return plgSendDao;
	}
	public PlgSendDao getPlgSendDao()
	{
		return plgSendDao;
	}
	public void setPlgSendDao(PlgSendDao plgSendDao)
	{
		this.plgSendDao = plgSendDao;
	}
	
	//================== begin ======================
	@Transactional
	public Boolean insertForType(PlgSend plgSend)
	{
		try
		{
			if("1".equals(plgSend.getPsdIssend())){
				
				String url = "http://183.131.13.86:9080/profile/getHotDegreeMobiles.do";
				HttpRequester req = new HttpRequester();
				Map<String, String> map = new HashMap<String, String>();
				HttpResponse res;
				map.put("from", "0");
				map.put("length", plgSend.getPsdAccount());
				res = req.sendPost(url, map);
				plgSend.setPsdAccount(res.getContent());
				plgSend.setPsdIssend("0");
				plgSendDao.insert(plgSend);
				return true;
			}else if("2".equals(plgSend.getPsdIssend())){
				plgSend.setPsdIssend("0");
				plgSendDao.insert(plgSend);
				return true;
			}
			return false;
		}
		catch (Exception e)
		{
			// TODO: handle exception
			return false;
		}
	}
	
	@Override
	public void updateForSend(Integer psdUnid)
	{
		// TODO Auto-generated method stub
		PlgSend plgSend = new PlgSend();
		plgSend.setPsdUnid(psdUnid);
		plgSend.setPsdIssend("1");
		plgSendDao.updateForSend(plgSend);
	}
	
	@Override
	public List<PlgSend> selectForSend(PlgSend pSend)
	{
		// TODO Auto-generated method stub
		return plgSendDao.selectForSend(pSend);
	}
	
	//================== end ======================
	
}
