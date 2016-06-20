package com.sme.service.impl;

import java.util.List;

import com.sme.dao.PAppDetailDao;
import com.sme.dao.PAppPatchDao;
import com.sme.entity.PAppDetail;
import com.sme.entity.PAppPatch;
import com.sme.service.AuditApp;
import com.sme.util.DifApkUtil;
import com.sme.util.RespMessage;

public class AppTestAudit implements AuditApp {

	private PAppDetailDao pAppDetailDao;

	private PAppPatchDao pAppPatchDao;

	public AppTestAudit() {
		super();
	}

	public AppTestAudit(PAppDetailDao pAppDetailDao, PAppPatchDao pAppPatchDao) {
		super();
		this.pAppDetailDao = pAppDetailDao;
		this.pAppPatchDao = pAppPatchDao;
	}

	@Override
	public Object audit(PAppDetail detail) {
		RespMessage msg = new RespMessage();
		try {
			if (pAppDetailDao.canTestPublish(detail) != 0) {
				msg.setCode("400");
				msg.setMessage("请按顺序测试发布！");
				return msg;
			}

			detail = pAppDetailDao.getById(detail);
			pAppDetailDao.updateState(detail);

			if ("0".equals(detail.getpAppdetailPlugintype())) {
				diffPatch(detail);
			}

			msg.setCode("500");
			msg.setMessage("测试发布成功！");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setCode("300");
			msg.setMessage("出现异常");
		}
		
		return msg;
	}

	/**
	 * 生成差分包
	 * 
	 * @param detail
	 */
	private void diffPatch(PAppDetail detail) {
		pAppPatchDao.deletePatch(detail.getpAppdetailId());

		List<PAppDetail> lowers = pAppDetailDao.selectLowerVersionApk(detail);

		if (lowers != null && lowers.size() > 0) {
			for (PAppDetail e : lowers) {
				PAppPatch patch = DifApkUtil.diff(detail, e);

				patch.setpPatchCurrentsize(detail.getpAppdetailSize());
				patch.setpPatchCurrentversion(detail.getpAppdetailVersion());
				patch.setpPatchCurrentversionid(detail.getpAppdetailId());
				patch.setpPatchPatchversionid(e.getpAppdetailId());
				patch.setpPatchPatchversion(e.getpAppdetailVersion());
				patch.setpPatchCurrentsize(e.getpAppdetailSize());

				pAppPatchDao.insert(patch);

			}
		}
	}

}
