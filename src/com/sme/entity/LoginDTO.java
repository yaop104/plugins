package com.sme.entity;

import com.sme.core.model.BaseObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginDTO extends BaseObject{

	private String account;
	private String password;
	private String accToken;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccToken() {
		return accToken;
	}

	public void setAccToken(String accToken) {
		this.accToken = accToken;
	}
}
