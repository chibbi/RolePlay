package org.ranin.rolePlay;

/*
author: chibbi
description: "Initiator of the whole Plugin"
TODO: ["add all future classes", "let it do stuff"]
*/

import org.ranin.rolePlay.job.JobCommand;
import org.ranin.rolePlay.job.Jobsql;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class App extends JavaPlugin {

    public static FileConfiguration config;
    private Jobsql sql = new Jobsql(getLogger());

    @Override
    public void onEnable() {
        config = getConfig();
        config.addDefault("youAreAwesome", true);
        config.options().copyDefaults(true);
        saveConfig();
        if (!sql.connect()) {
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "shutdown");
        }
        sql.createJobTable();
        // Initiating other Classes
        this.getCommand("kit").setExecutor(new KitCommand(getLogger()));
        this.getCommand("job").setExecutor(new JobCommand(getLogger()));
        getServer().getPluginManager().registerEvents(new Listeners(), this);
        getLogger().info("Hello, SpigotMC!");
    }

    @Override
    public void onDisable() {
        config = null;
        sql.disconnect();
        getLogger().info("See you again, SpigotMC!");
    }

}