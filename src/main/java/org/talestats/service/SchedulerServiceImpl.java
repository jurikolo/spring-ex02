package org.talestats.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.talestats.dao.SchedulerDAO;
import org.talestats.model.Scheduler;

@Service
@Transactional
public class SchedulerServiceImpl implements SchedulerService {

	@Autowired
	private SchedulerDAO schedulerDao;

	@Override
	public void addScheduler() {
		schedulerDao.addScheduler();
	}

	@Override
	public Scheduler getScheduler() {
		return schedulerDao.getScheduler();
	}

	@Override
	public void deleteScheduler() {
		schedulerDao.deleteScheduler();
	}

}
