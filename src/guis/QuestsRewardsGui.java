package guis;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import main.QuestsMain;


public class QuestsRewardsGui {
	private QuestsMain pl = JavaPlugin.getPlugin(QuestsMain.class);
	private Inventory normale = Bukkit.createInventory(null, 54,"Iteme Normal");
	private Inventory epic = Bukkit.createInventory(null, 54,"Iteme Epic");

	
	public void loadNormale() {
		normale.clear();
		if(pl.getRewards().contains("RecompenseNormal.Iteme")) {
			for(String s : pl.getRewards().getConfigurationSection("RecompenseNormal.Iteme").getKeys(false)) {
				normale.setItem(Integer.parseInt(s), pl.getRewards().getItemStack("RecompenseNormal.Iteme."+s+".Item"));
			}
		}
	}
	public void loadBoss() {
		epic.clear();
		if(pl.getRewards().contains("RecompenseEpic.Iteme")) {
			for(String s : pl.getRewards().getConfigurationSection("RecompenseEpic.Iteme").getKeys(false)) {
				epic.setItem(Integer.parseInt(s), pl.getRewards().getItemStack("RecompenseEpic.Iteme."+s+".Item"));
			}
		}
	}
	public void openNormale(Player p) {
		loadNormale();
		p.openInventory(normale);
	}
	public void openBoss(Player p) {
		loadBoss();
		p.openInventory(epic);
	}
}
