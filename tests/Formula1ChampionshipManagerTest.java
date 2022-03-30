import org.junit.Test;

import static org.junit.Assert.*;

public class Formula1ChampionshipManagerTest {

    /**
     * Method to test Formula1ChampionshipManager.printMenu() method.
     */
    @Test
    public void printMenu() {
        Formula1ChampionshipManager f1cm = new Formula1ChampionshipManager();

        String expectedOutput = "_______________________________________________\n" +
                "MENU\n\n" +
                "N: Create a new driver\n" +
                "D: Delete a driver\n" +
                "C: Change the driver for a team\n" +
                "P: Display driver statistics\n" +
                "T: Display formula 1 driver table\n" +
                "R: Add a race completed\n" +
                "S: Save all the data into a file\n" +
                "O: Open GUI for Formula 1 Championship Manager\n" +
                "Q: Quit the program\n";

        // Testing if the printMenu() works as expected.
        assertEquals(expectedOutput, f1cm.printMenu());
    }

    /**
     * Method to test Formula1ChampionshipManager.create() method.
     */
    @Test
    public void create() {
        Formula1ChampionshipManager f1cm = new Formula1ChampionshipManager();

        f1cm.create("Saman", "Sri Lanka", "Vega");

        assertEquals("Saman", f1cm.getDrivers().get(0).getName());
        assertEquals("Sri Lanka", f1cm.getDrivers().get(0).getLocation());
        assertEquals("Vega", f1cm.getDrivers().get(0).getTeam());
        assertEquals(0, f1cm.getDrivers().get(0).getfPositionCount());
        assertEquals(0, f1cm.getDrivers().get(0).getsPositionCount());
        assertEquals(0, f1cm.getDrivers().get(0).gettPositionCount());
        assertEquals(0, f1cm.getDrivers().get(0).getNumOfPoints());
        assertEquals(0, f1cm.getDrivers().get(0).getNumOfRaces());
    }

    /**
     * Method to test Formula1ChampionshipManager.delete() method.
     */
    @Test
    public void delete() {
        Formula1ChampionshipManager f1cm = new Formula1ChampionshipManager();

        //Trying to delete a driver when there are no drivers added.
        String output = f1cm.delete("Saman");
        String expectedOutput = "\nThere was no driver called 'Saman' found in Formula 1 Championship";

        assertEquals(expectedOutput, output);

        f1cm.create("Saman", "Sri Lanka", "Vega");

        //Trying to delete a driver that is not in formula 1 championship.
        output = f1cm.delete("Nimal");
        expectedOutput = "\nThere was no driver called 'Nimal' found in Formula 1 Championship";

        assertEquals(expectedOutput, output);

        //Trying to delete a valid driver in formula 1 championship.
        output = f1cm.delete("Saman");
        expectedOutput = "\nDriver Saman and team Vega was successfully deleted from Formula 1 Championship";

        assertEquals(expectedOutput, output);
    }

    /**
     * Method to test Formula1ChampionshipManager.change() method.
     */
    @Test
    public void change() {
        Formula1ChampionshipManager f1cm = new Formula1ChampionshipManager();

        f1cm.create("Saman", "Sri Lanka", "Vega");
        f1cm.create("Nezuko", "Japan", "Toyota");

        //Trying to change the driver for a team where the new drivers name is same as previous drivers name.
        String output = f1cm.change("Vega", "Saman", "");
        String expectedOutput = "\nNew driver's name is same as the previous driver's name" +
                "\nIf this is not the same driver, please enter his/her middle name or surname and try again";

        assertEquals(expectedOutput, output);

        //Trying to change the driver for a team where the new drivers name similar to a drivers name in another team.
        output = f1cm.change("Vega", "Nezuko", "");
        expectedOutput = "\nA driver from that name already exists in a team" +
                "\nIf this is not the same driver, please enter his/her middle name or surname and try again";

        assertEquals(expectedOutput, output);

        //Trying to change the driver for a team with valid inputs.
        expectedOutput = "\nDriver for team Vega successfully changed from 'Saman";
        output = f1cm.change("Vega", "Kamal", "Sri Lanka");
        expectedOutput += "' to 'Kamal'";

        assertEquals(expectedOutput, output);
    }

