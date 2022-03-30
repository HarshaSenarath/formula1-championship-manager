import java.io.Serializable;
import java.util.*;

/**
 * The Formula1ChampionshipManager class to store all the details of a formula 1 championship.
 */
public class Formula1ChampionshipManager implements ChampionshipManager, Serializable {
    private final ArrayList<Formula1Driver> drivers = new ArrayList<>();
    private final ArrayList<Race> races = new ArrayList<>();
    private int year;

    /**
     * Getter method get the list of formula 1 drivers.
     *
     * @return The formula 1 drivers list of the Formula1ChampionshipManager object.
     */
    public ArrayList<Formula1Driver> getDrivers() {
        return drivers;
    }

    /**
     * Getter method get the list of races.
     *
     * @return The races list of the Formula1ChampionshipManager object.
     */
    public ArrayList<Race> getRaces() {
        return races;
    }

    /**
     * Getter method get the year of the formula 1 championship.
     *
     * @return The year of the Formula1ChampionshipManager object.
     */
    public int getYear() {
        return year;
    }

    /**
     * Setter method to set the year for the formula 1 championship.
     *
     * @param year The season year for the Formula1ChampionshipManager object.
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Method to specify the menu of formula 1 championship manager.
     *
     * @return A String containing the menu of formula 1 championship manager.
     */
    @Override
    public String printMenu() {
        return ("_______________________________________________\n" +
                "MENU\n\n" +
                "N: Create a new driver\n" +
                "D: Delete a driver\n" +
                "C: Change the driver for a team\n" +
                "P: Display driver statistics\n" +
                "T: Display formula 1 driver table\n" +
                "R: Add a race completed\n" +
                "S: Save all the data into a file\n" +
                "O: Open GUI for Formula 1 Championship Manager\n" +
                "Q: Quit the program\n");
    }

    /**
     * Method to create a formula 1 driver in the Formula1ChampionshipManager object.
     *
     * @param name      Name of the formula 1 driver to be created.
     * @param location  Location of the formula 1 driver to be created.
     * @param team      Team of the formula 1 driver to be created.
     * @return A String containing the name of the driver created and the team driver was added.
     */
    @Override
    public String create(String name, String location, String team) {
        Formula1Driver driver = new Formula1Driver(name, location, team);
        drivers.add(driver);

        return ("\n" + name + " was successfully added to team " + team);
    }

    /**
     * Method to delete a formula 1 driver and the driver's team from the Formula1ChampionshipManager object.
     *
     * @param name Name of the formula 1 driver to be deleted.
     * @return A String containing a suitable message if the specified formula 1 driver and the driver's team was deleted successfully or
     *         A String containing a suitable message if the specified formula 1 driver does not exist in the Formula1ChampionshipManager object.
     */
    @Override
    public String delete(String name) {
        for (int i = 0; i < drivers.size(); i++) {
            if (drivers.get(i).getName().equalsIgnoreCase(name)) {

                String message = "\nDriver " + drivers.get(i).getName() + " and team " +
                        drivers.get(i).getTeam() + " was successfully deleted from Formula 1 Championship";

                drivers.remove(i);

                return (message);
            }
        }
        return ("\nThere was no driver called '" + name + "' found in Formula 1 Championship");
    }

    /**
     * Method to change a driver belongs to a specific team in the Formula1ChampionshipManager object.
     *
     * @param team      Team the formula 1 driver is being changed.
     * @param name      Name of the new formula 1 driver to be added.
     * @param location  Location of the new formula 1 driver to be added.
     * @return A String containing a suitable message if the new formula 1 driver's name is same as the previous formula 1 driver's name or
     *         A String containing a suitable message if there is a formula 1 driver already existing in another team with new formula 1 driver's name or
     *         A String containing a suitable message if the specified team's driver was changed successfully or
     *         An empty String if the location parameter contains an empty string or
     *         Null.
     */
    @Override
    public String change(String team, String name, String location) {
        for (Formula1Driver driver : drivers) {
            //Checking if the new driver's name is same as the previous driver's name in the specified team.
            if (driver.getName().equalsIgnoreCase(name) && driver.getTeam().equalsIgnoreCase(team)) {
                return ("\nNew driver's name is same as the previous driver's name" +
                        "\nIf this is not the same driver, please enter his/her middle name or surname and try again");
                //Checking if there is a formula 1 driver already existing in another team with new formula 1 driver's name.
            } else if (driver.getName().equalsIgnoreCase(name) && !driver.getTeam().equalsIgnoreCase(team)) {
                return ("\nA driver from that name already exists in a team" +
                        "\nIf this is not the same driver, please enter his/her middle name or surname and try again");
            }
        }

        if (!location.isEmpty()) {
            for (Formula1Driver driver : drivers) {
                if (driver.getTeam().equalsIgnoreCase(team)) {
                    String message = "\nDriver for team " + driver.getTeam() +
                            " successfully changed from '" + driver.getName();

                    driver.setName(name);
                    driver.setLocation(location);
                    driver.setfPositionCount(0);
                    driver.setsPositionCount(0);
                    driver.settPositionCount(0);
                    driver.setNumOfPoints(0);
                    driver.setNumOfRaces(0);

                    message += "' to '" + driver.getName() + "'";

                    return message;
                }
            }
        } else {
            return "";
        }
        return null;
    }

