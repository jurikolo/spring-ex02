package org.talestats.dao;

import java.util.List;

import org.talestats.model.Guild;

public interface GuildDAO {

	public void addGuild(Guild guild);

	public Guild getGuild(int id);

	public void updateGuild(Guild guild);

	public void deleteGuild(int id);

	public List<Guild> getGuilds();

	public void addOrUpdateGuild(Guild guild);

	public void deleteAllGuilds();

	public int getGuildSize(int guildId);
}