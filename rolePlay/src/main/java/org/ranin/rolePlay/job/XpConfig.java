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
            cusconf.set("lumberjack.break.Hoe.OAK_WOOD", 1);
            cusconf.set("lumberjack.craft.tool.WOODEN_SWORD", 15);
            cusconf.set("lumberjack.craft.tool.WOODEN_PICKAXE", 15);
            cusconf.set("lumberjack.craft.tool.WOODEN_AXE", 40);
            cusconf.set("lumberjack.craft.tool.WOODEN_SHOVEL", 15);
            // MINER
            cusconf.set("miner.break.ore.DIAMOND", 40);
            cusconf.set("miner.break.ore.IRON_ORE", 10);
            cusconf.set("miner.break.rock.STONE", 1);
            // BUILDER
            cusconf.set("builder.place.shovel.WOODEN_PICKAXE", 1);
            // FISHERMAN
            // HUNTER
            cusconf.set("hunter.craft.tool.DIAMOND_SWORD", 40);
            cusconf.set("hunter.craft.tool.DIAMOND_SWORD", 40);
            cusconf.set("hunter.craft.tool.DIAMOND_SWORD", 40);
            // WARRIOR
            cusconf.set("warrior.playerkill.tool.assassin", 200);
            cusconf.set("warrior.mobkill.friendly.SHEEP", 30);
            cusconf.set("warrior.mobkill.friendly.PIG", 30);
            cusconf.set("warrior.mobkill.hostile.CREEPER", 100);
            cusconf.set("warrior.mobkill.hostile.DROWNED", 70);
            cusconf.set("warrior.mobkill.hostile.SKELETON", 85);
            cusconf.set("warrior.mobkill.hostile.ZOMBIE", 40);
            cusconf.set("warrior.mobkill.hostile.SPIDER", 40);
            // KNIGHT
            cusconf.set("knight.playerkill.tool.assassin", 400);
            cusconf.set("knight.mobkill.hostile.PILLAGER", 70);
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
            cusconf.set("blacksmith.craft.tool.DIAMOND_SWORD", 40);
            cusconf.set("blacksmith.craft.tool.IRON_SWORD", 40);
            cusconf.set("blacksmith.craft.tool.DIAMOND_PICKAXE", 40);
            cusconf.set("blacksmith.craft.tool.IRON_PICKAXE", 40);
            cusconf.set("blacksmith.craft.tool.DIAMOND_AXE", 40);
            cusconf.set("blacksmith.craft.tool.IRON_AXE", 40);
            cusconf.set("blacksmith.craft.tool.DIAMOND_SHOVEL", 40);
            cusconf.set("blacksmith.craft.tool.IRON_SHOVEL", 40);
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
