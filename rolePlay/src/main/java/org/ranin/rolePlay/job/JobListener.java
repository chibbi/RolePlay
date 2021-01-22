package org.ranin.rolePlay.job;

/*
author: "chibbi"
description: "implements a Listener for Jobs"
TODO: ["add Listeners, which start and stop leveling and stuff", "implementing multiple Listeners causes issue, so lets talk about the start together"]
*/

import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
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
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;

public class JobListener implements Listener {

    private Logger log;
    private FileConfiguration defaultConfig;

    public JobListener(Logger logg, FileConfiguration config) {
        log = logg;
        defaultConfig = config;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (event.getPlayer() instanceof Player) {
            String[] info = new Jobsql(log).readfromJobTable(event.getPlayer().getName());
            if (info[0] == null) {
                event.getPlayer().sendMessage("§6EY JOOOOO \nPlease choose a job (§7/job help§6)");
            }
            new JobBlock(log).loadEffects(event.getPlayer());
        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        if (event.getPlayer() instanceof Player) {
            if (!defaultConfig.getBoolean("pvpmode")) {
                new Jobsql(log).deletefromJobTable(event.getPlayer().getName());
            }
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (event.getEntity().getKiller() instanceof Player) {
            new JobBlock(log).killPlayer(event.getEntity().getKiller(), event.getEntity());
        }
    }

    @EventHandler
    public void onMobDeath(EntityDeathEvent event) {
        if (event.getEntity().getKiller() instanceof Player) {
            // TODO: maybe disAllow kills of certain kinds, from certain professions????
            // https://bukkit.org/threads/custom-mob-drops.465022/
            // should be made here not in Job (for convenience)
            new JobBlock(log).killEntity(event.getEntity().getKiller(), event.getEntity());
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.getPlayer() instanceof Player) {
            new JobBlock(log).breaks(event.getPlayer(), event.getBlock());
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (event.getPlayer() instanceof Player) {
            new JobBlock(log).places(event.getPlayer(), event.getBlock());
        }
    }

    @EventHandler
    public void onHarvest(PlayerHarvestBlockEvent event) {
        String[] info = new Jobsql(log).readfromJobTable(event.getPlayer().getName());
        if (event.getPlayer() instanceof Player) {
            if (info[0] != "farmer") {
                // TODO: implement something, which denys harvesting
                // ( or just drop a shitton of stuff to a farmer if hes a farmer xD?)
                // isCancelled seems not to work and getDrops doens't exist, maybe you'll find
                // something in the Doc?
                event.isCancelled(true);
            }
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        // for test if in Water and stuff like that
        if (event.getPlayer() instanceof Player) {
            if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                new JobToolsArmor(log).isInteracting(event.getPlayer(), event.getClickedBlock().getType());
            } else if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
                new JobToolsArmor(log).isBreaking(event.getPlayer(), event.getClickedBlock().getType());
            }
        }
    }

    @EventHandler
    public void onBlockPlaceOn(BlockCanBuildEvent event) {
        // for test if in Water and stuff like that
        if (event.getPlayer() instanceof Player) {
            new JobBlock(log).placesOn(event.getPlayer(), event.getBlock());
        }
    }

    @EventHandler
    public void onVehicleEnter(VehicleEnterEvent event) {
        if (event.getEntered() instanceof Player) {
            new JobBlock(log).enters(event.getEntered(), event.getVehicle());
            // TODO: probably stay here? (should be destroyed and put into the players inv,
            // chance to completle destroy)
            // if player not fisher or messenger or smth like that
        }
    }

    @EventHandler
    public void onCook(FurnaceExtractEvent event) {
        new JobCrafting(log).cooks(event.getPlayer(), event.getItemType());
    }

    @EventHandler
    public void onSmithing(PrepareSmithingEvent event) {
        new JobCrafting(log).smithing(event.getView().getPlayer(), event.getResult());
    }

    @EventHandler
    public void onAnvil(PrepareAnvilEvent event) {
        new JobCrafting(log).anviling(event.getView().getPlayer(), event.getResult());
    }

    @EventHandler
    public void onEnchant(EnchantItemEvent event) {
        if (event.getEnchanter() instanceof Player) {
            new JobCrafting(log).enchants(event);
            // TODO: add, that warrior automatically gets smite and ban of arthorods ... on
            // his swords he crafts
            // TODO: add lumberjack auto effiecency on axe
            // TODO: add miner auto effiecency on pickaxe (haste to?? or only hast?)
            // TODO: think about the rest of the classes
        }
    }

    @EventHandler
    public void onCraft(CraftItemEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            new JobCrafting(log).crafts(event);
            // TODO: add, that warrior automatically gets smite and ban of arthorods ... on
            // his swords he crafts
            // TODO: add lumberjack auto effiecency on axe
            // TODO: add miner auto effiecency on pickaxe (haste to?? or only hast?)
            // TODO: think about the rest of the classes
        }
    }
}