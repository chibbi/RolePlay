package org.ranin.rolePlay.job;

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

public class JobCommand implements CommandExecutor {

    private Logger log;

    public JobCommand(Logger logg) {
        log = logg;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0 || args.length == 1) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.sendMessage(
                        "§7You have to first specify, if you want to declare youre main job, or your second job\n"
                                + "Like that:/job main/second blacksmith/miner\n");
                log.info(player.getName() + " has forgot Arguments: " + Arrays.toString(args));
            }
        } else {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                switch (args[0]) {
                    case "main":
                        return switchJobs(args[1], player, "main_job");
                    case "second":
                        return switchJobs(args[1], player, "second_job");
                    default:
                        player.sendMessage(
                                "§7Usage:\n/job main/second blacksmith/miner\nlist of all existing Jobs:\nblacksmith,miner, ... ");
                        log.info(player.getName() + " has tried: " + Arrays.toString(args));
                }
            } else {
                log.info(sender.getName() + " (" + sender.getClass() + ") has tried: " + Arrays.toString(args));
            }
        }
        return false;
    }

    public boolean switchJobs(String arg, Player player, String column) {
        switch (arg) {
            case "blacksmith":
                new Jobsql(log).addtoJobTable(player.getName(), column, "blacksmith");
                log.info("Gave " + player.getName() + " job: " + "blacksmith");
                return true;
            case "miner":
                new Jobsql(log).addtoJobTable(player.getName(), column, "miner");
                log.info("Gave " + player.getName() + "job: " + "miner");
                return true;
            default:
                player.sendMessage("§7List of existing Jobs:\nblacksmith,miner");
                log.info(player.getName() + " has tried: " + arg);
        }
        return false;
    }
}