package org.talestats.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.talestats.model.HeroHistory;

@Repository
public class HeroHistoryDAOImpl implements HeroHistoryDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void addHeroHistory(HeroHistory heroHistory) {
		Session openSession = sessionFactory.openSession();
		openSession.save(heroHistory);
		openSession.flush();
		openSession.close();
	}

	public List<HeroHistory> getHeroHistory(int id) {
		Session openSession = sessionFactory.openSession();
		List<HeroHistory> heroHistory = openSession.createQuery("FROM HeroHistory where heroid = " + id).list();
		openSession.close();
		return heroHistory;
	}
}