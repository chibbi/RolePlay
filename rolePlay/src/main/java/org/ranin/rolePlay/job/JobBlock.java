package org.ranin.rolePlay.job;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

/*
author: "chibbi"
description: "job class, it is a template for job"
TODO: ["think"]
*/

import org.bukkit.entity.Player;
import org.bukkit.entity.Vehicle;
import org.bukkit.inventory.ItemStack;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class JobBlock {

    private Logger log;

    private FileConfiguration jobConfig;
    private FileConfiguration xpConfig;

    public JobBlock(Logger logg) {
        jobConfig = new JobConfig(logg).getCustomConfig();
        xpConfig = new XpConfig(logg).getCustomConfig();
        log = logg;
    }

    private void giveEffect(Player player, PotionEffectType potion, int amplifier) {
        if (amplifier <= 0 || potion == null) {
        } else {
            player.addPotionEffect(new PotionEffect(potion, Integer.MAX_VALUE, amplifier));
        }
    }

    public void loadEffects(Player player) {
        String[] info = new Jobsql(log).readfromJobTable(player.getName());
        if (info[0] == null) {
            log.fine("No Job yet");
            System.out.println(info);
        } else {
            for (PotionEffect eff : player.getActivePotionEffects()) {
                player.removePotionEffect(eff.getType());
            }
            setEffects(player, info[0], (Integer.parseInt(info[1])));
            setEffects(player, info[2], (Integer.parseInt(info[3])));
            setEffects(player, info[4], (Integer.parseInt(info[5])));
            setEffects(player, info[6], (Integer.parseInt(info[7])));
        }
    }

    public void setEffects(Player player, String job, int xp) {
        log.info("player: " + player + " job: " + job + " xp: " + xp);
        int posAmp = xp;
        ArrayList<String> positives = (ArrayList<String>) jobConfig.getStringList(job + ".effects.positives");
        for (String positive : positives) {
            if (positive.strip() == "" || positive == null || positive == "TEMPLATE") {
                break;
            }
            giveEffect(player, PotionEffectType.getByName(positive), posAmp);
        }
        int negAmp = xp / 2;
        ArrayList<String> negatives = (ArrayList<String>) jobConfig.getStringList(job + ".effects.negatives");
        for (String negative : negatives) {
            if (negative.strip() == "" || negative == null || negative == "TEMPLATE") {
                break;
            }
            giveEffect(player, PotionEffectType.getByName(negative), negAmp);
        }
    }

    public void killPlayer(Player killer, Player dead) {
        String[] infKiller = new Jobsql(log).readfromJobTable(killer.getName());
        String[] infDead = new Jobsql(log).readfromJobTable(dead.getName());
        int i = 0;
        for (String killValue : infKiller) {
            if (killValue == null) {
            } else {
                try {
                    if (i % 2 == 0) {
                        for (String keys : xpConfig.getKeys(true)) {
                            String[] singleKeys = keys.split("\\.");
                            if (singleKeys.length == 4 && singleKeys[1].equals("playerkill")) {
                                int j = 0;
                                for (String deadValue : infDead) {
                                    if (deadValue == null) {
                                    } else {
                                        try {
                                            if (i % 2 == 0) {
                                                if (singleKeys[3].equals(deadValue)) {
                                                    new Jobsql(log).AddXp(killer.getName(), j, xpConfig.getInt(keys),
                                                            infKiller);
                                                }
                                            }
                                        } catch (NumberFormatException e) {
                                        }
                                    }
                                    j++;
                                }
                            }
                        }
                    }
                } catch (NumberFormatException e) {
                }
            }
            i++;
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

    public void placesOn(Player player, Block block) {
        String[] info = new Jobsql(log).readfromJobTable(player.getName());
    }

    public void harvests(Player player, List<ItemStack> itemsHarvested) {
        String[] info = new Jobsql(log).readfromJobTable(player.getName());
        log.info(itemsHarvested.get(0).getType().name());
        parseXp(info, player, itemsHarvested.get(0).getType().name(), "craft");
    }

    public void enters(Entity entered, Vehicle vehicle) {
    }

    public void isBreaking(Player player, Material type) {
        String[] info = new Jobsql(log).readfromJobTable(player.getName());
    }

    public void killEntity(Player player, LivingEntity entity) {
        String[] info = new Jobsql(log).readfromJobTable(player.getName());
        parseXp(info, player, entity.getName(), "mobkill");
    }

    public void parseXp(String[] info, Player player, String equal, String what) {
        int i = 0;
        for (String value : info) {
            if (value == null) {
            } else {
                try {
                    if (i % 2 == 0) {
                        for (String keys : xpConfig.getKeys(true)) {
                            String[] singleKeys = keys.split("\\.");
                            if (singleKeys.length == 4 && singleKeys[1].equals(what)) {
                                if (singleKeys[3].equals(equal)) {
                                    new Jobsql(log).AddXp(player.getName(), i, xpConfig.getInt(keys), info);
                                }
                            }
                        }
                    }
                } catch (NumberFormatException e) {
                }
            }
            i++;
        }
    }

}
