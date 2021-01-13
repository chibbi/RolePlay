package org.ranin.rolePlay.job;

/*
author: ["chibbi","raninninn"]
description: "job class, it is a template for job"
TODO: ["think"]
*/

import java.util.Map;
import java.util.logging.Logger;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;

public class JobCrafting {

    private Logger log;

    public FileConfiguration config;

    public JobCrafting(Logger logg) {
        config = new JobConfig(logg).getCustomConfig();
        log = logg;
    }

    private void giveEffect(Player player, PotionEffectType potion, int amplifier) {
        player.addPotionEffect(new PotionEffect(potion, Integer.MAX_VALUE, amplifier));
    }

    public void crafts(HumanEntity whoClicked, ItemStack result) {
        String[] info = new Jobsql(log).readfromJobTable(whoClicked.getName());
    }

    public void cooks(Player player, Material material) {
        String[] info = new Jobsql(log).readfromJobTable(player.getName());
    }

    public void enchants(Player player, Map<Enchantment, Integer> enchantsToAdd) {
        String[] info = new Jobsql(log).readfromJobTable(player.getName());
    }

    public void smithing(HumanEntity player, ItemStack result) {
        String[] info = new Jobsql(log).readfromJobTable(player.getName());
    }

    public void anviling(HumanEntity player, ItemStack result) {
        String[] info = new Jobsql(log).readfromJobTable(player.getName());
    }

}
