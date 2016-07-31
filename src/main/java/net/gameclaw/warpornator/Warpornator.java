package net.gameclaw.warpornator;

import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.logging.Level;

/**
 * The Main Class
 */
public class Warpornator extends JavaPlugin {

    public static Warpornator main;
    public static IOHandler warpFile = new IOHandler();

    @Override
    public void onEnable(){

        main = this;

        IOHandler warpFile = new IOHandler();

        Boolean checkResult = warpFile.checkForFile();

        if (checkResult != false) {

            Boolean fillResult = warpFile.fillMap();

            if (fillResult != false) {
                this.getCommand("addWP").setExecutor(new SetWarp());
                this.getCommand("removeWP").setExecutor(new DeleteWarp());
                this.getCommand("tpToWP").setExecutor(new TeleportToWarppoint());
                this.getLogger().log(Level.INFO, "Success at loading Warpornator!");

            } else {
                this.getLogger().log(Level.WARNING, "WARNING: Warpornator got not loaded properly please reload!");
            }

        } else {
            this.getLogger().log(Level.WARNING, "WARNING: Warpornator got not loaded properly please reload!");
        }
    }

    @Override
    public void onDisable(){


    }
}
