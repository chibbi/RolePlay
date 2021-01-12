package org.ranin.rolePlay;

/*
author: ["chibbi","raninninn"]
description: "contains Listeners"
TODO: ["maybe add GUI to choose jobs"]
*/

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Listeners implements Listener {

    public Listeners() {
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (App.config.getBoolean("youAreAwesome")) {
            event.getPlayer().sendMessage("You are awesome!");
        } else {
            event.getPlayer().sendMessage("You are not awesome...");
        }
        event.setJoinMessage("Welcome, " + event.getPlayer().getName() + "!");
    }
}