    /**
     * Method to display the seasonal statistics of a specific formula 1 driver in the Formula1ChampionshipManager object.
     *
     * @param name Name of the formula 1 driver that needs to be displayed the statistics of.
     * @return A String containing the seasonal statistics of the specified formula 1 driver or
     *         A String containing a suitable message if the specified formula 1 driver does not exist in the Formula1ChampionshipManager object.
     */
    @Override
    public String displayStats(String name) {
        for (Formula1Driver driver : drivers) {
            if (driver.getName().equalsIgnoreCase(name)) {

                //https://www.baeldung.com/java-pad-string
                String statistics = "\nDisplaying season statistics of driver " + driver.getName() + "\n\n" +
                        String.format("%1$" + -9 + "s", "Name ") + ": " + driver.getName() + "\n" +
                        String.format("%1$" + -9 + "s", "Location ") + ": " + driver.getLocation() + "\n" +
                        String.format("%1$" + -9 + "s", "Team ") + ": " + driver.getTeam() + "\n" +
                        String.format("%1$" + -27 + "s", "Number of first positions ") + ": " + driver.getfPositionCount() + "\n" +
                        String.format("%1$" + -27 + "s", "Number of second positions ") + ": " + driver.getsPositionCount() + "\n" +
                        String.format("%1$" + -27 + "s", "Number of third positions ") + ": " + driver.gettPositionCount() + "\n" +
                        "Number of points so far : " + driver.getNumOfPoints() + "\n" +
                        "Number of races participated : " + driver.getNumOfRaces();

                return (statistics);
            }
        }
        return ("\nThere was no driver called '" + name + "' found in Formula 1 Championship");
    }

    /**
     * Method to display the formula 1 driver's table in Formula1ChampionshipManager object.
     *
     * @return A String containing the formula 1 driver's table of drivers in the Formula1ChampionshipManager object.
     *         A String containing a suitable message if there are no formula 1 drivers existing in the Formula1ChampionshipManager object.
     */
    @Override
    public String displayTable() {
        if (drivers.isEmpty()) {
            return("There are no drivers to display in Formula 1 Driver Table");
        } else {
            Collections.sort(drivers);

            //https://www.geeksforgeeks.org/string-class-repeat-method-in-java-with-examples/#:~:text=repeat()%20method%20is%20used,the%20empty%20string%20is%20returned
            String line = "-".repeat(167);

            //https://www.baeldung.com/java-pad-string
            String table = line + "\n" +
                    String.format("%1$" + -6 + "s", "| Seq") +
                    String.format("%1$" + -20 + "s", "| Name") +
                    String.format("%1$" + -20 + "s", "| Location") +
                    String.format("%1$" + -20 + "s", "| Team") +
                    String.format("%1$" + -20 + "s", "| First Positions") +
                    String.format("%1$" + -20 + "s", "| Second Positions") +
                    String.format("%1$" + -20 + "s", "| Third Positions") +
                    String.format("%1$" + -20 + "s", "| Total Points") +
                    String.format("%1$" + -20 + "s", "| Races Participated") + "|\n" + line + "\n";

            //Variable count which represents the row number.
            int count = 1;

            for (Formula1Driver driver : drivers) {
                table += String.format("%1$" + -6 + "s", "| " + count) +
                        String.format("%1$" + -20 + "s", "| " + driver.getName()) +
                        String.format("%1$" + -20 + "s", "| " + driver.getLocation()) +
                        String.format("%1$" + -20 + "s", "| " + driver.getTeam()) +
                        String.format("%1$" + -20 + "s", "| " + driver.getfPositionCount()) +
                        String.format("%1$" + -20 + "s", "| " + driver.getsPositionCount()) +
                        String.format("%1$" + -20 + "s", "| " + driver.gettPositionCount()) +
                        String.format("%1$" + -20 + "s", "| " + driver.getNumOfPoints()) +
                        String.format("%1$" + -20 + "s", "| " + driver.getNumOfRaces()) + "|\n";

                count += 1;
            }

            table += line;
            return table;
        }
    }

    /**
     * Method to check if a specific formula 1 driver is already existing in the Formula1ChampionshipManager object.
     *
     * @param name The name of the formula 1 driver.
     * @return A Boolean value ('true' if the driver is already existing, otherwise 'false').
     */
    public boolean nameExists(String name) {
        for (Formula1Driver driver : drivers) {
            if (driver.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method to check if a specific formula 1 team is existing in the Formula1ChampionshipManager object.
     *
     * @param name The name of the formula 1 team.
     * @return A Boolean value ('true' if the team is existing, otherwise 'false').
     */
    public boolean teamExists(String name) {
        for (Formula1Driver driver : drivers) {
            if (driver.getTeam().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method to store races into the race Array of the Formula1ChampionshipManager object.
     *
     * @param winners   Array containing the names of the winners in a race.
     * @param date      Date object created for the race.
     */
    public void storeRace(String[] winners, Date date) {
        Race race = new Race();
        int[] racePoints = Race.getRacePoints();
        HashMap<Integer, String > places = new HashMap<>();

        for (int i = 0; i < winners.length; i++) {
            //Storing the drivers name and their position in the race inside the places hash map where the position is added as the key.
            places.put(i + 1, winners[i]);

            for (Formula1Driver driver : drivers) {
                if (driver.getName().equalsIgnoreCase(winners[i])) {
                    driver.updateStats((i + 1), racePoints[i]);
                    break;
                }
            }
        }

        race.setDate(date);
        race.setPlaces(places);
        races.add(race);
    }

    /**
     * Method to get the length of the formula 1 drivers array of the Formula1ChampionshipManager object.
     *
     * @return The size of the formula 1 drivers array of the Formula1ChampionshipManager object.
     */
    public int driverArrayLength() {
        return drivers.size();
    }
}