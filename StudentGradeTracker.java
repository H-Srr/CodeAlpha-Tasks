package Tasks;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentGradeTracker {
	private ArrayList<Double> grades;

    public StudentGradeTracker() {
        grades = new ArrayList<>();
    }

    public void addGrade(double grade) {
        grades.add(grade);
    }
    public double computeAverage() {
        if (grades.isEmpty()) {
            return 0.0;
        }
        double sum = 0.0;
        for (double grade : grades) {
            sum += grade;
        }
        return sum / grades.size();
    }
    public double HighestGrade() {
        if (grades.isEmpty()) {
            return 0.0;
        }
        double highest = grades.get(0);
        for (double grade : grades) {
            if (grade > highest) {
                highest = grade;
            }
        }
        return highest;
    }

    public double LowestGrade() {
        if (grades.isEmpty()) {
            return 0.0;
        }
        double lowest = grades.get(0);
        for (double grade : grades) {
            if (grade < lowest) {
                lowest = grade;
            }
        }
        return lowest;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        StudentGradeTracker tracker = new StudentGradeTracker();
        
        System.out.println("Enter grades (type 'end' to finish):");

        while (true) {
            String text = input.nextLine();
            if (text.equalsIgnoreCase("end")) {
                break;
            }
            try {
                double grade = Double.parseDouble(text);
                tracker.addGrade(grade);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid grade.");
            }
        }
        System.out.printf("Highest Grade: %.2f\n", tracker.HighestGrade());
        System.out.printf("Lowest Grade: %.2f\n", tracker.LowestGrade());
        System.out.printf("Average Grade: %.2f\n", tracker.computeAverage());
        input.close();
    }
}
