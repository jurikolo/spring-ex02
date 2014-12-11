package org.talestats.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.talestats.dao.VoteDAO;
import org.talestats.model.Vote;

@Service
@Transactional
public class VoteServiceImpl implements VoteService {

	@Autowired
	private VoteDAO voteDAO;

	@Override
	public void addVote(Vote vote) {
		voteDAO.addVote(vote);
	}

	@Override
	public void updateVote(Vote vote) {
		voteDAO.updateVote(vote);
	}

	@Override
	public Vote getVote(int id) {
		return voteDAO.getVote(id);
	}

	@Override
	public void deleteVote(int id) {
		voteDAO.deleteVote(id);
	}

	@Override
	public List<Vote> getVotes() {
		return voteDAO.getVotes();
	}

	@Override
	public List<Vote> getVotesByCityId(int cityId) {
		return voteDAO.getVotesByCityId(cityId);
	}

}
