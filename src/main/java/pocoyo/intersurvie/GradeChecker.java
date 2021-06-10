package pocoyo.intersurvie;

import java.util.UUID;

import me.alexcrea.proxygate.grades.GradeManager;
import me.alexcrea.proxygate.grades.Grades;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class GradeChecker extends PluginMain{

  public static PluginMain instance;

    public boolean isAdmin(Player p) { return GradeManager.getInstance().contain(p.getName(), Grades.Admin); };

    public boolean isAssistant(Player p) { return GradeManager.getInstance().contain(p.getName(), Grades.Assistant); };

    public boolean checkIfPlayerName(String checkPlayerName){
        return Bukkit.getPlayer(checkPlayerName) != null;
    }

    public boolean checkIfPlayerUUID(UUID checkPlayerUUID){
        return Bukkit.getPlayer(checkPlayerUUID) != null;
    }
}
