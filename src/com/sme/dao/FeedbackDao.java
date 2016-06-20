package com.sme.dao;

import org.mybatis.spring.annotation.Mapper;
import org.springframework.stereotype.Repository;

import com.sme.entity.Feedback;

@Mapper("feedbackDao")
@Repository
public interface FeedbackDao {
	
    void insert(Feedback record);

}