package org.talestats.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.talestats.dao.StrategyDAO;
import org.talestats.model.Strategy;

@Service
@Transactional
public class StrategyServiceImpl implements StrategyService {

	@Autowired
	private StrategyDAO strategyDAO;

	@Override
	public void addStrategy(Strategy strategy) {
		strategyDAO.addStrategy(strategy);
	}

	@Override
	public void updateStrategy(Strategy strategy) {
		strategyDAO.updateStrategy(strategy);
	}

	@Override
	public Strategy getStrategy(int id) {
		return strategyDAO.getStrategy(id);
	}

	@Override
	public void deleteStrategy(int id) {
		strategyDAO.deleteStrategy(id);
	}

	@Override
	public List<Strategy> getStrategies() {
		return strategyDAO.getStrategies();
	}

}
