import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
//AUTHOR:VAIBHAV SHALIGRAM TADE
public class StudentResults {
//METHOD TO RETRIEVE DATA BY ID
	public static void retrieveStudentData() throws SQLException {
		Connection con = null;
		PreparedStatement preparestatement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject", "root", "Dhananjay@1234");
			preparestatement = con.prepareStatement("select * from studentdata");
			ResultSet resultset = preparestatement.executeQuery();
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter your Student ID of quiz>>");
			int enterid = scanner.nextInt();
			while (resultset.next()) {
				if (enterid == resultset.getInt(2)) {
					System.out.println(
							"YOUR DATA IS>>>" + " {NAME=" + resultset.getString(3) + ",SCORE=" + resultset.getInt(4)+"}");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
			preparestatement.close();
		}
	}
//AUTHOR:VAIBHAV SHALIGRAM TADE
	public static void sortStudentData() throws SQLException {
//METHOD TO EXTRACT ALL THE DATA OF STUDENT ATTENDED THE EXAM IN SORTING ORDER
		Connection con = null;
		PreparedStatement preparedstatement = null;
		HashMap<Integer, String> hm = null;
		HashMap<Integer, Integer> hm2 = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject", "root", "Dhananjay@1234");
			preparedstatement = con.prepareStatement("select * from studentdata");
			ResultSet resultset = preparedstatement.executeQuery();
			while (resultset.next()) {
//EXTRACTING DATA FROM MYSQL
				hm = new HashMap<Integer, String>();
				hm.put(resultset.getInt(2), resultset.getString(3));
				ArrayList<Integer> al = new ArrayList<Integer>(hm.keySet());
				ArrayList<String> al2 = new ArrayList<String>(hm.values());
				hm2 = new HashMap<Integer, Integer>();
				hm2.put(resultset.getInt(1), resultset.getInt(4));
				ArrayList<Integer> al3 = new ArrayList<Integer>(hm2.values());
				Set s1 = hm.keySet();
				Collections.sort(al);
				Collections.sort(al2);
				Collections.sort(al3);
				System.out.println("ID>>" + al + " NAME>>" + al2 + " SCORE>>" + al3);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
			preparedstatement.close();
		}
	}
	public static void main(String[] args) throws SQLException {
		System.out.println("Select operation you want to do>>" + " 1)-Retrieve student data by Id's"
				+ " 2)-All student data display in sorting order");
		Scanner scanner = new Scanner(System.in);
		int select = scanner.nextInt();
		if (select==1) {
			retrieveStudentData();
		} else {
			sortStudentData();
		}
	}
}
