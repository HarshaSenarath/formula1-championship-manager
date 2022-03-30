import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Formula1GUI class which contains GUI for formula 1 championship.
 */
public class Formula1GUI {
    private final Formula1ChampionshipManager f1cm;
    private boolean running;
    private JFrame menuFrame;
    private JFrame tableFrame1;
    private JFrame tableFrame2;
    private JFrame tableFrame3;
    private JFrame randomRaceFrame1;
    private JFrame randomRaceFrame2;
    private JFrame raceTableFrame;
    private JFrame searchRaceFrame1;
    private JFrame searchRaceFrame2;

    /**
     * Parameterized constructor for Formula1GUI class
     *
     * @param f1cm Formula1Championship manager object from the CLI which contains all the details related to a specific season.
     */
    public Formula1GUI(Formula1ChampionshipManager f1cm) {
        this.f1cm = f1cm;
    }

    /**
     * Method which opens the menu frame of GUI
     */
    public void initializeGUI() {
        //Variable to check if the menu window is still open.
        running = true;

        //Frame which displays the GUI menu.
        menuFrame = new JFrame("Formula 1 Championship Manager");
        menuFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Window listener for menuFrame.
        WindowListener listener = new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                terminateSimilarFrames(tableFrame1);
                terminateSimilarFrames(tableFrame2);
                terminateSimilarFrames(tableFrame3);
                terminateSimilarFrames(randomRaceFrame1);
                terminateSimilarFrames(randomRaceFrame2);
                terminateSimilarFrames(raceTableFrame);
                terminateSimilarFrames(searchRaceFrame1);
                terminateSimilarFrames(searchRaceFrame2);
                running = false;
            }
        };

        //Label which contains the title of the Menu.
        JLabel l1 = new JLabel("Formula 1 Championship Manager");
        l1.setFont(new Font("Calibri", Font.BOLD, 30));
        l1.setForeground(Color.white);
        l1.setBounds(80, 60, 500, 40);

        //Button to display formula 1 drivers table in descending order based on points.
        JButton b1 = new JButton("Display Driver Table (Points)(Descending)");
        b1.setBounds(130, 160, 320, 40);
        //Customizing the button.
        configureButton(b1);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                terminateSimilarFrames(tableFrame1);

                //Frame which displays formula 1 drivers table in descending order based on points.
                tableFrame1 = new JFrame("Driver Table (Descending Order of Points)");
                tableFrame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                //Will sort all the formula 1 drivers in descending order based on points.
                Collections.sort(f1cm.getDrivers());
                displayTable(f1cm.getDrivers(), tableFrame1);
            }
        });

        //Button to display formula 1 drivers table in ascending order based on points.
        JButton b2 = new JButton("Display Driver Table (Points)(Ascending)");
        b2.setBounds(130, 220, 320, 40);
        //Customizing the button.
        configureButton(b2);

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                terminateSimilarFrames(tableFrame2);

                //Frame which displays formula 1 drivers table in ascending order based on points.
                tableFrame2 = new JFrame("Driver Table (Ascending Order of Points)");
                tableFrame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                //Will sort all the formula 1 drivers in ascending order based on points.
                f1cm.getDrivers().sort(Collections.reverseOrder());
                displayTable(f1cm.getDrivers(), tableFrame2);
            }
        });

        //Button to display formula 1 drivers table in descending order based on number of first positions.
        JButton b3 = new JButton("Display Driver Table (First Positions)(Descending)");
        b3.setBounds(130, 280, 320, 40);
        //Customizing the button.
        configureButton(b3);

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                terminateSimilarFrames(tableFrame3);

                //Frame which displays formula 1 drivers table in descending order based on number of first positions.
                tableFrame3 = new JFrame("Driver Table (Descending Order of Number of First Positions Won)");
                tableFrame3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                //Will sort all the formula 1 drivers in ascending order based on points.
                Collections.sort(f1cm.getDrivers(), new FPositionComparator());
                displayTable(f1cm.getDrivers(), tableFrame3);
            }
        });

        //Button to generate a random race.
        JButton b4 = new JButton("Generate a Race");
        b4.setBounds(130, 340, 320, 40);
        //Customizing the button.
        configureButton(b4);

        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                terminateSimilarFrames(randomRaceFrame1);

                //Frame which display the results of the random race.
                randomRaceFrame1 = new JFrame("Random Race");
                randomRaceFrame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                generateRace();
            }
        });

        //Button to generate a random Starting Position race.
        JButton b5 = new JButton("Generate a Race (RSP)");
        b5.setBounds(130, 400, 320, 40);
        //Customizing the button.
        configureButton(b5);

        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                terminateSimilarFrames(randomRaceFrame2);

                randomRaceFrame2 = new JFrame("Random Starting Position Race");
                randomRaceFrame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                generateRaceRSP();
            }
        });

        //Button to display all the races in ascending order of date.
        JButton b6 = new JButton("Display Completed Races");
        b6.setBounds(130, 460, 320, 40);
        //Customizing the button.
        configureButton(b6);

        b6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                terminateSimilarFrames(raceTableFrame);

                raceTableFrame = new JFrame("Completed Races");
                raceTableFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                displayRace();
            }
        });

        //Button to search for races related to a specific driver.
        JButton b7 = new JButton("Search Races");
        b7.setBounds(130, 520, 320, 40);
        //Customizing the button.
        configureButton(b7);

        b7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                terminateSimilarFrames(searchRaceFrame1);

                searchRaceFrame1 = new JFrame("Search a race");
                searchRaceFrame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                searchRace();
            }
        });

        menuFrame.add(l1);

        menuFrame.add(b1);
        menuFrame.add(b2);
        menuFrame.add(b3);
        menuFrame.add(b4);
        menuFrame.add(b5);
        menuFrame.add(b6);
        menuFrame.add(b7);

        //Adding a custom icon to frame
        addFrameIcon(menuFrame);
        menuFrame.getContentPane().setBackground(Color.decode("#141213"));
        menuFrame.addWindowListener(listener);
        menuFrame.setSize(600, 700);
        menuFrame.setLayout(null);
        menuFrame.setLocationRelativeTo(null);
        menuFrame.setResizable(false);
        menuFrame.setVisible(true);
    }

    /**
     * Method to display formula 1 drivers table from the Formula 1 GUI.
     *
     * @param drivers   Array containing the formula 1 drivers.
     * @param frame     Frame the table should be displayed.
     */
    private void displayTable(ArrayList<Formula1Driver> drivers, JFrame frame) {
        if (drivers.size() != 0) {
            //Defining column names for the formula 1 drivers table.
            String[] column = {"Seq", "Name", "Location", "Team", "First Positions", "Second Positions", "Third Positions",
                    "Total Points", "Races Participated"};

            //Arranging data for the formula 1 drivers table from the drivers array inside the f1cm object.
            String[][] data = new String[drivers.size()][9];

            for (int i = 0; i < drivers.size(); i++) {
                data[i][0] = String.valueOf((i + 1));
                data[i][1] = drivers.get(i).getName();
                data[i][2] = drivers.get(i).getLocation();
                data[i][3] = drivers.get(i).getTeam();
                data[i][4] = String.valueOf(drivers.get(i).getfPositionCount());
                data[i][5] = String.valueOf(drivers.get(i).getsPositionCount());
                data[i][6] = String.valueOf(drivers.get(i).gettPositionCount());
                data[i][7] = String.valueOf(drivers.get(i).getNumOfPoints());
                data[i][8] = String.valueOf(drivers.get(i).getNumOfRaces());
            }

            //Making the cells uneditable when the table is displayed.
            //https://stackoverflow.com/questions/1990817/how-to-make-a-jtable-non-editable
            TableModel model = new DefaultTableModel(data, column) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            JTable driverTable = new JTable(model);

            JScrollPane sPane = new JScrollPane(driverTable);

            //Adding a custom icon to frame
            addFrameIcon(frame);
            frame.add(sPane);
            frame.setSize(950, 500);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        } else {
            //Displaying a message dialog box if there are no drivers to display.
            JOptionPane.showMessageDialog(frame, "There are no drivers to display");
        }
    }

    /**
     * Method to generate a random race and display it from the Formula 1 GUI.
     */
    private void generateRace() {
        if (f1cm.driverArrayLength() >= 15) {
            //Generating a random date for the race.
            Date dateOfRace = generateDate();

            //Creating an array to store the winners of the race.
            String[] winners = new String[15];
            //Randomly choosing the winners from the drivers array inside the f1cm object.
            chooseDrivers(winners);

            //Storing the data inside the f1cm object.
            f1cm.storeRace(winners, dateOfRace);

            //Defining column names for the race result table.
            String[] column = {"Position", "Name"};

            //Arranging data for the race result table.
            String[][] data = new String[15][2];

            for (int i = 0; i < winners.length; i++) {
                data[i][0] = String.valueOf((i + 1));
                data[i][1] = winners[i];
            }

            //Making the cells uneditable when the table is displayed.
            //https://stackoverflow.com/questions/1990817/how-to-make-a-jtable-non-editable
            TableModel model = new DefaultTableModel(data, column) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            JTable resultTable = new JTable(model);

            JLabel label = new JLabel("Race Results (Date : " + dateOfRace + ")");
            label.setBorder(new EmptyBorder(5,50,5,0));
            label.setFont(new Font("Calibri", Font.BOLD, 15));

            Container container = randomRaceFrame1.getContentPane();
            container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

            container.add(label);

            container.add(resultTable.getTableHeader());
            container.add(resultTable);

            //Adding a custom icon to frame
            addFrameIcon(randomRaceFrame1);
            randomRaceFrame1.setSize(400, 330);
            randomRaceFrame1.setVisible(true);
        } else {
            //Displaying a message dialog box if there are insufficient drivers to generate a race.
            JOptionPane.showMessageDialog(randomRaceFrame1, "Insufficient drivers in Formula 1 Championship to generate a race");
        }
    }

    /**
     * Method to generate a random starting position race and display it from the Formula 1 GUI.
     */
    private void generateRaceRSP() {
        if (f1cm.driverArrayLength() >= 15) {
            Random random = new Random();
            //Generating a random date for the race.
            Date dateOfRace = generateDate();

            //Creating an array to store the starting positions of randomly selected drivers.
            String[] startingPositions = new String[15];
            //Creating an array to store the randomly selected winners of the race.
            String[] winners = new String[15];

            //Choose drivers for starting positions.
            chooseDrivers(startingPositions);

            //Generating a random number from 1 to 100
            int winningNumber = random.nextInt(100) + 1;

            //Choosing the winner(first place) based on the winningNumber which was generated and the ranges assigned to each position based on their probability.
            if (winningNumber <= 40) {
                winners[0] = startingPositions[0];
            } else if (winningNumber <= 70) {
                winners[0] = startingPositions[1];
            } else if (winningNumber <= 80) {
                winners[0] = startingPositions[2];
            } else if (winningNumber <= 90) {
                winners[0] = startingPositions[3];
            } else if (winningNumber <= 92) {
                winners[0] = startingPositions[4];
            } else if (winningNumber <= 94) {
                winners[0] = startingPositions[5];
            } else if (winningNumber <= 96) {
                winners[0] = startingPositions[6];
            } else if (winningNumber <= 98) {
                winners[0] = startingPositions[7];
            } else {
                winners[0] = startingPositions[8];
            }

            //Choose drivers for the rest of the positions
            for (int i = 1; i < winners.length; i++) {
                while (true) {
                    //Generating a random index based on the length of startingPositions array.
                    int index = random.nextInt(winners.length);
                    //Fetching the name of the driver stored inside the randomly generated index, from the startingPositions array.
                    String driverName = startingPositions[index];

                    try {
                        //Looping through the winners array to check if the driver is already chosen.
                        for (String winner : winners) {
                            if (winner.equalsIgnoreCase(driverName)) {
                                break;
                            }
                        }
                        //Catching the null pointer exception thrown from the for loop indicating the element does not exist inside the array.
                    } catch (NullPointerException e) {
                        winners[i] = driverName;
                        break;
                    }
                }
            }

            //Storing the data inside the f1cm object.
            f1cm.storeRace(winners, dateOfRace);

            JTable startTable = getRaceInfoTable(startingPositions, "Starting Position");
            JTable resultTable = getRaceInfoTable(winners, "Position");

            JLabel label1 = new JLabel("Race (Date : " + dateOfRace + ")");
            label1.setBorder(new EmptyBorder(5,50,5,0));
            label1.setFont(new Font("Calibri", Font.BOLD, 15));

            JLabel label2 = new JLabel("Starting Positions");
            label2.setBorder(new EmptyBorder(5,0,5,0));

            JLabel label3 = new JLabel("Results");
            label3.setBorder(new EmptyBorder(5,0,5,0));

            Container container = randomRaceFrame2.getContentPane();
            container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

            container.add(label1);
            container.add(label2);

            container.add(startTable.getTableHeader());
            container.add(startTable);

            container.add(label3);

            container.add(resultTable.getTableHeader());
            container.add(resultTable);

            //Adding a custom icon to frame
            addFrameIcon(randomRaceFrame2);
            randomRaceFrame2.setSize(400, 640);
            randomRaceFrame2.setVisible(true);
        } else {
            //Displaying a message dialog box if there are insufficient drivers to generate a race.
            JOptionPane.showMessageDialog(randomRaceFrame2, "Insufficient drivers in Formula 1 Championship to generate a race");
        }
    }

    /**
     * Method to display all the races stored inside f1cm object.
     */
    private void displayRace() {
        if (f1cm.getRaces().size() != 0) {
            ArrayList<Race> races = f1cm.getRaces();

            //Generating a JTable with the relevant races in ascending order of the date of the date of the race.
            JTable driverTable = getRaceTable(races);

            JScrollPane sPane = new JScrollPane(driverTable);

            //Adding a custom icon to frame
            addFrameIcon(raceTableFrame);
            raceTableFrame.add(sPane);
            raceTableFrame.setSize(1300, 600);
            raceTableFrame.setLocationRelativeTo(null);
            raceTableFrame.setVisible(true);
        } else {
            //Displaying a message dialog box if the races array is empty in f1cm object.
            JOptionPane.showMessageDialog(raceTableFrame, "There are no races available to display");
        }
    }

    /**
     * Method to display all the races participated by a specific driver out of the races stored inside f1cm object.
     */
    private void searchRace() {
        //Checking if the races array is empty in f1cm object.
        if (f1cm.getRaces().size() != 0) {
            JLabel label = new JLabel("Enter Driver's Name");
            label.setFont(new Font("Calibri", Font.BOLD, 15));
            label.setForeground(Color.white);
            label.setBounds(50,50, 200,30);

            JTextField nameTextField = new JTextField();
            nameTextField.setBounds(180,50, 200,30);

            JButton searchButton = new JButton("SEARCH");
            searchButton.setBounds(180,130, 100,30);
            configureButton(searchButton);

            searchButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //Getting what is inside the nameTextField after button press.
                    String name = nameTextField.getText();

                    //Disposing any similar frames if exists.
                    terminateSimilarFrames(searchRaceFrame2);

                    if (name.isEmpty()) {
                        //Displaying a message dialog box if the searchButton is pressed while the nameTextField is empty.
                        JOptionPane.showMessageDialog(searchRaceFrame1, "Name can not be empty");
                    } else if (containsNumerics(name)) {
                        //Displaying a message dialog box if the searchButton is pressed while the nameTextField contains numerics.
                        JOptionPane.showMessageDialog(searchRaceFrame1, "Name can not contain numbers");
                    } else if (!f1cm.nameExists(name)) {
                        //Displaying a message dialog box if the driver does not exist in f1cm object.
                        JOptionPane.showMessageDialog(searchRaceFrame1, "Driver does not exist in Formula 1 Championship");
                    } else {
                        //Filtering the races related to the specific driver entered by the user.
                        ArrayList<Race> filteredRaces = filterRaces(name);

                        if (filteredRaces.size() == 0) {
                            //Displaying a message dialog box if the specific driver has not participated any races.
                            JOptionPane.showMessageDialog(searchRaceFrame1, "Driver has not participated in any races yet");
                        } else {
                            //Frame which displays races related to the specific driver entered by the user.
                            searchRaceFrame2 = new JFrame("Displaying races for " + name);
                            searchRaceFrame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                            //Generating a JTable with the relevant races.
                            JTable table = getRaceTable(filteredRaces);

                            JScrollPane sPane = new JScrollPane(table);

                            //Adding a custom icon to frame
                            addFrameIcon(searchRaceFrame2);
                            searchRaceFrame2.add(sPane);
                            searchRaceFrame2.setSize(1300, 600);
                            searchRaceFrame2.setVisible(true);
                        }
                    }
                }
            });

            //Adding a custom icon to frame
            addFrameIcon(searchRaceFrame1);
            searchRaceFrame1.add(label);
            searchRaceFrame1.add(nameTextField);
            searchRaceFrame1.add(searchButton);

            searchRaceFrame1.getContentPane().setBackground(Color.decode("#141213"));
            searchRaceFrame1.setSize(450,300);
            searchRaceFrame1.setLayout(null);
            searchRaceFrame1.setResizable(false);
            searchRaceFrame1.setLocationRelativeTo(null);
            searchRaceFrame1.setVisible(true);
        } else {
            //Displaying a message dialog box if the races array is empty in f1cm object.
            JOptionPane.showMessageDialog(searchRaceFrame1, "There are no races available to search");
        }
    }

    /**
     * Method to check if the menu window of formula 1 championship GUI is open or closed.
     *
     * @return Returns a Boolean value ('true' if the menu window is still open, otherwise 'false').
     */
    public boolean stillRunning() {
        return running;
    }

    /**
     * Method to dispose any similar frames if exists when opening a specific frame.
     *
     * @param frame Frame that needs to be disposed.
     */
    private void terminateSimilarFrames(JFrame frame) {
        try {
            frame.dispose();
        } catch (NullPointerException ignored) {
        }
    }

    /**
     * Method to generate a random date for a random race.
     *
     * @return Date object containing the random date.
     */
    private Date generateDate() {
        Date date = new Date();
        Random random = new Random();

        int year = f1cm.getYear();
        //Generating a random month.
        int month = random.nextInt(12) + 1;
        int day;

        //Generating a random day and validating it.
        while (true) {
            day = random.nextInt(31) + 1;

            if (date.validateDay(month, day)) {
                break;
            }
        }

        //Saving the generated date inside a Date object.
        date.setYear(year);
        date.setMonth(month);
        date.setDay(day);

        return date;
    }

    /**
     * Method to randomly choose some drivers from the drivers ArrayList inside f1cm object.
     *
     * @param drivers   An Array to store the drivers who are being chosen.
     */
    private void chooseDrivers(String[] drivers) {
        Random random = new Random();

        for (int i = 0; i < drivers.length; i++) {
            while (true) {
                //Generating a random index based on the length of drivers ArrayList inside f1cm object.
                int index = random.nextInt(f1cm.driverArrayLength());
                //Fetching the name of the driver stored inside the randomly generated index, from the drivers ArrayList inside f1cm object.
                String driverName = f1cm.getDrivers().get(index).getName();

                try {
                    //Looping through the driver array to check if the driver is already chosen.
                    for (String winner : drivers) {
                        if (winner.equalsIgnoreCase(driverName)) {
                            break;
                        }
                    }
                    //Catching the null pointer exception thrown from the for loop indicating the element does not exists inside the array.
                } catch (NullPointerException e) {
                    drivers[i] = driverName;
                    break;
                }
            }
        }
    }

    /**
     * Method to generate tables that needs to display certain info when generating a random race.
     *
     * @param drivers       Array which contains the drivers that needs to be displayed in the table.
     * @param column1Name   Name of what the first column of the table should be.
     * @return JTable containing info of race.
     */
    private JTable getRaceInfoTable(String[] drivers, String column1Name) {
        //Defining column names for the table.
        String[] column = {column1Name, "Name"};

        //Arranging data for the table.
        String[][] data = new String[drivers.length][2];

        for (int i = 0; i < drivers.length; i++) {
            data[i][0] = String.valueOf((i + 1));
            data[i][1] = drivers[i];
        }

        //Making the cells uneditable when the table is displayed.
        //https://stackoverflow.com/questions/1990817/how-to-make-a-jtable-non-editable
        TableModel model = new DefaultTableModel(data, column) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(model);

        return table;
    }

    /**
     * Method to check if a user input contains any numbers.
     *
     * @param input The user input to be checked.
     * @return Boolean value ('true' if the input contains any numbers, otherwise 'false').
     */
    private boolean containsNumerics(String input) {
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
     * Method to generate tables that needs to display races.
     *
     * @param raceList ArrayList which contains the races that needs to be displayed in the table.
     * @return JTable containing races.
     */
    private JTable getRaceTable(ArrayList<Race> raceList) {
        //Will sort all the formula 1 races inside raceList in ascending order of date.
        Collections.sort(raceList);

        //Defining column names for the table.
        String[] column = {"Seq", "Date", "1st", "2nd", "3rd", "4th", "5th", "6th",
                "7th", "8th", "9th", "10th", "11th", "12th", "13th", "14th", "15th"};

        //Arranging data for the table.
        String[][] data = new String[raceList.size()][17];

        for (int i = 0; i < raceList.size(); i++) {
            data[i][0] = String.valueOf((i + 1));
            data[i][1] = raceList.get(i).getDate().toString();

            for (int key : raceList.get(i).getPlaces().keySet()) {
                data[i][key + 1] = raceList.get(i).getPlaces().get(key);
            }
        }

        //Making the cells uneditable when the table is displayed.
        //https://stackoverflow.com/questions/1990817/how-to-make-a-jtable-non-editable
        TableModel model = new DefaultTableModel(data, column) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(model);

        return table;
    }

    /**
     * Method to filter races participated by a specific driver.
     *
     * @param name Name of the driver which the races needs to be filtered out.
     * @return ArrayList containing the filtered races.
     */
    private ArrayList<Race> filterRaces(String name) {
        ArrayList<Race> races = f1cm.getRaces();
        ArrayList<Race> filteredRaces = new ArrayList<>();

        //Filtering the races the driver has participated.
        for (Race race : races) {
            //Looping through the hash map which has stored the places of each driver in a race inside a race object.
            for (String driverName : race.getPlaces().values()) {
                //Checking if the hash map contains the drivers name.
                if (driverName.equalsIgnoreCase(name)) {
                    filteredRaces.add(race);
                }
            }
        }

        return filteredRaces;
    }

    /**
     * Method to add configurations to a button.
     *
     * @param button JButton that needs to be configured.
     */
    private void configureButton(JButton button) {
        //Configuring buttons.
        //https://stackoverflow.com/questions/14159536/creating-jbutton-with-customized-look
        button.setFont(new Font("Calibri", Font.BOLD, 13));
        button.setForeground(Color.WHITE);
        button.setBackground(Color.decode("#B1211A"));
        button.setFocusPainted(false);
    }

    /**
     * Method to add a custom icon to a frame.
     *
     * @param frame JFrame the icon needs to be added.
     */
    private void addFrameIcon(JFrame frame) {
        //Adding a custom icon to a JFrame.
        //https://www.javatpoint.com/how-to-change-titlebar-icon-in-java-awt-swing
        Image icon = Toolkit.getDefaultToolkit().getImage("f1icon.png");
        frame.setIconImage(icon);
    }
}
