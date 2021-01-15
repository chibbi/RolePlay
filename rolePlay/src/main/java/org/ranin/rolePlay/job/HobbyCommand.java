package org.ranin.rolePlay.job;

/*
author: "chibbi"
description: "contains Commands"
TODO: ["don't develop yet", "copy JobCommand into here(when finished), and then develop"]
*/

import java.util.Arrays;
import java.util.Set;
import java.util.logging.Logger;
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
        if (args.length == 0) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.sendMessage(
                        "§6You have to first specify, if you want to declare youre main hobby, or your second hobby\n"
                                + "Like that:\n§7/hobby main/second JOB\n§6You can get a list of  all jobs with:\n§7/hobby list"
                                + "\n§6You can get a list of  your jobs and hobbies with:\n§7/hobby mine");
                log.info(player.getName() + " has forgot Arguments: " + Arrays.toString(args));
            }
        } else {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                String[] info = new Jobsql(log).readfromJobTable(player.getName());
                if (args.length == 1) {
                    if (args[0].toString().equals("list")) {
                        player.sendMessage("§6All hobby:\n" + Arrays.toString(jobConfig.getKeys(false).toArray()));
                        return true;

                    } else if (args[0].toString().equals("mine")) {
                        player.sendMessage(
                                "§6Your hobbies and jobs (if there are numbers, you didn't choose one yet):\n"
                                        + "§6main_job §7" + info[0] + "\n§6second_job §7" + info[2]
                                        + "\n§6main_hobby §7" + info[4] + "\n§6main_hobby §7" + info[6]);
                        return true;
                    } else {
                        player.sendMessage("§6You have to first specify, which job you want\n"
                                + "Like that:\n§7/hobby main/second JOB\n§6You can get a list of  all jobs with:\n§7/hobby list");
                        log.info(player.getName() + " has forgot Arguments: " + Arrays.toString(args));
                        return false;
                    }
                } else {
                    switch (args[0]) {
                        case "main":
                            return switchJobs(args[1], player, "main_job", true);
                        case "second":
                            if (new Jobsql(log).readfromJobTable(player.getName())[0] != null) {
                                return switchJobs(args[1], player, "second_job", false);
                            }
                            player.sendMessage("&6You have to first declare youre main hobby\n"
                                    + "Like that:\n§7/hobby main blacksmith/miner\n§6You can get a list of  all hobbies with:\n§7/hobby list");
                            return false;
                        case "xp":
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
                                    "§6Usage:\n§7/hobby main/second blacksmith/miner\n§6list of all existing Jobs:\n§7/hobby list");
                            log.info(player.getName() + " has tried: " + Arrays.toString(args));
                            return false;
                    }
                }
            } else {
                log.info(sender.getName() + " (" + sender.getClass() + ") has tried: " + Arrays.toString(args));
                return false;
            }
        }
        return false;
    }

    public boolean switchJobs(String arg, Player player, String column, boolean add) {
        Set<String> alljobs = jobConfig.getKeys(false);
        for (String job : alljobs) {
            if (arg.equals(job)) {
                if (add) {
                    player.sendMessage("§6Set Hobby to " + arg);
                    new Jobsql(log).addtoJobTable(player.getName(), column, arg);
                } else {
                    player.sendMessage("§6Set Hobby to " + arg);
                    new Jobsql(log).UpdateJobinJobTable(player.getName(), column, arg);
                }
                return true;
            }
        }
        return false;
    }
}