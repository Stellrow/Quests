package quests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;

import main.QuestsMain;
import playermanager.PlayerManager;
import utils.Utils;

public class QuestSparge implements Listener{
	private QuestsMain pl = JavaPlugin.getPlugin(QuestsMain.class);
	//Variable generale
	private String nume;
	private List<String> descriere = new ArrayList<String>();
	private String nivelRecompensa;
	//Variabile quest sparge
	private Material tipBloc;
	private int nrBlocuri;
	//Variabile statusuri
	private HashMap<UUID,Integer> playerStats = new HashMap<UUID,Integer>();
	private List<UUID> alreadyFinished = new ArrayList<UUID>();
	private List<Block> antiExploit = new ArrayList<Block>();
	
	
	public QuestSparge(String nume,List<String>descriere,String tip,Material tipBloc,Integer nrBlocuri,String nivelRecompensa) {
		this.nume=nume;
		this.descriere=descriere;
		this.tipBloc=tipBloc;
		this.nrBlocuri=nrBlocuri;
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
			p.sendMessage(Utils.retTrans("verificaRamasSpargeBlock").replaceAll("%nrBlocuriCurente", playerStats.get(p.getUniqueId())+"").replaceAll("%nrBlocuriCerute", nrBlocuri+""));
		}
	//Events
		
		//Verificare antiexploit
		@EventHandler
		public void placeBloc(BlockPlaceEvent e) {
			if(e.getBlock().getType()==tipBloc) {
				antiExploit.add(e.getBlock());
			}
		}
		
		//Verificare bloc normal
		@EventHandler
		public void breakBloc(BlockBreakEvent e) {
			if(e.getBlock().getType()==tipBloc) {
				Player p = e.getPlayer();
				if(antiExploit.contains(e.getBlock())) {
					return;
				}
				if(alreadyFinished.contains(p.getUniqueId())) {
					return;
				}
				if(playerStats.containsKey(p.getUniqueId())) {
					playerStats.replace(p.getUniqueId(), playerStats.get(p.getUniqueId())+1);
					if(playerStats.get(p.getUniqueId())>=nrBlocuri) {
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
