package de.keinbyte.cpschecker.listeners;

import de.keinbyte.cpschecker.CpsChecker;
import de.keinbyte.cpschecker.utils.ConfigUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageByEntityListener implements Listener {

    @EventHandler
    public void handleEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            if (ConfigUtil.config.getBoolean("config.cpscheck.settings.damage") == true) {
                if (!player.hasPermission(ConfigUtil.config.getString("config.cpscheck.permission.checkbypass"))) {
                    if (CpsChecker.clicks.get(player) > ConfigUtil.config.getInt("config.cpscheck.settings.maxclicks")) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }

}
