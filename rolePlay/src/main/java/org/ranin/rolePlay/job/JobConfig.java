package org.ranin.rolePlay.job;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.ArrayList;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class JobConfig {

    private FileConfiguration customConfig;
    private Logger log;

    public JobConfig(Logger logg, String name) {
        log = logg;
        createCustomConfig(name);
    }

    private FileConfiguration createCustomConfig(String name) {
        File customConfigFile = new File("plugins/rolePlay/", name);
        FileConfiguration cusconf = null;
        if (!customConfigFile.exists()) {
            try {
                customConfigFile.createNewFile();
            } catch (IOException e) {
                log.warning("\033[31mCould not create a custom config\033[39m");
                log.info(e.getMessage());
            }
        }
        cusconf = YamlConfiguration.loadConfiguration(customConfigFile);

        cusconf.addDefault("miner", true);
        cusconf.addDefault("farmer", true);
        cusconf.addDefault("lumberjack", true);
        cusconf.addDefault("builder", false);
        cusconf.addDefault("fisherman", false);
        cusconf.addDefault("hunter", true);
        cusconf.addDefault("warrior", true);
        cusconf.addDefault("knight", true);
        cusconf.addDefault("assassin", true);
        cusconf.addDefault("messenger", false);
        cusconf.addDefault("merchant", true);
        cusconf.addDefault("painter", true);
        cusconf.addDefault("stonemason", true);
        cusconf.addDefault("blacksmith", true);
        ArrayList<String> list = new ArrayList<String>();
        list.add("haste");
        list.add("luck");
        cusconf.addDefault("miner.effects.positives", list);
        list = new ArrayList<String>();
        list.add("");
        list.add("");
        cusconf.addDefault("farmer.effects", list);
        list = new ArrayList<String>();
        list.add("");
        list.add("");
        cusconf.addDefault("lumberjack.effects", list);
        list = new ArrayList<String>();
        list.add("");
        list.add("");
        cusconf.addDefault("builder.effects", list);
        list = new ArrayList<String>();
        list.add("dolphin's grace");
        list.add("conduit power");
        list.add("water breathing");
        cusconf.addDefault("fisherman.effects", list);
        list = new ArrayList<String>();
        list.add("");
        list.add("");
        cusconf.addDefault("hunter.effects", list);
        list = new ArrayList<String>();
        list.add("absorption");
        list.add("");
        cusconf.addDefault("warrior.effects", list);
        list = new ArrayList<String>();
        list.add("absorption");
        list.add("regeneration");
        list.add("hero of the village");
        cusconf.addDefault("knight.effects", list);
        list = new ArrayList<String>();
        list.add("speed");
        list.add("slow falling");
        list.add("bad omen");
        cusconf.addDefault("assassin.effects", list);
        list = new ArrayList<String>();
        list.add("speed");
        list.add("jump boost");
        cusconf.addDefault("messenger.effects", list);
        list = new ArrayList<String>();
        list.add("");
        list.add("");
        cusconf.addDefault("merchant.effects", list);
        list = new ArrayList<String>();
        list.add("");
        list.add("");
        cusconf.addDefault("painter.effects", list);
        list = new ArrayList<String>();
        list.add("");
        list.add("");
        cusconf.addDefault("stonemason.effects", list);
        list = new ArrayList<String>();
        list.add("");
        list.add("");
        cusconf.addDefault("blacksmith.effects", list);
        try {
            cusconf.save(customConfigFile);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return cusconf;
    }

    public FileConfiguration getCustomConfig() {
        return this.customConfig;
    }

}
