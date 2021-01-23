package org.ranin.rolePlay;

import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class WarnTabCompletion implements TabCompleter {

    private Logger log;

    public WarnTabCompletion(Logger logg) {
        log = logg;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> l = new ArrayList<String>(); // makes a ArrayList
        // define the possible possibility's for argument 1
        if (args.length == 1) {
            for (Player sing : Bukkit.getOnlinePlayers()) {
                l.add(sing.getName());
            }
        }
        // define the possible possibility's for argument 2
        else if (args.length >= 2) {
            l.add("TEXT"); // Possibility #1
            l.add("TEXT"); // Possibility #2
        }
        return l; // returns the possibility's to the client
    }

}
