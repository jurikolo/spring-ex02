package org.talestats.service;

import java.util.List;

import org.talestats.model.Strategy;

public interface StrategyService {

    public void addStrategy(Strategy strategy);
    public Strategy getStrategy(int id);
    public void updateStrategy(Strategy strategy);
    public void deleteStrategy(int id);
    public List<Strategy> getStrategies();

}
