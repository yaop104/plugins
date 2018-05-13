package com.sme.service;

import com.sme.core.service.InterfaceBaseService;
import com.sme.entity.TodOrder;

import java.util.List;

public interface TodOrderService  extends InterfaceBaseService<TodOrder> {
    //================== begin ======================
    String pay(TodOrder t);

    List<TodOrder> getImgList();

    //================== end ======================
}
