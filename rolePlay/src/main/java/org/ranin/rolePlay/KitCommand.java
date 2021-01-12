package org.ranin.rolePlay;

/*
author: "chibbi"
description: "contains Commands"
TODO: ["add commands to choose jobs", "work with jobManager, to save all chosen jobs"]
*/

import java.util.Arrays;
import java.util.logging.Logger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;

public class KitCommand implements CommandExecutor {

    private Logger log;

    public KitCommand(Logger logg) {
        log = logg;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
        } else {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                switch (args[0]) {
                    case "blacksmith":
                        log.info("Gave " + player.getName() + " job: " + "blacksmith");
                        return true;
                    case "miner":
                        log.info("Gave " + player.getName() + "job: " + "miner");
                        return true;
                    default:
                        player.sendMessage("§7List of existing Jobs:\nblacksmith,miner");
                        log.info(player.getName() + " has tried: " + Arrays.toString(args));
                }
            } else {
                log.info(sender.getName() + " (" + sender.getClass() + ") has tried: " + Arrays.toString(args));
            }
        }
        return false;
    }

    public ItemStack nItem(Material mat, int am) {
        // https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Material.html#ACACIA_BOAT
        ItemStack items = new ItemStack(mat);
        items.setAmount(am);
        return items;
    }

}