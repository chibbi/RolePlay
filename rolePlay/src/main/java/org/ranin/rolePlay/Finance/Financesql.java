package org.ranin.rolePlay.finance;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.bukkit.entity.Player;
import java.util.List;
import java.util.logging.Logger;

public class Financesql {
    private Logger log;
    private Connection conn = null;

    private String playerLoc = "jdbc:sqlite:plugins/rolePlay/db/Player.db";
    private String dbname = "playerfinances";

    public Financesql(Logger logg) {
        log = logg;
        if (!connect()) {
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "shutdown");
        }
    }

    public boolean createFinanceTable() {
        String sql = "CREATE TABLE IF NOT EXISTS finances " + dbname + " (\n"
        + "    player_id integer PRIMARY KEY,\n"
        + "    player text NOT NULL UNIQUE,\n" 
        + "    Balance int NOT NULL,\n"
        + ");";
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            disconnect();
            return true;
        } catch (SQLException e) {
            log.warning("\033[31mCould not add " + player +  dbname + " on " + dbname
            + " database\033[39m");
        }
        disconnect();
        return false;
    }

    // TODO: get data from table
    // TODO: add data to table
}