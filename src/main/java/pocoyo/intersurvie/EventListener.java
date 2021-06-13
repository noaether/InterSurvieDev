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
                } else {
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
        Player player = (Player) event.getWhoClicked();

        String chunkCoordsX = String.valueOf(player.getLocation().getChunk().getX());
        String chunkCoordsZ = String.valueOf(player.getLocation().getChunk().getZ());

        Inventory open = event.getClickedInventory();
        ItemStack item = event.getCurrentItem();

        if (open == null) {
            return;
        }
        if (
            open.getLocation() == null // Checks if inv is plugin-summoned
                &&
            open.getSize() == 27
        ) {
            event.setCancelled(true);

            ItemStack empty = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);
            ItemMeta emptyMeta = empty.getItemMeta();
            emptyMeta.setDisplayName("");
            empty.setItemMeta(emptyMeta);

            if (item.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "CAN INTERACT WITH REDSTONE : ON")) {
                ItemStack redstoneOFF = new ItemStack(Material.RED_TERRACOTTA, 1);
                ItemMeta rOFFmeta = redstoneOFF.getItemMeta();
                rOFFmeta.setDisplayName(ChatColor.RED + "CAN INTERACT WITH REDSTONE : OFF");
                redstoneOFF.setItemMeta(rOFFmeta);

                PluginMain.getInstance().getConfig().set("intersurvie.claims.chunk.perms.redstone." + chunkCoordsX + chunkCoordsZ, "false");

                open.setItem(3, redstoneOFF);
            } else
            if (item.getItemMeta().getDisplayName().equals(ChatColor.RED + "CAN INTERACT WITH REDSTONE : OFF")) {
                ItemStack redstoneON = new ItemStack(Material.LIME_TERRACOTTA, 1);
                ItemMeta rONmeta = redstoneON.getItemMeta();
                rONmeta.setDisplayName(ChatColor.GREEN + "CAN INTERACT WITH REDSTONE : ON");
                redstoneON.setItemMeta(rONmeta);

                PluginMain.getInstance().getConfig().set("intersurvie.claims.chunk.perms.redstone." + chunkCoordsX + chunkCoordsZ, "true");

                open.setItem(3, redstoneON);
            } else
            if (item.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "CAN HIT FRIENDLY ANIMALS : ON")) {
                ItemStack friendlyOFF = new ItemStack(Material.RED_TERRACOTTA, 1);
                ItemMeta fOFFmeta = friendlyOFF.getItemMeta();
                fOFFmeta.setDisplayName(ChatColor.RED + "CAN HIT FRIENDLY ANIMALS : OFF");
                friendlyOFF.setItemMeta(fOFFmeta);

                PluginMain.getInstance().getConfig().set("intersurvie.claims.chunk.perms.friendlymobs." + chunkCoordsX + chunkCoordsZ, "false");

                open.setItem(5, friendlyOFF);
            } else
            if (item.getItemMeta().getDisplayName().equals(ChatColor.RED + "CAN HIT FRIENDLY ANIMALS : OFF")) {
                ItemStack friendlyON = new ItemStack(Material.LIME_TERRACOTTA, 1);
                ItemMeta fONmeta = friendlyON.getItemMeta();
                fONmeta.setDisplayName(ChatColor.GREEN + "CAN HIT FRIENDLY ANIMALS : ON");
                friendlyON.setItemMeta(fONmeta);

                PluginMain.getInstance().getConfig().set("intersurvie.claims.chunk.perms.friendlymobs." + chunkCoordsX + chunkCoordsZ, "false");

                open.setItem(5, friendlyON);
            }
        }
    }

}
