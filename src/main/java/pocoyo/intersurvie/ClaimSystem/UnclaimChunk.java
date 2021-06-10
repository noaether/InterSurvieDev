package pocoyo.intersurvie.ClaimSystem;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pocoyo.intersurvie.PluginMain;

import java.util.Objects;

import static org.bukkit.Bukkit.getLogger;

public class UnclaimChunk implements CommandExecutor {
    private final PluginMain plugin;

    public UnclaimChunk(PluginMain plugin) {
        this.plugin = plugin; // Store the plugin in situations where you need it.
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player commandPlayer = (Player) sender;
            String chunkCoordsX = String.valueOf(commandPlayer.getLocation().getChunk().getX());
            String chunkCoordsZ = String.valueOf(commandPlayer.getLocation().getChunk().getZ());

            boolean isChunkClaimed = PluginMain.getInstance().getConfig().get("intersurvie.claims.chunk.isclaimed." + chunkCoordsX + chunkCoordsZ) != null && PluginMain.getInstance().getConfig().get("intersurvie.claims.chunk.isclaimed." + chunkCoordsX + chunkCoordsZ).equals("true");
            String chunkOwner = (String) PluginMain.getInstance().getConfig().get("intersurvie.claims.chunk.owner." + chunkCoordsX + chunkCoordsZ);

            getLogger().info(chunkOwner);

            String playerUUID = chunkOwner;

            if(isChunkClaimed) {
                // Chunk is claimed
                if(chunkOwner.equals(commandPlayer.getUniqueId().toString())) {
                    // Player is chunk owner
                    PluginMain.getInstance().getConfig().set("intersurvie.claims.chunk.owner." + chunkCoordsX + chunkCoordsZ, null);
                    PluginMain.getInstance().getConfig().set("intersurvie.claims.chunk.isclaimed." + chunkCoordsX + chunkCoordsZ, "false");

                    PluginMain.getInstance().getConfig().set("intersurvie.claims.chunk.perms.redstone.button" + chunkCoordsX + chunkCoordsZ, null);
                    PluginMain.getInstance().getConfig().set("intersurvie.claims.chunk.perms.redstone.lever" + chunkCoordsX + chunkCoordsZ, null);
                    PluginMain.getInstance().getConfig().set("intersurvie.claims.chunk.perms.redstone.pPlates" + chunkCoordsX + chunkCoordsZ, null);
                    PluginMain.getInstance().getConfig().set("intersurvie.claims.chunk.perms.redstone.doors" + chunkCoordsX + chunkCoordsZ, null);

                    PluginMain.getInstance().getConfig().set("intersurvie.claims.chunk.perms.friendlymobs." + chunkCoordsX + chunkCoordsZ, null);
                    PluginMain.getInstance().getConfig().set("intersurvie.claims.chunk.perms.chests." + chunkCoordsX + chunkCoordsZ, null);
                    PluginMain.getInstance().saveConfig();

                    commandPlayer.sendMessage(PluginMain.getInstance().getConfig().getString("unclaim-chunk"));
                } else {
                    // Player is not chunk owner
                    commandPlayer.sendMessage(PluginMain.getInstance().getConfig().getString("unclaim-notowner"));
                }
            } else {
                commandPlayer.sendMessage(PluginMain.getInstance().getConfig().getString("unclaim-notclaimed"));
            }
        return true;
    }
}
