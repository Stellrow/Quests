package rewards;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import main.QuestsMain;


public class CommandReward {
	private QuestsMain pl = JavaPlugin.getPlugin(QuestsMain.class);
	private List<String> commands = new ArrayList<String>();
	
	public CommandReward(String path) {
		commands = pl.getRewards().getStringList(path);
		
	}
	public void executeCommands(Player p) {
		for(String s : commands) {
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), s.replaceAll("%player", p.getName()));
		}
	}

}
