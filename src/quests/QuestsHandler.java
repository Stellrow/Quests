package quests;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import main.QuestsMain;
import utils.DateHandler;
import utils.Utils;
import utils.Utils.QuestType;

public class QuestsHandler {
	private QuestsMain pl = JavaPlugin.getPlugin(QuestsMain.class);
	public List<String> availableQuests = new ArrayList<String>();
	private Object questActiv;
	
	//Instanceing
	public QuestsHandler() {
		loadAvailableQuests();
		pickRandom();
		
	}
	
	//Utility
	public void loadAvailableQuests() {
		availableQuests.clear();
		if(pl.getQuests().contains("Questuri")) {
			for(String s : pl.getQuests().getConfigurationSection("Questuri").getKeys(false)) {
				availableQuests.add(s);
			}
		}
	}
	public void pickRandom() {
		Random rnd = new Random();
		int picked = rnd.nextInt(availableQuests.size());
		loadQuest(picked);
	}
	public void loadQuest(Integer choice) {
		String picked = availableQuests.get(choice);
		String tip = pl.getQuests().getString("Questuri."+picked+".Tip");
		String nume = pl.getQuests().getString("Questuri."+picked+".Nume");
		List<String> descriere = pl.getQuests().getStringList("Questuri."+picked+".Descriere");
		String nivelRecompensa = pl.getQuests().getString("Questuri."+picked+".NivelRecompensa");
		//Quest ucidere
		if(tip.equalsIgnoreCase("Ucidere")) {
			int nrUcideri = pl.getQuests().getInt("Questuri."+picked+".NrUcideri");
			EntityType tipmonstru = EntityType.valueOf(pl.getQuests().getString("Questuri."+picked+".TipMonstru"));
			questActiv = new QuestUcidere(nume,descriere,tip,tipmonstru,nrUcideri,nivelRecompensa);
		}
		//Quest sparge block
		if(tip.equalsIgnoreCase("SpargeBlock")) {
			Material mat = Material.valueOf(pl.getQuests().getString("Questuri."+picked+".TipBlock"));
			int nrBlocuri = pl.getQuests().getInt("Questuri."+picked+".NrBlocuri");
			questActiv = new QuestSparge(nume,descriere,tip,mat,nrBlocuri,nivelRecompensa);
		}
		//Quest breed
		if(tip.equalsIgnoreCase("Imperechere")) {
			EntityType tipMob = EntityType.valueOf(pl.getQuests().getString("Questuri."+picked+".TipMob"));
			int nrImperecheri = pl.getQuests().getInt("Questuri."+picked+".NrImperecheri");
			questActiv = new QuestBreed(nume,descriere,tipMob,nrImperecheri,nivelRecompensa);
		}
		//Quest fish
		if(tip.equalsIgnoreCase("Pescuit")) {
			Material tipPeste = Material.valueOf(pl.getQuests().getString("Questuri."+picked+".TipMob"));
			int nrPesti = pl.getQuests().getInt("Questuri."+picked+".NrPesti");
			questActiv = new QuestFish(nume,descriere,tipPeste,nrPesti,nivelRecompensa);
		}
		//Quest Craft
		if(tip.equalsIgnoreCase("Craft")) {
			Material tipItem = Material.valueOf(pl.getQuests().getString("Questuri."+picked+".TipItem"));
			int nrIteme = pl.getQuests().getInt("Questuri."+picked+".NrIteme");
			questActiv = new QuestCraft(nume,descriere,tipItem,nrIteme,nivelRecompensa);
		}
		//Quest explorare
		if(tip.equalsIgnoreCase("Explorare")) {
			int nrBlocuri = pl.getQuests().getInt("Questuri."+picked+".Distanta");
			questActiv = new QuestExplore(nume,descriere,nrBlocuri,nivelRecompensa);
		}
		
		
	}
	public Object getQuestActive() {
		return questActiv;
	}
	
	public void startQuest(Player p) {
		if(Utils.returnType(questActiv)==QuestType.QuestUcidere){
			QuestUcidere qu = (QuestUcidere) questActiv;
			if(!qu.isFinished(p)){
				p.sendMessage(qu.getNume());
				for(String s : qu.getDescriere()) {
					p.sendMessage(s);
				}
			}
			return;
		}
		if(Utils.returnType(questActiv)==QuestType.QuestSparge) {
			QuestSparge qu = (QuestSparge) questActiv;
			if(!qu.isFinished(p)){
				p.sendMessage(qu.getNume());
				for(String s : qu.getDescriere()) {
					p.sendMessage(s);
				}
			}
			return;
		}
		
		if(Utils.returnType(questActiv)==QuestType.QuestBreed) {
			QuestBreed qu = (QuestBreed) questActiv;
			if(!qu.isFinished(p)){
				p.sendMessage(qu.getNume());
				for(String s : qu.getDescriere()) {
					p.sendMessage(s);
				}
			}
			return;
		}
		
		if(Utils.returnType(questActiv)==QuestType.QuestFish) {
			QuestFish qu = (QuestFish) questActiv;
			if(!qu.isFinished(p)){
				p.sendMessage(qu.getNume());
				for(String s : qu.getDescriere()) {
					p.sendMessage(s);
				}
			}
			return;
		}
		
		if(Utils.returnType(questActiv)==QuestType.QuestCraft) {
			QuestCraft qu = (QuestCraft) questActiv;
			if(!qu.isFinished(p)){
				p.sendMessage(qu.getNume());
				for(String s : qu.getDescriere()) {
					p.sendMessage(s);
				}
			}
			return;
		}
		
		if(Utils.returnType(questActiv)==QuestType.QuestExplore) {
			QuestExplore qu = (QuestExplore) questActiv;
			if(!qu.isFinished(p)){
				p.sendMessage(qu.getNume());
				for(String s : qu.getDescriere()) {
					p.sendMessage(s);
				}
				qu.countPlayer(p);
			}
			return;
		}
		
		
	}
	
	
	public void incepeOrarul() {
		int ziuaAstazi = DateHandler.getDate();
		if(!pl.getConfig().contains("DataAcum")) {
			DateHandler.salveazaData(ziuaAstazi);
		}
		new BukkitRunnable() {

			@Override
			public void run() {
				int ziuaAstazi = DateHandler.getDate();
				int ziuaSalvata = pl.getConfig().getInt("DataAcum");
			if(ziuaAstazi!=ziuaSalvata) {
				DateHandler.salveazaData(ziuaAstazi);
				loadAvailableQuests();
				pickRandom();
			}
				
				
			}
			
		}.runTaskTimerAsynchronously(pl, 0, 3600*20);
		
		
		
	}

}
