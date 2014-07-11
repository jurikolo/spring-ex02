package org.talestats.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "council")
public class Council {

	@Id
	@Column(name = "councilid")
	private int id;

	@Basic
	@Column(name = "name")
	private String name;

	@Basic
	@Column(name = "job")
	private String job;

	@Basic
	@Column(name = "race")
	private String race;

	@Basic
	@Column(name = "skill")
	private String skill;

	@Basic
	@Column(name = "allies")
	private int allies;

	@Basic
	@Column(name = "enemies")
	private int enemies;

	@Basic
	@Column(name = "influence")
	private int influence;

	@ManyToOne
	@JoinColumn(name = "cityid", nullable = false)
	private City city;

	public Council() {
	}

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

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public int getAllies() {
		return allies;
	}

	public void setAllies(int allies) {
		this.allies = allies;
	}

	public int getEnemies() {
		return enemies;
	}

	public void setEnemies(int enemies) {
		this.enemies = enemies;
	}

	public int getInfluence() {
		return influence;
	}

	public void setInfluence(int influence) {
		this.influence = influence;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Council [id=" + id + ", name=" + name + ", job=" + job
				+ ", race=" + race + ", skill=" + skill + ", allies=" + allies
				+ ", enemies=" + enemies + ", influence=" + influence
				+ ", city=" + city + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Council other = (Council) obj;
		if (id != other.id)
			return false;
		return true;
	}
}