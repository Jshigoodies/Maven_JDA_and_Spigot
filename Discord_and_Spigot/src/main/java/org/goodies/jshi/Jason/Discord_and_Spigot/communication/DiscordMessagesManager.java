package org.goodies.jshi.Jason.Discord_and_Spigot.communication;

import org.bukkit.ChatColor;

public class DiscordMessagesManager {

    private static ApiToApi api;

    public DiscordMessagesManager(ApiToApi _api)
    {
        api = _api;
    }

    public void onDiscordChat(String msg, String author, String channel)
    {
        String complete = ChatColor.DARK_AQUA + "(" + channel + ") " + ChatColor.BLUE + "[" + author + "]: " + ChatColor.WHITE + msg;

        api.sendToMinecraft(complete);
    }
}
