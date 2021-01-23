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
                    case "diamond":
                        player.getInventory().addItem(nItem(Material.DIAMOND, 2));
                        log.info("Gave " + player.getName() + " 1 Diamond");
                        return true;
                    case "bricks":
                        player.getInventory().addItem(nItem(Material.BRICK, 20));
                        log.info("Gave " + player.getName() + "20 Bricks");
                        return true;
                    default:
                        player.sendMessage("§6List of existing Kits:\n§7diamond,bricks");
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