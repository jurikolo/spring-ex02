package org.talestats.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.talestats.model.City;
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

	//TODO fix method to execute correct request to DB instead of looping over hero list
	public Hero getHero(int id) {
		Session openSession = sessionFactory.openSession();
		List<Hero> heroes = openSession.createQuery("from Hero").list();
		openSession.close();
		for (Hero hero: heroes) {
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
		//Hero hero = (Hero) criteria.uniqueResult();
		
			openSession.saveOrUpdate(hero);
			openSession.getTransaction().commit();
		} catch (HibernateException e) { }
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

}
