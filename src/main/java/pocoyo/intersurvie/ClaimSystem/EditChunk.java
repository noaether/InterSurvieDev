package pocoyo.intersurvie.ClaimSystem;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pocoyo.intersurvie.PluginMain;

import static org.bukkit.Bukkit.getLogger;

public class EditChunk implements CommandExecutor {
    private final PluginMain plugin;

    public EditChunk(PluginMain plugin) {
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
                Inventory claimInventory = plugin.getServer().createInventory(null, 27, ChatColor.WHITE + "Claim settings");

                boolean isRedstone = PluginMain.getInstance().getConfig().get("intersurvie.claims.chunk.perms.redstone." + chunkCoordsX + chunkCoordsZ) != null && PluginMain.getInstance().getConfig().get("intersurvie.claims.chunk.perms.redstone." + chunkCoordsX + chunkCoordsZ).equals("true");
                boolean isFriendlyMobs = PluginMain.getInstance().getConfig().get("intersurvie.claims.chunk.perms.redstone." + chunkCoordsX + chunkCoordsZ) != null && PluginMain.getInstance().getConfig().get("intersurvie.claims.chunk.perms.friendlymobs." + chunkCoordsX + chunkCoordsZ).equals("true");
                boolean isChest = PluginMain.getInstance().getConfig().get("intersurvie.claims.chunk.perms.redstone." + chunkCoordsX + chunkCoordsZ) != null && PluginMain.getInstance().getConfig().get("intersurvie.claims.chunk.perms.chests." + chunkCoordsX + chunkCoordsZ).equals("true");

                ItemStack empty = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);
                ItemMeta emptyMeta = empty.getItemMeta();
                emptyMeta.setDisplayName("'");
                empty.setItemMeta(emptyMeta);

                ItemStack activated = new ItemStack(Material.LIME_STAINED_GLASS_PANE, 1 );
                ItemMeta activatedMeta = activated.getItemMeta();
                activatedMeta.setDisplayName("'");
                activated.setItemMeta(activatedMeta);

                ItemStack disabled = new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);
                ItemMeta disabledMeta = disabled.getItemMeta();
                disabledMeta.setDisplayName("'");
                disabled.setItemMeta(disabledMeta);

                ItemStack redstoneSettings = new ItemStack(Material.REDSTONE, 1);
                ItemMeta rONmeta = redstoneSettings.getItemMeta();
                rONmeta.setDisplayName(ChatColor.RED + "REDSTONE SETTINGS");
                redstoneSettings.setItemMeta(rONmeta);

                ItemStack friendlyON = new ItemStack(Material.IRON_SWORD, 1);
                ItemMeta fONmeta = friendlyON.getItemMeta();
                fONmeta.setDisplayName(ChatColor.GREEN + "CAN HIT FRIENDLY ANIMALS : ON");
                friendlyON.setItemMeta(fONmeta);

                ItemStack friendlyOFF = new ItemStack(Material.IRON_SWORD, 1);
                ItemMeta fOFFmeta = friendlyOFF.getItemMeta();
                fOFFmeta.setDisplayName(ChatColor.RED + "CAN HIT FRIENDLY ANIMALS : OFF");
                friendlyOFF.setItemMeta(fOFFmeta);

                ItemStack chestOFF = new ItemStack(Material.CHEST,1);
                ItemMeta cOFFmeta = chestOFF.getItemMeta();
                cOFFmeta.setDisplayName(ChatColor.RED + "CAN OPEN CHESTS : OFF");
                chestOFF.setItemMeta(cOFFmeta);

                ItemStack chestON = new ItemStack(Material.CHEST,1);
                ItemMeta cONmeta = chestON.getItemMeta();
                cONmeta.setDisplayName(ChatColor.GREEN + "CAN OPEN CHESTS : ON");
                chestON.setItemMeta(cONmeta);

                // if(isChest) { claimInventory.setItem( , chestON); } else { claimInventory.setItem( , chestOFF); }
                // if(isChest) { claimInventory.setItem( , chestON); } else { claimInventory.setItem( , chestOFF); }

                claimInventory.setItem(0 , empty);
                claimInventory.setItem(1, empty);
                if(isFriendlyMobs) { claimInventory.setItem(2, activated); } else { claimInventory.setItem(2, disabled); }
                claimInventory.setItem(3, empty);
                claimInventory.setItem(4, empty);
                claimInventory.setItem(5, empty);
                if(isChest) { claimInventory.setItem(6, activated); } else { claimInventory.setItem(6, disabled); }
                claimInventory.setItem(7, empty);
                claimInventory.setItem(8, empty);
                claimInventory.setItem(9, empty);
                claimInventory.setItem(10,empty);
                if(isFriendlyMobs) { claimInventory.setItem(11, friendlyON); } else { claimInventory.setItem(11, friendlyOFF); }
                claimInventory.setItem(12, empty);
                claimInventory.setItem(13, redstoneSettings);
                claimInventory.setItem(14, empty);
                if(isChest) { claimInventory.setItem(15, chestON); } else { claimInventory.setItem(15, chestOFF); }
                claimInventory.setItem(16, empty);
                claimInventory.setItem(17, empty);
                claimInventory.setItem(18, empty);
                claimInventory.setItem(19, empty);
                if(isFriendlyMobs) { claimInventory.setItem(20, activated); } else { claimInventory.setItem(20, disabled); }
                claimInventory.setItem(21, empty);
                claimInventory.setItem(22, empty);
                claimInventory.setItem(23, empty);
                if(isChest) { claimInventory.setItem(24, activated); } else { claimInventory.setItem(24, disabled); }
                claimInventory.setItem(25, empty);
                claimInventory.setItem(26, empty);

                commandPlayer.openInventory(claimInventory);

        } else {
            commandPlayer.sendMessage(PluginMain.getInstance().getConfig().getString("chunk-notowner"));
        }
        return true;
    }
}
