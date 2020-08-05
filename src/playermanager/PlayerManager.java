package playermanager;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import configs.CustomConfig;

public class PlayerManager implements Listener{
	public static HashMap<Player,CustomConfig> players = new HashMap<Player,CustomConfig>();
	
	
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		if(!players.containsKey(e.getPlayer())) {
		players.put(e.getPlayer(), new CustomConfig(e.getPlayer().getUniqueId().toString()));
		}
	}
	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		if(players.containsKey(e.getPlayer())) {
			players.remove(e.getPlayer());
		}
	}
	public static void completeQuest(Player p) {
		if(players.get(p).getCfg().contains("Stats.QuesturiCompletate")) {
			players.get(p).getCfg().set("Stats.QuesturiCompletate", players.get(p).getCfg().getInt("Stats.QuesturiCompletate")+1);
			players.get(p).save();
		}else {
			players.get(p).getCfg().set("Stats.QuesturiCompletate", 1);
			players.get(p).save();
		}
	}

}
