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
import java.util.logging.Logger;

import org.bukkit.Bukkit;

public class Jobsql {

    private Logger log;
    private Connection conn = null;

    String uuidUlr = "https://api.mojang.com/users/profiles/minecraft/";

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
            pstmt.setString(1, getUuid(player));
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
        String[] res = new String[8];
        String sql = "SELECT * FROM " + dbname + " WHERE player=?;";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, getUuid(player));
            ResultSet rs = pstmt.executeQuery();
            res[0] = rs.getString("main_job");
            res[1] = String.valueOf(rs.getInt("main_job_xp"));
            res[2] = rs.getString("second_job");
            res[3] = String.valueOf(rs.getInt("second_job_xp"));
            res[4] = rs.getString("main_hobby");
            res[5] = String.valueOf(rs.getInt("main_hobby_xp"));
            res[6] = rs.getString("second_hobby");
            res[7] = String.valueOf(rs.getInt("second_hobby_xp"));
            // log.info("\033[31m INFO: " + Arrays.toString(res) + "\033[39m");
            disconnect();
        } catch (SQLException e) {
            // log.warning(
            // "\033[31mCould not read Job Table at player = " + player + " on " + dbname +
            // " database\033[39m");
            // log.info(e.getMessage());
            res[0] = null;
        }
        disconnect();
        return res;
    }

    public String[] UpdateJobinJobTable(String player, String column, String job) {
        String[] res = new String[8];
        String sql = "UPDATE " + dbname + " SET " + column + " = ? WHERE player=?;";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, job);
            pstmt.setString(2, getUuid(player));
            pstmt.executeUpdate();
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

    public String[] UpdateXpinJobTable(String player, String column, int xp) {
        String[] res = new String[8];
        String sql = "UPDATE " + dbname + " SET " + column + " = ? WHERE player=?;";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, xp);
            pstmt.setString(2, getUuid(player));
            pstmt.executeUpdate();
            disconnect();
            return res;
        } catch (SQLException e) {
            log.warning("\033[31mCould not update Xp in Jobtable at player = " + player + " on " + dbname
                    + " database\033[39m");
            log.info(e.getMessage());
        }
        disconnect();
        return res;
    }

    public void AddXp(String player, int i, int j, String[] info) {
        player = getUuid(player);
        if (info[i + 1] == null) {
            // TODO: less xp for not main_job and even less for hobbys ....
            UpdateXpinJobTable(player, getColumn(i + 1), j);
        } else {
            // TODO: less xp for not main_job and even less for hobbys ....
            UpdateXpinJobTable(player, getColumn(i + 1), Integer.parseInt(info[i + 1]) + j);
        }
    }

    public boolean deletefromJobTable(String player) {
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
        return (player);
    }

    public String getColumn(int i) {
        switch (i) {
            case 0:
                return "main_job";
            case 1:
                return "main_job_xp";
            case 2:
                return "second_job";
            case 3:
                return "second_job_xp";
            case 4:
                return "main_hobby";
            case 5:
                return "main_hobby_xp";
            case 6:
                return "second_hobby";
            case 7:
                return "second_hobby_xp";
            default:
                return "ERROR in getColumn(" + i + ")";
        }
    }
}
