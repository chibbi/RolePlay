package org.ranin.rolePlay.job;

import java.util.Random;

/*
author: "chibbi"
description: "implements a Listener for Jobs"
TODO: ["add Listeners, which start and stop leveling and stuff", "implementing multiple Listeners causes issue, so lets talk about the start together"]
*/

import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockCanBuildEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.FurnaceExtractEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.ranin.rolePlay.Finance.Finance;

public class JobListener implements Listener {

    private String[] friendlyMob = { "BAT", "BEE", "CAT", "CHICKEN", "COD", "COW", "DOLPHIN", "DONKEY", "FOX", "HORSE",
            "IRON_GOLEM", "LLAMA", "MULE", "MUSHROOM_COW", "OCELOT", "PANDA", "PARROT", "PIG", "POLAR_BEAR",
            "PUFFERFISH", "RABBIT", "SALMON", "SHEEP", "SNOWMAN", "SQUID", "STRIDER", "TRADER_LLAMA", "TROPICAL_FISH",
            "TURTLE", "VILLAGER", "WOLF" };
    // sure those are all
    private String[] hostileMob = { "BLAZE", "CAVE_SPIDER", "CREEPER", "DROWNED", "ELDER_GUARDIAN", "ENDER_DRAGON",
            "ENDERMAN", "ENDERMITE", "EVOKER", "EVOKER_FANGS", "GHAST", "GIANT", "GUARDIAN", "HOGLIN", "HUSK",
            "ILLUSIONER", "MAGMA_CUBE", "PHANTOM", "PIGLIN", "PIGLIN_BRUTE", "PILLAGER", "RAVAGER", "SHULKER",
            "SILVERFISH", "SKELETON", "SKELETON_HORSE", "SLIME", "SPIDER", "STRAY", "VEX", "VINDICATOR", "WITCH",
            "WITHER", "WITHER_SKELETON", "ZOGLIN", "ZOMBIE", "ZOMBIE_HORSE", "ZOMBIE_VILLAGER", "ZOMBIFIED_PIGLIN" };
    // sure those are all

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
            new Tasks(log).loadEffects(event.getPlayer());
            new Finance(log).CreateAccount(event.getPlayer().getName());
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
            new JobXp(log).killPlayer(event.getEntity().getKiller(), event.getEntity());
        }
    }

    @EventHandler
    public void onMobDeath(EntityDeathEvent event) {
        boolean dele = true;
        if (event.getEntity().getKiller() instanceof Player) {
            String[] info = new Jobsql(log).readfromJobTable(event.getEntity().getKiller().getName());
            System.out.println("KILLED " + event.getEntity().getType());
            for (String friName : friendlyMob) {
                if (event.getEntity().getType().name().equals(friName)) {
                    if (info[0].equals("hunter")) {
                        dele = false;
                    } else if (new Random().nextInt(10 - 1 + 1) + 1 <= 3) {
                        event.getEntity().getKiller().sendMessage("Oh you were lucky");
                        dele = false;
                    } else {
                        event.getEntity().getKiller().sendMessage("You don't know how to gut animal");
                    }
                }
            }
            for (String hosName : hostileMob) {
                if (event.getEntity().getType().name().equals(hosName)) {
                    if (info[0].equals("warrior")) {
                        dele = false;
                    } else if (new Random().nextInt(10 - 1 + 1) + 1 <= 3) {
                        event.getEntity().getKiller().sendMessage("Oh you were lucky");
                        dele = false;
                    } else {
                        event.getEntity().getKiller().sendMessage("You don't know how to gut a monster");
                    }
                }
            }
            // TODO: maybe disAllow kills of certain kinds, from certain professions????
            // https://bukkit.org/threads/custom-mob-drops.465022/
            // should be made here not in Job (for convenience)
            new JobXp(log).killEntity(event.getEntity().getKiller(), event.getEntity());
        }
        if (dele) {
            System.out.println("Deleting");
            event.getDrops().clear(); // no drops
            event.setDroppedExp(0); // no xp output
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            if (new JobInteract(log).isBreaking(player) == false) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.getPlayer() instanceof Player) {
            new JobXp(log).breaks(event.getPlayer(), event.getBlock());
            // Farming
            if (event.getBlock().getType().name() == "POTATO" || event.getBlock().getType().name() == "WHEAT"
                    || event.getBlock().getType().name() == "CARROTS" || event.getBlock().getType().name() == "BEETROOT"
                    || event.getBlock().getType().name() == "PUMPKIN" || event.getBlock().getType().name() == "COCOA"
                    || event.getBlock().getType().name() == "MELON" || event.getBlock().getType().name() == "SUGAR_CANE"
                    || event.getBlock().getType().name() == "NETHER_WART"
                    || event.getBlock().getType().name() == "FARMLAND") {
                String[] info = new Jobsql(log).readfromJobTable(event.getPlayer().getName());
                if (event.getPlayer() instanceof Player) {
                    if (!info[0].equals("farmer")) {
                        event.getPlayer().sendMessage("§eYou lack the skill required to harvest!");
                        event.setCancelled(true);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (event.getPlayer() instanceof Player) {
            new JobXp(log).places(event.getPlayer(), event.getBlock());
        }
    }

    @EventHandler
    public void onFishing(PlayerFishEvent event) {
        String[] info = new Jobsql(log).readfromJobTable(event.getPlayer().getName());
        if (event.getPlayer() instanceof Player) {
            if (info[0] != "fisher") {
                event.getPlayer().sendMessage("§eYou lack the skill required to fish!");
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        // for test if in Water and stuff like that
        if (event.getPlayer() instanceof Player) {
            if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (new JobInteract(log).isInteracting(event.getPlayer()) == false) {
                    event.getPlayer().sendMessage("§eYou lack the skill required to use this item!");
                    event.setCancelled(true);
                }
            } else if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
                if (new JobInteract(log).isBreaking(event.getPlayer()) == false) {
                    event.getPlayer().sendMessage("§eYou lack the skill required to use this item!");
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onBlockPlaceOn(BlockCanBuildEvent event) {
        // for test if in Water and stuff like that
        if (event.getPlayer() instanceof Player) {
        }
    }

    @EventHandler
    public void onVehicleEnter(VehicleEnterEvent event) {
        if (event.getEntered() instanceof Player) {
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