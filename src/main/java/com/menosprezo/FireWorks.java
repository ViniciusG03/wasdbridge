package com.menosprezo;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Dye;

public class FireWorks implements Listener {

    @EventHandler
    public void onPlayerMovement(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        Location playerLocation = e.getPlayer().getLocation();

        if (playerLocation.getZ() > 240) {
            Firework firework = e.getPlayer().getWorld().spawn(e.getPlayer().getLocation(), Firework.class);
            FireworkMeta meta = (FireworkMeta) firework.getFireworkMeta();
            meta.addEffect(FireworkEffect.builder().withColor(Color.AQUA).withColor(Color.LIME).withFlicker().build());
            meta.setPower(1);
            firework.setFireworkMeta(meta);

            Location location = new Location(player.getWorld(), 52, 117, 182);

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
            breakWhiteWoolBlocks(location, 120);
            player.teleport(location);
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
