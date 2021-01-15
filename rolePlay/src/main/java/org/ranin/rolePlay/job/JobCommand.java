package org.ranin.rolePlay.job;

/*
author: "chibbi"
description: "contains Commands"
TODO: ["add commands to choose jobs", "work with jobManager, to save all chosen jobs"]
*/

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
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
                                + "Like that:\n§7/job main/second JOB\n§6You can get a list of all jobs with:\n§7/job list"
                                + "\n§6You can get a list of  your jobs and hobbies with:\n§7/job mine"
                                + "\n§6You can get a list of all available commands with:\n§7/job help");
                log.info(player.getName() + " has forgot Arguments: " + Arrays.toString(args));
            }
        } else {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                String[] info = new Jobsql(log).readfromJobTable(player.getName());
                if (args.length == 1) {
                    switch (args[0]) {
                        case "list":
                            player.sendMessage("§6All Jobs:\n" + Arrays.toString(jobConfig.getKeys(false).toArray()));
                            return true;
                        case "mine":
                            player.sendMessage(
                                    "§6Your jobs and hobbies(if there are numbers, you didn't choose one yet):\n"
                                            + "§6main_job §7" + info[0] + "\n§6second_job §7" + info[2]
                                            + "\n§6main_hobby §7" + info[4] + "\n§6main_hobby §7" + info[6]);
                            return true;
                        case "help":
                            player.sendMessage("§6set main job: §7/job main JOB" + "§6set second job: §7/job second JOB"
                                    + "§6list of all jobs: §7/job list" + "§6list of your jobs and hobbies: §7/job mine"
                                    + "§6list of all available commands: §7/job help");
                            if (player.isOp()) {
                                player.sendMessage("§cSERVER OPERATOR STUFF:");
                                player.sendMessage("§6list of jobs of online players: §7/job listAll");
                                player.sendMessage("§6cheat XP to main job: §7/job xp main XP");
                                player.sendMessage("§6cheat XP to second job: §7/job xp second XP");
                                player.sendMessage("§6cheat XP to main job: §7/job cheat xp main XP");
                                player.sendMessage("§6cheat XP to second job: §7/job cheat xp second XP");
                            }
                            return true;
                        case "listAll":
                            if (player.isOp()) {
                                player.sendMessage("§6Your jobs and hobbies:\n" + "§6main_job §7" + info[0]
                                        + "\n§6second_job §7" + info[2] + "\n§6main_hobby §7" + info[4]
                                        + "\n§6main_hobby §7" + info[6]);
                                List<Player> allplayers = player.getWorld().getPlayers();
                                for (Player singplayer : allplayers) {
                                    info = new Jobsql(log).readfromJobTable(player.getName());
                                    player.sendMessage("§6" + singplayer.getName() + "'s jobs and hobbies:\n"
                                            + "§6main_job §7" + info[0] + "\n§6second_job §7" + info[2]
                                            + "\n§6main_hobby §7" + info[4] + "\n§6main_hobby §7" + info[6]);
                                }
                            }
                            return true;
                        default:
                            player.sendMessage("§6You have to first specify, which job you want\n"
                                    + "Like that:\n§7/job main/second JOB\n§6You can get a list of  all jobs with:\n§7/job list"
                                    + "\n§6You can get a list of all available commands with:\n§7/job help");
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
                            player.sendMessage("&6You have to first declare youre main job\n"
                                    + "Like that:\n§7/job main blacksmith/miner\n§6You can get a list of  all jobs with:\n§7/job list"
                                    + "\n§6You can get a list of all available commands with:\n§7/job help");
                            return false;
                        case "xp":
                            if (!player.isOp()) {
                                player.sendMessage("§7You are not allowed to do that yet\n"
                                        + "\n§6You can get a list of all available commands with:\n§7/job help");
                                return false;
                            }
                            switch (args[1]) {
                                case "main":
                                    new Jobsql(log).AddXp(player.getName(), 0, Integer.parseInt(args[2]), info);
                                    return true;
                                case "second":
                                    new Jobsql(log).AddXp(player.getName(), 2, Integer.parseInt(args[2]), info);
                                    return true;
                                case "cheat":
                                    switch (args[2]) {
                                        case "main":
                                            new Jobsql(log).AddXp(player.getName(), 0, Integer.parseInt(args[3]), info);
                                            return true;
                                        case "second":
                                            new Jobsql(log).AddXp(player.getName(), 2, Integer.parseInt(args[3]), info);
                                            return true;
                                    }
                                    player.sendMessage("§7You have to first declare youre main job\n"
                                            + "Like that:/job main blacksmith/miner\n"
                                            + "\n§6You can get a list of all available commands with:\n§7/job help");
                                    return false;
                            }
                        default:
                            player.sendMessage(
                                    "§6Usage:\n§7/job main/second blacksmith/miner\n§6list of all existing Jobs:\n§7/job list"
                                            + "\n§6You can get a list of all available commands with:\n§7/job help");
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
                    player.sendMessage("§6Set Job to " + arg);
                    new Jobsql(log).addtoJobTable(player.getName(), column, arg);
                } else {
                    player.sendMessage("§6Set Job to " + arg);
                    new Jobsql(log).UpdateJobinJobTable(player.getName(), column, arg);
                }
                return true;
            }
        }
        return false;
    }
}