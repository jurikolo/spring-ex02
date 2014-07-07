package org.talestats.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.talestats.dao.CouncilDAO;
import org.talestats.model.Council;

@Service
@Transactional
public class CouncilServiceImpl implements CouncilService {

	@Autowired
	private CouncilDAO councilDAO;

	@Override
	public void addCouncil(Council council) {
		councilDAO.addCouncil(council);
	}

	@Override
	public void updateCouncil(Council council) {
		councilDAO.updateCouncil(council);
	}

	@Override
	public Council getCouncil(int id) {
		return councilDAO.getCouncil(id);
	}

	@Override
	public void deleteCouncil(int id) {
		councilDAO.deleteCouncil(id);
	}

	@Override
	public List<Council> getCouncils() {
		return councilDAO.getCouncils();
	}

}
