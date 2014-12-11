package org.talestats.model;

public class HeroExtra {

	private Integer id;
	private City city;
	private Council ally;
	private City allyCity;
	private Council enemy;
	private City enemyCity;
	private Guild guild;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Council getAlly() {
		return ally;
	}

	public void setAlly(Council ally) {
		this.ally = ally;
	}

	public City getAllyCity() {
		return allyCity;
	}

	public void setAllyCity(City allyCity) {
		this.allyCity = allyCity;
	}

	public Council getEnemy() {
		return enemy;
	}

	public void setEnemy(Council enemy) {
		this.enemy = enemy;
	}

	public City getEnemyCity() {
		return enemyCity;
	}

	public void setEnemyCity(City enemyCity) {
		this.enemyCity = enemyCity;
	}

	public Guild getGuild() {
		return guild;
	}

	public void setGuild(Guild guild) {
		this.guild = guild;
	}
}
