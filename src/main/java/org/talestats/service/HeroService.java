package org.talestats.service;

import java.util.List;

import org.talestats.model.Hero;

public interface HeroService {

    public void addHero(Hero hero);
    public Hero getHero(int id);
    public void updateHero(Hero hero);
    public void deleteHero(int id);
    public List<Hero> getHeroes();
    public List<Hero> getSubscribedHeroes();
    public List<Hero> getNotSubscribedHeroes();

}