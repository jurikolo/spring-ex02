package org.talestats.model;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "city")
public class City {

	@Id
	@Column(name = "cityid")
	private int id;

	@Basic
	@Column(name = "name")
	private String name;

	@Basic
	@Column(name = "size")
	private int size;

	@OneToMany(mappedBy = "city")
	private Set<Council> councils;

	@OneToMany(mappedBy = "city")
	private Set<Hero> heroes;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Set<Council> getCouncils() {
		return councils;
	}

	public void setCouncils(Set<Council> councils) {
		this.councils = councils;
	}

	public Set<Hero> getHeroes() {
		return heroes;
	}

	public void setHeroes(Set<Hero> heroes) {
		this.heroes = heroes;
	}

	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", size=" + size
				+ ", councils=" + councils + ", heroes=" + heroes + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((councils == null) ? 0 : councils.hashCode());
		result = prime * result + ((heroes == null) ? 0 : heroes.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + size;
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
		City other = (City) obj;
		if (councils == null) {
			if (other.councils != null)
				return false;
		} else if (!councils.equals(other.councils))
			return false;
		if (heroes == null) {
			if (other.heroes != null)
				return false;
		} else if (!heroes.equals(other.heroes))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (size != other.size)
			return false;
		return true;
	}

}