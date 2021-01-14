package org.ranin.rolePlay.job;

import org.bukkit.entity.Entity;

/*
author: ["chibbi","raninninn"]
description: "job class, it is a template for job"
TODO: ["think"]
*/

import org.bukkit.entity.Player;
import org.bukkit.entity.Vehicle;
import org.bukkit.inventory.ItemStack;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Job {

    private Logger log;

    public FileConfiguration jobConfig;
    public FileConfiguration xpConfig;

    public Job(Logger logg) {
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

    public void death(Player killer, Player dead) {
        String[] infKiller = new Jobsql(log).readfromJobTable(killer.getName());
        String[] infDead = new Jobsql(log).readfromJobTable(dead.getName());

    }

    public void breaks(Player player, Block block) {
        String[] info = new Jobsql(log).readfromJobTable(player.getName());
        int i = 0;
        for (String value : info) {
            if (value == null) {
            } else {
                try {
                    if (Integer.parseInt(value) % 2 == 0) {

                    }
                } catch (NumberFormatException e) {
                }
                switch (value) {
                    case "miner":
                        System.out.println(xpConfig.getList("miner.break.ore.default"));
                        if (block.getType().name().contains("ORE")) {
                            new Jobsql(log).AddXp(player.getName(), i, xpConfig.getInt("miner.break.ore.default"),
                                    info);
                        } else if (block.getType().name() == "COBBLESTONE" || block.getType().name() == "STONE"
                                || block.getType().name() == "GRANITE" || block.getType().name() == "DIORITE"
                                || block.getType().name() == "ANDESITE") {
                            new Jobsql(log).AddXp(player.getName(), i, xpConfig.getInt("miner.break.rock.default"),
                                    info);
                        }
                        break;

                    default:
                        break;
                }
            }
            i++;
        }
    }

    public void places(Player player, Block block) {
        String[] info = new Jobsql(log).readfromJobTable(player.getName());
    }

    public void placesOn(Player player, Block block) {
        String[] info = new Jobsql(log).readfromJobTable(player.getName());
    }

    public void harvests(Player player, List<ItemStack> itemsHarvested) {
        String[] info = new Jobsql(log).readfromJobTable(player.getName());
    }

    public void enters(Entity entered, Vehicle vehicle) {
    }

}
