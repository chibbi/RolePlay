package org.ranin.rolePlay;
import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;
public class App extends JavaPlugin {
    @Override
    public void onEnable() {
        // Register our command "kit" (set an instance of your command class as executor)
        this.getCommand("kit").setExecutor(new Commands(getLogger()));
        getLogger().info("Hello, SpigotMC!");
    }
    @Override
    public void onDisable() {
        getLogger().info("See you again, SpigotMC!");
    }
}