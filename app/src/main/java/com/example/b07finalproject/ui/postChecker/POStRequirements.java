package com.example.b07finalproject.ui.postChecker;

public interface POStRequirements {
    // courses required for cs
    String[] CSC = {"CSC A08", "CSC A48", "CSC/MAT A67",
            "MAT A22", "MAT A23", "MAT A30", "MAT A31", "MAT A32", "MAT A37"};
    // num of csc required
    //int NUM_CSC = 6;
    // courses required for cs major
    String[] CSCMAJ = {"CSC A48", "CSC/MAT A67",
            "MAT A22", "MAT A31", "MAT A37"};
    // num of csc required for cs major
    int NUM_CSCMAJ = 5;
    // courses required fpr cs specialist
    String[] CSCSP = {"CSC A48", "CSC/MAT A67",
            "MAT A22", "MAT A31", "MAT A37"};
    // num of csc required for cs specialist
    //int NUM_CSCSP = 5;
    // courses that require a certain grade to make specialist
    //String[] CSCSP_THRESH1 = {"CSC A48"};
    // courses that require a certain grade to make specialist
    //String[] CSCSP_THRESH2 = {"CSC A67", "MAT A67", "MAT A22", "MAT A37"};

    String[] MATC = {"CSC A08", "CSC A20", "CSC/MAT A67",
            "MAT A22", "MAT A23", "MAT A30", "MAT A31", "MAT A32", "MAT A37"};
    //int NUM_MATC = 5;

    String[] MATCMAJ = {"CSC/MAT A67",
            "MAT A22", "MAT A31", "MAT A37"};
    //int NUM_MATCMAJ = 4;

    String[] MATCSP = {"CSC/MAT A67",
            "MAT A22", "MAT A31", "MAT A37"};
    //int NUM_MATCSP = 4;

    String[] STATC = {"CSC A08", "CSC A20", "CSC/MAT A67",
            "MAT A22", "MAT A23", "MAT A30", "MAT A31", "MAT A32", "MAT A36", "MAT A37"};
    //int NUM_STATC = 5;

    String[] STATCMAJ = {"CSC A08", "CSC A20",
            "MAT A22", "MAT A30", "MAT A31", "MAT A36", "MAT A37"};
    //int NUM_STATCMAJ = 4;

    String[] STATCSP = {"CSC A08", "CSC/MAT A67",
            "MAT A22", "MAT A31", "MAT A37"};
    //int NUM_STATCSP = 5;

    String[] MACHSP = {"CSC A08", "CSC/MAT A67",
            "MAT A22", "MAT A31", "MAT A37", "CSC A48"};

}
