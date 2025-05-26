 /*  Name:  Popescu Adrian-Paul
 *  Number:  8240847
 *  Class:  Object Oriented Programming
*/
package src.main.java.fap.crew;

import java.io.Serializable;

/*
 * The CrewMember class represents a member of an aircraft's crew
 * Implements Serializable so it can be saved to a file 
 */
public class CrewMember implements Serializable {
    
    /* Rank is an enumeration that lists the possible military ranks */
    public enum Rank { LIEUTENANT, CAPTAIN, MAJOR, COLONEL, GENERAL }

    protected int id;
    protected String name;
    protected Rank rank;

    /*
     * Constructor for CrewMember that sets up a new crew member
     * with their ID, name and rank
     */
    public CrewMember(int id, String name, Rank rank) {
        this.id = id;
        this.name = name;
        this.rank = rank;
    }
    /* Getters for the crew member's ID, name and rank */
    public int getId() { return id; }
    public String getName() { return name; }
    public Rank getRank() { return rank; }
}
