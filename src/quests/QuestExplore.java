package quests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import main.QuestsMain;
import playermanager.PlayerManager;
import utils.Utils;

public class QuestExplore implements Listener{
	private QuestsMain pl = JavaPlugin.getPlugin(QuestsMain.class);
	//Variable generale
	private String nume;
	private List<String> descriere = new ArrayList<String>();
	private String nivelRecompensa;
	//Variabile statusuri
	private HashMap<UUID,Integer> playerStats = new HashMap<UUID,Integer>();
	private List<UUID> alreadyFinished = new ArrayList<UUID>();
	//Variabile missiune
	private int nrBlocuri;
	public QuestExplore(String nume,List<String>descriere,Integer nrBlocuri,String nivelRecompensa) {
		this.nume=nume;
		this.descriere=descriere;
		this.nrBlocuri=nrBlocuri;
		this.nivelRecompensa=nivelRecompensa;
		pl.getServer().getPluginManager().registerEvents(this, pl);
		startChecking();
	}
	//Utility
	public String getNume() {
		return ChatColor.translateAlternateColorCodes('&', nume);
	}
	public List<String> getDescriere(){
		List<String> tradus = new ArrayList<String>();
		for(String s : descriere) {
			tradus.add(ChatColor.translateAlternateColorCodes('&', s));
		}
		return tradus;
	}
	public boolean isFinished(Player p) {
		if(alreadyFinished.contains(p.getUniqueId())) {
			p.sendMessage(Utils.retTrans("revinoMaine"));
			return true;
		}
		if(playerStats.containsKey(p.getUniqueId())) {
			verificaRamase(p);
			return true;
		}
		return false;
	}
	public void verificaRamase(Player p) {
		p.sendMessage(Utils.retTrans("verificaRamasExplore").replaceAll("%nrBlocuriRamase", returnRem(p)+""));
	}
	
	//Handling
	public void countPlayer(Player p) {
		int toReach = p.getStatistic(Statistic.WALK_ONE_CM)/100+nrBlocuri;
		playerStats.put(p.getUniqueId(), toReach);
	}
	//Return remaining
	public int returnRem(Player p) {
		if(playerStats.containsKey(p.getUniqueId())) {
			return playerStats.get(p.getUniqueId())-p.getStatistic(Statistic.WALK_ONE_CM)/100;
		}
		return 0;
	}
	//Count
	public void startChecking() {
		new BukkitRunnable() {

			@Override
			public void run() {
				for(UUID uuid : playerStats.keySet()) {
					Player p = Bukkit.getPlayer(uuid);
					if(p.getStatistic(Statistic.WALK_ONE_CM)/100>=playerStats.get(p.getUniqueId())) {
					//Finish quest for player
					playerStats.remove(p.getUniqueId());
					alreadyFinished.add(p.getUniqueId());
					//Add completion to player
					PlayerManager.completeQuest(p);
					//Alege reward
					pl.rew.reward(p, nivelRecompensa);
					p.sendMessage(Utils.retTrans("questComplete"));
				}
				
			}
				
			}
			
		}.runTaskTimer(pl, 0, 20);
		
	}
}
