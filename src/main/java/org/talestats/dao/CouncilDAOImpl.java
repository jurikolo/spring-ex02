package org.talestats.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.talestats.model.Council;

@Repository
public class CouncilDAOImpl implements CouncilDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public void addCouncil(Council council) {
		getCurrentSession().save(council);
	}

	public void updateCouncil(Council council) {
		Council councilToUpdate = getCouncil(council.getId());
		councilToUpdate.setName(council.getName());
		councilToUpdate.setName(council.getName());
		councilToUpdate.setJob(council.getJob());
		councilToUpdate.setRace(council.getRace());
		councilToUpdate.setSkill(council.getSkill());
		councilToUpdate.setAllies(council.getAllies());
		councilToUpdate.setEnemies(council.getEnemies());
		getCurrentSession().update(councilToUpdate);
	}

	public Council getCouncil(int id) {
		Council council = (Council) getCurrentSession().get(Council.class, id);
		return council;
	}

	public void deleteCouncil(int id) {
		Council council = getCouncil(id);
		if (council != null)
			getCurrentSession().delete(council);
	}
	
	public void addOrUpdateCouncil(Council council) {
		Session openSession = sessionFactory.openSession();
		openSession.saveOrUpdate(council);
		openSession.flush();
	}

	@SuppressWarnings("unchecked")
	public List<Council> getCouncils() {
		return getCurrentSession().createQuery("from Council").list();
	}

}
