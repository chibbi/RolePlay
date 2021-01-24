package org.ranin.rolePlay.Finance;

/*
author: "raninninn"
description: "Commands for Finances"
TODO: ["add feedback"]
*/

import java.util.Arrays;
import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
                switch (args[0]) {
                    case "help":
                        player.sendMessage("§6All commands:§7\n" + "money transfer VALUE RECEIVER\n"
                                + "money add VALUE RECEIVER\n");
                        if (sender.isOp()) {
                            player.sendMessage("§eSERVER STUFF:§7\n" + "money set VALUE RECEIVER\n"
                                    + "money create-account PLAYER\n" + "money remove-account PLAYER\n"
                                    + "money reset-account PLAYER");
                        }
                        return true;
                    case "balance":
                        player.sendMessage("§6Your balance is: §7" + new Finance(log).GetBalance(player.getName()));
                        return true;
                    case "add":
                        new Finance(log).PlusMoney(Integer.valueOf(args[1]), player.getName());
                        player.sendMessage("§6Added §7" + args[1] + " §6to your Balance");
                        return true;
                    case "transfer":
                        new Finance(log).MinusMoney(Integer.valueOf(args[1]), player.getName());
                        new Finance(log).PlusMoney(Integer.valueOf(args[1]), args[2]);
                        player.sendMessage("§6Send §7" + args[2] + " " + args[1] + "§6$");
                        return true;
                    case "set":
                        if (sender.isOp()) {
                            new Finance(log).SetBalance(Integer.valueOf(args[1]), args[2]);
                            player.sendMessage("§6Set Balance Account of §7" + args[2] + "§6 to §7" + args[1]);
                            return true;
                        }
                    case "create-account":
                        if (sender.isOp()) {
                            new Finance(log).CreateAccount(args[2]);
                            player.sendMessage("§6Created Account of " + args[2]);
                            return true;
                        }

                    case "remove-account":
                        if (sender.isOp()) {
                            new Finance(log).RemoveAccount(args[2]);
                            player.sendMessage("§6Removed Account of " + args[2]);
                            return true;
                        }

                    case "reset-account":
                        if (sender.isOp()) {
                            new Finance(log).RemoveAccount(args[2]);
                            new Finance(log).CreateAccount(args[2]);
                            player.sendMessage("§6Reset Account of " + args[2]);
                            return true;
                        }
                }
                player.sendMessage("§6Usage:\n§7/money COMMAND ARG1 (ARG2)\n§6§7/finance help for help");
                log.info(player.getName() + " tried: " + Arrays.toString(args));
                return false;
            } else {
                log.info(sender.getName() + " (" + sender.getClass() + ") tried: " + Arrays.toString(args));
            }
        }
        return false;
    }
}