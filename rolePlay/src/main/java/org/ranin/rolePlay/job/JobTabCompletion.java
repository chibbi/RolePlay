package org.ranin.rolePlay.job;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class JobTabCompletion implements TabCompleter {

    private Logger log;
    private FileConfiguration jobConfig;

    public JobTabCompletion(Logger logg) {
        jobConfig = new JobConfig(logg).getCustomConfig();
        log = logg;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> l = new ArrayList<String>();
        if (args.length == 1) {
            l.add("help");
            l.add("mine");
            l.add("list");
            l.add("set");
            if (sender.isOp()) {
                l.add("listAll");
                l.add("put");
                l.add("xp");
                l.add("config");
            }
        } else if (args.length == 2) {
            switch (args[0]) {
                case "set":
                    for (String key : jobConfig.getKeys(false)) {
                        l.add(key);
                    }
                    break;
            }
            if (sender.isOp()) {
                switch (args[0]) {
                    case "put":
                        for (String key : jobConfig.getKeys(false)) {
                            l.add(key);
                        }
                        break;
                    case "xp":
                        for (Player sing : Bukkit.getOnlinePlayers()) {
                            l.add(sing.getName());
                        }
                        break;
                    case "config":
                        l.add("pvpmode");
                        l.add("imperatormode");
                        break;
                }
            }
        } else if (args.length == 3) {
            if (sender.isOp()) {
                switch (args[0]) {
                    case "xp":
                        l.add("100");
                        break;
                }
            }
        }
        return l;
    }

}
