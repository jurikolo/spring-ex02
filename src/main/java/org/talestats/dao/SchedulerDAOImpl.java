package org.talestats.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.talestats.model.Scheduler;

@Repository
public class SchedulerDAOImpl implements SchedulerDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void addScheduler() {
		Session openSession = sessionFactory.openSession();
		Scheduler scheduler = new Scheduler();
		scheduler.setId(1);
		try {
			openSession.beginTransaction();
			openSession.save(scheduler);
			openSession.getTransaction().commit();
		} catch (HibernateException e) {
		}
		openSession.close();
	}
	
	public void deleteScheduler() {
		Session openSession = sessionFactory.openSession();
		openSession.delete(getScheduler());
		openSession.flush();
		openSession.close();
	}

	// TODO fix method to execute correct request to DB instead of looping over
	// vote list
	public Scheduler getScheduler() {
		Session openSession = sessionFactory.openSession();
		Scheduler scheduler = (Scheduler) openSession.createQuery("from Scheduler").uniqueResult();
		openSession.close();
		return scheduler;
	}
}