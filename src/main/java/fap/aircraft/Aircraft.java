 /*  Name:  Popescu Adrian-Paul
 *  Number:  8240847
 *  Class:  Object Oriented Programming
*/
package src.main.java.fap.aircraft;

import src.main.java.fap.crew.Pilot;
import src.main.java.fap.crew.CrewMember;
import src.main.java.fap.mission.Mission;
import src.main.java.fap.utils.NotEnoughSpaceException;

import java.io.Serializable;
import java.time.LocalDate;

/*
 * Abstract class that is meant to be extended by the subclasses like 
 * Fighter, Transport and Helicopter
 */


public abstract class Aircraft implements Serializable {
    /* The data stored in every aircraft */
    private String id;
    private String manufacturer;
    private LocalDate incorporationDate;
    private LocalDate lastInspectionDate;
    private LocalDate nextInspectionDate;
    private LocalDate lastMaintenanceDate;
    private LocalDate nextMaintenanceDate;
    private double wingspan;
    private double weight;
    private double maxSpeed;
    private Mission[] missions = new Mission[100]; // Array for missions (max 100)
    private int missionCount = 0;
    private CrewMember[] crew = new CrewMember[10]; // Array for crew members (max 10)
    private int crewCount = 0;
    private Pilot currentPilot; // The currently assigned pilot to the aircraft


/*  Constructor to set up a new Aircraft with its main properties */

    public Aircraft(String id, String manufacturer, LocalDate incorporationDate,
                   double wingspan, double weight, double maxSpeed) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.incorporationDate = incorporationDate;
        this.wingspan = wingspan;
        this.weight = weight;
        this.maxSpeed = maxSpeed;
    }

    // === Getters/Setters ===
    /* Returns and sets the wingspan of the aircraft */
    public double getWingspan() { return wingspan; } 
    public void setWingspan(double wingspan) { this.wingspan = wingspan; 
    }
    /* Returns and sets the weight of the aircraft */
    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }

    /* Returns and sets the max speed of the aircraft */
    public double getMaxSpeed() { return maxSpeed; }
    public void setMaxSpeed(double maxSpeed) { this.maxSpeed = maxSpeed; }

    /* Returns all missions for an aircraft as an array */
    public Mission[] getMissions() { 
        Mission[] result = new Mission[missionCount];
        System.arraycopy(missions, 0, result, 0, missionCount);
        return result;
    }
   
    /**
     * 
     * @param mission
     */
    public void addMission(Mission mission) throws NullPointerException, NotEnoughSpaceException {
        if (mission == null){
            throw new NullPointerException();
        }

        if (missionCount < missions.length) {
        missions[missionCount++] = mission;
        }
        else {
            throw new NotEnoughSpaceException();
        }
    
        
    
        

        
    }


    /* Sets the missions array and the count so it can be used for updating
     * and deleting missions
     */
    public void setMissions(Mission[] missions, int count) {
        this.missions = missions;
        this.missionCount = count;
    }
    
    /* Returns the number of how many missions the aircraft has */
    public int getMissionCount() { return missionCount; }
    
    /* Returns all crew members for this aircraft as an array */
    public CrewMember[] getCrew() {
        CrewMember[] result = new CrewMember[crewCount];
        System.arraycopy(crew, 0, result, 0, crewCount);
        return result;
    }
    

    /* Function that makes it possible to add crew members to an aircraft */
    public void addCrewMember(CrewMember member) {
        if (crewCount < crew.length) {
            crew[crewCount++] = member;
        }
    }


    /* Sets the crew array and count used for updating and deleting crew */
    public void setCrew(CrewMember[] crew, int count) {
        this.crew = crew;
        this.crewCount = count;
    }

    /* Sets the current pilot for the aircraft and adds them to the crew */
    public void setCurrentPilot(Pilot p) {
        this.currentPilot = p;
        addCrewMember(p);
    }

    /* Sets the last and next maintenance dates for the aircraft */
    public void setMaintenanceDates(LocalDate last, LocalDate next) {
        this.lastMaintenanceDate = last;
        this.nextMaintenanceDate = next;
    }

    /* Returns the aircraft's ID */
    public String getId() { return id; }
    /* Returns the manufacturer name */
    public String getManufacturer() { return manufacturer; }
    /* Returns the date when the aircraft was added to the fleet */
    public LocalDate getIncorporationDate() { return incorporationDate; }
    /* Returns the next scheduled inspection date */
    public LocalDate getNextInspectionDate() { return nextInspectionDate; }
    /* Sets the last and next inspection dates for the aircraft */
    public void setInspectionDates(LocalDate last, LocalDate next) {
        this.lastInspectionDate = last;
        this.nextInspectionDate = next;
    }

    /* Used to return the aircraft type as a String */
    public abstract String getAircraftType();
}