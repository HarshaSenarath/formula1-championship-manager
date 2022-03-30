import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Main class which contains the main method to run formula 1 championship manager.
 */
public class Main {
    static Scanner userInput = new Scanner(System.in);
    static Formula1ChampionshipManager f1cm = new Formula1ChampionshipManager();

    /**
     * The main method.
     *
     * @param args System arguments.
     */
    public static void main(String[] args) {
        System.out.println("WELCOME TO FORMULA 1 CHAMPIONSHIP MANAGER\n");

        //Setting the season year for formula 1 championship.
        setSeasonYear();
        //Loading if any previous data is available.
        setF1CManager(f1cm.getYear());

        System.out.println("\nLoading Menu...");

        //Pausing the execution of the program for a while.
        //https://stackoverflow.com/questions/24104313/how-do-i-make-a-delay-in-java
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (true) {
            //Printing the formula 1 championship menu.
            System.out.println(f1cm.printMenu());

            System.out.print("Enter your option out of 'N,D,C,P,T,R,S,O,Q' : ");
            String option = userInput.nextLine().toUpperCase();

            switch (option) {
                case "N" :
                    createDriver();
                    break;
                case "D" :
                    deleteDriver();
                    break;
                case "C" :
                    changeDriver();
                    break;
                case "P" :
                    displayDriverStats();
                    break;
                case "T" :
                    displayF1DriverTable();
                    break;
                case "R" :
                    addRace();
                    break;
                case "S" :
                    saveData();
                    break;
                case "O" :
                    openGUI();
                    break;
                case "Q" :
                    while (true) {
                        //Saving program data if the user wants to before exiting the application.
                        System.out.print("\nDo you want to save all the season data of Formula 1 Championship (Y/N) : ");
                        String decision = userInput.nextLine();

                        if (decision.equalsIgnoreCase("y")) {
                            saveData();
                            System.out.println("\nClosing Formula 1 Championship manager...");
                            System.exit(0);
                        } else if (decision.equalsIgnoreCase("n")) {
                            System.out.println("\nClosing Formula 1 Championship manager...");
                            System.exit(0);
                        }
                    }
                default :
                    System.out.print("\nPlease enter a valid option\n");
            }
            backToMenu();
        }
    }

    /**
     * Method to create a formula 1 driver in the formula 1 championship manager.
     */
    public static void createDriver() {
        System.out.println("_______________________________________________");
        System.out.println("CREATE A NEW DRIVER\n");

        //Getting the input for driver's name.
        String driverName = readInput("Enter driver's name : ", "Driver's name");

        //Checking if the driverName contains an empty String.
        if (!driverName.isEmpty()) {

            //Checking if the driver from the same name exists.
            if (!f1cm.nameExists(driverName)) {
                //Getting the input for driver's location.
                String driverLocation = readInput("Enter driver's location : ", "Driver's location");

                //Checking if the driverLocation contains an empty String.
                if (!driverLocation.isEmpty()) {
                    //Getting the input for driver's team.
                    String driverTeam = readInput("Enter driver's team : ", "Driver's team");

                    //Checking if the driverTeam contains an empty String.
                    if (!driverTeam.isEmpty()) {
                        //Checking if the entered team is already existing.
                        if (!f1cm.teamExists(driverTeam)) {
                            System.out.println(f1cm.create(driverName, driverLocation, driverTeam));
                        } else {
                            System.out.println("\nTeam " + driverTeam + " already has a driver" +
                                    "\nPlease enter a different team and try again");
                        }
                    }
                }
            } else {
                System.out.println("\nA driver from that name already exists in a team" +
                        "\nIf this is not the same driver, please enter his/her middle name or surname and try again");
            }
        }
    }

    /**
     * Method to delete a formula 1 driver and the driver's team from the formula 1 championship manager.
     */
    public static void deleteDriver() {
        System.out.println("_______________________________________________");
        System.out.println("DELETE A DRIVER AND HIS/HER TEAM\n");

        //Getting the input for driver's name to be deleted.
        String message = "Enter the driver's name you want to delete : ";
        String inputType = "Driver's name";
        String driverName = readInput(message, inputType);

        //Checking if the driverName contains an empty String.
        if (!driverName.isEmpty()) {
            System.out.println(f1cm.delete(driverName));
        }
    }

    /**
     * Method to change the formula 1 driver for an existing in the formula 1 championship manager.
     */
    public static void changeDriver() {
        System.out.println("_______________________________________________");
        System.out.println("CHANGER DRIVER FOR AN EXISTING TEAM\n");

        //Getting the input for driver's team.
        String message = "Enter the team name you want to change the driver in : ";
        String inputType = "Driver's team";
        String driverTeam = readInput(message, inputType);

        //Checking if the driverTeam contains an empty String.
        if (!driverTeam.isEmpty()) {

            //Checking if the entered team is existing.
            if (f1cm.teamExists(driverTeam)) {
                //Getting the input for driver's name to be added.
                message = "Enter the new driver's name you want to add to the team : ";
                inputType = "Driver's name";
                String driverName = readInput(message, inputType);

                //Checking if the driverName contains an empty String.
                if (!driverName.isEmpty()) {
                    String result = f1cm.change(driverTeam, driverName, "");
                    //Checking if the result contains an empty String.
                    if (result.isEmpty()) {
                        //Getting the input for driver's location.
                        message = "Enter the new driver's location : ";
                        inputType = "Driver's location";
                        String driverLocation = readInput(message, inputType);

                        System.out.println(f1cm.change(driverTeam, driverName, driverLocation));
                    } else {
                        System.out.println(result);
                    }
                }
            } else {
                System.out.println("\nThere was no team called '" + driverTeam + "' found in Formula 1 Championship");
            }
        }
    }

