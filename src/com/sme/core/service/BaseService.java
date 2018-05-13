package com.sme.core.service;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.sme.core.dao.BaseDao;



@Transactional
public abstract class BaseService <T extends Serializable>{

	 	public abstract BaseDao<T> getDao();
	 
	 	public void insert(T t){
	 		getDao().insert(t);
	 		
	 	};
	 	
		public void delete(T t){
			getDao().delete(t);
		}
	 	
		public void update(T t){
			getDao().update(t);
		}
		
		@Transactional(readOnly = true)  
		public List<T> select(T t){
			return getDao().select(t);
		}
		public T getById(T t){
			return getDao().getById(t);
		}
		
		@Transactional(readOnly = true)  
		public List<T> page(Map<String, Object> parm){
			return getDao().page(parm);
		}
		@Transactional(readOnly = true)  
		public Integer count(Map<String, Object> parm){
			return getDao().count(parm);
		}

}