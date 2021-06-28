package org.goodies.jshi.Jason.Discord_and_Spigot.bot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.ChatColor;
import org.goodies.jshi.Jason.Discord_and_Spigot.Main;
import org.jetbrains.annotations.NotNull;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Bot extends ListenerAdapter {

    private static JDA jda;
    public static String prefix = "~";
    private final Main plugin;
    private Guild guild;
    private List<TextChannel> listCh;

    //i know this looks stupid, but I'm literally open to suggestions on how to make discord and minecraft work together

    public Bot(Main main) {
        plugin = main;
        setUp();
    }

    public void setUp() {
        File f = new File("C:\\Users\\Jshi\\Desktop\\JavaWorkSpace2\\Discord_and_Spigot\\src\\token.dat");
        Scanner input = null;
        try {
            input = new Scanner(f);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String token = input.nextLine();
        JDABuilder building = JDABuilder.createDefault(token);
        building.addEventListeners(this);
        try {
            jda = building.build();
        } catch (LoginException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //done building

        jda.getPresence().setStatus(OnlineStatus.DO_NOT_DISTURB);
        jda.getPresence().setActivity(Activity.watching("FKING ANIME"));

        input.close();

        //organizing is good
        additionalSetUp();
    }

    private void additionalSetUp() //this adds the channels that the bot can send to
    {
        guild = jda.getGuildById("772158782758715403");
        listCh = guild.getTextChannels();
    }

    // Events
    @Override
    public void onReady(@NotNull ReadyEvent event) {
        plugin.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[THE FKING BOT IS SPEAKING] Bot setup success!");
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        if(event.getMessage().getContentRaw().equalsIgnoreCase("!maven"))
        {
            event.getMessage().getChannel().sendMessage("good job, you are using maven to start a discord bot by running a spigot server").queue();
        }
    }

}
