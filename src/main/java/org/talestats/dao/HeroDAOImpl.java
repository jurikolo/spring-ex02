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

	public void addHero(Hero hero) {
		Session openSession = sessionFactory.openSession();
		openSession.save(hero);
		openSession.flush();
	}

	public void updateHero(Hero hero) {
		Session openSession = sessionFactory.openSession();
		Hero heroToUpdate = getHero(hero.getId());
		heroToUpdate.setName(hero.getName());
		heroToUpdate.setGuildId(hero.getGuildId());
		heroToUpdate.setAlly(hero.getAlly());
		heroToUpdate.setEnemy(hero.getEnemy());
		heroToUpdate.setKeeper(hero.getKeeper());
		openSession.update(heroToUpdate);
		openSession.flush();
	}

	public Hero getHero(int id) {
		Session openSession = sessionFactory.openSession();
		Hero hero = (Hero) openSession.get(Hero.class, id);
		return hero;
	}

	public void deleteHero(int id) {
		Session openSession = sessionFactory.openSession();
		Hero hero = getHero(id);
		if (hero != null)
			openSession.delete(hero);
		openSession.flush();
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
		Session openSession = sessionFactory.openSession();
		return openSession.createQuery("from Hero").list();
	}

}
