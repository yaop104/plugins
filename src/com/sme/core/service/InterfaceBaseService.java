package com.sme.core.service;


import java.io.Serializable;
import java.util.List;
import java.util.Map;
/**
 * 基本service
 * 提供常用的crud方法
 * @author haoy
 *
 * @param <T>
 */
public interface InterfaceBaseService<T extends Serializable> {
	 
		/**
		 * 新增
		 * @param t
		 */
	 	public void insert(T t);
	 	
	 	/**
	 	 * 删除
	 	 * @param t
	 	 */
		public void delete(T t);
	 	
		/**
		 * 修改
		 * @param t
		 */
		public void update(T t);
		
		/**
		 * 查询
		 * @param t
		 * @return list对象
		 */
		public List<T> select(T t);
		
		/**
		 * 根据id查找
		 * @param t
		 * @return
		 */
		public T getById(T t);
		
		/**
		 * 带分页的查询
		 * @param parm
		 * @return
		 */
		public List<T> page(Map<String, Object> parm);
		 
		/**
		 * 查询数目
		 * @param parm
		 * @return
		 */
		public Integer count(Map<String, Object> parm);

}