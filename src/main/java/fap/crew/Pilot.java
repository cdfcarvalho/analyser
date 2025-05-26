 /*  Name:  Popescu Adrian-Paul
 *  Number:  8240847
 *  Class:  Object Oriented Programming
*/
package src.main.java.fap.crew;

/*
 * The Pilot class represents a pilot (special crew member)
 * It inherits from CrewMember and adds a license number
 */
public class Pilot extends CrewMember {
    private String licenseNumber; // The pilot's license number

    /*
     * Constructor for Pilot to set up and ID, name and rank
     * and also sets the pilot's license number
     */
    public Pilot(int id, String name, Rank rank, String licenseNumber) {
        super(id, name, rank);
        this.licenseNumber = licenseNumber;
    }

    /* Getter for the pilot's license number */
    public String getLicenseNumber() { return licenseNumber; }
}
