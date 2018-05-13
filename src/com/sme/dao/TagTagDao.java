package com.sme.dao;

import org.mybatis.spring.annotation.Mapper;
import com.sme.core.dao.BaseDao;
import com.sme.entity.TagTag;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper("tagTagDao")
@Repository
public interface TagTagDao extends BaseDao<TagTag> {
    //================== begin ======================

    List<TagTag> selectForTag(TagTag tagTag);

	//================== end ======================
}
