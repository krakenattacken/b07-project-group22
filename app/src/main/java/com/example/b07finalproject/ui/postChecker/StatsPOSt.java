package com.example.b07finalproject.ui.postChecker;

public class StatsPOSt extends POSt{
    public StatsPOSt(Grades statsGrades) {
        super(statsGrades);
    }

    @Override
    public boolean qualifiedForSpecialist() {
        if (! qualifiedForMinor()) {
            return false;
        }
        // A grade point average of at least 2.5 across the following 5 courses:
        // CSC A08, CSC/MATA67, MATA22, MATA31, MATA37
        return qualifyPOSt(STATCSP, 5, 2.5);
    }

    public boolean qualifiedForMachine() {
        String[] thresholdCourses = {"CSC A48"};

        if (! qualifiedForMinor()) {
            return false;
        }

        return qualifyPOSt(MACHSP, 6, 2.5,
                thresholdCourses, 73, 1);
    }

    @Override
    public boolean qualifiedForMajor() {
        if (! qualifiedForMinor()) {
            return false;
        }
        // A grade point average of at least 2.3 across the following four courses:
        // CSC A08/A20, MATA22, MATA30/A31, MATA36/A37
        return qualifyPOSt(STATCMAJ, 4, 2.3);
    }

    @Override
    public boolean qualifiedForMinor() {

        return canApply(STATC, 5, 4.0);
    }
}
