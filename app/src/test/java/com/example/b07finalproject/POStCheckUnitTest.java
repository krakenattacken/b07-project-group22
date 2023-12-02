package com.example.b07finalproject;

import static com.example.b07finalproject.ui.postChecker.POStRequirements.*;


import com.example.b07finalproject.ui.postChecker.CSPOSt;
import com.example.b07finalproject.ui.postChecker.GradeException;
import com.example.b07finalproject.ui.postChecker.Grades;
import com.example.b07finalproject.ui.postChecker.MathPOSt;
import com.example.b07finalproject.ui.postChecker.POSt;
import com.example.b07finalproject.ui.postChecker.POStRequirements;
import com.example.b07finalproject.ui.postChecker.StatsPOSt;

import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class POStCheckUnitTest {

    Grades myGrades;

    @Test
    public void gradeTest1() {
        // courses and grades have different length
        String[] courses = {""};
        int[] grades = {};
        double credits = 0;
        assertThrows(GradeException.class, () -> {
            myGrades = new Grades(courses, grades, credits);

            throw new GradeException("Number of courses doesn't match number of grades!");
        });
    }

    @Test
    public void gradeTest2() {
        // grades contain value over 100
        String[] courses = {"MAT A31", "CSC A08"};
        int[] grades = {100, 101};
        double credits = 0;
        assertThrows(GradeException.class, () -> {
            myGrades = new Grades(courses, grades, credits);

            throw new GradeException("Grades cannot exceed 100!");
        });
    }

    @Test
    public void gpaTest() {
        // test if GPA for required courses are calculated when the original grades contain
        // other courses not in Math program
        // supposed to take grades from MAT A31, MAT A37, MAT A22,CSC/MAT A67 hence
        // 99, 40, 88, 70 -> 4.0, 0, 4.0, 2.7 -> 2.675
        String[] courses = {"MAT A31", "CSC C37","CSC A08", "MAT A37",
                "CSC A48","MAT A22", "CSC/MAT A67"};
        int[] grades = {99, 45, 90, 40, 66, 88, 70};
        double credits = 0;
        myGrades = new Grades(courses, grades, credits);
        MathPOSt mathPOSt = new MathPOSt(myGrades);

        Grades gradesForPost = mathPOSt.myGradesForPOSt(MATCMAJ, 4);

        double myGPA = gradesForPost.myGPA();
        double expectedGPA = 2.675;

        assertEquals(expectedGPA, myGPA, 0.00001);
    }

    @Test
    public void mathTest1() {
        // empty MathPOSt
        String[] courses = {};
        int[] grades = {};
        double credits = 0;
        myGrades = new Grades(courses, grades, credits);
        MathPOSt mathPOSt = new MathPOSt(myGrades);
        boolean[] result = mathPOSt.qualifiedPOSts();
        boolean[] expectedResult = {false, false, false};

        assertArrayEquals(expectedResult, result);
    }

    @Test
    public void mathTest2() {
        // Less than 5 math courses (Not even qualified for applying POSt) But credit over 4.0,
        // grades are all 100
        String[] courses = {"MAT A31", "CSC A08", "CSC/MAT A67", "MAT A22"};

        int[] grades = {100, 100, 100, 100};
        double credits = 4.0;
        myGrades = new Grades(courses, grades, credits);
        MathPOSt mathPOSt = new MathPOSt(myGrades);
        boolean[] result = mathPOSt.qualifiedPOSts();
        boolean[] expectedResult = {false, false, false};

        assertArrayEquals(expectedResult, result);
    }
    @Test
    public void mathTest3() {
        // Took all 5 math courses (includes 4 major requirement courses), credit over 4.0, and
        // GPA among required courses is above 2.0 and 73 + for some required courses but failed
        // one course (hence can't apply)
        // will not make minor, major, nor specialist
        String[] courses = {"CSC A08", "CSC/MAT A67", "MAT A22", "MAT A31",
                "MAT A37"};

        int[] grades = {100, 100, 100, 100, 5};
        double credits = 4.0;
        myGrades = new Grades(courses, grades, credits);
        MathPOSt mathPOSt = new MathPOSt(myGrades);
        boolean[] result = mathPOSt.qualifiedPOSts();
        boolean[] expectedResult = {false, false, false};

        assertArrayEquals(expectedResult, result);
    }


    @Test
    public void mathTest4() {
        // Passed all 5 math courses (includes 4 major requirement courses
        // along with some other courses), credit over 4.0, and
        // GPA among all courses are above 2.0 but GPA among the requirement courses for
        // math major is less than 2.0 (test that it can have unrelated courses)
        // will make minor but not major nor specialist
        String[] courses = {"CSC A08", "CSC/MAT A67", "MAT A22", "MAT A31", "LIN A01",
                "MAT A37", "CSC C37"};

        int[] grades = {63, 63, 63, 63, 75, 60, 63};
        double credits = 4.0;
        myGrades = new Grades(courses, grades, credits);
        MathPOSt mathPOSt = new MathPOSt(myGrades);
        boolean[] result = mathPOSt.qualifiedPOSts();
        boolean[] expectedResult = {true, false, false};

        assertArrayEquals(expectedResult, result);
    }

    @Test
    public void mathTest5() {
        // Passed all 5 math courses (includes 4 major requirement courses),
        // and gpa above 2.5 for required courses, along with
        // 73+ on some required courses but credit less 4.0
        // will not make minor, major, nor specialist
        String[] courses = {"CSC A08", "CSC/MAT A67", "MAT A22", "MAT A31",
                "MAT A37"};

        int[] grades = {100, 100, 100, 100, 100};
        double credits = 3.5;
        myGrades = new Grades(courses, grades, credits);
        MathPOSt mathPOSt = new MathPOSt(myGrades);
        boolean[] result = mathPOSt.qualifiedPOSts();
        boolean[] expectedResult = {false, false, false};

        assertArrayEquals(expectedResult, result);
    }

    @Test
    public void mathTest6() {
        // Passed all 5 math courses (includes 4 major requirement courses),
        // credit above 4.0, and gpa above 2.5 for required courses,
        // along with 73+ for 1 of the required courses
        // will make minor, major, but not specialist
        String[] courses = {"CSC A08", "CSC/MAT A67", "MAT A22", "MAT A31",
                "MAT A37"};

        int[] grades = {100, 70, 70, 100, 100};
        double credits = 4.0;
        myGrades = new Grades(courses, grades, credits);
        MathPOSt mathPOSt = new MathPOSt(myGrades);
        boolean[] result = mathPOSt.qualifiedPOSts();
        boolean[] expectedResult = {true, true, false};

        assertArrayEquals(expectedResult, result);
    }

    @Test
    public void mathTest7() {
        // Passed all 5 math courses (includes 4 major requirement courses),
        // credit above 4.0, and 73+ for 2+ of the required courses
        // but 2.0 < gpa < 2.5
        // will make minor, major, but not specialist
        String[] courses = {"CSC A08", "CSC/MAT A67", "MAT A22", "MAT A31",
                "MAT A37"};

        int[] grades = {100, 73, 73, 55, 50};
        double credits = 4.0;
        myGrades = new Grades(courses, grades, credits);
        MathPOSt mathPOSt = new MathPOSt(myGrades);
        boolean[] result = mathPOSt.qualifiedPOSts();
        boolean[] expectedResult = {true, false, false};

        assertArrayEquals(expectedResult, result);
    }

    @Test
    public void mathTest8() {
        // Passed all 5 math courses (includes 4 major requirement courses),
        // credit above 4.0, and gpa above 2.5 for required courses,
        // along with 73+ for 2+ of the required courses
        // will make minor, major, and specialist
        String[] courses = {"CSC A08", "CSC/MAT A67", "MAT A22", "MAT A31",
                "MAT A37"};

        int[] grades = {50, 73, 73, 67, 67};
        double credits = 4.0;
        myGrades = new Grades(courses, grades, credits);
        MathPOSt mathPOSt = new MathPOSt(myGrades);
        boolean[] result = mathPOSt.qualifiedPOSts();
        boolean[] expectedResult = {true, true, true};

        assertArrayEquals(expectedResult, result);
    }

    @Test
    public void mathTest9() {
        // Passed all 5 math courses, credit above 4.0 but took MAT A23 instead of MAT A22
        // will make minor, but not major nor specialist
        String[] courses = {"CSC A08", "CSC/MAT A67", "MAT A23", "MAT A31",
                "MAT A37"};

        int[] grades = {100, 100, 100, 100, 100};
        double credits = 4.0;
        myGrades = new Grades(courses, grades, credits);
        MathPOSt mathPOSt = new MathPOSt(myGrades);
        boolean[] result = mathPOSt.qualifiedPOSts();
        boolean[] expectedResult = {true, false, false};

        assertArrayEquals(expectedResult, result);
    }

    @Test
    public void mathTest10() {
        // Will make CS Specialist but not Math Specialist nor Math Major
        String[] courses = {"CSC A08", "CSC/MAT A67", "MAT A22", "MAT A31",
                "MAT A37", "CSC A48"};

        int[] grades = {50, 67, 67, 67, 67, 100};
        double credits = 4.0;
        myGrades = new Grades(courses, grades, credits);
        MathPOSt mathPOSt = new MathPOSt(myGrades);
        boolean[] result = mathPOSt.qualifiedPOSts();
        boolean[] expectedResult = {true, false, false};

        assertArrayEquals(expectedResult, result);
    }

    @Test
    public void mathTest11() {
        // Passed Specialist for Stats but does not make Math Specialist nor Math Major
        String[] courses = {"CSC A08", "CSC/MAT A67", "MAT A22", "MAT A31",
                "MAT A37", "CSC A48"};

        int[] grades = {50, 67, 67, 67, 67, 100};
        double credits = 4.0;
        myGrades = new Grades(courses, grades, credits);
        MathPOSt mathPOSt = new MathPOSt(myGrades);
        boolean[] result = mathPOSt.qualifiedPOSts();
        boolean[] expectedResult = {true, false, false};

        assertArrayEquals(expectedResult, result);
    }

    @Test
    public void csTest1() {
        // empty CSPOSt
        String[] courses = {};
        int[] grades = {};
        double credits = 0;
        myGrades = new Grades(courses, grades, credits);
        CSPOSt csPOSt = new CSPOSt(myGrades);
        boolean[] result = csPOSt.qualifiedPOSts();
        boolean[] expectedResult = {false, false, false};

        assertArrayEquals(expectedResult, result);
    }

    @Test
    public void csTest2() {
        // Less than 6 cs courses (Not even qualified for applying POSt) But credit over 4.0,
        // grades are all 100
        String[] courses = {"MAT A31", "CSC A08", "CSC/MAT A67", "MAT A22"};

        int[] grades = {100, 100, 100, 100};
        double credits = 4.0;
        myGrades = new Grades(courses, grades, credits);
        CSPOSt csPOSt = new CSPOSt(myGrades);
        boolean[] result = csPOSt.qualifiedPOSts();
        boolean[] expectedResult = {false, false, false};

        assertArrayEquals(expectedResult, result);
    }
    @Test
    public void csTest3() {
        // Took all 6 cs courses (includes 5 major requirement courses), credit over 4.0, and
        // GPA among required courses is above 2.5 and 73 + for CSC A48, 60+ in 2 of required
        // courses but failed in one of the required courses hence can't apply
        // will not make minor, major, nor specialist
        String[] courses = {"CSC A08", "CSC/MAT A67", "MAT A22", "MAT A31",
                "MAT A37", "CSC A48"};

        int[] grades = {100, 100, 100, 100, 5, 100};
        double credits = 4.0;
        myGrades = new Grades(courses, grades, credits);
        CSPOSt csPOSt = new CSPOSt(myGrades);
        boolean[] result = csPOSt.qualifiedPOSts();
        boolean[] expectedResult = {false, false, false};

        assertArrayEquals(expectedResult, result);
    }

    @Test
    public void csTest4() {
        // Passed all 6 cs courses (includes 5 major requirement courses
        // along with some other courses), credit over 4.0, and
        // GPA among all courses are above 2.5 but GPA among the requirement courses for
        // cs major is less than 2.5
        // will make minor but not major nor specialist
        String[] courses = { "CSC A08", "CSC/MAT A67", "MAT A22", "MAT A31", "LIN A01",
                "MAT A37", "CSC C37", "CSC A48"};

        int[] grades = {70, 70, 70, 70, 70, 60, 80, 57};
        double credits = 4.0;
        myGrades = new Grades(courses, grades, credits);
        CSPOSt csPOSt = new CSPOSt(myGrades);
        boolean[] result = csPOSt.qualifiedPOSts();
        boolean[] expectedResult = {true, false, false};

        assertArrayEquals(expectedResult, result);
    }

    @Test
    public void csTest5() {
        // Passed all 6 cs courses (includes 5 major requirement courses), and gpa above 2.5
        // for required courses, along with 73 + for CSC A48, 60+ in 2 of required courses
        // but credit below 4.0 so can't apply
        // will not make minor, major, nor specialist
        String[] courses = { "CSC A08", "CSC/MAT A67", "MAT A22", "MAT A31",
                "MAT A37", "CSC A48"};

        int[] grades = {100, 100, 100, 100, 100, 100};
        double credits = 3.5;
        myGrades = new Grades(courses, grades, credits);
        CSPOSt csPOSt = new CSPOSt(myGrades);
        boolean[] result = csPOSt.qualifiedPOSts();
        boolean[] expectedResult = {false, false, false};

        assertArrayEquals(expectedResult, result);
    }

    @Test
    public void csTest6() {
        // Passed all 6 cs courses (includes 5 major requirement courses), credit above 4.0,
        // and gpa above 2.5 for required courses, along with 73 + for CSC A48,
        // 60+ in 1 of required courses
        // will make minor, but not major nor specialist
        String[] courses = { "CSC A08", "CSC/MAT A67", "MAT A22", "MAT A31",
                "MAT A37", "CSC A48"};

        int[] grades = {100, 59, 59, 100, 100, 100};
        double credits = 4.0;
        myGrades = new Grades(courses, grades, credits);
        CSPOSt csPOSt = new CSPOSt(myGrades);
        boolean[] result = csPOSt.qualifiedPOSts();
        boolean[] expectedResult = {true, false, false};

        assertArrayEquals(expectedResult, result);
    }

    @Test
    public void csTest7() {
        // Passed all 6 cs courses (includes 5 major requirement courses), credit above 4.0,
        // and 73 + for CSC A48, 60+ in 2 of required courses but gpa < 2.5
        // will make minor, but not major nor specialist
        String[] courses = { "CSC A08", "CSC/MAT A67", "MAT A22", "MAT A31",
                "MAT A37", "CSC A48"};

        int[] grades = {67, 67, 67, 67, 67, 73};
        double credits = 4.0;
        myGrades = new Grades(courses, grades, credits);
        CSPOSt csPOSt = new CSPOSt(myGrades);
        boolean[] result = csPOSt.qualifiedPOSts();
        boolean[] expectedResult = {true, false, false};

        assertArrayEquals(expectedResult, result);
    }

    @Test
    public void csTest8() {
        // Passed all 6 math courses (includes 5 major requirement courses), credit above 4.0,
        // and 73 + for CSC A48, 60+ in 2 of required courses, and GPA above 2.5
        // among required courses
        // will make minor, major, and specialist
        String[] courses = { "CSC A08", "CSC/MAT A67", "MAT A22", "MAT A31",
                "MAT A37", "CSC A48"};

        int[] grades = {50, 67, 67, 67, 67, 100};
        double credits = 4.0;
        myGrades = new Grades(courses, grades, credits);
        CSPOSt csPOSt = new CSPOSt(myGrades);
        boolean[] result = csPOSt.qualifiedPOSts();
        boolean[] expectedResult = {true, true, true};

        assertArrayEquals(expectedResult, result);
    }
    @Test
    public void csTest9() {
        // Passed all 6 cs courses, credit above 4.0 but took MAT A23 instead of MAT A22
        // will make minor, but not major nor specialist
        String[] courses = { "CSC A08", "CSC/MAT A67", "MAT A23", "MAT A31",
                "MAT A37", "CSC A48"};

        int[] grades = {100, 100, 100, 100, 100, 100};
        double credits = 4.0;
        myGrades = new Grades(courses, grades, credits);
        CSPOSt csPOSt = new CSPOSt(myGrades);
        boolean[] result = csPOSt.qualifiedPOSts();
        boolean[] expectedResult = {true, false, false};

        assertArrayEquals(expectedResult, result);
    }


    @Test
    public void csTest10() {
        // Make Specialist for Math but is not even qualified for CS POSt
        String[] courses = { "CSC A08", "CSC/MAT A67", "MAT A22", "MAT A31",
                "MAT A37"};

        int[] grades = {50, 73, 73, 67, 67};
        double credits = 4.0;
        myGrades = new Grades(courses, grades, credits);
        CSPOSt csPOSt = new CSPOSt(myGrades);
        boolean[] result = csPOSt.qualifiedPOSts();
        boolean[] expectedResult = {false, false, false};

        assertArrayEquals(expectedResult, result);
    }

    @Test
    public void csTest11() {
        // Passed Specialist for stats but is not qualified for CS POSt
        String[] courses = {"CSC A08", "CSC/MAT A67", "MAT A22", "MAT A31",
                "MAT A37"};

        int[] grades = {100, 67, 67, 67, 67};
        double credits = 4.0;
        myGrades = new Grades(courses, grades, credits);
        CSPOSt csPOSt = new CSPOSt(myGrades);
        boolean[] result = csPOSt.qualifiedPOSts();
        boolean[] expectedResult = {false, false, false};

        assertArrayEquals(expectedResult, result);
    }

    @Test
    public void statsTest1() {
        // empty StatsPOSt
        String[] courses = {};
        int[] grades = {};
        double credits = 0;
        myGrades = new Grades(courses, grades, credits);
        StatsPOSt statsPOSt = new StatsPOSt(myGrades);
        boolean[] result = statsPOSt.qualifiedPOSts();
        boolean[] expectedResult = {false, false, false};

        assertArrayEquals(expectedResult, result);
    }

    @Test
    public void statsTest2() {
        // Less than 5 stats courses (Not even qualified for applying POSt) But credit over 4.0,
        // grades are all 100
        String[] courses = {"MAT A31", "CSC A08", "CSC/MAT A67", "MAT A22"};

        int[] grades = {100, 100, 100, 100};
        double credits = 4.0;
        myGrades = new Grades(courses, grades, credits);
        StatsPOSt statsPOSt = new StatsPOSt(myGrades);
        boolean[] result = statsPOSt.qualifiedPOSts();
        boolean[] expectedResult = {false, false, false};

        assertArrayEquals(expectedResult, result);
    }
    @Test
    public void statsTest3() {
        // Took all 5 stats courses (includes 4 major requirement courses), credit over 4.0, and
        // GPA among required courses is above 2.3 but failed in one of
        // the required courses hence can't apply
        // will not make minor, major, nor specialist
        String[] courses = {"CSC A08", "CSC/MAT A67", "MAT A22", "MAT A31",
                "MAT A37"};

        int[] grades = {100, 100, 100, 100, 5};
        double credits = 4.0;
        myGrades = new Grades(courses, grades, credits);
        StatsPOSt statsPOSt = new StatsPOSt(myGrades);
        boolean[] result = statsPOSt.qualifiedPOSts();
        boolean[] expectedResult = {false, false, false};

        assertArrayEquals(expectedResult, result);
    }

    @Test
    public void statsTest4() {
        // Passed all 5 stats courses (includes 4 major requirement courses
        // along with some other courses), credit over 4.0, and
        // GPA among all courses are above 2.3 but GPA among the requirement courses for
        // stats major is less than 2.3
        // will make minor but not major nor specialist
        String[] courses = {"CSC A08", "CSC/MAT A67", "MAT A22", "MAT A31", "LIN A01",
                "MAT A37", "CSC C37"};

        int[] grades = {67, 67, 67, 67, 67, 63, 80};
        double credits = 4.0;
        myGrades = new Grades(courses, grades, credits);
        StatsPOSt statsPOSt = new StatsPOSt(myGrades);
        boolean[] result = statsPOSt.qualifiedPOSts();
        boolean[] expectedResult = {true, false, false};

        assertArrayEquals(expectedResult, result);
    }

    @Test
    public void statsTest5() {
        // Passed all 5 stats courses (includes 4 major requirement courses), and gpa above 2.3
        // for required courses but credit below 4.0 so can't apply
        // will not make minor, major, nor specialist
        String[] courses = {"CSC A08", "CSC/MAT A67", "MAT A22", "MAT A31",
                "MAT A37"};

        int[] grades = {100, 100, 100, 100, 100};
        double credits = 3.5;
        myGrades = new Grades(courses, grades, credits);
        CSPOSt csPOSt = new CSPOSt(myGrades);
        boolean[] result = csPOSt.qualifiedPOSts();
        boolean[] expectedResult = {false, false, false};

        assertArrayEquals(expectedResult, result);
    }

    @Test
    public void statsTest6() {
        // Passed all 5 stats courses (includes 4 major requirement courses), credit above 4.0,
        // and gpa above 2.3 for required courses for major, but < 2.5
        // among required courses for specialist
        // will make minor, but not major nor specialist
        String[] courses = {"CSC A08", "CSC/MAT A67", "MAT A22", "MAT A31",
                "MAT A37"};

        int[] grades = {67, 51, 67, 67, 67};
        double credits = 4.0;
        myGrades = new Grades(courses, grades, credits);
        StatsPOSt statsPOSt = new StatsPOSt(myGrades);
        boolean[] result = statsPOSt.qualifiedPOSts();
        boolean[] expectedResult = {true, true, false};

        assertArrayEquals(expectedResult, result);
    }

    @Test
    public void statsTest7() {
        // Passed all 5 stats courses (includes 4 major requirement courses), credit above 4.0,
        // and gpa above 2.3 for required courses for major, and gpa over 2.5 among
        // CSC A20, CSC/MATA67, MATA22, MATA31, MATA37 (A20 is not substitutable for specialist
        // although it is for minor)
        // will make minor, major but not specialist
        String[] courses = {"CSC A20", "CSC/MAT A67", "MAT A22", "MAT A31",
                "MAT A37"};

        int[] grades = {100, 100, 100, 100, 100};
        double credits = 4.0;
        myGrades = new Grades(courses, grades, credits);
        StatsPOSt statsPOSt = new StatsPOSt(myGrades);
        boolean[] result = statsPOSt.qualifiedPOSts();
        boolean[] expectedResult = {true, true, false};

        assertArrayEquals(expectedResult, result);
    }

    @Test
    public void statsTest8() {
        // Passed all 5 stats courses (includes 5 specialist requirement courses), credit above 4.0,
        // and gpa above 2.5 for required courses for specialist,
        // but gpa below 2.3 for required courses for major
        // will make minor and specialist but not major
        String[] courses = {"CSC A08", "CSC/MAT A67", "MAT A22", "MAT A31",
                "MAT A37"};

        int[] grades = {67, 100, 67, 67, 66};
        double credits = 4.0;
        myGrades = new Grades(courses, grades, credits);
        StatsPOSt statsPOSt = new StatsPOSt(myGrades);
        boolean[] result = statsPOSt.qualifiedPOSts();
        boolean[] expectedResult = {true, false, true};

        assertArrayEquals(expectedResult, result);
    }

    @Test
    public void statsTest9() {
        // Passed all 5 stats courses (includes 5 specialist requirement courses), credit above 4.0,
        // and gpa above 2.5 for required courses for specialist,
        // and gpa above 2.3 for required courses for major
        // will make minor, major, and specialist
        String[] courses = {"CSC A08", "CSC/MAT A67", "MAT A22", "MAT A31",
                "MAT A37"};

        int[] grades = {100, 67, 67, 67, 67};
        double credits = 4.0;
        myGrades = new Grades(courses, grades, credits);
        StatsPOSt statsPOSt = new StatsPOSt(myGrades);
        boolean[] result = statsPOSt.qualifiedPOSts();
        boolean[] expectedResult = {true, true, true};

        assertArrayEquals(expectedResult, result);
    }

    @Test
    public void statsTest10() {
        // Make Specialist for Math but does not meet stats major nor specialist
        String[] courses = {"CSC A08", "CSC/MAT A67", "MAT A22", "MAT A31",
                "MAT A37"};

        int[] grades = {50, 73, 73, 67, 67};
        double credits = 4.0;
        myGrades = new Grades(courses, grades, credits);
        StatsPOSt statsPOSt = new StatsPOSt(myGrades);
        boolean[] result = statsPOSt.qualifiedPOSts();
        boolean[] expectedResult = {true, false, false};

        assertArrayEquals(expectedResult, result);
    }

    @Test
    public void statsTest11() {
        // Passed Specialist for CS but does not make Stats Specialist nor Stats Major
        String[] courses = {"CSC A08", "CSC/MAT A67", "MAT A22", "MAT A31",
                "MAT A37", "CSC A48"};

        int[] grades = {50, 67, 67, 67, 67, 100};
        double credits = 4.0;
        myGrades = new Grades(courses, grades, credits);
        StatsPOSt statsPOSt = new StatsPOSt(myGrades);
        boolean[] result = statsPOSt.qualifiedPOSts();
        boolean[] expectedResult = {true, false, false};

        assertArrayEquals(expectedResult, result);
    }

    @Test
    public void statsTest12() {
        // Passed all 5 stats courses (includes 5 specialist requirement courses), credit above 4.0,
        // and gpa above 2.5 for required courses for specialist,
        // and gpa above 2.3 for required courses for major
        // but no meet requirement for machine specialist
        // will make minor, major, and specialist
        String[] courses = {"CSC A08", "CSC/MAT A67", "MAT A22", "MAT A31",
                "MAT A37", "CSC A48"};

        int[] grades = {100, 67, 67, 67, 67, 70};
        double credits = 4.0;
        myGrades = new Grades(courses, grades, credits);
        StatsPOSt statsPOSt = new StatsPOSt(myGrades);
        boolean[] postResult = statsPOSt.qualifiedPOSts();
        boolean[] result = new boolean[4];
        for (int i = 0; i < 3; i++) {
            result[i] = postResult[i];
        }
        result[3] = statsPOSt.qualifiedForMachine();
        boolean[] expectedResult = {true, true, true, false};

        assertArrayEquals(expectedResult, result);
    }

    @Test
    public void statsTest13() {
        // Passed all 5 stats courses (includes 5 specialist requirement courses), credit above 4.0,
        // and gpa above 2.5 for required courses for specialist,
        // and gpa above 2.3 for required courses for major and 73+ for CSC A48
        // will make minor, major, and specialist
        String[] courses = {"CSC A08", "CSC/MAT A67", "MAT A22", "MAT A31",
                "MAT A37", "CSC A48"};

        int[] grades = {100, 67, 67, 67, 67, 73};
        double credits = 4.0;
        myGrades = new Grades(courses, grades, credits);
        StatsPOSt statsPOSt = new StatsPOSt(myGrades);
        boolean[] postResult = statsPOSt.qualifiedPOSts();
        boolean[] result = new boolean[4];
        for (int i = 0; i < 3; i++) {
            result[i] = postResult[i];
        }
        result[3] = statsPOSt.qualifiedForMachine();
        boolean[] expectedResult = {true, true, true, true};

        assertArrayEquals(expectedResult, result);
    }
}