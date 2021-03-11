package de.keinbyte.cpschecker.commands;

import de.keinbyte.cpschecker.CpsChecker;
import de.keinbyte.cpschecker.utils.ConfigUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CpsCommand implements CommandExecutor {

    public static ArrayList<Player> checks = new ArrayList<>();
    public static HashMap<Player, Player> check = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(ConfigUtil.config.getString("config.cpscheck.permission.check"))) {
                if (args.length != 0) {
                    if (args[0].equalsIgnoreCase("start") || args[0].equalsIgnoreCase("stop")) {
                        if (args[0].equalsIgnoreCase("start")) {
                            if (!checks.contains(player)) {
                                if (args.length != 1) {
                                    Player target = Bukkit.getPlayer(args[1]);
                                    if (target != null) {
                                        if (target != player) {
                                            checks.add(player);
                                            check.put(player, target);
                                            if (CpsChecker.clicks.get(target) == null) {
                                                CpsChecker.clicks.put(target, 0);
                                            }
                                            player.sendMessage(ConfigUtil.config.getString("config.cpscheck.message.checkin").replace("&", "§").replace("%PLAYER%", String.valueOf(check.get(player).getName())).replace("%PREFIX%", ConfigUtil.config.getString("config.cpscheck.message.prefix").replace("&", "§").replace("%PLAYER%", String.valueOf(check.get(player).getName()))));
                                            Bukkit.getScheduler().runTaskTimer(CpsChecker.getCpsChecker(), new Runnable() {
                                                @Override
                                                public void run() {
                                                    if (checks.contains(player)) {
                                                        if (CpsChecker.clicks.get(check.get(player)) < ConfigUtil.config.getInt("config.cpscheck.settings.maxclicks")) {
                                                            player.sendMessage(ConfigUtil.config.getString("config.cpscheck.message.check").replace("&", "§").replace("%PLAYER%", String.valueOf(check.get(player).getName())).replace("%CPS%", String.valueOf(CpsChecker.clicks.get(check.get(player)))).replace("%PREFIX%", ConfigUtil.config.getString("config.cpscheck.message.prefix").replace("&", "§")));
                                                        } else {
                                                            player.sendMessage(ConfigUtil.config.getString("config.cpscheck.message.check").replace("&", "§").replace("%PLAYER%", String.valueOf(check.get(player).getName())).replace("%CPS%", "§c" + String.valueOf(CpsChecker.clicks.get(check.get(player)))).replace("%PREFIX%", ConfigUtil.config.getString("config.cpscheck.message.prefix").replace("&", "§")));
                                                        }
                                                    }
                                                }
                                            }, 1, 1);
                                        } else {
                                            player.sendMessage(ConfigUtil.config.getString("config.cpscheck.message.nosendercheck").replace("&", "§").replace("%PREFIX%", ConfigUtil.config.getString("config.cpscheck.message.prefix").replace("&", "§")));
                                        }
                                    } else {
                                        player.sendMessage(ConfigUtil.config.getString("config.cpscheck.message.playerisoffline").replace("&", "§").replace("%PREFIX%", ConfigUtil.config.getString("config.cpscheck.message.prefix").replace("&", "§")).replace("%PLAYER%", args[1]));
                                    }
                                } else {
                                    player.sendMessage(ConfigUtil.config.getString("config.cpscheck.message.noplayername").replace("&", "§").replace("%PREFIX%", ConfigUtil.config.getString("config.cpscheck.message.prefix").replace("&", "§")));
                                }
                            } else {
                                player.sendMessage(ConfigUtil.config.getString("config.cpscheck.message.checkcurrent").replace("&", "§").replace("%PREFIX%", ConfigUtil.config.getString("config.cpscheck.message.prefix").replace("&", "§")).replace("%PLAYER%", String.valueOf(check.get(player).getName())));
                            }
                        }
                        if (args[0].equalsIgnoreCase("stop")) {
                            if (checks.contains(player)) {
                                checks.remove(player);
                                player.sendMessage(ConfigUtil.config.getString("config.cpscheck.message.checkaout").replace("&", "§").replace("%PLAYER%", String.valueOf(check.get(player).getName())).replace("%PREFIX%", ConfigUtil.config.getString("config.cpscheck.message.prefix").replace("&", "§")));
                                check.remove(player);
                            } else {
                                player.sendMessage(ConfigUtil.config.getString("config.cpscheck.message.checknotcurrent").replace("&", "§").replace("%PREFIX%", ConfigUtil.config.getString("config.cpscheck.message.prefix").replace("&", "§")));
                            }
                        }
                    } else {
                        List<String> use = ConfigUtil.config.getStringList("config.cpscheck.message.checkuse");
                        for (String list : use) {
                            player.sendMessage(list.replace("&", "§").replace("%PREFIX%", ConfigUtil.config.getString("config.cpscheck.message.prefix").replace("&", "§")));
                        }
                    }
                } else {
                    List<String> use = ConfigUtil.config.getStringList("config.cpscheck.message.checkuse");
                    for (String list : use) {
                        player.sendMessage(list.replace("&", "§").replace("%PREFIX%", ConfigUtil.config.getString("config.cpscheck.message.prefix").replace("&", "§")));
                    }
                }
            } else {
                player.sendMessage(ConfigUtil.config.getString("config.cpscheck.message.noperms").replace("&", "§").replace("%PREFIX%", ConfigUtil.config.getString("config.cpscheck.message.prefix").replace("&", "§")));
            }
        }
        return false;
    }

}
