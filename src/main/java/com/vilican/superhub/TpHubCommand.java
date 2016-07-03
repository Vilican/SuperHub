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
                Player player = (Player) sender;
                ConfigAccessor config = new ConfigAccessor(Main.getPlugin(), "config.yml");
                World w = getWorld(config.getConfig().getString("world"));
                player.teleport(new Location(w, config.getConfig().getInt("x"), config.getConfig().getInt("y"), config.getConfig().getInt("z")));
                config.saveConfig();
                sender.sendMessage(ChatColor.GREEN + "You have been teleported to the hub.");
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