    /**
     * Method to display individual statistics of a specific driver in formula 1 championship.
     */
    public static void displayDriverStats() {
        System.out.println("_______________________________________________");
        System.out.println("DISPLAY DRIVER STATISTICS\n");

        //Getting the input for driver's name that needed to be displayed the statistics of.
        String message = "Enter the driver's name you want to see the statistics of : ";
        String inputType = "Driver's name";
        String driverName = readInput(message, inputType);

        //Checking if the driverName contains an empty String.
        if (!driverName.isEmpty()) {
            System.out.println(f1cm.displayStats(driverName));
        }
    }

    /**
     * Method to display the formula 1 driver's table in formula 1 championship.
     */
    public static void displayF1DriverTable() {
        System.out.println();
        //https://www.geeksforgeeks.org/string-class-repeat-method-in-java-with-examples/#:~:text=repeat()%20method%20is%20used,the%20empty%20string%20is%20returned
        System.out.println("-".repeat(167));
        //https://www.baeldung.com/java-pad-string
        String title = String.format("%1$" + 98 + "s", "FORMULA 1 DRIVER TABLE");
        System.out.println(title);

        System.out.println(f1cm.displayTable());
    }

    /**
     * Method to add a race into the formula 1 championship manager.
     */
    public static void addRace() {
        System.out.println("_______________________________________________");
        System.out.println("ADD A RACE COMPLETED\n");

        //Checking if at least 10 drivers are existing.
        if (f1cm.driverArrayLength() >= 15) {
            //Creating a Date object to store the date of the race.
            Date dateOfRace = readDate();

            //Creating an array to store the winners of the race.
            String[] winners = new String[15];

            System.out.println("\nEnter the race results (" + dateOfRace.toString() + ")\n");

            System.out.println("Available Drivers...\n");

            //Printing all the available drivers.
            int count = 0;
            String racer;

            for (Formula1Driver driver : f1cm.getDrivers()) {
                if (!(count < 5)) {
                    System.out.println();
                    count = 0;
                }

                racer = String.format("%1$" + -20 + "s", driver.getName());

                System.out.print(racer);
                count += 1;
            }

            System.out.println();
            System.out.println("\nEnter the driver's name for each position");
            for (int i = 0; i < winners.length; i++) {
                while (true) {
                    //Getting the driver's name for each position in the race.
                    String message = "\nPosition " + (i + 1) + " : ";
                    String inputType = "Driver's name";
                    String driverName = readInput(message, inputType);

                    //Checking if driverName contains an empty String and if the driver already has a position in the race.
                    if (!driverName.isEmpty() && elementExists(winners, driverName)) {
                        System.out.println(driverName + " already has a position in the race");
                        //Checking if driverName contains an empty String and if the driver exists in formula 1 championship.
                    } else if (!driverName.isEmpty() && f1cm.nameExists(driverName)) {
                        winners[i] = driverName;
                        break;
                    } else if (!driverName.isEmpty() && !f1cm.nameExists(driverName)) {
                        System.out.println("Driver does not exist in Formula 1 Championship");
                    }
                }
            }

            //Storing the race details.
            f1cm.storeRace(winners, dateOfRace);

            System.out.println("\nRace added successfully");
        } else {
            System.out.println("There are not enough drivers in Formula 1 Championship to add a race");
        }
    }

    /**
     * Method to save all the formula 1 championship manager data into a file.
     */
    public static void saveData() {
        System.out.println("_______________________________________________");
        System.out.println("SAVE ALL THE DATA INTO A FILE\n");

        if (f1cm.driverArrayLength() != 0) {
            try {
                FileOutputStream fileOut = new FileOutputStream("f1cm" + f1cm.getYear() + ".txt");
                ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

                objectOut.writeObject(f1cm);
                objectOut.flush();

                objectOut.close();
                fileOut.close();
                System.out.println("Successfully saved all the data into 'f1cm" + f1cm.getYear() + ".txt' file");
            } catch (Exception e) {
                System.out.println("An error occurred when storing the data");
            }
        } else {
            System.out.println("There is no data to be saved\n");
        }
    }