    /**
     * Method to test Formula1ChampionshipManager.displayStats() method.
     */
    @Test
    public void displayStats() {
        Formula1ChampionshipManager f1cm = new Formula1ChampionshipManager();

        f1cm.create("Saman", "Sri Lanka", "Vega");

        //Trying to get the statistics of a driver that does not exist in formula 1 championship.
        String output = f1cm.displayStats("Nimal");
        String expectedOutput = "\nThere was no driver called 'Nimal' found in Formula 1 Championship";

        assertEquals(expectedOutput, output);

        //Trying to get the statistics of a driver that exists in formula 1 championship.
        output = f1cm.displayStats("Saman");
        expectedOutput = "\nDisplaying season statistics of driver Saman\n\n" +
                "Name     : Saman\n" +
                "Location : Sri Lanka\n" +
                "Team     : Vega\n" +
                "Number of first positions  : 0\n" +
                "Number of second positions : 0\n" +
                "Number of third positions  : 0\n" +
                "Number of points so far : 0\n" +
                "Number of races participated : 0";

        assertEquals(expectedOutput, output);
    }

    /**
     * Method to test Formula1ChampionshipManager.displayTable() method.
     */
    @Test
    public void displayTable() {
        Formula1ChampionshipManager f1cm = new Formula1ChampionshipManager();

        //Trying to display the drivers table when there are no drivers existing in formula 1 championship.
        String output = f1cm.displayTable();
        String expectedOutput = "There are no drivers to display in Formula 1 Driver Table";

        assertEquals(expectedOutput, output);


        f1cm.create("Saman", "Sri Lanka", "Vega");
        f1cm.create("Nezuko", "Japan", "Toyota");
        f1cm.create("Hamilton", "Canada", "Ferrari");

        //Printing the table when the drivers does not have any statistics.
        expectedOutput = "-----------------------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                "| Seq | Name              | Location          | Team              | First Positions   | Second Positions  | Third Positions   | Total Points      | Races Participated|\n" +
                "-----------------------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                "| 1   | Saman             | Sri Lanka         | Vega              | 0                 | 0                 | 0                 | 0                 | 0                 |\n" +
                "| 2   | Nezuko            | Japan             | Toyota            | 0                 | 0                 | 0                 | 0                 | 0                 |\n" +
                "| 3   | Hamilton          | Canada            | Ferrari           | 0                 | 0                 | 0                 | 0                 | 0                 |\n" +
                "-----------------------------------------------------------------------------------------------------------------------------------------------------------------------";
        output = f1cm.displayTable();
        assertEquals(expectedOutput, output);

        //Adding some statistics and printing the table to check if it is sorted as expected.
        f1cm.getDrivers().get(0).setNumOfPoints(50);
        f1cm.getDrivers().get(1).setNumOfPoints(50);
        f1cm.getDrivers().get(2).setNumOfPoints(40);
        f1cm.getDrivers().get(0).setfPositionCount(1);
        f1cm.getDrivers().get(1).setfPositionCount(2);

        expectedOutput = "-----------------------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                "| Seq | Name              | Location          | Team              | First Positions   | Second Positions  | Third Positions   | Total Points      | Races Participated|\n" +
                "-----------------------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                "| 1   | Nezuko            | Japan             | Toyota            | 2                 | 0                 | 0                 | 50                | 0                 |\n" +
                "| 2   | Saman             | Sri Lanka         | Vega              | 1                 | 0                 | 0                 | 50                | 0                 |\n" +
                "| 3   | Hamilton          | Canada            | Ferrari           | 0                 | 0                 | 0                 | 40                | 0                 |\n" +
                "-----------------------------------------------------------------------------------------------------------------------------------------------------------------------";
        output = f1cm.displayTable();
        assertEquals(expectedOutput, output);
    }

    /**
     * Method to test Formula1ChampionshipManager.nameExists() method.
     */
    @Test
    public void nameExists() {
        Formula1ChampionshipManager f1cm = new Formula1ChampionshipManager();

        f1cm.create("Saman", "Sri Lanka", "Vega");

        //Checking a drivers name that does not exist in formula 1 championship.
        assertFalse(f1cm.nameExists("Nimal"));

        //Checking a drivers name that does exist in formula 1 championship.
        assertTrue(f1cm.nameExists("Saman"));
    }

    /**
     * Method to test Formula1ChampionshipManager.teamExists() method.
     */
    @Test
    public void teamExists() {
        Formula1ChampionshipManager f1cm = new Formula1ChampionshipManager();

        f1cm.create("Saman", "Sri Lanka", "Vega");

        //Checking a teams name that does not exist in formula 1 championship.
        assertFalse(f1cm.teamExists("Ferrari"));

        //Checking a teams name that does exist in formula 1 championship.
        assertTrue(f1cm.teamExists("Vega"));
    }

