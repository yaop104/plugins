package com.sme.entity;

import java.util.Date;

import com.sme.core.model.BaseObject;

public class Feedback extends BaseObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2862320089882689749L;

	private Long id;

    private Long userId;

    private String text;

    private Date creatTime;
    
    private String pic;

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}
}