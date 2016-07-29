package net.gameclaw.warpornator;

import org.bukkit.Location;

import java.util.HashMap;

/**
 * Represents a Warplist
 */
public class WarpList {

    /**
     * Adds a Warppoint to the wpList
     * @param name The Name of the Warppoint
     * @param loc The Location of the Warppoint
     * @return True if it worked False at failure.
     */
    public static boolean addWarppoint(String name, Location loc){

        if(name.length() != 0){

            if(loc.length() != 0){
                Warpornator.wpList.put(name, loc);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Gets a Location
     * @param name Name of the Warppoint.
     * @return The Location or null by failure.
     */
    public static Location getLocation(String name){

        if (name.length() != 0) {

            for(String aName : Warpornator.wpList.keySet()){

                if (aName.equals(name)) {
                    return Warpornator.wpList.get(aName);
                }
            }
            return null;
        } else {
            return null;
        }
    }

    /**
     * Deletes a Location.
     * @param name The name of the Warppoint you want to delete.
     * @return True at a success and false at a fail
     */
    public static boolean deleteWarppoint(String name){

        if (name.length() != 0) {

            for(String aName : Warpornator.wpList.keySet()){
                if (aName.equals(name)) {
                    Warpornator.wpList.remove(aName);
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }

    }
}
