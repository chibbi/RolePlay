package org.ranin.rolePlay.job;

/*
author: "chibbi"
description: "Job Config interface"
TODO: []
*/

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.ArrayList;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class JobConfig {

    private Logger log;
    private String name = "jobs.yml";

    public JobConfig(Logger logg) {
        log = logg;
    }

    private FileConfiguration createCustomConfig(String name) {
        File customConfigFile = new File("plugins/rolePlay/", name);
        FileConfiguration cusconf = YamlConfiguration.loadConfiguration(customConfigFile);
        if (!customConfigFile.exists()) {
            try {
                customConfigFile.createNewFile();
            } catch (IOException e) {
                log.warning("\033[31mCould not create a custom config\033[39m");
                log.info(e.getMessage());
            }
            ArrayList<String> list = new ArrayList<String>();
            cusconf.set("farmer", true);
            cusconf.set("lumberjack", true);
            cusconf.set("miner", true);
            cusconf.set("builder", true);
            // ccusconf.set("fisherman", true);
            cusconf.set("hunter", true);
            cusconf.set("warrior", true);
            cusconf.set("knight", true);
            cusconf.set("assassin", true);
            // ccusconf.set("messenger", true);
            // ccusconf.set("merchant", true);
            // ccusconf.set("painter", true);
            // ccusconf.set("stonemason", true);
            cusconf.set("blacksmith", true);
            // cusconf.set("mage", true);
            list = new ArrayList<String>();
            list.add("FAST_DIGGING");
            list.add("LUCK");
            cusconf.set("miner.effects.positives", list);
            list = new ArrayList<String>();
            list.add("ABSORPTION");
            list.add("NIGHT_VISION");
            cusconf.set("warrior.effects.positives", list);
            list = new ArrayList<String>();
            list.add("ABSORPTION");
            list.add("HERO_OF_THE_VILLAGE");
            cusconf.set("knight.effects.positives", list);
            list = new ArrayList<String>();
            list.add("ABSORPTION");
            list.add("JUMP");
            list.add("NIGHT_VISION");
            cusconf.set("assassin.effects.positives", list);
            try {
                cusconf.save(customConfigFile);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return cusconf;
    }

    public FileConfiguration getCustomConfig() {
        return createCustomConfig(name);
    }

}
