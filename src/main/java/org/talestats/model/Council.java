package org.talestats.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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

	@Basic
	@Column(name = "cityid", nullable = false)
	private int cityId;

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
		this.name = name.trim();
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job.trim();
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race.trim();
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill.trim();
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

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	@Override
	public String toString() {
		return "Council [id=" + id + ", name=" + name + ", job=" + job + ", race=" + race + ", skill=" + skill
				+ ", allies=" + allies + ", enemies=" + enemies + ", influence=" + influence + ", cityId=" + cityId
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + allies;
		result = prime * result + cityId;
		result = prime * result + enemies;
		result = prime * result + id;
		result = prime * result + influence;
		result = prime * result + ((job == null) ? 0 : job.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((race == null) ? 0 : race.hashCode());
		result = prime * result + ((skill == null) ? 0 : skill.hashCode());
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
		if (allies != other.allies)
			return false;
		if (cityId != other.cityId)
			return false;
		if (enemies != other.enemies)
			return false;
		if (id != other.id)
			return false;
		if (influence != other.influence)
			return false;
		if (job == null) {
			if (other.job != null)
				return false;
		} else if (!job.equals(other.job))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (race == null) {
			if (other.race != null)
				return false;
		} else if (!race.equals(other.race))
			return false;
		if (skill == null) {
			if (other.skill != null)
				return false;
		} else if (!skill.equals(other.skill))
			return false;
		return true;
	}
}