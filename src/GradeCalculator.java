/*An application to calculate grades for students in a class, allowing for multiple students to have
* different numbers of grades.*/

import java.io.FileNotFoundException;
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

        // Print results
        System.out.println("\nFinal grades: ");
        for(int i = 0; i < grades.length; i++)
        {
            System.out.println(studentNames[i] + ": " + grades[i]);
        }

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
            // Get number
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

            } while(numberScores < 1);

            // Finish setting up array
            studentScores[ix] = new int[numberScores];

            // Get grades for each student
            for(int iy = 0; iy < numberScores; iy++)
            {
                System.out.println("Please enter score number " + (iy+1) + " for " + studentNames[ix]);
                String scoreString = keyboard.nextLine();
                int score = Integer.parseInt(scoreString);
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

        // Convert average to letter grade and append to list
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

}
