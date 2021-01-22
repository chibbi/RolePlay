package org.ranin.rolePlay.Finance;

/*
author: ["chibbi","raninninn"]
description: "Initiator of the whole Plugin"
TODO: []
sources:
    mysqlCommands: "http://g2pc1.bu.edu/~qzpeng/manual/MySQL%20Commands.htm"
    mysql: "https://www.vogella.com/tutorials/MySQLJava/article.html"
    sqlite: "https://www.sqlitetutorial.net/sqlite-java/"
*/

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;
import org.bukkit.Bukkit;

public class Financesql {

    private Logger log;
    private Connection conn = null;

    String uuidUlr = "https://api.mojang.com/users/profiles/minecraft/";

    private String playerLoc = "jdbc:sqlite:plugins/rolePlay/db/Player.db";
    private String dbname = "playerfinances";

    public Financesql(Logger logg) {
        log = logg;
        if (!connect()) {
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "shutdown");
        }
    }

    private boolean connect() {
        File dir = new File("plugins/rolePlay/db/");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        try {
            conn = DriverManager.getConnection(playerLoc);

            log.fine("connected to Player database");
            return true;
        } catch (SQLException e) {
            log.severe("\033[31mCould not connect to Player database\033[39m at: " + playerLoc);
            log.warning(e.getMessage());
            return false;
        }
    }

    public boolean disconnect() {
        try {
            if (conn != null) {
                conn.close();
                log.fine("disconnected from Player database");
            }
            return true;
        } catch (SQLException e) {
            log.warning("\033[31mCould not disconnect to Player database\033[39m");
            log.info(e.getMessage());
            return false;
        }
    }

    public boolean createAccountTable() {
        String sql = "CREATE TABLE IF NOT EXISTS " + dbname + " (\n" + "  player_id integer PRIMARY KEY,\n"
                + " player text NOT NULL UNIQUE,\n" + "    balance int NOT NULL,\n" + "    banker boolean NOT NULL\n" + ");";
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            disconnect();
            return true;
        } catch (SQLException e) {
            log.warning("\033[31mCould not create a table (" + dbname + ") on Player database\033[39m");
            log.info(e.getMessage());
        }
        disconnect();
        return false;
    }

    public boolean addtoAccountTable(String player, Integer balance, Boolean banker) {
        String sql = "INSERT INTO " + dbname + "(player, balance, banker) VALUES(?,?,?);";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, getUuid(player));
            pstmt.setInt(2, balance);
            pstmt.setBoolean(3, banker);
            pstmt.executeUpdate();
            disconnect();
            return true;
        } catch (SQLException e) {
            log.warning("\033[31mCould not add " + player + " with " + balance + " and " + banker + " to " + dbname + " on " + dbname
                    + " database\033[39m");
            log.info(e.getMessage());
        }
        disconnect();
        return false;
    }

    public Integer[] readfromAccountTable(String player) {
        Integer[] res = new Integer[2];
        String sql = "SELECT * FROM " + dbname + " WHERE player=?;";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, getUuid(player));
            ResultSet rs = pstmt.executeQuery();
            res[0] = rs.getInt("balance");
            res[1] = (rs.getBoolean("banker")) ? 1 : 0;
            // log.info("\033[31m INFO: " + Arrays.toString(res) + "\033[39m");
            disconnect();
        } catch (SQLException e) {
            res[0] = null;
        }
        disconnect();
        return res;
    }

    public String[] UpdateAccountinAccountTable(Integer balance,String player) {
        String[] res = new String[8];
        String sql = "UPDATE " + dbname + " SET = ? WHERE player=?;";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, balance);
            pstmt.setString(2, getUuid(player));
            pstmt.executeUpdate();
            disconnect();
            return res;
        } catch (SQLException e) {
            log.warning(
                    "\033[31mCould not read Account Table at player = " + player + " on " + dbname + " database\033[39m");
            log.info(e.getMessage());
        }
        disconnect();
        return res;
    }

    public String[] UpdateBankerStateinAccountTable(String player, String column, Boolean banker) {
        String[] res = new String[8];
        String sql = "UPDATE " + dbname + " SET " + column + " = ? WHERE player=?;";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setBoolean(1, banker);
            pstmt.setString(2, getUuid(player));
            pstmt.executeUpdate();
            disconnect();
            return res;
        } catch (SQLException e) {
            log.warning("\033[31mCould not update BankerState in Accounttable at player = " + player + " on " + dbname
                    + " database\033[39m");
            log.info(e.getMessage());
        }
        disconnect();
        return res;
    }

    public boolean deletefromAccountTable(String player) {
        String sql = "DELETE FROM " + dbname + " WHERE player = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, getUuid(player));
            pstmt.executeUpdate();
            disconnect();
            return true;
        } catch (SQLException e) {
            log.warning("\033[31mCould not delete " + player + " in " + dbname + " on " + dbname + " database\033[39m");
            log.info(e.getMessage());
        }
        disconnect();
        return false;
    }

    public String getUuid(String player) {
        return(player);

    }

    public String getColumn(int i) {
        switch (i) {
            case 0:
                return "balance";
            case 1:
                return "banker";
            default:
                return "ERROR in getColumn(" + i + ")";
        }
    }
}
