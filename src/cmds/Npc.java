package cmds;


public class Npc {
	/*
	private EntityPlayer ep;
	private Location loc;
	private QuestsMain pl = JavaPlugin.getPlugin(QuestsMain.class);
	public Npc(Location loc,String nume) {
		this.loc=loc;
		MinecraftServer server = ((CraftServer)Bukkit.getServer()).getServer();
		WorldServer world = ((CraftWorld)loc.getWorld()).getHandle();
		GameProfile gp = new GameProfile(UUID.randomUUID(),ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("Npc.Nume")));
		gp.getProperties().clear();
		gp.getProperties().put("textures", new Property("textures","eyJ0aW1lc3RhbXAiOjE1NjU0OTA5OTgwNDksInByb2ZpbGVJZCI6IjU2Njc1YjIyMzJmMDRlZTA4OTE3OWU5YzkyMDZjZmU4IiwicHJvZmlsZU5hbWUiOiJUaGVJbmRyYSIsInNpZ25hdHVyZVJlcXVpcmVkIjp0cnVlLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzdkZGExNzY0NjBkOWEwOGI0MmIyZGEyYWI5MTY4M2Q3YTcxMmRiN2ZlODYyMzAyNWIyNTk0MTc4MTEyOTA2OCJ9fX0=","YAS7BI78eu2w7wo2VNTDyBCPaZmBLwqbHhzbdKKx7vRBTfoDk9LbwSmNcSNo7EKLQIdRGeLtnVpCeJsArVyCVpcrkOXbxMcCWBXiElklGLddm0IkS2XxhFVwyz0y3xMVak8bd1RMdENln4pqAXAmK2OZ3BXv573fowOLPxyUv0FtJhSwW0eJygeG64klRRGTI2QgOHvby+4/IrIHLXokwwubx1qh9EjW/OcSuymm9Kyf/hOzRd6rsd3u4BnWf6wmcOviRgF7RMcAlSxqmMC7lp7fEP8CRy9/xxPY96bV4H0R75/o56TESPcb/JIvA0z3rhp4Jf12O4CD0nwMPtZv+mrQSJcGpDpgM0xSKkRiYJl4rUIeqBXOB5LldXi2CsRPf9g7RDU/zOJvx9NF3x+FUG+s5qWWUn8ZWr2YomSTSF/82CmYHpLKOdKvXYiNnRbWwVkFnvyc0ovcpux+jhNpTdsRpEGhQIPPW/1TkyMQIURIPSuqKJCEf2rET70F8RkSYSZ/vQoXOyFFhlGnOqz6W7nRxrlFhmbLcFqagXjLt1KvmsTczn6mPUyIZEP6RbqCepvK62YODa8Ls9ekQd+eJJ70LI57uttOCrR0O2e550oypDSUUuB6cBYLvpCjOP1qHs9gLBEmyH2DYx+vxaikpBIABeRlNHkUi1BcVQZ1xD8="));
		EntityPlayer npc = new EntityPlayer(server,world,
				gp,
				new PlayerInteractManager(world));
		npc.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
		for(Player op : Bukkit.getOnlinePlayers()) {
			PlayerConnection con = ((CraftPlayer)op).getHandle().playerConnection;
			con.sendPacket(new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.ADD_PLAYER,npc));
			con.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
			con.sendPacket(new PacketPlayOutEntityHeadRotation(npc,(byte) ((loc.getYaw() * 256.0F) / 360.0F)));
			removeTabList();
			
		}
		ep=npc;
		
	}
	
	public String[] getFromName(String name) {
        try {
            URL url_0 = new URL("https://api.mojang.com/users/profiles/minecraft/" + name);
            InputStreamReader reader_0 = new InputStreamReader(url_0.openStream());
            String uuid = new JsonParser().parse(reader_0).getAsJsonObject().get("id").getAsString();
     
            URL url_1 = new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid + "?unsigned=false");
            InputStreamReader reader_1 = new InputStreamReader(url_1.openStream());
            JsonObject textureProperty = new JsonParser().parse(reader_1).getAsJsonObject().get("properties").getAsJsonArray().get(0).getAsJsonObject();
            String texture = textureProperty.get("value").getAsString();
            String signature = textureProperty.get("signature").getAsString();
     
            return new String[] {texture, signature};
        } catch (IOException e) {
            System.err.println("Could not get skin data from session servers!");
            e.printStackTrace();
            return null;
        }
    }
	public Location getLocation() {
		return loc;
	}
	public int getEntityId() {
		return ep.getId();
	}
	public void removeEntity() {
		
	}
	public void removeTabList() {
		new BukkitRunnable() {

			@Override
			public void run() {
				for(Player op : Bukkit.getOnlinePlayers()) {
					PlayerConnection con = ((CraftPlayer)op).getHandle().playerConnection;
					con.sendPacket(new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.REMOVE_PLAYER,ep));
					
				}
				
			}
			
		}.runTaskLater(pl, 2);
		
	}
	public void spawnNpc(Player op) {
		PlayerConnection con = ((CraftPlayer)op).getHandle().playerConnection;
		con.sendPacket(new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.ADD_PLAYER,ep));
		con.sendPacket(new PacketPlayOutNamedEntitySpawn(ep));
		con.sendPacket(new PacketPlayOutEntityHeadRotation(ep,(byte) ((loc.getYaw() * 256.0F) / 360.0F)));
		new BukkitRunnable() {

			@Override
			public void run() {
				con.sendPacket(new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.REMOVE_PLAYER,ep));
				
			}
			
		}.runTaskLater(pl, 2);
		
		
	}
	public void setLocation(Location loc) {
		ep.getBukkitEntity().teleport(loc);
	}
	*/

}
