package com.sme.util;

import java.io.Serializable;

/**
 * 分页的基本参数对象
 */
@SuppressWarnings("serial")
public class PageInfo implements Serializable {

    /**
     * 每页显示记录数
     */
    private Integer pageSize;
    /**
     * 当前页码
     */
    private Integer pageNo;
    /**
     * 总记录数
     */
    private Integer recordCount;

    /**
     * 总页数
     */
    private Integer pageCount;

    /**
     * 排序字符，单个的，不支持多个字段的排序 。etc. plan_name desc
     */
    private String fieldOrder;
    /**
     * 在页面上显示的字段,Flexigrid专用
     */
    private String fields;


    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }



    public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public String getFieldOrder() {
        return fieldOrder;
    }

    public void setFieldOrder(String fieldOrder) {
        this.fieldOrder = fieldOrder;
    }

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    public Integer getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(Integer recordCount) {
        this.recordCount = recordCount;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }
}
