package net.gameclaw.warpornator;

import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

/**
 * The Main Class
 */
public class Warpornator extends JavaPlugin {

    public static HashMap<String, Location> wpList = new HashMap<String, Location>();

    @Override
    public void onEnable(){
        this.getCommand("addWP").setExecutor(new SetWarp());
        this.getCommand("removeWP").setExecutor(new DeleteWarp());
        this.getCommand("tpToWP").setExecutor(new TeleportToWarppoint());
    }

    @Override
    public void onDisable(){


    }
}
