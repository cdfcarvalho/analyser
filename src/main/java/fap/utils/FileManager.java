package src.main.java.fap.utils;


import src.main.java.fap.aircraft.Aircraft;
import java.io.*;


/*
 * Utility class for saving and loading aircraft data to and from files
 * Uses Java serilization to persist data between program runs
 */
public class FileManager {

    /*
     * Saves an array of Aircraft objects to a file
     * Creates the "data" directory if it doesn't exist
     * The parameters are the array of Aircraft to save and the filename is the path of the file to save to
     */
    public static void saveAircraft(Aircraft[] aircraft, String filename) {
        try {
            new File("data").mkdirs(); // Create directory if missing
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
                out.writeObject(aircraft);
            }
        } catch (IOException e) {
            System.out.println("❌ Error saving data: " + e.getMessage());
        }
    }


    /*
     * Loads an array of Aircraft objects from a file
     * The parameters filename is the path of the file to load from
     * It returns the loaded Aircraft array, or an empty array if loading fails
     */
    public static Aircraft[] loadAircraft(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (Aircraft[]) in.readObject();
        } catch (FileNotFoundException e) {
            return new Aircraft[0]; // Return empty array if file dosen't exist
        } catch (Exception e) {
            System.out.println("❌ Error loading data: " + e.getMessage());
            return new Aircraft[0];
        }
    }
}