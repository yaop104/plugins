package com.sme.service;

import com.sme.core.service.InterfaceBaseService;
import com.sme.entity.TatTagApp;

public interface TatTagAppService  extends InterfaceBaseService<TatTagApp>{
    //================== begin ======================
    void insertTagApp(String ids, String hotAppId);

	//================== end ======================
}
