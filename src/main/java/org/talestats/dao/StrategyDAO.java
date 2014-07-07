package org.talestats.dao;

import java.util.List;

import org.talestats.model.Strategy;

public interface StrategyDAO {

    public void addStrategy(Strategy strategy);
    public Strategy getStrategy(int id);
    public void updateStrategy(Strategy strategy);
    public void deleteStrategy(int id);
    public List<Strategy> getStrategies();
    
}
