package org.ranin.rolePlay.job;

/*
author: raninninn
description: "implements a Listener for Jobs"
TODO: ["add Listeners, which start and stop leveling and stuff", "implementing multiple Listeners causes issue, so lets talk about the start together"]
*/

import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockCanBuildEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.FurnaceExtractEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.bukkit.event.player.PlayerHarvestBlockEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;

public class JobListener implements Listener {

    private Logger log;

    public JobListener(Logger logg) {
        log = logg;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (event.getPlayer() instanceof Player) {
            new Job(log).loadEffects(event.getPlayer());
        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        if (event.getPlayer() instanceof Player) {
            new Jobsql(log).deletefromJobTable(event.getPlayer().getName());
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (event.getEntity().getKiller() instanceof Player) {
            new Job(log).death(event.getEntity().getKiller(), event.getEntity());
        }
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
                "say " + event.getEntity().getName() + " was slain!");
    }

    @EventHandler
    public void onMobDeath(EntityDeathEvent event) {
        // https://bukkit.org/threads/custom-mob-drops.465022/
        // should be made here not in Job (for convenience)
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.getPlayer() instanceof Player) {
            new Job(log).breaks(event.getPlayer(), event.getBlock());
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
                    "say " + event.getPlayer().getName() + " just broke " + event.getBlock().getType() + "!");
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (event.getPlayer() instanceof Player) {
            new Job(log).places(event.getPlayer(), event.getBlock());
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
                    "say " + event.getPlayer().getName() + " just placed a " + event.getBlock().getType() + "!");
        }
    }

    @EventHandler
    public void onBlockPlaceOn(BlockCanBuildEvent event) {
        // for test if in Water and stuff like that
        if (event.getPlayer() instanceof Player) {
            new Job(log).placesOn(event.getPlayer(), event.getBlock());
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
                    "say " + event.getPlayer().getName() + " places On a " + event.getBlock().getType() + "!");
        }
    }

    @EventHandler
    public void onVehicleEnter(VehicleEnterEvent event) {
        if (event.getEntered() instanceof Player) {
            new Job(log).enters(event.getEntered(), event.getVehicle());
            // TODO: probably stay here? (should be destroyed and put into the players inv,
            // chance to completle destroy)
            // if player not fisher or messenger or smth like that
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
                    "say " + event.getEntered().getName() + " just entered a " + event.getVehicle().getType() + "!");
        }
    }

    @EventHandler
    public void onHarvest(PlayerHarvestBlockEvent event) {
        if (event.getPlayer() instanceof Player) {
            new Job(log).harvests(event.getPlayer(), event.getItemsHarvested());
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
                    "say " + event.getPlayer().getName() + " just harvested " + event.getItemsHarvested() + "!");
        }
    }

    @EventHandler
    public void onCook(FurnaceExtractEvent event) {
        new JobCrafting(log).cooks(event.getPlayer(), event.getItemType());
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "say " + event.getPlayer() + " just got "
                + event.getItemType() + " out of " + event.getBlock().getType() + "!");
    }

    @EventHandler
    public void onSmithing(PrepareSmithingEvent event) {
        new JobCrafting(log).smithing(event.getView().getPlayer(), event.getResult());
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
                "say " + event.getView().getPlayer() + " just cooked " + event.getResult() + "!");
    }

    @EventHandler
    public void onAnvil(PrepareAnvilEvent event) {
        new JobCrafting(log).anviling(event.getView().getPlayer(), event.getResult());
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
                "say " + event.getView().getPlayer() + " just cooked " + event.getResult() + "!");
    }

    @EventHandler
    public void onEnchant(EnchantItemEvent event) {
        if (event.getEnchanter() instanceof Player) {
            new JobCrafting(log).enchants(event.getEnchanter(), event.getEnchantsToAdd());
            // TODO: add, that warrior automatically gets smite and ban of arthorods ... on
            // his swords he crafts
            // TODO: add lumberjack auto effiecency on axe
            // TODO: add miner auto effiecency on pickaxe (haste to?? or only hast?)
            // TODO: think about the rest of the classes
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
                    "say " + event.getEnchanter().getName() + " just crafted " + event.getEnchantsToAdd() + "!");
        }
    }

    @EventHandler
    public void onCraft(CraftItemEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            new JobCrafting(log).crafts(event.getWhoClicked(), event.getRecipe().getResult());
            // TODO: add, that warrior automatically gets smite and ban of arthorods ... on
            // his swords he crafts
            // TODO: add lumberjack auto effiecency on axe
            // TODO: add miner auto effiecency on pickaxe (haste to?? or only hast?)
            // TODO: think about the rest of the classes
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
                    "say " + event.getWhoClicked().getName() + " just crafted " + event.getRecipe().getResult() + "!");
        }
    }
}