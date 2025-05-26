 /*  Name:  Popescu Adrian-Paul
 *  Number:  8240847
 *  Class:  Object Oriented Programming
*/
package src.main.java.fap.aircraft;

import java.time.LocalDate;


/*
 * The Transport class represents a transport aircraft
 * It extends and inherits from the Aircraft class
 */
public class Transport extends Aircraft {

    /*
     * TransporterType is an enueration that lists the possible
     * types of transport aircraft and its missions
     */
    public enum TransportType { TACTICAL, STRATEGIC, REFUELING }

    private TransportType type; // The specific type of the transport aircraft

    /* 
     * Constructor for Transport that sets up the baisc info and sets the
     * specific TransportType
     */
    public Transport(String id, String manufacturer, LocalDate incorporationDate, double wingspan, double weight, double maxSpeed, TransportType type) {
        super(id, manufacturer, incorporationDate, wingspan, weight, maxSpeed);
        this.type = type;
    }

    /* Getter for the transport's type (e.g. TACTICAL) */
    public TransportType getType() { return type; }

    /* Returns the string "Transport to identify this as a transport aircraft
     * This overrides the abstract method from Aircraft
    */
    @Override
    public String getAircraftType() {
        return "Transport";
    }
}
