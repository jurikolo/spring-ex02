package org.talestats.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.talestats.model.Hero;

@Repository
public class HeroDAOImpl implements HeroDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void addHero(Hero hero) {
		Session openSession = sessionFactory.openSession();
		openSession.save(hero);
		openSession.flush();
		openSession.close();
	}

	public void updateHero(Hero hero) {
		Session openSession = sessionFactory.openSession();
		Hero heroToUpdate = getHero(hero.getId());
		heroToUpdate.setName(hero.getName());
		heroToUpdate.setKeeper(hero.getKeeper());
		openSession.update(heroToUpdate);
		openSession.flush();
		openSession.close();
	}

	// hero list
	public Hero getHero(int id) {
		Session openSession = sessionFactory.openSession();
		List<Hero> heroes = openSession.createQuery("from Hero where heroid =  " + id).list();
		openSession.close();
		for (Hero hero : heroes) {
			if (hero.getId() == id)
				return hero;
		}
		return null;
	}

	public void deleteHero(int id) {
		Session openSession = sessionFactory.openSession();
		Hero hero = getHero(id);
		if (hero != null)
			openSession.delete(hero);
		openSession.flush();
		openSession.close();
	}

	public void addOrUpdateHero(Hero hero) {
		Session openSession = sessionFactory.openSession();
		try {
			openSession.beginTransaction();
			openSession.saveOrUpdate(hero);
			openSession.getTransaction().commit();
			openSession.flush();
		} catch (HibernateException e) {
		}
		openSession.close();
	}

	public void deleteAllHeroes() {
		Session openSession = sessionFactory.openSession();
		Query query = openSession.createQuery("delete from Hero");
		query.executeUpdate();
		openSession.flush();
		openSession.close();
	}

	@SuppressWarnings("unchecked")
	public List<Hero> getHeroes() {
		Session openSession = sessionFactory.openSession();
		List<Hero> heroes = openSession.createQuery("from Hero").list();
		openSession.close();
		return heroes;
	}

	@SuppressWarnings("unchecked")
	public List<Hero> getSubscribedHeroes() {
		Session openSession = sessionFactory.openSession();
		List<Hero> heroes = openSession.createQuery("from Hero where SUBSCRIBER = 1").list();
		openSession.close();
		return heroes;
	}

	@SuppressWarnings("unchecked")
	public List<Hero> getNotSubscribedHeroes() {
		Session openSession = sessionFactory.openSession();
		List<Hero> heroes = openSession.createQuery("from Hero where SUBSCRIBER = 0").list();
		openSession.close();
		return heroes;
	}

	public void unknownToNotSubscribed() {
		Session openSession = sessionFactory.openSession();
		Query query = openSession.createQuery("update Hero set SUBSCRIBER = 0 where SUBSCRIBED = 2");
		query.executeUpdate();
		openSession.flush();
		openSession.close();
	}
}