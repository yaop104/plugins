package com.sme.service.impl;

import com.sme.dao.SysAccDao;
import com.sme.dao.TstStatementDao;
import com.sme.entity.SysAcc;
import com.sme.entity.TstStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sme.core.dao.BaseDao;
import com.sme.core.service.BaseService;
import com.sme.dao.TapApplicationDao;
import com.sme.entity.TapApplication;
import com.sme.service.TapApplicationService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class TapApplicationServiceImpl extends BaseService<TapApplication> implements TapApplicationService{
	@Autowired
	private TapApplicationDao tapApplicationDao;
	@Autowired
	private SysAccDao sysAccDao;
	@Autowired
	private TstStatementDao tstStatementDao;

	@Override
	public BaseDao<TapApplication> getDao() {
		return tapApplicationDao;
	}
	public TapApplicationDao getTapApplicationDao()
	{
		return tapApplicationDao;
	}
	public void setTapApplicationDao(TapApplicationDao tapApplicationDao)
	{
		this.tapApplicationDao = tapApplicationDao;
	}
	
	//================== begin ======================
	@Override
	@Transactional
	public String updateForCheck(TapApplication t) {
		String flag = "0";
		try {
			//判断是否通过
			//通过则需要增加流水,增加 账户余额
			//不通过只需要更新状态
			if("2".endsWith(t.getTapApplicationCheckstate())){
				TapApplication tapApplication  = new TapApplication();
				tapApplication.setTapApplicationUnid(t.getTapApplicationUnid());
				tapApplication = tapApplicationDao.getById(tapApplication);

				SysAcc sysAcc  = new SysAcc();
				sysAcc.setSysAccId(tapApplication.getTapApplicationCuser());
				sysAcc = sysAccDao.getById(sysAcc);

				sysAcc.setSysAccMoney(sysAcc.getSysAccMoney()+tapApplication.getTapApplicationAppname());
				sysAccDao.update(sysAcc);

				TstStatement tstStatement = new TstStatement();
				tstStatement.setTstStatementAccount(Float.valueOf(tapApplication.getTapApplicationAppname()));
				tstStatement.setTstStatementRemainder((float) (sysAcc.getSysAccMoney()));
				tstStatement.setTstStatementCdate(new Date());
				tstStatement.setTstStatementType("1");
				tstStatement.setTstStatementBasicinfoid(tapApplication.getTapApplicationCuser());
				tstStatement.setTstStatementRemark("审核通过，账户充值：" + tapApplication.getTapApplicationAppname());
				tstStatementDao.insert(tstStatement);
			}

			tapApplicationDao.update(t);
			flag = "1";
		}
		catch (Exception e){
			System.out.println(e.getMessage());
		}
		return  flag;
	}

	//================== end ======================
}
