package main;

import java.io.File;
import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.java.JavaPlugin;

import cmds.QuestsCmd;
import guis.GuiEvents;
import guis.QuestsRewardsGui;
import npc.CustomTrader;
import npc.Interact;
import playermanager.PlayerManager;
import quests.QuestsHandler;
import rewards.Rewards;

public class QuestsMain extends JavaPlugin{
	//Variables
	public File teamsFolder;
	public Rewards rew;
	public QuestsHandler qhandler;
	public QuestsRewardsGui gui;
	public CustomTrader npc;
	//Quests.yml
	private File quests;
	private FileConfiguration questscfg;
	//Rewards.yml
	private File rewards;
	private FileConfiguration rewardscfg;
	
	public Entity villager;
	
	//Start
	public void onEnable() {
		registerConfigs();	
		loadFolder();
		rew = new Rewards();
		qhandler=new QuestsHandler();
		getServer().getPluginManager().registerEvents(new GuiEvents(), this);
		getServer().getPluginManager().registerEvents(new PlayerManager(), this);
		checkNpc();
		getServer().getPluginManager().registerEvents(new Interact(), this);
		gui=new QuestsRewardsGui();
		getCommand("quests").setExecutor(new QuestsCmd());
	}
	public void checkNpc() {
		if(getConfig().contains("LocatieNpc")) {
		Location loc = getConfig().getLocation("LocatieNpc.Locatie");
		loc.getWorld().addPluginChunkTicket(loc.getChunk().getX(), loc.getChunk().getZ(), this);
		checkNearby(loc);
		npc=new CustomTrader(loc.getWorld(),loc);
		}
	}
	
	//Utility
		private void checkNearby(Location loc) {
			for(Entity e : loc.getWorld().getNearbyEntities(loc, 20, 20, 20)) {
				if(e.getType()==EntityType.WANDERING_TRADER) {
					e.remove();
				}
			}
			
		}
		private void registerConfigs() {
			loadConfig();
			createQuests();
			createRewards();
		}
		//Config
		private void loadConfig() {
			getConfig().options().copyDefaults(true);
			saveConfig();
		}
		//Quests.yml
		private void createQuests() {
			quests = new File(getDataFolder(),"quests.yml");
			if(!quests.exists()) {
				quests.getParentFile().mkdirs();
				saveResource("quests.yml",true);
			}
			loadQuests();
		}
		public void loadQuests() {
			questscfg = YamlConfiguration.loadConfiguration(quests);
		}
		public void saveQuests() {
			try {
				questscfg.save(quests);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		public FileConfiguration getQuests() {
			return questscfg;
		}
		//Rewards.yml
		private void createRewards() {
			rewards = new File(getDataFolder(),"rewards.yml");
			if(!rewards.exists()) {
				rewards.getParentFile().mkdirs();
				saveResource("rewards.yml",true);
			}
			loadRewards();
		}
		public void loadRewards() {
			rewardscfg = YamlConfiguration.loadConfiguration(rewards);
		}
		public void saveRewards() {
			try {
				rewardscfg.save(rewards);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		public FileConfiguration getRewards() {
			return rewardscfg;
		}
		 //Players folder
	    private void loadFolder() {
			teamsFolder = new File("plugins/Quests/players/");
			if(!teamsFolder.exists()) {
				teamsFolder.mkdir();
			}
			getConfig().options().copyDefaults(true);
			saveConfig();
		}
		
	
	
	
	
}
