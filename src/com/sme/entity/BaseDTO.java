package com.sme.entity;

import com.sme.core.model.BaseObject;

/**
 * Created by yaoping on 2018/5/19.
 */
public class BaseDTO extends BaseObject{
    // 分页参数
    protected int rows;// ，每页记录数
    protected int page;// 当前页码

    /** token */
    private String	token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getBegin() {
        return rows * (page - 1);
    }

    public int getEnd() {
        return rows;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
