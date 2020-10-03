package DatabaseHomework;

import java.sql.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        System.out.println("Successfully : Connected to Database Server.");
        SchoolManagerment schoolManagerment = new SchoolManagerment();
//        schoolManagerment.enrollStudent();
        schoolManagerment.displayStudent();

//        schoolManagerment.addNewCourse("2","Cloud Computing","4","150");
//        schoolManagerment.addNewCourse("3","Programming","3","180");
//        schoolManagerment.addNewCourse("4","Network Engineer","3","180");
//        schoolManagerment.addNewCourse("5","Data Structure","3","100");

        ArrayList<String> coursesID = new ArrayList<String>();
        coursesID.add("4");coursesID.add("2");coursesID.add("3");
//        ArrayList<String> finalScore = new ArrayList<String>();
//        finalScore.add("100");finalScore.add("90");finalScore.add("85");
        //schoolManagerment.enrollStuInACourse("4",coursesID);
        //schoolManagerment.showAllCourseAttendedByStudent("4");
        //schoolManagerment.deleteStudentByID("3");
        //schoolManagerment.displayCourse();
    }
}
