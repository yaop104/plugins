package com.sme.service.impl;

import com.sme.core.dao.BaseDao;
import com.sme.core.service.BaseService;
import com.sme.dao.SysAccDao;
import com.sme.dao.TodOrderDao;
import com.sme.dao.TstStatementDao;
import com.sme.dao.TtmTimeDao;
import com.sme.entity.*;
import com.sme.service.TodOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class TodOrderServiceImpl extends BaseService<TodOrder> implements TodOrderService{
	@Autowired
	private TodOrderDao todOrderDao;
	@Autowired
	private SysAccDao sysAccDao;
	@Autowired
	private TstStatementDao tstStatementDao;
	@Autowired
	private TtmTimeDao ttmTimeDao;

	@Override
	public BaseDao<TodOrder> getDao() {
		return todOrderDao;
	}
	public TodOrderDao getTodOrderDao()
	{
		return todOrderDao;
	}
	public void setTodOrderDao(TodOrderDao todOrderDao)
	{
		this.todOrderDao = todOrderDao;
	}

	//================== begin ======================

	@Override
	@Transactional
	public String pay(TodOrder t) {
		String flag = "0";
		try{
			TodOrder todOrder =  new TodOrder();
			todOrder.setTodOrderUnid(t.getTodOrderUnid());
			todOrder = todOrderDao.getById(todOrder);
			Integer paymoney = todOrder.getTodOrderPositionprice() * todOrder.getTodOrderTotaldays();

			//判断账户余额
			SysAcc sysAcc =  new SysAcc();
			sysAcc.setSysAccId(todOrder.getTodOrderCustomid());
			sysAcc = sysAccDao.getById(sysAcc);

			if (paymoney > sysAcc.getSysAccMoney()){
				flag = "3";
				return  flag;
			}

			//生成推广时间
			TtmTime ttmTime = new TtmTime();
			ttmTime.setTtmTimeCdate(new Date());
			ttmTime.setTtmTimeCuser(1);
			ttmTime.setTtmTimeOrderid(todOrder.getTodOrderUnid());
			ttmTime.setTtmTimePositionid(todOrder.getOdOrderPackageid());
			ttmTime.setTtmTimeState("1");

			for (int i = todOrder.getTodOrderTotaldays();i>0;i--){
				ttmTime.setTtmTimeTime(getMyDay(i));
				ttmTimeDao.insert(ttmTime);
			}


			//扣除账户金额
			sysAcc.setSysAccMoney(sysAcc.getSysAccMoney()-paymoney);
			sysAccDao.update(sysAcc);

			//增加流水
			TstStatement tstStatement = new TstStatement();
			tstStatement.setTstStatementAccount(Float.valueOf(paymoney));
			tstStatement.setTstStatementRemainder((float) (sysAcc.getSysAccMoney()));
			tstStatement.setTstStatementCdate(new Date());
			tstStatement.setTstStatementType("2");
			tstStatement.setTstStatementOrderid(todOrder.getTodOrderUnid());
			tstStatement.setTstStatementOrdernum(todOrder.getTodOrderOrdernum());
			tstStatement.setTstStatementBasicinfoid(todOrder.getTodOrderCustomid());
			tstStatement.setTstStatementRemark("购买广告，账户消费：" + paymoney);
			tstStatementDao.insert(tstStatement);

			//变更支付状态
			TodOrder todOrder1 = new TodOrder();
			todOrder1.setTodOrderState("1");
			todOrder1.setTodOrderUnid(todOrder.getTodOrderUnid());
			todOrderDao.update(todOrder1);

			flag = "1";
		}catch (Exception e){
			System.out.println(e.getMessage());
			flag = "3";
		}
		return flag;
	}

	@Override
	public List<TodOrder> getImgList() {
		return todOrderDao.getImgList();
	}

	public static Date getMyDay(Integer day){
		Calendar cal   =   Calendar.getInstance();
        cal.add(Calendar.DATE,   +day);
		return cal.getTime();
	}

	//================== end ======================
}
