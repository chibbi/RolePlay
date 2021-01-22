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

public class JobToolsArmor {
    private Logger log;

    private FileConfiguration jobConfig;
    private FileConfiguration xpConfig;
    private FileConfiguration interactConfig;

    public JobToolsArmor(Logger logg) {
        jobConfig = new JobConfig(logg).getCustomConfig();
        xpConfig = new XpConfig(logg).getCustomConfig();
        interactConfig = new InteractConfig(logg).getCustomConfig();
        log = logg;
    }

    public void isBreaking(Player player, Material type) {
        String[] info = new Jobsql(log).readfromJobTable(player.getName());
        boolean allowedMain = false;
        boolean allowedOff = false;
        for (String keys : interactConfig.getKeys(true)) {
            String[] singleKeys = keys.split("\\.");
            if (singleKeys.length == 4 && !singleKeys[1].equals("allowedTools") && singleKeys[0].equals(info[0])) {
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
            player.dropItem(true);
        } else if (!allowedOff) {
            ItemStack items = new ItemStack(Material.COBBLESTONE);
            items.setAmount(1);
            player.getInventory().setItemInOffHand(items);
        }
    }

    public void isInteracting(Player player, Material type) {
        String[] info = new Jobsql(log).readfromJobTable(player.getName());
        for (String keys : interactConfig.getKeys(true)) {
            String[] singleKeys = keys.split("\\.");
            if (singleKeys.length == 4 && singleKeys[1].equals("deniedTools") && singleKeys[0].equals(info[0])) {
                if (singleKeys[3].equals(player.getInventory().getItemInMainHand().getType().name())) {
                    player.dropItem(true);
                } else if (singleKeys[3].equals(player.getInventory().getItemInOffHand().getType().name())) {
                    ItemStack items = new ItemStack(Material.COBBLESTONE);
                    items.setAmount(1);
                    player.getInventory().setItemInOffHand(items);
                } else {

                }
            }
        }
    }

}
