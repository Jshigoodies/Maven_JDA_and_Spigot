package org.goodies.jshi.Jason.Discord_and_Spigot.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.goodies.jshi.Jason.Discord_and_Spigot.bot.Bot;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SendingCommand implements CommandExecutor {

    private Map<Player, String> communicators;

    public SendingCommand()
    {
        communicators = new HashMap<Player, String>();
    }

    public Map<Player, String> getCommunicators()
    {
        return communicators;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)){return true;}
        Player player = (Player) sender;

        if(cmd.getName().equalsIgnoreCase("discord"))
        {
            if(args.length == 1)
            {
                //talk to a channel and see discord messages
                communicators.put(player, args[0]);
                player.sendMessage(ChatColor.GREEN + "your messages will now be sent through the " + args[0] + " channel");
            }
            else if(args.length == 0)
            {
                //don't want to talk to a channel and ignore discord messages
                communicators.remove(player);
                player.sendMessage(ChatColor.RED + "your message will not be sent to discord");
            }
            else
            {
                player.sendMessage(ChatColor.RED + "/discord <channel name>");

            }
            return true;
        }
        return false;
    }
}
