package pocoyo.intersurvie;

import pocoyo.intersurvie.Claim;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import me.alexcrea.proxygate.grades.GradeManager;
import me.alexcrea.proxygate.grades.Grades;
import pocoyo.intersurvie.ClaimSystem.AddChunk;
import pocoyo.intersurvie.ClaimSystem.ClaimChunk;
import pocoyo.intersurvie.ClaimSystem.RemoveChunk;
import pocoyo.intersurvie.ClaimSystem.UnclaimChunk;
import pocoyo.intersurvie.Other.RandomTeleport;

public class PluginMain extends JavaPlugin {

    public HashMap<String, Long> cooldowns = new HashMap<>();

    // SET EXTERNAL VARIABLES
    Claim claimSystem;
    CustomInventory invSystem;

    public static PluginMain instance;

    public boolean debugMode = true;

    @Override
    public void onEnable() {

        instance = this;
            getDataFolder().mkdir();

        this.saveDefaultConfig();

        if (debugMode) {
            getLogger().info("*---*---*---*");
            getLogger().info("This plugin is in debug mode. Every statement will be logged");
            getLogger().info("*---*---*---*");
        }
        if (!debugMode) {
            getLogger().info("*---*---*---*");
            getLogger().info("This plugin is not in debug mode. Statements and checks wont be logged");
            getLogger().info("*---*---*---*");
        }

        // COMMANDS

        this.getCommand("claim").setExecutor(new ClaimChunk(this));
        this.getCommand("unclaim").setExecutor(new UnclaimChunk(this));
        this.getCommand("add").setExecutor(new AddChunk(this));
        this.getCommand("remove").setExecutor(new RemoveChunk(this));
        this.getCommand("rtp").setExecutor(new RandomTeleport(this, cooldowns, debugMode));


        // ENABLE EXTERNAL INSTANCES

        new EventListener(this);

    }

    public void onDisable(){
        instance.saveConfig();
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            Player commandPlayer = (Player) sender;

            String chunkCoordsX;
            String chunkCoordsZ;

            if ("debug-claim".equals(command.getName())) {
                chunkCoordsX = String.valueOf(commandPlayer.getLocation().getChunk().getX());
                chunkCoordsZ = String.valueOf(commandPlayer.getLocation().getChunk().getZ());

                Inventory claimInventory = PluginMain.getInstance().getServer().createInventory(null, 27, ChatColor.WHITE + "Claim settings");

                boolean isRedstone = PluginMain.getInstance().getConfig().get("intersurvie.claims.chunk.perms.redstone." + chunkCoordsX + chunkCoordsZ) != null && PluginMain.getInstance().getConfig().get("intersurvie.claims.chunk.perms.redstone." + chunkCoordsX + chunkCoordsZ).equals("true");
                boolean isFriendlyMobs = PluginMain.getInstance().getConfig().get("intersurvie.claims.chunk.perms.redstone." + chunkCoordsX + chunkCoordsZ) != null && PluginMain.getInstance().getConfig().get("intersurvie.claims.chunk.perms.friendlymobs." + chunkCoordsX + chunkCoordsZ).equals("true");
                boolean isChest = PluginMain.getInstance().getConfig().get("intersurvie.claims.chunk.perms.redstone." + chunkCoordsX + chunkCoordsZ) != null && PluginMain.getInstance().getConfig().get("intersurvie.claims.chunk.perms.chests." + chunkCoordsX + chunkCoordsZ).equals("true");

                ItemStack empty = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);
                ItemMeta emptyMeta = empty.getItemMeta();
                emptyMeta.setDisplayName("");
                empty.setItemMeta(emptyMeta);

                ItemStack activated = new ItemStack(Material.LIME_STAINED_GLASS_PANE, 1);
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

                ItemStack chestOFF = new ItemStack(Material.CHEST, 1);
                ItemMeta cOFFmeta = chestOFF.getItemMeta();
                cOFFmeta.setDisplayName(ChatColor.RED + "CAN OPEN CHESTS : OFF");
                chestOFF.setItemMeta(cOFFmeta);

                ItemStack chestON = new ItemStack(Material.CHEST, 1);
                ItemMeta cONmeta = chestON.getItemMeta();
                cONmeta.setDisplayName(ChatColor.GREEN + "CAN OPEN CHESTS : ON");
                chestON.setItemMeta(cONmeta);

                // if(isChest) { claimInventory.setItem( , chestON); } else { claimInventory.setItem( , chestOFF); }
                // if(isChest) { claimInventory.setItem( , chestON); } else { claimInventory.setItem( , chestOFF); }

                claimInventory.setItem(0, empty);
                claimInventory.setItem(1, empty);
                if (isFriendlyMobs) {
                    claimInventory.setItem(2, activated);
                } else {
                    claimInventory.setItem(2, disabled);
                }
                claimInventory.setItem(3, empty);
                claimInventory.setItem(4, empty);
                claimInventory.setItem(5, empty);
                if (isChest) {
                    claimInventory.setItem(6, activated);
                } else {
                    claimInventory.setItem(6, disabled);
                }
                claimInventory.setItem(7, empty);
                claimInventory.setItem(8, empty);
                claimInventory.setItem(9, empty);
                claimInventory.setItem(10, empty);
                if (isFriendlyMobs) {
                    claimInventory.setItem(11, friendlyON);
                } else {
                    claimInventory.setItem(11, friendlyOFF);
                }
                claimInventory.setItem(12, empty);
                claimInventory.setItem(13, redstoneSettings);
                claimInventory.setItem(14, empty);
                if (isChest) {
                    claimInventory.setItem(15, chestON);
                } else {
                    claimInventory.setItem(15, chestOFF);
                }
                claimInventory.setItem(16, empty);
                claimInventory.setItem(17, empty);
                claimInventory.setItem(18, empty);
                claimInventory.setItem(19, empty);
                if (isFriendlyMobs) {
                    claimInventory.setItem(20, activated);
                } else {
                    claimInventory.setItem(20, disabled);
                }
                claimInventory.setItem(21, empty);
                claimInventory.setItem(22, empty);
                claimInventory.setItem(23, empty);
                if (isChest) {
                    claimInventory.setItem(24, activated);
                } else {
                    claimInventory.setItem(24, disabled);
                }
                claimInventory.setItem(25, empty);
                claimInventory.setItem(26, empty);

                commandPlayer.openInventory(claimInventory);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
    private void rtp(Location location, Player player, String playerName, Integer x, Integer y, Integer z) {
        try {
            player.teleport(location);
            player.sendMessage(
                    PluginMain.getInstance().getConfig().getString("rtp-teleport").replace("$X$", String.valueOf(x))
                            .replace("$Y$", String.valueOf(y)).replace("$Z$", String.valueOf(z)));
            if (debugMode) {
                getLogger().info("[DEBUG INTERSURVIE] Player " + playerName + " has been teleported to location " + "X: "
                        + x + " / Y: " + y + " / Z: " + z);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static PluginMain getInstance() {
        return instance;
    }
}

    ;
