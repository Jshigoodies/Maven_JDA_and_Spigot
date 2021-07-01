package org.goodies.jshi.Jason.Discord_and_Spigot.events;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.goodies.jshi.Jason.Discord_and_Spigot.Main;

public class CountingEvents implements Listener {

    private Main plugin;

    public CountingEvents(Main main)
    {
        plugin = main;
    }

    @EventHandler
    public void onBlocBreak(BlockBreakEvent event)
    {
        if(event.getBlock().getType().equals(Material.DIAMOND_ORE)) {
            Player player = event.getPlayer();
            if (player.getGameMode().equals(GameMode.CREATIVE)) {
                return;
            }
            if (player.getInventory().getItemInMainHand().containsEnchantment(Enchantment.SILK_TOUCH)) {
                return;
            }
            plugin.counterConfig(event, player);
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event)
    {
        plugin.counterConfig(event, event.getEntity());
    }
}
