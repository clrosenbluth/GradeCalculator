/*An application to calculate grades for students in a class, allowing for multiple students to have
* different numbers of grades.*/

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class GradeCalculator {
    public static void main(String[] args) throws FileNotFoundException {
        // Make scanner
        Scanner keyboard = new Scanner(System.in);

        // Get number students
        int numStudents = getNumStudents(keyboard);

        // Get student names
        // Set up array to store them
        String[] studentNames = getStudentNames(numStudents, keyboard);

        // Get scores for each student
        int[][] studentScores = getStudentScores(numStudents, keyboard, studentNames);

        // Get letter grade for each student
        char[] grades = getGrades(studentScores);

        // Create the file "grades.txt" (not using FileWriter because I don't want to have multiple entries)
        PrintWriter gradesFile = new PrintWriter("grades.txt");

        // Add individual stats for each trainee
        addGrades(gradesFile, studentNames, grades);

        /*// Print results
        System.out.println("\nFinal grades: ");
        for(int i = 0; i < grades.length; i++)
        {
            System.out.println(studentNames[i] + ": " + grades[i]);
        }*/

        closures(keyboard, gradesFile);

    }

    private static int getNumStudents(Scanner keyboard) {
        // Get the number of students
        System.out.println("Please enter the number of students");

        // Make sure an integer is entered
        int numStudents = 0;
        while(true)
        {
            String numStudentsString = keyboard.nextLine();
            try
            {
                numStudents = Integer.parseInt(numStudentsString);
            }
            catch(Exception e)
            {
                System.out.println("Encountered an error, please try again");
                continue;
            }
            break;
        }

        return numStudents;
    }

    private static String[] getStudentNames(int numStudents, Scanner keyboard) {
        String[] studentNames = new String[numStudents];

        // Fill the array
        for(int i = 0; i < numStudents; i++)
        {
            System.out.println("Please enter the name for student " + (i+1));
            studentNames[i] = keyboard.nextLine();
        }

        return studentNames;
    }

    private static int[][] getStudentScores(int numStudents, Scanner keyboard, String[] studentNames) {
        int[][] studentScores = new int[numStudents][];

        String numberScoresString;
        int numberScores = 0;

        for(int ix = 0; ix < numStudents; ix++)
        {
            // Get number (and make sure it's valid - a student cannot have zero or negative grades)
            do {
                System.out.println("Please enter the number of grades for " + studentNames[ix]);
                numberScoresString = keyboard.nextLine();
                try
                {
                    numberScores = Integer.parseInt(numberScoresString);
                }
                catch(Exception e)
                {
                    System.out.println("Encountered an error, please try again");
                }
                if (numberScores < 1)
                {
                    System.out.println("Invalid number of scores, please try again");
                }

            } while(numberScores < 1);

            // Finish setting up array
            studentScores[ix] = new int[numberScores];

            // Get grades for each student (check parameter for the range of possible scores can be added by teachers
            // on an individual basis)
            for(int iy = 0; iy < numberScores; iy++)
            {
                System.out.println("Please enter score number " + (iy+1) + " for " + studentNames[ix]);
                String scoreString = keyboard.nextLine();
                int score = 0;
                while(true)
                {
                    try
                    {
                        score = Integer.parseInt(scoreString);
                        break;
                    }
                    catch(Exception e)
                    {
                        System.out.println("Encountered an error, please try again");
                    }
                }
                studentScores[ix][iy] = score;
            }
        }

        return studentScores;
    }

    private static char[] getGrades(int[][] studentScores) {
        char[] grades = new char[studentScores.length];

        for(int ix = 0; ix < studentScores.length; ix++)
        {
            grades[ix] = calcGrade(studentScores[ix]);
        }

        return grades;
    }

    public static char calcGrade(int[] grades){
        // Sum grades
        int gradeSum = 0;
        for (int grade : grades) {
            gradeSum += grade;
        }

        // Calculate average
        int gradeAvg = gradeSum / grades.length;

        char letter;

        // Convert average to letter grade and append to list - teachers can change the range limits given here
        if (gradeAvg >= 90)
        {
            letter = 'A';
        }
        else if (gradeAvg >= 80)
        {
            letter = 'B';
        }
        else if (gradeAvg >= 65)
        {
            letter = 'C';
        }
        else if (gradeAvg >= 50)
        {
            letter = 'D';
        }
        else
        {
            letter = 'F';
        }

        return letter;
    }

    private static void addGrades(PrintWriter gradesFile, String[] studentNames, char[] grades) {
        // Print header
        gradesFile.println("Final grades: ");

        for(int i = 0; i < grades.length; i++)
        {
            gradesFile.printf("%s: %c\n", studentNames[i], grades[i]);
        }
    }

    private static void closures(Scanner keyboard, PrintWriter gradesFile) {
        keyboard.close();
        gradesFile.close();
    }

}
