package org.goodies.jshi.Jason.Discord_and_Spigot.commands;

import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.goodies.jshi.Jason.Discord_and_Spigot.bot.Bot;

import java.util.ArrayList;
import java.util.List;

public class ChannelTab implements TabCompleter {
    List<String> arguments = new ArrayList<String>();
    Bot bot;
    public ChannelTab(Bot _bot)
    {
        bot = _bot;
    }

    public List<String> onTabComplete(CommandSender send, Command cmd, String label, String[] args)
    {
        List<TextChannel> list = bot.getChannelList();
        for(TextChannel ch : list)
        {
            arguments.add(ch.getName());
        }

        List<String> result = new ArrayList<String>();
        if(args.length == 1) {
            for(String a : arguments)
            {
                if(a.toLowerCase().startsWith(args[0].toLowerCase())) //if it starts with this first letter
                {
                    result.add(a); //if someone types a arugments that starts with a 'm' go through the whole list and see if any of them start with a 'm'
                }
            }
            return result;
        }

        return null;
    }
}
