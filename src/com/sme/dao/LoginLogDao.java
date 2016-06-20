package com.sme.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.annotation.Mapper;
import org.springframework.stereotype.Repository;

import com.sme.entity.LoginLog;

@Mapper("loginLogDao")
@Repository
public interface LoginLogDao {
	
	void insert(LoginLog t) throws SQLException;
	
	List<LoginLog> page(Map<String, Object> map) throws SQLException;
	
	int count(Map<String, Object> map) throws SQLException;
	
	void batchDelete(List<Integer> list) throws SQLException;

}
