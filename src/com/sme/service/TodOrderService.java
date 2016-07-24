package com.sme.service;

import com.sme.core.service.InterfaceBaseService;
import com.sme.entity.TodOrder;

public interface TodOrderService  extends InterfaceBaseService<TodOrder> {
    //================== begin ======================
    String pay(TodOrder t);

	//================== end ======================
}
