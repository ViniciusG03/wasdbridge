package com.menosprezo;


import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class Messaging implements PluginMessageListener {
    public static void send(String identifier, String... msgs) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF(identifier);

        for (String i : msgs)
            out.writeUTF(i);

        Bridges.plugin.getServer().sendPluginMessage(Bridges.plugin, Bridges.outChannel, out.toByteArray());
    }

    @Override
    public void onPluginMessageReceived(String s, Player player, byte[] bytes) {

    }
}
