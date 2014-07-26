package org.talestats.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.talestats.model.Guild;

@Repository
public class GuildDAOImpl implements GuildDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void addGuild(Guild guild) {
		Session openSession = sessionFactory.openSession();
		openSession.save(guild);
		openSession.flush();
		openSession.close();
	}

	public void updateGuild(Guild guild) {
		Session openSession = sessionFactory.openSession();
		Guild guildToUpdate = getGuild(guild.getId());
		guildToUpdate.setName(guild.getName());
		guildToUpdate.setSize(guild.getSize());
		openSession.update(guildToUpdate);
		openSession.flush();
	}

	public Guild getGuild(int id) {
		Session openSession = sessionFactory.openSession();
		Guild guild = (Guild) openSession.get(Guild.class, id);
		openSession.flush();
		openSession.close();
		return guild;
	}

	public void deleteGuild(int id) {
		Session openSession = sessionFactory.openSession();
		Guild guild = getGuild(id);
		if (guild != null)
			openSession.delete(guild);
		openSession.flush();
		openSession.close();
	}

	public void addOrUpdateGuild(Guild guild) {
		Session openSession = sessionFactory.openSession();
		openSession.saveOrUpdate(guild);
		openSession.flush();
		openSession.close();
	}

	@SuppressWarnings("unchecked")
	public List<Guild> getGuilds() {
		Session openSession = sessionFactory.openSession();
		List<Guild> cities = openSession.createQuery("from Guild").list();
		openSession.flush();
		openSession.close();
		return cities;
	}

	@Override
	public void deleteAllGuilds() {
		Session openSession = sessionFactory.openSession();
		Query query = openSession.createQuery("delete from Guild"); 
		query.executeUpdate();
		openSession.flush();
		openSession.close();
	}

	@Override
	public int getGuildSize(int guildId) {
		Session openSession = sessionFactory.openSession();
		int size = openSession.createQuery("from Hero where guildid = " + guildId).list().size() + 1; 
		openSession.flush();
		openSession.close();
		return size; 
		}

}
