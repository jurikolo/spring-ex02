package org.talestats.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hero")
public class Hero {

	@Id
	@Column(name="heroid")
	private Integer id;
	
	@Basic
	@Column(name="name")
	private String name;

	@Basic
	@Column(name="guildId")
	private int guildId;

	@Basic
	@Column(name="ally")
	private int ally;

	@Basic
	@Column(name="enemy")
	private int enemy;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "Hero [id=" + id + ", name=" + name + ", guildId=" + guildId + ", ally=" + ally + ", enemy=" + enemy + "]";
	}
}