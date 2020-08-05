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
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

import main.QuestsMain;
import playermanager.PlayerManager;
import utils.Utils;

public class QuestUcidere implements Listener{
	private QuestsMain pl = JavaPlugin.getPlugin(QuestsMain.class);
	//Variable generale
	private String nume;
	private List<String> descriere = new ArrayList<String>();
	private String tip;
	private String nivelRecompensa;
	
	//Variabile quest ucidere
	private EntityType tipmonstru;
	private int nrUcideri;
	
	private HashMap<UUID,Integer> playerStatsUcidere = new HashMap<UUID,Integer>();
	private List<UUID> alreadyFinished = new ArrayList<UUID>();
	
	public QuestUcidere(String nume,List<String>descriere,String tip,EntityType tipmonstru,Integer nrUcideri,String nivelRecompensa) {
		this.nume=nume;
		this.descriere=descriere;
		this.tip=tip;
		this.tipmonstru=tipmonstru;
		this.nrUcideri=nrUcideri;
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
				if(playerStatsUcidere.containsKey(p.getUniqueId())) {
					verificaRamase(p);
					return true;
				}
				return false;
			}
			public void verificaRamase(Player p) {
				p.sendMessage(Utils.retTrans("verificaRamasUcideri").replaceAll("%nrUcideriCurente", playerStatsUcidere.get(p.getUniqueId())+"").replaceAll("%nrUcideriCerute", nrUcideri+""));
			}
			
	//Events
	@EventHandler
	public void entDie(EntityDeathEvent e) {
		if(tip.equalsIgnoreCase("ucidere")) {
			if(e.getEntityType()==tipmonstru) {
				if(e.getEntity().getKiller()instanceof Player) {
					Player p = e.getEntity().getKiller();
					if(alreadyFinished.contains(p.getUniqueId())) {
						return;
					}
					if(playerStatsUcidere.containsKey(p.getUniqueId())) {
						playerStatsUcidere.replace(p.getUniqueId(), playerStatsUcidere.get(p.getUniqueId())+1);
						if(playerStatsUcidere.get(p.getUniqueId())>=nrUcideri) {
							//Finish quest for player
							playerStatsUcidere.remove(p.getUniqueId());
							alreadyFinished.add(p.getUniqueId());
							//Add completion to player
							PlayerManager.completeQuest(p);
							//Alege reward
							pl.rew.reward(p, nivelRecompensa);
							p.sendMessage(Utils.retTrans("questComplete"));
						}
					}else {
						playerStatsUcidere.put(p.getUniqueId(), 1);
					}
					
					
				}
			}
		}
		
	}
}
