package org.talestats.service;

import java.util.List;

import org.talestats.model.Guild;

public interface GuildService {

    public void addGuild(Guild guild);
    public Guild getGuild(int id);
    public void updateGuild(Guild guild);
    public void deleteGuild(int id);
    public List<Guild> getGuilds();
    public int getGuildSize(int guildId);
}
