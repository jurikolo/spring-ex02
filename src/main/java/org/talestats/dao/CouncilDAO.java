package org.talestats.dao;

import java.util.List;

import org.talestats.model.Council;

public interface CouncilDAO {

	public void addCouncil(Council council);

	public Council getCouncil(int id);

	public void updateCouncil(Council council);

	public void deleteCouncil(int id);

	public List<Council> getCouncils();

	public void addOrUpdateCouncil(Council council);

	public void deleteAllCouncils();

}