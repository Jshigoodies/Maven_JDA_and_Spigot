package org.goodies.jshi.Jason.Discord_and_Spigot;

import net.dv8tion.jda.api.EmbedBuilder;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.goodies.jshi.Jason.Discord_and_Spigot.bot.Bot;
import org.goodies.jshi.Jason.Discord_and_Spigot.commands.SendingCommand;
import org.goodies.jshi.Jason.Discord_and_Spigot.communication.ApiToApi;
import org.goodies.jshi.Jason.Discord_and_Spigot.communication.DiscordMessagesManager;
import org.goodies.jshi.Jason.Discord_and_Spigot.communication.MinecraftMessagesManager;
import org.goodies.jshi.Jason.Discord_and_Spigot.events.CountingEvents;
import org.goodies.jshi.Jason.Discord_and_Spigot.file.Counter;
import org.goodies.jshi.Jason.Discord_and_Spigot.file.DataManager;

import java.awt.*;
import java.util.List;

public class Main extends JavaPlugin {

    private Bot bot;
    private DataManager data;
    private Counter countConfig;
    private SendingCommand sendCmd;
    private ApiToApi api;

    @Override
    public void onEnable()
    {
        getCommand("discord").setExecutor(sendCmd = new SendingCommand());

        this.data = new DataManager(this);
        this.countConfig = new Counter(this);
        configSetUp();
        getServer().getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + data.getConfig().getString("testing.test"));

        api = new ApiToApi(sendCmd);
        api.setPlugin(this); //set minecraft
        DiscordMessagesManager obj1 = new DiscordMessagesManager(api);
        MinecraftMessagesManager obj2 = new MinecraftMessagesManager(api);
        getServer().getPluginManager().registerEvents(obj2, this);
        getServer().getPluginManager().registerEvents(new CountingEvents(this), this);


        this.bot = new Bot(this, data.getConfig().getString("bot.token"));
        this.bot.guildID = data.getConfig().getString("bot.guild_ID");
        this.bot.channelID = data.getConfig().getString("bot.text_channel");
        api.setBot(bot); //set discord bot
        this.bot.setManager(obj1);

        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[Discord_and_Spigot]: Plugin is Enabled");
    }

    @Override
    public void onDisable()
    {
        EmbedBuilder embedStop = new EmbedBuilder();
        embedStop.setTitle(":red_car: Server is down", "https://github.com/Jshigoodies");
        embedStop.setColor(Color.RED);
        bot.getMainChannel().sendMessage(embedStop.build()).queue();
        embedStop.clear();

        getServer().getConsoleSender().sendMessage(ChatColor.RED + "[Discord_and_Spigot]: Plugin is Disabled");
    }

    public void configSetUp()
    {
        if(!data.getConfig().contains("bot"))
        {
            data.getConfig().set("bot.token" , "put token here");
            data.getConfig().set("bot.guild_ID", "put guild ID here");
            data.getConfig().set("bot.text_channel", "put the id of the main channel the bot will send messages");
        }
        data.saveConfig();
        data.getConfig().set("testing.test", "Config File set up success");
        data.saveConfig();
    }

    public void counterConfig(Event event, Player player)
    {
        int amount = 0;
        if(event instanceof BlockBreakEvent)
        {
            if(countConfig.getConfig().contains("diamonds." + player.getName()))
            {
                amount = countConfig.getConfig().getInt("diamonds." + player.getName());
            }
            countConfig.getConfig().set("diamonds." + player.getName(), (amount + 1));
        }
        if(event instanceof PlayerDeathEvent)
        {
            if(countConfig.getConfig().contains("death." + player.getName()))
            {
                amount = countConfig.getConfig().getInt("death." + player.getName());
            }
            countConfig.getConfig().set("death." + player.getName(), (amount + 1));
        }
        countConfig.saveConfig();
    }

    public void getCounterConfigList(String name)
    {
        //figure it out
    }
}
