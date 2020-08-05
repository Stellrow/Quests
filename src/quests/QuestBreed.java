package quests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityBreedEvent;
import org.bukkit.plugin.java.JavaPlugin;

import main.QuestsMain;
import playermanager.PlayerManager;
import utils.Utils;

public class QuestBreed implements Listener{
	private QuestsMain pl = JavaPlugin.getPlugin(QuestsMain.class);
	//Variable generale
	private String nume;
	private List<String> descriere = new ArrayList<String>();
	private String nivelRecompensa;
	//Variabile statusuri
	private HashMap<UUID,Integer> playerStats = new HashMap<UUID,Integer>();
	private List<UUID> alreadyFinished = new ArrayList<UUID>();
	//Variabile missiune
	private int nrImperecheri;
	private EntityType tipMob;
	
	
	
	public QuestBreed(String nume,List<String>descriere,EntityType tipMob,Integer nrImperecheri,String nivelRecompensa) {
		this.nume=nume;
		this.descriere=descriere;
		this.tipMob=tipMob;
		this.nrImperecheri=nrImperecheri;
		this.nivelRecompensa=nivelRecompensa;
		pl.getServer().getPluginManager().registerEvents(this, pl);
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
				p.sendMessage(Utils.retTrans("verificaRamasImperecheri").replaceAll("%nrImperecheriCurente", playerStats.get(p.getUniqueId())+"").replaceAll("%nrImperecheriCerute", nrImperecheri+""));
			}
			
	//Events
			@EventHandler
			public void onBreed(EntityBreedEvent e) {
				if(e.getEntityType().equals(tipMob)) {
					if(e.getBreeder()instanceof Player) {
						Player p = (Player) e.getBreeder();
						if(alreadyFinished.contains(p.getUniqueId())) {
							return;
						}
						if(playerStats.containsKey(p.getUniqueId())) {
							playerStats.replace(p.getUniqueId(), playerStats.get(p.getUniqueId())+1);
							if(playerStats.get(p.getUniqueId())>=nrImperecheri) {
								//Finish quest for player
								playerStats.remove(p.getUniqueId());
								alreadyFinished.add(p.getUniqueId());
								//Add completion to player
								PlayerManager.completeQuest(p);
								//Alege reward
								pl.rew.reward(p, nivelRecompensa);
								p.sendMessage(Utils.retTrans("questComplete"));
							}
						}else {
							playerStats.put(p.getUniqueId(), 1);
						}
						
						
					}
				}
			}
	
}
