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
    private FileConfiguration defConfig;

    public JobCommand(Logger logg, FileConfiguration defCodnfig) {
        jobConfig = new JobConfig(logg).getCustomConfig();
        log = logg;
        defConfig = defCodnfig;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.sendMessage("§e ---------- §fTip: job §e---------- \n"
                        + "§6You have to first specify, if you want to declare youre main job, or your second job\n"
                        + "Like that: §7/job set JOB\n§6You can get a list of all jobs with: §7/job list"
                        + "\n§6You can get stats of  your job with: §7/job mine"
                        + "\n§6You can get a list of all available commands with: §7/job help");
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
                                    "§6Your job§7(if there are numbers(or null), you didn't choose one yet)§6:\n"
                                            + "§6job §7" + info[0] + " §6xp §7" + info[1]);
                            return true;
                        case "help":
                            player.sendMessage(
                                    "§e ---------- §fHelp: job §e---------- \n" + "§6set job: §7/job set JOB\n"
                                            + "§6list of all jobs: §7/job list\n" + "§6stats of your job: §7/job mine\n"
                                            + "§6list of all available commands: §7/job help");
                            if (player.isOp()) {
                                player.sendMessage("§cSERVER OPERATOR STUFF:\n"
                                        + "§6list of jobs of online players: §7/job listAll\n"
                                        + "§6cheat XP to job: §7/job xp XP\n"
                                        + "§6set your job new (regardless if you have one or not): §7/job put JOB\n"
                                        + "§6de/activate pvpmode: §7/job config pvpmode BOOL");
                            }
                            return true;
                        case "listAll":
                            if (player.isOp()) {
                                player.sendMessage("§6Your job:\n" + "§6job §7" + info[0] + " §6xp §7" + info[1]);
                                List<Player> allplayers = player.getWorld().getPlayers();
                                for (Player singplayer : allplayers) {
                                    info = new Jobsql(log).readfromJobTable(player.getName());
                                    player.sendMessage("§6" + singplayer.getName() + "'s job:\n" + "§6job §7" + info[0]
                                            + " §6xp §7" + info[1]);
                                }
                            }
                            return true;
                        default:
                            player.sendMessage("§e ---------- §fTip: job §e---------- \n"
                                    + "§6You have to first specify, which job you want\n"
                                    + "Like that: §7/job set JOB\n§6You can get a list of  all jobs with: §7/job list"
                                    + "\n§6You can get a list of all available commands with: §7/job help");
                            log.info(player.getName() + " has forgot Arguments: " + Arrays.toString(args));
                            return false;
                    }
                } else {
                    switch (args[0]) {
                        case "set":
                            if (info[0] == null) {
                                return switchJobs(args[1], player, true);
                            } else {
                                player.sendMessage("§6You already have a job (§7" + info[0] + "§6)");
                                return false;
                            }
                        case "put":
                            if (!player.isOp()) {
                                player.sendMessage("§e ---------- §fTip: job §e---------- \n"
                                        + "§7You are not allowed to do that yet\n"
                                        + "\n§6You can get a list of all available commands with: §7/job help");
                                return false;
                            }
                            new Jobsql(log).deletefromJobTable(player.getName());
                            return switchJobs(args[1], player, true);
                        case "xp":
                            if (!player.isOp()) {
                                player.sendMessage("§e ---------- §fTip: job §e---------- \n"
                                        + "§7You are not allowed to do that yet\n"
                                        + "\n§6You can get a list of all available commands with: §7/job help");
                                return false;
                            }
                            new Jobsql(log).AddXp(player.getName(), Integer.parseInt(args[1]));
                            player.sendMessage("§6Set xp to §7" + args[1]);
                            return false;

                        case "config":
                            if (player.isOp()) {
                                if (args.length == 3) {
                                    defConfig.set(args[1], args[2]);
                                    player.sendMessage("§e DISCLAIMER:: NOT WORKING YET \n" + "§7Config of §6" + args[1]
                                            + "§7 set to §6" + args[2] + "\n");
                                    return true;
                                } else {
                                    player.sendMessage("§e ---------- §fTip: job §e---------- \n"
                                            + "§6You used it wrong" + "\n§6list of all existing Jobs: §7/job list"
                                            + "\n§6You can get a list of all available commands with: §7/job help");
                                    return false;
                                }
                            }
                        default:
                            player.sendMessage(
                                    "§e ---------- §fTip: job §e---------- \n" + "§6Usage: §7/job set blacksmith/miner"
                                            + "\n§6list of all existing Jobs: §7/job list"
                                            + "\n§6You can get a list of all available commands with: §7/job help");
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

    public boolean switchJobs(String arg, Player player, boolean add) {
        Set<String> alljobs = jobConfig.getKeys(false);
        for (String job : alljobs) {
            if (arg.equals(job)) {
                if (add) {
                    player.sendMessage("§6Set Job to " + arg);
                    new Jobsql(log).addtoJobTable(player.getName(), arg);
                } else {
                    player.sendMessage("§6Set Job to " + arg);
                    new Jobsql(log).UpdateJobinJobTable(player.getName(), arg);
                }
                return true;
            }
        }
        player.sendMessage("§6Coulnd't set Job to §7" + arg);
        return false;
    }
}