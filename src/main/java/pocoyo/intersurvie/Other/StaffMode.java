package pocoyo.intersurvie.Other;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import pocoyo.intersurvie.PluginMain;

public class StaffMode implements CommandExecutor {

    private final PluginMain plugin;

    public StaffMode(PluginMain plugin) {
        this.plugin = plugin; // Store the plugin in situations where you need it.
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return false;
    }
}
