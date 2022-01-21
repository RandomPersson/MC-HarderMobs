package pl.org.mensa.rp.mc.HarderMobs;

import org.bukkit.plugin.java.JavaPlugin;

public class HarderMobsPlugin extends JavaPlugin {
	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(new CreatureSpawnListener(), this);
	}
}
