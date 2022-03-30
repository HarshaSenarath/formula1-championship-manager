import java.io.Serializable;
import java.util.HashMap;

/**
 * The Race class to store races happening in formula 1 championship.
 */
public class Race implements Comparable<Race>, Serializable {
    private final static int[] racePoints = {25, 18, 15, 12, 10, 8, 6, 4, 2, 1, 0, 0, 0, 0, 0};
    private HashMap<Integer, String > places = new HashMap<>();
    private Date date;

    /**
     * Getter method to get the racePoints array.
     *
     * @return The racePoints array of the Race class.
     */
    public static int[] getRacePoints() {
        return racePoints;
    }

    /**
     * Getter method to get the places hash.
     *
     * @return The places hash map of the Race object.
     */
    public HashMap<Integer, String> getPlaces() {
        return places;
    }

    /**
     * Setter method to set the places hash map.
     *
     * @param places The hash map for the Race object.
     */
    public void setPlaces(HashMap<Integer, String> places) {
        this.places = places;
    }

    /**
     * Getter method to get the Date object.
     *
     * @return The Date object of the Race object.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Setter method to set the Date object.
     *
     * @param date The Date object for the Race object.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * compareTo method to compare the Race object based on their Date object.
     *
     * @param race Race object to be compared with.
     * @return Returns an Integer ('0' if equal in value, '-1' if lesser, '1' if greater).
     */
    @Override
    public int compareTo(Race race) {
        int result = date.compareTo(race.date);

        if(result == 0) {
            return 0;
        } else if(result == 1) {
            return 1;
        } else {
            return -1;
        }
    }
}