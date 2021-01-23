package org.ranin.rolePlay;

/*
author: "chibbi"
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
            event.getPlayer().sendTitle("all HAIL imperator CHIBBI!", "brought to you by Illuminati", 20, 45, 20);
        } else {
            event.getPlayer().sendTitle("You are awesome!", "", 20, 45, 20);
        }
        event.setJoinMessage("Welcome, " + event.getPlayer().getName() + "!");
        event.setJoinMessage("Quick Reminder, if you change your Username, you loose all your xp and money!");
        event.setJoinMessage("If you have any issues or find bugs, don't hesitate, and contact an admin or a mod!");
    }
}