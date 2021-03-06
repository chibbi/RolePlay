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

public class XpConfig {

    private Logger log;
    private String name = "xp.yml";

    public XpConfig(Logger logg) {
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
            // XP GIVING //
            // FARMER
            // break
            cusconf.set("farmer.break.crops.POTATO", 10);
            cusconf.set("farmer.break.crops.CARROTS", 10);
            cusconf.set("farmer.break.crops.PUMPKIN", 10);
            cusconf.set("farmer.break.crops.MELON", 10);
            cusconf.set("farmer.break.crops.NETHER_WART", 15);
            cusconf.set("farmer.break.crops.WHEAT", 10);
            cusconf.set("farmer.break.crops.BEETROOT", 10);
            cusconf.set("farmer.break.crops.COCOA", 15);
            cusconf.set("farmer.break.crops.SUGAR_CANE", 5);
            // place
            cusconf.set("farmer.place.block.FARMLAND", 50);
            // craft
            // TODO: add seeds
            // LUMBERJACK
            // break
            cusconf.set("lumberjack.break.Hoe.OAK_WOOD", 15);
            cusconf.set("lumberjack.break.tool.SPRUCE_WOOD", 15);
            cusconf.set("lumberjack.break.Hoe.BIRCH_WOOD", 15);
            cusconf.set("lumberjack.break.tool.JUNGLE_WOOD", 15);
            cusconf.set("lumberjack.break.Hoe.ACACIA_WOOD", 15);
            cusconf.set("lumberjack.break.tool.DARK_OAK_WOOD", 15);
            cusconf.set("lumberjack.break.Hoe.CRIMSON_WOOD", 40);
            cusconf.set("lumberjack.break.tool.WARPED_WOOD", 40);
            cusconf.set("lumberjack.break.Hoe.OAK_LOG", 15);
            cusconf.set("lumberjack.break.tool.SPRUCE_LOG", 15);
            cusconf.set("lumberjack.break.Hoe.BIRCH_LOG", 15);
            cusconf.set("lumberjack.break.tool.JUNGLE_LOG", 15);
            cusconf.set("lumberjack.break.Hoe.ACACIA_LOG", 15);
            cusconf.set("lumberjack.break.tool.DARK_OAK_LOG", 15);
            cusconf.set("lumberjack.break.Hoe.CRIMSON_LOG", 40);
            cusconf.set("lumberjack.break.tool.WARPED_LOG", 40);
            // place
            // craft
            cusconf.set("lumberjack.craft.Hoe.CRAFTING_TABLE", 15);
            cusconf.set("lumberjack.craft.Hoe.WHITE_BED", 15);
            cusconf.set("lumberjack.craft.Hoe.YELLOW_BED", 15);
            cusconf.set("lumberjack.craft.Hoe.RED_BED", 15);
            cusconf.set("lumberjack.craft.Hoe.PURPLE_BED", 15);
            cusconf.set("lumberjack.craft.Hoe.PINK_BED", 15);
            cusconf.set("lumberjack.craft.Hoe.ORANGE_BED", 15);
            cusconf.set("lumberjack.craft.Hoe.MAGENTA_BED", 15);
            cusconf.set("lumberjack.craft.Hoe.LIME_BED", 15);
            cusconf.set("lumberjack.craft.Hoe.LIGHT_GRAY_BED", 15);
            cusconf.set("lumberjack.craft.Hoe.LIGHT_BLUE_BED", 15);
            cusconf.set("lumberjack.craft.Hoe.GREEN_BED", 15);
            cusconf.set("lumberjack.craft.Hoe.GRAY_BED", 15);
            cusconf.set("lumberjack.craft.Hoe.CYAN_BED", 15);
            cusconf.set("lumberjack.craft.Hoe.BROWN_BED", 15);
            cusconf.set("lumberjack.craft.Hoe.BLUE_BED", 15);
            cusconf.set("lumberjack.craft.Hoe.BLACK_BED", 15);
            cusconf.set("lumberjack.craft.Hoe.OAK_WOOD", 5);
            cusconf.set("lumberjack.craft.tool.SPRUCE_WOOD", 5);
            cusconf.set("lumberjack.craft.Hoe.BIRCH_WOOD", 5);
            cusconf.set("lumberjack.craft.tool.JUNGLE_WOOD", 5);
            cusconf.set("lumberjack.craft.Hoe.ACACIA_WOOD", 5);
            cusconf.set("lumberjack.craft.tool.DARK_OAK_WOOD", 5);
            cusconf.set("lumberjack.craft.Hoe.CRIMSON_WOOD", 10);
            cusconf.set("lumberjack.craft.tool.WARPED_WOOD", 10);
            cusconf.set("lumberjack.craft.Hoe.OAK_LOG", 5);
            cusconf.set("lumberjack.craft.tool.SPRUCE_LOG", 5);
            cusconf.set("lumberjack.craft.Hoe.BIRCH_LOG", 5);
            cusconf.set("lumberjack.craft.tool.JUNGLE_LOG", 5);
            cusconf.set("lumberjack.craft.Hoe.ACACIA_LOG", 5);
            cusconf.set("lumberjack.craft.tool.DARK_OAK_LOG", 5);
            cusconf.set("lumberjack.craft.Hoe.CRIMSON_LOG", 10);
            cusconf.set("lumberjack.craft.tool.WARPED_LOG", 10);
            cusconf.set("lumberjack.craft.tool.OAK_PLANKS", 1);
            cusconf.set("lumberjack.craft.tool.SPRUCE_PLANKS", 1);
            cusconf.set("lumberjack.craft.tool.BIRCH_PLANKS", 1);
            cusconf.set("lumberjack.craft.tool.JUNGLE_PLANKS", 1);
            cusconf.set("lumberjack.craft.tool.DARK_OAK_PLANKS", 1);
            cusconf.set("lumberjack.craft.tool.ACACIA_PLANKS", 1);
            cusconf.set("lumberjack.craft.tool.CRIMSON_PLANKS", 2);
            cusconf.set("lumberjack.craft.tool.WARPED_PLANKS", 2);
            cusconf.set("lumberjack.craft.tool.OAK_SLABS", 1);
            cusconf.set("lumberjack.craft.tool.SPRUCE_SLABS", 1);
            cusconf.set("lumberjack.craft.tool.BIRCH_SLABS", 1);
            cusconf.set("lumberjack.craft.tool.JUNGLE_SLABS", 1);
            cusconf.set("lumberjack.craft.tool.ACACIA_SLABS", 1);
            cusconf.set("lumberjack.craft.tool.DARK_OAK_SLABS", 1);
            cusconf.set("lumberjack.craft.tool.CRIMSON_SLABS", 2);
            cusconf.set("lumberjack.craft.tool.WARPED_SLABS", 2);
            cusconf.set("lumberjack.craft.tool.OAK_STAIRS", 2);
            cusconf.set("lumberjack.craft.tool.BIRCH_STAIRS", 2);
            cusconf.set("lumberjack.craft.tool.JUNGLE_STAIRS", 2);
            cusconf.set("lumberjack.craft.tool.ACACIA_STAIRS", 2);
            cusconf.set("lumberjack.craft.tool.DARK_OAK_STAIRS", 2);
            cusconf.set("lumberjack.craft.tool.CRIMSON_STAIRS", 3);
            cusconf.set("lumberjack.craft.tool.WARPED_STAIRS", 3);
            cusconf.set("lumberjack.craft.tool.OAK_FENCE", 3);
            cusconf.set("lumberjack.craft.tool.SPRUCE_FENCE", 3);
            cusconf.set("lumberjack.craft.tool.BIRCH_FENCE", 3);
            cusconf.set("lumberjack.craft.tool.JUNGLE_FENCE", 3);
            cusconf.set("lumberjack.craft.tool.ACACIA_FENCE", 3);
            cusconf.set("lumberjack.craft.tool.DARK_OAK_FENCE", 3);
            cusconf.set("lumberjack.craft.tool.CRIMSON_FENCE", 4);
            cusconf.set("lumberjack.craft.tool.WARPED_FENCE", 4);
            cusconf.set("lumberjack.craft.tool.OAK_FENCE_GATE", 3);
            cusconf.set("lumberjack.craft.tool.SPRUCE_FENCE_GATE", 3);
            cusconf.set("lumberjack.craft.tool.BIRCH_FENCE_GATE", 3);
            cusconf.set("lumberjack.craft.tool.JUNGLE_FENCE_GATE", 3);
            cusconf.set("lumberjack.craft.tool.ACACIA_FENCE_GATE", 3);
            cusconf.set("lumberjack.craft.tool.DARH_OAK_FENCE_GATE", 3);
            cusconf.set("lumberjack.craft.tool.CRIMSON_FENCE_GATE", 5);
            cusconf.set("lumberjack.craft.tool.WARPED_FENCE_GATE", 5);
            cusconf.set("lumberjack.craft.tool.WOODEN_PICKAXE", 15);
            cusconf.set("lumberjack.craft.tool.WOODEN_PICKAXE", 15);
            cusconf.set("lumberjack.craft.tool.WOODEN_PICKAXE", 15);
            cusconf.set("lumberjack.craft.tool.WOODEN_PICKAXE", 15);
            cusconf.set("lumberjack.craft.tool.WOODEN_PICKAXE", 15);
            cusconf.set("lumberjack.craft.tool.WOODEN_AXE", 40);
            cusconf.set("lumberjack.craft.tool.WOODEN_SHOVEL", 15);
            // MINER
            cusconf.set("miner.break.ore.DIAMOND", 40);
            cusconf.set("miner.break.ore.LAPIS_ORE", 25);
            cusconf.set("miner.break.ore.NETHER_GOLD_ORE", 22);
            cusconf.set("miner.break.ore.GOLD_ORE", 17);
            cusconf.set("miner.break.ore.IRON_ORE", 10);
            cusconf.set("miner.break.ore.COAL_ORE", 5);
            cusconf.set("miner.break.rock.STONE", 1);
            cusconf.set("miner.break.rock.ANDESITE", 1);
            cusconf.set("miner.break.rock.DIORITE", 1);
            cusconf.set("miner.break.rock.GRANITE", 1);
            // BUILDER
            cusconf.set("builder.place.shovel.WOODEN_PICKAXE", 1);
            // FISHERMAN
            // HUNTER
            cusconf.set("hunter.mobkill.friendly.SHEEP", 30);
            cusconf.set("hunter.mobkill.friendly.COW", 30);
            cusconf.set("hunter.mobkill.friendly.BEE", -20);
            cusconf.set("hunter.mobkill.friendly.CHICKEN", 15);
            cusconf.set("hunter.mobkill.friendly.PFERDE", 25);
            cusconf.set("hunter.mobkill.friendly.POLAR_BEAR", 40);
            cusconf.set("hunter.mobkill.friendly.PARROT", 60);
            cusconf.set("hunter.mobkill.friendly.LLAMA", 80);
            cusconf.set("hunter.mobkill.friendly.FOX", 70);
            cusconf.set("hunter.mobkill.friendly.OCELOT", 80);
            cusconf.set("hunter.mobkill.friendly.WOLF", 50);
            cusconf.set("hunter.mobkill.friendly.PIG", 30);
            // WARRIOR
            cusconf.set("warrior.playerkill.tool.assassin", 200);
            cusconf.set("warrior.mobkill.friendly.WOLF", 30);
            cusconf.set("warrior.mobkill.hostile.ENDERMAN", 95);
            cusconf.set("warrior.mobkill.hostile.CAVE_SPIDER", 15);
            cusconf.set("warrior.mobkill.hostile.SKELETON_HORSE", 185);
            cusconf.set("warrior.mobkill.hostile.CREEPER", 70);
            cusconf.set("warrior.mobkill.hostile.DROWNED", 70);
            cusconf.set("warrior.mobkill.hostile.SKELETON", 85);
            cusconf.set("warrior.mobkill.hostile.ZOMBIE", 40);
            cusconf.set("warrior.mobkill.hostile.ZOMBIFIED_PIGLIN", 15);
            cusconf.set("warrior.mobkill.hostile.SPIDER", 40);
            cusconf.set("warrior.mobkill.hostile.CAVE_SPIDER", 15);
            // KNIGHT
            cusconf.set("knight.playerkill.tool.assassin", 400);
            cusconf.set("knight.mobkill.hostile.PILLAGER", 70);
            cusconf.set("knight.mobkill.hostile.EVOKER", 200);
            cusconf.set("knight.mobkill.hostile.VINDICATOR", 100);
            cusconf.set("knight.mobkill.hostile.RAVAGER", 200);
            // ASSASSIN
            cusconf.set("assassin.playerkill.tool.farmer", 450);
            cusconf.set("assassin.playerkill.tool.lumberjack", 300);
            cusconf.set("assassin.playerkill.tool.miner", 300);
            cusconf.set("assassin.playerkill.tool.builder", 350);
            cusconf.set("assassin.playerkill.tool.hunter", 600);
            cusconf.set("assassin.playerkill.tool.warrior", 600);
            cusconf.set("assassin.playerkill.tool.knight", 1000);
            // MESSENGER
            // MERCHANT
            // PAINTER
            // STONMASON
            // currently blacksmith inherits stonmasons xp (and also his abilitys)
            cusconf.set("blacksmith.craft.tool.STONE_SWORD", 100);
            cusconf.set("blacksmith.craft.tool.STONE_PICKAXE", 100);
            cusconf.set("blacksmith.craft.tool.STONE_AXE", 100);
            cusconf.set("blacksmith.craft.tool.STONE_SHOVEL", 100);
            cusconf.set("blacksmith.craft.tool.STONE_HOE", 100);
            // BLACKSMITH
            cusconf.set("blacksmith.craft.tool.DIAMOND_SWORD", 220);
            cusconf.set("blacksmith.craft.tool.IRON_SWORD", 150);
            cusconf.set("blacksmith.craft.tool.DIAMOND_PICKAXE", 220);
            cusconf.set("blacksmith.craft.tool.IRON_PICKAXE", 150);
            cusconf.set("blacksmith.craft.tool.DIAMOND_AXE", 220);
            cusconf.set("blacksmith.craft.tool.IRON_AXE", 150);
            cusconf.set("blacksmith.craft.tool.DIAMOND_SHOVEL", 220);
            cusconf.set("blacksmith.craft.tool.IRON_SHOVEL", 150);
            cusconf.set("blacksmith.craft.tool.DIAMOND_HOE", 220);
            cusconf.set("blacksmith.craft.tool.IRON_HOE", 150);
            // MAGE
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
