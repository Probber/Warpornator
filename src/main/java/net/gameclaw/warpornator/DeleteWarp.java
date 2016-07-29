package net.gameclaw.warpornator;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Represents a Command that deletes a Warppoint
 */
public class DeleteWarp implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length != 0) {

            if (sender instanceof Player) {

                Boolean result = WarpList.deleteWarppoint(args[0]);

                if (result != false) {
                    sender.sendMessage("Warppoint: " + args[0] + " got deleted.");
                    return true;

                } else {
                    sender.sendMessage("Error by deleting the Warppoint.");
                    return false;
                }

            } else {
                sender.sendMessage("A Console cannot fire this command.");
                return false;
            }

        } else {
            sender.sendMessage("Please add a Warppoint to your argument.");
            return false;
        }
    }
}
