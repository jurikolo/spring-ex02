package org.talestats.dao;

import java.util.List;

import org.talestats.model.Hero;

public interface HeroDAO {

    public void addHero(Hero hero);
    public Hero getHero(int id);
    public void updateHero(Hero hero);
    public void deleteHero(int id);
    public List<Hero> getHeroes();
    
}