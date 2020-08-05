package npc;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

import main.QuestsMain;

@SuppressWarnings("unused")
public class Interact implements Listener{
	private QuestsMain pl = JavaPlugin.getPlugin(QuestsMain.class);
	/*
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		new BukkitRunnable() {

			@Override
			public void run() {
				if(pl.npc!=null) {
					pl.npc.spawnNpc(e.getPlayer());
				}
				
			}
			
		}.runTaskLater(pl, 20);
		
		injectPlayer(e.getPlayer());
	}
	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		removePlayer(e.getPlayer());
	}
	@EventHandler
	public void worldChange(PlayerChangedWorldEvent e) {
		if(pl.npc!=null) {
			pl.npc.spawnNpc(e.getPlayer());
		}
	}
	@EventHandler
	public void onTeleport(PlayerTeleportEvent e) {
		if(e.getTo().getWorld().equals(e.getFrom().getWorld())) {
			if(pl.npc!=null) {
				pl.npc.spawnNpc(e.getPlayer());
			}
		}
	}
	
	private void removePlayer(Player player) {
		Channel channel = ((CraftPlayer)player).getHandle().playerConnection.networkManager.channel;
		channel.eventLoop().submit(()->{
		channel.pipeline().remove(player.getName());
		return null;
		});
	}
	private void injectPlayer(Player player) {
		ChannelDuplexHandler channelDuplexHandler = new ChannelDuplexHandler() {
			@Override
			public void channelRead(ChannelHandlerContext channelHandlerContext,Object packet) {
				if(packet instanceof PacketPlayInUseEntity) {
					try {
						PacketPlayInUseEntity pack = (PacketPlayInUseEntity) packet;
						readPacket(pack,player);
						}
					 catch (Exception e) {
						e.printStackTrace();
					}
				}
				try {
					super.channelRead(channelHandlerContext, packet);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		};
		ChannelPipeline pipeline = ((CraftPlayer)player).getHandle().playerConnection.networkManager.channel.pipeline();
		pipeline.addBefore("packet_handler", player.getName(), channelDuplexHandler);
		
	}
	public void readPacket(Packet<?> packet,Player p)
    {
        if(packet.getClass().getSimpleName().equalsIgnoreCase("PacketPlayInUseEntity"))
        {
        	PacketPlayInUseEntity pack = (PacketPlayInUseEntity) packet;
            int id = (int) getValue(packet, "a");
            if(getValue(packet, "action").toString().equalsIgnoreCase("interact"))
            {
            	if(pack.c()==EnumHand.MAIN_HAND) {
            	if(pl.npc.getEntityId()==id) {
            		pl.qhandler.startQuest(p);
            	}
                }
            	}
            }
        }
    

    private Object getValue(Object instance, String name)
    {
        Object result = null;
       
        try
        {
            Field field = instance.getClass().getDeclaredField(name);
           
            field.setAccessible(true);
           
            result = field.get(instance);
           
            field.setAccessible(false);
        } catch(Exception exception)
        {
            exception.printStackTrace();
        }
       
        return result;
    }*/
	@EventHandler
	public void onInteract(PlayerInteractEntityEvent e) {
		if(e.getRightClicked().equals(pl.npc.getEnt())) {
			e.setCancelled(true);
			Bukkit.dispatchCommand(e.getPlayer(), "quests");
		}
	}
	
}
