 /*  Name:  Popescu Adrian-Paul
 *  Number:  8240847
 *  Class:  Object Oriented Programming
*/
package src.main.java.fap.aircraft;

import java.time.LocalDate;

/*
 * The Fighter class represents the fighter aircraft
 * It extends and inherits from the Aircraft class
 */

public class Fighter extends Aircraft {

/*
 * FighterType is an enumeration that lists the possible kinds of 
 * fighter aircraft and its missions
 */
    public enum FighterType { AIR_SUPERIORITY, FIGHTER_BOMBER, MULTIROLE }
    
    private FighterType type; // The specific type of this fighter

    /* 
     * Constructor for Fighter
     * Sets up all the basic aircraft info and the specific FigtherType
     */
    public Fighter(String id, String manufacturer, LocalDate incorporationDate, 
                  double wingspan, double weight, double maxSpeed, FighterType type) {
        super(id, manufacturer, incorporationDate, wingspan, weight, maxSpeed);
        this.type = type;
    }

    /* Getter for the fighter's type (e.g.. AIR_SUPERIORITY) */
    public FighterType getType() { return type; }

    /* Returns the string "Fighter" to identify this as a fighter aircraft
     * This overrides the abstract method from Aircraft
     */
    @Override
    public String getAircraftType() {
        return "Fighter";
    }
}


