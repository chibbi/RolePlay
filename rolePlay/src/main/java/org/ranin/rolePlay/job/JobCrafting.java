package org.ranin.rolePlay.job;

import java.util.ArrayList;
import java.util.List;

/*
author: ["chibbi","raninninn"]
description: "job class, it is a template for job"
TODO: ["think"]
*/

import java.util.Random;
import java.util.logging.Logger;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

public class JobCrafting {

    private Logger log;

    private FileConfiguration jobConfig;
    private FileConfiguration xpConfig;
    private FileConfiguration interactConfig;

    public JobCrafting(Logger logg) {
        jobConfig = new JobConfig(logg).getCustomConfig();
        xpConfig = new XpConfig(logg).getCustomConfig();
        interactConfig = new InteractConfig(logg).getCustomConfig();
        log = logg;
    }

    private void giveEffect(Player player, PotionEffectType potion, int amplifier) {
        player.addPotionEffect(new PotionEffect(potion, Integer.MAX_VALUE, amplifier));
    }

    public void crafts(CraftItemEvent event) {
        // see:
        // https://www.spigotmc.org/threads/how-to-get-amount-of-item-crafted.377598/
        ItemStack craftedItem = event.getInventory().getResult(); // Get result of recipe
        Inventory Inventory = event.getInventory(); // Get crafting inventory
        ClickType clickType = event.getClick();
        int realAmount = craftedItem.getAmount();
        if (clickType.isShiftClick()) {
            int lowerAmount = craftedItem.getMaxStackSize() + 1000; // Set lower at recipe result max stack size + 1000
                                                                    // (or just highter max stacksize of reciped item)
            for (ItemStack actualItem : Inventory.getContents()) // For each item in crafting inventory
            {
                if (!actualItem.getType().isAir() && lowerAmount > actualItem.getAmount()
                        && !actualItem.getType().equals(craftedItem.getType()))
                    // if slot is not air && lowerAmount is higher than this slot amount && it's not
                    // the recipe amount
                    lowerAmount = actualItem.getAmount(); // Set new lower amount
            }
            // Calculate the final amount : lowerAmount * craftedItem.getAmount
            realAmount = lowerAmount * craftedItem.getAmount();
        }

        ItemStack result = event.getRecipe().getResult();
        String[] info = new Jobsql(log).readfromJobTable(event.getWhoClicked().getName());
        boolean allowed = true;
        for (String keys : interactConfig.getKeys(true)) {
            String[] singleKeys = keys.split("\\.");
            if (singleKeys.length == 4 && singleKeys[1].equals("denyCraft") && singleKeys[0].equals(info[0])) {
                if (result.getType().name().contains(singleKeys[3])) {
                    allowed = false;
                }
            }
        }
        for (String keys : interactConfig.getKeys(true)) {
            String[] singleKeys = keys.split("\\.");
            if (singleKeys.length == 4 && singleKeys[1].equals("allowCraft") && singleKeys[0].equals(info[0])) {
                if (result.getType().name().contains(singleKeys[3])) {
                    allowed = true;
                }
            }
        }
        if (info[0] == null) {
            event.getWhoClicked().sendMessage("§6EY JOOOOO \nPlease choose a job (§7/job help§6)");
            event.setCancelled(true);
            return;
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
                            Integer.parseInt(info[1]) + (xpConfig.getInt(keys) * result.getAmount()));
                    System.out.println(xpConfig.getInt(keys) * realAmount);
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
