package org.ranin.rolePlay.job;

/*
author: "chibbi"
description: "XP Config interface"
TODO: []
*/

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class InteractConfig {

    private Logger log;
    private String name = "interact.yml";

    public InteractConfig(Logger logg) {
        log = logg;
    }

    private FileConfiguration createCustomConfig(String name) {
        File customConfigFile = new File("plugins/rolePlay/", name);
        FileConfiguration cusconf = YamlConfiguration.loadConfiguration(customConfigFile);
        if (!customConfigFile.exists()) {
            try {
                customConfigFile.createNewFile();
            } catch (IOException e) {
                log.warning("\033[31mCould not create a custom config\033[39m");
                log.info(e.getMessage());
            }
            // CRAFTING, BREAKING, INTERACTING STUFF CONFIG //
            // FARMER
            cusconf.set("farmer.allowedTools.Hoe.NETHERITE_HOE", 1);
            cusconf.set("farmer.allowedTools.Hoe.DIAMOND_HOE", 1);
            cusconf.set("farmer.allowedTools.Hoe.GOLDEN_HOE", 1);
            cusconf.set("farmer.allowedTools.Hoe.IRON_HOE", 1);
            cusconf.set("farmer.allowedTools.Hoe.STONE_HOE", 1);
            cusconf.set("farmer.allowedTools.Hoe.WOODEN_HOE", 1);
            cusconf.set("farmer.deniedTools.fishing.FISHING_ROD", 1);
            cusconf.set("farmer.denyCraft.tools.SWORD", 1);
            cusconf.set("farmer.denyCraft.tools.PICKAXE", 1);
            cusconf.set("farmer.denyCraft.tools.AXE", 1);
            cusconf.set("farmer.denyCraft.tools.HOE", 1);
            cusconf.set("farmer.denyCraft.tools.SHOVEL", 1);
            cusconf.set("farmer.allowCraft.tools.WOODEN_SHOVEL", 1);
            cusconf.set("farmer.allowCraft.tools.WOODEN_AXE", 1);
            cusconf.set("farmer.allowCraft.tools.WOODEN_PICKAXE", 1);
            cusconf.set("farmer.allowCraft.tools.WOODEN_HOE", 1);
            cusconf.set("farmer.allowCraft.tools.WOODEN_SWORD", 1);
            cusconf.set("farmer.denyCraft.blocks.STONE", 1);
            cusconf.set("farmer.denyCraft.blocks.WOOD", 1);
            // LUMBERJACK
            cusconf.set("lumberjack.allowedTools.Hoe.NETHERITE_AXE", 1);
            cusconf.set("lumberjack.allowedTools.Hoe.DIAMOND_AXE", 1);
            cusconf.set("lumberjack.allowedTools.Hoe.GOLDEN_AXE", 1);
            cusconf.set("lumberjack.allowedTools.Hoe.IRON_AXE", 1);
            cusconf.set("lumberjack.allowedTools.Hoe.STONE_AXE", 1);
            cusconf.set("lumberjack.allowedTools.Hoe.WOODEN_AXE", 1);
            cusconf.set("lumberjack.deniedTools.fishing.FISHING_ROD", 1);
            cusconf.set("lumberjack.denyCraft.tools.SWORD", 1);
            cusconf.set("lumberjack.denyCraft.tools.PICKAXE", 1);
            cusconf.set("lumberjack.denyCraft.tools.AXE", 1);
            cusconf.set("lumberjack.denyCraft.tools.HOE", 1);
            cusconf.set("lumberjack.denyCraft.tools.SHOVEL", 1);
            cusconf.set("lumberjack.allowCraft.tools.WOODEN_SHOVEL", 1);
            cusconf.set("lumberjack.allowCraft.tools.WOODEN_AXE", 1);
            cusconf.set("lumberjack.allowCraft.tools.WOODEN_PICKAXE", 1);
            cusconf.set("lumberjack.allowCraft.tools.WOODEN_SWORD", 1);
            cusconf.set("lumberjack.allowCraft.tools.WOODEN_HOE", 1);
            // MINER
            cusconf.set("miner.allowedTools.Hoe.NETHERITE_PICKAXE", 1);
            cusconf.set("miner.allowedTools.Hoe.DIAMOND_PICKAXE", 1);
            cusconf.set("miner.allowedTools.Hoe.GOLDEN_PICKAXE", 1);
            cusconf.set("miner.allowedTools.Hoe.IRON_PICKAXE", 1);
            cusconf.set("miner.allowedTools.Hoe.STONE_PICKAXE", 1);
            cusconf.set("miner.allowedTools.Hoe.WOODEN_PICKAXE", 1);
            cusconf.set("miner.allowedTools.Hoe.WOODEN_AXE", 1);
            cusconf.set("miner.allowedTools.shovel.WOODEN_SHOVEL", 1);
            cusconf.set("miner.deniedTools.fishing.FISHING_ROD", 1);
            cusconf.set("miner.denyCraft.tools.SWORD", 1);
            cusconf.set("miner.denyCraft.tools.PICKAXE", 1);
            cusconf.set("miner.denyCraft.tools.AXE", 1);
            cusconf.set("miner.denyCraft.tools.HOE", 1);
            cusconf.set("miner.denyCraft.tools.SHOVEL", 1);
            cusconf.set("miner.allowCraft.tools.WOODEN_SHOVEL", 1);
            cusconf.set("miner.allowCraft.tools.WOODEN_AXE", 1);
            cusconf.set("miner.allowCraft.tools.WOODEN_PICKAXE", 1);
            cusconf.set("miner.allowCraft.tools.WOODEN_SWORD", 1);
            cusconf.set("miner.denyCraft.blocks.WOOD", 1);
            cusconf.set("miner.denyCraft.blocks.PLANKS", 1);
            // BUILDER
            cusconf.set("builder.allowedTools.shovel.WOODEN_PICKAXE", 1);
            cusconf.set("builder.allowedTools.shovel.WOODEN_AXE", 1);
            cusconf.set("builder.allowedTools.shovel.NETHERITE_SHOVEL", 1);
            cusconf.set("builder.allowedTools.shovel.DIAMOND_SHOVEL", 1);
            cusconf.set("builder.allowedTools.shovel.GOLDEN_SHOVEL", 1);
            cusconf.set("builder.allowedTools.shovel.IRON_SHOVEL", 1);
            cusconf.set("builder.allowedTools.shovel.STONE_SHOVEL", 1);
            cusconf.set("builder.allowedTools.shovel.WOODEN_SHOVEL", 1);
            cusconf.set("builder.deniedTools.fishing.FISHING_ROD", 1);
            cusconf.set("builder.denyCraft.tools.SWORD", 1);
            cusconf.set("builder.denyCraft.tools.PICKAXE", 1);
            cusconf.set("builder.denyCraft.tools.AXE", 1);
            cusconf.set("builder.denyCraft.tools.HOE", 1);
            cusconf.set("builder.denyCraft.tools.SHOVEL", 1);
            cusconf.set("builder.allowCraft.tools.WOODEN_SHOVEL", 1);
            cusconf.set("builder.allowCraft.tools.WOODEN_AXE", 1);
            cusconf.set("builder.allowCraft.tools.WOODEN_PICKAXE", 1);
            cusconf.set("builder.allowCraft.tools.WOODEN_SWORD", 1);
            cusconf.set("builder.denyCraft.blocks.WOOD", 1);
            cusconf.set("builder.denyCraft.blocks.PLANKS", 1);
            // FISHERMAN
            // HUNTER
            cusconf.set("hunter.allowedTools.Hoe.BOW", 1);
            cusconf.set("hunter.allowedTools.Hoe.CROSSBOW", 1);
            cusconf.set("hunter.allowedTools.Hoe.GOLDEN_SWORD", 1);
            cusconf.set("hunter.allowedTools.Hoe.IRON_SWORD", 1);
            cusconf.set("hunter.allowedTools.Hoe.STONE_SWORD", 1);
            cusconf.set("hunter.allowedTools.Hoe.WOODEN_SWORD", 1);
            cusconf.set("hunter.deniedTools.fishing.FISHING_ROD", 1);
            cusconf.set("hunter.denyCraft.tools.SWORD", 1);
            cusconf.set("hunter.denyCraft.tools.PICKAXE", 1);
            cusconf.set("hunter.denyCraft.tools.AXE", 1);
            cusconf.set("hunter.denyCraft.tools.HOE", 1);
            cusconf.set("hunter.denyCraft.tools.SHOVEL", 1);
            cusconf.set("hunter.allowCraft.tools.WOODEN_SHOVEL", 1);
            cusconf.set("hunter.allowCraft.tools.WOODEN_AXE", 1);
            cusconf.set("hunter.allowCraft.tools.WOODEN_PICKAXE", 1);
            cusconf.set("hunter.allowCraft.tools.WOODEN_SWORD", 1);
            cusconf.set("hunter.denyCraft.blocks.WOOD", 1);
            cusconf.set("hunter.denyCraft.blocks.PLANKS", 1);
            // WARRIOR
            cusconf.set("warrior.allowedTools.Hoe.NETHERITE_SWORD", 1);
            cusconf.set("warrior.allowedTools.Hoe.DIAMOND_SWORD", 1);
            cusconf.set("warrior.allowedTools.Hoe.GOLDEN_SWORD", 1);
            cusconf.set("warrior.allowedTools.Hoe.IRON_SWORD", 1);
            cusconf.set("warrior.allowedTools.Hoe.STONE_SWORD", 1);
            cusconf.set("warrior.allowedTools.Hoe.WOODEN_SWORD", 1);
            cusconf.set("warrior.deniedTools.fishing.FISHING_ROD", 1);
            cusconf.set("warrior.denyCraft.tools.SWORD", 1);
            cusconf.set("warrior.denyCraft.tools.PICKAXE", 1);
            cusconf.set("warrior.denyCraft.tools.AXE", 1);
            cusconf.set("warrior.denyCraft.tools.HOE", 1);
            cusconf.set("warrior.denyCraft.tools.SHOVEL", 1);
            cusconf.set("warrior.allowCraft.tools.WOODEN_SHOVEL", 1);
            cusconf.set("warrior.allowCraft.tools.WOODEN_AXE", 1);
            cusconf.set("warrior.allowCraft.tools.WOODEN_PICKAXE", 1);
            cusconf.set("warrior.allowCraft.tools.WOODEN_SWORD", 1);
            cusconf.set("warrior.denyCraft.blocks.WOOD", 1);
            cusconf.set("warrior.denyCraft.blocks.PLANKS", 1);
            // KNIGHT
            cusconf.set("knight.allowedTools.Hoe.NETHERITE_SWORD", 1);
            cusconf.set("knight.allowedTools.Hoe.DIAMOND_SWORD", 1);
            cusconf.set("knight.allowedTools.Hoe.GOLDEN_SWORD", 1);
            cusconf.set("knight.allowedTools.Hoe.IRON_SWORD", 1);
            cusconf.set("knight.allowedTools.Hoe.STONE_SWORD", 1);
            cusconf.set("knight.allowedTools.Hoe.WOODEN_SWORD", 1);
            cusconf.set("knight.deniedTools.fishing.FISHING_ROD", 1);
            cusconf.set("knight.denyCraft.tools.SWORD", 1);
            cusconf.set("knight.denyCraft.tools.PICKAXE", 1);
            cusconf.set("knight.denyCraft.tools.AXE", 1);
            cusconf.set("knight.denyCraft.tools.HOE", 1);
            cusconf.set("knight.denyCraft.tools.SHOVEL", 1);
            cusconf.set("knight.allowCraft.tools.WOODEN_SHOVEL", 1);
            cusconf.set("knight.allowCraft.tools.WOODEN_AXE", 1);
            cusconf.set("knight.allowCraft.tools.WOODEN_PICKAXE", 1);
            cusconf.set("knight.allowCraft.tools.WOODEN_SWORD", 1);
            cusconf.set("knight.denyCraft.blocks.WOOD", 1);
            cusconf.set("knight.denyCraft.blocks.PLANKS", 1);
            // MESSENGER
            // MERCHANT
            // PAINTER
            // STONMASON
            // BLACKSMITH
            cusconf.set("blacksmith.deniedTools.fishing.FISHING_ROD", 1);
            cusconf.set("blacksmith.denyCraft.blockTools.STONE", 1);
            cusconf.set("blacksmith.denyCraft.blocks.WOOD", 1);
            cusconf.set("blacksmith.denyCraft.blocks.PLANKS", 1);
            try {
                cusconf.save(customConfigFile);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return cusconf;
    }

    public FileConfiguration getCustomConfig() {
        return createCustomConfig(name);
    }

}
