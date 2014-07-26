package org.talestats.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.talestats.dao.GuildDAO;
import org.talestats.model.Guild;

@Service
@Transactional
public class GuildServiceImpl implements GuildService {

	@Autowired
	private GuildDAO guildDAO;

	@Override
	public void addGuild(Guild guild) {
		guildDAO.addGuild(guild);
	}

	@Override
	public void updateGuild(Guild guild) {
		guildDAO.updateGuild(guild);
	}

	@Override
	public Guild getGuild(int id) {
		return guildDAO.getGuild(id);
	}

	@Override
	public void deleteGuild(int id) {
		guildDAO.deleteGuild(id);
	}

	@Override
	public List<Guild> getGuilds() {
		return guildDAO.getGuilds();
	}

	@Override
	public int getGuildSize(int guildId) {
		return guildDAO.getGuildSize(guildId);
	}

}
