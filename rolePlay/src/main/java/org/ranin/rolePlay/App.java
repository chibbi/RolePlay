package org.ranin.rolePlay;

/*
author: chibbi
description: "Initiator of the whole Plugin"
TODO: ["add all future classes", "let it do stuff"]
*/

import org.ranin.rolePlay.Finance.FinanceCommand;
import org.ranin.rolePlay.job.InteractConfig;
import org.ranin.rolePlay.job.JobBlock;
import org.ranin.rolePlay.job.JobCommand;
import org.ranin.rolePlay.job.JobConfig;
import org.ranin.rolePlay.job.XpConfig;
import org.ranin.rolePlay.job.Jobsql;
import org.ranin.rolePlay.job.JobListener;
import org.ranin.rolePlay.job.JobToolsArmor;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class App extends JavaPlugin {

    public static FileConfiguration config;

    @Override
    public void onEnable() {
        config = getConfig();
        config.addDefault("imperatormode", true);
        config.addDefault("pvpmode", false);
        config.options().copyDefaults(true);
        saveConfig();
        // Initiating other Classes
        new JobConfig(getLogger()).getCustomConfig();
        new XpConfig(getLogger()).getCustomConfig();
        new InteractConfig(getLogger()).getCustomConfig();
        new Jobsql(getLogger()).createJobTable();
        this.getCommand("kit").setExecutor(new KitCommand(getLogger()));
        this.getCommand("job").setExecutor(new JobCommand(getLogger(), getConfig()));
        this.getCommand("money").setExecutor(new FinanceCommand(getLogger()));
        getServer().getPluginManager().registerEvents(new Listeners(), this);
        getServer().getPluginManager().registerEvents(new JobListener(getLogger(), config), this);
        getLogger().info("Hello, SpigotMC!");
        getLogger().info(Bukkit.getWorlds().toString());
        startScheduler();

    }

    public int id = 1;

    public void startScheduler() {
        Bukkit.getServer().getScheduler().runTaskTimer(this, new Runnable() {
            @Override
            public void run() {
                new JobBlock(getLogger()).loadEveryoneEffects(Bukkit.getWorld("world"));
                new JobBlock(getLogger()).loadEveryoneEffects(Bukkit.getWorld("world_nether"));
                new JobBlock(getLogger()).giveNearbyReg(Bukkit.getWorld("world"));
            }
        }, 10L, 10L);
    }

    @Override
    public void onDisable() {
        config = null;
        getLogger().info("See you again, SpigotMC!");
    }

}