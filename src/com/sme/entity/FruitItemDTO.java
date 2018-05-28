package com.sme.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * item(FruitItem)模型对象
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FruitItemDTO extends FruitItem {
	//======================字段列表========================
	/** 个数 */
	private Integer	itemNumber;

	public Integer getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(Integer itemNumber) {
		this.itemNumber = itemNumber;
	}

//================== begin ======================

	//================== end ======================
}
