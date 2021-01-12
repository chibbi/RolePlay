package org.ranin.rolePlay.job;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.logging.Logger;

import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.ranin.rolePlay.App;

/*
author: ["chibbi","raninninn"]
description: "job class, it is a template for job"
TODO: ["think"]
*/

public class Job {

    private Logger log;

    public FileConfiguration config;

    public Job(Logger logg) {
        config = new App().getCustomConfig();
        log = logg;
    }

    private void giveEffect(Player player, PotionEffectType potion, int amplifier) {
        player.addPotionEffect(new PotionEffect(potion, Integer.MAX_VALUE, amplifier));
    }

    public void death(Player killer, Player dead) {
        String[] infKiller = new Jobsql(log).readfromJobTable(killer.getName());
        String[] infDead = new Jobsql(log).readfromJobTable(dead.getName());

    }

    public void loadEffects(Player player, String string) {
        String[] info = new Jobsql(log).readfromJobTable(player.getName());
    }

    public void breaks(Player player, Block block) {
        String[] info = new Jobsql(log).readfromJobTable(player.getName());
    }

    public void places(Player player, Block block) {
        String[] info = new Jobsql(log).readfromJobTable(player.getName());
    }

    public void crafts(HumanEntity whoClicked, ItemStack result) {
        String[] info = new Jobsql(log).readfromJobTable(whoClicked.getName());
    }

    public void harvests(Player player, List<ItemStack> itemsHarvested) {
        String[] info = new Jobsql(log).readfromJobTable(player.getName());
    }

}
