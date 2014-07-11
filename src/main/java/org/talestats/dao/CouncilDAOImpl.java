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

	public void addCouncil(Council council) {
		Session openSession = sessionFactory.openSession();
		openSession.save(council);
		openSession.flush();
	}

	public void updateCouncil(Council council) {
		Session openSession = sessionFactory.openSession();
		Council councilToUpdate = getCouncil(council.getId());
		councilToUpdate.setName(council.getName());
		councilToUpdate.setJob(council.getJob());
		councilToUpdate.setRace(council.getRace());
		councilToUpdate.setSkill(council.getSkill());
		councilToUpdate.setAllies(council.getAllies());
		councilToUpdate.setEnemies(council.getEnemies());
		councilToUpdate.setInfluence(council.getInfluence());
		openSession.update(councilToUpdate);
		openSession.flush();
	}

	public Council getCouncil(int id) {
		Session openSession = sessionFactory.openSession();
		Council council = (Council) openSession.get(Council.class, id);
		openSession.flush();
		return council;
	}

	public void deleteCouncil(int id) {
		Session openSession = sessionFactory.openSession();
		Council council = getCouncil(id);
		if (council != null)
			openSession.delete(council);
		openSession.flush();
	}
	
	public void addOrUpdateCouncil(Council council) {
		Session openSession = sessionFactory.openSession();
		openSession.saveOrUpdate(council);
		openSession.flush();
	}

	@SuppressWarnings("unchecked")
	public List<Council> getCouncils() {
		Session openSession = sessionFactory.openSession();
		return openSession.createQuery("from Council").list();
	}

}
