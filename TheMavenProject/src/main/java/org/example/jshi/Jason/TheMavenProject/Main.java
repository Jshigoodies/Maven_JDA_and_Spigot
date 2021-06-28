package org.example.jshi.Jason.TheMavenProject;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable()
    {
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "it fking works");
    }

    @Override
    public void onDisable()
    {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "it fking disable");
    }
}
