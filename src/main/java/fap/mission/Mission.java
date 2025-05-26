 /*  Name:  Popescu Adrian-Paul
 *  Number:  8240847
 *  Class:  Object Oriented Programming
*/
package src.main.java.fap.mission;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/*
 * The Mission class represents a single mission flown by an aircraft
 * It stores all the important details about the mission
 * It implements Serializable so it can be saved to a file
 */
public class Mission implements Serializable {
    private LocalDate startDate;
    private LocalDate endDate;
    private String purpose;
    private MissionType type;
    private LocalDate authorizationDate;
    private String authorizingOfficerName; // Name of the person who authorized
    private String authorizingOfficerRank; // Rank of the person who authorized
    private String context; // Context: "NATIONAL", "NATO", "UN"
    //private List<Integer> crewMemberIds = new ArrayList<>(); // The IDs of the crew members in the mission
    String[] crewMemberIds;


    /*
     * Constructor for mission that sets up all its details and the list of crew member IDs
     */
    public Mission(LocalDate startDate, LocalDate endDate, String purpose, MissionType type,
                   LocalDate authorizationDate, String authorizingOfficerName,
                   String authorizingOfficerRank, String context, String[] crewMemberIds) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.purpose = purpose;
        this.type = type;
        this.authorizationDate = authorizationDate;
        this.authorizingOfficerName = authorizingOfficerName;
        this.authorizingOfficerRank = authorizingOfficerRank;
        this.context = context;
        this.crewMemberIds = crewMemberIds;
    }

    /* Getter for the list of crew member IDs who took part in the mission */
    public String[] getCrewMemberIds(){
        return crewMemberIds;
    }
    

    /* Getters for mission start, end, type, context, authorizing officer name and rank */
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public String getPurpose() { return purpose; }
    public MissionType getType() { return type; }
    public String getContext() { return context; }
    public String getAuthorizingOfficerName() { return authorizingOfficerName; }
    public String getAuthorizingOfficerRank() { return authorizingOfficerRank; }
}