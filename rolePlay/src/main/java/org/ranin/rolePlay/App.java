package org.ranin.rolePlay;

/*
author: chibbi
description: "Initiator of the whole Plugin"
TODO: ["add all future classes", "let it do stuff"]
*/

import org.ranin.rolePlay.job.JobCommand;
import org.ranin.rolePlay.job.JobListener;
import org.ranin.rolePlay.job.Jobsql;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class App extends JavaPlugin {

    public static FileConfiguration config;

    @Override
    public void onEnable() {
        config = getConfig();
        config.addDefault("imperatormode", true);
        config.options().copyDefaults(true);
        saveConfig();
        new Jobsql(getLogger()).createJobTable();
        // Initiating other Classes
        this.getCommand("kit").setExecutor(new KitCommand(getLogger()));
        this.getCommand("job").setExecutor(new JobCommand(getLogger()));
        getServer().getPluginManager().registerEvents(new Listeners(), this);
        getServer().getPluginManager().registerEvents(new JobListener(getLogger()), this);
        getLogger().info("Hello, SpigotMC!");
    }

    @Override
    public void onDisable() {
        config = null;
        getLogger().info("See you again, SpigotMC!");
    }

}