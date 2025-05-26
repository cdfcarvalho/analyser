 /*  Name:  Popescu Adrian-Paul
 *  Number:  8240847
 *  Class:  Object Oriented Programming
*/
package src.main.java.fap;
// Replace ArrayList with a normal array




import src.main.java.fap.aircraft.*;
import src.main.java.fap.crew.*;
import src.main.java.fap.mission.*;
import src.main.java.fap.utils.FileManager;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class InteractiveApp {

    // ==== Fields and Main Entry Point ====
    private static Aircraft[] aircraftList;
    private static Scanner scanner = new Scanner(System.in);

    /*
     * Main method: This is where the program starts
     * Loads saved aircraft data, shows the menu and handles user choices
     */
    public static void main(String[] args) {
        aircraftList = FileManager.loadAircraft("data/aircraft.dat");
        boolean running = true;

        while (running) {
            printMainMenu();
            int choice = safeParseInt(scanner.nextLine(), 0);

            switch (choice) {
                case 1 -> manageAircraft();
                case 2 -> manageMissions();
                case 3 -> manageCrew();
                case 4 -> generateReports();
                case 5 -> manageDataUtilities();
                case 6 -> running = false;
                default -> System.out.println("Invalid option");
            }
        }
        FileManager.saveAircraft(aircraftList, "data/aircraft.dat");
        System.out.println("Data saved. Exiting.");
    }

    // === Utility: Safe Integer Parsing ===
    /* 
     * Tries to convert a string to an integer and returns defaultValue if it fails
     */
    private static int safeParseInt(String input, int defaultValue) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    // === Main Menu ===
    /*
     * Prints the main menu options for the user
     */
    private static void printMainMenu() {
        System.out.println("\n=== FAP Mission Control ===");
        System.out.println("1. Manage Aircraft");
        System.out.println("2. Manage Missions");
        System.out.println("3. Manage Crew");
        System.out.println("4. Generate Reports");
        System.out.println("5. Data Utilities");
        System.out.println("6. Exit and Save");
        System.out.print("Choose [1-6]: ");
    }

    // === Aircraft Management ===
    /* 
     * Handles the sub-menu for managin aircraft (list, add, update, delete)
     */
    private static void manageAircraft() {
        System.out.println("\n=== Aircraft Management ===");
        System.out.println("1. List all aircraft");
        System.out.println("2. Add new aircraft");
        System.out.println("3. Update aircraft");
        System.out.println("4. Delete aircraft");
        System.out.print("Choose: ");

        switch (safeParseInt(scanner.nextLine(), 0)) {
            case 1 -> listAllAircraft(true);
            case 2 -> addNewAircraft();
            case 3 -> updateAircraft();
            case 4 -> deleteAircraft();
            default -> System.out.println("Invalid option");
        }
    }

    /*
     * Lists all aircraft in the system. If detailed is true, shows all info
     */
    private static void listAllAircraft(boolean detailed) {
        System.out.println("\nRegistered Aircraft:");
        for (Aircraft a : aircraftList) {
            if (detailed) {
                System.out.printf("- %s (%s)%n  Manufacturer: %s%n  Incorporated: %s%n  Next Inspection: %s%n",
                        a.getId(), a.getAircraftType(), a.getManufacturer(),
                        a.getIncorporationDate(), a.getNextInspectionDate());
            } else {
                System.out.println("- " + a.getId() + " (" + a.getAircraftType() + ")");
            }
        }
    }

    /* 
     * Adds a new aircraft (Fighter, Transport, or Helicopter) by prompting
     * the user for all required info
    */
    private static void addNewAircraft() {
        System.out.println("\n=== New Aircraft ===");
        try {
            System.out.print("Aircraft Type (1-Fighter, 2-Transport, 3-Helicopter): ");
            int typeChoice = safeParseInt(scanner.nextLine(), 1);

            System.out.print("ID: ");
            String id = scanner.nextLine();

            System.out.print("Manufacturer: ");
            String manufacturer = scanner.nextLine();

            System.out.print("Incorporation Date (YYYY-MM-DD): ");
            LocalDate incorpDate = LocalDate.parse(scanner.nextLine());

            System.out.print("Wingspan: ");
            double wingspan = Double.parseDouble(scanner.nextLine());

            System.out.print("Weight: ");
            double weight = Double.parseDouble(scanner.nextLine());

            System.out.print("Max Speed: ");
            double maxSpeed = Double.parseDouble(scanner.nextLine());

            Aircraft newAircraft = null;
            switch (typeChoice) {
                case 1 -> {
                    System.out.print("Fighter Type (1-Air Superiority, 2-Fighter Bomber, 3-Multirole): ");
                    int fighterType = safeParseInt(scanner.nextLine(), 1);
                    newAircraft = new Fighter(id, manufacturer, incorpDate, wingspan, weight, maxSpeed,
                            Fighter.FighterType.values()[fighterType - 1]);
                }
                case 2 -> {
                    System.out.print("Transport Type (1-Tactical, 2-Strategic, 3-Refueling): ");
                    int transportType = safeParseInt(scanner.nextLine(), 1);
                    newAircraft = new Transport(id, manufacturer, incorpDate, wingspan, weight, maxSpeed,
                            Transport.TransportType.values()[transportType - 1]);
                }
                case 3 -> {
                    System.out.print("Helicopter Type (1-Search Rescue, 2-Combat Support, 3-Tactical Transport): ");
                    int heliType = safeParseInt(scanner.nextLine(), 1);
                    newAircraft = new Helicopter(id, manufacturer, incorpDate, wingspan, weight, maxSpeed,
                            Helicopter.HelicopterType.values()[heliType - 1]);
                }
                default -> {
                    System.out.println("Invalid aircraft type!");
                    return;
                }
            }

            Aircraft[] newList = new Aircraft[aircraftList.length + 1];
            System.arraycopy(aircraftList, 0, newList, 0, aircraftList.length);
            newList[aircraftList.length] = newAircraft;
            aircraftList = newList;

            System.out.println("Aircraft added successfully!");
        } catch (Exception e) {
            System.out.println("Error creating aircraft: " + e.getMessage());
        }
    }

    /*
     * Updates the wingspan, weight, and max speed of an existing aircraft
     */
    private static void updateAircraft() {
        System.out.print("\nEnter aircraft ID to update: ");
        Aircraft aircraft = findAircraftById(scanner.nextLine());
        if (aircraft == null) {
            System.out.println("Aircraft not found!");
            return;
        }

        try {
            System.out.print("New wingspan (current: " + aircraft.getWingspan() + "): ");
            aircraft.setWingspan(Double.parseDouble(scanner.nextLine()));

            System.out.print("New weight (current: " + aircraft.getWeight() + "): ");
            aircraft.setWeight(Double.parseDouble(scanner.nextLine()));

            System.out.print("New max speed (current: " + aircraft.getMaxSpeed() + "): ");
            aircraft.setMaxSpeed(Double.parseDouble(scanner.nextLine()));

            System.out.println("Aircraft updated successfully!");
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format!");
        }
    }

    /*
     * Delets an aircraft from the system by its ID
     */
    private static void deleteAircraft() {
        System.out.print("\nEnter aircraft ID to delete: ");
        String id = scanner.nextLine();

        int index = -1;
        for (int i = 0; i < aircraftList.length; i++) {
            if (aircraftList[i].getId().equalsIgnoreCase(id)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println("Aircraft not found!");
            return;
        }

        Aircraft[] newList = new Aircraft[aircraftList.length - 1];
        System.arraycopy(aircraftList, 0, newList, 0, index);
        System.arraycopy(aircraftList, index + 1, newList, index, aircraftList.length - index - 1);
        aircraftList = newList;
        System.out.println("Aircraft deleted successfully!");
    }

    // === Mission Management ===
    /*
     * Handles the sub-menu for managin missions (create, delete)
     */
    private static void manageMissions() {
        System.out.println("\n=== Mission Management ===");
        System.out.println("1. Create new mission");
        System.out.println("2. Delete mission");
        System.out.print("Choose: ");

        switch (safeParseInt(scanner.nextLine(), 0)) {
            case 1 -> addNewMission();
            case 2 -> deleteMission();
            default -> System.out.println("Invalid option");
        }
    }

    /*
     * Adds a new mission to a selected aircraft, requiring at least 2 crew members
     */
    private static void addNewMission() {
        System.out.println("\n=== Create New Mission ===");
        listAllAircraft(false);
        System.out.print("\nEnter Aircraft ID: ");
        Aircraft aircraft = findAircraftById(scanner.nextLine());
        
        if (aircraft == null) {
            System.out.println("Invalid aircraft ID!");
            return;
        }

        try {
            System.out.print("\nMission start date (YYYY-MM-DD): ");
            LocalDate start = LocalDate.parse(scanner.nextLine());
            
            System.out.print("Mission end date (YYYY-MM-DD): ");
            LocalDate end = LocalDate.parse(scanner.nextLine());
            
            System.out.print("Mission purpose description: ");
            String purpose = scanner.nextLine();
            
            System.out.println("\nMission Types (Choose Number):");
            for (int i = 0; i < MissionType.values().length; i++) {
                System.out.printf("%d. %s%n", i+1, MissionType.values()[i]);
            }
            System.out.print("Select [1-" + MissionType.values().length + "]: ");
            int typeChoice = safeParseInt(scanner.nextLine(), 1) - 1;
            MissionType type = MissionType.values()[typeChoice];
            
            System.out.print("\nAuthorization date (YYYY-MM-DD): ");
            LocalDate authDate = LocalDate.parse(scanner.nextLine());
            
            System.out.print("Authorizing officer's full name: ");
            String officerName = scanner.nextLine();
            
            System.out.print("Authorizing officer's rank: ");
            String officerRank = scanner.nextLine();
            
            System.out.print("Context [NATIONAL/NATO/UN]: ");
            String context = scanner.nextLine().toUpperCase();
            
            


                // 1. the count 
                // 2. 
            System.out.println("\nSelect Crew Members (minimum 2):"); // Crew selection for the mission
            
            int count = 0;
            


            while (count < 2) {
                System.out.println("Available Crew:");
                for (CrewMember cm : aircraft.getCrew()) {
                    System.out.printf("%d: %s (%s)%n", cm.getId(), cm.getName(), cm.getRank());
                }
                System.out.print("Enter crew member ID (0 to finish): ");
                int crewId = safeParseInt(scanner.nextLine(), -1);
                
                if (crewId == 0 && crewIds.size() >= 2) break;
                if (crewId == 0) {
                    System.out.println("You need at least 2 crew members!");
                    continue;
                }
                
                if (findCrewMember(aircraft, crewId) != null) {
                    crewIds.add(crewId);
                    System.out.println("Added crew member: " + crewId);
                } else {
                    System.out.println("Invalid crew ID!");
                }
            }
            System.out.println("\nSelect the Pilot:");
            boolean hasPilot = false;
            for (int crewId : crewIds){
                CrewMember cm = findCrewMember(aircraft, crewId);
                if (cm instanceof Pilot) {
                    hasPilot = true;
                    break;
                }
            }
            if (!hasPilot) {
                System.out.println("At least 1 Capitan needs to be chosen");
                return;
            }
            Mission mission = new Mission(
                start, end, purpose, type,
                authDate, officerName, officerRank, context, 
            );
            
            aircraft.addMission(mission);
            System.out.println("\n✅ Mission added successfully!");
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    /*
     * Deletes a mission from a selected aircraft
     */
    private static void deleteMission() {
        System.out.println("\nAvailable Aircraft:");
        listAllAircraft(false);
        System.out.print("\nEnter Aircraft ID: ");
        Aircraft aircraft = findAircraftById(scanner.nextLine());
        if (aircraft == null) {
            System.out.println("Aircraft not found!");
            return;
        }

        Mission[] missions = aircraft.getMissions();
        if (missions.length == 0) {
            System.out.println("No missions found for this aircraft.");
            return;
        }

        System.out.println("\nMissions:");
        for (int i = 0; i < missions.length; i++) {
            System.out.printf("%d. %s [%s to %s]%n", 
                i+1, missions[i].getPurpose(), 
                missions[i].getStartDate(), missions[i].getEndDate()
            );
        }
        System.out.print("Select mission to delete [1-" + missions.length + "]: ");
        int choice = safeParseInt(scanner.nextLine(), 1) - 1;

        if (choice < 0 || choice >= missions.length) {
            System.out.println("Invalid selection!");
            return;
        }

        Mission[] newMissions = new Mission[missions.length - 1];
        System.arraycopy(missions, 0, newMissions, 0, choice);
        System.arraycopy(missions, choice + 1, newMissions, choice, missions.length - choice - 1);

        aircraft.setMissions(newMissions, newMissions.length);
        System.out.println("Mission deleted successfully!");
    }

    // === Crew Management ===
    /*
     * Handles the sub-menu for managin crew (add, update, delete)
     */
    private static void manageCrew() {
        System.out.println("\n=== Crew Management ===");
        System.out.println("1. Add crew member");
        System.out.println("2. Update crew member");
        System.out.println("3. Delete crew member");
        System.out.print("Choose: ");

        switch (safeParseInt(scanner.nextLine(), 0)) {
            case 1 -> addCrewMember();
            case 2 -> updateCrewMember();
            case 3 -> deleteCrewMember();
            default -> System.out.println("Invalid option");
        }
    }

    /*
     * Adds a new crew memeber (or pilot) to a selected aircraft
     */
    private static void addCrewMember() {
        System.out.println("\nAvailable Aircraft:");
        listAllAircraft(false);
        System.out.print("\nEnter Aircraft ID: ");
        Aircraft aircraft = findAircraftById(scanner.nextLine());
        if (aircraft == null) {
            System.out.println("Invalid Aircraft ID!");
            return;
        }

        try {
            System.out.print("\nNew Crew Member ID (number): ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("Full Name: ");
            String name = scanner.nextLine();

            System.out.println("Available Ranks:");
            for (int i = 0; i < CrewMember.Rank.values().length; i++) {
                System.out.printf("%d. %s%n", i+1, CrewMember.Rank.values()[i]);
            }
            System.out.print("Select Rank [1-" + CrewMember.Rank.values().length + "]: ");
            int rankChoice = safeParseInt(scanner.nextLine(), 1) - 1;
            CrewMember.Rank rank = CrewMember.Rank.values()[rankChoice];

            CrewMember member;
            if (rank == CrewMember.Rank.CAPTAIN) {
                System.out.print("Pilot License Number: ");
                String license = scanner.nextLine();
                member = new Pilot(id, name, rank, license);
            } else {
                member = new CrewMember(id, name, rank);
            }

            aircraft.addCrewMember(member);
            System.out.println("\n✅ Crew member added!");
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    /*
     * Updates the name of a crew member for a selected aircraft
     */
    private static void updateCrewMember() {
        System.out.println("\nAvailable Aircraft:");
        listAllAircraft(false);
        System.out.print("\nEnter Aircraft ID: ");
        Aircraft aircraft = findAircraftById(scanner.nextLine());
        if (aircraft == null) {
            System.out.println("Aircraft not found!");
            return;
        }

        System.out.println("Crew Members:");
        for (CrewMember cm : aircraft.getCrew()) {
            System.out.printf("%d: %s (%s)%n", cm.getId(), cm.getName(), cm.getRank());
        }
        System.out.print("Enter crew member ID: ");
        int id = safeParseInt(scanner.nextLine(), -1);
        CrewMember member = findCrewMember(aircraft, id);
        if (member == null) {
            System.out.println("Crew member not found!");
            return;
        }

        System.out.print("New name: ");
        String newName = scanner.nextLine();

        CrewMember[] crew = aircraft.getCrew();
        for (int i = 0; i < crew.length; i++) {
            if (crew[i].getId() == id) {
                crew[i] = new CrewMember(id, newName, member.getRank());
                break;
            }
        }
        aircraft.setCrew(crew, crew.length);
        System.out.println("Crew member updated!");
    }

    /*
     * Delets a crew member from a selected aircraft
     */
    private static void deleteCrewMember() {
        System.out.println("\nAvailable Aircraft:");
        listAllAircraft(false);
        System.out.print("\nEnter Aircraft ID: ");
        Aircraft aircraft = findAircraftById(scanner.nextLine());
        if (aircraft == null) {
            System.out.println("Aircraft not found!");
            return;
        }

        System.out.println("Crew Members:");
        for (CrewMember cm : aircraft.getCrew()) {
            System.out.printf("%d: %s (%s)%n", cm.getId(), cm.getName(), cm.getRank());
        }
        System.out.print("Enter crew member ID: ");
        int id = safeParseInt(scanner.nextLine(), -1);

        CrewMember[] crew = aircraft.getCrew();
        CrewMember[] newCrew = new CrewMember[crew.length - 1];
        int idx = 0;
        for (CrewMember cm : crew) {
            if (cm.getId() != id) {
                newCrew[idx++] = cm;
            }
        }
        aircraft.setCrew(newCrew, newCrew.length);
        System.out.println("Crew member deleted!");
    }

    // === Data Utilities ===
    /*
     * Allows the user to clear up all data (reset the system)
     */
    private static void manageDataUtilities() {
        System.out.println("\n=== Data Utilities ===");
        System.out.println("1. Clear ALL data");
        System.out.println("2. Return to main menu");
        System.out.print("Choose: ");
        
        if (safeParseInt(scanner.nextLine(), 0) == 1) {
            aircraftList = new Aircraft[0];
            FileManager.saveAircraft(aircraftList, "data/aircraft.dat");
            System.out.println("✅ All data cleared!");
        }
    }

    // === Reports ===
    private static void generateReports() {
        System.out.println("\n=== Reports ===");
        System.out.println("1. Aircraft by registration year");
        System.out.println("2. Missions by aircraft");
        System.out.println("3. Average missions by type");
        System.out.println("4. Missions in specific context");
        System.out.println("5. Aircraft lifespan report");
        System.out.println("6. Missions by crew member");
        System.out.println("7. Aircraft mission count");
        System.out.print("Choose: ");

        int choice = safeParseInt(scanner.nextLine(), 0);
        switch (choice) {
            case 1 -> queryAircraftByYear();
            case 2 -> queryMissionsByAircraft();
            case 3 -> queryAverageMissions();
            case 4 -> queryMissionsByContext();
            case 5 -> queryAircraftLifespan();
            case 6 -> queryMissionsByCrew();
            case 7 -> queryAircraftMissionCount();
            default -> System.out.println("Invalid option");
        }
    }

    /*
     * Lists all aircraft registered in a specific year
     */
    private static void queryAircraftByYear() {
        System.out.print("Enter registration year: ");
        int year = safeParseInt(scanner.nextLine(), 2020);
        
        System.out.println("\nAircraft registered in " + year + ":");
        for (Aircraft a : aircraftList) {
            if (a.getIncorporationDate().getYear() == year) {
                System.out.printf("- %s (%s)%n  Next Inspection: %s%n",
                        a.getId(), a.getAircraftType(), a.getNextInspectionDate());
            }
        }
    }

    /*
     * Lists all missions for a selected aircraft, including crew members
     */
    private static void queryMissionsByAircraft() {
        System.out.print("\nEnter aircraft ID: ");
        Aircraft aircraft = findAircraftById(scanner.nextLine());
        if (aircraft == null) {
            System.out.println("Aircraft not found!");
            return;
        }

        System.out.println("\nMissions for " + aircraft.getId() + ":");
        for (Mission m : aircraft.getMissions()) {
            System.out.printf("- %s to %s: %s%n  Crew: %s%n  Type: %s%n  Authorized by: %s %s%n",
                    m.getStartDate(), m.getEndDate(), m.getPurpose(),
                    getCrewNames(aircraft, m.getCrewMemberIds()),
                    m.getType(), m.getAuthorizingOfficerRank(), m.getAuthorizingOfficerName());
        }
    }

    /*
     * Helper function to get a comma-separated string of crew member names for a mission
     */
    private static String getCrewNames(Aircraft a, List<Integer> crewIds) {
        StringBuilder sb = new StringBuilder();
        for (int id : crewIds) {
            CrewMember cm = findCrewMember(a, id);
            if (cm != null) sb.append(cm.getName()).append(", ");
        }
        return sb.length() > 0 ? sb.substring(0, sb.length()-2) : "None";
    }

    /*
     * Shows the avarage number of missions of a certain type for each aircraft type
     */
    private static void queryAverageMissions() {
        System.out.println("Mission Types:");
        for (int i = 0; i < MissionType.values().length; i++) {
            System.out.printf("%d. %s%n", i+1, MissionType.values()[i]);
        }
        System.out.print("Mission type to average [1-" + MissionType.values().length + "]: ");
        int typeChoice = safeParseInt(scanner.nextLine(), 1) - 1;
        MissionType type = MissionType.values()[typeChoice];

        int fighterCount = 0, fighterMissions = 0;
        int transportCount = 0, transportMissions = 0;
        int heliCount = 0, heliMissions = 0;
        for (Aircraft a : aircraftList) {
            int count = 0;
            for (Mission m : a.getMissions()) {
                if (m.getType() == type) count++;
            }
            if (a instanceof Fighter) {
                fighterCount++;
                fighterMissions += count;
            } else if (a instanceof Transport) {
                transportCount++;
                transportMissions += count;
            } else if (a instanceof Helicopter) {
                heliCount++;
                heliMissions += count;
            }
        }

        System.out.println("\nAverage " + type + " missions per:");
        System.out.printf("Fighter: %.1f%n", fighterCount > 0 ? (double)fighterMissions/fighterCount : 0.0);
        System.out.printf("Transport: %.1f%n", transportCount > 0 ? (double)transportMissions/transportCount : 0.0);
        System.out.printf("Helicopter: %.1f%n", heliCount > 0 ? (double)heliMissions/heliCount : 0.0);
    }

    /*
     * Lists all missions for a selected aircraft in a specific context(NATIONAL, NATO, UN)
     */
    private static void queryMissionsByContext() {
        System.out.print("\nEnter aircraft ID: ");
        Aircraft aircraft = findAircraftById(scanner.nextLine());
        if (aircraft == null) {
            System.out.println("Aircraft not found!");
            return;
        }

        System.out.print("Context (NATIONAL/NATO/UN): ");
        String context = scanner.nextLine().toUpperCase();

        System.out.println("\nMissions in " + context + " context:");
        for (Mission m : aircraft.getMissions()) {
            if (m.getContext().equals(context)) {
                System.out.printf("- %s: %s (%s to %s)%n",
                    m.getPurpose(), m.getType(),
                    m.getStartDate(), m.getEndDate()
                );
            }
        }
    }

    /*
     * Lists all aircraft whose lifespan ends within a certain number of years
     */
    private static void queryAircraftLifespan() {
        System.out.print("Years remaining (5,10,custom): ");
        int years = safeParseInt(scanner.nextLine(), 5);

        int currentYear = LocalDate.now().getYear();
        System.out.println("\nAircraft ending lifespan within " + years + " years:");
        for (Aircraft a : aircraftList) {
            int lifespanEnd = a.getIncorporationDate().getYear() + 25;
            if (lifespanEnd - currentYear <= years) {
                System.out.printf("- %s (%s) ends in %d%n",
                        a.getId(), a.getAircraftType(), lifespanEnd);
            }
        }
    }

    /*
     * Lists all missions involving a crew member with a specific name
     */
    private static void queryMissionsByCrew() {
        System.out.print("\nEnter crew member name: ");
        String name = scanner.nextLine();

        System.out.println("\nMissions involving " + name + ":");
        for (Aircraft a : aircraftList) {
            for (CrewMember cm : a.getCrew()) {
                if (cm.getName().equalsIgnoreCase(name)) {
                    for (Mission m : a.getMissions()) {
                        System.out.printf("- %s: %s (%s)%n",
                                a.getId(), m.getPurpose(), m.getType());
                    }
                }
            }
        }
    }

    /*
     * Shows the number of aircraft that performed at least a minimum number of missions
     * in a specific year
     */
    private static void queryAircraftMissionCount() {
        System.out.print("Minimum missions: ");
        int min = safeParseInt(scanner.nextLine(), 1);

        System.out.print("Year: ");
        int year = safeParseInt(scanner.nextLine(), 2023);

        int count = 0;
        for (Aircraft a : aircraftList) {
            int missions = 0;
            for (Mission m : a.getMissions()) {
                if (m.getStartDate().getYear() == year) missions++;
            }
            if (missions >= min) count++;
        }
        System.out.println("\nAircraft with ≥" + min + " missions in " + year + ": " + count);
    }

    // === Helper Methods ===

    /*
     * Finds and returns an aircraft by its ID (case-insensitive)
     */
    private static Aircraft findAircraftById(String id) {
        for (Aircraft a : aircraftList) {
            if (a.getId().equalsIgnoreCase(id)) {
                return a;
            }
        }
        return null;
    }

    /*
     * Finds and returns a crew member by their ID fo a given aircraft
     */
    private static CrewMember findCrewMember(Aircraft a, int id) {
        for (CrewMember cm : a.getCrew()) {
            if (cm.getId() == id) {
                return cm;
            }
        }
        return null;
    }
}