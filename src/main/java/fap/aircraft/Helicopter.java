 /*  Name:  Popescu Adrian-Paul
 *  Number:  8240847
 *  Class:  Object Oriented Programming
*/
package src.main.java.fap.aircraft;

import java.time.LocalDate;

/* 
 * The Helicopter class represents a helicopter aircraft
 * It extends and inherits from the Aircraft class
 */
public class Helicopter extends Aircraft {

    /* HelicopterType is an enumeration that lists the possible types
     * of helicopter and its missions
     */
    public enum HelicopterType { SEARCH_RESCUE, COMBAT_SUPPORT, TACTICAL_TRANSPORT }

    private HelicopterType type; // Tje specific type of the helicopter

    /*
     * Constructor for Helicopter that sets up all the basic info and the
     * specific HelicopterType
     */
    public Helicopter(String id, String manufacturer, LocalDate incorporationDate, double wingspan, double weight, double maxSpeed, HelicopterType type) {
        super(id, manufacturer, incorporationDate, wingspan, weight, maxSpeed);
        this.type = type;
    }

    /* Getter for the helicopter's type (e.g. SEARCH_RESCUE) */
    public HelicopterType getType() { return type; }

    /* 
     * Returns the string "Helicopter" to identify this as a helicopter aircraft
     * This overrides the abstract method from Aircraft
     */
    @Override
    public String getAircraftType() {
        return "Helicopter";
    }
}
