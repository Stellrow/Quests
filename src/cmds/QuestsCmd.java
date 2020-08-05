package cmds;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import main.QuestsMain;
import npc.CustomTrader;
import playermanager.PlayerManager;

public class QuestsCmd implements CommandExecutor{
	private QuestsMain pl = JavaPlugin.getPlugin(QuestsMain.class);
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if(sender instanceof Player) {
			Player p = (Player)sender;
			
			
			if(args.length>=2&&args[0].equalsIgnoreCase("questsreward")) {
				if(p.hasPermission("quests.edit")) {
				if(args[1].equalsIgnoreCase("normal")) {
					pl.gui.openNormale(p);
				}
				if(args[1].equalsIgnoreCase("epic")) {
					pl.gui.openBoss(p);
				}
				return true;
			}}
			
			if(args.length>=2&&args[0].equalsIgnoreCase("start")) {
				if(p.hasPermission("quests.start")) {
					if(pl.qhandler.availableQuests.contains(args[1])) {
				pl.qhandler.loadQuest(Integer.parseInt(args[1]));
					}
				p.sendMessage("Starting selected quest if it exists");
				}
				return true;
			}
			
			if(args.length>=1&&args[0].equalsIgnoreCase("setnpc")) {
				if(pl.npc!=null) {
					pl.npc.teleport(p.getLocation());;
				}
				pl.npc = new CustomTrader(p.getLocation().getWorld(),p.getLocation());
				pl.getConfig().set("LocatieNpc.Locatie", p.getLocation());
				pl.saveConfig();
				return true;
			}
			
			if(args.length>=1&&args[0].equalsIgnoreCase("stats")) {
				for(String s : pl.getConfig().getStringList("QuestStats")) {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', s).replaceAll("%numeJucator", p.getDisplayName()).replaceAll("%nrQuesturi", PlayerManager.players.get(p).getCfg().getInt("Stats.QuesturiCompletate")+""));
				}
				return true;
			}
			
			if(args.length>=1&&args[0].equalsIgnoreCase("reload")) {
				if(p.hasPermission("quests.reload")) {
				pl.reloadConfig();
				pl.loadQuests();
				pl.loadRewards();
				pl.rew.loadRewards();
				p.sendMessage("Reloaded Quests");
				return true;
			}}
			
			
			if(cmd.getName().equalsIgnoreCase("quests")) {
				pl.qhandler.startQuest(p);
				return true;
			}
		}
		
		
		
		return true;
	}

}
