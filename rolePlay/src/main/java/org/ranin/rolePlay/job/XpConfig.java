package org.ranin.rolePlay.job;

/*
author: "chibbi"
description: "XP Config interface"
TODO: []
*/

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.ArrayList;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class XpConfig {

    private Logger log;
    private String name = "xp.yml";

    public XpConfig(Logger logg) {
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
            cusconf.set("miner.break.ore.DIAMOND", 10);
            cusconf.set("miner.break.rock.default", 1);
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
