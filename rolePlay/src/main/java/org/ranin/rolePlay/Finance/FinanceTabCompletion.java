package org.ranin.rolePlay.Finance;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class FinanceTabCompletion implements TabCompleter {

    private Logger log;

    public FinanceTabCompletion(Logger logg) {
        log = logg;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> l = new ArrayList<String>();
        if (args.length == 1) {
            l.add("help");
            l.add("balance");
            l.add("add");
            l.add("set");
            l.add("transfer");
            // if (sender.isOp()) {
            l.add("create-account");
            l.add("remove-account");
            l.add("reset-account");
            // }
        } else if (args.length == 2) {
            switch (args[0]) {
                case "add":
                    l.add("100");
                    break;
                case "set":
                    l.add("100");
                    break;
                case "transfer":
                    l.add("100");
                    break;
            }
        } else if (args.length == 3) {
            switch (args[0]) {
                case "transfer":
                    for (Player sing : Bukkit.getOnlinePlayers()) {
                        l.add(sing.getName());
                    }
                    break;
            }
        }
        return l;
    }

}
