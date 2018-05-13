package com.sme.core.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sme.entity.SysAcc;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sme.core.model.BaseObject;
import com.sme.core.model.StringJSON;
import com.sme.core.service.InterfaceBaseService;
import com.sme.util.RespUtil;

public abstract class BaseController<T extends BaseObject> {
	// 分页参数
	protected int rows;// ，每页记录数
	protected int page;// 当前页码

	protected int getBegin() {
		return rows * (page - 1);
	}

	protected int getEnd() {
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

	/**
	 * 字符串转换成JSON
	 * 
	 * @param isSuccess
	 * @param message
	 * @return
	 */
	protected StringJSON getSuccess(boolean isSuccess, String message, Long id) {
		StringJSON json = new StringJSON();
		json.setSuccess(isSuccess);
		json.setMessage(message);
		json.setId(id);
		return json;
	}

	protected StringJSON getSuccess(boolean isSuccess, String message) {
		StringJSON json = new StringJSON();
		json.setSuccess(isSuccess);
		json.setMessage(message);
		return json;
	}

	public SysAcc getLoginUser(HttpServletRequest request){
		SysAcc sysAcc = (SysAcc) request.getSession().getAttribute("loginUser");
		return sysAcc;
	}

	protected long id;

	public abstract InterfaceBaseService<T> getService();

	@RequestMapping(value = "/insert", method = { RequestMethod.POST })
	@ResponseBody
	public StringJSON insert(T t) {
		try {
			getService().insert(t);
			return getSuccess(true, "新增成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return getSuccess(false, "系统异常！");
		}
	}

	@RequestMapping(value = "/update", method = { RequestMethod.POST })
	@ResponseBody
	public StringJSON update(T t) {
		try {
			getService().update(t);
			return getSuccess(true, "更新成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return getSuccess(false, "系统异常！");
		}
	}

	@RequestMapping(value = "/delete", method = { RequestMethod.POST })
	@ResponseBody
	public StringJSON delete(T t) {
		try {
			getService().delete(t);
			return getSuccess(true, "删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return getSuccess(false, "系统异常！");
		}
	}

	@RequestMapping(value = "/select", method = { RequestMethod.POST })
	@ResponseBody
	public List<T> select(T t) {
		List<T> objs = new ArrayList<T>();
		try {
			objs = getService().select(t);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return objs;
	}

	@RequestMapping(value = "/getById", method = { RequestMethod.POST })
	@ResponseBody
	public T getById(T t) {
		T obj = null;
		try {
			obj = (T) getService().getById(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	@RequestMapping(value = "/page", method = { RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> page(HttpServletRequest req) {
		// 分页属性
		if (req.getParameter("rows") != null && req.getParameter("page") != null) {
			rows = Integer.parseInt(req.getParameter("rows"));
			page = Integer.parseInt(req.getParameter("page"));
		} else {
			rows = 15;
			page = 1;
		}
		try {
			Map<String, Object> parm = new HashMap<String, Object>();
			parm.put("page", getBegin());
			parm.put("pageCount", getEnd());
			int count = getService().count(parm);
			List<T> sysAccs = getService().page(parm);
			return RespUtil.pageResult(count, sysAccs);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
