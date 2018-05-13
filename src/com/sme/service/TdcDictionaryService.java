package com.sme.service;

import com.sme.core.service.InterfaceBaseService;
import com.sme.entity.TdcDictionary;

public interface TdcDictionaryService  extends InterfaceBaseService<TdcDictionary>{
    //================== begin ======================
    Boolean getDictionary(TdcDictionary tdcDictionary);

	//================== end ======================
}
