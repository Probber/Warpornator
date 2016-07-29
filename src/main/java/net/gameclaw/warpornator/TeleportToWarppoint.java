package net.gameclaw.warpornator;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Probber on 29.07.2016.
 */
public class TeleportToWarppoint implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

        if (args.length != 0) {

            if (sender instanceof Player) {

                Location destination = WarpList.getLocation(args[0]);

                if (destination != null) {

                    ((Player) sender).teleport(destination);
                    Location senderLoc = ((Player) sender).getLocation();

                        sender.sendMessage("Teleportation to the Warppoint was successfull.");
                        return true;

                } else {
                    sender.sendMessage("Error by getting the Location of the Warppoint.");
                    return false;
                }

            } else {
                sender.sendMessage("Dear console, you cannot teleport to a player.");
                return false;
            }

        } else {
            sender.sendMessage("Please add a Warppoint you want to teleport to.");
            return false;
        }
    }
}
