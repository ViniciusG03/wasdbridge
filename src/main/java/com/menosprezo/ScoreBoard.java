package com.menosprezo;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.*;

import java.util.HashMap;
import java.util.UUID;

public class ScoreBoard implements Listener {

    private HashMap<UUID, Integer> blocksPlacedMap = new HashMap<>();
    private HashMap<UUID, Integer> distanceMap = new HashMap<>();

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();

        Objective obj = board.registerNewObjective("BridgeScoreboard", "dummy");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName("§3§lPONTES");

        Score wasdIp = obj.getScore("§3         wasdmc.com.br");
        wasdIp.setScore(0);

        Score linha = obj.getScore("§8§m--------------------");
        linha.setScore(1);

//        Score pulo = obj.getScore("  ");
//        pulo.setScore(2);

//        Score record = obj.getScore("§f Record: §4Não alcançado");
//        record.setScore(3);

//        Score pulo1 = obj.getScore("   ");
//        pulo1.setScore(4);

        Score mode = obj.getScore("§f Modo: §7Padrão");
        mode.setScore(2);

        Score pulo2 = obj.getScore(" ");
        pulo2.setScore(3);

//        Score speed = obj.getScore("§f Velocidade: §70.0 m/s");
//        speed.setScore(7);

//        Score blocksplaced = obj.getScore("§f Blocos: §70");
//        blocksplaced.setScore(8);

//        Score distance = obj.getScore("§f Distancia: §70/35");
//        distance.setScore(6);

        Score pulo3 = obj.getScore("     ");
        pulo3.setScore(6);

//        Score comece = obj.getScore("§f Tempo: §70.00");
//        comece.setScore(9);

        Score linha2 = obj.getScore("§8§m --------------------");
        linha2.setScore(9);

        Team timeTemp = board.registerNewTeam("timeTeam");
        timeTemp.addEntry(ChatColor.GREEN.toString());
        timeTemp.setPrefix("§f Tempo: ");
        timeTemp.setSuffix("§70.00");
        obj.getScore(ChatColor.GREEN.toString()).setScore(8);

        Team blocksPlaced = board.registerNewTeam("blocksPlaced");
        blocksPlaced.addEntry(ChatColor.BOLD.toString());
        blocksPlaced.setPrefix("§f Blocos: ");
        blocksPlaced.setSuffix("§70");
        obj.getScore(ChatColor.BOLD.toString()).setScore(4);

        Team distance = board.registerNewTeam("distanceTeam");
        distance.addEntry(ChatColor.RED.toString());
        distance.setPrefix("§f Distancia: ");
        distance.setSuffix("§70/50");
        obj.getScore(ChatColor.RED.toString()).setScore(5);


        Team speedTeam = board.registerNewTeam("speedBoardTeam");
        speedTeam.addEntry(ChatColor.GRAY.toString());
        speedTeam.setPrefix("§f Velocidade: ");
        speedTeam.setSuffix("§70.0 m/s");
        obj.getScore(ChatColor.GRAY.toString()).setScore(7);

        player.setScoreboard(board);
        blocksPlacedMap.put(player.getUniqueId(), 0);
        distanceMap.put(player.getUniqueId(), 0);
    }

    @EventHandler
    public void onBlockPlaced(BlockPlaceEvent e) {
        Player player = e.getPlayer();

        int amount = blocksPlacedMap.get(player.getUniqueId());
        amount++;

        int amountDistance = distanceMap.get(player.getUniqueId());
        amountDistance++;

        blocksPlacedMap.put(player.getUniqueId(), amount);
        distanceMap.put(player.getUniqueId(), amountDistance);
        player.getScoreboard().getTeam("distanceTeam").setSuffix("§7" + amountDistance + "§7/50");
        player.getScoreboard().getTeam("blocksPlaced").setSuffix("§7" + amount);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();

        if (e.getAction().name().contains("RIGHT")) {
            ItemStack itemInHand = player.getItemInHand();

            if (itemInHand != null && itemInHand.getType() == Material.REDSTONE_COMPARATOR) {
                blocksPlacedMap.put(player.getUniqueId(), 0);
                distanceMap.put(player.getUniqueId(), 0);
                player.getScoreboard().getTeam("distanceTeam").setSuffix("§70/50");
                player.getScoreboard().getTeam("blocksPlaced").setSuffix("§70");
            }
        }
    }

    @EventHandler
    public void onPlayerVoid(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        Location playerLocation = player.getLocation();

        if (playerLocation.getY() < 116 || playerLocation.getY() > 221) {
            blocksPlacedMap.put(player.getUniqueId(), 0);
            distanceMap.put(player.getUniqueId(), 0);
            player.getScoreboard().getTeam("distanceTeam").setSuffix("§70/50");
            player.getScoreboard().getTeam("blocksPlaced").setSuffix("§70");
        }
    }
}