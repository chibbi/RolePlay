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

import org.bukkit.World;
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
            player.addPotionEffect(new PotionEffect(potion, Integer.MAX_VALUE, amplifier - 1));
            // FOR SOME REASON it uses amplifier = 1 and gives POTION EFFECT with level 2
            // (thats why i do -1)
            player.sendMessage("Gave effect: " + potion.getName() + " strength: " + amplifier);
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
        }
    }

    public void setEffects(Player player, String job, int xp) {
        int posAmp = xp / 10000; // TODO: creat a logarithmic function instead of just / 10000
        // ex.:
        // 10000xp = amp 1
        // 23000xp = amp 2
        // 35000xp = amp 3
        // 48000xp = amp 4
        // 65000xp = amp 5
        // and may multiply with a constant for changing how much xp is needed for a
        // milestone
        ArrayList<String> positives = (ArrayList<String>) jobConfig.getStringList(job + ".effects.positives");
        for (String positive : positives) {
            if (positive.strip() == "" || positive == null || positive == "TEMPLATE") {
                break;
            }
            giveEffect(player, PotionEffectType.getByName(positive), posAmp);
        }
        int negAmp = xp / 15000; // TODO: same as posAmp (likely with a different constant)
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

    public void placesOn(Player player, Block block) {
        String[] info = new Jobsql(log).readfromJobTable(player.getName());
    }

    public void enters(Entity entered, Vehicle vehicle) {
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

    public void loadEveryoneEffects(World wor) {
        List<Player> allplayers = wor.getPlayers();
        for (Player singplayer : allplayers) {
            loadEffects(singplayer);
        }
    }

    public void giveNearbyReg(World wor) {
        List<Player> allplayers = wor.getPlayers();
        for (Player singplayer : allplayers) {
            String[] info = new Jobsql(log).readfromJobTable(singplayer.getName());
            if (info[0] == "knight") {
                double maxDist = 5L;
                for (Player other : allplayers) {
                    if (other.getLocation().distance(singplayer.getLocation()) <= maxDist) {
                        String[] inf = new Jobsql(log).readfromJobTable(other.getName());
                        if (inf[0] != "assassin") {
                            setEffects(other, "REGENERATION", 1 + (Integer.parseInt(info[1])));
                        }
                    }
                }
            }
        }
    }

}
