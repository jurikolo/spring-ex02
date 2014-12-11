package org.talestats.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.talestats.dao.HeroDAO;
import org.talestats.model.Hero;

@Service
@Transactional
public class HeroServiceImpl implements HeroService {

	@Autowired
	private HeroDAO heroDAO;

	@Override
	public void addHero(Hero hero) {
		heroDAO.addHero(hero);
	}

	@Override
	public void updateHero(Hero hero) {
		heroDAO.updateHero(hero);
	}

	@Override
	public Hero getHero(int id) {
		return heroDAO.getHero(id);
	}

	@Override
	public void deleteHero(int id) {
		heroDAO.deleteHero(id);
	}

	@Override
	public List<Hero> getHeroes() {
		return heroDAO.getHeroes();
	}

	@Override
	public List<Hero> getSubscribedHeroes() {
		return heroDAO.getSubscribedHeroes();
	}

	@Override
	public List<Hero> getNotSubscribedHeroes() {
		return heroDAO.getNotSubscribedHeroes();
	}

}