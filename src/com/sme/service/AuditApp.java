package com.sme.service;

import com.sme.entity.PAppDetail;

public interface AuditApp {

	/**
	 * 审核app
	 * @param detail
	 */
	public Object audit(PAppDetail detail);
}
