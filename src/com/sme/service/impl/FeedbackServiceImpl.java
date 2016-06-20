package com.sme.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sme.dao.FeedbackDao;
import com.sme.entity.Feedback;
import com.sme.service.FeedbackService;

@Service("feedbackService")
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	public FeedbackDao feedbackDao;
	
	@Override
	public void insert(Feedback record){
		feedbackDao.insert(record);
	}

	@Override
	public void delete(Feedback t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Feedback t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Feedback> select(Feedback t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Feedback getById(Feedback t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Feedback> page(Map<String, Object> parm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer count(Map<String, Object> parm) {
		// TODO Auto-generated method stub
		return null;
	}
}
