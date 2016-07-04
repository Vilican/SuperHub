/*
 * Copyright (C) 2016 vilican
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.vilican.superhub;

import static com.vilican.superhub.MoveEvent.checked;
import static java.lang.Thread.sleep;
import static org.bukkit.Bukkit.getWorld;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author vilican
 */
public class TpHubCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("help")) {
            com.vilican.superhub.Functions.displayHelp(sender);
            return true;
        } else if (sender instanceof Player) {
            if (sender.hasPermission("hub.tphub")) {
                final Player player = (Player) sender;
                if (!sender.hasPermission("hub.immediate")) {
                    sender.sendMessage(ChatColor.BLUE + "You will be teleported to the hub. Do not move for 10 seconds.");
                    MoveEvent.addPlayer(player);
                    new java.util.Timer().schedule(
                            new java.util.TimerTask() {
                        @Override
                        public void run() {
                            if (checked.containsKey(player)) {
                                MoveEvent.removePlayer(player);
                                MoveEvent.teleport(player);
                            }
                        }
                    }, 10000);
                } else {
                    MoveEvent.teleport(player);
                }
                return true;
            } else {
                sender.sendMessage(ChatColor.RED + "Access denied!");
                return true;
            }
        } else {
            sender.sendMessage(ChatColor.RED + "This command can be only used by player!");
            return true;
        }
    }
}
