package org.ranin.rolePlay.job;

/*
author: "chibbi"
description: "contains Commands"
TODO: ["add commands to choose jobs", "work with jobManager, to save all chosen jobs"]
*/

import java.util.Arrays;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.configuration.file.FileConfiguration;

public class JobCommand implements CommandExecutor {

    private Logger log;
    private FileConfiguration jobConfig;

    public JobCommand(Logger logg) {
        jobConfig = new JobConfig(logg).getCustomConfig();
        log = logg;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.sendMessage(
                        "§6You have to first specify, if you want to declare youre main job, or your second job\n"
                                + "Like that:\n§7/job main/second JOB\n§6You can get a list of  all jobs with:\n§7/job list");
                log.info(player.getName() + " has forgot Arguments: " + Arrays.toString(args));
            }
        } else {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                    if(args.length == 1) {
                        if(args[0] == "list") {
                            log.info(Arrays.toString(jobConfig.getStringList("alljobs").toArray()));
                            player.sendMessage("§6All Jobs:\n" + Arrays.toString(jobConfig.getStringList("alljobs").toArray()));
                            return true;
                        } else {
                            player.sendMessage(
                            "§6You have to first specify, which job you want\n"
                                    + "Like that:\n§7/job main/second JOB\n§6You can get a list of  all jobs with:\n§7/job list");
                            log.info(player.getName() + " has forgot Arguments: " + Arrays.toString(args));
                        }
                    } else {
                        switch (args[0]) {
                            case "main":
                                return switchJobs(args[1], player, "main_job",true);
                            case "second":
                                if (new Jobsql(log).readfromJobTable(player.getName())[0] != null) {
                                    return switchJobs(args[1], player, "second_job", false);
                                }
                                player.sendMessage("&6You have to first declare youre main job\n"
                                        + "Like that:\n§7/job main blacksmith/miner\n§6You can get a list of  all jobs with:\n§7/job list");
                                
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
                                player.sendMessage("§7You have to first declare youre main job\n"
                                        + "Like that:/job main blacksmith/miner\n");
                                
                            default:
                                player.sendMessage(
                                        "§6Usage:\n§7/job main/second blacksmith/miner\n§6list of all existing Jobs:\n§7/job list");
                                log.info(player.getName() + " has tried: " + Arrays.toString(args));
                                
                        }
                    }
            } else {
                log.info(sender.getName() + " (" + sender.getClass() + ") has tried: " + Arrays.toString(args));
                
            }
        }
        return false;
    }

    public boolean switchJobs(String arg, Player player, String column, boolean add) {
        ArrayList<String> alljobs = (ArrayList<String>) jobConfig.getStringList("alljobs");
        for (String job : alljobs) {
            if(arg == job) {
                if(!add) {
                    new Jobsql(log).UpdateJobinJobTable(player.getName(), column, "blacksmith");
                } else {
                    new Jobsql(log).addtoJobTable(player.getName(), column, "blacksmith");
                }
                return true;
            }
        }
        return false;
    }
}