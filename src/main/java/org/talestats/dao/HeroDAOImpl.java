package org.talestats.dao;

import java.util.List;

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

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public void addHero(Hero hero) {
		getCurrentSession().save(hero);
	}

	public void updateHero(Hero hero) {
		Hero heroToUpdate = getHero(hero.getId());
		heroToUpdate.setName(hero.getName());
		heroToUpdate.setGuildId(hero.getGuildId());
		heroToUpdate.setAlly(hero.getAlly());
		heroToUpdate.setEnemy(hero.getEnemy());
		getCurrentSession().update(heroToUpdate);
	}

	public Hero getHero(int id) {
		Hero hero = (Hero) getCurrentSession().get(Hero.class, id);
		return hero;
	}

	public void deleteHero(int id) {
		Hero hero = getHero(id);
		if (hero != null)
			getCurrentSession().delete(hero);
	}
	
	public void addOrUpdateHero(Hero hero) {
		Session openSession = sessionFactory.openSession();
		openSession.saveOrUpdate(hero);
		openSession.flush();
	}
	
	public void deleteAllHeroes() {
		Session openSession = sessionFactory.openSession();
		Query query = openSession.createQuery("delete from Hero"); 
		query.executeUpdate();		
		openSession.flush();
	}

	@SuppressWarnings("unchecked")
	public List<Hero> getHeroes() {
		return getCurrentSession().createQuery("from Hero").list();
	}

}
