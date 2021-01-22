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

    public boolean PlusMoney(int value, String owner) {
        int currentBalance = new Financesql(log).readfromAccountTable(owner)[0];
        new Financesql(log).UpdateAccountinAccountTable(currentBalance + value, owner);
        return true;
    }
    public boolean MinusMoney(int value, String owner) {
        int currentBalance = new Financesql(log).readfromAccountTable(owner)[0];
        new Financesql(log).UpdateAccountinAccountTable(currentBalance - value, owner);
        return true;
    }
    public int GetBalance(String owner) {
        return new Financesql(log).readfromAccountTable(owner)[0];
    }
    public void SetBalance(int balance, String owner) {
        new Financesql(log).UpdateAccountinAccountTable(balance, owner);
    }

    public boolean CreateAccount(String owner) {
        return new Financesql(log).addtoAccountTable(owner, 2000, false);
    }
    public boolean RemoveAccount(String owner) {
        return new Financesql(log).deletefromAccountTable(owner);
    }
}
