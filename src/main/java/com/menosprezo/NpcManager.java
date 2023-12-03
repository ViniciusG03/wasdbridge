package com.menosprezo;

import net.minecraft.server.v1_8_R3.ScoreboardTeam;
import net.minecraft.server.v1_8_R3.ScoreboardTeamBase;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.scoreboard.CraftScoreboard;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NpcManager implements Listener {

    public static ScoreboardTeam team;
    public static List<Npc> npcs = new ArrayList<>();

    public NpcManager() {
        team = new ScoreboardTeam(((CraftScoreboard) Bukkit.getScoreboardManager().getMainScoreboard()).getHandle(), UUID.randomUUID().toString().substring(0, 16));
        team.setNameTagVisibility(ScoreboardTeamBase.EnumNameTagVisibility.NEVER);

        Npc pontes = new Npc();
        pontes.fast("world", "menosprezo", 56.5, 117,182.5, 90, 0, "§7Configure seu modo!", "§3§lPONTES");
        pontes.setup(48);

    }

    public static void onInteractWithNpc(Player player, Npc npc) {


    }

    public static Npc getFromId(String npcId) {
        for (Npc npc : npcs)
            if (npc.getNpcId().equals(npcId))
                return npc;

        return null;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        Location p_loc = player.getLocation();

        npcs.forEach(npc -> {
            Location npc_loc = npc.getLocation();
            if (Util.distance2D(p_loc, npc_loc) > 48 && !npc.getPlayers().contains(player)) {
                npc.hide(player);
                npc.getPlayers().add(player);
            }
            if (Util.distance2D(p_loc, npc_loc) < 48 && npc.getPlayers().contains(player)) {
                npc.show(player, 10);
                npc.getPlayers().remove(player);
            }
        });
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        for (Npc npc : npcs)
            npc.show(e.getPlayer(), 100);
    }
}
