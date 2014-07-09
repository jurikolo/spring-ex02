package org.talestats.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "council")
public class Council {

	@Id
	private int id;

	@Basic
	private int cityId;

	@Basic
	private String name;

	@Basic
	private String job;

	@Basic
	private String race;

	@Basic
	private String skill;

	@Basic
	private int allies;

	@Basic
	private int enemies;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public int getCityId() {
		return cityId;
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

	@Override
	public String toString() {
		return "Council [id=" + id + ", cityId=" + cityId + ", name=" + name
				+ ", job=" + job + ", race=" + race + ", skill=" + skill + ", allies="
				+ allies + ", enemies=" + enemies + "]";
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