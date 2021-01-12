package org.ranin.rolePlay.job;

import java.io.File;

/*
author: "chibbi"
description: "Initiator of the whole Plugin"
TODO: ["interfaces for other classes", "create,load and unload DB", "create,select,read,save stuff in DB"]
sources:
    mysqlCommands: "http://g2pc1.bu.edu/~qzpeng/manual/MySQL%20Commands.htm"
    mysql: "https://www.vogella.com/tutorials/MySQLJava/article.html"
    sqlite: "https://www.sqlitetutorial.net/sqlite-java/"
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class Jobsql {

    private Logger log;
    private Connection conn = null;

    private String playerLoc = "jdbc:sqlite:plugins/rolePlay/db/Player.db";

    public Jobsql(Logger logg) {
        log = logg;
    }

    public boolean connect() {
        File dir = new File("plugins/rolePlay/db/");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        try {
            conn = DriverManager.getConnection(playerLoc);

            log.info("connected to Player database");
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
                log.info("disconnected from Player database");
            }
            return true;
        } catch (SQLException e) {
            log.warning("\033[31mCould not disconnect to Player database\033[39m");
            log.info(e.getMessage());
            return false;
        }
    }

    public boolean createJobTable() {
        String sql = "CREATE TABLE IF NOT EXISTS playerjobs (\n" + "  player_id integer AUTO_INCREMENT PRIMARY KEY,\n"
                + " player text NOT NULL UNIQUE,\n" + "    job text NOT NULL,\n" + "    xp integer\n" + ");";
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            return true;
        } catch (SQLException e) {
            log.warning("\033[31mCould not create a table (playerjobs) on playerjobs database\033[39m");
            log.info(e.getMessage());
        }
        return false;
    }

    public boolean addJobtoTable(String player, String job) {
        String sql = "INSERT INTO playerjobs(player,job) VALUES(?,?);";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, player);
            pstmt.setString(2, job);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            log.warning("\033[31mCould not add " + player + " with " + job
                    + " to playerjobs on playerjobs database\033[39m");
            log.info(e.getMessage());
        }
        return false;
    }

}
