import java.io.*;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

/**
 *
 * CSCU9T4 Java strings and files exercise.
 *
 */

public class FilesInOut2 {
    public static void main(String[] args) {
        FilesInOut2 f = new FilesInOut2();
        try {
            System.out.println("Enter a file name for input:");
            Scanner s = new Scanner(System.in);
            File inputFile = new File(s.nextLine());
            if (!inputFile.exists() || inputFile.isDirectory()){
                System.out.println("Please provide a valid input file name");
                return;
            }

            System.out.println("Enter a file name for output:");
            File outputFile = new File(s.nextLine());
            if (outputFile.exists() || outputFile.isDirectory()){
                System.out.println("Please provide a valid output file name: A directory with same name exists");
                return;
            }

            boolean toUpper = false;
            System.out.println("Do you want everything in upper case?");
            String ans = s.nextLine();

            if (ans.equals("y") || ans.equals("Y")){
                toUpper = true;
            }

            f.readFile(inputFile, outputFile, toUpper);

        } catch (Exception e){
            System.out.println(e);
        }
    } // main

    public void readFile(File input, File output, boolean option){
        try {
            if (input.exists()) {
                if (input.canRead()){
                    Scanner myReader = new Scanner(input);
                    int SIZE;
                    if (option) {
                        SIZE = 14;
                    } else {
                        SIZE = 15;
                    }
                    String[] arr = new String[SIZE];
                    int i = 0;
                    while (myReader.hasNextLine()) {
                        String data = myReader.nextLine();
                        //System.out.println(data);
                        String[] result = data.split("\\s+");

                        if (result.length <=3){
                            String fName = result[0];
                            String lName = result[1];
                            if (option){
                                fName = fName.toUpperCase();
                                lName = lName.toUpperCase();
                            } else {
                                fName = fName.substring(0, 1).toUpperCase() + fName.substring(1);
                                lName = lName.substring(0, 1).toUpperCase() + lName.substring(1);
                            }

                            String day = result[2].substring(0,2);
                            String month = result[2].substring(2,4);
                            String year = result[2].substring(4,8);

                            String date = day + "/" + month + "/" + year;

                            String msg = fName + " " + lName + " " + date;

                            arr[i] = msg;
                        } else if (result.length > 3){
                            String fName = result[0];
                            String mName = result[1];
                            String lName = result[2];
                            if (option){
                                fName = fName.toUpperCase();
                                mName = mName.toUpperCase();
                                lName = lName.toUpperCase();
                            } else {
                                fName = fName.substring(0, 1).toUpperCase() + fName.substring(1);
                                mName = mName.toUpperCase();
                                lName = lName.substring(0, 1).toUpperCase() + lName.substring(1);
                            }

                            String day = result[3].substring(0,2);
                            String month = result[3].substring(2,4);
                            String year = result[3].substring(4,8);

                            String date = day + "/" + month + "/" + year;

                            String msg = fName + " " + mName + " " + lName + " " + date;

                            arr[i] = msg;
                        }
                        i++;
                    }
                    createFile(arr, output);
                    myReader.close();
                } else {
                    System.out.println("The input file you have entered is not readable!\nPlease enter a readable input file!");
                }
            } else {
                System.out.println("Your input file doesnt exist!\nPlease enter a valid input file that exists!");
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            System.out.println("\n" + e);
        }
    }

    private void createFile (String[] arr, File output){
        try {
            if (output.createNewFile()) {
                System.out.println("File created: " + output.getName());
                BufferedWriter outputWriter = null;
                outputWriter = new BufferedWriter(new FileWriter(output));
                for (int i = 0; i < arr.length; i++) {
                    outputWriter.write(arr[i]+"");
                    outputWriter.newLine();
                }
                outputWriter.flush();
                outputWriter.close();
                System.out.println("Successfully wrote to the file.");
            } else {
                System.out.println("File already exists.");
                System.out.println("Do You want to replace the current file called " + output + "?\n('y' for yes or 'n' for no)");
                Scanner s = new Scanner(System.in);
                String ans = s.nextLine();
                if (ans.equals("y") || ans.equals("Y")) {
                    if (output.delete()) {
                        System.out.println("Deleted the file: " + output.getName());
                        createFile(arr, output);
                    } else {
                        System.out.println("Failed to delete the file.");
                    }
                } else {
                    System.out.println("The file won't be replaced");
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
