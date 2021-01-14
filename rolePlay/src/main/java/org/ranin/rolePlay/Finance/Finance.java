package org.ranin.rolePlay.finance;

import org.bukkit.entity.Entity;

/*
author: ["chibbi","raninninn"]
description: "job class, it is a template for job"
TODO: ["think"]
*/

import org.bukkit.entity.Player;
import java.util.List;
import java.util.logging.Logger;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Finance {

    private Logger log;

    public FileConfiguration jobConfig;
    public FileConfiguration xpConfig;

    public Finance(Logger logg) {
        // jobConfig = new JobConfig(logg).getCustomConfig();
        // xpConfig = new XpConfig(logg).getCustomConfig();
        log = logg;
    }

    public boolean PlusMoney(int __VALUE, Player owner) {
        // add __VALUE to row `owner`
        // return success or failure
    }
    public boolean MinusMoney(int __VALUE, Player owner) {
        // subtract __VALUE from row `owner`
        // return success or failure

    }
    public int GetBalance(Player owner) {
        // return value of row `owner`
    }
    public SetBalance(int __VALUE, Player owner) {
        // set balance field of row `owner` to __VALUE
    }

    public boolean CreateAccount(Player owner) {
        // if `owner` iselementof table {return false} else {create new row `owner`; SetBalance(DEFAULT, `owner`); return true}
    }
    public boolean RemoveAccount(Player owner) {
        // if `owner`iselementof table {remove row `owner`; return true} else {return false}
    }

    // if(PLAYER does JOB_TASK) {PlusMoney(SALARY, PLAYER)}

}
