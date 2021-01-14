package org.ranin.rolePlay.Finance;

import org.bukkit.entity.Entity;

/*
author: ["chibbi","raninninn"]
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

public class FinanceCommand implements CommandExecutor {

    private Logger log;

    public FinanceCommand(Logger logg) {
                log = logg;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("finance")) {
            if (args.length == 0) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    player.sendMessage(
                            "§6You first have to specify, if you want to transfer,  withdraw or deposit money.\n"
                                    + "E.g.\n§7/money deposit VALUE\n§6");
                    log.info(player.getName() + " forgot Arguments: " + Arrays.toString(args));
                }
            } else {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                        if(args.length == 1) {
                            if(args[0] == "help") {
                                player.sendMessage("§6All commands:\n"
                                                    +"finance transfer VALUE RECEIVER\n"
                                                    +"finance add VALUE RECEIVER\n"
                                                    +"finance set VALUE RECEIVER\n"
                                                    +"finance create-account PLAYER\n"
                                                    +"finance remove-account PLAYER\n"
                                                    +"finance reset-account PLAYER");
                                return true;
                            } else if(args[0] == "balance") {
                                // TODO get balance from row of player
                            }
                        } else {
                            switch (args[0]) {
                                case "add":
                                    // TODO add args[1] to row of player
                                case "set":
                                    // TODO set args[1] for row of player
                                case "transfer":
                                    // TODO add args[1] to row of args[2], subtract args[1] from row of player
                                case "create-account":
                                    // TODO add args[1] to account table
                                case "remove-account":
                                    // TODO remove args[1] from account table
                                case "reset-account":
                                    // TODO set all values of row args[1] to defaults
                                default:
                                    player.sendMessage(
                                            "§6Usage:\n§7/finance COMMAND ARG1 (ARG2)\n§6§7/finance help for help");
                                    log.info(player.getName() + " tried: " + Arrays.toString(args));       
                            }
                        }
                } else {
                    log.info(sender.getName() + " (" + sender.getClass() + ") tried: " + Arrays.toString(args));
                }
            }
            return false;
        } else {return false;}
    }
}