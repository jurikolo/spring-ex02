package org.talestats.model;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "guild")
public class Guild {

	@Id
	@Column(name = "guildId")
	private int id;

	@Basic
	@Column(name = "name")
	private String name;

	@Basic
	@Column(name = "size")
	private int size;

	@OneToMany(mappedBy = "guild")
	private Set<Hero> heroes;

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

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Set<Hero> getHeroes() {
		return heroes;
	}

	public void setHeroes(Set<Hero> heroes) {
		this.heroes = heroes;
	}

	@Override
	public String toString() {
		return "Guild [id=" + id + ", name=" + name + ", size=" + size + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Guild other = (Guild) obj;
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