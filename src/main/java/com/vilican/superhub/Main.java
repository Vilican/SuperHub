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

import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author vilican
 */
public class Main extends JavaPlugin {

    public static JavaPlugin plugin;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        plugin = this;
        this.getCommand("sethub").setExecutor(new SetHubCommand());
        this.getCommand("hub").setExecutor(new TpHubCommand());
        getServer().getPluginManager().registerEvents(new MoveEvent(), this);
        System.out.println("SuperHub v1.1 by vilican");
        System.out.println("Plugin enabled and running");
    }

    @Override
    public void onDisable() {
        plugin = null;
        System.out.println("Plugin disabled");
    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }
}
