package com.menosprezo;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class Bridges extends JavaPlugin implements Listener {
    public static Bridges plugin;
    public static String outChannel = "vel:backend";
    public static String inChannel = "vel:proxy";

    private Map<UUID, Location> lastLocations = new HashMap<>();
    private DecimalFormat decimalFormat = new DecimalFormat("0.00");

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new NpcManager(), this);
        getServer().getPluginManager().registerEvents(new FireWorks(), this);
        getServer().getPluginManager().registerEvents(new Items(), this);
        getServer().getPluginManager().registerEvents(new RestrictBuild(), this);
        getServer().getPluginManager().registerEvents(new ScoreBoard(), this);
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getMessenger().registerIncomingPluginChannel(this, inChannel, new Messaging());
        getServer().getMessenger().registerOutgoingPluginChannel(this, outChannel);

        plugin = this;

    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();

    }


    @EventHandler
    public void onPlayerMovement(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        Location playerLocation = e.getPlayer().getLocation();

        if (playerLocation.getY() < 116) {
            player.getScoreboard().getTeam("timeTeam").setSuffix("§7" + startTime);
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();

        if (e.getAction().name().contains("RIGHT")) {
            ItemStack itemInHand = player.getItemInHand();

            if (itemInHand != null && itemInHand.getType() == Material.REDSTONE_COMPARATOR) {
                player.getScoreboard().getTeam("timeTeam").setSuffix("§7" + startTime);
            }

            if (e.getAction().name().contains("RIGHT") && itemInHand != null && itemInHand.getType() == Material.INK_SACK && itemInHand.getDurability() == 1) {
                if (itemInHand.hasItemMeta() && itemInHand.getItemMeta().hasDisplayName() && itemInHand.getItemMeta().getDisplayName().equals("§cSair")) {
                    player.getScoreboard().getTeam("timeTeam").setSuffix("§7" + startTime);
                }
            }

        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        e.setCancelled(true);
    }

}
