package pocoyo.intersurvie;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import static org.bukkit.Bukkit.getLogger;

import java.util.Objects;

public class Claim {
    String chunkCoordsX;
    String chunkCoordsZ;

    public void claimChunk(Player commandPlayer) {
        try {
            chunkCoordsX = String.valueOf(commandPlayer.getLocation().getChunk().getX());
            chunkCoordsZ = String.valueOf(commandPlayer.getLocation().getChunk().getZ());

            String playerUUID = String.valueOf(commandPlayer.getUniqueId());

            PluginMain.getInstance().getConfig().set("intersurvie.claims.chunk.owner." + chunkCoordsX + chunkCoordsZ, playerUUID);
            PluginMain.getInstance().getConfig().set("intersurvie.claims.chunk.isclaimed." + chunkCoordsX + chunkCoordsZ, "true");

            PluginMain.getInstance().getConfig().set("intersurvie.claims.chunk.perms.redstone.button" + chunkCoordsX + chunkCoordsZ, "false");
            PluginMain.getInstance().getConfig().set("intersurvie.claims.chunk.perms.redstone.lever" + chunkCoordsX + chunkCoordsZ, "false");
            PluginMain.getInstance().getConfig().set("intersurvie.claims.chunk.perms.redstone.pPlates" + chunkCoordsX + chunkCoordsZ, "false");
            PluginMain.getInstance().getConfig().set("intersurvie.claims.chunk.perms.redstone.doors" + chunkCoordsX + chunkCoordsZ, "false");

            PluginMain.getInstance().getConfig().set("intersurvie.claims.chunk.perms.friendlymobs." + chunkCoordsX + chunkCoordsZ, "false");
            PluginMain.getInstance().getConfig().set("intersurvie.claims.chunk.perms.chests." + chunkCoordsX + chunkCoordsZ, "false");

            PluginMain.getInstance().saveConfig();

            commandPlayer.sendMessage(PluginMain.getInstance().getConfig().getString("claim-chunk"));
            getLogger().info((String) PluginMain.getInstance().getConfig().get("intersurvie.claims.chunk.owner." + chunkCoordsX + chunkCoordsZ));
            getLogger().info((String) PluginMain.getInstance().getConfig().get("intersurvie.claims.chunk.isclaimed." + chunkCoordsX + chunkCoordsZ));
        } catch (Throwable e){
            throw(e);
        }
    };

    public void unclaimChunk(Player commandPlayer) {
        chunkCoordsX = String.valueOf(commandPlayer.getLocation().getChunk().getX());
        chunkCoordsZ = String.valueOf(commandPlayer.getLocation().getChunk().getZ());

        boolean isChunkClaimed = PluginMain.getInstance().getConfig().get("intersurvie.claims.chunk.isclaimed." + chunkCoordsX + chunkCoordsZ) != null && PluginMain.getInstance().getConfig().get("intersurvie.claims.chunk.isclaimed." + chunkCoordsX + chunkCoordsZ).equals("true");
        String chunkOwner = (String) PluginMain.getInstance().getConfig().get("intersurvie.claims.chunk.owner." + chunkCoordsX + chunkCoordsZ);

        assert chunkOwner != null;
        String playerUUID = Objects.requireNonNull(Bukkit.getPlayer(chunkOwner)).getUniqueId().toString();

        if(isChunkClaimed) {
            // Chunk is claimed
            if(chunkOwner.equals(commandPlayer.getUniqueId().toString())) {
                // Player is chunk owner
                PluginMain.getInstance().getConfig().set("intersurvie.claims.chunk.owner." + chunkCoordsX+chunkCoordsZ, null);
                PluginMain.getInstance().getConfig().set("intersurvie.claims.chunk.isclaimed." + chunkCoordsX+chunkCoordsZ, "false");

                PluginMain.getInstance().getConfig().set("intersurvie.claims.chunk.owner." + chunkCoordsX + chunkCoordsZ, playerUUID);
                PluginMain.getInstance().getConfig().set("intersurvie.claims.chunk.isclaimed." + chunkCoordsX + chunkCoordsZ, "true");

                PluginMain.getInstance().getConfig().set("intersurvie.claims.chunk.perms.redstone.button" + chunkCoordsX + chunkCoordsZ, "true");
                PluginMain.getInstance().getConfig().set("intersurvie.claims.chunk.perms.redstone.lever" + chunkCoordsX + chunkCoordsZ, "true");
                PluginMain.getInstance().getConfig().set("intersurvie.claims.chunk.perms.redstone.pPlates" + chunkCoordsX + chunkCoordsZ, "true");
                PluginMain.getInstance().getConfig().set("intersurvie.claims.chunk.perms.redstone.doors" + chunkCoordsX + chunkCoordsZ, "true");

                PluginMain.getInstance().getConfig().set("intersurvie.claims.chunk.perms.friendlymobs." + chunkCoordsX + chunkCoordsZ, "true");
                PluginMain.getInstance().getConfig().set("intersurvie.claims.chunk.perms.chests." + chunkCoordsX + chunkCoordsZ, "true");
                PluginMain.getInstance().saveConfig();

                commandPlayer.sendMessage(PluginMain.getInstance().getConfig().getString("unclaim-chunk"));
            } else {
                // Player is not chunk owner
                commandPlayer.sendMessage(PluginMain.getInstance().getConfig().getString("unclaim-notowner"));
            }
        } else {
            commandPlayer.sendMessage(PluginMain.getInstance().getConfig().getString("unclaim-notclaimed"));
        }
    }

    public Boolean isChunkClaimed(String chunkCoordsX, String chunkCoordsZ) {
    return PluginMain.getInstance().getConfig().get("intersurvie.claims.chunk.isclaimed." + chunkCoordsX + chunkCoordsZ).equals("true");
    };

    private String playerExists;
    public String whoClaimedChunk(String chunkCoordsX, String chunkCoordsZ) {
        String playerUUID = (String) PluginMain.getInstance().getConfig().get("intersurvie.claims.chunk.owner." + chunkCoordsX + chunkCoordsZ);

        return playerUUID != null ? Bukkit.getPlayer(playerUUID).getUniqueId().toString() : Bukkit.getPlayer(playerUUID).getUniqueId().toString();
    }
}
