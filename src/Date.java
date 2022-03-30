import java.io.Serializable;

/**
 * Custom Date class to store dates.
 */
public class Date implements Comparable<Date>, Serializable {
    private int year;
    private int month;
    private int day;

    /**
     * Setter method to set the year.
     *
     * @param year The year for the Date object.
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Setter method to set the month.
     *
     * @param month The month for the Date object.
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * Setter method to set the day of the Date object.
     *
     * @param day The day for the Date object.
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * Method to validate a day.
     *
     * @param month Month.
     * @param day Day.
     * @return Returns a Boolean value ('true' if the day is valid, otherwise 'false').
     */
    public boolean validateDay(int month, int day) {
        //Checking if the month is one of the months which has 31 days.
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            if (day < 1 || day > 31) {
                return false;
            } else {
                return true;
            }
            //Checking if the month is one of the months which has 30 days.
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            if (day < 1 || day > 30) {
                return false;
            } else {
                return true;
            }
            //Checking if the month is February.
        } else if (month == 2) {
            if (day < 1 || day > 28) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * toString method for Date object.
     *
     * @return Returns the Date object as a String containing all the details of the Date object.
     */
    @Override
    public String toString() {
        return year + "/" + month + "/" + day;
    }

    /**
     * compareTo method to compare the Date object based on year, month, day.
     *
     * @param date Date object to be compared with.
     * @return Returns an Integer ('0' if equal in value, '-1' if lesser, '1' if greater).
     */
    @Override
    public int compareTo(Date date) {
        if (month == date.month) {
            if (day == date.day) {
                return 0;
            } else if (day > date.day) {
                return 1;
            } else {
                return -1;
            }
        } else if (month > date.month) {
            return 1;
        } else {
            return -1;
        }
    }
}