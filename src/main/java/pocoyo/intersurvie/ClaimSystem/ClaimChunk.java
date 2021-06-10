package pocoyo.intersurvie.ClaimSystem;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pocoyo.intersurvie.PluginMain;

import static org.bukkit.Bukkit.getLogger;

public class ClaimChunk implements CommandExecutor {
    private final PluginMain plugin;

    public ClaimChunk(PluginMain plugin) {
        this.plugin = plugin; // Store the plugin in situations where you need it.
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player commandPlayer = (Player) sender;

        String chunkCoordsX = String.valueOf(commandPlayer.getLocation().getChunk().getX());
        String chunkCoordsZ = String.valueOf(commandPlayer.getLocation().getChunk().getZ());

        if(PluginMain.getInstance().getConfig().get("intersurvie.claims.chunk.owner." + chunkCoordsX + chunkCoordsZ) == null || PluginMain.getInstance().getConfig().get("intersurvie.claims.chunk.owner." + chunkCoordsX + chunkCoordsZ) == "false") {
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
        } else {
            commandPlayer.sendMessage(PluginMain.getInstance().getConfig().getString("claim-notavailable"));
        }
        return true;
    }
}
