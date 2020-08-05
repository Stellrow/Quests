package quests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerFishEvent.State;
import org.bukkit.plugin.java.JavaPlugin;

import main.QuestsMain;
import playermanager.PlayerManager;
import utils.Utils;

public class QuestFish implements Listener{
	private QuestsMain pl = JavaPlugin.getPlugin(QuestsMain.class);
	//Variable generale
	private String nume;
	private List<String> descriere = new ArrayList<String>();
	private String nivelRecompensa;
	//Variabile statusuri
	private HashMap<UUID,Integer> playerStats = new HashMap<UUID,Integer>();
	private List<UUID> alreadyFinished = new ArrayList<UUID>();
	//Variabile missiune
	private Material tipPeste;
	private int nrPesti;
	
	public QuestFish(String nume,List<String>descriere,Material tipPeste,Integer nrPesti,String nivelRecompensa) {
		this.nume=nume;
		this.descriere=descriere;
		this.tipPeste=tipPeste;
		this.nrPesti=nrPesti;
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
		p.sendMessage(Utils.retTrans("verificaRamasPesti").replaceAll("%nrPestiCurent", playerStats.get(p.getUniqueId())+"").replaceAll("%nrPestiCeruti", nrPesti+""));
	}
	
	//Events
	@EventHandler
	public void onFish(PlayerFishEvent e) {
		
		Player p = e.getPlayer();
		if(alreadyFinished.contains(p.getUniqueId())) {
			return;
		}
		if(e.getState()==State.CAUGHT_FISH) {
			if(((Item) e.getCaught()).getItemStack().getType()==tipPeste) {
			if(playerStats.containsKey(p.getUniqueId())) {
				playerStats.replace(p.getUniqueId(), playerStats.get(p.getUniqueId())+1);
				if(playerStats.get(p.getUniqueId())>=nrPesti) {
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
			
			
		}}
	}
	
}
