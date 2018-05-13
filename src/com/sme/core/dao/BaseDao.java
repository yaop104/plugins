package com.sme.core.dao;

import java.util.List;
import java.util.Map;


public interface BaseDao<T> {
	void insert(T t);
	void delete(T t);
	void update(T t);
	
	
	List<T> select(T t);
	T getById (T t);
	List<T> page(Map<String, Object> parm);
	Integer count(Map<String, Object> parm);
}
