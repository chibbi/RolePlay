package org.ranin.rolePlay;

import java.util.logging.Logger;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class App extends JavaPlugin {

    public static FileConfiguration config;

    @Override
    public void onEnable() {
        config = getConfig();
        config.addDefault("youAreAwesome", true);
        config.options().copyDefaults(true);
        saveConfig();
        // Initiating other Classes
        this.getCommand("kit").setExecutor(new Commands(getLogger()));
        getServer().getPluginManager().registerEvents(new Listeners(), this);
        getLogger().info("Hello, SpigotMC!");
    }
    @Override
    public void onDisable() {
        config = null;
        getLogger().info("See you again, SpigotMC!");
    }

}