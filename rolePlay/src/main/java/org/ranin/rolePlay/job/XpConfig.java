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
            //craft
            cusconf.set("lumberjack.craft.tool.OAK_PLANKS", 5);
            cusconf.set("lumberjack.craft.tool.SPRUCE_PLANKS", 5);
            cusconf.set("lumberjack.craft.tool.BIRCH_PLANKS" , 5 );
            cusconf.set("lumberjack.craft.tool.JUNGLE_PLANKS", 5);
            cusconf.set("lumberjack.craft.tool.DARK_OAK_PLANKS", 5);
            cusconf.set("lumberjack.craft.tool.OAK_PLANKS", 5);
            cusconf.set("lumberjack.craft.tool.CRIMSON_PLANKS", 10);
            cusconf.set("lumberjack.craft.tool.WARPED_PLANKS", 10);
            // TODO: add for stairs and slabs and so on
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
            // BLACKSMITH
            cusconf.set("blacksmith.craft.tool.DIAMOND_SWORD", 200);
            cusconf.set("blacksmith.craft.tool.IRON_SWORD", 100);
            cusconf.set("blacksmith.craft.tool.DIAMOND_PICKAXE", 200);
            cusconf.set("blacksmith.craft.tool.IRON_PICKAXE", 100);
            cusconf.set("blacksmith.craft.tool.DIAMOND_AXE", 200);
            cusconf.set("blacksmith.craft.tool.IRON_AXE", 100);
            cusconf.set("blacksmith.craft.tool.DIAMOND_SHOVEL", 200);
            cusconf.set("blacksmith.craft.tool.IRON_SHOVEL", 100);
            cusconf.set("blacksmith.craft.tool.IRON_INGOT", 40);
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
