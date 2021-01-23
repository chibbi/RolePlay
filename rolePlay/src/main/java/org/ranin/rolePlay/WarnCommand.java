package org.ranin.rolePlay;

/*
author: "chibbi"
description: "a command, to warn a player, who is not acting appropriatly"
*/

import java.util.Arrays;
import java.util.Random;
import java.util.logging.Logger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;

public class WarnCommand implements CommandExecutor {

    private Logger log;

    public WarnCommand(Logger logg) {
        log = logg;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length < 2) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (Bukkit.getPlayer(args[0]) == null) {
                    player.sendMessage("§7" + args[0] + " §6is not online!");
                    return true;
                }
                if (new Random().nextInt(10 - 1 + 1) + 1 > 5) {
                    Bukkit.getPlayer(args[0]).sendTitle("DU BIST SCHEIẞE UND MACHST KEIN Roleplay!!!", "", 20, 45, 20);
                } else {
                    Bukkit.getPlayer(args[0]).sendTitle("YOU ARE A BAD BAD GIRL", "fuck you", 20, 45, 20);
                }
                Bukkit.getPlayer(args[0]).chat("§0I §1a§2m §3a §4s§bt§5u§9p§3i§6d §cf§1u§ec§ak§f.");
                return true;
            } else {
                log.info(sender.getName() + " (" + sender.getClass() + ") has tried: " + Arrays.toString(args));
            }
        } else {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (Bukkit.getPlayer(args[0]) == null) {
                    player.sendMessage("§6" + args[0] + " §7is not online!");
                    return true;
                }
                String output = "";
                int i = 0;
                for (String arg : args) {
                    if (i != 0) {
                        output += arg + " ";
                    }
                    i++;
                }
                Bukkit.getPlayer(args[0]).sendTitle(output, "", 20, 45, 20);
                Bukkit.getPlayer(args[0]).chat("§4I am a stupid fuck");
                return true;
            } else {
                log.info(sender.getName() + " (" + sender.getClass() + ") has tried: " + Arrays.toString(args));
            }
        }
        log.info(sender.getName() + " (" + sender.getClass() + ") has tried: " + Arrays.toString(args));
        return false;
    }

}