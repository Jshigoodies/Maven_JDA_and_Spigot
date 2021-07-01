package org.goodies.jshi.Jason.Discord_and_Spigot.bot;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.goodies.jshi.Jason.Discord_and_Spigot.Main;
import org.goodies.jshi.Jason.Discord_and_Spigot.communication.DiscordMessagesManager;
import org.jetbrains.annotations.NotNull;

import javax.security.auth.login.LoginException;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Bot extends ListenerAdapter {

    private static JDA jda;
    private final Main plugin;
    private Guild guild;
    private List<TextChannel> channelList;
    private final String token;
    private EmbedBuilder embed;

    public String guildID;
    public String channelID;

    private TextChannel mainChannel;

    private DiscordMessagesManager manager;

    //i know this looks stupid, but I'm literally open to suggestions on how to make discord and minecraft work together

    public Bot(Main main, String _token) {
        plugin = main;
        token = _token;
        botSetUp();
    }

    private void botSetUp() {
        JDABuilder building = JDABuilder.createDefault(token);
        building.addEventListeners(this);
        try {
            jda = building.build();
        } catch (LoginException e) {
            // TODO Auto-generated catch block
            plugin.getServer().getConsoleSender().sendMessage(ChatColor.RED + "[Discord_and_Spigot]: Please provide a token");
            return;
        }
        //done building

        jda.getPresence().setStatus(OnlineStatus.DO_NOT_DISTURB);
        jda.getPresence().setActivity(Activity.watching("FKING ANIME"));
    }

    private void embedSetUp()
    {
        embed = new EmbedBuilder();
        embed.setTitle(":salad: Sever Is Up", "https://github.com/Jshigoodies");
        embed.setColor(Color.GREEN);
    }

    private void channelSetUp()
    {
        try {
            guild = jda.getGuildById(guildID);
            channelList = guild.getTextChannels();
        }
        catch(Exception e)
        {
            plugin.getServer().getConsoleSender().sendMessage(ChatColor.RED + "[Discord_and_Spigot]: Error, setting up the channel does not work");
        }

        try{
            mainChannel = jda.getTextChannelById(channelID);
            mainChannel.sendMessage(embed.build()).queue();
        }
        catch(Exception e)
        {
            plugin.getServer().getConsoleSender().sendMessage(ChatColor.RED + "[Discord_and_Spigot]: Main channel set up did not work");
        }
    }

    public TextChannel getMainChannel()
    {
        return mainChannel;
    }

    public List<TextChannel> getChannelList()
    {
        return channelList;
    }
    public void setManager(DiscordMessagesManager _manager)
    {
        manager = _manager;
    }

    // Events
    @Override
    public void onReady(@NotNull ReadyEvent event) {
        embedSetUp();
        channelSetUp();
        plugin.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[Discord_and_Spigot]: Bot setup success!");

    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        if(event.getAuthor().isBot())
        {
            return;
        }
        if(event.getMessage().getContentRaw().equalsIgnoreCase("!maven"))
        {
            event.getMessage().getChannel().sendMessage("good job, you are using maven to start a discord bot by running a spigot server").queue();
        }
        if(event.getMessage().getContentRaw().equalsIgnoreCase("!refresh"))
        {
            try {
                channelSetUp();
                event.getChannel().sendMessage("channels refreshed").queue();
            }
            catch(Exception e)
            {
                event.getChannel().sendMessage("Error with refreshing channels").queue();
            }
        }
        if(event.getMessage().getContentRaw().equalsIgnoreCase("!diamonds"))
        {
            plugin.getCounterConfigList("diamonds", event.getChannel());
        }

        if(event.getMessage().getContentRaw().equalsIgnoreCase("!deaths"))
        {
            plugin.getCounterConfigList("death", event.getChannel());
        }

        String msg = event.getMessage().getContentRaw();
        String author = event.getAuthor().getName();
        String channelName = event.getChannel().getName();

        manager.onDiscordChat(msg, author, channelName);
    }
}
