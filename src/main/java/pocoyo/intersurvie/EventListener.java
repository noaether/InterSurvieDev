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
            emptyMeta.setDisplayName(" ");
            empty.setItemMeta(emptyMeta);

            ItemStack activated = new ItemStack(Material.LIME_STAINED_GLASS_PANE, 1 );
            ItemMeta activatedMeta = activated.getItemMeta();
            activatedMeta.setDisplayName(" ");
            activated.setItemMeta(activatedMeta);

            ItemStack disabled = new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);
            ItemMeta disabledMeta = disabled.getItemMeta();
            disabledMeta.setDisplayName(" ");
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

            if (item.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "CAN HIT FRIENDLY ANIMALS : ON")) {
                ItemStack redstoneOFF = new ItemStack(Material.RED_TERRACOTTA, 1);
                ItemMeta rOFFmeta = redstoneOFF.getItemMeta();
                rOFFmeta.setDisplayName(ChatColor.RED + "CAN INTERACT WITH REDSTONE : OFF");
                redstoneOFF.setItemMeta(rOFFmeta);

                PluginMain.getInstance().getConfig().set("intersurvie.claims.chunk.perms.redstone." + chunkCoordsX + chunkCoordsZ, "false");

                open.setItem(3, redstoneOFF);
            } else if (item.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "CAN HIT FRIENDLY ANIMALS : ON")) {
                ItemStack redstoneOFF = new ItemStack(Material.RED_TERRACOTTA, 1);
                ItemMeta rOFFmeta = redstoneOFF.getItemMeta();
                rOFFmeta.setDisplayName(ChatColor.RED + "CAN INTERACT WITH REDSTONE : OFF");
                redstoneOFF.setItemMeta(rOFFmeta);

                PluginMain.getInstance().getConfig().set("intersurvie.claims.chunk.perms.redstone." + chunkCoordsX + chunkCoordsZ, "false");

                open.setItem(3, redstoneOFF);
            } else if (item.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "CAN HIT FRIENDLY ANIMALS : ON")) {
                ItemStack redstoneOFF = new ItemStack(Material.RED_TERRACOTTA, 1);
                ItemMeta rOFFmeta = redstoneOFF.getItemMeta();
                rOFFmeta.setDisplayName(ChatColor.RED + "CAN INTERACT WITH REDSTONE : OFF");
                redstoneOFF.setItemMeta(rOFFmeta);

                PluginMain.getInstance().getConfig().set("intersurvie.claims.chunk.perms.redstone." + chunkCoordsX + chunkCoordsZ, "false");

                open.setItem(3, redstoneOFF);
            } else if (item.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "CAN HIT FRIENDLY ANIMALS : ON")) {
                ItemStack redstoneOFF = new ItemStack(Material.RED_TERRACOTTA, 1);
                ItemMeta rOFFmeta = redstoneOFF.getItemMeta();
                rOFFmeta.setDisplayName(ChatColor.RED + "CAN INTERACT WITH REDSTONE : OFF");
                redstoneOFF.setItemMeta(rOFFmeta);

                PluginMain.getInstance().getConfig().set("intersurvie.claims.chunk.perms.redstone." + chunkCoordsX + chunkCoordsZ, "false");

                open.setItem(3, redstoneOFF);
            } else if (item.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "CAN HIT FRIENDLY ANIMALS : ON")) {
                ItemStack redstoneOFF = new ItemStack(Material.RED_TERRACOTTA, 1);
                ItemMeta rOFFmeta = redstoneOFF.getItemMeta();
                rOFFmeta.setDisplayName(ChatColor.RED + "CAN INTERACT WITH REDSTONE : OFF");
                redstoneOFF.setItemMeta(rOFFmeta);

                PluginMain.getInstance().getConfig().set("intersurvie.claims.chunk.perms.redstone." + chunkCoordsX + chunkCoordsZ, "false");

                open.setItem(3, redstoneOFF);
            }
        }
    }

}
