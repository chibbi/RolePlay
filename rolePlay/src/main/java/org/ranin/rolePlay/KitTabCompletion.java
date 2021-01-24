package org.ranin.rolePlay;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class KitTabCompletion implements TabCompleter {

    private Logger log;

    public KitTabCompletion(Logger logg) {
        log = logg;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> l = new ArrayList<String>();
        if (args.length == 1) {
            l.add("diamond");
            l.add("bricks");
        } else if (args.length == 2) {
            l.add("TEXT");
        }
        return l;
    }

}
