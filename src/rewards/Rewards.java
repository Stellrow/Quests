package rewards;

import java.util.Random;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import main.QuestsMain;
import utils.RandomCollections;

public class Rewards {
	private RandomCollections<CommandReward> comenziNormale = new RandomCollections<CommandReward>();
	private RandomCollections<ItemStack> itemeNormale = new RandomCollections<ItemStack>();
	private RandomCollections<ItemStack> itemeEpic = new RandomCollections<ItemStack>();
	private RandomCollections<CommandReward> comenziEpic = new RandomCollections<CommandReward>();
	private QuestsMain pl = JavaPlugin.getPlugin(QuestsMain.class);
	public Rewards() {
		loadRewards();
	}
	
	
	public void loadRewards() {
		//Comenzi
		comenziNormale.clear();
		if(pl.getRewards().contains("RecompenseNormal.Comenzi")) {
			for(String s : pl.getRewards().getConfigurationSection("RecompenseNormal.Comenzi").getKeys(false)) {
				comenziNormale.add(pl.getRewards().getDouble("RecompenseNormal.Comenzi."+s+".Sansa"),new CommandReward("RecompenseNormal.Comenzi."+s+".Comenzi"));
			}
		}
		comenziEpic.clear();
		if(pl.getRewards().contains("RecompenseEpic.Comenzi")) {
			for(String s : pl.getRewards().getConfigurationSection("RecompenseEpic.Comenzi").getKeys(false)) {
				comenziEpic.add(pl.getRewards().getDouble("RecompenseEpic.Comenzi."+s+".Sansa"),new CommandReward("RecompenseEpic.Comenzi."+s+".Comenzi"));
			}
		}
		//Iteme
		itemeNormale.clear();
		if(pl.getRewards().contains("RecompenseNormal.Iteme")) {
			for(String s : pl.getRewards().getConfigurationSection("RecompenseNormal.Iteme").getKeys(false)) {
				itemeNormale.add(pl.getRewards().getDouble("RecompenseNormal.Iteme."+s+".Sansa"), pl.getRewards().getItemStack("RecompenseNormal.Iteme."+s+".Item"));
			}
		}
		itemeEpic.clear();
		if(pl.getRewards().contains("RecompenseEpic.Iteme")) {
			for(String s : pl.getRewards().getConfigurationSection("RecompenseEpic.Iteme").getKeys(false)) {
				itemeEpic.add(pl.getRewards().getDouble("RecompenseEpic.Iteme."+s+".Sansa"), pl.getRewards().getItemStack("RecompenseEpic.Iteme."+s+".Item"));
			}
		}
	}
	//Rewards
		public void reward(Player p,String nivelRecompensa) {
			Random rnd = new Random();
			if(rnd.nextInt(100)<=50) {
				if(nivelRecompensa.equalsIgnoreCase("normal")) {
					alegeItemNormal(p);
					return;
				}
				if(nivelRecompensa.equalsIgnoreCase("epic")) {
					alegeItemEpic(p);
					return;
				}
			}else {
				if(nivelRecompensa.equalsIgnoreCase("normal")) {
					alegeComandaNormal(p);
					return;
				}
				if(nivelRecompensa.equalsIgnoreCase("epic")) {
					alegeComandaEpic(p);
					return;
				}
			}
			
		}
	
	
	
	public void alegeItemNormal(Player p) {
		p.getInventory().addItem(itemeNormale.next());
	}
	public void alegeItemEpic(Player p) {
		p.getInventory().addItem(itemeEpic.next());
	}
	public void alegeComandaNormal(Player p) {
		comenziNormale.next().executeCommands(p);
	}
	public void alegeComandaEpic(Player p) {
		comenziEpic.next().executeCommands(p);
	}
	

}