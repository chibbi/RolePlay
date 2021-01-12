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
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerHarvestBlockEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class JobListener implements Listener {

    private Logger log;

    public JobListener(Logger logg) {
        log = logg;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (event.getPlayer() instanceof Player) {
            new Job(log).loadEffects(event.getPlayer(), "join");
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
    }

    public void onMobDeath(EntityDeathEvent event) {
        // https://bukkit.org/threads/custom-mob-drops.465022/
        // should be made here not in Job (for convenience)
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.getPlayer() instanceof Player) {
            new Job(log).breaks(event.getPlayer(), event.getBlock());
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
                    "say " + event.getPlayer().getName() + " just broke " + event.getBlock() + "!");
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (event.getPlayer() instanceof Player) {
            new Job(log).places(event.getPlayer(), event.getBlock());
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
                    "say " + event.getPlayer().getName() + " just placed a " + event.getBlock() + "!");
        }
    }

    public void onHarvest(PlayerHarvestBlockEvent event) {
        if (event.getPlayer() instanceof Player) {
            new Job(log).harvests(event.getPlayer(), event.getItemsHarvested());
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
                    "say " + event.getPlayer().getName() + " just placed a " + event.getItemsHarvested() + "!");
        }
    }

    @EventHandler
    public void onCraft(CraftItemEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            new Job(log).crafts(event.getWhoClicked(), event.getRecipe().getResult());
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