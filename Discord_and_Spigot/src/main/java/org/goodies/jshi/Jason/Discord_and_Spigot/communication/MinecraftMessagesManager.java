package org.goodies.jshi.Jason.Discord_and_Spigot.communication;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class MinecraftMessagesManager implements Listener {

    private static ApiToApi api;

    public MinecraftMessagesManager(ApiToApi _api)
    {
        api = _api;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event)
    {
        String msg = event.getMessage();
        String author = event.getPlayer().getName();
        Player player = event.getPlayer();

        String complete = "[" + author + "]: " + msg;

        api.sendToDiscord(complete, player);
    }
}
