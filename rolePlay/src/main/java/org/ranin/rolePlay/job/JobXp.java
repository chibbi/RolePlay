package org.ranin.rolePlay.job;

import java.util.logging.Logger;

import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.LivingEntity;

/*
author: "chibbi"
description: "job class, it is a template for job"
TODO: ["think"]
*/

import org.bukkit.entity.Player;

public class JobXp {

    private Logger log;

    private FileConfiguration jobConfig;
    private FileConfiguration xpConfig;

    public JobXp(Logger logg) {
        jobConfig = new JobConfig(logg).getCustomConfig();
        xpConfig = new XpConfig(logg).getCustomConfig();
        log = logg;
    }

    public void killPlayer(Player killer, Player dead) {
        String[] infKiller = new Jobsql(log).readfromJobTable(killer.getName());
        String[] infDead = new Jobsql(log).readfromJobTable(dead.getName());
        for (String keys : xpConfig.getKeys(true)) {
            String[] singleKeys = keys.split("\\.");
            if (singleKeys.length == 4 && singleKeys[1].equals("playerkill") && singleKeys[0].equals(infKiller[0])) {
                if (singleKeys[3].equals(infDead[0])) {
                    new Jobsql(log).AddXp(killer.getName(), Integer.parseInt(infKiller[1]) + xpConfig.getInt(keys));
                }
            }
        }
    }

    public void breaks(Player player, Block block) {
        String[] info = new Jobsql(log).readfromJobTable(player.getName());
        parseXp(info, player, block.getType().name(), "break");
    }

    public void places(Player player, Block block) {
        String[] info = new Jobsql(log).readfromJobTable(player.getName());
        parseXp(info, player, block.getType().name(), "place");
    }

    public void killEntity(Player player, LivingEntity entity) {
        String[] info = new Jobsql(log).readfromJobTable(player.getName());
        parseXp(info, player, entity.getType().toString(), "mobkill");
    }

    public void parseXp(String[] info, Player player, String equal, String what) {
        for (String keys : xpConfig.getKeys(true)) {
            String[] singleKeys = keys.split("\\.");
            if (singleKeys.length == 4 && singleKeys[1].equals(what) && singleKeys[0].equals(info[0])) {
                if (singleKeys[3].equals(equal)) {
                    new Jobsql(log).AddXp(player.getName(), Integer.parseInt(info[1]) + xpConfig.getInt(keys));
                } else {

                }
            }
        }
    }

}
