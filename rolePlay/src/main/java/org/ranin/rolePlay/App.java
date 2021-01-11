package org.ranin.rolePlay;

import java.util.logging.Logger;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class App extends JavaPlugin {

    FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        // Initiating other Classes
        config.addDefault("youAreAwesome", true);
        config.options().copyDefaults(true);
        saveConfig();

        this.getCommand("kit").setExecutor(new Commands(getLogger()));
        getServer().getPluginManager().registerEvents(new Listeners(), this);
        getLogger().info("Hello, SpigotMC!");
    }
    @Override
    public void onDisable() {
        getLogger().info("See you again, SpigotMC!");
    }
}