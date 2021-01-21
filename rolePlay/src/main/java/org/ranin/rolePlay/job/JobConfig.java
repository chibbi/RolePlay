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
            cusconf.set("mage", true);
            cusconf.set("miner", true);
            cusconf.set("farmer", true);
            cusconf.set("lumberjack", true);
            cusconf.set("builder", false);
            cusconf.set("fisherman", false);
            cusconf.set("hunter", true);
            cusconf.set("warrior", true);
            cusconf.set("knight", true);
            cusconf.set("assassin", true);
            cusconf.set("messenger", false);
            cusconf.set("merchant", true);
            cusconf.set("painter", true);
            cusconf.set("stonemason", true);
            cusconf.set("blacksmith", true);
            list = new ArrayList<String>();
            list.add("FAST_DIGGING");
            list.add("LUCK");
            cusconf.set("miner.effects.positives", list);
            list = new ArrayList<String>();
            list.add("TEMPLATE");
            cusconf.set("farmer.effects.positives", list);
            list = new ArrayList<String>();
            list.add("TEMPLATE");
            cusconf.set("lumberjack.effects.positives", list);
            list = new ArrayList<String>();
            list.add("TEMPLATE");
            cusconf.set("builder.effects.positives", list);
            list = new ArrayList<String>();
            list.add("DOLPHINS_GRACE");
            list.add("CONDUIT_POWER");
            list.add("WATER_BREATHING");
            cusconf.set("fisherman.effects.positives", list);
            list = new ArrayList<String>();
            list.add("TEMPLATE");
            cusconf.set("hunter.effects.positives", list);
            list = new ArrayList<String>();
            list.add("ABSORPTION");
            cusconf.set("warrior.effects.positives", list);
            list = new ArrayList<String>();
            list.add("ABSORPTION");
            list.add("HERO_OF_THE_VILLAGE");
            cusconf.set("knight.effects.positives", list);
            list = new ArrayList<String>();
            list.add("SPEED");
            list.add("SLOW_FALLING");
            cusconf.set("assassin.effects.positives", list);
            list = new ArrayList<String>();
            list.add("SPEED");
            list.add("JUMP");
            cusconf.set("messenger.effects.positives", list);
            list = new ArrayList<String>();
            list.add("HERO_OF_THE_VILLAGE");
            cusconf.set("merchant.effects.positives", list);
            list = new ArrayList<String>();
            list.add("TEMPLATE");
            cusconf.set("painter.effects.positives", list);
            list = new ArrayList<String>();
            cusconf.set("stonemason.effects.positives", list);
            list = new ArrayList<String>();
            list.add("TEMPLATE");
            cusconf.set("blacksmith.effects.positives", list);
            list = new ArrayList<String>();
            list.add("TEMPLATE");
            cusconf.set("miner.effects.negatives", list);
            list = new ArrayList<String>();
            list.add("TEMPLATE");
            cusconf.set("farmer.effects.negatives", list);
            list = new ArrayList<String>();
            list.add("TEMPLATE");
            cusconf.set("lumberjack.effects.negatives", list);
            list = new ArrayList<String>();
            list.add("TEMPLATE");
            cusconf.set("builder.effects.negatives", list);
            list = new ArrayList<String>();
            list.add("TEMPLATE");
            cusconf.set("fisherman.effects.negatives", list);
            list = new ArrayList<String>();
            list.add("TEMPLATE");
            cusconf.set("hunter.effects.negatives", list);
            list = new ArrayList<String>();
            list.add("TEMPLATE");
            cusconf.set("warrior.effects.negatives", list);
            list = new ArrayList<String>();
            list.add("TEMPLATE");
            cusconf.set("knight.effects.negatives", list);
            list = new ArrayList<String>();
            list.add("BAD_OMEN");
            cusconf.set("assassin.effects.negatives", list);
            list = new ArrayList<String>();
            list.add("TEMPLATE");
            cusconf.set("messenger.effects.negatives", list);
            list = new ArrayList<String>();
            list.add("TEMPLATE");
            cusconf.set("merchant.effects.negatives", list);
            list = new ArrayList<String>();
            list.add("TEMPLATE");
            cusconf.set("painter.effects.negatives", list);
            list = new ArrayList<String>();
            list.add("TEMPLATE");
            cusconf.set("stonemason.effects.negatives", list);
            list = new ArrayList<String>();
            list.add("TEMPLATE");
            cusconf.set("blacksmith.effects.negatives", list);
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
