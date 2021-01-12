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
        if (App.config.getBoolean("imperatormode")) {
            event.getPlayer().sendTitle("all HAIL imeperator CHIBBI!", "", 40, 100, 40);
        } else {
            event.getPlayer().sendTitle("You are awesome!", "", 40, 100, 40);
        }
        event.setJoinMessage("Welcome, " + event.getPlayer().getName() + "!");
    }
}