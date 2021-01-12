package org.ranin.rolePlay.job;

/*
author: "chibbi"
description: "Initiator of the whole Plugin"
TODO: ["interfaces for other classes", "create,load and unload DB", "create,select,read,save stuff in DB"]
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
import java.util.Arrays;
import java.util.logging.Logger;
import org.bukkit.Bukkit;

public class Jobsql {

    private Logger log;
    private Connection conn = null;

    private String playerLoc = "jdbc:sqlite:plugins/rolePlay/db/Player.db";
    private String dbname = "playerjobs";

    public Jobsql(Logger logg) {
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
        String sql = "CREATE TABLE IF NOT EXISTS " + dbname + " (\n" + "  player_id integer PRIMARY KEY,\n"
                + " player text NOT NULL UNIQUE,\n" + "    main_job text NOT NULL,\n" + "    main_job_xp integer,\n"
                + "    second_job text,\n" + "    second_job_xp integer,\n" + "    main_hobby text,\n"
                + "    main_hobby_xp integer,\n" + "    second_hobby text,\n" + "    second_hobby_xp integer\n" + ");";
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

    public boolean addtoJobTable(String player, String column, String job) {
        String sql = "INSERT INTO " + dbname + "(player," + column + ") VALUES(?,?);";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, player);
            pstmt.setString(2, job);
            pstmt.executeUpdate();
            disconnect();
            return true;
        } catch (SQLException e) {
            log.warning("\033[31mCould not add " + player + " with " + job + " to " + dbname + " on " + dbname
                    + " database\033[39m");
            log.info(e.getMessage());
        }
        disconnect();
        return false;
    }

    public String[] readfromJobTable(String player) {
        String[] res = new String[7];
        String sql = "SELECT * FROM " + dbname + " WHERE player = " + player + " ;";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            res[0] = rs.getString("main_job");
            res[1] = String.valueOf(rs.getInt("main_job_xp"));
            res[2] = rs.getString("second_job");
            res[3] = String.valueOf(rs.getInt("second_job_xp"));
            res[4] = rs.getString("main_hobby");
            res[5] = String.valueOf(rs.getInt("main_hobby_xp"));
            res[6] = rs.getString("second_hobby");
            res[7] = String.valueOf(rs.getInt("second_hobby_xp"));
            log.info("\033[31m INFO: " + Arrays.toString(res) + "\033[39m");
            disconnect();
            return res;
        } catch (SQLException e) {
            log.warning(
                    "\033[31mCould not read Job Table at player = " + player + " on " + dbname + " database\033[39m");
            log.info(e.getMessage());
        }
        disconnect();
        return res;
    }

    public boolean deletefromJobTable(String player) {
        String sql = "DELETE FROM " + dbname + " WHERE player = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, player);
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
}
