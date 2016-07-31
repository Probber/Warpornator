package net.gameclaw.warpornator;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


/**
 * Represents a command that is setting the WarpPoint.
 */
public class SetWarp implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length != 0) {

            if (sender instanceof Player) {

                Location senderLoc = ((Player) sender).getLocation();

                if (senderLoc.length() != 0) {

                    String dupResult = WarpList.checkForDuplicate(args[0]);

                    if (dupResult.equals("B")) {

                        Boolean result = WarpList.addWarppoint(args[0], senderLoc);

                        if (result != false) {

                            Boolean fileResult = Warpornator.warpFile.checkForFile();

                            if (fileResult != false) {

                                Boolean wtfResult = Warpornator.warpFile.writeToFile(args[0], senderLoc);

                                if (wtfResult != false) {
                                    sender.sendMessage("Added Warppoint: " + args[0]);
                                    return true;

                                } else {
                                    sender.sendMessage("Cannot write to warps.txt");
                                    return false;
                                }

                            } else {
                                sender.sendMessage("Cannot create warps.txt");
                                return false;
                            }

                        } else {
                            sender.sendMessage("Error by adding the Warppoint.");
                            return false;
                        }

                    } else if(dupResult.equals("A")) {
                        sender.sendMessage("This Warppoint is already existing.");
                        return false;

                    } else if(dupResult.equals("E")) {
                        sender.sendMessage("Error at checking for equal Warppoints.");
                        return false;
                    }

                } else {
                    sender.sendMessage("Error by getting your location.");
                    return false;
                }

            } else {
                sender.sendMessage("Sorry, but this command is only for Players #NoRacists");
                return false;
            }

        } else {
            sender.sendMessage("Please add some Name in your argument!");
            return false;
        }
        return false;
    }
}
