package org.ranin.rolePlay.Finance;

/*
author: "raninninn"
description: "Commands for Finances"
TODO: ["think"]
*/

import java.util.Arrays;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;

public class FinanceCommand implements CommandExecutor {

    private Logger log;

    public FinanceCommand(Logger logg) {
        log = logg;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.sendMessage("§6You first have to specify, if you want to transfer,  withdraw or deposit money.\n"
                        + "E.g.\n§7/money deposit VALUE\n§6");
                log.info(player.getName() + " forgot Arguments: " + Arrays.toString(args));
                return false;
            }
        } else {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (args.length == 1) {
                    if (args[0] == "help") {
                        player.sendMessage(
                                "§6All commands:§7\n" + "money transfer VALUE RECEIVER\n" + "money add VALUE RECEIVER\n"
                                        + "money set VALUE RECEIVER\n" + "money create-account PLAYER\n"
                                        + "money remove-account PLAYER\n" + "money reset-account PLAYER");
                        return true;
                    } else if (args[0] == "balance") {
                        player.sendMessage("Your balance is: " + new Finance(log).GetBalance(player.getName()));
                        return true;
                    }
                } else {
                    switch (args[0]) {
                        case "add":
                            new Finance(log).PlusMoney(Integer.valueOf(args[1]), player.getName());
                            return true;
                        case "set":
                            new Finance(log).SetBalance(Integer.valueOf(args[1]), player.getName());
                            return true;
                        case "transfer":
                            new Finance(log).MinusMoney(Integer.valueOf(args[1]), player.getName());
                            new Finance(log).PlusMoney(Integer.valueOf(args[1]), args[2]);
                            return true;
                        case "create-account":
                            new Finance(log).CreateAccount(player.getName());
                            return true;
                        case "remove-account":
                            new Finance(log).RemoveAccount(player.getName());
                            return true;
                        case "reset-account":
                            new Finance(log).RemoveAccount(player.getName());
                            new Finance(log).CreateAccount(player.getName());
                            return true;
                        default:
                            player.sendMessage("§6Usage:\n§7/money COMMAND ARG1 (ARG2)\n§6§7/finance help for help");
                            log.info(player.getName() + " tried: " + Arrays.toString(args));
                            return false;
                    }
                }
            } else {
                log.info(sender.getName() + " (" + sender.getClass() + ") tried: " + Arrays.toString(args));
            }
        }
        return false;
    }
}