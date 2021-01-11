package org.ranin.rolePlay;

import java.util.logging.Logger;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class App extends JavaPlugin {

    Cunfig cunf = new Cunfig();
    FileConfiguration custconfig = cunf.getConfig();

    @Override
    public void onEnable() {
        // Initiating other Classes
        this.getCommand("kit").setExecutor(new Commands(getLogger()));
        getServer().getPluginManager().registerEvents(new Listeners(), this);
        getLogger().info("Hello, SpigotMC!");
    }
    @Override
    public void onDisable() {
        getLogger().info("See you again, SpigotMC!");
    }

}