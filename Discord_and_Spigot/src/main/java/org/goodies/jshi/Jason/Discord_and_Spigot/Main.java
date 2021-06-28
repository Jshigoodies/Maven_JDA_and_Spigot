package org.goodies.jshi.Jason.Discord_and_Spigot;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.goodies.jshi.Jason.Discord_and_Spigot.bot.Bot;

public class Main extends JavaPlugin {

    private Bot bot;

    @Override
    public void onEnable()
    {
        this.bot = new Bot(this);
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[Discord_and_Spigot]: Plugin is Enabled");
    }

    @Override
    public void onDisable()
    {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "[Discord_and_Spigot]: Plugin is Disabled");
    }
}
