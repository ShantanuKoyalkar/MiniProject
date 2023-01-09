import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
//AUTHOR:SHANTANU DHANANJAY KOYALKAR
public class TestDemo {
	protected static int idfinal;
	protected static String namefinal;
	protected static int score;
	public static void studentInfo() throws SQLException {
//to get id name from student who are attending test
		new TestDemo();
		System.out.println("Enter your id");
		Scanner scanner = new Scanner(System.in);
		TestDemo.idfinal = scanner.nextInt();
		System.out.println("Enter your name");
		TestDemo.namefinal = scanner.next();
//passing id,name into quizMain method to let them appear for the exam
		quizMain(TestDemo.idfinal, TestDemo.namefinal);
	}
//AUTHOR:SHANTANU DHANANJAY KOYALKAR
//Actual test will be executed by METHOD QUIZMAIN()
	public static void quizMain(int id, String name) throws SQLException {
		System.out.println("Lets Start Your Exam>>>" + name);
		Connection con = null;
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject", "root", "Dhananjay@1234");
			ps = con.prepareStatement("select * from test");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString("questions"));
				Scanner scanner = new Scanner(System.in);
				String answer = scanner.next();
				if (answer.equals(rs.getString("answers"))) {
					score++;
				}
			}
			System.out.println("Your Score is>>" + score + "/10");
//Grading system created here for evaluation
			if (score > 8 && score <= 10) {
				System.out.println("You Got Grade-A");
			} else if (score >= 6 && score <= 8) {
				System.out.println("You got Grade-B");
			} else if (score == 5) {
				System.out.println("You Got Grade-C");
			} else {
				System.out.println("You Failed");
			}
//TO STORE DATA INTO DATABASE AFTER EVERY STUDENT APPEARS FOR EXAM WE ARE CALLING saveDataIntoSql();		
			saveDataIntoSql();
//FOR NEXT STUDENT SCORE MUST BE ZERO AT THE START OF TEST
			score = 0;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
			ps.close();
		}
	}
//METHOD FOR SAVING DATA INTO MYSQL DATABASE
//AUTHOR:SHANTANU DHANANJAY KOYALKAR
	public static void saveDataIntoSql() throws SQLException {
		
		Connection con = null;
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject", "root", "Dhananjay@1234");
			ps = con.prepareStatement("insert into studentdata(studid,name,score) values(?,?,?)");
			ps.setInt(1, idfinal);
			ps.setString(2, namefinal);
			ps.setInt(3, score);
			int a = ps.executeUpdate();
			System.out.println("your Data stored into Database for current test" + a);
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			con.close();
			ps.close();
		}
	}
//MAIN() METHOD TO CALL studentInfo(); FROM THERE quizMain(int id, String name); TO saveDataIntoSql(); 
public static void main(String[] args) throws SQLException {
//AUTHOR:SHANTANU DHANANJAY KOYALKAR
	System.out.println("Enter number of students attending test>>");
	Scanner scanner=new Scanner(System.in);
	int number=scanner.nextInt();
		for (int i = 1; i <= number; i++) {
			studentInfo();
		}
	}
}