    /**
     * Method to test Formula1ChampionshipManager.storeRace() method.
     */
    @Test
    public void storeRace() {
        Formula1ChampionshipManager f1cm = new Formula1ChampionshipManager();
        f1cm.setYear(2021);

        f1cm.create("Saman", "Sri Lanka", "Vega");
        f1cm.create("Nezuko", "Japan", "Toyota");
        f1cm.create("Hamilton", "Canada", "Ferrari");
        f1cm.create("Paul", "USA", "Mazda");
        f1cm.create("Kit", "Germany", "BMW");
        f1cm.create("Lucas", "England", "Mini");
        f1cm.create("Michael", "Italy", "Alfa Romeo");
        f1cm.create("David", "England", "Aston Martin");
        f1cm.create("Christopher", "Germany", "Audi");
        f1cm.create("Anthony", "England", "Bentley");
        f1cm.create("Andrew", "France", "Bugatti");
        f1cm.create("Timothy", "USA", "Cadillac");
        f1cm.create("Nadia", "USA", "Ford");
        f1cm.create("Dan", "Japan", "Nissan");
        f1cm.create("Hanks", "England", "Acura");
        f1cm.create("Daisy", "Switzerland", "Fiat");
        f1cm.create("Gillian", "Poland", "Genesis");
        f1cm.create("Jenny", "Denmark", "Jaguar");
        f1cm.create("Alexander", "Italy", "Lamborghini");
        f1cm.create("Jerome", "Canada", "Maserati");

        //Displaying the formula 1 driver's table before storing the race
        String expectedOutput = "-----------------------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                "| Seq | Name              | Location          | Team              | First Positions   | Second Positions  | Third Positions   | Total Points      | Races Participated|\n" +
                "-----------------------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                "| 1   | Saman             | Sri Lanka         | Vega              | 0                 | 0                 | 0                 | 0                 | 0                 |\n" +
                "| 2   | Nezuko            | Japan             | Toyota            | 0                 | 0                 | 0                 | 0                 | 0                 |\n" +
                "| 3   | Hamilton          | Canada            | Ferrari           | 0                 | 0                 | 0                 | 0                 | 0                 |\n" +
                "| 4   | Paul              | USA               | Mazda             | 0                 | 0                 | 0                 | 0                 | 0                 |\n" +
                "| 5   | Kit               | Germany           | BMW               | 0                 | 0                 | 0                 | 0                 | 0                 |\n" +
                "| 6   | Lucas             | England           | Mini              | 0                 | 0                 | 0                 | 0                 | 0                 |\n" +
                "| 7   | Michael           | Italy             | Alfa Romeo        | 0                 | 0                 | 0                 | 0                 | 0                 |\n" +
                "| 8   | David             | England           | Aston Martin      | 0                 | 0                 | 0                 | 0                 | 0                 |\n" +
                "| 9   | Christopher       | Germany           | Audi              | 0                 | 0                 | 0                 | 0                 | 0                 |\n" +
                "| 10  | Anthony           | England           | Bentley           | 0                 | 0                 | 0                 | 0                 | 0                 |\n" +
                "| 11  | Andrew            | France            | Bugatti           | 0                 | 0                 | 0                 | 0                 | 0                 |\n" +
                "| 12  | Timothy           | USA               | Cadillac          | 0                 | 0                 | 0                 | 0                 | 0                 |\n" +
                "| 13  | Nadia             | USA               | Ford              | 0                 | 0                 | 0                 | 0                 | 0                 |\n" +
                "| 14  | Dan               | Japan             | Nissan            | 0                 | 0                 | 0                 | 0                 | 0                 |\n" +
                "| 15  | Hanks             | England           | Acura             | 0                 | 0                 | 0                 | 0                 | 0                 |\n" +
                "| 16  | Daisy             | Switzerland       | Fiat              | 0                 | 0                 | 0                 | 0                 | 0                 |\n" +
                "| 17  | Gillian           | Poland            | Genesis           | 0                 | 0                 | 0                 | 0                 | 0                 |\n" +
                "| 18  | Jenny             | Denmark           | Jaguar            | 0                 | 0                 | 0                 | 0                 | 0                 |\n" +
                "| 19  | Alexander         | Italy             | Lamborghini       | 0                 | 0                 | 0                 | 0                 | 0                 |\n" +
                "| 20  | Jerome            | Canada            | Maserati          | 0                 | 0                 | 0                 | 0                 | 0                 |\n" +
                "-----------------------------------------------------------------------------------------------------------------------------------------------------------------------";

        String output = f1cm.displayTable();
        assertEquals(expectedOutput, output);

        //Storing a race
        Date raceDate = new Date();
        raceDate.setYear(f1cm.getYear());
        raceDate.setMonth(7);
        raceDate.setDay(13);

        String[] winners = {"Christopher", "Anthony", "Daisy", "Saman", "Kit", "Lucas", "Hamilton",
                "Jerome", "Michael", "David", "Dan", "Jenny", "Nadia", "Timothy", "Alexander"};

        f1cm.storeRace(winners, raceDate);

        //Checking if the date is properly stored.
        expectedOutput = "2021/7/13";
        output = f1cm.getRaces().get(0).getDate().toString();

        assertEquals(expectedOutput, output);

        //Checking if the positions of the race are stored properly.
        expectedOutput = "1 Christopher\n" +
                "2 Anthony\n" +
                "3 Daisy\n" +
                "4 Saman\n" +
                "5 Kit\n" +
                "6 Lucas\n" +
                "7 Hamilton\n" +
                "8 Jerome\n" +
                "9 Michael\n" +
                "10 David\n" +
                "11 Dan\n" +
                "12 Jenny\n" +
                "13 Nadia\n" +
                "14 Timothy\n" +
                "15 Alexander\n";

        output = "";

        for (int i = 1; i <= 15; i++) {
            output += i + " " + f1cm.getRaces().get(0).getPlaces().get(i) + "\n";
        }

        assertEquals(expectedOutput, output);

        //Displaying the formula 1 driver's table after storing the race to check if its updated based on race results.
        expectedOutput = "-----------------------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                "| Seq | Name              | Location          | Team              | First Positions   | Second Positions  | Third Positions   | Total Points      | Races Participated|\n" +
                "-----------------------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                "| 1   | Christopher       | Germany           | Audi              | 1                 | 0                 | 0                 | 25                | 1                 |\n" +
                "| 2   | Anthony           | England           | Bentley           | 0                 | 1                 | 0                 | 18                | 1                 |\n" +
                "| 3   | Daisy             | Switzerland       | Fiat              | 0                 | 0                 | 1                 | 15                | 1                 |\n" +
                "| 4   | Saman             | Sri Lanka         | Vega              | 0                 | 0                 | 0                 | 12                | 1                 |\n" +
                "| 5   | Kit               | Germany           | BMW               | 0                 | 0                 | 0                 | 10                | 1                 |\n" +
                "| 6   | Lucas             | England           | Mini              | 0                 | 0                 | 0                 | 8                 | 1                 |\n" +
                "| 7   | Hamilton          | Canada            | Ferrari           | 0                 | 0                 | 0                 | 6                 | 1                 |\n" +
                "| 8   | Jerome            | Canada            | Maserati          | 0                 | 0                 | 0                 | 4                 | 1                 |\n" +
                "| 9   | Michael           | Italy             | Alfa Romeo        | 0                 | 0                 | 0                 | 2                 | 1                 |\n" +
                "| 10  | David             | England           | Aston Martin      | 0                 | 0                 | 0                 | 1                 | 1                 |\n" +
                "| 11  | Nezuko            | Japan             | Toyota            | 0                 | 0                 | 0                 | 0                 | 0                 |\n" +
                "| 12  | Paul              | USA               | Mazda             | 0                 | 0                 | 0                 | 0                 | 0                 |\n" +
                "| 13  | Andrew            | France            | Bugatti           | 0                 | 0                 | 0                 | 0                 | 0                 |\n" +
                "| 14  | Timothy           | USA               | Cadillac          | 0                 | 0                 | 0                 | 0                 | 1                 |\n" +
                "| 15  | Nadia             | USA               | Ford              | 0                 | 0                 | 0                 | 0                 | 1                 |\n" +
                "| 16  | Dan               | Japan             | Nissan            | 0                 | 0                 | 0                 | 0                 | 1                 |\n" +
                "| 17  | Hanks             | England           | Acura             | 0                 | 0                 | 0                 | 0                 | 0                 |\n" +
                "| 18  | Gillian           | Poland            | Genesis           | 0                 | 0                 | 0                 | 0                 | 0                 |\n" +
                "| 19  | Jenny             | Denmark           | Jaguar            | 0                 | 0                 | 0                 | 0                 | 1                 |\n" +
                "| 20  | Alexander         | Italy             | Lamborghini       | 0                 | 0                 | 0                 | 0                 | 1                 |\n" +
                "-----------------------------------------------------------------------------------------------------------------------------------------------------------------------";

        output = f1cm.displayTable();
        assertEquals(expectedOutput, output);
    }
}