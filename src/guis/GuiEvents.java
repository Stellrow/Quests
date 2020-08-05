package guis;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import main.QuestsMain;



public class GuiEvents implements Listener{
	private QuestsMain pl = JavaPlugin.getPlugin(QuestsMain.class);
	
	@EventHandler
	public void closeInv(InventoryCloseEvent e) {
		if(e.getView().getTitle().equals("Iteme Normal")) {
			for(int x = 0;x<=53;x++) {
				if(e.getInventory().getItem(x)!=null) {
					saveItemNormal(x,e.getInventory().getItem(x));
				}else {
					pl.getRewards().set("RecompenseNormal.Iteme."+x, null);
				}
			}
			pl.saveRewards();
		}
		if(e.getView().getTitle().equals("Iteme Epic")) {
			for(int x = 0;x<=53;x++) {
				if(e.getInventory().getItem(x)!=null) {
					saveItemBoss(x,e.getInventory().getItem(x));
				}else {
					pl.getRewards().set("RecompenseEpic.Iteme."+x, null);
				}
			}
			pl.saveRewards();
		}
	}
	
	
	private void saveItemNormal(Integer i,ItemStack item) {
		pl.getRewards().set("RecompenseNormal.Iteme."+i+".Item", item);
		if(!pl.getRewards().contains("RecompenseNormal.Iteme."+i+".Sansa")) {
			pl.getRewards().set("RecompenseNormal.Iteme."+i+".Sansa", 50);
		}
	}
	private void saveItemBoss(Integer i,ItemStack item) {
		pl.getRewards().set("RecompenseEpic.Iteme."+i+".Item", item);
		if(!pl.getRewards().contains("RecompenseEpic.Iteme."+i+".Sansa")) {
			pl.getRewards().set("RecompenseEpic.Iteme."+i+".Sansa", 50);
		}
	}

}
