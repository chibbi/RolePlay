package org.ranin.rolePlay.job;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Tasks {

    private Logger log;

    private FileConfiguration jobConfig;

    public Tasks(Logger logg) {
        jobConfig = new JobConfig(logg).getCustomConfig();
        log = logg;
    }

    private void giveEffect(Player player, PotionEffectType potion, int amplifier) {
        if (amplifier <= 0 || potion == null) {
        } else {
            player.addPotionEffect(new PotionEffect(potion, Integer.MAX_VALUE, amplifier - 1));
            // FOR SOME REASON it uses amplifier = 1 and gives POTION EFFECT with level 2
            // (thats why i do -1)
            player.sendMessage("Gave effect: " + potion.getName() + " strength: " + amplifier);
        }
    }

    public void loadEffects(Player player) {
        String[] info = new Jobsql(log).readfromJobTable(player.getName());
        if (info[0] == null) {
            log.fine("No Job yet");
        } else {
            for (PotionEffect eff : player.getActivePotionEffects()) {
                player.removePotionEffect(eff.getType());
            }
            setEffects(player, info[0], (Integer.parseInt(info[1])));
        }
    }

    public void setEffects(Player player, String job, int xp) {
        int posAmp = xp / 10000; // TODO: creat a logarithmic function instead of just / 10000
        // ex.:
        // 10000xp = amp 1
        // 23000xp = amp 2
        // 35000xp = amp 3
        // 48000xp = amp 4
        // 65000xp = amp 5
        // and may multiply with a constant for changing how much xp is needed for a
        // milestone
        ArrayList<String> positives = (ArrayList<String>) jobConfig.getStringList(job + ".effects.positives");
        for (String positive : positives) {
            if (positive.strip() == "" || positive == null || positive == "TEMPLATE") {
                break;
            }
            giveEffect(player, PotionEffectType.getByName(positive), posAmp);
        }
        int negAmp = xp / 15000; // TODO: same as posAmp (likely with a different constant)
        ArrayList<String> negatives = (ArrayList<String>) jobConfig.getStringList(job + ".effects.negatives");
        for (String negative : negatives) {
            if (negative.strip() == "" || negative == null || negative == "TEMPLATE") {
                break;
            }
            giveEffect(player, PotionEffectType.getByName(negative), negAmp);
        }
    }

    public void loadEveryoneEffects(World wor) {
        List<Player> allplayers = wor.getPlayers();
        for (Player singplayer : allplayers) {
            loadEffects(singplayer);
        }
    }

    public void giveNearbyReg(World wor) {
        List<Player> allplayers = wor.getPlayers();
        for (Player singplayer : allplayers) {
            String[] info = new Jobsql(log).readfromJobTable(singplayer.getName());
            if (info[0] != null && info[0].equals("knight")) {
                double maxDist = 10L; // TODO make configurable
                for (Player other : allplayers) {
                    if (other != singplayer && other.getLocation().distance(singplayer.getLocation()) <= maxDist) {
                        String[] inf = new Jobsql(log).readfromJobTable(other.getName());
                        if (inf[0] != null && inf[0] != "assassin") {
                            other.addPotionEffect(new PotionEffect(PotionEffectType.getByName("REGENERATION"), 65,
                                    Integer.parseInt(info[1])));
                        }
                    }
                }
            }
        }
    }

}
