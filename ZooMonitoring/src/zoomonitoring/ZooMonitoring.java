/* This program allows the monitoring of zoo animals and habitats. The program
presents the user with a menu. Depending on the user's choice, a submenu appears
detailing animals or habitats. The program is to return to main menu after
each action is completed
*/
package zoomonitoring;
//Import necessary libraries
import java.util.Scanner;
import java.io.*;

public class ZooMonitoring {
    public static void main(String [] args) throws IOException {
        // Create new scanner to read user's input
        Scanner scnr = new Scanner(System.in);
        
        // initialize variables
        int i = 0;
        int userInput = 0; // user's input of animal or habitat
        Scanner animalInfo = null;
        int detailsUserInput = 0; // user's input of choice of animal details
        FileInputStream monitorFile = null; // allows to read from file
        String animalLineInfo = "";
        Scanner habitatInfo = null;
        String habitatLineInfo = "";
        Scanner animalSubInfo = null; // for animal file's submenu
        Scanner habitatSubInfo = null; // for habitat file's submenu
        boolean keepRunning = false; 
        
        // Run loop while user does not press 3(exit)
        OUTER:
        while (userInput != 3) {
            System.out.println("Would you like to monitor animal, habitat or exit?");
            System.out.println("Press 1 for animal");
            System.out.println("Press 2 for habitat");
            System.out.println("Press 3 to  exit");
            
            // Scan for user input and store in variable userInput
            userInput = scnr.nextInt();
            System.out.println();
            
            // Switch case statement utilized. Originally use loops
            switch (userInput) {
                case 1:
                    // if user chooses animal
                    monitorFile = new FileInputStream("animal.txt"); // read from file
                    animalInfo = new Scanner (monitorFile); // store into animalInfo
                    System.out.println("Choose which animal to monitor");
                    i = 0; // initialize to 0
                    while (animalInfo.hasNextLine()){ // while there is a line to read
                        animalLineInfo = animalInfo.nextLine();
                        if(animalLineInfo.contains("Details")) { // if line contains details add 1 to i
                            i++;
                            System.out.println(i + ". " + animalLineInfo); // print number and corresponding line for animal choices
                        }
                    }   monitorFile.close(); // close file scanner
                    
                    System.out.print("Which would you like to choose? ");
                    detailsUserInput = scnr.nextInt(); // animal details
                    System.out.println();
                    monitorFile = new FileInputStream("animal.txt"); // read from animal.txt file
                    animalSubInfo = new Scanner (monitorFile); // for submenu of animals
                    i = 0; // initialize i to 0
                    while (animalSubInfo.hasNextLine()) { // run while file has next line
                        animalLineInfo = animalSubInfo.nextLine(); // store line into variable
                        if(animalLineInfo.contains("Animal")) {
                            i++; // add one to next line and stop loop when lines run out
                        }
                        if ( i == detailsUserInput) {
                            keepRunning = true;
                            if(animalLineInfo.contains("*****")){
                                String alert = animalLineInfo.substring(5,animalLineInfo.length()); // Removes astericks
                                int length = alert.length();
                                for (int j = 0; j < length; j++) { // for loop to print while j is less than length
                                    System.out.print(alert);
                                }
                                System.out.println();
                            }
                            else if(!animalLineInfo.isEmpty()){ // if no astericks, print line as is
                                System.out.println(animalLineInfo);
                            }        
                        }
                    }
                    // if boolean is not true, print error and close file reader then break
                    if(keepRunning == false) {
                        System.out.println("Error");
                    }   
                    monitorFile.close(); // close file reader
                    System.out.println();
                    break;
                    
                    // if user chooses habitat, read from habitat.txt file
                case 2:
                    monitorFile = new FileInputStream("habitat.txt");
                    habitatInfo = new Scanner (monitorFile); 
                    System.out.println("Which habitat would you like to monitor?");
                    
                    // initialize i to 0 and run while loop
                    i = 0;
                    while (habitatInfo.hasNextLine()){
                        habitatLineInfo = habitatInfo.nextLine();
                        if(habitatLineInfo.contains("Details")) {
                            i++;
                            System.out.println(i + ". " + habitatLineInfo);
                        }
                    }
                    // close monitorFile when there are no more lines to read
                    monitorFile.close();
                    
                    System.out.print("Enter your selection: ");
                    detailsUserInput = scnr.nextInt();
                    System.out.println();
                    
                    // For sub menu of habitat
                    monitorFile = new FileInputStream("habitat.txt");
                    habitatSubInfo = new Scanner (monitorFile);
                    
                    // initialize i to 0 and run while loop
                    i = 0;
                    while (habitatSubInfo.hasNextLine()) {
                        habitatLineInfo = habitatSubInfo.nextLine();
                        if(habitatLineInfo.contains("Habitat")) {
                            i++;
                        }
                        if (i == detailsUserInput) {
                            keepRunning = true;
                            
                            // alert box for details containing astericks
                            if (habitatLineInfo.contains("*****")) {
                                String alert = habitatLineInfo.substring(5,habitatLineInfo.length()); // Removes astericks
                                
                                // set length to alert's length and loop through
                                int length = alert.length();
                                for (int j = 0; j< length; j++) {
                                    System.out.print(alert);
                                }
                                
                                System.out.println();
                            }
                            else if (!habitatLineInfo.isEmpty()) {
                                System.out.println(habitatLineInfo);
                            }
                        }
                    }       
                    // if keepRunning is no longer true, close monitorFile and break
                    if(keepRunning == false) {
                        System.out.println("Invalid");
                    }           
                    monitorFile.close();
                    System.out.println();
                    break;
                    
                // If user chooses to exit    
                case 3:
                    // 3 to exit
                    System.out.println("Goodbye");
                    // break outer
                    break OUTER;
                
                // default equates to printing invalid and break program.
                default:
                    System.out.println("Invalid");
                    break;
            }
        }
    
    }
}
