package org.ranin.rolePlay.job;

/*
author: "chibbi"
description: "starting/should to get a file with all functions that don't quite fit into other categories"
TODO: ["think"]
*/

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import java.util.logging.Logger;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

public class JobInteract {
    private Logger log;

    private FileConfiguration jobConfig;
    private FileConfiguration xpConfig;
    private FileConfiguration interactConfig;

    public JobInteract(Logger logg) {
        jobConfig = new JobConfig(logg).getCustomConfig();
        xpConfig = new XpConfig(logg).getCustomConfig();
        interactConfig = new InteractConfig(logg).getCustomConfig();
        log = logg;
    }

    public boolean isBreaking(Player player) {
        String[] info = new Jobsql(log).readfromJobTable(player.getName());
        boolean allowedMain = false;
        boolean allowedOff = false;
        if (info[0] == null) {
            player.sendMessage("§6EY JOOOOO \nPlease choose a job (§7/job help§6)");
            return false;
        }
        for (String keys : interactConfig.getKeys(true)) {
            String[] singleKeys = keys.split("\\.");
            if (singleKeys.length == 4 && singleKeys[0].equals(info[0]) && singleKeys[1].equals("allowedTools")) {
                if (singleKeys[3].equals(player.getInventory().getItemInMainHand().getType().name())) {
                    allowedMain = true;
                } else if (player.getInventory().getItemInOffHand().getType().name() == "AIR"
                        || singleKeys[3].equals(player.getInventory().getItemInOffHand().getType().name())) {
                    allowedOff = true;
                } else {

                }
            }
        }
        if (!allowedMain) {
            return false;
        } else if (!allowedOff) {
            return false;
        }
        return true;
    }

    public boolean isInteracting(Player player) {
        String[] info = new Jobsql(log).readfromJobTable(player.getName());
        for (String keys : interactConfig.getKeys(true)) {
            String[] singleKeys = keys.split("\\.");
            if (singleKeys.length == 4 && singleKeys[0].equals(info[0]) && singleKeys[1].equals("deniedTools")) {
                if (singleKeys[3].equals(player.getInventory().getItemInMainHand().getType().name())) {
                    return false;
                } else if (singleKeys[3].equals(player.getInventory().getItemInOffHand().getType().name())) {
                    return false;
                } else {

                }
            }
        }
        return true;
    }

}
