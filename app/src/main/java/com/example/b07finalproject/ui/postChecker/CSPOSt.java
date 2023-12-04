package com.example.b07finalproject.ui.postChecker;

public class CSPOSt extends POSt{
    public CSPOSt(Grades csGrades) {
        super(csGrades);
    }

    @Override
    public boolean qualifiedForSpecialist() {
        // First check if eligible to apply
        if (! qualifiedForMinor()) {
            return false;
        }
        // A grade point average of at least 2.5 across the following five courses:
        // CSC/MAT A67, CSC A48, MAT A22, MAT A31, MAT A37.(CSCSP)
        // A grade of at least B(73) in 1 of CSC A48
        String[] thresholdCourses1 = {"CSC A48"};
        // A grade of at least C-(60) in two of
        // CSC/MAT A67, MAT A22, MAT A37
        String[] thresholdCourses2 = {"CSC A67", "MAT A67", "MAT A22", "MAT A37"};
        return qualifyPOSt(CSCSP, 5,
                2.5, thresholdCourses1, 73, 1,
                thresholdCourses2, 60, 2);
    }

    @Override
    public boolean qualifiedForMajor() {
        return qualifiedForSpecialist();
    }

    @Override
    public boolean qualifiedForMinor() {

        return canApply(CSC, 6, 4.0);
    }

}
