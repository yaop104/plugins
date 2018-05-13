package com.sme.service.impl;

import com.sme.core.dao.BaseDao;
import com.sme.core.service.BaseService;
import com.sme.dao.TagTagDao;
import com.sme.entity.TagTag;
import com.sme.entity.TdcDictionary;
import com.sme.service.TagTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagTagServiceImpl extends BaseService<TagTag> implements TagTagService{
	@Autowired
	private TagTagDao tagTagDao;

	@Override
	public BaseDao<TagTag> getDao() {
		return tagTagDao;
	}
	public TagTagDao getTagTagDao()
	{
		return tagTagDao;
	}
	public void setTagTagDao(TagTagDao tagTagDao)
	{
		this.tagTagDao = tagTagDao;
	}

	@Override
	public Boolean getTagTag(TagTag tagTag) {
		List<TagTag> list = new ArrayList<TagTag>();
		list = tagTagDao.selectForTag(tagTag);
		if (list.size() > 0) {
			tagTag = list.get(0);
		} else {
			tagTag = null;
		}
		return tagTag == null ? false : true;
	}

	//================== begin ======================

	//================== end ======================
}
