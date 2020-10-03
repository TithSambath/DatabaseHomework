package DatabaseHomework;

import netscape.security.UserTarget;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SchoolManagerment {
    private String url = "jdbc:sqlserver://DESKTOP-42TKEOP:1433;databaseName=Homework01";
    private String user = "Sambath";
    private String password = "012886342";
    private void updateStuName(String id,String name){
        try{
            String sql = "UPDATE TbStudent " +
                    "SET StudentName =" + " '" + name + "' " +
                    "WHERE StudentID = " + id;
            Connection connection = DriverManager.getConnection(url,user,password);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Updated!");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    private void updateStuGender(String id,String gender){
        try{
            String sql = "UPDATE TbStudent " +
                    "SET Gender =" + "'" + gender + "'" +
                    "WHERE StudentID = " + id;
            Connection connection = DriverManager.getConnection(url,user,password);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Updated!");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    private void updateStuPhoneNo(String id,String phoneNo){
        try{
            String sql = "UPDATE TbStudent " +
                    "SET PhoneNo =" + "'" + phoneNo + "'" +
                    "WHERE StudentID = " + id;
            Connection connection = DriverManager.getConnection(url,user,password);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Updated!");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void displayStudent(){
        try{
            String query = "SELECT * FROM TbStudent";
            Connection connection = DriverManager.getConnection(url,user,password);
            Statement statement = connection.createStatement();
            statement.executeQuery(query);
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()){
                String id = resultSet.getString("StudentID");
                String name = resultSet.getString("StudentName");
                String gender = resultSet.getString("Gender");
                String phoneNo = resultSet.getString("PhoneNo");
                System.out.print(id + "\t" + name + "\t" + gender + "\t" + phoneNo);
                System.out.println("");
            }
        }catch (SQLException e){
            System.out.println("Fail to display!");
            e.printStackTrace();
        }
    }
    public void displayCourse(){
        try{
            String query = "SELECT * FROM TbCourse";
            Connection connection = DriverManager.getConnection(url,user,password);
            Statement statement = connection.createStatement();
            statement.executeQuery(query);
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()){
                String id = resultSet.getString("CourseID");
                String name = resultSet.getString("CourseName");
                String credit = resultSet.getString("NoOfCredit");
                String hours = resultSet.getString("NoOfHours");
                System.out.print(id + "\t" + name + "\t" + credit + "\t" + hours);
                System.out.println("");
            }
        }catch (SQLException e){
            System.out.println("Fail to display!");
            e.printStackTrace();
        }
    }
    public void enrollStudent(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Fill in your information below : ");
        System.out.print("ID : ");
        String id = scan.nextLine();
        System.out.print("Name : ");
        String name = scan.nextLine();
        System.out.print("Gender : ");
        String gender = scan.nextLine();
        System.out.print("PhoneNo : ");
        String phoneNo = scan.nextLine();
        try{
            String sql = "INSERT INTO TbStudent VALUES(?,?,?,?)";
            Connection connection = DriverManager.getConnection(url,user,password);
            PreparedStatement pstatement = connection.prepareStatement(sql);
            pstatement.setString(1,id);
            pstatement.setString(2,name);
            pstatement.setString(3,gender);
            pstatement.setString(4,phoneNo);
            pstatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void deleteStudentByID(String id){
        try{
            Connection connection = DriverManager.getConnection(url,user,password);
            String query = "DELETE FROM TbStudent WHERE StudentID = " + id;
            PreparedStatement pStatement = connection.prepareStatement(query);
            //pStatement.setString(1,id);
            pStatement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void searchStuById(String id){
        try{
            Connection connection = DriverManager.getConnection(url,user,password);
            String query = "SELECT * FROM TbStudent WHERE StudentID = " + id;
            Statement statement = connection.createStatement();
            statement.executeQuery(query);
            ResultSet result = statement.getResultSet();
            if (result.next()){
                System.out.println(
                        result.getString(1) + " "
                                + result.getString(2) + " "
                                + result.getString(3) + " "
                                + result.getString(4)
                );
            }else System.out.println("Result: Not Found! Student ID : " + id);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void updateStuInfo(String id,String name,String gender,String phoneNo){
        updateStuName(id,name);updateStuGender(id,gender);updateStuPhoneNo(id,phoneNo);
    }
    public void addNewCourse(String id,String courseName,String NoOfCredit,String NoOfHours){
        try{
            String sql = "INSERT INTO TbCourse VALUES(?,?,?,?)";
            Connection connection = DriverManager.getConnection(url,user,password);
            PreparedStatement pstatement = connection.prepareStatement(sql);
            pstatement.setString(1,id);
            pstatement.setString(2,courseName);
            pstatement.setString(3,NoOfCredit);
            pstatement.setString(4,NoOfHours);
            pstatement.executeUpdate();
            System.out.println("Course is Registered !");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void enrollStuInACourse(String studentID, ArrayList<String> coursesID){
        String query = "INSERT INTO StudentDetail VALUES(?,?,?)";
        try{
            Connection connection = DriverManager.getConnection(url,user,password);
            PreparedStatement pstatement = connection.prepareStatement(query);
            for (String c : coursesID) {
                pstatement.setString(1, studentID);
                pstatement.setString(2, c);
                pstatement.setString(3, "0");
                pstatement.executeUpdate();
            }
            System.out.println("Enroll to Courses Successfully !");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void showAllCourseAttendedByStudent(String id){
        String query = "SELECT TbStudent.StudentID,StudentName,CourseName, NoOfCredit, NoOfHours\n" +
                       "FROM TbStudent \n" +
                       "INNER JOIN (StudentDetail \n" +
                       "INNER JOIN TbCourse ON StudentDetail.CourseID = TbCourse.CourseID) ON  TbStudent.StudentID = StudentDetail.StudentID\n" +
                       "WHERE TbStudent.StudentID = 4;";
        try{
            Connection connection = DriverManager.getConnection(url,user,password);
            Statement statement = connection.createStatement();
            statement.executeQuery(query);
            ResultSet resultSet = statement.getResultSet();
            System.out.println("Result : ");
            while (resultSet.next()){
                System.out.println(
                        resultSet.getString("StudentID") + " " +
                        resultSet.getString("StudentName") + " " +
                        resultSet.getString("CourseName") + " " +
                        resultSet.getString("NoOfCredit") + " " +
                        resultSet.getString("NoOfHours")
                );
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

}
