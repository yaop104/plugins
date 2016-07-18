package com.sme.dao;

import com.sme.core.dao.BaseDao;
import org.mybatis.spring.annotation.Mapper;
import org.springframework.stereotype.Repository;

import com.sme.entity.Feedback;

@Mapper("feedbackDao")
@Repository
public interface FeedbackDao extends BaseDao<Feedback>{

}