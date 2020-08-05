package npc;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.v1_16_R1.CraftWorld;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.WanderingTrader;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import main.QuestsMain;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_16_R1.EntityTypes;
import net.minecraft.server.v1_16_R1.EntityVillagerTrader;

public class CustomTrader extends EntityVillagerTrader implements Listener{
	private QuestsMain pl = JavaPlugin.getPlugin(QuestsMain.class);
	private WanderingTrader npc;

	public CustomTrader(World world,Location loc) {
		super(EntityTypes.WANDERING_TRADER, ((CraftWorld)world).getHandle());
		npc=(WanderingTrader) this.getBukkitEntity();
		npc.setCustomName(ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("Npc.Nume")));
		npc.setInvulnerable(true);
		npc.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(200);
		npc.setHealth(200);
		npc.setAI(false);
		this.getWorld().addEntity(this);
		npc.teleport(loc);
		pl.npc=this;
	}
	public void kill() {
		npc.remove();
	}
	public void teleport(Location to) {
		npc.teleport(to);
	}
	public LivingEntity getEnt() {
		return npc;
	}

}
