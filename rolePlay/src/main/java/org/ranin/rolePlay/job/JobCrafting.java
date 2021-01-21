package org.ranin.rolePlay.job;

import java.util.ArrayList;
import java.util.List;

/*
author: ["chibbi","raninninn"]
description: "job class, it is a template for job"
TODO: ["think"]
*/

import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.BlastingRecipe;
import org.bukkit.inventory.CampfireRecipe;
import org.bukkit.inventory.CookingRecipe;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.SmithingRecipe;
import org.bukkit.inventory.SmokingRecipe;
import org.bukkit.inventory.StonecuttingRecipe;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.md_5.bungee.api.chat.hover.content.Item;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;

public class JobCrafting {

    private Logger log;

    private FileConfiguration jobConfig;
    private FileConfiguration xpConfig;

    public JobCrafting(Logger logg) {
        jobConfig = new JobConfig(logg).getCustomConfig();
        xpConfig = new XpConfig(logg).getCustomConfig();
        log = logg;
    }

    private void giveEffect(Player player, PotionEffectType potion, int amplifier) {
        player.addPotionEffect(new PotionEffect(potion, Integer.MAX_VALUE, amplifier));
    }

    public void crafts(CraftItemEvent event) {
        ItemStack result = event.getRecipe().getResult();
        String[] info = new Jobsql(log).readfromJobTable(event.getWhoClicked().getName());
        boolean allowed = true;
        for (String keys : xpConfig.getKeys(true)) {
            String[] singleKeys = keys.split("\\.");
            if (singleKeys.length == 4 && singleKeys[1].equals("denyCraft") && singleKeys[0].equals(info[0])) {
                if (result.getType().name().contains(singleKeys[3])) {
                    log.info(singleKeys[3] + " DISALLOWED: " + result.getType().name());
                    allowed = false;
                }
            }
        }
        for (String keys : xpConfig.getKeys(true)) {
            String[] singleKeys = keys.split("\\.");
            if (singleKeys.length == 4 && singleKeys[1].equals("allowCraft") && singleKeys[0].equals(info[0])) {
                if (result.getType().name().contains(singleKeys[3])) {
                    log.info(singleKeys[3] + " ALLOWED: " + result.getType().name());
                    allowed = true;
                }
            }
        }
        if (!allowed) {
            if (new Random().nextInt(10 - 1 + 1) + 1 >= 5) {
                event.getInventory().setResult(new ItemStack(Material.AIR));
                event.getSlot(); // find something, to delete the used Resources
                event.setCancelled(true);
            } else {
                event.setCancelled(true);
            }
            return;
        }
        for (String keys : xpConfig.getKeys(true)) {
            String[] singleKeys = keys.split("\\.");
            if (singleKeys.length == 4 && singleKeys[1].equals("craft") && singleKeys[0].equals(info[0])) {
                if (singleKeys[3].equals(result.getType().name())) {
                    new Jobsql(log).AddXp(event.getWhoClicked().getName(),
                            Integer.parseInt(info[1]) + xpConfig.getInt(keys));
                }
            }
        }
    }

    public void cooks(Player player, Material material) {
        String[] info = new Jobsql(log).readfromJobTable(player.getName());
    }

    public void enchants(EnchantItemEvent event) {
        String[] info = new Jobsql(log).readfromJobTable(event.getEnchanter().getName());
        // TODO: implement mage
        if (info[0] != "mage") {
            // TODO: implement something, which denys enchanting (maybe just deny
            // interacting with the block?)
            // isCancelled seems not to work and getDrops doens't exist, maybe you'll find
            // something in the Doc?
        }
    }

    public void smithing(HumanEntity player, ItemStack result) {
        String[] info = new Jobsql(log).readfromJobTable(player.getName());
    }

    public void anviling(HumanEntity player, ItemStack result) {
        String[] info = new Jobsql(log).readfromJobTable(player.getName());
    }
}
