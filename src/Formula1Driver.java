import java.io.Serializable;

/**
 * The Formula1Driver class to store drivers participating in formula 1 championship.
 */
public class Formula1Driver extends Driver implements Comparable<Formula1Driver>, Serializable {
    private int fPositionCount;
    private int sPositionCount;
    private int tPositionCount;
    private int numOfPoints;
    private int numOfRaces;

    /**
     * Parameterized constructor for Formula1Driver class.
     *
     * @param name      The name of the formula 1 driver.
     * @param location  The location of the formula 1 driver.
     * @param team      The team name of the formula 1 driver.
     */
    public Formula1Driver(String name, String location, String team) {
        super(name, location, team);
    }

    /**
     * Getter method to get the number of first positions.
     *
     * @return Number of first positions of the Formula1Driver object.
     */
    public int getfPositionCount() {
        return fPositionCount;
    }

    /**
     * Setter method to set the number of first positions.
     *
     * @param fPositionCount The number of first positions for the Formula1Driver object.
     */
    public void setfPositionCount(int fPositionCount) {
        this.fPositionCount = fPositionCount;
    }

    /**
     * Getter method to get the number of second positions.
     *
     * @return Number of second positions of the Formula1Driver object.
     */
    public int getsPositionCount() {
        return sPositionCount;
    }

    /**
     * Setter method to set the number of second positions.
     *
     * @param sPositionCount The number of second positions for the Formula1Driver object.
     */
    public void setsPositionCount(int sPositionCount) {
        this.sPositionCount = sPositionCount;
    }

    /**
     * Getter method to get the number of third positions.
     *
     * @return The number of third positions of the Formula1Driver object.
     */
    public int gettPositionCount() {
        return tPositionCount;
    }

    /**
     * Setter method to set the number of third positions.
     *
     * @param tPositionCount The number of third positions for the Formula1Driver object.
     */
    public void settPositionCount(int tPositionCount) {
        this.tPositionCount = tPositionCount;
    }

    /**
     * Getter method to get the number of points.
     *
     * @return The number of points of the Formula1Driver object.
     */
    public int getNumOfPoints() {
        return numOfPoints;
    }

    /**
     * Setter method to set the number of points.
     *
     * @param numOfPoints The number of points for the Formula1Driver object.
     */
    public void setNumOfPoints(int numOfPoints) {
        this.numOfPoints = numOfPoints;
    }

    /**
     * Getter method to get the number of races.
     *
     * @return The number of races of the Formula1Driver object.
     */
    public int getNumOfRaces() {
        return numOfRaces;
    }

    /**
     * Setter method to set the number of races.
     *
     * @param numOfRaces The number of races of the Formula1Driver object.
     */
    public void setNumOfRaces(int numOfRaces) {
        this.numOfRaces = numOfRaces;
    }

    /**
     * A method to update the statistics of the Formula1Driver object after a race.
     *
     * @param position  The position of the Formula1Driver object.
     * @param points    The points of the Formula1Driver object.
     */
    public void updateStats(int position, int points) {
        if (position == 1) {
            this.fPositionCount += 1;
        } else if (position == 2) {
            this.sPositionCount += 1;
        } else if (position == 3) {
            this.tPositionCount += 1;
        }

        this.numOfPoints += points;
        this.numOfRaces += 1;
    }

    /**
     * compareTo method to compare the Formula1Driver object based on their points and number of first positions.
     *
     * @param f1Driver Formula1Driver object to be compared with.
     * @return Integer ('0' if equal in value, '-1' if greater, '1' if lesser).
     */
    @Override
    public int compareTo(Formula1Driver f1Driver) {
        if (numOfPoints == f1Driver.numOfPoints) {
            if (fPositionCount == f1Driver.fPositionCount) {
                return 0;
            } else if (fPositionCount < f1Driver.fPositionCount) {
                return 1;
            } else {
                return -1;
            }
        } else if (numOfPoints < f1Driver.numOfPoints) {
            return 1;
        } else {
            return -1;
        }
    }
}