/* package pocoyo.intersurvie;

import com.sun.tools.javac.jvm.Items;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.PressureSensor;
import org.bukkit.material.Redstone;
import org.bukkit.plugin.Plugin;

import net.md_5.bungee.api.ChatColor;

public class CustomInventory extends PluginMain implements Listener {
    private final Plugin plugin = CustomInventory.getPlugin(PluginMain.class);

    public void newClaimParamsInventory(Player commandPlayer) {
        String chunkCoordsX = String.valueOf(commandPlayer.getLocation().getChunk().getX());
        String chunkCoordsZ = String.valueOf(commandPlayer.getLocation().getChunk().getZ());

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


    }
    public void newRedstoneParamsInventory(Player commandPlayer) {
        String chunkCoordsX = String.valueOf(commandPlayer.getLocation().getChunk().getX());
        String chunkCoordsZ = String.valueOf(commandPlayer.getLocation().getChunk().getZ());

        Inventory claimInventory = plugin.getServer().createInventory(null, 9, ChatColor.WHITE + "Claim settings");

        boolean isRedstone = PluginMain.getInstance().getConfig().get("intersurvie.claims.chunk.perms.redstone." + chunkCoordsX + chunkCoordsZ) != null && PluginMain.getInstance().getConfig().get("intersurvie.claims.chunk.perms.redstone." + chunkCoordsX + chunkCoordsZ).equals("true");
        boolean isFriendlyMobs = PluginMain.getInstance().getConfig().get("intersurvie.claims.chunk.perms.redstone." + chunkCoordsX + chunkCoordsZ) != null && PluginMain.getInstance().getConfig().get("intersurvie.claims.chunk.perms.redstone." + chunkCoordsX + chunkCoordsZ).equals("true");


        ItemStack empty = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);
        ItemMeta emptyMeta = empty.getItemMeta();
        emptyMeta.setDisplayName("'");
        empty.setItemMeta(emptyMeta);

        ItemStack redstoneON = new ItemStack(Material.LIME_TERRACOTTA, 1);
        ItemMeta rONmeta = redstoneON.getItemMeta();
        rONmeta.setDisplayName(ChatColor.GREEN + "CAN INTERACT WITH REDSTONE : ON");
        redstoneON.setItemMeta(rONmeta);

        ItemStack redstoneOFF = new ItemStack(Material.RED_TERRACOTTA, 1);
        ItemMeta rOFFmeta = redstoneOFF.getItemMeta();
        rOFFmeta.setDisplayName(ChatColor.RED + "CAN INTERACT WITH REDSTONE : OFF");
        redstoneOFF.setItemMeta(rOFFmeta);

        ItemStack friendlyON = new ItemStack(Material.LIME_TERRACOTTA, 1);
        ItemMeta fONmeta = friendlyON.getItemMeta();
        fONmeta.setDisplayName(ChatColor.GREEN + "CAN HIT FRIENDLY ANIMALS : ON");
        friendlyON.setItemMeta(fONmeta);

        ItemStack friendlyOFF = new ItemStack(Material.RED_TERRACOTTA, 1);
        ItemMeta fOFFmeta = friendlyOFF.getItemMeta();
        fOFFmeta.setDisplayName(ChatColor.RED + "CAN HIT FRIENDLY ANIMALS : OFF");
        friendlyOFF.setItemMeta(fOFFmeta);

        claimInventory.setItem(0,empty);
        claimInventory.setItem(1,empty);
        claimInventory.setItem(2,empty);
        if(isRedstone) { claimInventory.setItem(3, redstoneON); } else { claimInventory.setItem(3, redstoneOFF); }
        claimInventory.setItem(4,empty);
        if(isRedstone) { claimInventory.setItem(5, friendlyON); } else { claimInventory.setItem(5, friendlyOFF); }
        claimInventory.setItem(6,empty);
        claimInventory.setItem(7,empty);
        claimInventory.setItem(8,empty);

        commandPlayer.openInventory(claimInventory);


    }

}
*/
