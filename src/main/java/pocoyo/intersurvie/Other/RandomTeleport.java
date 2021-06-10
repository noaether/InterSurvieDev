package pocoyo.intersurvie.Other;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pocoyo.intersurvie.PluginMain;

import java.util.HashMap;
import java.util.Objects;

import static org.bukkit.Bukkit.getLogger;

public class RandomTeleport implements CommandExecutor {
    private final PluginMain plugin;
    private final HashMap<String, Long> cooldowns;
    private final Boolean debugMode;

    public RandomTeleport(PluginMain plugin, HashMap<String, Long> cooldowns, Boolean debugMode) {
        this.plugin = plugin; // Store the plugin in situations where you need it.
        this.cooldowns = cooldowns;
        this.debugMode = debugMode;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String playerName = sender.getName();
        Player commandPlayer = (Player) sender;
            int cooldownTime = PluginMain.getInstance().getConfig().getInt("rtp-cooldown");
            if (cooldowns.containsKey(sender.getName())) {
                long secondsLeft = ((cooldowns.get(sender.getName()) / 1000) + cooldownTime)
                        - (System.currentTimeMillis() / 1000);
                if (secondsLeft > 0) {
                    // If player is still on cooldown
                    if (debugMode) {
                        getLogger().info(
                                "[DEBUG INTERSURVIE] Player is still on cooldown for " + secondsLeft + " seconds");
                    }
                    sender.sendMessage(PluginMain.getInstance().getConfig().getString("cooldown")
                            .replace("$TIME_REMAINING$", String.valueOf(secondsLeft)));
                    return true;
                }
            }
            // Player is not on cooldown, save new cooldown
            cooldowns.put(sender.getName(), System.currentTimeMillis());
            // Command stuff

            while (true) {

                // Generate location
                int x = (int) (Math.random() * (5000 + 5000 + 1) - 5000),
                        z = (int) (Math.random() * (5000 + 5000 + 1) - 5000);

                if (debugMode) {
                    getLogger().info("[DEBUG INTERSURVIE] Teleport location X and Z are generated");
                }

                // Get final block location
                Location randomTp = new org.bukkit.Location(commandPlayer.getLocation().getWorld(), x,
                        Objects.requireNonNull(commandPlayer.getLocation().getWorld()).getHighestBlockYAt(x, z), z);

                if (debugMode) {
                    getLogger().info("[DEBUG INTERSURVIE] Final location found");
                }

                // Check if location is safe
                Material rtpM = randomTp.getBlock().getType();
                boolean rtpMSafe = rtpM == Material.LAVA || rtpM == Material.WATER;
                if (debugMode & rtpMSafe) {
                    getLogger().info("[DEBUG INTERSURVIE] Location (" + x + " "
                            + commandPlayer.getLocation().getWorld().getHighestBlockYAt(x, z) + " " + z
                            + ") has been deemed unsafe, trying to find new location");
                }
                if (!rtpMSafe) {
                    int xC = randomTp.getBlockX();
                    int yC = randomTp.getBlockY();
                    int zC = randomTp.getBlockZ();

                    commandPlayer.teleport(randomTp);
                    sender.sendMessage(
                            PluginMain.getInstance().getConfig().getString("rtp-teleport").replace("$X$", String.valueOf(xC))
                                    .replace("$Y$", String.valueOf(yC)).replace("$Z$", String.valueOf(zC)));
                    if (debugMode) {
                        getLogger().info("[DEBUG INTERSURVIE] Player " + playerName + " has been teleported to location " + "X: "
                                + xC + " / Y: " + yC + " / Z: " + zC);
                        break;
                    }
                }
            }
        return true;
        }
    };
