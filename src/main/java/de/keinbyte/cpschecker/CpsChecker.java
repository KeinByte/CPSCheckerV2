package de.keinbyte.cpschecker;

import de.keinbyte.cpschecker.commands.CpsCommand;
import de.keinbyte.cpschecker.listeners.BlockPlaceListener;
import de.keinbyte.cpschecker.listeners.EntityDamageByEntityListener;
import de.keinbyte.cpschecker.listeners.PlayerInteractListener;
import de.keinbyte.cpschecker.utils.ConfigUtil;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class CpsChecker extends JavaPlugin {

    public static HashMap<Player, Integer> clicks = new HashMap<>();

    public static CpsChecker cpsChecker;

    @Override
    public void onEnable() {
        cpsChecker = this;
        getServer().getPluginCommand("cps").setExecutor(new CpsCommand());
        getServer().getPluginManager().registerEvents(new BlockPlaceListener(), this);
        getServer().getPluginManager().registerEvents(new EntityDamageByEntityListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);
        ConfigUtil.load();
    }

    @Override
    public void onDisable() {
    }

    public static CpsChecker getCpsChecker() {
        return cpsChecker;
    }

}
