package org.goodies.jshi.Jason.Discord_and_Spigot.communication;

import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.goodies.jshi.Jason.Discord_and_Spigot.Main;
import org.goodies.jshi.Jason.Discord_and_Spigot.bot.Bot;
import org.goodies.jshi.Jason.Discord_and_Spigot.commands.SendingCommand;

import java.util.List;
import java.util.Set;

public class ApiToApi {

    private SendingCommand sendCMD;
    private Main plugin;
    private Bot bot;

    public ApiToApi(SendingCommand _sendCMD)
    {
        sendCMD = _sendCMD;

    }

    public void setPlugin(Main main)
    {
        plugin = main;
    }

    public void setBot(Bot _bot)
    {
        bot = _bot;
    }

    public void sendToMinecraft(String completeMessage)
    {
        Set<Player> playerSet = sendCMD.getCommunicators().keySet();
        for(Player player : playerSet)
        {
            player.sendMessage(completeMessage);
        }
    }

    public void sendToDiscord(String completeMessage, Player player)
    {
        if(!sendCMD.getCommunicators().containsKey(player))
        {
            return;
        }

        String channelToSend = sendCMD.getCommunicators().get(player);
        List<TextChannel> list = bot.getChannelList();

        boolean exists = false;
        for (TextChannel textChannel : list) {
            if (textChannel.getName().equalsIgnoreCase(channelToSend)) {
                textChannel.sendMessage("```diff\n" + "+" + completeMessage + "\n```").queue();
                exists = true;
                break;
            }
        }
        if(!exists)
        {
            player.sendMessage(ChatColor.RED + "cannot find channel");
        }

    }
}
