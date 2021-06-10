package pocoyo.intersurvie.ClaimSystem;

import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.Plugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pocoyo.intersurvie.PluginMain;

import static org.bukkit.Bukkit.getLogger;

public class RemoveChunk implements CommandExecutor {
    private final PluginMain plugin;

    public RemoveChunk(PluginMain plugin) {
        this.plugin = plugin; // Store the plugin in situations where you need it.
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player commandPlayer = (Player) sender;
        String chunkCoordsX = String.valueOf(commandPlayer.getLocation().getChunk().getX());
        String chunkCoordsZ = String.valueOf(commandPlayer.getLocation().getChunk().getZ());

        String playerUUID = String.valueOf(commandPlayer.getUniqueId());

        String chunkOwnerUUID = PluginMain.getInstance().getConfig().get("intersurvie.claims.chunk.owner." + chunkCoordsX + chunkCoordsZ).toString();
        boolean isPlayerOwner = chunkOwnerUUID.equalsIgnoreCase(playerUUID);

        if(isPlayerOwner) {
            String addedPlayerName = args[0];
            Player addedPlayer = Bukkit.getPlayer(addedPlayerName);
            assert addedPlayer != null;
            String addedUUid = addedPlayer.getUniqueId().toString();

            // Player is owner, proceed with adding the friend
            PluginMain.getInstance().getConfig().set("intersurvie.claims.chunk.members." + chunkCoordsX + chunkCoordsZ + "." + addedUUid, "false");
        } else {

        }


        return true;
    }


}
