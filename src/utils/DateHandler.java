package utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.bukkit.plugin.java.JavaPlugin;

import main.QuestsMain;


public class DateHandler {
	private static QuestsMain pl = JavaPlugin.getPlugin(QuestsMain.class);
	private static int Day;
	
	
	public static int getDate(){
	Date now = new Date();
	Calendar cal = GregorianCalendar.getInstance();
	cal.setTime(now);
	Day = cal.get(Calendar.DAY_OF_MONTH);
	return Day;
	
	}
	public static void salveazaData(int data) {
	 pl.getConfig().set("DataAcum", data);
	pl.saveConfig();
		
	}
	
}
