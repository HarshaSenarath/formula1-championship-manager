import java.util.Comparator;

/**
 * The FPositionComparator class.
 */
public class FPositionComparator implements Comparator<Formula1Driver> {

    /**
     * compareTo method to compare Formula1Driver objects based on number of first positions.
     *
     * @param fDriver1 Formula1Driver object to be compared.
     * @param fDriver2 Formula1Driver object to be compared with.
     * @return Returns an Integer ('0' if equal in value, '-1' if greater, '1' if lesser).
     */
    @Override
    public int compare(Formula1Driver fDriver1, Formula1Driver fDriver2) {
        if(fDriver1.getfPositionCount() == fDriver2.getfPositionCount())
            return 0;
        else if(fDriver1.getfPositionCount() < fDriver2.getfPositionCount())
            return 1;
        else
            return -1;
    }
}