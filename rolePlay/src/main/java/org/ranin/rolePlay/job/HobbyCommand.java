package org.ranin.rolePlay.job;

/*
author: "chibbi"
description: "contains Commands"
TODO: ["add commands to choose jobs", "work with jobManager, to save all chosen jobs"]
*/

import java.util.Arrays;
import java.util.logging.Logger;
import java.util.ArrayList;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.configuration.file.FileConfiguration;

public class HobbyCommand implements CommandExecutor {

    private Logger log;

    private FileConfiguration jobConfig;

    public HobbyCommand(Logger logg) {
        jobConfig = new JobConfig(logg).getCustomConfig();
        log = logg;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0 || args.length == 1) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.sendMessage(
                        "§7You have to first specify, if you want to declare youre main hobby, or your second hobby\n"
                                + "Like that:/hobby main/second blacksmith/miner\n");
                log.info(player.getName() + " has forgot Arguments: " + Arrays.toString(args));
            }
        } else {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                switch (args[0]) {
                    case "main":
                        return switchJobs(args[1], player, "main_hobby");
                    case "second":
                        if (new Jobsql(log).readfromJobTable(player.getName())[0] != null) {
                            return switchJobs(args[1], player, "second_hobby");
                        }
                        player.sendMessage("§7You have to first declare youre main hobby\n"
                                + "Like that:/hobby main blacksmith/miner\n");
                        return false;
                    case "xp":
                        String[] info = new Jobsql(log).readfromJobTable(player.getName());
                        switch (args[1]) {
                            case "main":
                                new Jobsql(log).AddXp(player.getName(), 0, Integer.parseInt(args[2]), info);
                                return true;
                            case "second":
                                new Jobsql(log).AddXp(player.getName(), 2, Integer.parseInt(args[2]), info);
                                return true;
                        }
                        player.sendMessage("§7You have to first declare youre main hobby\n"
                                + "Like that:/hobby main blacksmith/miner\n");
                        return false;
                    default:
                        player.sendMessage(
                                "§7Usage:\n/hobby main/second blacksmith/miner\nlist of all existing hobbys:\nblacksmith,miner, ... ");
                        log.info(player.getName() + " has tried: " + Arrays.toString(args));
                }
            } else {
                log.info(sender.getName() + " (" + sender.getClass() + ") has tried: " + Arrays.toString(args));
            }
        }
        return false;

    }

    public boolean switchJobs(String arg, Player player, String column) {
        ArrayList<String> alljobs = (ArrayList<String>) jobConfig.getStringList("alljobs");
        for (String job : alljobs) {
            if (arg == job) {
                new Jobsql(log).UpdateJobinJobTable(player.getName(), column, "blacksmith");
                return true;
            }
        }
        return false;
    }
}