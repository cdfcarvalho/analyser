==========================================================
Portuguese Air Force (FAP) Aircraft Management Application
==========================================================

Student: Popescu Adrian-Paul 
Number: 8240847
Class: Object Oriented Programming

----------------------------------
1. Project Overview & Requirements
----------------------------------

This Java application is designed to manage the fleet, missions, and crew of the Portuguese Air Force (FAP) using object-oriented programming principles. It allows users to:

- Register and manage different types of aircraft (Fighter, Transport, Helicopter)
- Manage crew and pilots for each aircraft
- Record and view missions, including mission crew and context
- Query and generate reports on aircraft, missions, and crew
- Save and load all data persistently between runs

The application is entirely menu-driven and runs in the console.

-------------------------
2. Imports and Libraries
-------------------------

This program uses only standard Java SE libraries:

- java.util.List, java.util.ArrayList, java.util.Scanner
- java.io.Serializable, java.io.File, java.io.FileInputStream, java.io.FileOutputStream, java.io.ObjectInputStream, java.io.ObjectOutputStream
- java.time.LocalDate

No external libraries or frameworks are required.

--------------------
3. Project Structure
--------------------

src/
 └── main/
      └── java/
           ├── fap/
           │    ├── InteractiveApp.java      <-- Main interactive menu program
           │    ├── utils/FileManager.java   <-- Handles saving/loading data
           │    ├── aircraft/                <-- Aircraft classes
           │    ├── crew/                    <-- CrewMember and Pilot classes
           │    └── mission/                 <-- Mission and MissionType classes
           └── data/
                └── aircraft.dat             <-- Serialized data file (created at runtime)

------------------------
4. Logic and Main Design
------------------------

- **Aircraft**: Abstract class with subclasses for Fighter, Transport, and Helicopter. Each aircraft stores its own missions and crew.
- **CrewMember/Pilot**: Each crew member has an ID, name, and rank. A pilot is a special type of crew member.
- **Mission**: Stores mission details, including a list of crew member IDs who participated.
- **Persistence**: All data is saved to and loaded from a binary file (`data/aircraft.dat`) using Java serialization.
- **Menu System**: The console interface allows users to add/update/delete aircraft, crew, and missions, and to generate various reports.
- **Data Utilities**: You can clear all data and start fresh using the Data Utilities menu option.

------------------------
5. How to Use the Program
------------------------

**A. Running the Program**
- Open the project folder in VS Code.
- Open `InteractiveApp.java`.
- Click the green "Run" button or use the terminal:  
  `java fap.InteractiveApp`

**B. Menu Navigation**
- The main menu displays numbered options.
- Enter the number for the action you want (e.g., "1" for Manage Aircraft).
- Follow the prompts to input IDs, names, dates, and select options from lists.
- All lists (aircraft, crew, missions) are shown before you are asked to choose an ID or make a selection.

**C. Adding Data**
- To add an aircraft, select "Manage Aircraft" > "Add new aircraft".
- To add crew, select "Manage Crew" > "Add crew member" (you must select an aircraft first).
- To add a mission, select "Manage Missions" > "Create new mission" (you must select an aircraft and at least two crew members).

**D. Deleting and Updating**
- To delete or update, use the corresponding menu options. You will be shown valid IDs/names to choose from.

**E. Data Persistence**
- All changes are saved automatically when you exit the program or use the "Data Utilities" menu.
- To clear all data and start fresh, use "Data Utilities" > "Clear ALL data".

**F. Reports**
- Use "Generate Reports" to query:
    - Aircraft by registration year
    - Missions by aircraft
    - Average missions by type
    - Missions by context (NATIONAL, NATO, UN)
    - Aircraft nearing end of lifespan
    - Missions involving a specific crew member
    - Aircraft with a minimum number of missions in a year

--------------------------
6. Notes and Limitations
--------------------------

- The data file (`aircraft.dat`) is not human-readable; it uses Java serialization.
- The application must be run from the console or a Java IDE (like VS Code with the Java Extension Pack).


** What I haven't implemented succsesfully:

- The lifespan function is not implemented yet
- The captian is not required to be in the mission, though it should be 
- A better prompt to be able to see only the crew members or only the pilots (now they can be seen when making a query for the aircraft or mission)


