package org.goodies.jshi.Jason.Discord_and_Spigot.communication;

public class DiscordMessagesManager {

    private static ApiToApi api;

    public DiscordMessagesManager(ApiToApi _api)
    {
        api = _api;
    }

    public void onDiscordChat(String msg, String author, String channel)
    {
        String complete = "(" + channel + ") " + "[" + author + "]: " + msg;

        api.sendToMinecraft(complete);
    }
}
