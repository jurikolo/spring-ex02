package org.talestats.dao;

import java.util.List;

import org.talestats.model.HeroHistory;

public interface HeroHistoryDAO {

	public void addHeroHistory(HeroHistory heroHistory);

	public List<HeroHistory> getHeroHistory(int id);

}