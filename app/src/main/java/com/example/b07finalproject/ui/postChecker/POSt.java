package com.example.b07finalproject.ui.postChecker;

public abstract class POSt implements POStRequirements{

    Grades myGrades;

    public POSt(Grades myGrades) {
        this.myGrades = myGrades;
    }

    public abstract boolean qualifiedForSpecialist();
    public abstract boolean qualifiedForMajor();

    public abstract boolean qualifiedForMinor();

    public boolean[] qualifiedPOSts() {
        // return which POSt you're qualified
        // returns array of boolean where
        // index0: if qualified for minor
        // index1: if qualified for major
        // index2: if qualified for specialist

        boolean[] qualification = new boolean[3];
        qualification[0] = this.qualifiedForMinor();
        qualification[1] = this.qualifiedForMajor();
        qualification[2] = this.qualifiedForSpecialist();

        return qualification;
    }

    public boolean meetThreshold(String[] thresholdCourses,
                                 int threshold, int numRequired) {
        int count = 0;
        for (int i = 0; i < myGrades.courses.length; i++) {
            if (belongToCourses(myGrades.courses[i], thresholdCourses)){
                if (myGrades.grades[i] >= threshold) {
                    count++;
                }
                if (count >= numRequired) {
                    return true;
                }
            }

        }
        return false;
    }

    public boolean meetGPAReq(Grades programGrades, double thresholdGPA) {
        // A grade point average of at least thresholdGPA across the following four courses:
        // Courses
        if (programGrades.myGPA() < thresholdGPA) {
            return false;
        }
        return true;
    }

    public boolean belongToCourses(String course, String[] programCourses) {
        for(int i = 0; i < programCourses.length; i++) {
            if(course.equals(programCourses[i])) {
                return true;
            }
        }
        return false;
    }

    public Grades myGradesForPOSt(String[] programCourses, int numRequired) {
        String[] courses = new String[numRequired];
        int[] grades = new int[numRequired];
        int count = 0;

        for (int i = 0; i < myGrades.courses.length; i++) {
            if (belongToCourses(myGrades.courses[i], programCourses)) {
                grades[count] = myGrades.grades[i];
                courses[count] = myGrades.courses[i];
                count++;
            }
        }

        // return null if didn't take required num of courses
        if (count < numRequired) {
            return null;
        }

        return new Grades(courses, grades, myGrades.credits);
    }

    // When qualification is just completing the courses & credits
    public boolean canApply(String[] programCourses, int numCoursesRequired,
                            double creditsRequired){
        // need to pass all the required courses (50+) and more than 4.0 credits
        if (myGrades.credits < creditsRequired) {
            return false;
        }

        Grades myGrade = myGradesForPOSt(programCourses, numCoursesRequired);

        if (myGrade == null) {
            return false;
        }

        for (int i = 0; i < myGrade.grades.length; i++) {
            if (myGrade.grades[i] < 50) {
                return false;
            }
        }
        return true;
    }

    // When qualification is achieving certain GPA among certain courses
    public boolean qualifyPOSt(String[] programCourses, int numCoursesRequired,
                               double thresholdGPA){

        Grades programGrades = myGradesForPOSt(programCourses, numCoursesRequired);
        if (programGrades == null) {
            return false;
        }

        return meetGPAReq(programGrades, thresholdGPA);
    }

    // When qualification is completing the courses, credits, GPA, and meeting threshold for some
    // courses
    public boolean qualifyPOSt(String[] programCourses, int numCoursesRequired,
                               double thresholdGPA,
                               String[] thresholdCourses, int threshold, int numRequired) {
        if (! qualifyPOSt(programCourses, numCoursesRequired, thresholdGPA)) {
            return false;
        }

        return meetThreshold(thresholdCourses, threshold, numRequired);
    }

    // When qualification is completing the courses, credits, GPA, and meeting 2 thresholds for some
    // courses
    public boolean qualifyPOSt(String[] programCourses, int numCoursesRequired,
                               double thresholdGPA,
                               String[] thresholdCourses1, int threshold1, int numRequired1,
                               String[] thresholdCourses2, int threshold2, int numRequired2) {
        if (! qualifyPOSt(programCourses, numCoursesRequired, thresholdGPA,
                thresholdCourses1, threshold1, numRequired1)) {
            return false;
        }

        return meetThreshold(thresholdCourses2, threshold2, numRequired2);
    }
}
