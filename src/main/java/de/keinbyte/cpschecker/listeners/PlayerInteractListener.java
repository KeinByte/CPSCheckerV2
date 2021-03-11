package de.keinbyte.cpschecker.listeners;

import de.keinbyte.cpschecker.CpsChecker;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void handlePlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (CpsChecker.clicks.get(player) == null) {
            CpsChecker.clicks.put(player, 1);
        } else {
            CpsChecker.clicks.put(player, CpsChecker.clicks.get(player) + 1);
        }
        Bukkit.getScheduler().runTaskLater(CpsChecker.getCpsChecker(), new Runnable() {
            @Override
            public void run() {
                CpsChecker.clicks.put(player, CpsChecker.clicks.get(player) - 1);
            }
        }, 20);
    }

}



