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
	@Column(name = "heroid")
	private Integer id;

	@Basic
	@Column(name = "name")
	private String name;

	@Basic
	@Column(name = "keeper")
	private String keeper;

	@Basic
	@Column(name = "ally")
	private int allyId;
	
	@Basic
	@Column(name = "enemy")
	private int enemyId;
	
	@Basic
	@Column(name = "cityid")
	private int cityId;
	
	@Basic
	@Column(name = "guildid")
	private int guildId;

	public Hero() {
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

	public String getKeeper() {
		return keeper;
	}

	public void setKeeper(String keeper) {
		this.keeper = keeper.trim();
	}
	
	public int getAllyId() {
		return allyId;
	}

	public void setAllyId(int allyId) {
		this.allyId = allyId;
	}

	public int getEnemyId() {
		return enemyId;
	}

	public void setEnemyId(int enemyId) {
		this.enemyId = enemyId;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public int getGuildId() {
		return guildId;
	}

	public void setGuildId(int guildId) {
		this.guildId = guildId;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Hero [id=" + id + ", name=" + name + ", keeper=" + keeper
				+ ", allyId=" + allyId + ", enemyId=" + enemyId + ", cityId="
				+ cityId + ", guildId=" + guildId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + allyId;
		result = prime * result + cityId;
		result = prime * result + enemyId;
		result = prime * result + guildId;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((keeper == null) ? 0 : keeper.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Hero other = (Hero) obj;
		if (allyId != other.allyId)
			return false;
		if (cityId != other.cityId)
			return false;
		if (enemyId != other.enemyId)
			return false;
		if (guildId != other.guildId)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (keeper == null) {
			if (other.keeper != null)
				return false;
		} else if (!keeper.equals(other.keeper))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}