package todo;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Scanner;

public class ToDo {

 public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Scanner s = new Scanner(System.in);
         
        int choice = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try( Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ToDo?", "root", "")) 
            {
           
               do {
                    System.out.println("To-Do List Management");
                    System.out.println("");
                    System.out.println("You Can Only Enter 1 To 5 for this Databse.");
                    System.out.println(" ___________________________________");
                    System.out.println("");
                    System.out.println("Enter '1' For Adding new To Do Work");
                    System.out.println("");
                    System.out.println("Enter '2' To Show The List");
                    System.out.println("");
                    System.out.println("Enter '3' For Updating List");
                    System.out.println("");
                    System.out.println("Enter '4' To Delete Any Item");
                    System.out.println("");
                    System.out.println("Enter '5' To Exit.");
                    System.out.println("");
                    System.out.print("Enter a Number To Continue: ");
                    choice = s.nextInt();
                    switch (choice) {
                        case 1:
                            try {
                            System.out.println("Enter Your Task No : ");
                            int No = s.nextInt();
                            System.out.println("Enter Priority : ");
                            String Priority = s.next();
                            System.out.println("Assigned to : ");
                            String Assigned_to = s.next();
                            System.out.println("Enter Note : ");
                            String Note = s.next();
                            String sql = "insert into list values (?,?,?,?);";
                            PreparedStatement stmt = con.prepareStatement(sql);
                            stmt.setInt(1, No);
                            stmt.setString(2, Priority);
                            stmt.setString(4, Assigned_to);
                            stmt.setString(3,Note);
                            stmt.execute();
                            System.out.println("New Input Added Successfully!");
                            System.out.println("");
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                        case 2:
                            try {
                            System.out.println("Enter Your Task No to see the Data : ");
                            Scanner input = new Scanner(System.in);
                            int No = input.nextInt();
                            String sql = "select * from list where No=?;";
                            PreparedStatement stmt = con.prepareStatement(sql);
                            stmt.setInt(1, No);
                            ResultSet rs = stmt.executeQuery();
                            while (rs.next()) {

                                System.out.println("No = " + rs.getInt(1));
                                System.out.println("Priority = " + rs.getString(2));
                                System.out.println("Assigned to = " + rs.getString(4));
                                System.out.println("Note = " + rs.getString(3));
                            }
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                        case 3:
                            try {
                            System.out.println("Enter Your Task No : ");
                            int No = s.nextInt();
                            System.out.println("Enter Priority Input : ");
                            String Priority = s.next();
                            String sql = "update list set Priority= ? where No = ?";
                            PreparedStatement statement = con.prepareStatement(sql);
                            statement.setString(1, Priority);
                            statement.setInt(2,No);
                            int rowsUpdated = statement.executeUpdate();
                            if (rowsUpdated > 0) {
                                System.out.println("Data Updated Successfully!");
                            } else {
                                System.out.println("There is no Record Founded in This Database That You Entered");
                            }
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                        case 4:
                            try {
                            System.out.println("Enter Task No To Delete Data : ");
                            int No = s.nextInt();
                            String sql = "delete from list where No = ?;";
                            PreparedStatement statement = con.prepareStatement(sql);
                            statement.setInt(1, No);
                            int rowsUpdated = statement.executeUpdate();
                            if (rowsUpdated > 0) {
                                System.out.println("Data Deleted Successfully!");
                            } else {
                                System.out.println("There is no Record Founded in This Database That You Entered");
                            }
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                        case 5:
                            System.out.println("");
                            break;
                        default:
                            System.out.println("Please Enter '1-5' Option!");
                            break;
                    }

                } while (choice != 5);
                System.out.println("Thank You!");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            
        }
    }
}
