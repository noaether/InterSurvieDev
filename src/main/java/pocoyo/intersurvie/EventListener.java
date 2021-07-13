package pocoyo.intersurvie;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static org.bukkit.Bukkit.getLogger;

public class EventListener implements Listener {

    public EventListener(PluginMain plugin){
        //register Events of this class
        //with methode: registerEvents(Listener, Plugin);
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        getLogger().info("BlockBreakEvent triggered");
        Player eventPlayer = event.getPlayer();
        Block eventBlock = event.getBlock();

        String chunkCoordsX = String.valueOf(eventBlock.getChunk().getX());
        String chunkCoordsZ = String.valueOf(eventBlock.getChunk().getZ());

        boolean isChunkClaimed = PluginMain.getInstance().getConfig().get("intersurvie.claims.chunk.isclaimed." + chunkCoordsX + chunkCoordsZ) != null && PluginMain.getInstance().getConfig().get("intersurvie.claims.chunk.isclaimed." + chunkCoordsX + chunkCoordsZ).equals("true");

        String chunkOwner = (String) PluginMain.getInstance().getConfig().get("intersurvie.claims.chunk.owner." + chunkCoordsX + chunkCoordsZ);
    /*
    1) Check block chunk coords
    2) Check if chunk is claimed
        - If not claimed, break the block
        - If claimed, step 3
    3) Check if claim belongs to player
    4) If it does, break the block
    */
        if(isChunkClaimed) {
            // Chunk is claimed
            if(chunkOwner.equals(eventPlayer.getUniqueId().toString())) {
                // Player is chunk owner
                event.setCancelled(false);
            } else {
                // Player is not chunk owner
                event.setCancelled(true);
                event.setDropItems(false);
                event.setExpToDrop(0);
                eventPlayer.sendMessage(PluginMain.getInstance().getConfig().getString("claim-chunkclaimed"));
            }
        } else {
            event.setCancelled(false);
        }
    }
    @EventHandler
    public void onCropBreak(PlayerInteractEvent event) {
        getLogger().info("PlayerInteractEvent triggered");

        if (event.getAction() == Action.PHYSICAL)
        {
            Player eventPlayer = event.getPlayer();
            Block eventBlock = event.getClickedBlock();

            String chunkCoordsX = String.valueOf(eventPlayer.getLocation().getChunk().getX());
            String chunkCoordsZ = String.valueOf(eventPlayer.getLocation().getChunk().getZ());

            boolean isChunkClaimed = PluginMain.getInstance().getConfig().get("intersurvie.claims.chunk.isclaimed." + chunkCoordsX + chunkCoordsZ) != null && PluginMain.getInstance().getConfig().get("intersurvie.claims.chunk.isclaimed." + chunkCoordsX + chunkCoordsZ).equals("true");

            String chunkOwner = (String) PluginMain.getInstance().getConfig().get("intersurvie.claims.chunk.owner." + chunkCoordsX + chunkCoordsZ);

            if(isChunkClaimed) {
                // Chunk is claimed
                if(chunkOwner.equals(eventPlayer.getUniqueId().toString())) {
                    // Player is chunk owner
                    event.setCancelled(false);
                } else if (PluginMain.getInstance().getConfig().get("intersurvie.claims.chunk." + String.valueOf(eventPlayer.getUniqueId()) + "." + chunkCoordsX + chunkCoordsZ + "." + "added") == "true") {
                    // Player is added to chunk
                    event.setCancelled(false);
                }{
                    // Player is not chunk owner
                    event.setCancelled(true);
                    eventPlayer.sendMessage(PluginMain.getInstance().getConfig().getString("claim-chunkclaimed"));
                }
            } else {
                event.setCancelled(false);
            }
        }
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        getLogger().info("PlayerInteractEvent triggered");

        if (event.getAction() == Action.LEFT_CLICK_BLOCK)
        {
            Player eventPlayer = event.getPlayer();
            Block eventBlock = event.getClickedBlock();

            String chunkCoordsX = String.valueOf(eventBlock.getChunk().getX());
            String chunkCoordsZ = String.valueOf(eventBlock.getChunk().getZ());

            boolean isChunkClaimed = PluginMain.getInstance().getConfig().get("intersurvie.claims.chunk.isclaimed." + chunkCoordsX + chunkCoordsZ) != null && PluginMain.getInstance().getConfig().get("intersurvie.claims.chunk.isclaimed." + chunkCoordsX + chunkCoordsZ).equals("true");

            String chunkOwner = (String) PluginMain.getInstance().getConfig().get("intersurvie.claims.chunk.owner." + chunkCoordsX + chunkCoordsZ);
    /*
    1) Check block chunk coords
    2) Check if chunk is claimed
        - If not claimed, break the block
        - If claimed, step 3
    3) Check if claim belongs to player
    4) If it does, break the block
    */
            if(isChunkClaimed) {
                // Chunk is claimed
                if(chunkOwner.equals(eventPlayer.getUniqueId().toString())) {
                    // Player is chunk owner
                    event.setCancelled(false);
                } else if (PluginMain.getInstance().getConfig().get("intersurvie.claims.chunk." + String.valueOf(eventPlayer.getUniqueId()) + "." + chunkCoordsX + chunkCoordsZ + "." + "added") == "true") {
                    // Player is added to chunk
                    event.setCancelled(false);
                }{
                    // Player is not chunk owner
                    event.setCancelled(true);
                    eventPlayer.sendMessage(PluginMain.getInstance().getConfig().getString("claim-chunkclaimed"));
                }
            } else {
                event.setCancelled(false);
            }
        }
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK)
        {
            Player eventPlayer = event.getPlayer();
            Block eventBlock = event.getClickedBlock();

            String chunkCoordsX = String.valueOf(eventBlock.getChunk().getX());
            String chunkCoordsZ = String.valueOf(eventBlock.getChunk().getZ());

            boolean isChunkClaimed = PluginMain.getInstance().getConfig().get("intersurvie.claims.chunk.isclaimed." + chunkCoordsX + chunkCoordsZ) != null && PluginMain.getInstance().getConfig().get("intersurvie.claims.chunk.isclaimed." + chunkCoordsX + chunkCoordsZ).equals("true");
            String chunkOwner = (String) PluginMain.getInstance().getConfig().get("intersurvie.claims.chunk.owner." + chunkCoordsX + chunkCoordsZ);

            if(isChunkClaimed) {
                // Chunk is claimed
                if(chunkOwner.equals(eventPlayer.getUniqueId().toString())) {
                    // Player is chunk owner
                    event.setCancelled(false);
                } else {
                    // Player is not chunk owner
                    event.setCancelled(true);
                    eventPlayer.sendMessage(PluginMain.getInstance().getConfig().getString("claim-chunkclaimed"));
                }
            } else {
                event.setCancelled(false);
            }
        }
    }

    @EventHandler
    public void InvenClick(InventoryClickEvent event) {
        event.setCancelled(true);
        Player player = (Player) event.getWhoClicked();

        String chunkCoordsX = String.valueOf(player.getLocation().getChunk().getX());
        String chunkCoordsZ = String.valueOf(player.getLocation().getChunk().getZ());

        Inventory open = event.getClickedInventory();
        ItemStack item = event.getCurrentItem();

        if (open == null) {
            return;
        }
        if (
                        open.getSize() == 26 && // Checks for correct size
                        open.getLocation() == null // Checks if inv is plugin-summoned

        ) {

            event.setCancelled(true);

            ItemStack empty = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);
            ItemMeta emptyMeta = empty.getItemMeta();
            emptyMeta.setDisplayName("");
            empty.setItemMeta(emptyMeta);

            ItemStack activated = new ItemStack(Material.LIME_STAINED_GLASS_PANE, 1 );
            ItemMeta activatedMeta = activated.getItemMeta();
            activatedMeta.setDisplayName(null);
            activated.setItemMeta(activatedMeta);

            ItemStack disabled = new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);
            ItemMeta disabledMeta = disabled.getItemMeta();
            disabledMeta.setDisplayName("'");
            disabled.setItemMeta(disabledMeta);

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


            if (item == null || !item.hasItemMeta() || item == empty) {
                event.setCancelled(true);
            }


            if (item.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "CAN OPEN CHESTS : ON")) {
                PluginMain.getInstance().getConfig().set("intersurvie.claims.chunk.perms.chests." + chunkCoordsX + chunkCoordsZ, "false");

                open.setItem(6, disabled);
                open.setItem(15, chestOFF);
                open.setItem(24, disabled);
            } else
            if (item.getItemMeta().getDisplayName().equals(ChatColor.RED + "CAN OPEN CHESTS : OFF")) {
                PluginMain.getInstance().getConfig().set("intersurvie.claims.chunk.perms.chests." + chunkCoordsX + chunkCoordsZ, "true");

                open.setItem(6, activated);
                open.setItem(15, chestON);
                open.setItem(24, activated);
            } else
            if (item.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "CAN HIT FRIENDLY ANIMALS : ON")) {
                PluginMain.getInstance().getConfig().set("intersurvie.claims.chunk.perms.friendlymobs." + chunkCoordsX + chunkCoordsZ, "false");

                open.setItem(6, disabled);
                open.setItem(11, friendlyOFF);
                open.setItem(20, disabled);
            } else
            if (item.getItemMeta().getDisplayName().equals(ChatColor.RED + "CAN HIT FRIENDLY ANIMALS : OFF")) {
                PluginMain.getInstance().getConfig().set("intersurvie.claims.chunk.perms.friendlymobs." + chunkCoordsX + chunkCoordsZ, "false");

                open.setItem(6, activated);
                open.setItem(11, friendlyON);
                open.setItem(20, activated);
            }
        }
    }

}
