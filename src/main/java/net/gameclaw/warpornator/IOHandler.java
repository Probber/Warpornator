package net.gameclaw.warpornator;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.io.*;

import java.util.logging.Level;
import java.util.regex.Pattern;


import static net.gameclaw.warpornator.Warpornator.main;

/**
 * Handles every IO related Task
 */
public class IOHandler {

    File warps;
    public static String newline = System.getProperty("line.separator");

    public IOHandler() {

    }

    /**
     * Writes a Warppoint to warps.txt
     * @param name Name of the Warppoint.
     * @param loc Location of the Warppoint.
     * @return true at success false at failure.
     */
    public boolean writeToFile(String name, Location loc) {
        try {

            String world = loc.getWorld().getName();
            Double x = loc.getX();
            Double y = loc.getY();
            Double z = loc.getZ();
            Float pitch = loc.getPitch();
            Float yaw = loc.getYaw();

            PrintWriter pw = new PrintWriter(new FileWriter(warps, true));

                pw.append(name + ";" + world + ";" + x + ";" + y + ";" + z + ";" + pitch + ";" + yaw + newline);
                pw.close();
                return true;

        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Takes the warps from file and put them into WarpList.wpList
     * @return true at success false at failure.
     */
    public boolean fillMap() {
        try {

            BufferedReader br = new BufferedReader(new FileReader(warps));

            String aLine;

            while ((aLine = br.readLine()) != null) {

                if (aLine.contains(";")) {

                    String[] locationSplit = aLine.split(Pattern.quote(";"));

                    if (locationSplit.length == 7) {

                        String warpName = locationSplit[0].replace(";", "");
                        String worldString = locationSplit[1].replace(";", "");
                        World world = Bukkit.getWorld(worldString);
                        Double x = (Double.parseDouble(locationSplit[2].replace("|", "")));
                        Double y = (Double.parseDouble(locationSplit[3].replace("|", "")));
                        Double z = (Double.parseDouble(locationSplit[4].replace("|", "")));
                        Float pitch = (Float.parseFloat(locationSplit[5].replace("|", "")));
                        Float yaw = (Float.parseFloat(locationSplit[6].replace("|", "")));

                        Location newLoc = new Location(world, x, y, z, yaw, pitch);

                        Boolean result = WarpList.addWarppoint(warpName, newLoc);
                    }
                }
            }
            br.close();
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Checks for warps.txt if its not there the method will create it.
     * @return true at success false at failure.
     */
    public boolean checkForFile() {
        try {
            if (!main.getDataFolder().exists()) {
                main.getDataFolder().mkdirs();
            }
            warps = new File(main.getDataFolder(), "warps.txt");

            if (!warps.exists()) {
                warps.createNewFile();
            }
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Removes a line from warps.txt.
     * @param name Name of the Warppoint.
     * @return true at success false at failure.
     */
    public boolean removeWarp(String name) {
        try {

            File temp = new File(main.getDataFolder(), "temp.txt");
            File warpFile = new File(main.getDataFolder(), "warps.txt");

            if (!temp.exists()) {
                temp.createNewFile();
            }

            BufferedReader br = new BufferedReader(new FileReader(warpFile));
            PrintWriter pw = new PrintWriter(new FileWriter(temp));

            String aLine;

            while ((aLine = br.readLine()) != null) {

                if (aLine.contains(";")) {

                    String[] locationSplit = aLine.split(Pattern.quote(";"));

                    if (locationSplit.length == 7) {

                        String warpName = locationSplit[0].replace(";", "");

                        if (!name.equals(warpName)) {
                            pw.println(aLine);
                        }
                    }
                }
            }
            pw.close();
            br.close();

            if (!warpFile.delete()) {
                main.getLogger().log(Level.WARNING, "Deleting warps.txt failed!");
                return false;
            }
            if (!temp.renameTo(warpFile)) {
                main.getLogger().log(Level.WARNING, "Rename temp.txt to warps.txt failed!");
                return false;
            }

            return true;

        } catch (Exception e) {
            main.getLogger().log(Level.WARNING, "Exception at removeWarp!");
            return false;
        }
    }
}