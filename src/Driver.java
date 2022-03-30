import java.io.Serializable;

/**
 * Abstract Class Driver.
 */
public abstract class Driver implements Serializable {
    private String name;
    private String location;
    private String team;

    /**
     * Parameterized constructor for Driver class.
     *
     * @param name      The name of the driver.
     * @param location  The location of the driver.
     * @param team      The team name of the driver.
     */
    public Driver(String name, String location, String team) {
        this.name = name;
        this.location = location;
        this.team = team;
    }

    /**
     * Getter method to get the name.
     *
     * @return The name of the Driver object.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method to set the name.
     *
     * @param name The name for the Driver object.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method to get the location.
     *
     * @return The location of the Driver object.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Setter method to set the location.
     *
     * @param location The location of the Driver object.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Getter method to get the team.
     *
     * @return The team of the Driver object.
     */
    public String getTeam() {
        return team;
    }
}