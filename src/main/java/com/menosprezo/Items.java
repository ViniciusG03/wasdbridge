package com.menosprezo;

import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Dye;

public class Items implements Listener {

    public Items() {

    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        World world = e.getPlayer().getWorld();
        world.setPVP(false);

        Player player = e.getPlayer();

        Location SpawnTeleport = new Location(player.getWorld(), 52, 117, 182);
        player.teleport(SpawnTeleport);

        ItemStack Wool = new ItemStack(Material.WOOL, 64);
        ItemStack Wool2 = new ItemStack(Material.WOOL, 64);
        ItemStack Wool3 = new ItemStack(Material.WOOL, 64);

        ItemStack Restart = new ItemStack(Material.REDSTONE_COMPARATOR, 1);

        Dye redDye = new Dye();
        redDye.setColor(DyeColor.RED);
        ItemStack Sair = redDye.toItemStack(1);

        ItemMeta restart = Restart.getItemMeta();
        restart.setDisplayName("§7Restart");
        Restart.setItemMeta(restart);

        ItemMeta sair = Sair.getItemMeta();
        sair.setDisplayName("§cSair");
        Sair.setItemMeta(sair);

        player.getInventory().setItem(0, Wool);
        player.getInventory().setItem(1, Wool2);
        player.getInventory().setItem(2, Wool3);

        player.getInventory().setItem(7, Restart);
        player.getInventory().setItem(8, Sair);
    }

    @EventHandler
    public void onPlayerVoid(PlayerMoveEvent e) {

        Player player = e.getPlayer();
        Location playerLocation = e.getPlayer().getLocation();

        if (playerLocation.getY() < 115 || playerLocation.getY() > 132) {
            player.getInventory().clear();

            ItemStack Wool = new ItemStack(Material.WOOL, 64);
            ItemStack Wool2 = new ItemStack(Material.WOOL, 64);
            ItemStack Wool3 = new ItemStack(Material.WOOL, 64);

            ItemStack Restart = new ItemStack(Material.REDSTONE_COMPARATOR, 1);

            Dye redDye = new Dye();
            redDye.setColor(DyeColor.RED);
            ItemStack Sair = redDye.toItemStack(1);

            ItemMeta restart = Restart.getItemMeta();
            restart.setDisplayName("§7Restart");
            Restart.setItemMeta(restart);

            ItemMeta sair = Sair.getItemMeta();
            sair.setDisplayName("§cSair");
            Sair.setItemMeta(sair);

            player.getInventory().setItem(0, Wool);
            player.getInventory().setItem(1, Wool2);
            player.getInventory().setItem(2, Wool3);

            player.getInventory().setItem(7, Restart);
            player.getInventory().setItem(8, Sair);

            player.getScoreboard().getTeam("blocksPlaced").setSuffix("§7" + 0);
            Location destination = new Location(player.getWorld(), 52, 117, 182);
            breakWhiteWoolBlocks(destination, 120);
            player.teleport(destination);
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {

        Player player = e.getPlayer();

        Location location = new Location(player.getWorld(), 52, 117, 182);

        if (e.getAction().name().contains("RIGHT")) {
            ItemStack itemInHand = player.getItemInHand();

            if (itemInHand != null && itemInHand.getType() == Material.REDSTONE_COMPARATOR) {
                player.getInventory().clear();

                ItemStack Wool = new ItemStack(Material.WOOL, 64);
                ItemStack Wool2 = new ItemStack(Material.WOOL, 64);
                ItemStack Wool3 = new ItemStack(Material.WOOL, 64);

                ItemStack Restart = new ItemStack(Material.REDSTONE_COMPARATOR, 1);

                Dye redDye = new Dye();
                redDye.setColor(DyeColor.RED);
                ItemStack Sair = redDye.toItemStack(1);

                ItemMeta restart = Restart.getItemMeta();
                restart.setDisplayName("§7Restart");
                Restart.setItemMeta(restart);

                ItemMeta sair = Sair.getItemMeta();
                sair.setDisplayName("§cSair");
                Sair.setItemMeta(sair);

                player.getInventory().setItem(0, Wool);
                player.getInventory().setItem(1, Wool2);
                player.getInventory().setItem(2, Wool3);

                player.getInventory().setItem(7, Restart);
                player.getInventory().setItem(8, Sair);

                player.getScoreboard().getTeam("blocksPlaced").setSuffix("§7" + 0);
                breakWhiteWoolBlocks(location, 120);
                player.teleport(location);

            }
            if (e.getAction().name().contains("RIGHT") && itemInHand != null && itemInHand.getType() == Material.INK_SACK && itemInHand.getDurability() == 1) {
                if (itemInHand.hasItemMeta() && itemInHand.getItemMeta().hasDisplayName() && itemInHand.getItemMeta().getDisplayName().equals("§cSair")) {
                    Messaging.send("send", player.getName(), "lobby");
                    breakWhiteWoolBlocks(location, 120);
                }
            }
        }
    }

    private void breakWhiteWoolBlocks(Location center, int radius) {
        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    Block block = center.getWorld().getBlockAt(center.getBlockX() + x, center.getBlockY() + y, center.getBlockZ() + z);

                    if (block.getType() == Material.WOOL && block.getData() == (byte) 0) {
                        block.getDrops().clear();
                        block.setType(Material.AIR);
                    }
                }
            }
        }
    }
}





