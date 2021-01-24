package org.ranin.rolePlay.Finance;

/*
author: "raninninn"
description: "finance class, it is a template for job"
TODO: ["think"]
*/

import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;

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

    public Integer GetBalance(String owner) {
        return new Financesql(log).readfromAccountTable(owner)[0];
    }

    public void SetBalance(int balance, String owner) {
        new Financesql(log).UpdateAccountinAccountTable(balance, owner);
    }

    public boolean CreateAccount(String owner) {
        if (GetBalance(owner) != null) {
            return false;
        }
        return new Financesql(log).addtoAccountTable(owner, 2000, false);
    }

    public boolean RemoveAccount(String owner) {
        return new Financesql(log).deletefromAccountTable(owner);
    }
}
