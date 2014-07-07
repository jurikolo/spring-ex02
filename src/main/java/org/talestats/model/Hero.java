package org.talestats.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hero")
public class Hero {

	@Id
	private Integer key;

	@Basic
	private String name;

	@Basic
	private int guildId;

	@Basic
	private int ally;

	@Basic
	private int enemy;

	@Basic
	private String town;

	public Integer getKey() {
		return key;
	}

	public void setKey(Integer key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGuildId() {
		return guildId;
	}

	public void setGuildId(int guildId) {
		this.guildId = guildId;
	}

	public int getAlly() {
		return ally;
	}

	public void setAlly(int ally) {
		this.ally = ally;
	}

	public int getEnemy() {
		return enemy;
	}

	public void setEnemy(int enemy) {
		this.enemy = enemy;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	@Override
	public String toString() {
		return "City [key=" + key + ", name=" + name + "]";
	}
}