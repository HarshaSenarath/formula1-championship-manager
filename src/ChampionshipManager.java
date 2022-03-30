/**
 * Interface for the championship manager classes.
 */
public interface ChampionshipManager {

    /**
     * Method to specify the menu of championship manager.
     *
     * @return A String containing the menu of championship manager.
     */
    String printMenu();

    /**
     * Method to create a driver in the championshipManager object.
     *
     * @param name      Name of the driver to be created.
     * @param location  Location of the driver to be created.
     * @param team      Team of the driver to be created.
     * @return A String containing the name of the driver created and the team driver was added.
     */
    String create(String name, String location, String team);

    /**
     * Method to delete a driver and the driver's team from the championshipManager object.
     *
     * @param name Name of the driver to be deleted.
     * @return A String containing a suitable message if the specified driver and the driver's team was deleted successfully or
     *         A String containing a suitable message if the specified driver does not exist in the championshipManager object.
     */
    String delete(String name);

    /**
     * Method to change a driver belongs to a specific team in the championshipManager object.
     *
     * @param team      Team the driver is being changed.
     * @param name      Name of the new driver to be added.
     * @param location  Location of the new driver to be added.
     * @return A String containing a suitable message if the new driver's name is same as the previous driver's name or
     *         A String containing a suitable message if there is a driver already existing in another team with new driver's name or
     *         A String containing a suitable message if the specified team's driver was changed successfully or
     *         An empty String if the location parameter contains an empty string or
     *         Null.
     */
    String change(String team, String name, String location);

    /**
     * Method to display the seasonal statistics of a specific driver in the championshipManager object.
     *
     * @param name Name of the driver that needs to be displayed the statistics of.
     * @return A String containing the seasonal statistics of the specified driver or
     *         A String containing a suitable message if the specified driver does not exist in the championshipManager object.
     */
    String displayStats(String name);

    /**
     * Method to display the driver's table in the championshipManager object.
     *
     * @return A String containing the driver's table of drivers in the championshipManager object.
     *         A String containing a suitable message if there are no drivers existing in the championshipManager object.
     */
    String displayTable();
}
