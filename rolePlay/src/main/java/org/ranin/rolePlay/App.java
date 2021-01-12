package org.ranin.rolePlay;

/*
author: chibbi
description: "Initiator of the whole Plugin"
TODO: ["add all future classes", "let it do stuff"]
*/

import org.ranin.rolePlay.job.JobCommand;
import org.ranin.rolePlay.job.JobListener;
import org.ranin.rolePlay.job.Jobsql;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class App extends JavaPlugin {

    public static FileConfiguration config;
    public FileConfiguration customConfig = createCustomConfig("jobs.yml");

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

    private FileConfiguration createCustomConfig(String name) {
        File customConfigFile = new File(getDataFolder(), name);
        if (!customConfigFile.exists()) {
            customConfigFile.getParentFile().mkdirs();
            saveResource("custom.yml", false);
        }

        FileConfiguration cusconf = new YamlConfiguration();
        try {
            customConfig.load(customConfigFile);
            customConfig.addDefault("miner", true);
            customConfig.addDefault("farmer", true);
            customConfig.addDefault("lumberjack", true);
            customConfig.addDefault("builder", false);
            customConfig.addDefault("fisherman", false);
            customConfig.addDefault("hunter", true);
            customConfig.addDefault("warrior", true);
            customConfig.addDefault("knight", true);
            customConfig.addDefault("assassin", true);
            customConfig.addDefault("messenger", false);
            customConfig.addDefault("merchant", true);
            customConfig.addDefault("painter", true);
            customConfig.addDefault("stonemason", true);
            customConfig.addDefault("blacksmith", true);
            ArrayList<String> list = new ArrayList<String>();
            list.add("haste");
            list.add("luck");
            customConfig.addDefault("miner.effects.positives", list);
            list = new ArrayList<String>();
            list.add("");
            list.add("");
            customConfig.addDefault("farmer.effects", list);
            list = new ArrayList<String>();
            list.add("");
            list.add("");
            customConfig.addDefault("lumberjack.effects", list);
            list = new ArrayList<String>();
            list.add("");
            list.add("");
            customConfig.addDefault("builder.effects", list);
            list = new ArrayList<String>();
            list.add("dolphin's grace");
            list.add("conduit power");
            list.add("water breathing");
            customConfig.addDefault("fisherman.effects", list);
            list = new ArrayList<String>();
            list.add("");
            list.add("");
            customConfig.addDefault("hunter.effects", list);
            list = new ArrayList<String>();
            list.add("absorption");
            list.add("");
            customConfig.addDefault("warrior.effects", list);
            list = new ArrayList<String>();
            list.add("absorption");
            list.add("regeneration");
            list.add("hero of the village");
            customConfig.addDefault("knight.effects", list);
            list = new ArrayList<String>();
            list.add("speed");
            list.add("slow falling");
            list.add("bad omen");
            customConfig.addDefault("assassin.effects", list);
            list = new ArrayList<String>();
            list.add("speed");
            list.add("jump boost");
            customConfig.addDefault("messenger.effects", list);
            list = new ArrayList<String>();
            list.add("");
            list.add("");
            customConfig.addDefault("merchant.effects", list);
            list = new ArrayList<String>();
            list.add("");
            list.add("");
            customConfig.addDefault("painter.effects", list);
            list = new ArrayList<String>();
            list.add("");
            list.add("");
            customConfig.addDefault("stonemason.effects", list);
            list = new ArrayList<String>();
            list.add("");
            list.add("");
            customConfig.addDefault("blacksmith.effects", list);
            customConfig.options().copyDefaults(true);
            saveConfig();
        } catch (IOException | InvalidConfigurationException e) {
            getLogger().severe("\033[31mCould not create jobs.yml file\033[39m");
            getLogger().warning(e.getMessage());
        }
        return cusconf;
    }

    public FileConfiguration getCustomConfig() {
        return this.customConfig;
    }

}