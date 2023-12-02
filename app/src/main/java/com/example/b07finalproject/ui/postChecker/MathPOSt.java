package com.example.b07finalproject.ui.postChecker;

public class MathPOSt extends POSt{
    public MathPOSt(Grades mathGrades) {
        super(mathGrades);
    }

    @Override
    public boolean qualifiedForSpecialist() {

        // A grade point average of at least 2.5 across the following 4 courses:
        // CSC/MAT A67, MAT A22, MAT A31, MAT A37
        // A grade of at least B(73) in 2 of CSC/MAT A67, MAT A22, MAT A37
        String[] thresholdCourses = {"CSC/MAT A67", "MAT A22", "MAT A37"};

        if (! qualifiedForMinor()) {
            return false;
        }

        return qualifyPOSt(MATCSP, 4,
                2.5, thresholdCourses,73, 2);
    }

    @Override
    public boolean qualifiedForMajor() {

        // A grade point average of at least 2.0 across the following 4 courses:
        // CSC/MAT A67, MAT A22, MAT A31, MAT A37
        // A grade of at least B(73) in 1 of CSC/MAT A67, MAT A22, MAT A37
        String[] thresholdCourses = {"CSC/MAT A67", "MAT A22", "MAT A37"};

        if (! qualifiedForMinor()) {
            return false;
        }

        return qualifyPOSt(MATCMAJ, 4, 2.0,
                thresholdCourses,73, 1);
    }

    @Override
    public boolean qualifiedForMinor() {
        return canApply(MATC, 5, 4.0);
    }
}
