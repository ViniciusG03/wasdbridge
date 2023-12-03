package com.menosprezo;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.material.Wool;

public class RestrictBuild implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {

        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block clickedBlock = e.getClickedBlock();

            if (clickedBlock != null) {
                Material clickedMaterial = clickedBlock.getType();


                if (clickedMaterial == Material.STONE && clickedBlock.getData() == 6) {
                    return;
                }

                if (clickedMaterial == Material.WOOL) {
                    Wool wool = (Wool) clickedBlock.getState().getData();
                    if (wool.getColor() == org.bukkit.DyeColor.WHITE) {
                        return;
                    }
                }


                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onBlockPlaced(BlockPlaceEvent e) {
        Location location = e.getPlayer().getLocation();

        if (location.getZ() < 188 || location.getZ() > 239) {
            e.setCancelled(true);
        }
    }
}