    /**
     * Method to open the GUI.
     */
    public static void openGUI() {
        Formula1GUI gui = new Formula1GUI(f1cm);

        System.out.println("\nOpening Formula 1 Championship GUI...");

        //Pausing the execution of the program for a while.
        //https://stackoverflow.com/questions/24104313/how-do-i-make-a-delay-in-java
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Opening GUI Menu.
        gui.initializeGUI();

        System.out.println("\nClose the GUI to return back to Menu");

        //Printing an empty string while the GUI Menu is open.
        while (gui.stillRunning()) {
            System.out.print("");
        }
    }

    /**
     * Method to check and load if any previous data is available for a specific season and set the formula 1 championship manager.
     *
     * @param seasonYear The season year for the formula 1 championship manager.
     */
    public static void setF1CManager(int seasonYear) {
        try {
            FileInputStream fileIn = new FileInputStream("f1cm" + seasonYear + ".txt");
            ObjectInputStream objectOut = new ObjectInputStream(fileIn);

            f1cm = (Formula1ChampionshipManager) objectOut.readObject();
            objectOut.close();
            objectOut.close();

            System.out.println("\nSuccessfully loaded previous data from file 'f1cm" + seasonYear + ".txt' file");
        } catch (FileNotFoundException ignored) {

        } catch (Exception e) {
            System.out.println("\nAn error occurred when loading the data from 'f1cm" + seasonYear + ".txt' file");
        }
    }

    /**
     * Method to set the season year for the formula 1 championship manager.
     */
    private static void setSeasonYear() {
        while (true) {
            try {
                System.out.print("Enter the Formula 1 Championship season year : ");
                int year = userInput.nextInt();
                userInput.nextLine();

                //Checking if the user entered year is within the allowed range.
                if (year >= 1933 && year <= 3000) {
                    f1cm.setYear(year);
                    System.out.println("\nSetting the season year for Formula 1 Championship manager to " + year);
                    break;
                } else {
                    System.out.println("Please enter a year between 1933 - 3000\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid year\n");
                userInput.nextLine();
            }
        }
    }

    /**
     * Method to get and validate String inputs from the user.
     *
     * @param message The message to be prompted when getting the input.
     * @param inputType Variable name or the kind of input expected from the user.
     * @return String which contains the user input if it is a valid a input or
     *         An empty String if the user input is not valid.
     */
    public static String readInput(String message, String inputType) {
        System.out.print(message);
        String input = userInput.nextLine();

        //Checking if the user input is an empty String.
        if (!input.isEmpty()) {
            //Checking if the user input contains any numbers.
            if (!containsNumerics(input)) {
                return input;
            } else {
                System.out.println("\n" + inputType + " can not contain numerics" +
                        "\nPlease try again");
                return "";
            }
        } else {
            System.out.println("\n" + inputType + " cannot be empty" +
                    "\nPlease try again");
            return "";
        }
    }

    /**
     * Method to check if a user input contains any numbers.
     *
     * @param input The user input to be checked.
     * @return Boolean value ('true' if the input contains any numbers, otherwise 'false').
     */
    public static boolean containsNumerics(String input) {
        //Breaking the user input into a characters array.
        char[] chars = input.toCharArray();

        for (char c : chars) {
            if (Character.isDigit(c)) {
                return true;
            }
        }

        return false;
    }

    /**
     * A method to indicate the program is returning back to the menu.
     */
    public static void backToMenu() {
        System.out.println("\nReturning to menu...");

        //Pausing the execution of the program for a while.
        //https://stackoverflow.com/questions/24104313/how-do-i-make-a-delay-in-java
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to get and validate date inputs from the user.
     *
     * @return Validated Date object.
     */
    public static Date readDate() {
        Date date = new Date();

        while (true) {
            try {
                System.out.println("Enter the date");
                int year = f1cm.getYear();
                System.out.println("\nYear : " + year);

                //Getting the user input for the month.
                System.out.print("Month : ");
                int month = userInput.nextInt();
                userInput.nextLine();

                //Validating the user input for the month.
                if (month < 1 || month >12) {
                    System.out.println("\nMonth can not be less than 1 or larger than 12\n");
                    continue;
                }

                //Getting the user input for day.
                System.out.print("Day : ");
                int day = userInput.nextInt();
                userInput.nextLine();

                //Checking if the user input for the day is valid.
                if (!date.validateDay(month, day)) {
                    System.out.println("\nInvalid day for month " + month + "\n");
                    continue;
                }

                date.setYear(year);
                date.setMonth(month);
                date.setDay(day);
                return date;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid input\n");
                userInput.nextLine();
            }
        }
    }

    /**
     * Method to check if a specific String element exists inside a String array.
     *
     * @param array The array that needs to be checked.
     * @param string The String that needs to be checked.
     * @return Returns a Boolean value ('true' if the specified String exists in the specified array, otherwise 'false').
     */
    public static boolean elementExists(String[] array, String string) {
        try {
            //Looping through the array.
            for (String element : array) {
                if (element.equalsIgnoreCase(string)) {
                    return true;
                }
            }
            return false;
            //Catching the null pointer exception thrown from the for loop indicating the element does not exists inside the array.
        } catch (NullPointerException e) {
            return false;
        }
    }
}