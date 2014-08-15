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
import org.talestats.model.Vote;

@Repository
public class VoteDAOImpl implements VoteDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void addVote(Vote vote) {
		Session openSession = sessionFactory.openSession();
		try {
			openSession.beginTransaction();
			openSession.save(vote);
			openSession.getTransaction().commit();
		} catch (HibernateException e) {
		}
		openSession.close();
	}

	public void updateVote(Vote vote) {
		Session openSession = sessionFactory.openSession();
		Vote voteToUpdate = getVote(vote.getId());
		openSession.update(voteToUpdate);
		openSession.flush();
		openSession.close();
	}

	// TODO fix method to execute correct request to DB instead of looping over
	// vote list
	public Vote getVote(int id) {
		Session openSession = sessionFactory.openSession();
		List<Vote> votes = openSession.createQuery("from Vote").list();
		openSession.close();
		for (Vote vote : votes) {
			if (vote.getId() == id)
				return vote;
		}
		return null;
	}

	public void deleteVote(int id) {
		Session openSession = sessionFactory.openSession();
		Vote vote = getVote(id);
		if (vote != null)
			openSession.delete(vote);
		openSession.flush();
		openSession.close();
	}

	public void addOrUpdateVote(Vote vote) {
		Session openSession = sessionFactory.openSession();
		try {
			openSession.beginTransaction();
			openSession.saveOrUpdate(vote);
			openSession.getTransaction().commit();
		} catch (HibernateException e) {
		}
		openSession.close();
	}

	public void deleteAllVotes() {
		Session openSession = sessionFactory.openSession();
		Query query = openSession.createQuery("delete from Vote");
		query.executeUpdate();
		openSession.flush();
		openSession.close();
	}

	@SuppressWarnings("unchecked")
	public List<Vote> getVotes() {
		Session openSession = sessionFactory.openSession();
		List<Vote> votes = openSession.createQuery("from Vote").list();
		openSession.flush();
		openSession.close();
		return votes;
	}

	@SuppressWarnings("unchecked")
	public List<Vote> getVotesByCityId(int cityId) {
		Session openSession = sessionFactory.openSession();
		Query query = openSession
				.createQuery("from Vote where cityid = :cityid");
		query.setInteger("cityid", cityId);
		List<Vote> votes = query.list();
		openSession.close();
		return votes;
	}

}