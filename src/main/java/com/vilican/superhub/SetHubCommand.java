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

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author vilican
 */
public class SetHubCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("help")) {
            com.vilican.superhub.Functions.displayHelp(sender);
            return true;
        } else if (sender instanceof Player) {
            if (sender.hasPermission("hub.sethub")) {
                Player player = (Player) sender;
                Location loc = player.getLocation();
                ConfigAccessor config = new ConfigAccessor(Main.getPlugin(), "config.yml");
                config.getConfig().set("x", loc.getBlockX());
                config.getConfig().set("y", loc.getBlockY());
                config.getConfig().set("z", loc.getBlockZ());
                config.getConfig().set("world", loc.getWorld().getName());
                config.saveConfig();
                sender.sendMessage(ChatColor.GREEN + "Hub coordinates written to config.");
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
