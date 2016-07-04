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

import java.util.HashMap;
import java.util.Map;
import static org.bukkit.Bukkit.getWorld;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 *
 * @author vilican
 */
public class MoveEvent implements Listener {

    static Map<Player, Location> checked = new HashMap<Player, Location>();

    public static void addPlayer(Player player) {
        checked.put(player, player.getLocation());
    }

    public static void removePlayer(Player player) {
        checked.remove(player);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (event.getFrom().getBlock().equals(event.getTo().getBlock())) {
            return;
        }
        if (checked.containsKey(event.getPlayer())) {
            removePlayer(event.getPlayer());
            event.getPlayer().sendMessage(ChatColor.RED + "You have moved, you will not be teleported to the hub!");
        }
    }

    public static void teleport(Player player) {
        ConfigAccessor config = new ConfigAccessor(Main.getPlugin(), "config.yml");
        World w = getWorld(config.getConfig().getString("world"));
        player.teleport(new Location(w, config.getConfig().getInt("x"), config.getConfig().getInt("y"), config.getConfig().getInt("z")));
        player.sendMessage(ChatColor.GREEN + "You have been teleported to the hub.");
    }
}