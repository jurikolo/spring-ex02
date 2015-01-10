package org.talestats.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hero_history")
public class HeroHistory {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer heroHistoryId;

	@Basic
	@Column(name = "heroid")
	private Integer id;

	@Basic
	@Column(name = "name")
	private String name;

	public HeroHistory() {
	}

	public Integer getHeroHistoryId() {
		return heroHistoryId;
	}

	public void setHeroHistoryId(Integer heroHistoryId) {
		this.heroHistoryId = heroHistoryId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name.trim();
	}

	@Override
	public String toString() {
		return "HeroHistory [heroHistoryId=" + heroHistoryId + ", id=" + id + ", name=" + name + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((heroHistoryId == null) ? 0 : heroHistoryId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		HeroHistory other = (HeroHistory) obj;
		if (heroHistoryId == null) {
			if (other.heroHistoryId != null)
				return false;
		} else if (!heroHistoryId.equals(other.heroHistoryId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}