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
	@Column(name = "heroid")
	private Integer id;

	@Basic
	@Column(name = "name")
	private String name;

	@Basic
	@Column(name = "keeper")
	private String keeper;

	@Basic
	@Column(name = "guildId")
	private int guildId;

	@Basic
	@Column(name = "ally")
	private int ally;

	@Basic
	@Column(name = "enemy")
	private int enemy;

	@ManyToOne
	@JoinColumn(name = "cityid", nullable = false)
	private City city;

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
		return "Hero [id=" + id + ", name=" + name + ", keeper=" + keeper
				+ ", guildId=" + guildId + ", ally=" + ally + ", enemy="
				+ enemy + ", city=" + city + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ally;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + enemy;
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
		if (ally != other.ally)
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (enemy != other.enemy)
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