package com.sme.service.impl;

import java.util.List;
import java.util.Map;

import com.sme.core.dao.BaseDao;
import com.sme.core.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sme.dao.FeedbackDao;
import com.sme.entity.Feedback;
import com.sme.service.FeedbackService;

@Service("feedbackService")
public class FeedbackServiceImpl extends BaseService<Feedback> implements FeedbackService {

	@Autowired
	public FeedbackDao feedbackDao;

	@Override
	public BaseDao<Feedback> getDao() {
		return feedbackDao;
	}
}
