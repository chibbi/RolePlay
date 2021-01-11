package org.ranin.rolePlay;

import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;

public class App extends JavaPlugin {
    @Override
    public void onEnable() {
        // Initiating other Classes
        config.addDefault("youAreAwesome", true);
        config.options().copyDefaults(true);
        saveConfig();
        
        this.getCommand("kit").setExecutor(new Command(getLogger()));
        getServer().getPluginManager().registerEvents(new MyListener(), this);
        getLogger().info("Hello, SpigotMC!");
    }
    @Override
    public void onDisable() {
        getLogger().info("See you again, SpigotMC!");
    }
}