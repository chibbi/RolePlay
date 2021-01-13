package org.ranin.rolePlay.job;

import org.bukkit.entity.Entity;

/*
author: ["chibbi","raninninn"]
description: "job class, it is a template for job"
TODO: ["think"]
*/

import org.bukkit.entity.Player;
import org.bukkit.entity.Vehicle;
import org.bukkit.inventory.ItemStack;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Finance {

    private Logger log;

    public FileConfiguration jobConfig;
    public FileConfiguration xpConfig;

    public Finance(Logger logg) {
        // jobConfig = new JobConfig(logg).getCustomConfig();
        // xpConfig = new XpConfig(logg).getCustomConfig();
        log = logg;
    }
}
