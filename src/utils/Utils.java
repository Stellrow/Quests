package utils;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import main.QuestsMain;
import quests.QuestBreed;
import quests.QuestCraft;
import quests.QuestExplore;
import quests.QuestFish;
import quests.QuestSparge;
import quests.QuestUcidere;

public class Utils {
	private static QuestsMain pl = JavaPlugin.getPlugin(QuestsMain.class);
	public enum QuestType{
		QuestUcidere,
		QuestSparge,
		QuestBreed,
		QuestFish,
		QuestCraft,
		QuestExplore,
	}
	public static QuestType returnType(Object obj) {
		if(obj instanceof QuestUcidere) {
			return QuestType.QuestUcidere;
		}
		if(obj instanceof QuestSparge) {
			return QuestType.QuestSparge;
		}
		if(obj instanceof QuestBreed) {
			return QuestType.QuestBreed;
		}
		if(obj instanceof QuestFish) {
			return QuestType.QuestFish;
		}
		if(obj instanceof QuestCraft) {
			return QuestType.QuestCraft;
		}
		if(obj instanceof QuestExplore) {
			return QuestType.QuestExplore;
		}
		return null;
	}
	public static String retTrans(String path) {
		return ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("Mesaje."+path));
	}

}
