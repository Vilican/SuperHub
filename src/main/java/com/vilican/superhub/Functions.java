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
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author vilican
 */
public class Functions {

    public static void displayHelp(Object sender) {
        if (sender instanceof Player) {
            Player playersender = (Player) sender;
            playersender.sendMessage(ChatColor.AQUA + "=====================================================");
            playersender.sendMessage(ChatColor.AQUA + "");
            playersender.sendMessage(ChatColor.AQUA + "SuperHub v1.1 by vilican");
            playersender.sendMessage(ChatColor.AQUA + "");
            playersender.sendMessage(ChatColor.AQUA + "/hub - teleports you to the hub");
            playersender.sendMessage(ChatColor.AQUA + "/sethub - set hub coordinates to here");
            playersender.sendMessage(ChatColor.AQUA + "");
            playersender.sendMessage(ChatColor.AQUA + "=====================================================");
        } else if (sender instanceof ConsoleCommandSender) {
            System.out.println("=====================================================");
            System.out.println("SuperHub v1.1 by vilican");
            System.out.println("/hub - teleports you to the hub");
            System.out.println("/sethub - set hub coordinates to here");
            System.out.println("=====================================================");
        }
    }

}
