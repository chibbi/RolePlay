package org.ranin.rolePlay;

import java.util.logging.Logger;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Cunfig extends JavaPlugin {

    FileConfiguration custconfig = getConfig();

    @Override
    public void onEnable() {
        custconfig.addDefault("youAreAwesome", true);
        custconfig.options().copyDefaults(true);
        saveConfig();
    }
    public FileConfiguration getConfig() {
        return this.custconfig;
    }

}