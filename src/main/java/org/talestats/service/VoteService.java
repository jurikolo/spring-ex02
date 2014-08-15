package org.talestats.service;

import java.util.List;

import org.talestats.model.Vote;

public interface VoteService {

    public void addVote(Vote vote);
    public Vote getVote(int id);
    public void updateVote(Vote vote);
    public void deleteVote(int id);
    public List<Vote> getVotes();
    public List<Vote> getVotesByCityId(int cityId);

}
