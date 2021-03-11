package de.keinbyte.cpschecker.utils;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ConfigUtil {

    public static File file = new File("plugins/CpsChecker", "config.yml");
    public static YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

    public static void load() {
        try {
            if (config.getString("config.cpscheck.settings.maxclicks") == null) {
                config.set("config.cpscheck.settings.maxclicks", 30);
            }
            if (config.getString("config.cpscheck.settings.place") == null) {
                config.set("config.cpscheck.settings.place", true);
            }
            if (config.getString("config.cpscheck.settings.damage") == null) {
                config.set("config.cpscheck.settings.damage", true);
            }
            if (config.getString("config.cpscheck.permission.check") == null) {
                config.set("config.cpscheck.permission.check", "check.command");
            }
            if (config.getString("config.cpscheck.permission.checkbypass") == null) {
                config.set("config.cpscheck.permission.checkbypass", "check.checkbypass");
            }
            if (config.getString("config.cpscheck.message.noperms") == null) {
                config.set("config.cpscheck.message.noperms", "%PREFIX% &cDazu hast du keine Rechte!");
            }
            if (config.getString("config.cpscheck.message.prefix") == null) {
                config.set("config.cpscheck.message.prefix", "&8[&9CPS-Check&8]");
            }
            if (config.getString("config.cpscheck.message.check") == null) {
                config.set("config.cpscheck.message.check", "%PREFIX% &7Der Spieler &a%PLAYER% &7hat &a%CPS% &7cps.");
            }
            if (config.getString("config.cpscheck.message.checkin") == null) {
                config.set("config.cpscheck.message.checkin", "%PREFIX% &7Du checkst nun &a%PLAYER%&7.");
            }
            if (config.getString("config.cpscheck.message.checkaout") == null) {
                config.set("config.cpscheck.message.checkaout", "%PREFIX% &7Du checkst nun nicht mehr &a%PLAYER%&7.");
            }
            if (config.getString("config.cpscheck.message.checkcurrent") == null) {
                config.set("config.cpscheck.message.checkcurrent", "%PREFIX% &cDu checkst bereits %PLAYER%!");
            }
            if (config.getString("config.cpscheck.message.checknotcurrent") == null) {
                config.set("config.cpscheck.message.checknotcurrent", "%PREFIX% &cDu checkst niemand!");
            }
            if (config.getString("config.cpscheck.message.checkuse") == null) {
                List<String> use = config.getStringList("config.cpscheck.message.checkuse");
                use.add("%PREFIX% &7/cps start <Spieler>");
                use.add("%PREFIX% &7/cps stop");
                config.set("config.cpscheck.message.checkuse", use);
            }
            if(config.getString("config.cpscheck.message.nosendercheck") == null) {
                config.set("config.cpscheck.message.nosendercheck", "%PREFIX% &cDu kannst dich nicht checken!");
            }
            if(config.getString("config.cpscheck.message.playerisoffline") == null) {
                config.set("config.cpscheck.message.playerisoffline", "%PREFIX% &cDer Spieler %PLAYER% ist nicht auffindbar!");
            }
            if(config.getString("config.cpscheck.message.noplayername") == null) {
                config.set("config.cpscheck.message.noplayername", "%PREFIX% &cBitte gib einen Spielernamen an!");
            }
            config.save(file);
        } catch (IOException e) {
        }
    }

}
