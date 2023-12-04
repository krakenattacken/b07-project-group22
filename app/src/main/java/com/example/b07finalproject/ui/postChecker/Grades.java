package com.example.b07finalproject.ui.postChecker;

public class Grades implements GradeToGPA{

    String[] courses;
    int[] grades;
    double credits;

    public Grades() {
    }

    public Grades(String[] courses, int[] grades, double credits){
        if (grades.length != courses.length) {
            throw new GradeException("Number of courses doesn't match number of grades!");
        }

        for (int i = 0; i < grades.length; i++) {
            if (grades[i] > 100) {
                throw new GradeException("Grades cannot exceed 100!");
            }
        }
        this.courses = courses;
        this.grades = grades;
        this.credits = credits;
    }

    @Override
    public double toGPA(int grade) {
        double gpa;
        if (grade >= 85) {
            gpa = 4.0;
        }
        else if (grade >= 80) {
            gpa = 3.7;
        }
        else if (grade >= 77) {
            gpa = 3.3;
        }
        else if (grade >= 73) {
            gpa = 3.0;
        }
        else if (grade >= 70) {
            gpa = 2.7;
        }
        else if (grade >= 67) {
            gpa = 2.3;
        }
        else if (grade >= 63) {
            gpa = 2.0;
        }
        else if (grade >= 60) {
            gpa = 1.7;
        }
        else if (grade >= 57) {
            gpa = 1.3;
        }
        else if (grade >= 53) {
            gpa = 1.0;
        }
        else if (grade >= 50) {
            gpa = 0.7;
        }
        else {
            gpa = 0;
        }

        return gpa;
    }

    public double myGPA() {

        int numCourses = grades.length;
        double GPASum = 0;

        for (int i = 0; i < numCourses; i++) {
            GPASum += toGPA(grades[i]);
        }
        return GPASum/(double) numCourses;
    }

}
