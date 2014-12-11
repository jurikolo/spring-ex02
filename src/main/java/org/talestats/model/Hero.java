package org.talestats.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

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

	@Basic
	@Column(name = "subscriber")
	private int subscribed;

	// Timestamp from 01.01.1970 in seconds for the last visit of the game
	@Basic
	@Column(name = "lastvisittimestamp")
	public int lastVisitTimeStamp;

	// Checks achievement of politics different from Zero.
	@Basic
	@Column(name = "ispoliticallyactive")
	public int politicallyActive;

	@Basic
	@Column(name = "level")
	public int level;

	public Hero() {
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

	public int getSubscribed() {
		return subscribed;
	}

	public void setSubscribed(int subscribed) {
		this.subscribed = subscribed;
	}

	// Unix timestamp in milliseconds
	public int getLastVisitTimeStamp() {
		return lastVisitTimeStamp;
	}

	// Formatted timestamp: year - month - day
	public String getLastVisitTimeStampFormatted() {
		Date date = new Date(lastVisitTimeStamp * 1000L); // *1000 is to convert
															// seconds to
															// milliseconds
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // the format
																	// of your
																	// date
		sdf.setTimeZone(TimeZone.getTimeZone("GMT-4")); // give a timezone
														// reference for
														// formating (see
														// comment at the bottom
		return sdf.format(date);
	}

	public void setLastVisitTimeStamp(int lastVisitTimeStamp) {
		this.lastVisitTimeStamp = lastVisitTimeStamp;
	}

	public int getPoliticallyActive() {
		return politicallyActive;
	}

	public void setPoliticallyActive(int PoliticallyActive) {
		this.politicallyActive = PoliticallyActive;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "Hero [id=" + id + ", name=" + name + ", keeper=" + keeper + ", allyId=" + allyId + ", enemyId="
				+ enemyId + ", cityId=" + cityId + ", guildId=" + guildId + ", subscribed=" + subscribed
				+ ", lastVisitTimeStamp=" + lastVisitTimeStamp + ", politicallyActive=" + politicallyActive
				+ ", level=" + level + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + politicallyActive;
		result = prime * result + lastVisitTimeStamp;
		result = prime * result + allyId;
		result = prime * result + cityId;
		result = prime * result + enemyId;
		result = prime * result + guildId;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((keeper == null) ? 0 : keeper.hashCode());
		result = prime * result + level;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + subscribed;
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
		if (politicallyActive != other.politicallyActive)
			return false;
		if (lastVisitTimeStamp != other.lastVisitTimeStamp)
			return false;
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
		if (level != other.level)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (subscribed != other.subscribed)
			return false;
		return true;
	}
}