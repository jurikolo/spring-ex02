package org.talestats.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	@Column(name="keeper")
	private String keeper;

	@Basic
	@Column(name="guildId")
	private int guildId;

	@Basic
	@Column(name="ally")
	private int ally;

	@Basic
	@Column(name="enemy")
	private int enemy;

	@ManyToOne
	@JoinColumn(name="cityid", nullable=false)
	private City city;
	
	public Hero() {}
	
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

	public String getKeeper() {
		return keeper;
	}

	public void setKeeper(String keeper) {
		this.keeper = keeper;
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

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Hero [id=" + id + ", name=" + name + ", keeper=" + keeper + ", guildId=" + guildId + ", ally=" + ally + ", enemy=" + enemy + "]";
	}
}