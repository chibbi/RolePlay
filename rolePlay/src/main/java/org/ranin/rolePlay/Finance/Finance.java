package org.ranin.rolePlay.Finance;

/*
author: "raninninn"
description: "finance class, it is a template for job"
TODO: ["think"]
*/

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Logger;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.entity.Entity;

public class Finance {

    private Logger log;

    public FileConfiguration jobConfig;
    public FileConfiguration xpConfig;

    public Finance(Logger logg) {
        log = logg;
    }

    public boolean PlusMoney(int balance, Player owner) {
        int currentBalance = readfromAccountTable(owner);
        UpdateAccountinAccountTable(currentBalance + value, owner);
        return true;
    }
    public boolean MinusMoney(int value, Player owner) {
        int currentBalance = readfromAccountTable(owner);
        UpdateAccountinAccountTable(currentBalance - value, owner);
        return true;
    }
    public int GetBalance(Player owner) {
        readfromAccountTable(owner);
        return 1000000000;
    }
    public void SetBalance(int balance, Player owner) {
        UpdateAccountinAccountTable(balance, owner)
    }

    public boolean CreateAccount(Player owner) {
        addtoAccountTable(owner, 2000, false);
        return true;
    }
    public boolean RemoveAccount(Player owner) {
        deletefromAccountTable(owner);
        return true;
    }
}
